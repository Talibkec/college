-- 13 uploaded images and documents of page blocks (approved 2026-10-19). Served by /content-file/{id}.
CREATE TABLE IF NOT EXISTS content_file (
  file_id       BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  area_code     VARCHAR(40)   NOT NULL,               -- content area of the page the file was uploaded for
  file_name     VARCHAR(255)  NOT NULL,               -- original name, cleaned ('Brochure 2026.pdf')
  content_type  VARCHAR(100)  NOT NULL,               -- checked against the file content: image/jpeg|png|gif|webp, application/pdf, Office types
  file_size     BIGINT        NOT NULL,
  data          LONGBLOB      NOT NULL,               -- up to the upload limit (20 MB, spring.servlet.multipart.max-file-size)
  uploaded_by   VARCHAR(255)  NULL,
  uploaded_at   DATETIME      NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
