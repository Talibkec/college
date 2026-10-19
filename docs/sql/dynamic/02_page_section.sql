-- 02 rich-text blocks (T2)
CREATE TABLE IF NOT EXISTS page_section (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
