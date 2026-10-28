-- 16 designation of a faculty member (approved 2026-10-28). NULL = "Assistant Professor", the text the faculty list
-- and the profile showed for everybody before. Idempotent: adds the column only if it is missing.
SET @col_exists = (SELECT COUNT(*) FROM information_schema.columns
                   WHERE table_schema = DATABASE() AND table_name = 'faculty' AND column_name = 'f_designation');
SET @ddl = IF(@col_exists = 0, 'ALTER TABLE faculty ADD COLUMN f_designation VARCHAR(100) NULL', 'SELECT 1');
PREPARE stmt FROM @ddl;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
