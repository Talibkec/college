#!/usr/bin/env python3
"""
Writes docs/sql/dynamic/15_page_section_pages_seed.sql from the <kec:section> tags in the JSPs.

Every <kec:section key="page/section" title="...">today's HTML</kec:section> becomes one page_section row whose body is
exactly the HTML between the tags (including line endings), so the page looks the same whether the row exists or not.
The About texts are seeded by 02_page_section_seed.sql and are skipped here.

Usage (from the repository root, after wrapping texts of more pages):
    python3 docs/sql/dynamic/tools/page_sections_seed.py           # writes the seed file
    python3 docs/sql/dynamic/tools/page_sections_seed.py --check   # exit code 1 if the file is out of date

The area of a page (who may edit it) follows PageKeys.area() in Java; PageSectionsSeedTest checks that both agree and
that every seeded body equals its JSP text.
"""
import os
import re
import sys

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), '..', '..', '..', '..'))
JSP_DIR = os.path.join(ROOT, 'src', 'main', 'webapp', 'jsp')
SQL_DIR = os.path.join(ROOT, 'docs', 'sql', 'dynamic')
OUT = os.path.join(SQL_DIR, '15_page_section_pages_seed.sql')
OLD_SEED = os.path.join(SQL_DIR, '02_page_section_seed.sql')

TAG = re.compile(r'<kec:section key="([^"]+)"(?: title="([^"]*)")?>(.*?)</kec:section>', re.S)


def area(page_key):
    """Mirror of com.college.core.tags.PageKeys.area()."""
    parts = page_key.split('.')
    page = parts[1].lower() if len(parts) > 1 else ''
    s = parts[0]
    if s == 'home':
        return 'home'
    if s == 'department':
        return 'department.' + parts[1]
    if s == 'about':
        return 'office' if page in ('staff', 'allotment', 'administration') else 'about'
    if s == 'academic':
        if page in ('calender', 'syllabus', 'attendance', 'universityresults'):
            return 'exam'
        if page == 'holidays':
            return 'office'
        if page == 'paymenttutorial':
            return 'fees'
        if page in ('lab', 'labs'):
            return 'facilities'
        return 'academic'
    if s == 'facilities':
        if page == 'bank':
            return 'fees'
        if page == 'centrallibrary':
            return 'library'
        if page == 'hostels':
            return 'hostel'
        if page in ('club', 'clubs', 'sportsfacilities', 'gymnasium'):
            return 'activities'
        if page in ('placementbrochure', 'startupcell'):
            return 'tp'
        if page == 'downloads':
            return 'downloads'
        if page == 'contactus':
            return 'site'
        return 'facilities'
    if s == 'trainingplacement':
        return 'tp'
    if s in ('approval', 'rti', 'gallery', 'nba'):
        return s
    if s == 'legal':
        if page in ('refundandcancellation', 'feestructure'):
            return 'fees'
        if page == 'contactus':
            return 'site'
        return 'legal'
    if s == 'category':
        if page in ('notices', 'tenders'):
            return 'notices'
        if page == 'kecwebteam':
            return 'system'
        return 'site'
    raise SystemExit('no area for page key ' + page_key)


def sql_string(text):
    return "'" + (text.replace('\\', '\\\\').replace("'", "''").replace('\r', '\\r').replace('\n', '\\n')
                  .replace('\0', '\\0').replace('\x1a', '\\Z')) + "'"


def sections():
    with open(OLD_SEED, encoding='utf-8') as f:
        seeded = set(re.findall(r"\('([^']+)', '([^']+)', '", f.read()))
    found, keys = [], set()
    for folder, _, files in sorted(os.walk(JSP_DIR)):
        for name in sorted(files):
            if not name.endswith('.jsp'):
                continue
            path = os.path.join(folder, name)
            with open(path, 'rb') as f:
                text = f.read().decode('utf-8')
            for key, title, body in TAG.findall(text):
                page_key, section_key = key.split('/')
                if (page_key, section_key) in seeded:
                    continue
                rel = os.path.relpath(path, ROOT)
                if key in keys:
                    raise SystemExit('%s: key %s is used twice' % (rel, key))
                if not title:
                    raise SystemExit('%s: <kec:section key="%s"> needs a title' % (rel, key))
                if '${' in body or '#{' in body or '<%' in body or '<c:' in body or '<jsp:' in body:
                    raise SystemExit('%s: %s contains JSP code; keep that outside the section' % (rel, key))
                if any(ord(ch) > 127 for ch in body):
                    raise SystemExit('%s: %s contains non-ASCII characters; check the page encoding first' % (rel, key))
                keys.add(key)
                found.append((page_key, section_key, area(page_key), title, body))
    order = {}
    rows = []
    for page_key, section_key, area_code, title, body in sorted(found, key=lambda r: r[0]):
        order[page_key] = order.get(page_key, 0) + 10
        rows.append((page_key, section_key, area_code, title, body, order[page_key]))
    return rows


def render(rows):
    out = ['-- Page texts of all pages except About (findings §1.2 T2). GENERATED by tools/page_sections_seed.py from the',
           '-- <kec:section> tags in the JSPs: do not edit by hand, run the script again after wrapping more texts.',
           '-- Bodies = today\'s exact HTML of the JSPs (incl. line endings), so the pages stay byte-identical.',
           '-- Idempotent: INSERT IGNORE (unique page_key + section_key) never overwrites a text changed in /manage/sections.',
           '-- Run with: mysql --default-character-set=utf8mb4 ...']
    if rows:
        out.append('INSERT IGNORE INTO page_section (page_key, section_key, area_code, title, body_html, sort_order) VALUES')
        values = ['  (%s, %s, %s, %s, %s, %d)' % (sql_string(p), sql_string(s), sql_string(a), sql_string(t), sql_string(b), o)
                  for p, s, a, t, b, o in rows]
        out.append(',\n'.join(values) + ';')
    return '\n'.join(out) + '\n'


def main():
    rows = sections()
    content = render(rows)
    if '--check' in sys.argv:
        current = open(OUT, encoding='utf-8').read() if os.path.exists(OUT) else ''
        if current != content:
            print('15_page_section_pages_seed.sql is out of date: run docs/sql/dynamic/tools/page_sections_seed.py')
            sys.exit(1)
        print('up to date (%d texts)' % len(rows))
        return
    with open(OUT, 'w', encoding='utf-8', newline='\n') as f:
        f.write(content)
    print('wrote %s (%d texts on %d pages)' % (os.path.relpath(OUT, ROOT), len(rows), len({r[0] for r in rows})))


if __name__ == '__main__':
    main()
