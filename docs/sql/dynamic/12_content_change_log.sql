-- 12 who changed what (history / restore)
CREATE TABLE IF NOT EXISTS content_change_log (
  log_id        BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  area_code     VARCHAR(40)  NOT NULL,
  entity        VARCHAR(40)  NOT NULL,                -- 'page_section', 'link_item', 'download', 'user_role', ...
  entity_id     VARCHAR(100) NOT NULL,
  action        VARCHAR(10)  NOT NULL,                -- 'create' | 'update' | 'delete' | 'restore'
  old_value     MEDIUMTEXT   NULL,                    -- JSON of the row before the change (no file bytes)
  new_value     MEDIUMTEXT   NULL,                    -- JSON of the row after the change
  changed_by    VARCHAR(255) NOT NULL,
  changed_at    DATETIME     NOT NULL,
  KEY ix_change_entity (entity, entity_id, changed_at),
  KEY ix_change_area (area_code, changed_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
