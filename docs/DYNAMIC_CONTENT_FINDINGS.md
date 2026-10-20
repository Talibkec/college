# Dynamic content – findings (phase "make pages dynamic")

_Audit of 2026-10-16, branch `kec_claude` at `f702683`. Updated 2026-10-19: roles and permissions (§1.3), standard
treatments (§1.2), and in every page table the columns **How I'd address it** and **Who can update**.
**No code, schema or data was changed for this document.**_

> **Status 2026-10-19:** all recommendations of §8 approved (D9 and D4 still need your input). Steps 1–6 of §9
> (migrations in `docs/sql/dynamic/`, roles and permissions, site settings, menus/links, sidebars, About texts) are implemented; see `UPGRADE_NOTES.md` §10.
_Store management is excluded (CLAUDE.md, `PROJECT_ANALYSIS.md` §5). Personal phone numbers and e-mail addresses
found in templates are deliberately **not** reproduced here; files are referenced instead._

Preconditions checked:
* Branch `kec_claude`, working tree clean.
* `mvn clean verify` (JDK 25): **BUILD SUCCESS – 58 tests run, 0 failures, 0 errors, 1 skipped** (the opt-in `HtmlSnapshotTest`).

How to read this document:
* §1 is the overall approach: §1.1 the kinds of content and their source, §1.2 the **standard treatments** (T1–T8,
  how every item is made dynamic without breaking the page), §1.3 the **roles** (today and proposed) and **who may
  update which content area**, §1.4 screens, security and rendering.
* §2 lists the **proposed new tables/columns (⏸ approval needed)**, including the permission tables. §3 lists the
  proposed configuration keys.
* §4 goes page by page, in the order of the main menu. Every hard-coded item has a **Source** (existing table,
  new table from §2, or config from §3), a **Screen** (which edit screen), **How I'd address it** (treatment from §1.2
  plus the page-specific steps) and **Who can update** (roles from §1.3).
* §5 covers cross-cutting items (header, footer, dropdowns, Java). §6 reviews the modules that are **already dynamic**
  (code standard, what needs to be addressed and who should maintain them). §7 lists bugs found on the way. §8 lists
  the decisions you need to make. §9 is the proposed commit plan.

Legend for **Source**: `T:<table>` existing table · `N:<table>` new table (§2) · `C:<key>` application config (§3) ·
`D:<doc_type>` the existing `download` table with a new `doc_type` value (no schema change) · `keep` stays in code (reason given).

Legend for **Who can update** (details in §1.3):
* **Admin and SubAdmin can update everything in §4 and §5 unless the cell says "Admin only"**, so the cell lists only
  the *additional* roles.
* `E` = edit: create, change, reorder, hide and delete any entry of that content area.
  `A` = add only: upload/append new entries and delete **their own** entries; cannot change other people's entries.
* `(own dept)` = limited to the department(s) the user belongs to (Faculty) or is HOD / HOD in-charge of (HOD).

---

## 1. Approach

### 1.1 Kinds of content and their source

The pages contain eight kinds of hard-coded content. Each kind gets **one** generic source, so we don't end up with a
table per page:

| Kind of content | Examples | Proposed source | Schema change? | Treatment (§1.2) |
|---|---|---|---|---|
| Scalar facts | phone, address, PIN, principal name, motto, "Established 2016", copyright year, social links, map/YouTube embed | `N:site_setting` (key/value) | new table | T1 |
| Rich text blocks | history, vision/mission, principal message, admission rules, payment steps, department intros, lab equipment lists, Wi-Fi text, legal texts | `N:page_section` (HTML block per page + section, seeded with today's exact inner HTML, so the layout stays byte-identical) | new table | T2 |
| Link lists and menus | main menu, top bar, footer links, every page sidebar, "Important Links", library links, syllabus links, home marquee | `N:link_item` (nested list with `list_code`, `parent_id`, `sort_order`) | new table | T3 |
| Documents (PDFs) | allotment report, regulations, AKU approval, mandatory disclosure, brochure, RTI act/form, RTI responses, payment tutorial, resume tips | the existing **`download`** table with new `doc_type` values (it already holds `download` and `nba`; `doc_type` is `varchar(30)`) + `N:document_type` (label, area, display mode per type) | none on `download` | T4 |
| People who are not faculty records (or are, but listed in a role) | club in-charges, hostel superintendents, library contacts, web team, list of principals | `N:contact_person` (optionally pointing to `faculty.faculty_id`) | new table | T5 |
| Images | photo gallery, computer-centre photos, library photo, home "Gallery" strip, web-team photos, AICTE banner | `N:site_image` (album + BLOB **or** the path of today's static file, so existing files keep working without copying them into the DB) | new table | T6 |
| Tables of numbers | day-scholar fee structure, hostel charges | `N:fee_item` | new table | T7 |
| Department metadata | the 10 department sites, their names, menu groups (UG/PG), notice-type codes, HOD user, faculty/lab department ids | `N:department_site` (one row per mini-site, pointing to `department.dept_id`) | new table | Admin-only screen |

New uploads always **append** (each upload is a new row with date and uploader; nothing is overwritten), which is what
you asked for on the Allotment page. Lists show newest first unless `sort_order` is set.

### 1.2 Standard treatments (how each kind is made dynamic)

The rule for every treatment: **the page must look exactly the same before and after**, and it must keep working if
a table is empty or not yet created on a server. That is achieved with a small JSP tag that renders the DB value and
falls back to today's markup, which stays in the tag body during the transition:

```jsp
<kec:section key="about.history/main">
   ... today's exact HTML (rendered only if no row exists) ...
</kec:section>
```

Once production is seeded and verified, the fallback bodies are removed in a separate clean-up commit.

| Code | Treatment | What is built | Edit screen behaviour | Safety |
|---|---|---|---|---|
| **T1** | Setting | `@ControllerAdvice` puts `site` (map of `site_setting`) into every model; JSPs print `${site['contact.phone']}` via `<kec:setting key=".." >fallback</kec:setting>` | `/manage/settings`: list of keys grouped by area, one input per key, description shown; keys are created by migrations only (editors change values, not keys) | values are escaped (`<c:out>`); URL-type keys validated (`http/https` only); cache evicted on save |
| **T2** | Rich-text section | `page_section` row per page/section, seeded with today's inner HTML; `<kec:section>` tag | `/manage/sections?page=<page_key>`: WYSIWYG editor (MIT/BSD-licensed, e.g. Quill 2, copied to `/js/vendor`, no CDN), **Preview**, **History/Restore** (from the change log) and a "visible" switch | server-side sanitising (OWASP Java HTML Sanitizer, policy allowing today's tags/classes/iframes from youtube/google maps only) because the HTML is rendered unescaped |
| **T3** | Link list / menu | `link_item` tree; one include `common/linklist.jsp` (or `<kec:links list="..">`) renders the same `<ul>/<li>` markup and CSS classes as today | `/manage/links?list=<list_code>`: add, edit label/URL, up/down reorder, nest (menus), hide, "open in new tab" | URL validation: relative path or `http/https`; `javascript:` rejected; the active-page highlighting stays as today |
| **T4** | Document list | `download` rows with a new `doc_type`, metadata in `document_type`; one generic include `common/documentlist.jsp` with display modes `list`, `embed_latest`, `list_embed_first`; files streamed by the existing `/facilities/document/{id}` | `/manage/documents?type=<doc_type>`: upload (PDF/JPG/PNG whitelist, size limit, title, date), **append-only**, newest first, delete via POST with confirmation | correct `Content-Type` from a whitelist (not from the extension, §6.4), quoted `Content-Disposition`; uploader and date stored; empty list ⇒ today's placeholder ("Coming Soon"/"Updated Soon…") from the tag body |
| **T5** | People / contact list | `contact_person` rows per `list_code`; include `common/contactlist.jsp` reproducing today's table/card markup; if `faculty_id` is set, name and photo come from the faculty record | `/manage/people?list=<list_code>`: add/edit/reorder/hide; pick a faculty member (autocomplete, existing `/searchUserName`-style lookup) or type a name; "show phone/e-mail publicly" switch | phone stored as text (keeps `0`/`+91`); seed keeps today's visibility, editors can hide personal numbers |
| **T6** | Images / albums | `site_image` rows per `album_code`; served by a new `/site-image/{id}` (cacheable, `ETag`) or by `static_path` for seeded files; include renders today's gallery/grid markup | `/manage/images?album=<album_code>`: multi-upload, caption, reorder, hide, delete | image type checked by content (magic bytes), size limit; no base64 inlining |
| **T7** | Fee table | `fee_item` rows; totals computed in Java (verified equal to today's printed totals) | `/manage/fees?schedule=<code>`: grid editor (rows = fee heads, columns = category × year) | numbers validated; amount text for hostel rows |
| **T8** | Existing dynamic module | keep table, entity and URL; fix the issues listed in §6 (edit screen, ordering, POST deletes, streaming photos) and widen the authorisation **only on that module's paths** to the roles listed | existing screen, made role-aware (sidebar shows only what the user may use) | existing tests + new role tests; Admin behaviour unchanged |
| **K** | Keep in code | – | – | theme assets, security routing, application UI texts |

### 1.3 Roles and who may update what

#### 1.3.1 Roles today (local copy of production, `test_role` / `user_role`)

| Authority (`test_role.name`) | Users | Landing page after login | Can do today |
|---|---|---|---|
| `Admin` | 3 | `/auth/uploadDashboard` | everything under `/auth/**` (all uploads, users, HOD assignment, SMS) |
| `HOD` | 19 | `/hod/hodDashboard` | `/hod/**`: department notices, add faculty; lab documents (`/auth/uploadLabDoc`); delete links in department pages (JSPs check fixed user names `hodcse`, `hodcivil`, …) |
| `Faculty` | 50 | `/facultyDetails?facultyId=…` | `/user/**`: own profile, notices for their department. The `/user/uploadDownload`, `/user/uploadHoliday`, `/user/uploadAICTEapproval`, `/user/uploadResponsibilityDoc` form pages exist, but their scripts post to `/auth/api/*` (Admin-only), so those uploads **fail for Faculty** (§7 B21) |
| `SM`, `SK` | 1 + 1 | store dashboards | **store management – out of scope, untouched** |

Facts that shape the design:
* A role is just a row in `test_role`; Spring uses `test_role.name` directly as the authority (no `ROLE_` prefix).
  **Adding a role is a data insert, no DDL.**
* `user_role` is many-to-many and already used that way: 16 users hold two or three roles (13 × `Faculty,HOD`,
  1 × `Admin,Faculty`, 1 × `Admin,Faculty,HOD`). So a faculty member who is also TPO simply gets `Faculty` + `TPO`.
* `registration.jsp` lists **all** roles from the DB, so new roles become selectable there automatically.
* The only role-assignment screen is `/auth/hodincharge` (HOD only). There is no screen to give an existing user a
  second role, or to remove one.
* The header's "Dashboard" link points to `/auth/uploadDashboard` for every logged-in user, so HOD/Faculty get an
  access-denied page when they click it (§7 B22).

#### 1.3.2 Proposed roles

Naming follows the existing style (`Admin`, `Faculty`, `HOD`: no prefix, no spaces). Your spelling → proposed name:
Sub_Admin → `SubAdmin`, Registerar → `Registrar`, Exam_Controller → `ExamController`.

| Authority | Who holds it | Manages (content areas, §1.3.3) | Scope | Landing page |
|---|---|---|---|---|
| `Admin` (existing) | web team / system administrator | everything, plus **system**: users, roles, department metadata, notice types, SMS groups, menus | all | unchanged `/auth/uploadDashboard` |
| `SubAdmin` (new) | office/web-team member who maintains the website content | every content area (E), **not** system (users/roles, department metadata, notice types, SMS) | all | `/manage` |
| `Principal` (new, optional – §8 D16) | the principal's account, if it should not be a full Admin | about (principal's message, history), academic, approval, office (E); can read the change log | all | `/manage` |
| `Registrar` (new) | registrar / establishment section | office (administration list, responsibility docs, support staff, allotment report, holidays), academic (admission, regulations, rules, MoM, govt notices), approval, facilities texts, downloads, college notices/news/tenders | all | `/manage` |
| `ExamController` (new) | controller of examinations / exam cell | exam (academic calendar, syllabus, attendance page, university results), exam notices, downloads | all | `/manage` |
| `TPO` (new) | training & placement officer | T&P pages, brochure, rules, resume tips, placed students, student coordinators, placement portal link, startup cell, placement notices | all | `/manage` |
| `NBA` (new) | NBA / IQAC coordinator | NBA documents (`download.doc_type = 'nba'`) | all | `/manage` |
| `Accounts` (new) | accounts section | fees (fee structure, hostel charges, payment tutorial, bank page, refund page) | all | `/manage` |
| `Librarian` (new) | librarian | library page (photo, e-resource links, contacts) | all | `/manage` |
| `Warden` (new) | chief warden / hostel superintendent | hostel page (wardens, hostel text, together with Accounts the hostel charges) | all | `/manage` |
| `PIO` (new) | public information officer (RTI) | RTI pages (act, form, RTI status/responses, Public Information Cell) | all | `/manage` |
| `ClubInCharge` (new) | cultural / sports in-charges | club, sports facilities, gymnasium pages (E); gallery (A) | all | `/manage` |
| `Staff` (new) | non-teaching office staff | own support-staff profile (photo, phone); college notices (A); gallery (A) | own record | `/manage` |
| `HOD` (existing) | heads of department (and HOD in-charge) | own department mini-site: about, vision, labs, students, awards, programme activities, documents (question bank, course file, weekly test), department notices, department faculty/staff order; NBA (A); gallery (A) | own dept | unchanged `/hod/hodDashboard` |
| `Faculty` (existing) | teaching staff | own profile (as today), own department notices (as today), own department documents (A: question bank, course file, weekly test) | own dept | unchanged |
| `SM`, `SK` (existing) | store | **out of scope, untouched** | – | unchanged |

More roles can be added later without code (a row in `test_role` + rows in `content_area_role`), e.g. a
`ComputerCentreInCharge` for the Computer Centre page, a `StartupCell` coordinator, or an `IQAC` role.

#### 1.3.3 Content areas and the permission matrix

Every editable item belongs to exactly one **content area**. The matrix below is the seed for the new table
`content_area_role` (§2, 10); Admin can change it later in `/auth/roles/areas` without a deployment.

| Area code | Covers (pages / items) | Edit (E) | Add only (A) |
|---|---|---|---|
| `system` | users, role assignment, `department_site`, `notice_type`, SMS groups and sending, web-team list | **Admin only** | – |
| `site` | site settings: name, contact, address, PIN, social links, footer texts, map/video embed | Admin, SubAdmin | – |
| `menu` | main menu (desktop + mobile), top bar, footer links, all page sidebars | Admin, SubAdmin | – |
| `home` | marquee, Important Links, About box, gallery strip; slider and achievements (existing) | Admin, SubAdmin | – |
| `about` | history, principals list, principal's message, vision & mission, affiliation | SubAdmin, Principal | – |
| `office` | administration list (`administration`), responsibility docs, support staff (`staff`), allotment & surrender report, holidays | SubAdmin, Registrar, Principal | Staff (own staff profile only) |
| `academic` | admission text, seat table (`no_of_seat`), regulations, disciplinary rules, anti-ragging, MoM of academic council, notices from govt., mentors | SubAdmin, Registrar, Principal | – |
| `exam` | academic calendar, syllabus, attendance page, university results | SubAdmin, ExamController | – |
| `fees` | fee structure, payment tutorial (steps + PDF), bank page, refund & cancellation | SubAdmin, Accounts | – |
| `approval` | AICTE documents + banner, AKU approval, mandatory disclosure | SubAdmin, Registrar, Principal | – |
| `facilities` | computer centre, guest house, health centre, medical, gymnasium text, Wi-Fi, useful links | SubAdmin, Registrar | – |
| `library` | central library page | SubAdmin, Librarian | – |
| `hostel` | hostel wardens, hostel text, hostel charges | SubAdmin, Warden, Accounts | – |
| `activities` | cultural club, sports facilities, gymnasium in-charges | SubAdmin, ClubInCharge | – |
| `tp` | all Training & Placement pages, placed students, coordinators, startup cell | SubAdmin, TPO | – |
| `rti` | RTI act, application form, RTI status/responses, Public Information Cell | SubAdmin, PIO | – |
| `gallery` | photo gallery, media gallery | SubAdmin | ClubInCharge, HOD, TPO, Staff |
| `nba` | NBA documents | SubAdmin, NBA | HOD |
| `downloads` | Downloads page | SubAdmin, Registrar, ExamController | – |
| `notices` | college Notice / News / Tender (+ proposed `ExamNotice`, `PlacementNotice` types) | Admin, SubAdmin, Registrar | ExamController (exam notices), TPO (placement notices), Staff |
| `legal` | privacy policy, terms, product & services | Admin, SubAdmin | – |
| `department.<site>` (10 areas, e.g. `department.cse`) | the mini-site's about, vision, labs, students, awards, programmes, attendance text, staff/faculty order, department documents | SubAdmin, HOD (own dept) | Faculty (own dept): department documents only |
| department notices (existing) | `CSENotice`, `CIVILNotice`, … | as today: HOD and Faculty of that department, Admin | – |
| faculty profiles (existing) | faculty records, key props, photos | as today: Faculty (own), HOD (add faculty), Admin | – |

Rules:
1. **Nobody loses access.** Admin keeps everything; HOD and Faculty keep exactly what they can do today. New roles
   only add access.
2. A user may hold several roles; permissions are the union.
3. Department scope: **HOD** → every `department_site` whose `hod_username` is the user (an HOD in-charge of
   mechanical, manufacturing and MSM gets all three). **Faculty** → the `department_site` rows whose `dept_id` equals
   the user's `faculty.department_id` (linked via `faculty.username`). The legacy department accounts (`hodcse`, …)
   are seeded as `hod_username`, so the 40 hard-coded `UserName eq 'hodcse'` checks become one permission check with
   the same result.
4. `A` entries record `created_by`; a user with only `A` may delete only rows where `created_by` = their user name.
5. Every change writes a `content_change_log` row (who, when, what, old value), so with many editors you can see who
   changed a page and restore the previous text with one click (T2 "History").

#### 1.3.4 How roles are implemented

| Piece | Implementation |
|---|---|
| New roles | data migration `docs/sql/dynamic/10_roles_seed.sql`: `INSERT INTO test_role(name)` for each new role (no DDL; ids assigned by `AUTO_INCREMENT` as today) |
| Assigning roles | new Admin-only screen `/auth/roles`: search a user, tick roles, for HOD pick the department site(s) (writes `department_site.hod_username`); removing a role works too. `/auth/hodincharge` and `/auth/hod/saveRole` stay unchanged and keep working |
| Permission check | `ContentPermissionService.can(user, area, access, siteCode)` reading `content_area_role` (cached, evicted on change); used by every `/manage/**` controller **and** by the JSPs (`<kec:canEdit area="tp">` shows Edit/Delete links only to permitted users) |
| URL security (`WebSecurityConfig`) | new rule `/manage/**` → authenticated and holding any content role; the fine-grained check is in the service (method security `@EnableMethodSecurity` + `@PreAuthorize("@perm.can(...)")`). Existing rules for `/auth/**`, `/hod/**`, `/user/**` stay; for existing modules (T8) a path-specific rule is added **before** `/auth/**`, e.g. `/auth/uploadfile/addPlacements`, `/auth/tpoCoordinator/**` → `Admin, SubAdmin, TPO` |
| Landing page | `KECUrlAuthenticationSuccessHandler`: users holding `Admin`, `HOD`, `Faculty` (or store roles) land exactly where they land today; users holding **only** new roles land on `/manage` |
| Dashboard | `/manage` shows one tile per content area the user may edit; the Admin, HOD and Faculty dashboards get a "Manage website content" link when the user also holds a new role or may edit an area; the header "Dashboard" link goes to `/mydashboard`, which redirects to the user's own landing page (fixes B22) |
| Tests | a role matrix test: for each role, allowed screens return 200 and everything else 403; department scope (HOD of CSE cannot edit `department.civil`); `A` users cannot delete others' rows; Admin/HOD/Faculty behaviour of today unchanged; `PagesBaselineTest` unchanged |

### 1.4 Screens, security and rendering

Edit screens live under one new area **`/manage/**`** (new URLs; existing URLs untouched). `/auth/**` stays Admin-only,
because it is one rule in `WebSecurityConfig` and the new editors are not Admins:
`/manage/settings`, `/manage/sections`, `/manage/links`, `/manage/people`, `/manage/images`, `/manage/fees`,
`/manage/documents?type=<doc_type>`. Admin-only system screens go under `/auth/`: `/auth/roles`,
`/auth/roles/areas`, `/auth/content/departments`, `/auth/content/noticetypes`, `/auth/content/smsgroups`.
All changes are POST-only; deletes ask for confirmation.

Rendering: a `@ControllerAdvice` adds `site` (settings) and `menus` to every model, so `header.jsp`/`footer.jsp`
read them; settings, menus and permissions are cached (`@EnableCaching` is already on) and evicted on save.

---

## 2. ⏸ Proposed new tables / columns (need your approval – CLAUDE.md "Database")

Facts that shape the DDL:
* Existing tables are a mix of **MyISAM** (`department`, `download`, `faculty`, …) and InnoDB, all `latin1_swedish_ci`.
  MyISAM has no foreign keys, so the new tables **reference ids without FK constraints**.
* New tables: InnoDB, `utf8mb4` (needed for `₹`, Devanagari, curly quotes). Joins with latin1 columns still work.
* Naming follows the existing lower_snake_case physical naming; IDs are `AUTO_INCREMENT` (`GenerationType.IDENTITY`).
* No existing table, column or ID generation is changed, except the **optional** `ALTER`s marked "option" below.
* Every new content table has `area_code` (permission, §1.3.3) and `created_by`/`updated_by`/`updated_at`.
* Migration files would go to `docs/sql/dynamic/NN_<name>.sql` (DDL) and `NN_<name>_seed.sql` (seed = today's
  hard-coded values, so the pages look the same). Nothing is applied automatically (`ddl-auto=none` stays).

```sql
-- 01 key/value facts shown on many pages
CREATE TABLE site_setting (
  setting_key   VARCHAR(100)  NOT NULL PRIMARY KEY,   -- e.g. 'contact.phone', 'principal.name'
  setting_value TEXT          NULL,
  value_type    VARCHAR(20)   NOT NULL DEFAULT 'text',-- 'text' | 'url' | 'email' | 'phone' | 'number' (validation)
  area_code     VARCHAR(40)   NOT NULL DEFAULT 'site',
  description   VARCHAR(255)  NULL,                   -- shown in the edit screen
  updated_by    VARCHAR(255)  NULL,
  updated_at    DATETIME      NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 02 rich-text blocks
CREATE TABLE page_section (
  section_id    BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  page_key      VARCHAR(100)  NOT NULL,               -- e.g. 'about.history', 'department.cse.about'
  section_key   VARCHAR(100)  NOT NULL,               -- e.g. 'main', 'intro', 'facts'
  area_code     VARCHAR(40)   NOT NULL,               -- e.g. 'about', 'department.cse'
  title         VARCHAR(255)  NULL,
  body_html     MEDIUMTEXT    NULL,
  sort_order    INT           NOT NULL DEFAULT 0,
  visible       TINYINT(1)    NOT NULL DEFAULT 1,
  updated_by    VARCHAR(255)  NULL,
  updated_at    DATETIME      NULL,
  UNIQUE KEY uk_page_section (page_key, section_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 03 menus and link lists
CREATE TABLE link_item (
  link_id       BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  list_code     VARCHAR(60)   NOT NULL,               -- 'main_menu', 'top_bar', 'footer', 'social', 'about_sidebar',
                                                      -- 'home_important_links', 'home_marquee', 'library_links', ...
  area_code     VARCHAR(40)   NOT NULL,               -- 'menu' for menus/sidebars, else the page's area
  parent_id     BIGINT        NULL,                   -- submenu entries
  group_label   VARCHAR(100)  NULL,                   -- e.g. 'Under Graduates' / 'Post Graduates'
  label         VARCHAR(255)  NOT NULL,
  url           VARCHAR(1000) NOT NULL,
  open_new_tab  TINYINT(1)    NOT NULL DEFAULT 0,
  icon_class    VARCHAR(100)  NULL,                   -- social icons
  visibility    VARCHAR(20)   NOT NULL DEFAULT 'all', -- 'all' | 'desktop' | 'mobile' | 'anonymous' | 'authenticated'
  sort_order    INT           NOT NULL DEFAULT 0,
  visible       TINYINT(1)    NOT NULL DEFAULT 1,
  updated_by    VARCHAR(255)  NULL,
  updated_at    DATETIME      NULL,
  KEY ix_link_list (list_code, parent_id, sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 04 role/contact lists that are not (only) faculty profiles
CREATE TABLE contact_person (
  contact_id    BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  list_code     VARCHAR(60)   NOT NULL,               -- 'library', 'cultural_club', 'sports_club', 'hostel',
                                                      -- 'web_team', 'principals', 'pio'
  area_code     VARCHAR(40)   NOT NULL,
  group_label   VARCHAR(255)  NULL,                   -- e.g. hostel name
  role_label    VARCHAR(255)  NULL,                   -- 'Prof. In-charge', 'Superintendent', 'Content Head'
  person_name   VARCHAR(255)  NOT NULL,
  detail        VARCHAR(255)  NULL,                   -- department / batch / period ('2018 to till now')
  phone         VARCHAR(30)   NULL,                   -- VARCHAR: keeps leading 0 / +91
  email         VARCHAR(255)  NULL,
  show_contact  TINYINT(1)    NOT NULL DEFAULT 1,     -- 0 = hide phone/e-mail on the public page
  profile_url   VARCHAR(1000) NULL,
  faculty_id    BIGINT        NULL,                   -- optional link to faculty (photo, profile)
  image_id      BIGINT        NULL,                   -- optional site_image
  sort_order    INT           NOT NULL DEFAULT 0,
  visible       TINYINT(1)    NOT NULL DEFAULT 1,
  updated_by    VARCHAR(255)  NULL,
  updated_at    DATETIME      NULL,
  KEY ix_contact_list (list_code, sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 05 images grouped in albums
CREATE TABLE site_image (
  image_id      BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  album_code    VARCHAR(60)   NOT NULL,               -- 'photo_gallery', 'computer_centre', 'library', 'home_gallery',
                                                      -- 'web_team', 'aicte_banner', 'principal', 'logo'
  area_code     VARCHAR(40)   NOT NULL,
  caption       VARCHAR(255)  NULL,
  file_name     VARCHAR(255)  NULL,
  file_type     VARCHAR(50)   NULL,
  image         MEDIUMBLOB    NULL,                   -- uploaded image ...
  static_path   VARCHAR(500)  NULL,                   -- ... or an existing file, e.g. '/wp-content/uploads/2018/img1.JPG'
  sort_order    INT           NOT NULL DEFAULT 0,
  visible       TINYINT(1)    NOT NULL DEFAULT 1,
  created_by    VARCHAR(255)  NULL,                   -- needed for 'A' (add-only) users
  uploaded_at   DATETIME      NULL,
  KEY ix_image_album (album_code, sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 06 fee tables
CREATE TABLE fee_item (
  fee_id        BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  schedule_code VARCHAR(60)   NOT NULL,               -- 'day_scholar', 'hostel'
  area_code     VARCHAR(40)   NOT NULL,               -- 'fees' / 'hostel'
  label         VARCHAR(255)  NOT NULL,               -- '(i) Registration fee'
  category      VARCHAR(60)   NULL,                   -- 'GEN_BC_EBC', 'SC_ST' (NULL for hostel)
  year_no       TINYINT       NULL,                   -- 1..4 (NULL for hostel)
  amount        DECIMAL(10,2) NULL,
  amount_text   VARCHAR(255)  NULL,                   -- hostel rows: '6000/- (for Six Months 6 * 1000=6000)'
  sort_order    INT           NOT NULL DEFAULT 0,
  updated_by    VARCHAR(255)  NULL,
  updated_at    DATETIME      NULL,
  KEY ix_fee_schedule (schedule_code, sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 07 the department mini-sites (10 sites, but `department` has 7 rows)
CREATE TABLE department_site (
  site_code       VARCHAR(40)  NOT NULL PRIMARY KEY,  -- URL segment: 'cse', 'mechanical', 'ecevlsi', ...
  dept_id         BIGINT       NULL,                  -- department.dept_id used for /faculty?deptno= and Faculty scope
  lab_dept_id     INT          NULL,                  -- lab_document.dep_id used for /academic/lab?dept_id=
  full_name       VARCHAR(255) NOT NULL,              -- 'Computer Sc. & Engineering'
  short_name      VARCHAR(60)  NOT NULL,              -- 'CSE' (sidebar titles, placed-student branch column)
  menu_group      VARCHAR(10)  NOT NULL,              -- 'UG' | 'PG'
  about_path      VARCHAR(100) NOT NULL,              -- '/department/mechanical/mech' (keeps today's URL)
  notice_code     VARCHAR(20)  NULL,                  -- prefix of noticeboard.nb_noticetype: 'CSE','CIVIL','MECH','ELEC','ASH','FP'
  hod_username    VARCHAR(255) NULL,                  -- HOD scope; replaces UserName eq 'hodcse' etc. in JSPs
  duration_years  TINYINT      NULL,                  -- Product & Services page
  course_level    VARCHAR(60)  NULL,                  -- 'Graduation'
  sort_order      INT          NOT NULL DEFAULT 0,
  visible         TINYINT(1)   NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- If one HOD may be in charge of several sites (today: yes) and one site may have more than one editor-HOD,
-- a link table replaces hod_username:  department_site_hod(site_code, username, PRIMARY KEY(site_code, username)).

-- 08 notice types (today: string literals in 4 upload forms + NoticeBoardServiceImpl)
CREATE TABLE notice_type (
  code          VARCHAR(40)  NOT NULL PRIMARY KEY,    -- value stored in noticeboard.nb_noticetype ('CSENotice', ...)
  label         VARCHAR(100) NOT NULL,
  site_code     VARCHAR(40)  NULL,                    -- NULL = college-wide (Notice/News/Tender)
  area_code     VARCHAR(40)  NOT NULL DEFAULT 'notices', -- who may post: via content_area_role
  for_admin     TINYINT(1)   NOT NULL DEFAULT 1,      -- kept for today's forms (Admin/HOD/Faculty)
  for_hod       TINYINT(1)   NOT NULL DEFAULT 0,
  for_faculty   TINYINT(1)   NOT NULL DEFAULT 0,
  sort_order    INT          NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 09 bulk-SMS groups (today: phone lists in SMS.properties, committed to git)
CREATE TABLE sms_group (
  group_code    VARCHAR(40)  NOT NULL PRIMARY KEY,    -- 'KECWebTeam', 'allStudents', 'allParents', 'allFaculties'
  label         VARCHAR(100) NOT NULL,
  sort_order    INT          NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE sms_group_member (
  member_id     BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  group_code    VARCHAR(40)  NOT NULL,
  phone         VARCHAR(20)  NOT NULL,
  name          VARCHAR(255) NULL,
  KEY ix_sms_member (group_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 10 content areas and who may edit them (seed = the matrix in §1.3.3)
CREATE TABLE content_area (
  area_code     VARCHAR(40)  NOT NULL PRIMARY KEY,    -- 'tp', 'rti', 'department.cse', ...
  label         VARCHAR(100) NOT NULL,                -- tile title on /manage
  description   VARCHAR(255) NULL,
  site_code     VARCHAR(40)  NULL,                    -- set for department.<site> areas (department scope)
  sort_order    INT          NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE content_area_role (
  area_code     VARCHAR(40)  NOT NULL,
  role_name     VARCHAR(255) NOT NULL,                -- = test_role.name (the Spring authority)
  access        CHAR(1)      NOT NULL,                -- 'E' edit | 'A' add only
  scope         VARCHAR(20)  NOT NULL DEFAULT 'all',  -- 'all' | 'own_department' | 'own_record'
  PRIMARY KEY (area_code, role_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 11 document types for the generic document screen (download.doc_type values)
CREATE TABLE document_type (
  doc_type      VARCHAR(30)  NOT NULL PRIMARY KEY,    -- same length as download.doc_type
  label         VARCHAR(100) NOT NULL,                -- 'Allotment & Surrender Report'
  area_code     VARCHAR(40)  NOT NULL,                -- who may upload/delete
  display_mode  VARCHAR(20)  NOT NULL DEFAULT 'list', -- 'list' | 'embed_latest' | 'list_embed_first'
  empty_text    VARCHAR(255) NULL,                    -- today's placeholder ('Coming Soon', 'Updated Soon...')
  sort_order    INT          NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 12 who changed what (history / restore for all new content tables)
CREATE TABLE content_change_log (
  log_id        BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  area_code     VARCHAR(40)  NOT NULL,
  entity        VARCHAR(40)  NOT NULL,                -- 'page_section', 'link_item', 'download', ...
  entity_id     VARCHAR(100) NOT NULL,
  action        VARCHAR(10)  NOT NULL,                -- 'create' | 'update' | 'delete' | 'restore'
  old_value     MEDIUMTEXT   NULL,                    -- JSON of the row before the change (no file bytes)
  new_value     MEDIUMTEXT   NULL,                    -- the row after the change
  changed_by    VARCHAR(255) NOT NULL,
  changed_at    DATETIME     NOT NULL,
  KEY ix_change_entity (entity, entity_id, changed_at),
  KEY ix_change_area (area_code, changed_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

Data-only migration (no DDL, needs approval because it changes data):

```sql
-- 10_roles_seed.sql – new authorities (ids by AUTO_INCREMENT as today)
INSERT INTO test_role (name) VALUES ('SubAdmin'), ('Principal'), ('Registrar'), ('ExamController'), ('TPO'),
  ('NBA'), ('Accounts'), ('Librarian'), ('Warden'), ('PIO'), ('ClubInCharge'), ('Staff');
-- users get them via /auth/roles (user_role rows); nobody gets a new role automatically.
```

Optional `ALTER`s on existing tables (each one is a separate decision, §8):

```sql
-- A1 staff: department, designation, ordering (department "Staff of …" pages say "No Staffs here.")
ALTER TABLE staff ADD COLUMN dept_id BIGINT NULL, ADD COLUMN designation VARCHAR(255) NULL,
                  ADD COLUMN sort_order INT NOT NULL DEFAULT 0;
-- A2 faculty: designation and ordering (facultylist.jsp prints "Assistant Professor" for everybody).
--     Alternative without DDL: a 'Designation' row in faculty_key_props/faculty_keyprop_values.
ALTER TABLE faculty ADD COLUMN designation VARCHAR(255) NULL, ADD COLUMN sort_order INT NOT NULL DEFAULT 0;
-- A3 download: optional external link and a display title separate from the file name
--     (syllabus/calendar entries are links to akubihar.ac.in / beu-bih.ac.in, not files).
--     Alternative without DDL: put those links in link_item.
ALTER TABLE download ADD COLUMN title VARCHAR(255) NULL, ADD COLUMN link VARCHAR(1000) NULL;
-- A4 download: uploader, so 'A' users can delete only their own documents.
--     Alternative without DDL: read the uploader from content_change_log ('create' row).
ALTER TABLE download ADD COLUMN created_by VARCHAR(255) NULL;
```

New `download.doc_type` values (no DDL; existing queries filter on `'download'`/`'nba'` so nothing else changes), each
with a `document_type` row: `allotment`, `regulation`, `aku_approval`, `mandatory_disclosure`, `tp_brochure`,
`tp_rules`, `tp_resume_sample`, `tp_resume_tips`, `payment_tutorial`, `rti_act`, `rti_application`, `rti_response`,
`academic_council`, `govt_notice`, `antiragging`, `disciplinary_rules`, `mentors`, `university_result`,
`startup_cell`, `syllabus`, `calendar`, and per department `qb_<site>`, `cf_<site>`, `wt_<site>` (question bank,
course file, weekly test – §8 D5). Seeding the PDFs that exist on disk today means inserting their bytes (a small
one-off import tool or `LOAD_FILE`), **or** keeping the static file and linking it from
`link_item`/`site_image.static_path` style – see §8 D6.

---

## 3. Proposed application configuration (`kec.*`, values = today's literals)

Only things that change with a deployment, not with daily content work (so nobody edits them in a screen):

| Key | Today | Where |
|---|---|---|
| `kec.site.canonical-url` | `http://www.keck.ac.in` | `header.jsp` canonical / shortlink / `og:url` |
| `kec.site.favicon` | `/images/logo1.jpeg` | `header.jsp` |
| `kec.college.short-name` | `KEC, Katihar` | `DocUtils` (leave PDF "The Principal / KEC, Katihar") |
| `kec.calendar.location` | `Katihar, Bihar, India` | `LeaveMailService` |
| `kec.calendar.utc-offset` | `-07:00` (bug, should be `+05:30`; §7) | `LeaveMailService` |
| `kec.mail.attendance-subject` | `KEC, Attendance Report.` | `AttendanceReportService` |
| `kec.upload.max-document-size`, `kec.upload.max-image-size` | multipart limit only | new `/manage` upload screens (T4, T6) |
| `kec.security.landing.*` | `/auth/uploadDashboard`, `/hod/hodDashboard`, `/sk/storekeeper`, … | `KECUrlAuthenticationSuccessHandler` – recommended **keep in code** (security routing, not content) |

Already in config since the refactoring (§9.4 of the upgrade notes): mail from/to, upload paths, PDF logo, SMS URL/sender,
Firebase, calendar ids, attendance-report recipient.

---

## 4. Page-by-page findings (order of the main menu)

Columns: **Hard-coded today** · **Source** · **Screen** (`/manage/<screen>` unless an existing screen is named) ·
**How I'd address it** (treatment from §1.2 + page-specific steps) · **Who can update** (in addition to Admin and
SubAdmin, see the legend at the top) · **Notes**.

### 4.1 Home `/`  (`index.jsp`, `achievement.jsp`, `category/webteamswiper.jsp`) – area `home`

Already dynamic: image slider (`image_slide`), achievements (`achievement_slide`), notice lists and scrolling notices
(`noticeboard`).

| Hard-coded today | Source | Screen | How I'd address it | Who can update | Notes |
|---|---|---|---|---|---|
| Top marquee: 3 links (AICTE EVC video, placement portal, AICTE feedback) | `N:link_item` `home_marquee` | links | T3: seed the 3 links; the `<marquee>` element and separators stay in the JSP, only the `<a>` list comes from the DB | – | |
| "Important Links" box: 8 government/university links | `N:link_item` `home_important_links` | links | T3: same `<ul>` markup, `open_new_tab` = 1 as today | – | "Social Welfare Dept. Bhar" typo kept in seed (same look) – fix in the screen later |
| "About KEC" paragraph + "more" link | `N:page_section` `home.about` | sections | T2: section `home.about/main`; the "more" link stays in the JSP (URL is fixed) | Principal (E) | |
| "Gallery" strip: 3 images (`/images/sportkec*.jpeg`, captions unrelated to the photos) | `N:site_image` `home_gallery` (`static_path`) | images | T6: seed 3 rows with `static_path` + today's captions; the include renders the first 3 visible images | – | |
| Video gallery YouTube embed id | `N:site_setting` `home.video_embed_url` | settings | T1 (`value_type = url`, only `youtube.com/embed/…` accepted) | – | |
| Google Maps embed URL | `N:site_setting` `contact.map_embed_url` | settings | T1 (`value_type = url`, only `google.com/maps/embed…`) | – | also usable on Contact pages |
| Notice icon `/sites/noticeicon.gif`, title-bar background images | keep | – | K | – | theme assets, not content |
| Web-team swiper (names, photos, roles) | `N:contact_person` `web_team` + `N:site_image` | people (Admin) | T5: the swiper include loops over `web_team`; photo from `site_image` | **Admin only** (area `system`) | same data as §4.12 |
| Slider, achievements, notices (existing) | `T:image_slide`, `T:achievement_slide`, `T:noticeboard` | existing `/auth/uploadfile/uploadslideimage`, `/auth/uploadfile/achievementslideimage`, `/auth/uploadfile/upload` | T8: add caption edit + reorder, POST deletes (§6.6); notices see §1.3.3 `notices` | slider/achievements: –; notices: Registrar (E), ExamController/TPO/Staff (A) | |

### 4.2 About KEC

| Page | Hard-coded today | Source | Screen | How I'd address it | Who can update | Notes |
|---|---|---|---|---|---|---|
| **Sidebar** (7 JSPs repeat the same 7 links; `academic/administration.jsp` too) | link list | `N:link_item` `about_sidebar`, rendered by one include | links | T3: one include `about/sidebar.jsp` replaces the 8 copies; the active entry is highlighted by comparing the request URI (same CSS class as today) | – (area `menu`) | removes 8 copies |
| **History** `/about/history` | history text; list of principals (2 rows with periods); facts table (motto, motto in English, students 720, established 2016, affiliations, website, location); logo image | text → `N:page_section` `about.history`; principals → `N:contact_person` `principals`; facts → `N:site_setting` `college.*` | sections / people / settings | T2 for the text; T5 for principals (period in `detail`); T1 for each fact (`college.motto`, `college.students`, `college.established`, …); the facts table layout stays in the JSP | Principal (E) | "Students 720" is a number that goes stale – setting |
| **Administration** `/about/administration` | page renders `facilities/responsibilityDoc.jsp` (responsibility docs, **0 rows** → empty table); the `administration` table (26 rows) is **not shown anywhere**: `about/administration.jsp` expects `adminsDetails` that no controller sets; `/academic/adminsValue` (`academic/administration.jsp`, 1,791 lines) is a hard-coded list of office holders with photos and personal phone numbers, linked from nowhere | `T:administration` (+ `T:faculty` for photo/name/phone) and `T:responsibility_doc` | existing `/auth/uploadfile/updateAdministration` (+ edit/reorder to add) | T8: controller sets `adminsDetails` (rows joined to faculty for photo/name); add edit + order to the screen; secure `/api/uploadAdministration` (anonymous today, §6.2); responsibility docs stay below | Registrar, Principal (E) | ⏸ D3: show `administration` rows on this page (16 rows have an empty role and need cleaning first) |
| **Principal's Message** `/about/principal` | 4 paragraphs, principal's name and personal e-mail, photo hard-wired to faculty id `11` (`/11/image`) | text → `N:page_section` `about.principal`; name/e-mail/faculty id → `N:site_setting` `principal.name`, `principal.email`, `principal.faculty_id` | sections / settings | T2 for the message; T1 for name, e-mail and faculty id (photo URL becomes `/${site['principal.faculty_id']}/image`); when a new principal joins, the editor changes 3 settings and the text | Principal (E) | |
| **Vision & Mission** `/about/vision` | 1 vision item, 3 mission items | `N:page_section` `about.vision` (sections `vision`, `mission`) | sections | T2: two sections, each the `<ul>` as today | Principal (E) | |
| **Affiliation** `/about/affiliation` | 1 paragraph with BEU link | `N:page_section` `about.affiliation` | sections | T2 | Principal (E) | |
| **Allotment & Surrender Report** `/about/allotment` | heading + table row "Coming Soon" | `D:allotment` – list of uploaded reports (year/title, date, download), newest first; **each upload is appended**, never replaces; first PDF embedded like the holiday/responsibility pages | documents (`/manage/documents?type=allotment`) | T4 with `display_mode = list_embed_first`, `empty_text = 'Coming Soon'`; the table header and heading stay in the JSP; each row: title, upload date, "View/Download" → `/facilities/document/{id}` | Registrar, Principal (E) (area `office`) | when the list is empty the page shows today's "Coming Soon" row, so it looks the same until the first upload. Needs A3 (`title`) or uses the file-name-as-title convention the downloads page already uses |
| **Support Staff** `/about/staff` | already dynamic (`staff`) – see the review in §6.1 | `T:staff` | existing `/auth/uploadfile/addstaff`; edit missing | T8: fix §6.1 (Add button, edit screen, POST delete, `/staff/{id}/image`, ordering with A1) | Registrar (E); Staff (own record: photo, phone) | |

### 4.3 Academic

| Page | Hard-coded today | Source | Screen | How I'd address it | Who can update | Notes |
|---|---|---|---|---|---|---|
| **Sidebar** (12 links, repeated per page; contains the broken link `/academic/ attendance` with a space) | link list | `N:link_item` `academic_sidebar` | links | T3: one include; seed with the corrected URL only if B3 is approved, otherwise byte-identical | – (area `menu`) | §7 |
| **Admission** `/academic/admission` | entrance-test text, 2018 prospectus link, qualification rules, qualifying-exam list, residential criteria | `N:page_section` `academic.admission` | sections | T2: one section per block (`entrance`, `qualification`, `residential`); the dynamic seat table stays where it is | Registrar, Principal (E) | the seat table is dynamic (`no_of_seat`) but the table has **0 rows** (shows an empty table); its Delete link points to `/auth/courses/{id}` but the endpoint is `/auth/course/{id}` (§7) |
| Seat table (existing) | `no_of_seat` | `T:no_of_seat` | existing `/auth/uploadfile/coursecreator` | T8: fix delete link (B4), POST delete, branch from `department_site` (§6.5), widen `/auth/uploadfile/coursecreator`, `/auth/course/**` to `Registrar` | Registrar, Principal (E) | |
| **Academic Regulations** | embedded `/jsp/academic/regulation.pdf` – **file missing from the repo** (404 locally) | `D:regulation` | documents | T4 `embed_latest`; until something is uploaded the iframe keeps pointing to today's path (same 404 locally, works where the file exists) | Registrar, Principal (E) | |
| **Academic Calendar** | BEU calendar link | `N:link_item` `academic_calendar` (or `D:calendar` with A3 link) | links | T3 (a list of calendar links, newest first); with A3 also uploaded PDFs | ExamController (E) (area `exam`) | |
| **Computer Center Labs** (menu: `/academic/lab?id=1`) | **page does not exist**: the menu sends `id=1`, the controller requires `dept_id` → HTTP 400. The entry is in the desktop menu only (missing from the mobile menu). `/facilities/csecenter` ("Computer Centre") is a separate static page with one image and two paragraphs | new page (⏸ D4, URL choice) with intro text `N:page_section` `academic.computer_centre`, a photo grid `N:site_image` album `computer_centre` (admin uploads/reorders/deletes photos, captions), optional lab list `N:link_item`/lab docs | sections / images | new JSP using T2 (intro) + T6 (photo grid, captions, reorder) + optional T3 (lab list); `/facilities/csecenter` renders the **same** section and album so there is one source; menu entry fixed (B1) | Registrar (E) (area `facilities`); optionally a `ComputerCentreInCharge` role or HOD CSE via `content_area_role` | needs content from you (text + photos). Recommend one content source shared with `/facilities/csecenter` |
| **List of Holidays** | dynamic (`holiday`, 1 row) | `T:holiday` | existing `/auth/uploadHoliday` | T8: generic document component (§6.4); widen `/auth/uploadHoliday`, `/auth/api/uploadHoliday` to Registrar | Registrar, Principal (E) (area `office`) | §6 |
| **Attendance** | "Updated Soon…" | `N:page_section` `academic.attendance` | sections | T2, seed = the placeholder | ExamController (E) | placeholder text is seed |
| **Syllabus** | 2 AKU PDF links with titles | `N:link_item` `academic_syllabus` (or `D:syllabus` + A3) | links | T3 (title + link); with A3 uploaded PDFs too | ExamController (E) | |
| **Disciplinary Rules** | "Updated Soon…" | `D:disciplinary_rules` + `N:page_section` intro | documents | T4 `list`, empty ⇒ today's text; optional T2 intro | Registrar, Principal (E) | empty list ⇒ shows today's placeholder |
| **Online Fee Payment Tutorial** | 11 steps + PDF link `/jsp/facilities/payment.pdf` | steps → `N:page_section` `academic.paymenttutorial`; PDF → `D:payment_tutorial` | sections / documents | T2 (the `<ol>` of steps) + T4 `list` (link to the latest PDF; today's static path as fallback) | Accounts (E) (area `fees`) | same PDF also linked from `facilities/bank.jsp` and the admin dashboard – all three read the latest `payment_tutorial` |
| **Anti Ragging** | "Updated Soon…" | `D:antiragging` + section | documents | T4 + optional T2 intro (committee, helpline) | Registrar, Principal (E) | |
| **MoM of Academic Council** | "Updated Soon…" | `D:academic_council` | documents | T4 `list` (one PDF per meeting, newest first) | Registrar, Principal (E) | |
| **Notice from Govt.** | "Updated Soon…" | `D:govt_notice` (or a new notice type `GovtNotice` in `noticeboard`) | documents | T4 `list`; recommended over a notice type because these are documents with long life | Registrar, Principal (E) | |
| University Results `/academic/universityresults` | "Updated Soon…" | `D:university_result` | documents | T4 `list` | ExamController (E) | sidebar only |
| Mentors `/academic/mentors` | "Updated Soon…" | `D:mentors` | documents | T4 `list` (mentor-mentee lists per session) | Registrar (E); HOD (A) | sidebar only |
| `/academic/adminsValue` | orphan static copy of the administration list | remove after §4.2 decision | – | after D3: redirect to `/about/administration` (URL kept, no 404) | – | ⏸ D3 |

### 4.4 Departments (10 mini-sites under `/department/<site>/…`) – areas `department.<site>`

One `DepartmentController` already serves all sites; the content is in ~120 JSPs.

| Hard-coded today | Source | Screen | How I'd address it | Who can update | Notes |
|---|---|---|---|---|---|
| Department list in the main menu (UG/PG groups), `category/department.jsp`, Product & Services course list, `FacultyListView.BRANCH_NAMES`, dropdowns (§5.3) | `N:department_site` | `/auth/content/departments` | one Admin screen for the 10 site rows; menu group, names, ordering; all lists render from it (T3-style include for the menu part) | **Admin only** (area `system`) | the desktop menu labels `/department/ece/about` as "Electronics Engineering (VLSI Design and Technology)" – same label as `ecevlsi`; the mobile menu omits Mechanical & Smart Manufacturing, ECE-VLSI (PG) and Manufacturing (§7) |
| Sidebar per site (11–13 links each; 11 copies incl. `fp/fpsidebar.jsp`) | generated from `N:department_site` (+ `N:link_item` `department_sidebar` for the page list) | `/auth/content/departments` + links | one include `department/sidebar.jsp` builds the links from `site_code`, `dept_id`, `lab_dept_id`; page list from `department_sidebar` | **Admin only** (structure); HOD cannot change it | **deptno/dept_id mismatches** (§7): ece/ecevlsi → `deptno=4` (= EEE); fp → `deptno=7` (= MSM) and `lab dept_id=5` (= ASH); mechandsmartmanufacturing → `deptno=3` |
| About: introduction text, sub-division lists, highlights/USPs, careers, recruiters, intake "60" | `N:page_section` `department.<site>.about` | sections | T2: sections `intro`, `highlights`, `careers`, `recruiters`; intake as a section field or setting `department.<site>.intake` | HOD (own dept) (E) | dept notices already dynamic |
| HOD-only delete link: `UserName eq 'hodcse'`, `'hodcivil'`, `'hodmech'`, `'hodeee'`, `'hodash'`, `'hodfood'`, `'principal'` (40 occurrences) | `N:department_site.hod_username` (or `department.dept_hod` + `user_role`) | `/auth/roles` | replace each check by `<kec:canEdit area="department.<site>">`; seed `hod_username` with today's accounts and keep `principal` as an Admin-level exception ⇒ the same users see the link | (permission check, not content) | |
| Vision & Mission | `N:page_section` `department.<site>.vision` | sections | T2 | HOD (own dept) (E) | |
| Labs: lab names (cse 7, civil 7 with equipment lists, eee 5, ash 3, ece 1 block, fp empty, mech "Updated Soon") | `N:page_section` `department.<site>.labs` + existing `T:lab_document` iframe | sections; existing `/auth/uploadLabDoc` | T2 for the lab list/equipment text; lab documents stay in `lab_document` (T8), iframe URL from `department_site.lab_dept_id` | HOD (own dept) (E) – already allowed for lab docs today | cse/labs.jsp embeds `/academic/lab?dept_id=1` in an iframe |
| Faculty: `mechanical|manufacturing|mechandsmartmanufacturing/faculty.jsp` – static cards (names, faculty ids for photos, **personal phones and e-mails**) | `T:faculty` (same data `/faculty?deptno=3` shows) | existing add/edit faculty | replace the static cards with the same include `/faculty?deptno=` uses (card markup kept); needs D2 for the right dept ids | as today: Faculty (own profile), HOD (add faculty) | other sites link to `/faculty?deptno=N` already |
| Staff: "No Staffs here." (cse, ash, fp; ece/eee/ecevlsi "Updated Soon") | `T:staff` + **A1** (`dept_id`) | existing add staff + edit | T8: list `staff` rows with `dept_id`; empty ⇒ today's text | Registrar (E); HOD (own dept): order and designation | |
| Principal page `ece|eee|ecevlsi/principal.jsp`: profile with hard-coded qualification/research/role | `T:faculty_key_props` (the template already reads the faculty profile) | existing edit profile | render the faculty key props of `principal.faculty_id` (setting) instead of the literals | Principal / the faculty member (own profile) | |
| Question Bank (empty table), Weekly Test Result, Course File | `D:qb_<site>`, `D:wt_<site>`, `D:cf_<site>` | documents | T4 `list` per site; the existing empty table header stays; each row shows uploader and date | HOD (own dept) (E); Faculty (own dept) (A) | ⏸ D5 |
| Awards, Program Activities, Students, Attendance ("Updated Soon…") | `N:page_section` `department.<site>.<page>` | sections | T2, seed = today's text | HOD (own dept) (E) | |
| Share links with a wrong target (`ash/about.jsp`, `civil/attendance.jsp` share "About Civil Engg." on twitter) and social icons | `N:link_item` `social` | links | social icons from T3 `social`; share URL built from the current page URL and title (fixes B19, approval) | – (area `menu`) | |
| Per-faculty shortcut URLs (~75, `FACULTY_USERNAMES` map) | `T:faculty.username` | – | look up `faculty.username` and render `facultydetails.jsp` (D8) | – | target JSPs don't exist (broken today, baseline); after the change they could render `facultydetails.jsp` – ⏸ D8 |

### 4.5 Facilities

| Page | Hard-coded today | Source | Screen | How I'd address it | Who can update | Notes |
|---|---|---|---|---|---|---|
| Sidebar (7–15 links, differs per page) | | `N:link_item` `facilities_sidebar` | links | T3; where the page-specific differences are intentional, separate `list_code`s (e.g. `facilities_sidebar_hostel`), else one list | – (area `menu`) | |
| Bank | one sentence + payment PDF | `N:page_section` + `D:payment_tutorial` | sections / documents | T2 + T4 (latest payment tutorial) | Accounts (E) | |
| Central Library | photo, 5 e-resource links, 2 contact persons (names/designations) | photo → `N:site_image` `library`; links → `N:link_item` `library_links`; contacts → `N:contact_person` `library` (with `faculty_id`) | images / links / people | T6 (1 photo) + T3 (e-resources) + T5 (contacts); optional T2 for timings/rules | Librarian (E) | |
| Computer Centre `/facilities/csecenter` | 1 photo (`../wp-content/uploads/2018/img3.JPG`, relative path), 2 paragraphs ("approx 50 computers", 4 Mbps) | shared with §4.3 Computer Center Labs | sections / images | same section + album as §4.3 | Registrar (E) | |
| Club | Cultural club: 2 in-charges with **personal phones** | `N:contact_person` `cultural_club` | people | T5; `show_contact` lets the in-charge hide the number | ClubInCharge (E) | |
| Guest House, Health Centre, Medical, Useful Link | "Updated Soon…" | `N:page_section` | sections | T2 each (`facilities.guesthouse`, …); Useful Link could also be T3 | Registrar (E) | `/facilities/usefulllink` (3 l's) is the real URL – keep |
| Gymnasium | one sentence | `N:page_section` | sections | T2 | Registrar, ClubInCharge (E) | |
| Hostels | 3 hostels × superintendent/assistant; hostel charges table (admission 1000, maintenance 6000, mess 3000/month, totals 7000 / 9000 / 16000) | wardens → `N:contact_person` `hostel` (`group_label` = hostel); charges → `N:fee_item` `hostel` | people / fees | T5 grouped by `group_label` (same per-hostel table) + T7 for the charges (totals computed) | Warden (E); Accounts (E) for charges | |
| Placement `/facilities/placementbrochure` | embedded brochure PDF | `D:tp_brochure` (same as §4.6) | documents | T4 `embed_latest` | TPO (E) | |
| Sports Facilities | 2 in-charges | `N:contact_person` `sports_club` | people | T5 (+ optional T2 facility text) | ClubInCharge (E) | |
| Startup Cell | 2 "Download" links to `https://#/…` (dead), "Updated Soon" | `D:startup_cell` | documents | T4 `list`; empty ⇒ today's text (dead links disappear only with approval of B10) | TPO (E) | dead links today (§7) |
| Wi-Fi | 2 paragraphs (BSNL, 4 Mbps, hours) | `N:page_section` | sections | T2 | Registrar (E) | |
| Contact Us `/facilities/contactus` | address, PIN **854106**, phone; contact form posts to `doContact()` which is **not defined anywhere** (form does nothing) | `N:site_setting` `contact.*` | settings | T1 for address/PIN/phone; form fixed only with B11 approval (would mail to `kec.mail.grievance-to` style config) | – (area `site`) | PIN differs from the fee page (**854109**) – which one is right? (§8 D9) |
| Downloads | dynamic (`download`/`download`) | `T:download` | existing `/auth/uploadDownload` | T8 + generic document component; widen `/auth/uploadDownload`, `/auth/api/uploadDownload` | Registrar, ExamController (E) | §6 |
| `facilities/holiday.jsp` | unreferenced duplicate of the responsibility-doc page | – | – | delete (no URL uses it) | – | delete candidate |

### 4.6 Training & Placement – area `tp`

| Page | Hard-coded today | Source | Screen | How I'd address it | Who can update | Notes |
|---|---|---|---|---|---|---|
| Sidebar + external "Placement Portal" `https://tpo.keckatihar.in` (also on home marquee) | | `N:link_item` `tp_sidebar` | links | T3 | – (area `menu`); portal URL: TPO (E) via setting `tp.portal_url` | mobile menu's "About Placement" points to the brochure, desktop to `/trainingplacement/aboutplacement` (§7) |
| About Placement | heading + embedded brochure | `N:page_section` + `D:tp_brochure` | sections / documents | T2 (about text, cell members) + T4 `embed_latest` | TPO (E) | |
| Placement Brochure | `/jsp/trainingplacement/brochure.pdf` | `D:tp_brochure` | documents | T4 `embed_latest`, fallback today's path | TPO (E) | |
| Rules of T&P | embeds `sample.pdf` (the **resume sample**, not rules) | `D:tp_rules` | documents | T4 `embed_latest`; until the rules are uploaded the fallback keeps today's file (B9 fixed by uploading) | TPO (E) | wrong file today (§7) |
| Tips for Resume | resume-tips text + 2 PDFs | `N:page_section` + `D:tp_resume_sample`, `D:tp_resume_tips` | sections / documents | T2 + T4 ×2 | TPO (E) | |
| **Placed Students** | dynamic list from `placements` (0 rows) **plus 83 hard-coded rows** (sessions 2016-20: 10, 2017-21: 30, 2018-22: 35, 2019-23: 8) | `T:placements` – seed the 83 rows; branch shown as `department_site.short_name` (today the dynamic part prints the numeric `department_id`, the static part prints "CSE") | existing `/auth/uploadfile/addPlacements` (+ edit) | T8: seed 83 rows, delete the static rows from the JSP, group by session as today; add edit; widen `/auth/uploadfile/addPlacements`, `/auth/addPlacement` to TPO; optional CSV import for a whole batch | TPO (E) | registration numbers fit `bigint`. Seed contains student names – personal data of students, already public on the site |
| Student Placement Coordinator | dynamic (`tpo_coordinator`, 0 rows); department shown as a number | `T:tpo_coordinator` + department name | existing `/auth/uploadfile/addtpocoordinator` | T8: show department name, add edit, POST delete; widen `/auth/uploadfile/addtpocoordinator`, `/auth/tpoCoordinator/**` to TPO | TPO (E) | §6 |
| Placement notices (new) | – | `T:noticeboard` new type `PlacementNotice` (via `N:notice_type`) | existing notice upload | shown on the T&P pages and in the home notice list | TPO (A) | optional, D18 |

### 4.7 Approval – area `approval`

| Page | Hard-coded today | Source | Screen | How I'd address it | Who can update | Notes |
|---|---|---|---|---|---|---|
| AICTE Approval | dynamic list (`aicte_documents`, 1 row); banner image `/jsp/approval/img.jpg` | `T:aicte_documents`; banner → `N:site_image` `aicte_banner` | existing `/auth/uploadAICTEapproval` + images | T8 (generic document component, widen `/auth/uploadAICTEapproval`, `/auth/api/uploadAICTEapproval`) + T6 for the banner | Registrar, Principal (E) | |
| AKU Approval | embedded `aku.pdf`, caption "AKU Regulation —-" | `D:aku_approval` | documents | T4 `embed_latest` with caption = document title | Registrar, Principal (E) | |
| Mandatory Disclosure | **shows the AKU PDF** (copy-paste) | `D:mandatory_disclosure` | documents | T4 `embed_latest`; fallback = today's AKU PDF until the real one is uploaded (D10) | Registrar, Principal (E) | wrong content today (§7); empty list ⇒ keep showing aku.pdf until you upload the real one? (§8 D10) |
| Grievance | phone `06452-239122`; form → mail to `kec.mail.grievance-to` | `N:site_setting` `contact.phone` | settings | T1; form unchanged | – (area `site`) | recipient already config |

### 4.8 RTI – area `rti`

| Page | Hard-coded today | Source | Screen | How I'd address it | Who can update | Notes |
|---|---|---|---|---|---|---|
| Sidebar | | `N:link_item` `rti_sidebar` | links | T3 | – (area `menu`) | |
| RTI Act 2005 | `/wp-content/uploads/2018/rti-act-2.pdf` | `D:rti_act` | documents | T4 `embed_latest`/link, fallback today's path | PIO (E) | |
| RTI Application Form | `RTI-application-format.pdf` | `D:rti_application` | documents | T4, fallback today's path | PIO (E) | |
| RTI Status | college header text, "No RTI's filed" | `D:rti_response` list; empty ⇒ "No RTI's filed" | documents | T4 `list` (title = RTI no./date), empty ⇒ today's text | PIO (E) | |
| Public Information Cell | **empty page** (`&nbsp;` only) | `N:contact_person` `pio` (PIO / APIO / appellate authority, address, fee) + `N:page_section` | people / sections | T5 (officers) + T2 (address, fee, procedure); empty ⇒ today's empty page | PIO (E) | needs content from you |

### 4.9 Gallery – area `gallery`

| Page | Hard-coded today | Source | Screen | How I'd address it | Who can update | Notes |
|---|---|---|---|---|---|---|
| Photo Gallery | 13 file names + captions in `GalleryController.PHOTOS` (`/wp-content/uploads/2018/imgN.JPG`); **img11 and img13 are missing** on disk | `N:site_image` `photo_gallery` (`static_path` seed) | images | T6: seed 13 rows from `PHOTOS`; the controller reads the album instead of the constant; albums per event possible later | ClubInCharge, HOD, TPO, Staff (A) | broken images today (§7) |
| Media Gallery | dynamic (`gallery_image`, 0 rows) | `T:gallery_image` | existing `/auth/uploadfile/uploadgalleryimage` | T8 now; merge into `site_image` albums later (D7) with both URLs kept | ClubInCharge, HOD, TPO, Staff (A) | consider merging both galleries into `site_image` (⏸ D7) |

### 4.10 NBA, Downloads

Already dynamic (`download` with `doc_type` `nba` / `download`). See §6.4.

| Page | How I'd address it | Who can update |
|---|---|---|
| NBA `/nba/nba` | T8: generic document component, fix the delete redirect (B18), widen `/auth/nba/uploadNBA`, `/auth/api/nba/uploadNBA` to `NBA` and (add only) `HOD`; optional grouping by criterion/department later | NBA (E); HOD (A) |
| Downloads `/facilities/downloads` | T8, see §4.5 | Registrar, ExamController (E) |

### 4.11 Footer / legal pages (`/legal/*`)

| Page | Hard-coded today | Source | Screen | How I'd address it | Who can update |
|---|---|---|---|---|---|
| Contact Us `/legal/contactus` | principal's personal e-mail, phone | `N:site_setting` `contact.email`, `contact.phone` | settings | T1 (same keys as §4.5 Contact Us) | – (area `site`) |
| Privacy Policy, Terms and Conditions | 3 / 1 sentences | `N:page_section` `legal.*` | sections | T2 | – (area `legal`) |
| Product and Services | 7 courses × duration × level | `N:department_site` (`duration_years`, `course_level`) | `/auth/content/departments` | table rendered from `department_site` rows (visible, sorted) | **Admin only** |
| Fee Structure | 6 fee heads × 2 categories × 4 years; totals; college address with PIN 854109 | `N:fee_item` `day_scholar` – totals computed (checked: the computed sums equal all 8 printed totals, e.g. 2500+2100+120+10+7400+30 = 12,160.00) | fees | T7 (same table markup, number format `#,##0.00`), address via T1 | Accounts (E) |
| Refund and Cancellation | phone, `principal@` e-mail | `N:site_setting` | settings | T1 for contact; policy text T2 | Accounts (E) for the text; contact: – (area `site`) |

### 4.12 Misc

| Page | Hard-coded today | Source | Screen | How I'd address it | Who can update | Notes |
|---|---|---|---|---|---|---|
| KEC Web Team `/category/kecwebteam` (+ home swiper) | 5+ members: names, photos `/jsp/category/*`, roles, **personal phones/e-mails**, GitHub/portfolio links; profile links to `/department/cse/mta` etc. (broken shortcuts) | `N:contact_person` `web_team` + `N:site_image` `web_team` | people | T5 + T6; profile links stored as `profile_url` (seed = today's, fixed with D8) | **Admin only** | |
| Departments index `/category/department` | 8 department names | `N:department_site` | `/auth/content/departments` | list rendered from `department_site` | **Admin only** | |
| Notices / Tenders | dynamic | `T:noticeboard` | existing upload | T8 (notice types from `notice_type`, §5.3) | Registrar (E); ExamController, TPO, Staff (A) | |
| Login, forgot/update password, error pages | static UI text | keep | – | K | – | application UI, not content |

---

## 5. Cross-cutting

### 5.1 Header (`header.jsp`, 897 lines)
| Item | Source | How I'd address it | Who can update |
|---|---|---|---|
| `<title>`, meta description/og tags ("KATIHAR ENGINEERING COLLEGE, KATIHAR", "KEC Katihar") | `N:site_setting` `site.title`, `site.short_name` | T1 | – (area `site`) |
| canonical / shortlink / og:url | `C:kec.site.canonical-url` | config (deployment value) | deployment |
| favicon, logo `/images/logo.png` | `C:kec.site.favicon`; logo → `N:site_image` `logo` (or setting with path) | config / T6 | deployment / **Admin only** |
| Top bar: 5 links | `N:link_item` `top_bar` | T3 | – (area `menu`) |
| 6 social links (also repeated in every department `about.jsp`) | `N:link_item` `social` | T3, one include used everywhere | – (area `menu`) |
| Main menu: 10 top items, ~60 sub-items, **desktop and mobile copies that differ** (§7); compact top menu (11 links) | `N:link_item` `main_menu` (one tree, `visibility` desktop/mobile where the difference is intended) | T3: one tree rendered twice (desktop markup and mobile markup, each with today's classes); seed reproduces today's differences exactly, so `PagesBaselineTest` stays green; differences fixed only with B2 approval | – (area `menu`) |
| Dashboard/Login/Logout entries | keep (depend on login state; already conditional) | K; "Dashboard" target per role only with B22 approval | – |

### 5.2 Footer (`footer.jsp`)
| Item | Source | How I'd address it | Who can update |
|---|---|---|---|
| Logo, "KEC Katihar", "Katihar Engineering College, Katihar" | `N:site_setting` | T1 | – (area `site`) |
| 6 legal links | `N:link_item` `footer` | T3 | – (area `menu`) |
| "Since January, 2019", "© 2018 KEC Katihar", "Designed & Developed by KEC Web Team" | `N:site_setting` `footer.*` | T1 (copyright year stays a setting; it is not auto-updated, so the page stays identical) | – (area `site`) |

### 5.3 Dropdowns in admin forms
| Form | Values today | Source | How I'd address it | Who can update |
|---|---|---|---|---|
| Department (`addfaculty.jsp`, `admin/HodAddFaculty.jsp`, `user/HodAddFaculty.jsp`, `addPlacement.jsp`, `addPlacements.jsp`, `addtpocoordinator.jsp`, `admin/uploadLabDoc.jsp`) | ids 1–6 or 1–8; 7 = "Mechanical and Manufacturing", 8 = "Manufacturing" – but `department` row 7 is **MSM** and there is no row 8; no Food Processing | `T:department` (+ `N:department_site` for lab ids) – ⏸ D2 | `<option>`s rendered from the table; the submitted parameter name and value type stay the same | **Admin only** (department data) |
| Branch (`coursecreator.jsp`) – stores the **name** in `no_of_seat.branch_name` | 6 names | `N:department_site.full_name` | options from `department_site`, value = the same name string as today | **Admin only** |
| Notice type (`uploadfile.jsp` 3, `HODFileUpload.jsp` 11, `uploadNoticeFaculty.jsp` / `uploadfileByFaculty.jsp` 9) | inconsistent case (`CivilNotice` / `CIVILNotice`, `MechNotice` / `MECHNotice`) – it only works because the column collation is case-insensitive; no FP option although `NoticeBoardServiceImpl` queries `FPNotice` | `N:notice_type` | each form lists only the types the user may post (`for_*` flags + `content_area_role`); stored codes unchanged | **Admin only** (the list of types) |
| SMS group (`sms.jsp`) + phone lists in `SMS.properties` (**personal numbers committed to git**) | 5 groups | `N:sms_group` / `N:sms_group_member` (⏸ D11) | Admin screen for groups/members; `MessageSender` reads the DB | **Admin only** |
| Role (`registration.jsp`), department (`hodincharge.jsp`) | already from DB | – | new roles appear automatically; `/auth/roles` added for assigning extra roles | **Admin only** |

### 5.4 Java
| Where | Literal | Source |
|---|---|---|
| `DepartmentController` | `ALL_DEPTS`, `MECH_DEPTS`, `PAGES`, `FACULTY_USERNAMES` (75 entries), notice switch | `N:department_site` (+ `T:faculty.username`) |
| `NoticeBoardServiceImpl`, `NoticeBoardHelper`, `UserController` pagination | notice-type strings, dept code → view mapping | `N:notice_type` + `N:department_site` |
| `FacultyListView.BRANCH_NAMES` | 7 names (with leading spaces) | `N:department_site` |
| `GalleryController.PHOTOS` | 13 photos | `N:site_image` |
| `DocUtils` | "The Principal / KEC, Katihar" | `C:kec.college.short-name` |
| `LeaveMailService` | location, `-07:00` | `C:kec.calendar.*` |
| `AttendanceReportService` | mail subject | `C:kec.mail.attendance-subject` |
| `KECUrlAuthenticationSuccessHandler` | landing URL per role | keep (security routing); one new branch: only-new-roles → `/manage` (§1.3.4) |
| `WebSecurityConfig` | role names per path | keep in code; new `/manage/**` rule and path-specific rules for T8 modules (§1.3.4) |
| `MessageSender` | `KECWebTeam` group name | `N:sms_group` |

### 5.5 Images
205 hard-coded `/wp-content`, `/sites`, `/images` paths in JSPs. Theme images (backgrounds, icons, logo) stay as files;
**content** images (galleries, people photos, facility photos) move to `site_image` (with `static_path` for the
seed). Faculty photos under `jsp/gallery/facultypic/*.jpg` and `jsp/category/*.jpg` are replaced by the faculty
photo BLOBs (`/{id}/image`) where a faculty record exists.

---

## 6. Already-dynamic modules – code-standard review (what needs to be addressed)

### 6.1 About KEC → Support Staff (`/about/staff`) – verified
Works: lists `staff` rows (2 locally) with photo, name, phone, official e-mail; Admin sees "Delete Staff".

Issues:
1. **"Add Staff" button never shows.** `AboutController.getStaff` sets `userType` to 0 or 1, the JSP checks
   `userType == 2`. The link is also relative and mis-cased (`auth/uploadfile/addStaff` → resolves to
   `/about/auth/uploadfile/addStaff`; the endpoint is `/auth/uploadfile/addstaff`).
2. **No edit.** Only add and delete; a typo means delete + re-add (new photo upload).
3. **Delete is a GET** (`/about/deleteStaff?staffId=`), CSRF is disabled → a link/image can delete staff for a
   logged-in Admin. Role check is manual in the controller, not in `WebSecurityConfig`; non-admins are silently redirected.
4. `deleteStaff` calls `staffService.getStaffById` and ignores the result (an unknown id throws → blank HTTP 200 via
   the global handler).
5. Photos are inlined as **base64 data URLs** in the HTML: page weight grows with every staff member and the
   browser can't cache them. Faculty photos already have a streaming endpoint (`/{id}/image`); staff should get
   `/staff/{id}/image` the same way.
6. `staff.mobile_no` is `BIGINT` → leading `0`/`+91` are lost; no validation on the form (`facultyMobNo` must parse
   as `Long` or the request fails with 400).
7. Add-staff form reuses faculty parameter names (`facultyName`, `facultyMobNo`, `slideImage`) – confusing, but URL
   and parameter names must stay (CLAUDE.md), so only document it.
8. `staff.username` is never filled; personal e-mail is stored but not used.
9. No ordering, designation or department → department "Staff of …" pages can't use it (A1).
10. Magic numbers for `userType` (0/1/2) – the same pattern exists in `facultylist.jsp` and `placedstudent.jsp`.
11. Sidebar markup is copy-pasted (see §4.2).

How I'd address it: Add button via `<kec:canEdit area="office">` with the absolute URL (1); edit screen
`/manage/staff/{id}` keeping the add form's parameter names (2); delete as POST from a small form, permission in
`WebSecurityConfig`/service, unknown id → "not found" message (3, 4); `/staff/{id}/image` (5); phone validation now,
`VARCHAR` only with approval (6); link `staff.username` to the login so a `Staff` user can edit their own photo/phone (8);
A1 for order/designation/department (9); replace `userType` numbers by permission checks (10); sidebar T3 (11).
**Who:** Registrar (E), Staff (own record), Admin/SubAdmin.

### 6.2 Administration (`administration` table)
Upload POST `/api/uploadAdministration` is **anonymous** (not under `/auth`); delete is a GET that redirects to `/`;
no edit; data never displayed publicly (§4.2); 16 of 26 rows have an empty role; no ordering column (order would come
from insertion id).

How I'd address it: protect `/api/uploadAdministration` with the `office` permission (URL kept), POST delete with a
redirect back to the screen, edit + reorder (order by a `sort_order` – needs an ALTER, or by role rank in code),
show it on `/about/administration` (D3). **Who:** Registrar, Principal (E).

### 6.3 Placements / TPO coordinators
Both list pages print the numeric department id; deletes are GETs; no edit; the add forms' department dropdowns are
hard-coded (§5.3); `/auth/addPlacement` and `/auth/uploadfile/addPlacements` are two pages for the same thing;
`TrainingPlacementController` maps `studentplacementcoordinator` twice (GET-specific + any-method fallback).

How I'd address it: department name from `department_site`; edit screens; POST deletes; both add pages kept (URLs)
but rendering the same form; seed the 83 static placed-student rows (§4.6). **Who:** TPO (E).

### 6.4 Document lists (holidays, downloads, NBA, AICTE, responsibility docs, lab docs)
Six near-identical tables/entities/services/controllers/JSPs (`download`, `holiday`, `responsibility_doc`,
`lab_document`, `aicte_documents` with a different shape). Common issues: GET deletes; `Content-Type:
application/<ext>` from the stored extension (images get `application/jpg`); `content-disposition` with an unquoted
user file name; NBA delete links go to `/facilities/deletedocument/{id}` which redirects to Downloads; lab-doc delete
redirects to Holidays; the "Year" column header shows the file name. The new `D:<doc_type>` pages should reuse one
generic list/stream/delete component instead of adding a seventh copy.

How I'd address it: one `DocumentListView` + `common/documentlist.jsp` + one streaming helper (whitelisted content
types, quoted file name) used by all six tables (the tables stay; only the code is shared); delete as POST and
redirect back to the page it came from (B18). **Who:** holidays/responsibility docs → Registrar; downloads →
Registrar, ExamController; NBA → NBA (E), HOD (A); AICTE → Registrar, Principal; lab docs → HOD (own dept), as today.

### 6.5 Course seats (`no_of_seat`)
Table empty locally; Delete link broken (`/auth/courses/` vs `/auth/course/`); delete is a GET; branch stored as free
text from a hard-coded dropdown.

How I'd address it: fix the link (B4), POST delete, branch options from `department_site` (same stored text).
**Who:** Registrar, Principal (E).

### 6.6 Slides / achievements / media gallery / notices
Dynamic and working. Captions are not editable after upload; no reordering (`flag`/`display_flag` only toggle
visibility); delete via GET.

How I'd address it: caption edit, up/down ordering (order by upload id within the visible ones, or `sort_order` with
an ALTER), POST deletes. **Who:** slides/achievements → SubAdmin (area `home`); media gallery → see §4.9; notices →
§1.3.3 `notices` + department notices as today.

### 6.7 Faculty list (`/faculty?deptno=`)
"Designation: Assistant Professor" printed for everybody; HOD found by **name string** match against
`department.dept_hod`; a faculty member whose name contains "HOD" is hidden; `/faculty?deptno=6|7` fail (no faculty →
`FacultyListView` throws, truncated page – known baseline).

How I'd address it: designation via key prop (A2 alternative), HOD from `department_site.hod_username` → faculty,
empty department ⇒ "No faculty" message instead of an exception (behaviour change, approval). **Who:** Faculty (own
profile), HOD (own dept: order, designation), Admin.

---

## 7. Bugs found during the audit (pre-existing; fixing them changes behaviour → each needs your OK)

| # | Where | Bug |
|---|---|---|
| B1 | header menu | "Computer Center Labs" → `/academic/lab?id=1` → 400 (needs `dept_id`). Missing from the mobile menu |
| B2 | header menu | desktop label of `/department/ece/about` duplicates the ECE-VLSI label; mobile menu lacks 3 departments; mobile "About Placement" → brochure |
| B3 | academic sidebar | link `/academic/ attendance` (space) |
| B4 | `academic/admission.jsp` | course Delete link `/auth/courses/{id}` (endpoint is `/auth/course/{id}`) |
| B5 | `/academic/regulations` | `regulation.pdf` missing from the repo |
| B6 | `/about/administration` | `administration` data never shown; page shows (empty) responsibility docs |
| B7 | `/about/staff` | Add Staff button never visible; link wrong (§6.1) |
| B8 | `/approval/mandatorydisclosure` | shows the AKU regulation PDF |
| B9 | `/trainingplacement/rulesoftrainingplacement` | shows the resume sample PDF |
| B10 | `/facilities/startupcell` | download links `https://#/…` |
| B11 | `/facilities/contactus` | form calls undefined `doContact()` – nothing is sent |
| B12 | `/gallery/photogallery` | `img11.JPG`, `img13.JPG` missing |
| B13 | department sidebars | ece/ecevlsi faculty link → EEE (`deptno=4`); fp → MSM (`deptno=7`) and ASH labs (`dept_id=5`); MSM → mechanical faculty |
| B14 | dropdowns | department ids 7/8 don't match the `department` table; no Food Processing |
| B15 | notice types | mixed case only works thanks to `latin1_swedish_ci`; HOD/faculty forms can't post FP notices |
| B16 | address | PIN 854106 (contact page) vs 854109 (fee page) |
| B17 | `LeaveMailService` | calendar events at `-07:00` instead of IST (already listed in the upgrade notes) |
| B18 | NBA page | delete redirects to Downloads; lab-doc delete redirects to Holidays |
| B19 | `ash/about.jsp`, `civil/attendance.jsp` | share links say "About Civil Engg." with a broken URL |
| B20 | `/category/kecwebteam` | profile links go to broken per-faculty shortcuts |
| B21 | `/user/uploadDownload`, `/user/uploadHoliday`, `/user/uploadAICTEapproval`, `/user/uploadResponsibilityDoc` | Faculty can open these form pages, but the scripts (`js/uploadDownload.js`, …) post to `/auth/api/*`, which is Admin-only → the upload fails for Faculty. Either remove the pages from the Faculty side or give the right roles access (§1.3.3) |
| B22 | `header.jsp` (3 places) | "Dashboard" link is `/auth/uploadDashboard` for every logged-in user → HOD/Faculty get access denied; should go to the user's own landing page |

---

## 8. ⏸ Decisions needed

| # | Question | Recommendation |
|---|---|---|
| D1 | Approve the new tables of §2 (01–12)? | yes; 08/09 can wait for their module; 10–12 are needed as soon as anyone other than Admin edits |
| D2 | Department ids: `department` has 7 rows (no Manufacturing, no Food Processing, ECE row without HOD, row 7 = MSM) while forms use 1–8 with other meanings. Add rows (e.g. 8 Manufacturing, 9 Food Processing) and fix the dropdowns/sidebars to the table? | yes – data change only, but it changes which faculty appear on ece/fp/MSM pages (B13) |
| D3 | `/about/administration`: show the `administration` table (after cleaning 16 empty roles) above the responsibility docs, and retire the static `/academic/adminsValue`? | yes |
| D4 | Computer Center Labs URL: new `/academic/computercentre` (menu fixed) **or** make `/academic/lab` accept `id` **or** point the menu to `/facilities/csecenter`? And please send the text and photos | new page `/academic/computercentre`, same content shown on `/facilities/csecenter` |
| D5 | Per-department documents (question bank, weekly test, course file): `doc_type` codes like `qb_cse` (no DDL) or A3 + a site column? | `doc_type` codes |
| D6 | Seed PDFs/images that exist as static files: import bytes into BLOBs, or keep the files and store their path? | keep files (`static_path` / link) – pages stay identical, no large SQL dumps (CLAUDE.md forbids committing `.sql` dumps; seed SQL contains only text) |
| D7 | Merge photo gallery and media gallery into `site_image` albums? | yes, keep both URLs |
| D8 | Per-faculty shortcut URLs (75, broken today): make them show the faculty profile? | yes (behaviour change, approval) |
| D9 | Correct PIN: 854106 or 854109? | your input |
| D10 | Fix B8/B9 (wrong PDFs) when the real documents are uploaded? | yes |
| D11 | Move SMS phone lists from `SMS.properties` (in git) to the DB? | yes, and purge the numbers from the repo later |
| D12 | Optional ALTERs A1 (staff), A2 (faculty), A3 (download), A4 (download uploader) | A1 yes; A2 via key props (no DDL); A3 only if links-as-documents are wanted; A4 no (use the change log) |
| D13 | HTML editing: allow editors to edit page HTML (sanitised)? Or plain text + fixed layout? | sanitised HTML – the only way to keep today's markup byte-identical |
| D14 | Which bugs of §7 to fix in this phase? | B1, B3, B4, B7, B11, B13, B18, B21, B22 |
| D15 | Approve the new roles of §1.3.2 (`SubAdmin`, `Registrar`, `ExamController`, `TPO`, `NBA`, `Accounts`, `Librarian`, `Warden`, `PIO`, `ClubInCharge`, `Staff`) and their names (CamelCase like `Admin`/`HOD`, or your spelling with underscores, e.g. `Exam_Controller`)? | yes, CamelCase |
| D16 | `Principal` as its own role, or does the principal's account stay a full `Admin`? | own role, if the principal should not manage users/roles |
| D17 | Approve the permission matrix of §1.3.3 (in particular: Faculty may add department documents; HOD may add NBA documents; Staff may post college notices)? | yes; Admin can change it later in `/auth/roles/areas` |
| D18 | New notice types `ExamNotice` and `PlacementNotice` (shown in the home notice list like `Notice`)? | yes, but only after `notice_type` (commit 17) |
| D19 | Editor URL prefix `/manage/**` for non-Admin editors (instead of `/auth/content/**`, which would require changing the Admin-only `/auth/**` rule)? | `/manage/**` |
| D20 | Change approval: should edits by some roles (e.g. Faculty uploads) go live only after HOD/Admin approval (draft → publish)? | no for now – change log + restore is enough; can be added later per area |
| D21 | Who assigns roles: only Admin, or may HOD assign department-scoped roles (e.g. a faculty member as department web editor)? | only Admin |

---

## 9. Proposed commit plan (after approval; one page/module per commit, each with tests)

Test approach per commit: `PagesBaselineTest` must stay green **unchanged** (the seed reproduces today's content, and
the tag fallback reproduces it when the tables are empty); `HtmlSnapshotTest` compare must be byte-identical for every
page touched (or differ only where an approved bug fix applies); new tests per edit screen (permitted role can
create/edit/delete, other roles and anonymous get 403/redirect, department scope, add-only users cannot delete others'
rows, POST-only, HTML sanitised, each upload appends a row, change-log row written).

1. ✅ Migration framework: `docs/sql/dynamic/` DDL + seed files, a test that the seed reproduces the baseline; entities/repositories for the approved tables.
2. ✅ **Roles and permissions**: role seed (D15/D16), `content_area`/`content_area_role` + seed (§1.3.3), `ContentPermissionService`, `<kec:canEdit>`, `/manage/**` rule, `/manage` dashboard, landing page for new-only roles, `/auth/roles` assignment screen, `content_change_log`; role-matrix tests (Admin/HOD/Faculty unchanged).
3. ✅ `site_setting` + `<kec:setting>` tag (instead of a model advice: works in every JSP, with fallback) + settings screen → header/footer/contact/legal/refund/grievance pages.
4. ✅ `link_item` + links screen → main menu (desktop + mobile), top bar, social, footer.
5. ✅ Page sidebars (about, academic, facilities, T&P, approval, RTI) → `link_item`.
6. ✅ `page_section` + sections screen (sanitised editor, preview, history/restore) → About pages (history, vision, principal, affiliation).
7. Allotment & Surrender Report (`D:allotment`, `document_type`, append-only uploads, generic document list component).
8. Other document pages: regulations, AKU, mandatory disclosure, RTI ×3, T&P brochure/rules/tips, payment tutorial, academic placeholders.
9. Academic text pages (admission, payment steps, attendance, …).
10. `site_image` + images screen → photo gallery, home gallery, library, AICTE banner.
11. Computer Center Labs page (after D4 and your content).
12. `contact_person` + people screen → library, clubs, sports, hostels (wardens), web team, principals list, PIO.
13. `fee_item` + fees screen → fee structure, hostel charges.
14. `department_site` + departments screen → department menu, index, Product & Services, `DepartmentController`, sidebars, HOD checks (`<kec:canEdit>`), `FacultyListView`.
15. Department content per site (about/vision/labs/…): 10 commits, one per site.
16. Existing modules opened to the new roles (T8): placements/TPO coordinators (TPO), holidays/responsibility docs/administration/staff/seats (Registrar), downloads (Registrar, ExamController), NBA (NBA, HOD), AICTE (Registrar, Principal), media gallery; placements seed (83 rows); edit screens; POST deletes.
17. Administration page (D3) and support-staff fixes (§6.1, after approval).
18. `notice_type` → upload forms + `NoticeBoardServiceImpl` (+ `ExamNotice`/`PlacementNotice` if D18); `sms_group` → `sms.jsp`/`MessageSender`.
19. Java/config literals (§3).
20. Docs: `UPGRADE_NOTES.md` (new tables, roles, permission matrix, config, screens), `PROJECT_ANALYSIS.md` §6 marked done, CLAUDE.md test notes.

Store management stays untouched throughout (`ProductTransformer`, `jsp/store/**`, `/common/store/**`, roles `SM`/`SK`).

---

## 10. Page blocks: headings, subheadings, text, images and documents on every page (approved 2026-10-19)

Requested after step 6: editors can add their own content to any page. Approved: new tables `page_block` and
`content_file`, all pages including the department sites. Details and SQL: `UPGRADE_NOTES.md` §10.6,
`docs/sql/dynamic/README.md`.

| Block | Shown as | Input |
|---|---|---|
| Heading / Subheading | `<h2>` / `<h3>` in the site style | text (escaped) |
| Text | paragraph(s) with lists, links, bold/italic | visual editor or HTML, sanitised (OWASP) |
| Image | figure with optional caption, small / medium / full width | JPG, PNG, GIF, WebP (checked by content), max 20 MB |
| Document | PDF inside the page + download link, or link only; Office files as download link | PDF, .doc(x), .xls(x), .ppt(x) (checked by content), max 20 MB |

The blocks appear below the page's existing content (full width, above the footer). Who may add them follows §1.3.3
via the page's content area (`PageKeys.area`); add-only users change only their own blocks. Pages without blocks are
byte-identical for visitors.

---

## 11. Existing page texts editable, page by page (approved 2026-10-19)

The hard-coded texts of every page become `page_section` rows (treatment T2, same as the About pages in step 6), so
the "Edit this page" screen can change them. Static parts are wrapped in `<kec:section key title>` with today's HTML as
fallback; the seed (`15_page_section_pages_seed.sql`) is generated from those tags (`tools/page_sections_seed.py`), so
pages stay byte-identical. Database lists, forms and scripts stay in code. No schema change.

| Group | Pages | Status |
|---|---|---|
| 1 | Academic (12 pages, 16 texts) | ✅ done (holidays, labs = document lists; `adminsValue` = D3) |
| 2 | Facilities (14 pages, 19 texts) | ✅ done (downloads, responsibility documents = document lists; contact us = form + D9; holiday.jsp not mapped) |
| 3 | T&P, Approval, RTI (10 pages, 11 texts) | ✅ done (NBA, AICTE approval, placed students, coordinators = database lists; grievance = form) |
| 4 | Legal pages (5 pages, 5 texts) | ✅ done (footer = settings since step 3; contact us = form; fee and course tables as HTML until steps 12–13) |
| 5 | Home page (static parts, 6 texts) | ✅ done (slider, notices, achievements, web team = database; marquee and Important Links as HTML instead of `link_item`) |
| 6 | Departments, one site per commit | in progress: CSE (9 texts) ✅, ASH (8 texts) ✅, Civil (9 texts) ✅, ECE (13 texts) ✅, ECE-VLSI (13 texts) ✅, EEE (10 texts) ✅, FP (9 texts) ✅, Manufacturing (9 texts) ✅ |

Tables of people and fees become editable as HTML first; steps 12–13 (people, fee screens) replace them later.
