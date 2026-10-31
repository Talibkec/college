-- 17 course files of the department sites (approved 2026-10-31). Shown on /department/<site>/course, managed by the
-- department's HOD on /manage/course-files. The file itself is a content_file row; served by /course-file/{id}.
CREATE TABLE IF NOT EXISTS course_file (
  course_file_id   BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  site_code        VARCHAR(40)   NOT NULL,               -- department_site.site_code ('eee', 'cse', ...)
  semester         TINYINT       NOT NULL,               -- 1-8
  subject_code     VARCHAR(30)   NULL,                   -- 'EE301'
  subject_name     VARCHAR(255)  NOT NULL,
  faculty_name     VARCHAR(255)  NULL,                   -- who taught / prepared it
  academic_session VARCHAR(20)   NULL,                   -- '2025-26'
  file_id          BIGINT        NOT NULL,               -- content_file.file_id (PDF or Word/Excel/PowerPoint, up to 20 MB)
  visible          TINYINT(1)    NOT NULL DEFAULT 1,
  created_by       VARCHAR(255)  NULL,
  created_at       DATETIME      NULL,
  updated_by       VARCHAR(255)  NULL,
  updated_at       DATETIME      NULL,
  KEY ix_course_file_site (site_code, semester),
  KEY ix_course_file_file (file_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
