-- New authorities (docs/DYNAMIC_CONTENT_FINDINGS.md §1.3.2, approved D15/D16). Data only, no DDL; ids by AUTO_INCREMENT as today.
-- Idempotent: a role that already exists is skipped. Nobody gets a role automatically (Admin assigns them in /auth/roles).
INSERT INTO test_role (name) SELECT 'SubAdmin' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'SubAdmin');
INSERT INTO test_role (name) SELECT 'Principal' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'Principal');
INSERT INTO test_role (name) SELECT 'Registrar' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'Registrar');
INSERT INTO test_role (name) SELECT 'ExamController' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'ExamController');
INSERT INTO test_role (name) SELECT 'TPO' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'TPO');
INSERT INTO test_role (name) SELECT 'NBA' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'NBA');
INSERT INTO test_role (name) SELECT 'Accounts' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'Accounts');
INSERT INTO test_role (name) SELECT 'Librarian' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'Librarian');
INSERT INTO test_role (name) SELECT 'Warden' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'Warden');
INSERT INTO test_role (name) SELECT 'PIO' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'PIO');
INSERT INTO test_role (name) SELECT 'ClubInCharge' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'ClubInCharge');
INSERT INTO test_role (name) SELECT 'Staff' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM test_role WHERE name = 'Staff');
