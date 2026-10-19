-- 01 key/value facts shown on many pages (docs/DYNAMIC_CONTENT_FINDINGS.md §1.1, T1)
CREATE TABLE IF NOT EXISTS site_setting (
  setting_key   VARCHAR(100)  NOT NULL PRIMARY KEY,   -- e.g. 'contact.phone', 'principal.name'
  setting_value TEXT          NULL,
  value_type    VARCHAR(20)   NOT NULL DEFAULT 'text',-- 'text' | 'url' | 'email' | 'phone' | 'number' (validation)
  area_code     VARCHAR(40)   NOT NULL DEFAULT 'site',
  description   VARCHAR(255)  NULL,                   -- shown in the edit screen
  updated_by    VARCHAR(255)  NULL,
  updated_at    DATETIME      NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
