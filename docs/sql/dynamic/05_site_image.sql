-- 05 images grouped in albums (T6)
CREATE TABLE IF NOT EXISTS site_image (
  image_id      BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  album_code    VARCHAR(60)   NOT NULL,               -- 'photo_gallery', 'computer_centre', 'library', 'home_gallery', 'web_team', 'aicte_banner', 'logo'
  area_code     VARCHAR(40)   NOT NULL,
  caption       VARCHAR(255)  NULL,
  file_name     VARCHAR(255)  NULL,
  file_type     VARCHAR(50)   NULL,
  image         MEDIUMBLOB    NULL,                   -- uploaded image ...
  static_path   VARCHAR(500)  NULL,                   -- ... or an existing file, e.g. '/wp-content/uploads/2018/img1.JPG'
  sort_order    INT           NOT NULL DEFAULT 0,
  visible       TINYINT(1)    NOT NULL DEFAULT 1,
  created_by    VARCHAR(255)  NULL,                   -- needed for 'A' (add-only) users
  uploaded_at   DATETIME      NULL,
  KEY ix_image_album (album_code, sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
