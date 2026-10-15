# Upgrade notes – branch `kec_claude`

Upgrade of the college website/ERP from Spring Boot 1.3.3 / Java 8 to Spring Boot 4.1.1 / Java 21 (target Java 25).
Only Java, Spring and libraries were upgraded. There was no refactoring, no "making pages dynamic", and no
schema change. Analysis and decisions: `docs/PROJECT_ANALYSIS.md`.

## 1. Final versions

| Component | Before | After |
|---|---|---|
| Java | 8 | **21** (Java 25 LTS as soon as the JDK is installed, see §6) |
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
| iText | 5.5.6 (+ unused iText 7.0.2) | **unchanged – decision pending (§6.2)** |
| jQuery webjar | 2.2.4 (unused) | **unchanged – decision pending (§6.3)** |

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
| (this file) | Upgrade notes |

Nothing has been pushed. Push the branch yourself: `git push -u origin kec_claude`.

## 3. Changes that matter (and how the old behaviour was kept)

Each one was found or confirmed by the tests (§5).

**Configuration / deployment**
* **Credentials** are no longer in `application.properties`. Production needs `DB_URL`, `DB_USERNAME`,
  `DB_PASSWORD`, `MAIL_USERNAME`, `MAIL_PASSWORD` (or its own `application-local.properties` + `local` profile).
* **`spring.jpa.hibernate.ddl-auto=none`**: Hibernate no longer tries to alter the schema at startup.
* **Java 21+ is required to run the app.** If production deploys the WAR into an external Tomcat, that Tomcat
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

`src/test/java/com/college/upgradetest` – 36 tests that start the real app against the local MySQL copy (see
CLAUDE.md "Tests"). They were written and the baselines recorded on the **original** version, then kept
unchanged through all stages (only the Boot-specific wiring in `AbstractAppTest` moved).
Result on Boot 4.1.1 / Java 21: **36 run, 0 failures, 1 skipped** (`leaveRequestEndpointMailsPdf`, see §6.6).

## 6. Open issues / decisions for you

1. **Install JDK 25** and switch the build to it:
   `sudo apt install openjdk-25-jdk`, then set `<java.version>25</java.version>` in `pom.xml` and use
   `JAVA_HOME=/usr/lib/jvm/java-25-openjdk-amd64`. Re-run `mvn clean package`.
2. **iText licensing (⏸ your decision).** The PDF code uses iText 5.5.6 (`com.itextpdf.text.*`, **AGPL-3.0**)
   through the `com.lowagie:itext:4.2.2` relocation. iText 7.0.2 `kernel`/`layout` (also AGPL) are declared but
   unused. Running AGPL iText in a public web app without a commercial licence brings AGPL obligations.
   Options: (a) OpenPDF 3.0.5 (LGPL/MPL, iText-4-style API, package rename `com.itextpdf.text` → `org.openpdf`
   style changes in `DocUtils`/`FirebaseDocumentHelper`), (b) keep iText 5 / move to iText 9 with a commercial
   licence or AGPL compliance, (c) Apache PDFBox (rewrite). In every case the unused iText 7.0.2 can be removed.
3. **jQuery webjar 2.2.4** is unused (pages load jQuery from a CDN). Keep, bump, or remove – your call.
4. **Junk rows in the local DB** – `/approval/addFaculty` is an **anonymous GET that inserts a blank faculty row**
   (+ "Administrative Responsibilities" key properties) on every call. Early test runs called it; 8 rows
   (faculty_id 78–102) are in the local `keckacin_college2`. Delete them with:
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
6. **Logo path**: `DocUtils.kecLogo()` reads `/sites/default/files/compressed/logo.png` (absolute path). On machines
   without it, attendance-report and leave PDFs fail (already the case before the upgrade). To run the skipped
   test locally: `sudo mkdir -p /sites/default/files/compressed && sudo cp src/main/webapp/sites/default/files/compressed/logo.png /sites/default/files/compressed/`.
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
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64   # java-25-openjdk-amd64 once installed
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
IntelliJ: set the project SDK to JDK 21 (or 25) and add `local` to the run configuration's active profiles.
