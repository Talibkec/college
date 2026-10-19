-- 07 the department mini-sites (10 sites, but `department` has 7 rows). Seeded in the departments step.
CREATE TABLE IF NOT EXISTS department_site (
  site_code       VARCHAR(40)  NOT NULL PRIMARY KEY,  -- URL segment: 'cse', 'mechanical', 'ecevlsi', ...
  dept_id         BIGINT       NULL,                  -- department.dept_id: /faculty?deptno= and Faculty scope
  lab_dept_id     INT          NULL,                  -- lab_document.dep_id used for /academic/lab?dept_id=
  full_name       VARCHAR(255) NOT NULL,              -- 'Computer Sc. & Engineering'
  short_name      VARCHAR(60)  NOT NULL,              -- 'CSE'
  menu_group      VARCHAR(10)  NOT NULL,              -- 'UG' | 'PG'
  about_path      VARCHAR(100) NOT NULL,              -- '/department/mechanical/mech' (keeps today's URL)
  notice_code     VARCHAR(20)  NULL,                  -- prefix of noticeboard.nb_noticetype: 'CSE','CIVIL','MECH','ELEC','ASH','FP'
  hod_username    VARCHAR(255) NULL,                  -- HOD scope (one HOD may appear on several sites)
  duration_years  TINYINT      NULL,                  -- Product & Services page
  course_level    VARCHAR(60)  NULL,                  -- 'Graduation'
  sort_order      INT          NOT NULL DEFAULT 0,
  visible         TINYINT(1)   NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
