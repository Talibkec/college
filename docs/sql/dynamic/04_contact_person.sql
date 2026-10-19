-- 04 role/contact lists that are not (only) faculty profiles (T5)
CREATE TABLE IF NOT EXISTS contact_person (
  contact_id    BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  list_code     VARCHAR(60)   NOT NULL,               -- 'library', 'cultural_club', 'sports_club', 'hostel', 'web_team', 'principals', 'pio'
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
