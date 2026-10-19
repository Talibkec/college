-- Department mini-sites (findings §2 table 07). dept_id = department.dept_id of the department (used for the
-- department scope of HOD and Faculty: their faculty.dept_id); hod_username = the department account that the JSPs
-- check today (HOD scope also for accounts without a faculty record). Names/groups/paths are today's menu texts.
-- Idempotent: INSERT IGNORE keeps rows changed later.
INSERT IGNORE INTO department_site (site_code, dept_id, lab_dept_id, full_name, short_name, menu_group, about_path, notice_code, hod_username, duration_years, course_level, sort_order) VALUES
  ('civil', 2, NULL, 'Civil Engineering', 'CIVIL', 'UG', '/department/civil/about', 'CIVIL', 'hodcivil', 4, 'Graduation', 10),
  ('mechanical', 3, NULL, 'Mechanical Engineering', 'MECH', 'UG', '/department/mechanical/mech', 'MECH', 'hodmech', 4, 'Graduation', 20),
  ('mechandsmartmanufacturing', 7, NULL, 'Mechanical and Smart Manufacturing', 'MSM', 'UG', '/department/mechandsmartmanufacturing/about', 'MECH', 'hodmech', 4, 'Graduation', 30),
  ('ece', 6, NULL, 'Electronics Engineering (VLSI Design and Technology)', 'ECE', 'UG', '/department/ece/about', 'ELEC', 'hodeee', 4, 'Graduation', 40),
  ('cse', 1, 1, 'Computer Sc. & Engineering', 'CSE', 'UG', '/department/cse/about', 'CSE', 'hodcse', 4, 'Graduation', 50),
  ('eee', 4, NULL, 'Electrical & Electronics Engg.', 'EEE', 'UG', '/department/eee/about', 'ELEC', 'hodeee', 4, 'Graduation', 60),
  ('ash', 5, NULL, 'Applied Sc. & Humanities', 'ASH', 'UG', '/department/ash/about', 'ASH', 'hodash', NULL, NULL, 70),
  ('fp', NULL, NULL, 'Food Processing', 'FP', 'UG', '/department/fp/about', 'FP', 'hodfood', 4, 'Graduation', 80),
  ('ecevlsi', 6, NULL, 'Electronics Engineering (VLSI Design and Technology)', 'ECE-VLSI', 'PG', '/department/ecevlsi/about', 'ELEC', 'hodeee', 2, 'Post Graduation', 90),
  ('manufacturing', 3, NULL, 'Manufacturing Engineering', 'MFG', 'PG', '/department/manufacturing/about', 'MECH', 'hodmech', 2, 'Post Graduation', 100);
