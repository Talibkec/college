# Database changes – dynamic-content phase

Approved in `docs/DYNAMIC_CONTENT_FINDINGS.md` §8 (D1, D15–D17) and on 2026-10-19 (page blocks, existing page texts). The application never
changes the schema itself (`spring.jpa.hibernate.ddl-auto=none`): **every change below must be run by hand on the
production database before the new version is deployed.** Every script is idempotent (`CREATE TABLE IF NOT EXISTS`,
`INSERT IGNORE`, `WHERE NOT EXISTS`, "seed only if empty"), so running it twice does no harm and never overwrites
rows changed later in the application. No existing table or column is changed; only new tables and new rows.

Only text goes into these files: DDL and values already public on the website. No data dumps, no credentials.
**Keep this file up to date with every database change** (section "Change log" at the end).

## Production: one command

`apply_production.sh` does everything below in the right order: asks the password once, makes the backup, runs the
table scripts and the data scripts, and prints the check queries with the expected values.

```bash
bash docs/sql/dynamic/apply_production.sh -u <db user> -d <database>          # optional: -h <host> -P <port> --no-backup
```

It asks for confirmation ("yes") before changing anything. It is safe to run again with every later release: new seed
rows are added, rows that exist (including texts changed on the website) are left alone. Restart the application
afterwards (the website caches settings, links and texts).

## Production: SQL to run by hand (in this order)

The same steps one by one, if you prefer. Run on the production server from the repository folder `docs/sql/dynamic` (or copy the folder there).
Replace `<user>` and the database name if they differ; `-p` asks for the password (never put it on the command line).

```bash
cd docs/sql/dynamic
DB=keckacin_college2

# 0. backup first (keep the file outside the repository, never commit it)
mysqldump -u <user> -p --single-transaction --routines "$DB" | gzip > ~/backup-before-dynamic-$(date +%F-%H%M).sql.gz

# 1. new tables (DDL)
mysql -u <user> -p "$DB" < 01_site_setting.sql
mysql -u <user> -p "$DB" < 02_page_section.sql
mysql -u <user> -p "$DB" < 03_link_item.sql
mysql -u <user> -p "$DB" < 04_contact_person.sql
mysql -u <user> -p "$DB" < 05_site_image.sql
mysql -u <user> -p "$DB" < 06_fee_item.sql
mysql -u <user> -p "$DB" < 07_department_site.sql
mysql -u <user> -p "$DB" < 08_notice_type.sql
mysql -u <user> -p "$DB" < 09_sms_group.sql
mysql -u <user> -p "$DB" < 10_content_area.sql
mysql -u <user> -p "$DB" < 11_document_type.sql
mysql -u <user> -p "$DB" < 12_content_change_log.sql
mysql -u <user> -p "$DB" < 13_content_file.sql
mysql -u <user> -p "$DB" < 14_page_block.sql

# 2. data (seeds) – utf8mb4 so that special characters arrive unchanged
mysql -u <user> -p --default-character-set=utf8mb4 "$DB" < 10_roles_seed.sql
mysql -u <user> -p --default-character-set=utf8mb4 "$DB" < 10_content_area_seed.sql
mysql -u <user> -p --default-character-set=utf8mb4 "$DB" < 07_department_site_seed.sql
mysql -u <user> -p --default-character-set=utf8mb4 "$DB" < 01_site_setting_seed.sql
mysql -u <user> -p --default-character-set=utf8mb4 "$DB" < 02_page_section_seed.sql
mysql -u <user> -p --default-character-set=utf8mb4 "$DB" < 03_link_item_seed.sql
mysql -u <user> -p --default-character-set=utf8mb4 "$DB" < 15_page_section_pages_seed.sql
```

To avoid typing the password 20 times: run `mysql -u <user> -p --default-character-set=utf8mb4 "$DB"` once and enter
`source 01_site_setting.sql;` … for each file in the same order.

### Check after running

```sql
SELECT COUNT(*) FROM content_area;                                   -- 31
SELECT COUNT(*) FROM content_area_role;                              -- 79 (more if changed in /auth/roles/areas)
SELECT name FROM test_role ORDER BY id;                              -- Admin, Faculty, SM, HOD, SK + 12 new roles
SELECT COUNT(*) FROM department_site;                                -- 10
SELECT COUNT(*) FROM site_setting;                                   -- 15
SELECT COUNT(*) FROM page_section;                                   -- 142 (5 About + 16 Academic + 19 Facilities + 11 T&P/Approval/RTI + 5 Legal + 6 Home + 9 CSE + 8 ASH + 9 Civil + 13 ECE + 13 ECE-VLSI + 10 EEE + 9 FP + 9 Manufacturing; grows with each group of pages)
SELECT list_code, COUNT(*) FROM link_item GROUP BY list_code;        -- 10 lists, 129 entries in total
SELECT setting_value FROM site_setting WHERE setting_key = 'links.main_menu.enabled';   -- no
SHOW TABLES LIKE 'page_block'; SHOW TABLES LIKE 'content_file';      -- both exist (empty)
SHOW VARIABLES LIKE 'max_allowed_packet';                            -- must be > 20 MB (uploads); MySQL 8 default 64 MB
```

### Rollback (if the new version has to be removed)

Deploy the previous WAR first, then (the new tables are not referenced by any existing table):

```sql
DROP TABLE IF EXISTS page_block, content_file, content_change_log, document_type, content_area_role, content_area,
  sms_group_member, sms_group, notice_type, department_site, fee_item, site_image, contact_person, link_item,
  page_section, site_setting;
DELETE ur FROM user_role ur JOIN test_role r ON r.id = ur.role_id
  WHERE r.name IN ('SubAdmin', 'Principal', 'Registrar', 'ExamController', 'TPO', 'NBA', 'Accounts', 'Librarian',
                   'Warden', 'PIO', 'ClubInCharge', 'Staff');
DELETE FROM test_role WHERE name IN ('SubAdmin', 'Principal', 'Registrar', 'ExamController', 'TPO', 'NBA', 'Accounts',
                   'Librarian', 'Warden', 'PIO', 'ClubInCharge', 'Staff');
```
Or restore the backup of step 0. Content entered in the new screens (texts, links, blocks, uploaded files) is lost
with the tables.

## Files

| File | Kind | Used by |
|---|---|---|
| `01_site_setting.sql` | new table `site_setting` | site settings (step 3), on/off switches |
| `02_page_section.sql` | new table `page_section` | page texts (step 6) |
| `03_link_item.sql` | new table `link_item` | menus, link lists, sidebars (steps 4-5) |
| `04_contact_person.sql` … `06_fee_item.sql` | new tables `contact_person`, `site_image`, `fee_item` | later steps (people, images, fees) |
| `07_department_site.sql` | new table `department_site` | department scope of HOD/Faculty; later the departments step |
| `08_notice_type.sql`, `09_sms_group.sql` | new tables | later steps |
| `10_content_area.sql` | new tables `content_area`, `content_area_role` | roles and permissions (step 2) |
| `11_document_type.sql` | new table | document pages (later) |
| `12_content_change_log.sql` | new table | history of all changes (step 2) |
| `13_content_file.sql` | new table `content_file` | uploaded images/documents of page blocks |
| `14_page_block.sql` | new table `page_block` | headings, text, images, documents added to pages |
| `10_roles_seed.sql` | 12 new rows in the existing table `test_role` | roles |
| `10_content_area_seed.sql` | 31 areas + permission matrix | roles |
| `07_department_site_seed.sql` | the 10 department sites (department id, HOD account) | department scope |
| `01_site_setting_seed.sql` | 13 texts of header, footer, contact pages | site settings |
| `02_page_section_seed.sql` | 5 texts of the About pages + setting `principal.faculty_id` | page texts |
| `03_link_item_seed.sql` | 10 link lists (129 entries) + switch `links.main_menu.enabled` = no | menus and links |
| `15_page_section_pages_seed.sql` | existing texts of the pages (today: 16 texts of 12 Academic pages, 19 texts of 14 Facilities pages, 11 texts of 10 T&P, Approval and RTI pages, 5 texts of 5 Legal pages, 6 texts of the Home page, 9 texts of the CSE site, 8 texts of the ASH site, 9 texts of the Civil site, 13 texts of the ECE site, 13 texts of the ECE-VLSI site, 10 texts of the EEE site, 9 texts of the Food Processing site, 9 texts of the Manufacturing site), **generated** | page texts of all pages except About |
| `apply_production.sh` | runs all of the above in order (backup, tables, data, checks) | production deployment |
| `tools/page_sections_seed.py` | writes `15_page_section_pages_seed.sql` from the `<kec:section>` tags in the JSPs (`--check`: is it up to date?) | development |

### How the page texts are made editable (for the next groups of pages)

1. In the JSP, put `<kec:section key="<page key>/<name>" title="<Page: what>">…today's HTML…</kec:section>` around a
   static part of the page (no `${…}`, `<c:…>`, scripts or forms inside; the page key is the URL with dots,
   `/academic/admission` → `academic.admission`).
2. `python3 docs/sql/dynamic/tools/page_sections_seed.py` regenerates `15_page_section_pages_seed.sql`.
3. Apply it locally (`mysql … < 15_page_section_pages_seed.sql`), restart, run `PageTextsTest` (seed = JSP text, area =
   `PageKeys.area`) and the snapshot comparison (pages byte-identical).
4. Production: `apply_production.sh` again (or only the `15_…` command); restart.

## Change log (every database change of this phase)

| Date | Commit / step | Change | Production SQL |
|---|---|---|---|
| 2026-10-19 | step 1 (`3d7d094`) | new tables 01–12 | `01_…sql` – `12_…sql` |
| 2026-10-19 | step 2 (`d6db9b1`) | 12 new roles (`test_role` rows), 31 content areas, permission matrix | `10_roles_seed.sql`, `10_content_area_seed.sql` |
| 2026-10-19 | step 3 (`f59445c`) | 13 site settings | `01_site_setting_seed.sql` |
| 2026-10-19 | steps 4-6 (`3235fc8`) | `link_item` got column `css_class` (part of `03_link_item.sql`); 129 links; 5 page sections; settings `principal.faculty_id`, `links.main_menu.enabled` | `03_link_item.sql`, `03_link_item_seed.sql`, `02_page_section_seed.sql` |
| 2026-10-19 | page blocks (`f6295cb`) | new tables `content_file`, `page_block`; 10 department sites | `13_content_file.sql`, `14_page_block.sql`, `07_department_site_seed.sql` |
| 2026-10-19 | existing page texts: Academic (`cab352d`) | 16 new `page_section` rows (12 Academic pages); no schema change | `15_page_section_pages_seed.sql` |
| 2026-10-19 | existing page texts: Facilities (`2af314b`) | 19 new `page_section` rows (14 Facilities pages); no schema change. Production: run `15_page_section_pages_seed.sql` again (INSERT IGNORE: existing rows untouched) or `apply_production.sh` | `15_page_section_pages_seed.sql` |
| 2026-10-20 | existing page texts: T&P, Approval, RTI (`1c6f0cb`) | 11 new `page_section` rows (10 pages); no schema change. Production: run `15_page_section_pages_seed.sql` again or `apply_production.sh` | `15_page_section_pages_seed.sql` |
| 2026-10-20 | existing page texts: Legal (`27de1c0`) | 5 new `page_section` rows (5 pages); no schema change. Production: run `15_page_section_pages_seed.sql` again or `apply_production.sh` | `15_page_section_pages_seed.sql` |
| 2026-10-20 | existing page texts: Home (`b85d067`) | 6 new `page_section` rows (home page); no schema change. Production: run `15_page_section_pages_seed.sql` again or `apply_production.sh` | `15_page_section_pages_seed.sql` |
| 2026-10-20 | existing page texts: CSE site (`d750255`) | 9 new `page_section` rows (9 CSE pages); no schema change. Production: run `15_page_section_pages_seed.sql` again or `apply_production.sh` | `15_page_section_pages_seed.sql` |
| 2026-10-20 | existing page texts: ASH site (`448f2b6`) | 8 new `page_section` rows (8 ASH pages); no schema change. Production: run `15_page_section_pages_seed.sql` again or `apply_production.sh` | `15_page_section_pages_seed.sql` |
| 2026-10-20 | existing page texts: Civil site (`4d89cfe`) | 9 new `page_section` rows (9 Civil pages); no schema change. Production: run `15_page_section_pages_seed.sql` again or `apply_production.sh` | `15_page_section_pages_seed.sql` |
| 2026-10-20 | existing page texts: ECE site (`947a2ad`) | 13 new `page_section` rows (9 ECE pages); no schema change. Production: run `15_page_section_pages_seed.sql` again or `apply_production.sh` | `15_page_section_pages_seed.sql` |
| 2026-10-20 | existing page texts: ECE-VLSI site (`3b14d36`) | 13 new `page_section` rows (9 ECE-VLSI pages); no schema change. Production: run `15_page_section_pages_seed.sql` again or `apply_production.sh` | `15_page_section_pages_seed.sql` |
| 2026-10-20 | existing page texts: EEE site (`21e371e`) | 10 new `page_section` rows (9 EEE pages); no schema change. Production: run `15_page_section_pages_seed.sql` again or `apply_production.sh` | `15_page_section_pages_seed.sql` |
| 2026-10-20 | existing page texts: Food Processing site (`784b5c9`) | 9 new `page_section` rows (9 FP pages); no schema change. Production: run `15_page_section_pages_seed.sql` again or `apply_production.sh` | `15_page_section_pages_seed.sql` |
| 2026-10-20 | existing page texts: Manufacturing site | 9 new `page_section` rows (9 Manufacturing pages); no schema change. Production: run `15_page_section_pages_seed.sql` again or `apply_production.sh` | `15_page_section_pages_seed.sql` |

Only on the local development copy (already done there, **not needed in production**, where `03_link_item.sql` creates
the table with the column): `ALTER TABLE link_item ADD COLUMN css_class VARCHAR(100) NULL AFTER icon_class;`
