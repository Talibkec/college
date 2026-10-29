# UI Audit – Katihar Engineering College website (JSP)

- Date: 2026-10-29
- Branch: `kec_claude` (at `372d740`)
- Scope: all 260 JSPs under `src/main/webapp/jsp/` except store management (out of scope, see `CLAUDE.md`).
- Method: code inspection only (page markup, `header.jsp` inline styles, `footer.jsp`, `css/style.css`, loaded
  frameworks: Tailwind 2.2.19 + Flowbite; Bootstrap is **not** loaded on public pages). No headless browser was
  available, so nothing was checked in a rendered browser.
- Audit only: no code was changed.

Paths are relative to `src/main/webapp/jsp/`.
**"All dept"** = `department/{ash,civil,cse,ece,ecevlsi,eee,fp,manufacturing,mechanical,mechandsmartmanufacturing}/`.

## Sidebar and layout alignment

| ID | Page / JSP File | UI Issue Description | Severity |
|---|---|---|---|
| UI-001 | `/about/history` – `about/history.jsp` | Sidebar is capped at 280px but sits in a ¼-width column; content is only ½ width plus an extra principals card; sidebar heading starts ~39px below the page heading. Sidebar and content don't line up, and the layout differs from the other About pages (¼ + ¾). | High |
| UI-002 | `/academic/antiragging` – `academic/antiragging.jsp` | Sidebar and short content are centred as one narrow block inside a non-centred container; the sidebar sits mid-page with a large blank area on the left. | High |
| UI-003 | **Same problem as Anti Ragging** (content column has no width): `academic/academiccouncil.jsp`, `academic/mentors.jsp`, `academic/rules.jsp`; `department/{civil,manufacturing,mechanical,mechandsmartmanufacturing}/course.jsp`; `department/fp/{programs,students,weekly,staff}.jsp`; `department/{ash,cse,ece,ecevlsi,eee}/staff.jsp`; `department/{ece,ecevlsi,eee}/question.jsp`; `department/eee/programs.jsp` | Sidebar drifts towards the centre; content width follows text length, so blank space left/right varies from page to page. | High |
| UI-004 | **Same family** (sidebar + fixed 600px content centred in a non-centred container): `academic/{attendance,holidays,regulations,syllabus,notice,admission,labs}.jsp`; `approval/{aicteapproval,akuapproval,mandatorydisclosure,grievance}.jsp`; `facilities/{bank,centrallibrary,club,csecenter,guesthouse,gymnasium,healthcenter,hostels,medicalfacilities,placementbrochure,sportsfacilities,startupcell,usefullink,wifi}.jsp`; `rti/{rtiact,rtiapplication,rtistatus,publicinformationcell}.jsp`; `trainingplacement/{placedstudent,rulesoftrainingplacement,tipsforresume,placementbrochure}.jsp`; `about/administration.jsp`; all other dept sub-pages (every file except `about.jsp`, `sidebar.jsp`, `cse/notice.jsp`) | On desktop the sidebar + narrow content form an off-centre block with uneven blank margins; content is only 600px wide on large screens. | High |
| UI-005 | **Same as History** (Tailwind sidebar capped at 280px): `about/{vision,affiliation,allotment,principal,staff}.jsp`; `academic/{paymenttutorial,universityresult,calender}.jsp`; `trainingplacement/{studentplacementcoordinator,aboutplacement}.jsp`; `page/custom.jsp` (`/page/*`); `facultydetails.jsp` | From 1280px up the sidebar is narrower than its column, leaving a gap beside it; the row doesn't fill the container. | Medium |
| UI-006 | ~~Every page with a sidebar~~ | **Withdrawn after rendering:** the 39px margin holds the blue slanted cap above the sidebar title; the cap's top lines up with the content (History: 334px vs 340px). | – |
| UI-007 | Pages with a non-centred page container: all of UI-002 to UI-004, plus `termandcondition.jsp`, `category/tenders.jsp`, `facilities/{downloads,holiday,contactus}.jsp`, `nba/nba.jsp`, `invalidDeptId.jsp`, `sms.jsp`, `hodincharge.jsp` | On wide screens the page body is pushed left with empty space on the right. | Medium |
| UI-008 | `department/*/about.jsp` (all 10 depts) | Three columns add up to more than 100% (¼ + ⅔ + ⅓); columns get compressed and widths come out unequal. | Medium |
| UI-009 | `department/cse/notice.jsp` | Section sidebar is hidden below 1024px, so tablet/mobile users get no section menu (other dept notice pages show it). | Medium |
| UI-010 | `about/administration.jsp`, `academic/administration.jsp`, `facilities/contactus.jsp`, `termandcondition.jsp`, `hodincharge.jsp`, `facilities/{downloads,holiday}.jsp`, `nba/nba.jsp` | Bootstrap grid rows/columns are forced into a centred flex row: profile cards/columns squash side by side; tables in `.col-md-12` shrink to content instead of full width. | Medium |

## Responsive (mobile and tablet)

| ID | Page / JSP File | UI Issue Description | Severity |
|---|---|---|---|
| UI-011 | Phones ≤600px: all UI-001 and UI-005 pages, plus `category/notices.jsp` | Full-width sidebars get an extra 20px margin on each side; page becomes wider than the screen and scrolls horizontally. | High |
| UI-012 | Phones ≤600px: all UI-002 to UI-004 pages, plus `category/department.jsp` | Legacy sidebars shrink to 80% width with uneven margins, not centred. | Medium |
| UI-013 | Tablets ~600–900px: all UI-004 pages; worst on `ash`, `cse`, `ece`, `ecevlsi`, `eee`, `fp` dept sub-pages (side by side from 600px) and `department/{mechanical,manufacturing,mechandsmartmanufacturing}/{programs,weekly}.jsp` (inline `min-width:600px`) | Fixed 600px content sits beside the sidebar; sidebar is squeezed to a narrow column or the page overflows horizontally. | High |
| UI-014 | `header.jsp` (all public pages) | Between 600px and 767px the desktop menu shows but isn't laid out in a row; items wrap into a tall blue bar. | Medium |
| UI-015 | All pages with a `.jumbotron` title (academic, approval, facilities, rti, trainingplacement pages, dept sub-pages, `about/administration`, `tenders`, `downloads`, `nba`, `invalidDeptId`) | Fixed 200px left padding moves the title ~100px right of centre; on phones it squeezes or wraps the title. | Medium |

## Headers, titles and consistency

| ID | Page / JSP File | UI Issue Description | Severity |
|---|---|---|---|
| UI-016 | Site-wide | 6 different page-title bar styles: (1) grey jumbotron (academic, facilities, rti, approval, most dept pages); (2) grey breadcrumb bar (`about/*`, `academic/{admission,paymenttutorial,universityresult}`, `approval/aicteapproval`, `gallery/*`, `facultylist`, `trainingplacement/studentplacementcoordinator`, `department/*/about`); (3) light-grey legal bar (`contactus`, `privacypolicy`, `productandservice`, `refundandcancellation`, `termandcondition`, `feestructure`); (4) blue gradient (`academic/calender`); (5) `bg-gray-300` bar (`category/kecwebteam`); (6) no background (`facilities/csecenter`). | Medium |
| UI-017 | Empty grey band, no title: `about/staff.jsp`, `approval/aicteapproval.jsp` (second empty band under its title bar), `sms.jsp`, `department/civil/{attendance,course,labs,notice,question,vision}.jsp`, `department/{mechanical,manufacturing,mechandsmartmanufacturing}/labs.jsp`. No title at all: `department/civil/{programs,students,weekly}.jsp` | Blank title area or missing page heading. | Medium |
| UI-018 | `approval/akuapproval.jsp`, `approval/mandatorydisclosure.jsp` ("AICTE Approval"); `facilities/healthcenter.jsp` ("Medical Facilities"); `facilities/{hostels,club}.jsp` ("Facilities"); `facilities/holiday.jsp` ("Administrative Responsibility"); `gallery/mediagallery.jsp` ("Photo Gallery"); `department/{ash,cse,fp}/awards.jsp` ("Course"); `department/{ece,ecevlsi}/{staff,notice}.jsp`, `department/eee/notice.jsp` ("Staff of EEE"); `department/fp/{staff,students}.jsp` ("…of CSE"); `department/{manufacturing,mechandsmartmanufacturing}/{faculty,notice,students}.jsp` ("Mechanical…") | Page heading doesn't match the page. | Medium |
| UI-019 | `about/history.jsp`, `gallery/photogallery.jsp`, `gallery/mediagallery.jsp` | Same title appears twice (title bar + heading in content/card). | Low |
| UI-020 | `academic/notice.jsp` ("Governement"); header menu and sidebars ("Academic Calender", "Placement Brouchure", "AICTE Aproval", "photo Gallery"); `department/cse/sidebar.jsp` ("awards & Recognition") | Visible typos and inconsistent capitalisation. | Low |

## Homepage (`index.jsp`, `/`)

| ID | Page / JSP File | UI Issue Description | Severity |
|---|---|---|---|
| UI-021 | ~~`index.jsp`~~ | **Withdrawn after rendering:** the column next to "About KEC" shows the college building as a CSS background (`.gtmaincontenthome` in `style.css`); it is not empty. | – |
| UI-022 | `index.jsp` | Desktop and mobile sliders are both always rendered; the unused one shows as an empty slider with arrows/pagination. Caption bar shows even when the caption is empty. | Medium |
| UI-023 | `index.jsp` | "View All Notices/Tenders" buttons are pinned to the box bottom with no reserved space and can overlap the scrolling list. Important Links box uses `h-[50vh]` (unsupported in Tailwind 2.2), so the three boxes have unequal heights. | Medium |
| UI-024 | `index.jsp` | Video aspect-ratio wrapper class (`pb-9/16`) doesn't exist; video has a fixed 200px height and doesn't scale. | Low |

## Images, tables, embeds and components

| ID | Page / JSP File | UI Issue Description | Severity |
|---|---|---|---|
| UI-025 | `academic/{holidays,regulations}.jsp`, `approval/{akuapproval,mandatorydisclosure}.jsp`, `rti/{rtiact,rtiapplication}.jsp`, `facilities/{holiday,responsibilityDoc,placementbrochure}.jsp`, `trainingplacement/rulesoftrainingplacement.jsp` | Embedded PDFs fixed at 700×1000px overflow the 600px column and phone screens. | High |
| UI-026 | `facilities/csecenter.jsp` (image 800px), `approval/aicteapproval.jsp` (image 639×150), `facilities/centrallibrary.jsp` (table 834px), `academic/administration.jsp` (27 tables at 652px), `facilities/{bank,startupcell}.jsp`, `trainingplacement/tipsforresume.jsp` (tables 500px), `department/cse/labs.jsp` (iframe 600×500) | Fixed-width elements overflow the content column on desktop and mobile. | Medium |
| UI-027 | `academic/{labs,holidays,admission,administration}.jsp`, `facilities/{hostels,startupcell,sportsfacilities,club,bank,centrallibrary,downloads,holiday,responsibilityDoc}.jsp`, `trainingplacement/{placedstudent,tipsforresume}.jsp`, `nba/nba.jsp`, `approval/aicteapproval.jsp`, `department/civil/awards.jsp`, `department/{ece,ecevlsi,eee}/principal.jsp` | Tables have no horizontal-scroll wrapper and overflow the screen on mobile. | Medium |
| UI-028 | All 105 pages with a `.page-content` block (most academic, facilities, rti, approval, trainingplacement pages, dept sub-pages, `about/staff`, `page/custom`, `nba`, `invalidDeptId`) | Horizontal scrollbar always shown, even when content fits. | Low |
| UI-029 | `index.jsp`, `department/*/about.jsp` (scrolling notice bar), dept notice pages, `about/administration.jsp`, `category/tenders.jsp` | Notice/News/date badges render as unstyled plain text (Bootstrap `label label-*` classes, Bootstrap not loaded). | Medium |
| UI-030 | `facilities/{downloads,holiday,contactus}.jsp`, `academic/holidays.jsp`, `approval/{akuapproval,grievance}.jsp`, `trainingplacement/tipsforresume.jsp`, `category/tenders.jsp`, `about/administration.jsp`, `academic/administration.jsp`, `sms.jsp`, `hodincharge.jsp`, dept notice/faculty/principal pages | Bootstrap buttons, panels and form controls render unstyled (plain links, no panel borders, raw inputs). | Medium |
| UI-031 | `about/staff.jsp` | Staff photos forced to 125×125px without cropping; non-square photos look stretched. | Low |
| UI-032 | `admin/HodDashboard.jsp`, `admin/HodAddFaculty.jsp`, `user/HodAddFaculty.jsp` | AdminLTE/Bootstrap 4 loaded on top of the site header's Tailwind and global sidebar/row rules, so styles conflict; `jsp/admin/plugins/` is empty, so plugin widgets are unstyled. | Medium |

## Global and markup

| ID | Page / JSP File | UI Issue Description | Severity |
|---|---|---|---|
| UI-033 | All public pages (`footer.jsp`) | `style.css` is loaded at the end of the page; pages first appear unstyled, then the sidebar and footer jump into place. | Low |
| UI-034 | All public pages (`header.jsp`) | Every list item gets an underline and pointer cursor on hover, including non-link items. | Low |
| UI-035 | Unbalanced `<div>` open/close counts: `about/administration`, `academic/{academiccouncil,administration,admission,paymenttutorial}`, `contactus`, `feestructure`, `termandcondition`, `index`, `facilities/{bank,contactus,guesthouse,gymnasium,medicalfacilities,usefullink,wifi}`, `rti/rtiapplication`, `trainingplacement/{placedstudent,rulesoftrainingplacement,tipsforresume}`, `sms`, `updatePassword`, `department/ash/{labs,notice,question,students}`, `department/civil/awards`, `department/cse/{attendance,awards,course,labs,programs,question,students,vision,weekly}`, `department/{ece,ecevlsi,eee}/principal`, `department/fp/{attendance,awards,course,labs,notice,programs,question,students,vision,weekly}`, `department/{manufacturing,mechanical,mechandsmartmanufacturing}/course` | Wrappers close too early or too late and can push later sections (content blocks, footer) out of their container. Visual effect needs a browser check. | Low |

## Notes

- UI-007, UI-011 to UI-015 and UI-028 to UI-030 come from shared CSS rules in `header.jsp`, `footer.jsp`
  and `css/style.css`, so they appear on many pages at once.
- A rendered pass at desktop, tablet and phone widths should confirm the layout findings before fixes start.

# Fix plan

Shared CSS first, page-by-page edits later. About a dozen rules in `header.jsp`, `footer.jsp` and `css/style.css`
cause most findings, so fixing them once fixes 100+ pages.

- Store pages don't include `header.jsp`/`footer.jsp`, so shared CSS changes don't touch store management.
- PDFs, images and tables usually sit inside `<kec:section>` blocks; the live content comes from `page_section` rows.
  Width problems there are fixed with CSS rules, not by editing the JSP fallback text.
- `PagesBaselineTest` only checks status code and page kind; `HtmlSnapshotTest` shows the intended diffs and is
  re-recorded after each approved phase.

## Decisions (approved 2026-10-29)

1. The UI-fix phase is started.
2. One title-bar style everywhere: the grey breadcrumb bar of the About pages.
3. Wrong headings and typos may be corrected (Phase 5). Menu labels stored in the DB are fixed in Website Content.
4. History page: the principals/motto card moves below the text.
5. Playwright/Chromium may be installed for screenshots (outside the repository).

## Phase 0 – Before/after safety net (no code changes)
- Record HTML snapshots on the current code (`HtmlSnapshotTest`, untracked `html-snapshots/`).
- Screenshots at 1440, 768 and 375px of ~40 pages: one per layout family, homepage, History, Anti Ragging,
  one dept About page and one dept sub-page per pattern.

## Phase 1 – Shared CSS foundation (`css/site-fixes.css` + `header.jsp`)
Faulty rules are fixed where they are defined (inline `<style>` in `header.jsp`); new rules go into
`css/site-fixes.css`. Both stylesheets are linked at the end of `header.jsp`; `footer.jsp` still loads `style.css`
again so its precedence over the 29 page-level `<style>` blocks stays as before.

| Fix | IDs |
|---|---|
| Restrict the global `aside` rules (80% width / 20px margin / 280px cap) to the legacy sidebar | 011, 012 |
| Jumbotron title centred with normal padding instead of `padding-left:200px` | 015 |
| `.col-md-8` fluid instead of fixed 600px | 013 (partly), 004 |
| `img`, `object`, `embed`, `iframe` `max-width:100%`; PDF embeds sized to the column | 025, 026 |
| Tables inside `.page-content` scroll sideways on small screens | 027 |
| `.page-content` `overflow-x:auto` instead of `scroll` | 028 |
| Small compatibility styles for the Bootstrap classes in use (`label-*`, `btn`, `panel`, `form-control`) | 029, 030 |
| Underline-on-hover only for links, not every `li` | 034 |
| Align the menu breakpoint (600px vs 768px) | 014 |
| No unstyled flash on load | 033 |

## Phase 2 – Sidebar layout, Anti Ragging family (CSS only)
Centre `.container`; sidebar column fixed at ¼, content takes the rest (sibling selectors on `aside.col-sm-3`);
`.row` wraps. Fixes 002, 003, 004, 007, 010, 013 on ~150 legacy pages.

## Phase 3 – Per-page layout fixes (small JSP edits)
001 History ¼ + ¾ with the principals card below the text; 005 Tailwind sidebars fill their column;
008 dept About pages ¼ / ½ / ¼; 009 `cse/notice` section menu on mobile; 031 staff photos cropped, not stretched.

## Phase 4 – Homepage (`index.jsp`)
~~021~~ (withdrawn); 022 one slider per screen size, no empty captions; 023 space for "View All" buttons,
equal box heights; 024 responsive 16:9 video.

## Phase 5 – Titles and consistency
016/017 one title-bar style, no empty bands; 018/019/020 correct headings and typos, no duplicate titles.

## Phase 6 – Markup hygiene and admin dashboards
035 balance `<div>` tags (checked per page snapshot diff); 032 HOD dashboards with AdminLTE (needs a decision).

## Delivery per phase
Small commits on `kec_claude`, `mvn clean package` green, change-log row in `docs/sql/dynamic/README.md`,
list of pages to check per width, push only after "verified".

## Progress

| Phase | Status |
|---|---|
| 0 | done 2026-10-29: Playwright screenshots (45 pages × 1440/768/375 px) before and after, outside the repository |
| 1 | done 2026-10-29 (20f8b75), waiting for your check (see "Phase 1 result") |
| 2 | done 2026-10-29, waiting for your check (see "Phase 2 result") |

## Phase 1 result (2026-10-29)

Changed: `jsp/header.jsp` (inline styles + 2 stylesheet links), new `css/site-fixes.css`.

| ID | Change | Measured |
|---|---|---|
| 011 | phones: sidebar `width:100%`, `padding:20px 16px`, `margin:30px 0 0` (was 80% / 20px side margin) | About pages, `aboutplacement`, `notices`, `studentplacementcoordinator`: page width 379–395px → 375px |
| 012 | same rule: legacy sidebars full width on phones | sidebar 300px off-centre → 375px |
| 013 | `.col-md-8` `width:600px; max-width:100%; min-width:0` (was min = max = 600px); inline `min-width:600px` neutralised | `department/mechanical/programs` 375px: 600px → 375px; tablet 768px: sidebar no longer squeezed to 168px |
| 014 | menu wraps centred in rows from 600px, single spread row from 1280px; header grows with the menu (min 80px); no menu gap at exactly 600px | 600–767px: 3 tidy rows inside the blue bar (third row was painted over before) |
| 015 | jumbotron `padding: 0 16px; text-align:center` (was `padding-left:200px`) | title centre = page centre at all widths |
| 025, 026 | `object, embed, iframe, video { max-width:100% }` (images were already capped by Tailwind) | `facilities/contactus` 768px: 850px → 768px; `feestructure` 768px: 817px → 768px |
| 027 | tables inside `.page-content` already scroll inside the block (see 028) | – |
| 028 | `.page-content` `overflow-x:auto` (was `scroll`) | no permanent scrollbar |
| 029, 030 | Bootstrap 3 look-alikes for `label-*`, `btn`, `panel`, `form-control` (base rules in `:where()`, so Tailwind and the existing `.btn-default`/`.btn-danger` win) | homepage notice badges coloured again |
| 033 | `style.css` linked in the header | page no longer renders unstyled first |
| 034 | underline on hover only for links (`li > a:hover`), no pointer on plain list items | – |

Still open (found while measuring, moved to later phases): `approval/grievance` on phones (form 444px wide → page
410px), `trainingplacement/studentplacementcoordinator` at 768px (840px), content column flush to the screen edge
on phones on legacy pages (Phase 2). `department/ece/question` never finishes loading in the browser (before and
after the change; the server answers in 7 ms; to be checked separately).

## Phase 2 result (2026-10-29)

Changed: `css/site-fixes.css` (Phase 2 block) and 8 JSPs whose grey title band was never closed (UI-035 cases that
broke the layout): `approval/grievance`, `department/ash/labs`, `department/civil/{course,labs,notice,question}`,
`facilities/club`, `facilities/contactus`. In those pages the whole body (on `facilities/contactus` even the footer) sat
inside the title band; now the band is closed after the title and the surplus `</div>` at the end is removed.

| ID | Change | Measured |
|---|---|---|
| 002–004 | legacy sidebar (`aside.col-sm-3` or `aside.m-5.p-5` with `role="complementary"`) on the left at ¼ width (max 280px), content fills the rest, from 768px; below 768px both stack at full width | 159 legacy pages × 1440/768/375px: sidebar left edge at the container edge (96px at 1440px), content ≥ 700px wide at 1440px, no horizontal overflow |
| 007 | `.container` centred with a 16px gutter (`:where()`, Tailwind classes still win); the same width for legacy wrappers without a container | `termandcondition`, `tenders`, `downloads`, `nba` centred |
| 010 | Bootstrap 3 grid widths for `col-sm-*` (≥768px) and `col-md-*` (≥992px) inside `.row`; `.row` wraps; title-band columns keep their natural width | `facilities/contactus` location + form side by side; profile rows no longer squashed |
| 013 | tablets: sidebar and content stack below 768px | `department/*` sub-pages at 600–767px no longer squeezed |

Still open:
- `academic/syllabus`: the live text (`page_section` academic.syllabus / main, never edited) contains an unclosed
  `<code>` that the browser turns into a second column. Fix: remove `<code>` from the text in Website Content. The
  JSP fallback stays equal to the seed (checked by `PageTextsTest`).
- `trainingplacement/studentplacementcoordinator` at 768px (829px wide) – Phase 3.
- Not reachable, so not checked: `department/{ash,cse,ece,ecevlsi,eee,fp}/staff.jsp`, `department/ash/attendace.jsp`
  (no route maps to them).
