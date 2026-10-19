-- 08 notice types (today: string literals in 4 upload forms + NoticeBoardServiceImpl)
CREATE TABLE IF NOT EXISTS notice_type (
  code          VARCHAR(40)  NOT NULL PRIMARY KEY,    -- value stored in noticeboard.nb_noticetype ('CSENotice', ...)
  label         VARCHAR(100) NOT NULL,
  site_code     VARCHAR(40)  NULL,                    -- NULL = college-wide (Notice/News/Tender)
  area_code     VARCHAR(40)  NOT NULL DEFAULT 'notices',
  for_admin     TINYINT(1)   NOT NULL DEFAULT 1,
  for_hod       TINYINT(1)   NOT NULL DEFAULT 0,
  for_faculty   TINYINT(1)   NOT NULL DEFAULT 0,
  sort_order    INT          NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
