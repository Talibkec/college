# Schema migrations – dynamic-content phase

Approved in `docs/DYNAMIC_CONTENT_FINDINGS.md` §8 (D1, D15–D17). The application never applies these itself
(`spring.jpa.hibernate.ddl-auto=none`); run them by hand on every database **before** deploying the version that
needs them. Every script is idempotent (`CREATE TABLE IF NOT EXISTS`, `INSERT IGNORE` / `WHERE NOT EXISTS`), so running
it twice does no harm and never overwrites rows changed in the application.

Only text goes into these files: DDL and seed values that are already public on the website. No data dumps,
no credentials (CLAUDE.md).

## Order

| File | Kind | Needed from |
|---|---|---|
| `01_site_setting.sql` … `09_sms_group.sql` | DDL, new tables | created now, used from the step that fills them (§9 of the findings) |
| `10_content_area.sql` | DDL: `content_area`, `content_area_role` | roles and permissions (step 2) |
| `11_document_type.sql` | DDL | document pages |
| `12_content_change_log.sql` | DDL | roles and permissions (step 2) |
| `10_roles_seed.sql` | data: new rows in `test_role` | roles and permissions (step 2) |
| `10_content_area_seed.sql` | data: areas + permission matrix | roles and permissions (step 2) |
| `01_site_setting_seed.sql` | data: texts of header, footer, contact pages | site settings (step 3) |

Run all DDL files first, then the seeds:

```bash
cd docs/sql/dynamic
for f in [0-9][0-9]_*.sql; do case "$f" in *_seed.sql) ;; *) mysql -h <host> -u <user> -p <database> < "$f";; esac; done
for f in [0-9][0-9]_*_seed.sql; do mysql -h <host> -u <user> -p <database> < "$f"; done
```

Check: `SELECT COUNT(*) FROM content_area;` → 31, `SELECT name FROM test_role;` lists the 12 new roles next to
`Admin`, `Faculty`, `SM`, `HOD`, `SK`.

## Rollback

The new tables are not referenced by any existing table, so they can be dropped without touching existing data:
`DROP TABLE content_change_log, document_type, content_area_role, content_area, sms_group_member, sms_group,
notice_type, department_site, fee_item, site_image, contact_person, link_item, page_section, site_setting;`
New roles: first remove their `user_role` rows, then `DELETE FROM test_role WHERE name IN ('SubAdmin', 'Principal',
'Registrar', 'ExamController', 'TPO', 'NBA', 'Accounts', 'Librarian', 'Warden', 'PIO', 'ClubInCharge', 'Staff');`
