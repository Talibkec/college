# Upgrade notes – branch `kec_claude`

Upgrade of the college website/ERP from Spring Boot 1.3.3 / Java 8 to Spring Boot 4.1.1 / Java 25 (LTS).
Sections 1-8: the upgrade (only Java, Spring and libraries). Section 9: the refactoring phase that followed
(code structure only; no "making pages dynamic", no schema change). Analysis and decisions:
`docs/PROJECT_ANALYSIS.md`.

## 1. Final versions

| Component | Before | After |
|---|---|---|
| Java | 8 | **25** (LTS; JDK 25.0.4.1) |
| Spring Boot | 1.3.3.RELEASE | **4.1.1** |
| Spring Framework | 4.2.5 | 7.0.9 |
| Spring Security | 4.0.3 (taglibs 5.2.2) | 7.1.1 |
| Spring Data JPA | 1.9 | 4.1.1 |
| Hibernate | 4.3.11 | 7.4.5 |
| Servlet container | Tomcat 8.0.3 (forced) | Tomcat 11.0.24 (Jakarta Servlet 6.1) |
| JSTL | javax.servlet:jstl 1.2 | Jakarta Tags 3.0 (glassfish impl) |
| JSON (Spring MVC) | Jackson 2.6 | Jackson 3.1.5 (Boot default; Jackson 2.21 still pulled in by Google libraries); Gson Boot-managed |
| MySQL driver | mysql-connector-java 8.0.33 | com.mysql:mysql-connector-j 9.7.0 |
| Connection pool | tomcat-jdbc | HikariCP |
| Multipart | commons-fileupload 1.3.3 | servlet multipart (20 MB file / 25 MB request) |
| firebase-admin | 6.11.0 | 9.11.0 |
| google-api-client / Calendar | 1.23.0 / v3-rev305-1.23.0 | 2.9.1 / v3-rev20260925-2.0.0 (Gson JSON factory) |
| google-http-client(-jackson, Jackson 1) | 1.19.0 | removed; google-http-client 2.2.0 (BOM) |
| modelmapper | 2.3.0 | 3.2.7 |
| mysql-backup4j | 1.0.1 | 1.3.0 |
| commons-io | transitive | 2.22.0 (explicit, used directly) |
| PDF library | iText 5.5.6, AGPL (+ unused iText 7.0.2) | **OpenPDF 3.0.5** (LGPL/MPL) – approved |
| jQuery webjar | 2.2.4 (unused) | removed – approved |

## 2. Commits (oldest first)

| Commit | What |
|---|---|
| `aacf571` | Fix start-class, add spring-boot-maven-plugin |
| `fd8eb97` | DB/mail credentials moved to env vars / untracked `application-local.properties` |
| `baf779d` | CLAUDE.md (rules) |
| `df21ba5` | Phase 1 analysis |
| `b5bb85c` | Record approved Phase 1 decisions |
| `b6db4b8` | Phase 2: upgrade safety-net tests on the original version |
| `17ce977` | `ddl-auto=update` → `none` (approved) |
| `c6742b0` | 3a: Boot 1.5.22 (Tomcat 8.5, Hibernate 5.0) |
| `5585f34` | 3b: Boot 2.7.18 (Spring 5.3, Security 5.7, Hibernate 5.6) |
| `a9193bb` | 3c: Boot 3.5.16, Java 21, javax → jakarta |
| `5abb5bc` | 3d: Boot 4.1.1 (Spring 7, Security 7, Hibernate 7, Tomcat 11) |
| `5da417d` | 3e: third-party libraries |
| `a3bebf1` | Upgrade notes |
| `405f1c9` | Java 21 → 25 |
| `fed5427` | iText 5 (AGPL) → OpenPDF 3.0.5 (approved) |
| `d382434` | Remove unused jQuery webjar (approved) |
| `27d2817` | Notes updated |
| `5f4caf5` | Embed the site's own PDFs instead of the Google viewer on keck.ac.in (approved) |
| (latest) | Production deployment guide |

Pushed to `origin/kec_claude` up to `5f4caf5` (at your request). Not merged into `master`; no pull request.

## 3. Changes that matter (and how the old behaviour was kept)

Each one was found or confirmed by the tests (§5).

**Configuration / deployment**
* **Credentials** are no longer in `application.properties`. Production needs `DB_URL`, `DB_USERNAME`,
  `DB_PASSWORD`, `MAIL_USERNAME`, `MAIL_PASSWORD` (or its own `application-local.properties` + `local` profile).
* **`spring.jpa.hibernate.ddl-auto=none`**: Hibernate no longer tries to alter the schema at startup.
* **Java 25 is required to run the app** (the WAR is compiled for Java 25). If production deploys the WAR into an external Tomcat, that Tomcat
  must be **11.x** (Jakarta EE 11). Tomcat 8/9 can no longer run this WAR. `java -jar` on the WAR (embedded
  Tomcat) works as before.
* **Upload limit**: before there was no limit; now 20 MB per file and 25 MB per request (approved).
* **Connection pool**: HikariCP instead of tomcat-jdbc. The old `testWhileIdle/validationQuery` keys were removed;
  Hikari validates connections itself.

**Database / JPA**
* Entity IDs: `GenerationType.AUTO` (with undefined `generator=` names) → `GenerationType.IDENTITY`. This is
  what Hibernate 4/5 actually did on MySQL (`AUTO_INCREMENT`), so IDs are generated exactly as before.
* Stray `)` removed from 3 JPQL queries (`FacultyRepository`, `StaffRepository`). Hibernate 4 ignored it,
  Hibernate 5.6+ rejects it at startup. The queries are otherwise identical.
* Spring Data renames: `findOne(id)` → `findById(id).orElse(null)`, `delete(id)` → `deleteById(id)`,
  `new PageRequest` → `PageRequest.of` (same semantics).
* Naming of tables/columns is unchanged (Boot's default physical naming = the old Spring naming strategy).

**Web / JSP**
* JSP view paths: many controllers return `"/x.jsp"` on top of the `/jsp/` prefix. Tomcat 8.5+ answers 404 for
  `/jsp//x.jsp`, so the view resolver collapses `//` (`BeanConfig`). Without this, ~40 pages would return 404.
* JSTL taglib URIs `http://java.sun.com/jsp/jstl/*` → `jakarta.tags.*` in all JSPs.
* Trailing slash (`/about/history/`) still works (`UrlHandlerFilter` in `WebConfig`; Spring 6 dropped it).
* Error responses as before: unknown URLs show `error-404.jsp` (Spring 6.1+ would answer JSON), and standard MVC
  errors (e.g. missing parameter → 400) have no JSON body (`CustomExceptionHandler`). Verified against the
  original version.
* Message converters: Spring 7's builder API is used; the image/PDF byte-array converter is appended to the
  defaults as before (the old override would have caused 406 on all String/JSON responses).
* Missing Boot 4 modules added: `spring-boot-gson` (Gson bean), `spring-boot-cache` (`@EnableCaching`).

**Security**
* `WebSecurityConfigurerAdapter` → `SecurityFilterChain` with the **same rules in the same order**. Unmatched
  URLs stay public (`anyRequest().permitAll()`, implicit before). JSP forwards/includes and error dispatches are
  not authorized, same as before.
* `requireExplicitSave(false)`: the password-reset link and the registration auto-login set the SecurityContext by
  hand and rely on it being stored in the session (old behaviour). The full reset flow is tested.
* `BCryptPasswordEncoder` bean made `static` (breaks a bean cycle Boot 2.6+ rejects); `AuthenticationManager` bean
  exposed (Boot 2+ no longer does it). Existing `$2a$` bcrypt hashes work unchanged.
* Spring Security's removed `codec.Base64` → `java.util.Base64` (identical output for the faculty/staff photo
  data URLs).

**Integrations**
* PDFs (attendance report, leave application): iText 5 → **OpenPDF 3.0.5**. Only the package changed
  (`com.itextpdf.text` → `org.openpdf.text`); same API calls. PDF content test green; logo embedding from an
  absolute path checked separately.
* **Embedded PDFs (approved, found in the manual check):** 13 pages showed PDFs through
  `drive.google.com/viewerng/viewer?url=http://keck.ac.in/...`, so they always showed (and linked to) the
  production file and could not work locally. They now embed the same file by relative path
  (`<object data="/approval/holiday/${document.id}">`, `/jsp/approval/aku.pdf`, ...), shown by the browser's PDF
  viewer. URLs and parameters unchanged. `rtiact.jsp`'s fallback `<embed>` had a typo (`keck.ac.i`, wrong file) and
  now points to the same file as its `<object>`. Spring Security's `X-Frame-Options` changed from `DENY` to
  `SAMEORIGIN` so the site may embed its own files (other sites still cannot frame it).
  `/jsp/academic/regulation.pdf` (regulations page) is not in the repository – it 404s locally; on the server it
  works only if the file was copied there by hand. Canonical/`og:url` meta tags still name `http://www.keck.ac.in`
  (correct for production). 5 `facultydocuments` and 2 `noticeboard` rows contain absolute keck.ac.in links (data).
* Calendar client: `JacksonFactory` → `GsonFactory`; `GoogleCredential` with the P12 key is kept (deprecated but
  supported). Firebase initialisation unchanged.

## 4. Store management

* **Nothing was disabled.** No `TODO(store-management)` markers exist.
* Compile-only mechanical edits (approved decision 1), no logic changes:
  * `jakarta.*` imports: `controller/store/CommonResourceController`, `ProductController`, `StoreFacultyController`,
    `StoreKeeperController`
  * `java.util.Base64`: `CommonResourceController` (core endpoint `/faculty`)
  * `IDENTITY` IDs: entities `Order`, `Product`, `Purchase`, `Request`
  * Spring Data renames: `ProductServiceImpl`, `PurchaseServiceImpl`, `RequestServiceImpl`
  * taglib URIs: 15 JSPs under `jsp/store/`
* Store pages are covered by the page baseline only (SM/SK dashboards, product/order pages, faculty order pages).
  Store workflows (purchases, requests) are **not** covered by automated tests; see the checklist in the analysis.

## 5. Tests

`src/test/java/com/college/upgradetest` – 37 tests that start the real app against the local MySQL copy (see
CLAUDE.md "Tests"). They were written and the baselines recorded on the **original** version, then kept
unchanged through all stages (only the Boot-specific wiring in `AbstractAppTest` moved).
Result on Boot 4.1.1 / Java 25: **37 run, 0 failures, 1 skipped** (`leaveRequestEndpointMailsPdf`, see §6.6).
After the refactoring (§9): **58 run, 0 failures, 1 skipped** (the opt-in `HtmlSnapshotTest`); the /rl test now runs.

## 6. Open issues / decisions for you

1. **Done (approved):** Java 25, OpenPDF instead of iText, jQuery webjar removed.
2. **modelmapper 3.2.7** prints a JDK 25 warning (`sun.misc.Unsafe::staticFieldOffset will be removed`). It works
   today; watch for a modelmapper release that drops `Unsafe` before a future JDK removes it.
3. **Maven 3.6.3** (system package) also prints `sun.misc.Unsafe` warnings on JDK 25 (its own Guava). Harmless;
   a newer Maven (3.9.x) or the Maven wrapper would remove them.
4. **Junk rows in the local DB** – `/approval/addFaculty` is an **anonymous GET that inserts a blank faculty row**
   (+ "Administrative Responsibilities" key properties) on every call. Early test runs called it; the 8 rows
   (faculty_id 78–102) they left in the local `keckacin_college2` are gone (checked 2026-10-16: no blank faculty
   rows left). If it happens again, delete them with:
   ```sql
   DELETE v FROM faculty_keyprop_values v JOIN faculty_key_props p ON p.key_property_id=v.key_property_id
     JOIN faculty f ON f.faculty_id=p.faculty_id
     WHERE f.faculty_id>=78 AND f.f_name IS NULL AND f.username IS NULL AND f.dept_id IS NULL;
   DELETE p FROM faculty_key_props p JOIN faculty f ON f.faculty_id=p.faculty_id
     WHERE f.faculty_id>=78 AND f.f_name IS NULL AND f.username IS NULL AND f.dept_id IS NULL;
   DELETE FROM faculty WHERE faculty_id>=78 AND f_name IS NULL AND username IS NULL AND dept_id IS NULL;
   ```
   The endpoint itself is a security problem in production (anyone can create rows); it is still there because
   removing it changes behaviour – decide in the security follow-up.
5. **Not covered automatically:** `/rn` (live Firebase query + report mail) and `/rs` (live Google Calendar insert;
   `CalendarService` calls `System.exit(1)` on failure). Test them manually (checklist in the analysis).
6. **Logo path**: the attendance-report and leave PDFs read the logo from `kec.paths.pdf-logo`
   (default `/sites/default/files/compressed/logo.png`, absolute path). On servers without it these PDFs fail
   (already the case before the upgrade). The tests point the property at the repository's logo (§9).
7. **Deprecated but working** (clean up in a later phase): `GoogleCredential` (P12), `new FirebaseOptions.Builder()`,
   `@Temporal` on 7 date fields (Hibernate 7 logs a deprecation), `requireExplicitSave(false)`, JUnit 4 tests run via
   the vintage engine.
8. **Security items from the analysis are unchanged** (anonymous `/rn`, `/rl`, `/rs`, `/api/uploadAdministration`,
   `/approval/addFaculty`; CSRF disabled; new faculty get password = username). **Rotate the secrets still in git
   history** (DB, SMTP, Firebase/Google JSON + P12, Textlocal API key, `KECDBBackup` passwords).
9. **Pre-existing broken pages** (~90 URLs: missing JSPs, per-faculty shortcuts, 4 pages with a missing sidebar
   include, `/faculty?deptno=7`) are unchanged and annotated in
   `src/test/resources/upgradetest/anonymous-pages.txt`.

## 7. Build and run (WSL Ubuntu 22.04)

```bash
cd ~/college            # or the project path
git checkout kec_claude
export JAVA_HOME=/usr/lib/jvm/java-25-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# build + all tests (needs local MySQL and ./application-local.properties)
mvn clean package

# run with the local profile (application-local.properties), http://localhost:8080
mvn spring-boot:run -Dspring-boot.run.profiles=local

# same on another port (e.g. while IntelliJ uses 8080)
mvn spring-boot:run -Dspring-boot.run.profiles=local "-Dspring-boot.run.arguments=--server.port=8099"

# or run the built WAR
java -jar target/spring-boot-example-1.0-SNAPSHOT.war --spring.profiles.active=local
```
IntelliJ: set the project SDK to JDK 25 and add `local` to the run configuration's active profiles.

## 8. Production deployment

Step-by-step guide for the Ubuntu VPS (Java 25, Tomcat 11.0.x ≥ 11.0.24, MySQL 8.0, nginx, `setenv.sh`,
systemd, deploy/rollback commands): **`docs/PRODUCTION_DEPLOYMENT.md`**.

## 9. Refactoring (after the upgrade)

Approved plan (2026-10-16): refactor the non-standard/risky code of `PROJECT_ANALYSIS.md` §7, **excluding store
management**, one area per commit, behaviour/URLs/parameters/form fields/page look unchanged. Decisions:
1a (exception responses unchanged), 2a (validation only where invalid input already failed), 3a (delete
unreferenced duplicate JSPs), 4 (SMS key and backup credentials from environment variables).
Result: 121 main files changed, about 7,400 lines removed and 2,800 added.

### 9.1 Safety net (added first, recorded on the unrefactored code)
* **`HtmlSnapshotTest`** (opt-in): compares the full response body, status, Content-Type and Content-Disposition
  of every baseline URL plus `refactor-pages.txt` (role-specific views, delete endpoints per role) with snapshots.
  The snapshots contain real data of the local DB copy and live in the untracked `html-snapshots/` directory.
  ```bash
  mvn test -Dtest=HtmlSnapshotTest -Dupgradetest.snapshot=record    # on the reference code
  mvn test -Dtest=HtmlSnapshotTest -Dupgradetest.snapshot=compare   # after a change
  ```
  Run it on its own (other tests leave rows that show up in pages). Snapshots were recorded on `5c8990e` and,
  after the whitespace-only JSP change (§9.3), re-recorded on `eb7823b`; every later commit is byte-identical.
  To re-record: check out the reference commit in a worktree, copy `application-local.properties` and the current
  `HtmlSnapshotTest`/`WebSession`/`upgradetest/*.txt` into it and run `record` with
  `-Dupgradetest.snapshot.dir=<repo>/html-snapshots`.
* **`RefactoringSafetyTest`**: exact responses and stored rows of every upload/save endpoint touched (notice, slide,
  gallery, AICTE, downloads, NBA, responsibility, holiday, lab docs, administration roles, staff, HOD add faculty,
  HOD in-charge, registration, password-reset tokens, `/approval/addFaculty`, lenient notice dates, department URL
  and method edge cases). New tests were also run against the code before the respective commit.

### 9.2 Commits
| Commit | Area |
|---|---|
| `5c8990e`, `6504567`, `57fef94` | Safety net (snapshot test, endpoint tests) |
| `4b9f199` | SLF4J logging instead of `System.out`/`printStackTrace` |
| `079fa27` | One global exception handler with request-aware logging (responses unchanged) |
| `98bdd68` | `KecProperties` (`kec.*`); SMS API key and backup credentials from environment variables |
| `f9b7819` | Constructor injection (61 classes) |
| `348e982` | `AccessService` instead of 13 copy-pasted role checks |
| `b9e65b8` | Layering: `PasswordResetService`, `GrievanceService`, `FacultyService.addPlaceholderFaculty`, `UserService.assignHodRole` |
| `3703c8e` | DTOs instead of entities in the document-list and department views |
| `a77967b`, `dd69b17` | Shared notice filter (13 copies); one `DepartmentController` instead of ten (1,871 -> 137 lines) |
| `03c403f` | One `UploadService` instead of 14 copy-pasted upload handlers |
| `30f52a9` | Attendance/leave integration: stateless services, per-request temp files, no `System.exit` |
| `749d7ed` | Core endpoints moved out of `store/CommonResourceController` (`FacultyProfileController`, `PeopleAdminController`) |
| `eb7823b` | JSPs without scriptlets/Java imports; 11 unreferenced duplicate JSPs deleted |
| `993f702` | JPQL/derived queries instead of native SQL; list queries no longer load the file BLOBs |
| `4b3e403` | Bean Validation for the registration form |
| `6a6c917` | Renames (`NoticeUploadController`, `NoticeController`, `HolidayServiceImpl`, `com.college.firebase.FirebaseSetup`) |

### 9.3 Behaviour changes (all approved)
* **Registration with invalid input** shows the form again with the messages (was a 404: the controller returned
  a view that does not exist). Rules and messages unchanged.
* **Calendar misconfiguration** (`/rs`) fails that request instead of `System.exit(1)` stopping the whole server.
* **SMS** needs the environment variable **`SMS_API_KEY`** (or `kec.sms.api-key` in `application-local.properties`).
  The key was in `MessageSender.java`; it is still in git history, so rotate it at Textlocal. Without it a warning is
  logged at startup and Textlocal rejects the messages. Added to `PRODUCTION_DEPLOYMENT.md`.
* **Deleted JSPs** (unreferenced, but they could be opened directly under `/jsp/...`): `admin/HeaderDashboard copy`,
  `admin/UploadDashboard copy`, `footer copy`, `footer2`, `header2`, `old/login-old`, `old/registration-old`,
  `old/sms-old`, `old/uploadfile-old`, `user/HeaderDashboard copy`, `user/HodDashboard copy`, and the fully
  commented-out `FacultyController.java`.
* **Whitespace only**: `/faculty`, `/about/staff`, `/trainingplacement/placedstudent` and `invalidDeptId.jsp` start
  with 4 fewer blank lines (removed `<%@ page import %>` directives); the photo gallery has one more.
* Kept on purpose (not behaviour changes): a controller exception still gives an empty HTTP 200 (decision 1a);
  notice dates keep `SimpleDateFormat`'s lenient parsing (a new formatter per call: the shared one was not
  thread-safe); the known broken pages stay broken (e.g. `/faculty?deptno=7` truncated, per-faculty shortcuts).

### 9.4 New settings (`application.properties`, values = the former hard-coded ones)
`kec.mail.from`, `kec.mail.grievance-to`, `kec.paths.notice-upload-dir`, `kec.paths.home-page-image-dir`,
`kec.paths.pdf-logo`, `kec.sms.url`, `kec.sms.sender`, `kec.sms.api-key` (= `${SMS_API_KEY}`),
`kec.firebase.database-url`, `kec.firebase.credentials`, `kec.calendar.service-account`, `kec.calendar.p12-key`,
`kec.calendar.calendar-id`, `kec.report.attendance-mail-to`. `KECDBBackup` (standalone `main`) reads `DB_USERNAME`,
`DB_PASSWORD`, `MAIL_USERNAME`, `MAIL_PASSWORD`, `BACKUP_DB_NAME`, `BACKUP_MAIL_TO`.

### 9.5 Not changed (out of scope or needs a decision)
* Store management (only the core endpoints left `CommonResourceController`; its `/common/store/**` code is
  unchanged). Store classes keep field injection, `System.out`, native queries.
* Security items of `PROJECT_ANALYSIS.md` §7 (anonymous `/rn` `/rl` `/rs` `/api/uploadAdministration`
  `/approval/addFaculty`, GET deletes, CSRF off, JSPs outside `WEB-INF`, path traversal in `/hod/{fileName}/{id}`,
  new faculty password = user name, unchecked upload types).
* Known bugs kept: calendar events use `-07:00` instead of IST; every attendance report goes to
  `kec.report.attendance-mail-to`, not to the requesting faculty member; `/approval/addFaculty` inserts blank rows.
* Remaining naming oddities: `FileUploadFacultycontroller`, `RoleserviceImpl`, `AchievementImageSlider`
  (a controller), `FacultyKeyPrpsValueRepository`, `UserDashboard` (a controller). `UserServiceImpl` still uses
  the store's `ProductTransformer` for two user-name lists.
* Hard-coded page content (header/footer menus, contacts, fee table, ...) - the "make pages dynamic" phase.

### 9.6 Manual re-test after the refactoring
Items of the checklist in `PROJECT_ANALYSIS.md` §9 whose code changed (the rest is covered by the page baseline
and the snapshots):
* Faculty list `/faculty?deptno=1..8` and profile; HOD deletes faculty of own department (other department refused).
* Each department site: about (notices, scrolling notices, civil blinking message), notice, static pages.
* All uploads with real files: admin notice/slide/gallery/AICTE/download/NBA/responsibility/holiday/lab doc,
  HOD notice and lab doc, faculty notice and slide, faculty profile photo, document and link; open and delete them.
* Admin: add faculty and staff, placements, TPO coordinators; HOD in-charge; registration (also with invalid input);
  bulk SMS with `SMS_API_KEY` set.
* Forgot password (mail, link, expired/invalid token, new password); grievance mail.
* Photo gallery page.
* Integrations: `/rn` (attendance report from Firebase), `/rl`, `/rs` (calendar event); app keeps running when the
  calendar key is wrong.
* Store regression: the faculty/product autocompletes on the SM and faculty store pages (`/common/store/**`).

## 10. Dynamic content (phase "make pages dynamic")

Plan and findings: **`docs/DYNAMIC_CONTENT_FINDINGS.md`**. All recommendations of its §8 were approved on
2026-10-19 (D9 PIN and D4 Computer Centre content still need input). Commit plan: findings §9.

### 10.1 Schema migrations (`docs/sql/dynamic/`)
* New tables 01–12 (`site_setting`, `page_section`, `link_item`, `contact_person`, `site_image`, `fee_item`,
  `department_site`, `notice_type`, `sms_group`/`sms_group_member`, `content_area`/`content_area_role`,
  `document_type`, `content_change_log`): InnoDB, `utf8mb4_unicode_ci`, no foreign keys, no change to existing tables.
* Seeds: `10_roles_seed.sql` (12 new rows in `test_role`), `10_content_area_seed.sql` (31 areas, permission matrix).
* **Apply them by hand before deploying** (order and rollback: `docs/sql/dynamic/README.md`); `ddl-auto` stays `none`.
  Applied to the local copy `keckacin_college2` on 2026-10-19. `.gitignore` excludes `*.sql` except this folder.
* `content_change_log` has a `new_value` column next to `old_value` (not in the first draft of the findings).

### 10.2 Roles and permissions (step 2)
* New authorities: `SubAdmin`, `Principal`, `Registrar`, `ExamController`, `TPO`, `NBA`, `Accounts`, `Librarian`,
  `Warden`, `PIO`, `ClubInCharge`, `Staff` (rows in `test_role`, so they also appear in the registration dropdown).
* `ContentPermissionService`: areas a user may edit (`E` edit / `A` add only), from `content_area_role`. Admin may
  always edit everything (not a matrix row). Scope `own_department`: HOD → `department_site.hod_username`,
  Faculty → `department_site.dept_id` = own `faculty.dept_id`. `department_site` is still empty (seeded in the
  departments step), so HOD/Faculty get no department area yet.
* New URLs: `/manage` (dashboard for every editor; 403 without any area), `/mydashboard` (redirects to the user's
  landing page), Admin only: `/auth/roles` (assign the new roles; Admin/Faculty/HOD/SM/SK are never changed there),
  `/auth/roles/user/{id}` (POST saves), `/auth/roles/areas` (permission matrix, POST saves; the `system` area cannot
  be given away). Every change is written to `content_change_log`.
* `WebSecurityConfig`: `/manage/**` and `/mydashboard` require login; all existing rules unchanged.
* Landing page: unchanged for SM, SK, Admin, Faculty, HOD (also when they hold a new role in addition); users with
  only new roles land on `/manage`.
* Approved behaviour change B22: the header's "Dashboard" link (`header.jsp`, desktop and mobile) points to
  `/mydashboard` instead of `/auth/uploadDashboard`, so HOD/Faculty no longer get access denied.
* Admin sidebar: "Assign Website Roles", "Website Content". HOD dashboard: "Website Content" tile when the HOD may
  edit an area (seed: gallery and NBA, add only).
* No edit screens yet: the `/manage` tiles describe the areas; screens follow area by area (findings §9, steps 3+).
* Tests: `ContentRolesTest` (landing pages, dashboard per role, 403 for the Admin screens, assigning/removing roles,
  matrix edit, department scope). Test users `zzupgtest_tpo`, `zzupgtest_subadmin`; the tests need the
  `docs/sql/dynamic` scripts applied (they fail with a hint otherwise).

### 10.3 Site settings (step 3)
* `site_setting` (seed `01_site_setting_seed.sql`, 13 keys, area `site`): browser title, college name, short name,
  phone, e-mail, principal's e-mail, footer address lines, fee-page PIN, footer "Since", copyright and credit.
* JSP tag `<kec:setting key="...">today's text</kec:setting>` (`WEB-INF/kec.tld`, `SettingTag`): prints the value, or
  the body when the value is NULL or the table is missing, so every page keeps working without the migration.
  Values are escaped with `HtmlText.escapeMarkup` (`< > " '`); `&` is kept, so `&copy` and similar stay byte-identical.
  The `taglib` directive is put on an existing line so no extra newline appears in the output.
* Used in `header.jsp`, `footer.jsp`, both dashboard headers, `forgotPassword.jsp`, `updatePassword.jsp`, legal
  Contact Us, refund, fee structure, grievance, facilities Contact Us (phone only).
* Not yet dynamic because the values differ between pages (decision D9, PIN 854106/854109): the address on
  `/facilities/contactus` ("Katihar Bihar, India, Pin – 854106") and in the password pages ("Bheriya Rahika ...,
  Katihar 854106"); the footer itself says "Hajipur 854109".
* `/manage/settings` (Admin, SubAdmin = editors of area `site`): one field per text, "use the page's original text"
  (NULL), validation per type (phone/e-mail/url/number), nothing saved if a field is invalid, change log.
  Values are cached; changes made with SQL directly need an application restart.
* `HtmlSnapshotTest` compare: all 410 pages byte-identical with the seed applied (snapshots re-recorded on
  `d6db9b1`, i.e. after the approved B22/sidebar changes of step 2).
* Tests: `SiteSettingsTest` (change shows on all pages, original text, escaping, validation, permissions).
* Working-tree note: the repository is checked out with `core.autocrlf=true` (JSPs have CRLF in the working tree).
  Editing a JSP with a tool that writes LF changes the HTML output (line endings) although git shows no change;
  the snapshots catch it.

### 10.4 Menus and link lists (steps 4-5)
* `link_item` (seed `03_link_item_seed.sql`, 10 lists, 129 entries; DDL got a `css_class` column for the social
  icons' colours), JSP tag `<kec:links list=".." var=".."/>` + `<c:choose>` with today's markup as fallback,
  EL function `kec:text` (same escaping as settings). Lists are cached; changes made with SQL need a restart.
* Live from the database: top bar, social icons, footer links (byte-identical) and the page sidebars of About,
  Academics, Facilities, Training & Placement, Approval, RTI (49 JSPs; same look, the generated markup differs only in
  whitespace, legacy `menu-mlid-*` classes and an unstyled `active` class). Differences found by the snapshot compare
  (all intended): B3 fixed (`/academic/ attendance`), one label per list ("Academic Calender" also on the calendar page,
  "Anti Ragging" also on the labs page, "Allotment & Surrender Report" on `/academic/adminsValue`).
  Not changed: the sidebar of `/trainingplacement/studentplacementcoordinator` (different markup), department sidebars
  (departments step).
* Main menu: one tree (70 entries) for the desktop menu, the mobile drawer and the side menu. **Switched off** by the
  seed (`links.main_menu.enabled` = no), so the pages keep today's menus. Switching it on in `/manage/links` makes
  the mobile menu show the same entries as the desktop menu (B2) and generates clean markup (same CSS classes).
  Login/Logout/Dashboard entries stay in the JSP.
* `/manage/links` (area `menu`: Admin, SubAdmin): choose a list, add/edit/delete, up/down, visible, new tab, parent and
  column heading (main menu), icon/classes (social), switch a list on/off, preview (`/manage/links/preview` = home
  page with every list from the database, also switched-off ones). URLs: `/...`, `#`, `http(s)://`, `mailto:`,
  `tel:` only. Change log.
* Tests: `LinksTest`.

### 10.5 Page sections (step 6)
* `page_section` (seed `02_page_section_seed.sql`): History text, Principal's message (with signature), Vision,
  Mission, Affiliation; bodies are today's exact HTML incl. line endings, so the pages stay byte-identical.
  Setting `principal.faculty_id` (11) for the principal's photo.
* JSP tag `<kec:section key="page/section">today's HTML</kec:section>`: stored HTML, nothing if hidden, body if no row.
* `/manage/sections` (areas with sections; About: Admin, SubAdmin, Principal): HTML editor (default) and a visual
  editor (Quill 2.0.3, BSD, served from `/js/vendor/quill-2.0.3/`, checksum checked against npm), preview in a new tab,
  "show on the page" switch, history with "restore this version" and "restore the original text" (byte-identical).
  HTML is sanitised on save with the OWASP Java HTML Sanitizer (new dependency
  `owasp-java-html-sanitizer:20240325.1`): scripts, event handlers, forms and `javascript:` links are removed;
  classes, inline styles (CSS whitelist), tables, images and YouTube/Google-Maps iframes stay. Unchanged text is not
  re-sanitised.
* Tests: `PageSectionsTest`.
* Snapshot compare after steps 3-6 (reference `d6db9b1`): 355 of 410 pages byte-identical; the 55 others are the
  sidebar pages, identical after normalising whitespace except the intended differences listed in 10.4.

### 10.6 Page blocks (headings, subheadings, text, images, documents on every page)
* Approved 2026-10-19: new tables `page_block`, `content_file` (`13_…`, `14_…`); `department_site` seeded
  (`07_department_site_seed.sql`, department ids and the HOD accounts the JSPs check today). HOD scope = own faculty
  department + `hod_username`; Faculty scope = own faculty department.
* `<kec:blocks/>` in 191 public JSPs (before the footer include, or at the end of `feestructure.jsp`): renders the
  page's visible blocks and, for its editors, an "Edit this page" link. Prints nothing otherwise, so anonymous pages
  stay byte-identical. Page key from the URL (`/about/history` → `about.history`, `/` → `home`); area per page in
  `PageKeys.area` (e.g. T&P pages → `tp`, `/academic/syllabus` → `exam`, `/department/cse/*` → `department.cse`).
* `/manage/blocks` (`?page=…`, or `?url=` to open a page by address): add heading / subheading / text (visual editor or
  HTML, sanitised) / image (JPG, PNG, GIF, WebP; small/medium/full; caption) / document (PDF shown in the page or as a
  link; .doc/.docx/.xls/.xlsx/.ppt/.pptx as download link), reorder, hide, delete. Add-only users (e.g. Faculty on
  their department) change/delete only their own blocks. Files are checked by content (no SVG/HTML), max 20 MB.
* `/content-file/{id}[/{name}]`: serves the files with a whitelisted type, `nosniff`, inline for images/PDF and
  attachment for Office files; files of hidden blocks only for the page's editors. Deleting/replacing removes unused files.
* Tests: `PageBlocksTest`. Snapshot compare: anonymous pages unchanged; logged-in editors' pages show the edit link.

### 10.7 Existing page texts editable (group 1: Academic) and navigation
* No schema change: the texts are `page_section` rows (`15_page_section_pages_seed.sql`, **generated** from the
  `<kec:section key title>` tags by `docs/sql/dynamic/tools/page_sections_seed.py`). 16 texts on 12 Academic pages
  (admission ×5, regulations, calender, syllabus, attendance, university results, payment tutorial, rules, academic
  council, anti ragging, mentors, notice). Area per page = `PageKeys.area` (calender/syllabus/attendance/results →
  `exam`, payment tutorial → `fees`, the rest → `academic`).
* Kept in code: database lists (course seats on admission, holiday and lab documents), the unlinked legacy
  `/academic/adminsValue` (decision D3), page titles in the coloured bar.
* Sanitiser: additionally allows `<object>`/`<embed>` of type `application/pdf` with a path on this website (regulations).
* "Edit this page" (`/manage/blocks?page=…`) lists the page's texts with links to `/manage/sections?id=…`;
  `/manage/sections` groups the texts by page and has a search box.
* Navigation: `<kec:manageNav/>` button bar on every content screen and on `/auth/roles*` (only the screens the user
  may open); header menus (desktop, mobile, side) have "Website Content" (`/manage`) for users with any content area.
* Production: `docs/sql/dynamic/apply_production.sh` runs backup, tables, seeds and checks in one go.
* Tests: `PageTextsTest` (seed = JSP text, area, edit/restore byte-identical, permissions, PDF embed, buttons).
  Snapshot compare against `f6295cb`: all anonymous pages byte-identical; logged-in Admin/HOD/Faculty pages show the
  header link and the reworded "Edit this page" link; store users unchanged.

### 10.7a Existing page texts editable (group 2: Facilities)
* 19 more `page_section` rows (same generated seed, no schema change) on 14 pages: bank, central library (photo, text),
  clubs, computer centre (photo, text), guest house, gymnasium, health centre, hostels (introduction and the contents of
  the three accordion panels: in-charges, fees, instructions; the accordion stays code), medical facilities, placement
  brochure (PDF), sports facilities, startup cell, useful links, Wi-Fi. Areas: bank → `fees`, library → `library`,
  hostels → `hostel`, clubs/sports/gymnasium → `activities`, placement brochure/startup cell → `tp`, rest → `facilities`.
* Kept in code: downloads and responsibility documents (database lists), contact us (form; address is decision D9),
  the unmapped `holiday.jsp`.
* Wi-Fi page: a raw non-breaking space was printed as "5Â PM" (the JSP is read as ISO-8859-1); it is now `&nbsp;`.
  That is the only change in the anonymous snapshot compare.
* Test: `PageTextsTest.facilitiesTextsFollowTheirAreas`.

### 10.7b Existing page texts editable (group 3: T&P, Approval, RTI)
* 11 more `page_section` rows (generated seed, no schema change) on 10 pages: about T&P cell (text, brochure),
  placement brochure, rules of T&P, tips for resume (area `tp`); AKU approval, mandatory disclosure (`approval`);
  public information cell, RTI act, RTI application, RTI status (`rti`).
* Kept in code: NBA documents, AICTE approval documents, placed students, student coordinators (database lists with
  their own upload screens), grievance (form; contact via settings).
* Tips for resume: curly quotes were printed as mojibake (JSP read as ISO-8859-1); now `&lsquo;`/`&rsquo;`.
* Test: `PageTextsTest.tpoChangesTrainingAndPlacementTextsButNotRti`. Snapshot compare: anonymous pages byte-identical
  except the Wi-Fi and resume-tips encoding fixes.

### 10.7c Existing page texts editable (group 4: Legal)
* 5 more `page_section` rows (generated seed, no schema change): privacy policy, terms and conditions, product and
  services (courses table) → area `legal`; refund and cancellation (text only, phone and e-mail stay contact
  settings), fee structure (heading and fee table; college name and PIN above it stay settings) → area `fees`.
* Kept in code: `/legal/contactus` (form; contact via settings). The footer was made editable in step 3 (settings).
* The fee and course tables are plain HTML for now; the planned fee and department screens (steps 12–13, findings
  §4.7) would replace them.
* Test: `PageTextsTest.legalTextsKeepTheirContactSettings`. Snapshot compare: Legal pages byte-identical.

### 10.7d Existing page texts editable (group 5: Home page)
* 6 more `page_section` rows (generated seed, no schema change) of `index.jsp`, area `home` (SubAdmin; Admin):
  scrolling links before the notices, Important Links, About KEC, Gallery pictures, Video Gallery video, map.
* Kept in code: image slider, notices, achievements, web-team swiper (database data with their own upload screens),
  the "View All" buttons, styles and scripts. The marquee links and Important Links are edited as HTML (instead of the
  `link_item` lists planned in findings §4.1), so all home texts are on one screen.
* Test: `PageTextsTest.homeTextsAreEditableByTheHomeArea`. Snapshot compare: home page byte-identical.

### 10.7e Existing page texts editable (group 6: department sites, one site per commit)
Area `department.<site>` (SubAdmin, HOD of the site: edit; Faculty of the department: add page blocks only).
An HOD edits only the own site(s): `department_site.hod_username` or the HOD's faculty department (scope
`own_department`); `hodeee` covers eee, ece and ecevlsi, `hodmech` the three mechanical sites. Test:
`PageTextsTest.hodChangesOnlyTheTextsOfOwnDepartment` (CSE HOD: CSE yes, ASH and Civil 403).
* **CSE** (9 texts): about introduction, vision & mission, lab list, attendance, awards, course file, programs,
  students, weekly test. Kept in code: notices and the HOD upload/delete links, the social icons (one shared list
  later, findings §4.4), the sidebar, the lab-documents iframe. Not wrapped: question bank (empty table remnant, no
  text; documents later, D5), `staff.jsp` (no route, unreachable), notice page (database).
  Test: `PageTextsTest.cseTextsAreEditableByTheDepartmentArea`. Snapshot compare: CSE pages byte-identical.
* **ASH** (8 texts): about introduction, vision & mission, lab list, awards, course file, programs, students, weekly
  test. Kept in code / not wrapped as for CSE (question bank, unrouted `staff.jsp`, notices). `attendace.jsp` (typo)
  is not wrapped: `/department/ash/attendance` answered 404 before and still does (fixing it would be a behaviour
  change). Vision: raw bullet (shown as mojibake "â€¢") replaced by `&bull;`.
  Test: `PageTextsTest.ashTextsBelongToTheAshArea`. Snapshot compare: byte-identical except the vision bullet.

### 10.8 Manual re-test
* Log in as an Admin: sidebar → Assign Website Roles; give a test account `TPO`, log in with it → lands on `/manage`
  and sees "Training & Placement", "Gallery", "College notices" only. Remove the role again.
* Who may edit what: change one cell, check the account's `/manage`, change it back.
* As HOD and Faculty: header "Dashboard" opens the own dashboard; landing pages after login unchanged.
* Website Content → Site settings → "Texts": change the phone, check footer, Contact Us, refund and grievance
  pages; tick "use the page's original text" and check again; set it back.
* Website Content → Menus and sidebars: add a footer link and a sidebar entry, move, hide, delete; main menu: preview,
  switch on, check desktop + mobile (narrow window) + side menu, switch off again.
* Website Content → About KEC → Page texts: change History in HTML and in the visual editor, preview, save, restore
  the original text; hide/show a section.
* Any page as an editor → "Edit this page": add heading, subheading, text, image, PDF, Word file; reorder, hide, delete;
  as Faculty on an own department page (add, change own only) and on another department (refused).
* Header "Website Content" → button bar: every button opens its screen, the current one is highlighted.
* `/academic/admission` as SubAdmin → "Edit this page" → a text → change, save, check the page, restore the original.
