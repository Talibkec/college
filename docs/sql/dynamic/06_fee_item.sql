-- 06 fee tables (T7)
CREATE TABLE IF NOT EXISTS fee_item (
  fee_id        BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  schedule_code VARCHAR(60)   NOT NULL,               -- 'day_scholar', 'hostel'
  area_code     VARCHAR(40)   NOT NULL,               -- 'fees' / 'hostel'
  label         VARCHAR(255)  NOT NULL,               -- '(i) Registration fee'
  category      VARCHAR(60)   NULL,                   -- 'GEN_BC_EBC', 'SC_ST' (NULL for hostel)
  year_no       TINYINT       NULL,                   -- 1..4 (NULL for hostel)
  amount        DECIMAL(10,2) NULL,
  amount_text   VARCHAR(255)  NULL,                   -- hostel rows: '6000/- (for Six Months 6 * 1000=6000)'
  sort_order    INT           NOT NULL DEFAULT 0,
  updated_by    VARCHAR(255)  NULL,
  updated_at    DATETIME      NULL,
  KEY ix_fee_schedule (schedule_code, sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
