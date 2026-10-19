-- 03 menus and link lists (T3)
CREATE TABLE IF NOT EXISTS link_item (
  link_id       BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  list_code     VARCHAR(60)   NOT NULL,               -- 'main_menu', 'top_bar', 'footer', 'social', 'about_sidebar', ...
  area_code     VARCHAR(40)   NOT NULL,               -- 'menu' for menus/sidebars, else the page's area
  parent_id     BIGINT        NULL,                   -- submenu entries
  group_label   VARCHAR(100)  NULL,                   -- e.g. 'Under Graduates' / 'Post Graduates'
  label         VARCHAR(255)  NOT NULL,
  url           VARCHAR(1000) NOT NULL,
  open_new_tab  TINYINT(1)    NOT NULL DEFAULT 0,
  icon_class    VARCHAR(100)  NULL,                   -- social icons ('fab fa-facebook')
  css_class     VARCHAR(100)  NULL,                   -- extra CSS classes of the link ('hover:text-blue-600')
  visibility    VARCHAR(20)   NOT NULL DEFAULT 'all', -- 'all' | 'desktop' | 'mobile' | 'anonymous' | 'authenticated'
  sort_order    INT           NOT NULL DEFAULT 0,
  visible       TINYINT(1)    NOT NULL DEFAULT 1,
  updated_by    VARCHAR(255)  NULL,
  updated_at    DATETIME      NULL,
  KEY ix_link_list (list_code, parent_id, sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
