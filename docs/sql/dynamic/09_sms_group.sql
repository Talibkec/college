-- 09 bulk-SMS groups (today: phone lists in SMS.properties)
CREATE TABLE IF NOT EXISTS sms_group (
  group_code    VARCHAR(40)  NOT NULL PRIMARY KEY,    -- 'KECWebTeam', 'allStudents', 'allParents', 'allFaculties'
  label         VARCHAR(100) NOT NULL,
  sort_order    INT          NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
CREATE TABLE IF NOT EXISTS sms_group_member (
  member_id     BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  group_code    VARCHAR(40)  NOT NULL,
  phone         VARCHAR(20)  NOT NULL,
  name          VARCHAR(255) NULL,
  KEY ix_sms_member (group_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
