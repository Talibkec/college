-- 10 content areas and who may edit them (§1.3.3; seed: 10_content_area_seed.sql)
CREATE TABLE IF NOT EXISTS content_area (
  area_code     VARCHAR(40)  NOT NULL PRIMARY KEY,    -- 'tp', 'rti', 'department.cse', ...
  label         VARCHAR(100) NOT NULL,                -- tile title on /manage
  description   VARCHAR(255) NULL,
  site_code     VARCHAR(40)  NULL,                    -- set for department.<site> areas (department scope)
  sort_order    INT          NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
CREATE TABLE IF NOT EXISTS content_area_role (
  area_code     VARCHAR(40)  NOT NULL,
  role_name     VARCHAR(100) NOT NULL,                -- = test_role.name (the Spring authority)
  access        CHAR(1)      NOT NULL,                -- 'E' edit | 'A' add only
  scope         VARCHAR(20)  NOT NULL DEFAULT 'all',  -- 'all' | 'own_department' | 'own_record'
  PRIMARY KEY (area_code, role_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
