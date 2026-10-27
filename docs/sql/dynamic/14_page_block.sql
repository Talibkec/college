-- 14 headings, subheadings, texts, images and documents that editors add to a page (approved 2026-10-19).
CREATE TABLE IF NOT EXISTS page_block (
  block_id      BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  page_key      VARCHAR(150)  NOT NULL,               -- from the page URL: '/about/history' -> 'about.history', '/' -> 'home'
  area_code     VARCHAR(40)   NOT NULL,               -- who may edit (content_area_role)
  block_type    VARCHAR(20)   NOT NULL,               -- 'heading' | 'subheading' | 'text' | 'image' | 'document' | 'link'
  heading       VARCHAR(255)  NULL,                   -- heading text, image caption, document title or link text
  body_html     MEDIUMTEXT    NULL,                   -- text blocks (sanitised HTML); link blocks: the checked address
  file_id       BIGINT        NULL,                   -- content_file.file_id (image / document)
  display       VARCHAR(20)   NULL,                   -- image: 'small' | 'medium' | 'full'; document: 'embed' | 'link'
  sort_order    INT           NOT NULL DEFAULT 0,
  visible       TINYINT(1)    NOT NULL DEFAULT 1,
  created_by    VARCHAR(255)  NULL,                   -- add-only users may change/delete only their own blocks
  created_at    DATETIME      NULL,
  updated_by    VARCHAR(255)  NULL,
  updated_at    DATETIME      NULL,
  KEY ix_block_page (page_key, sort_order),
  KEY ix_block_file (file_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
