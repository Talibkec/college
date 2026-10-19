-- 11 document types for the generic document screen (download.doc_type values; seeded with the document pages)
CREATE TABLE IF NOT EXISTS document_type (
  doc_type      VARCHAR(30)  NOT NULL PRIMARY KEY,    -- same length as download.doc_type
  label         VARCHAR(100) NOT NULL,
  area_code     VARCHAR(40)  NOT NULL,
  display_mode  VARCHAR(20)  NOT NULL DEFAULT 'list', -- 'list' | 'embed_latest' | 'list_embed_first'
  empty_text    VARCHAR(255) NULL,                    -- today's placeholder ('Coming Soon', 'Updated Soon...')
  sort_order    INT          NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
