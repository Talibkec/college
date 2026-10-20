#!/usr/bin/env bash
# Applies all database changes of the dynamic-content phase in the right order (see README.md in this folder):
# backup -> new tables (DDL) -> data (seeds) -> check queries.
# Every script is idempotent, so running this again (e.g. after a later release added a seed) is safe: existing rows,
# including texts changed in the website's edit screens, are never overwritten.
#
# Usage (on the production server, from the repository root or this folder):
#   bash docs/sql/dynamic/apply_production.sh -u <db user> -d <database> [-h <host>] [-P <port>] [--no-backup]
# The password is asked once and kept only in a temporary file readable by you, deleted at the end.
set -euo pipefail

DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
USER_NAME="" DB="" HOST="localhost" PORT="3306" BACKUP=1
while [ $# -gt 0 ]; do
  case "$1" in
    -u) USER_NAME="$2"; shift 2 ;;
    -d) DB="$2"; shift 2 ;;
    -h) HOST="$2"; shift 2 ;;
    -P) PORT="$2"; shift 2 ;;
    --no-backup) BACKUP=0; shift ;;
    *) echo "unknown option: $1"; exit 2 ;;
  esac
done
if [ -z "$USER_NAME" ] || [ -z "$DB" ]; then
  echo "usage: $0 -u <db user> -d <database> [-h <host>] [-P <port>] [--no-backup]"; exit 2
fi

DDL=(01_site_setting.sql 02_page_section.sql 03_link_item.sql 04_contact_person.sql 05_site_image.sql 06_fee_item.sql
     07_department_site.sql 08_notice_type.sql 09_sms_group.sql 10_content_area.sql 11_document_type.sql
     12_content_change_log.sql 13_content_file.sql 14_page_block.sql)
SEEDS=(10_roles_seed.sql 10_content_area_seed.sql 07_department_site_seed.sql 01_site_setting_seed.sql
       02_page_section_seed.sql 03_link_item_seed.sql 15_page_section_pages_seed.sql)
for f in "${DDL[@]}" "${SEEDS[@]}"; do
  [ -f "$DIR/$f" ] || { echo "missing $DIR/$f"; exit 1; }
done

read -r -s -p "Password of $USER_NAME@$HOST: " PASSWORD; echo
CNF="$(mktemp)"; chmod 600 "$CNF"
trap 'rm -f "$CNF"' EXIT
ESCAPED="${PASSWORD//\\/\\\\}"; ESCAPED="${ESCAPED//\"/\\\"}"
printf '[client]\nuser=%s\npassword="%s"\nhost=%s\nport=%s\ndefault-character-set=utf8mb4\n' \
  "$USER_NAME" "$ESCAPED" "$HOST" "$PORT" > "$CNF"
unset ESCAPED
unset PASSWORD
run() { mysql --defaults-extra-file="$CNF" "$DB" "$@"; }

run -e "SELECT 1" > /dev/null || { echo "cannot connect to $DB"; exit 1; }
echo "Database: $DB on $HOST:$PORT"
echo "Will run ${#DDL[@]} table scripts and ${#SEEDS[@]} data scripts."
read -r -p "Type yes to continue: " OK
[ "$OK" = "yes" ] || { echo "stopped, nothing changed"; exit 1; }

if [ "$BACKUP" = 1 ]; then
  OUT="$HOME/backup-before-dynamic-$(date +%F-%H%M).sql.gz"
  echo "1/4 backup -> $OUT"
  mysqldump --defaults-extra-file="$CNF" --single-transaction --routines "$DB" | gzip > "$OUT"
else
  echo "1/4 backup skipped (--no-backup)"
fi

echo "2/4 new tables"
for f in "${DDL[@]}"; do echo "    $f"; run < "$DIR/$f"; done

echo "3/4 data"
for f in "${SEEDS[@]}"; do echo "    $f"; run < "$DIR/$f"; done

echo "4/4 checks (expected values in brackets)"
run -t -e "
SELECT 'content areas (31)' AS what, COUNT(*) AS found FROM content_area
UNION ALL SELECT 'permission rows (79, more if changed on the website)', COUNT(*) FROM content_area_role
UNION ALL SELECT 'department sites (10)', COUNT(*) FROM department_site
UNION ALL SELECT 'site settings (15 or more)', COUNT(*) FROM site_setting
UNION ALL SELECT 'page texts (151 or more)', COUNT(*) FROM page_section
UNION ALL SELECT 'links (129 or more)', COUNT(*) FROM link_item
UNION ALL SELECT 'page block tables (2)', COUNT(*) FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name IN ('page_block', 'content_file');"
run -t -e "SELECT setting_value AS 'main menu from database (no)' FROM site_setting WHERE setting_key = 'links.main_menu.enabled';
SHOW VARIABLES LIKE 'max_allowed_packet';"
echo "Done. Restart the application so it reads the new rows. max_allowed_packet must be above 20 MB (uploads)."
