# CLAUDE.md – rules for every session on this project

This is a legacy Spring Boot WAR application (college website + ERP) being upgraded step by step.
The plan and progress are in `docs/PROJECT_ANALYSIS.md` and `docs/UPGRADE_NOTES.md`.

## Git
- Work only on branch `kec_claude`. Never commit to `main`/`master`.
- Never push, force-push or rewrite history (no rebase/amend of pushed commits, no `reset --hard` on shared refs).
  The user pushes. The only exception is when the user explicitly asks for a push in the current session.
- Make small logical commits with clear messages. Build and run the tests before each commit.

## Secrets
- Never commit secrets (DB, SMTP, Firebase or Google credentials) or `.sql` dumps.
- Credentials come from environment variables (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `MAIL_USERNAME`,
  `MAIL_PASSWORD`) or from the untracked `application-local.properties` in the project root, activated with the `local` profile.
- Never print credential values in output or docs. Mask them.

## Scope: STORE MANAGEMENT is out of scope
- Never modify, refactor or modernise store management. That covers:
  - `core/controller/StoreController.java` and `core/controller/store/**`
  - entities `Product`, `Purchase`, `Order` and models `Product*`, `Purchase*`, `Order*`, `CustomerOrder`
  - `ProductRepository`, `PurchaseRepository`, `OrderRepository` and `Product*`/`Purchase*`/`Order*` services
  - `ProductTransformer`
  - `webapp/jsp/store/**` and `webapp/js/store/**`
  - the full list is in `docs/PROJECT_ANALYSIS.md` §5
  - Exception: `core/controller/store/CommonResourceController.java` also hosts core faculty/staff/placement endpoints.
    Only its `/common/store/**` endpoints are store. See the decision in `docs/PROJECT_ANALYSIS.md` §10.
- Only if it blocks compiling or startup: comment out the smallest piece, mark it
  `// TODO(store-management): disabled during upgrade – <reason>`, and record it in `docs/UPGRADE_NOTES.md`.

## Database
- Never change the DB schema, table/column names or ID generation without the user's approval.
- Use `ddl-auto=none` or `validate`. (The legacy config has `ddl-auto=update`. Changing it needs the user's approval; see the analysis.)
  Preserve the existing physical naming. Use identity IDs where the old code relied on `AUTO`.

## Behaviour
- No existing functionality may break. If a feature can't be kept working, stop and ask. Never silently remove it.
- Keep URLs, request parameter names, form field names and session behaviour unchanged.
- No refactoring and no "making pages dynamic" until the user explicitly starts that phase.

## Environment (WSL Ubuntu 22.04)
- JDKs: `/usr/lib/jvm/java-8-openjdk-amd64`, `/usr/lib/jvm/java-11-openjdk-amd64`, `/usr/lib/jvm/java-21-openjdk-amd64`.
  Don't install JDKs. Tell the user the install command instead.
- Maven 3.6.3 (system `mvn`, no wrapper).
- Local DB: MySQL `localhost:3306`, database `keckacin_college2` (copy of production), user `keckacin_system`.
- Port 8080 is often already used by the user's IntelliJ run. Use another port (e.g. 8099) for verification runs.

## Build / run (current version: Spring Boot 1.3.3, Java 8)
```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
mvn clean package                         # build + tests
mvn spring-boot:run -Drun.profiles=local  # run with application-local.properties
# verification run without touching the schema, on another port:
mvn spring-boot:run -Drun.profiles=local "-Drun.arguments=--server.port=8099,--spring.jpa.hibernate.ddl-auto=none"
```
(From Spring Boot 2.x the flags become `-Dspring-boot.run.profiles=local` and `-Dspring-boot.run.arguments=...`.
Keep this section updated after each upgrade stage.)
