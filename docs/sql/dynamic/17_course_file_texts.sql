-- 17 the paragraph above the course files of the 10 department sites (2026-10-31): replaces the old placeholder
-- 'Updated Soon...' only where it was never changed on the website (updated_by IS NULL and still the old text).
-- New installations get the text from 15_page_section_pages_seed.sql. Restart the application afterwards (text cache).
SET NAMES utf8mb4;
UPDATE page_section SET body_html = '<p>The course file is the complete teaching record of a subject: syllabus, course outcomes, lesson plan, lecture notes, assignments, question papers and results. The course files of the Department of Applied Science &amp; Humanities are listed below, semester by semester. Search for a subject by its name, code or faculty member.</p>'
  WHERE page_key = 'department.ash.course' AND section_key = 'main' AND updated_by IS NULL
    AND body_html = '<p>Updated Soon&#8230;</p>';
UPDATE page_section SET body_html = '<p>The course file is the complete teaching record of a subject: syllabus, course outcomes, lesson plan, lecture notes, assignments, question papers and results. The course files of the Department of Civil Engineering are listed below, semester by semester. Search for a subject by its name, code or faculty member.</p>'
  WHERE page_key = 'department.civil.course' AND section_key = 'main' AND updated_by IS NULL
    AND body_html = '<p><strong>Updated Soon &#8230</strong></p>';
UPDATE page_section SET body_html = '<p>The course file is the complete teaching record of a subject: syllabus, course outcomes, lesson plan, lecture notes, assignments, question papers and results. The course files of the Department of Computer Science &amp; Engineering are listed below, semester by semester. Search for a subject by its name, code or faculty member.</p>'
  WHERE page_key = 'department.cse.course' AND section_key = 'main' AND updated_by IS NULL
    AND body_html = '<p>Updated Soon&#8230;</p>';
UPDATE page_section SET body_html = '<p>The course file is the complete teaching record of a subject: syllabus, course outcomes, lesson plan, lecture notes, assignments, question papers and results. The course files of the Department of Electronics Engineering (VLSI Design and Technology) are listed below, semester by semester. Search for a subject by its name, code or faculty member.</p>'
  WHERE page_key = 'department.ece.course' AND section_key = 'main' AND updated_by IS NULL
    AND body_html = '<p>Updated Soon&#8230;</p>';
UPDATE page_section SET body_html = '<p>The course file is the complete teaching record of a subject: syllabus, course outcomes, lesson plan, lecture notes, assignments, question papers and results. The course files of the Department of Electronics Engineering (VLSI Design and Technology), M.Tech. are listed below, semester by semester. Search for a subject by its name, code or faculty member.</p>'
  WHERE page_key = 'department.ecevlsi.course' AND section_key = 'main' AND updated_by IS NULL
    AND body_html = '<p>Updated Soon&#8230;</p>';
UPDATE page_section SET body_html = '<p>The course file is the complete teaching record of a subject: syllabus, course outcomes, lesson plan, lecture notes, assignments, question papers and results. The course files of the Department of Electrical &amp; Electronics Engineering are listed below, semester by semester. Search for a subject by its name, code or faculty member.</p>'
  WHERE page_key = 'department.eee.course' AND section_key = 'main' AND updated_by IS NULL
    AND body_html = '<p>Updated Soon&#8230;</p>';
UPDATE page_section SET body_html = '<p>The course file is the complete teaching record of a subject: syllabus, course outcomes, lesson plan, lecture notes, assignments, question papers and results. The course files of the Department of Food Processing are listed below, semester by semester. Search for a subject by its name, code or faculty member.</p>'
  WHERE page_key = 'department.fp.course' AND section_key = 'main' AND updated_by IS NULL
    AND body_html = '<p>Updated Soon&#8230;</p>';
UPDATE page_section SET body_html = '<p>The course file is the complete teaching record of a subject: syllabus, course outcomes, lesson plan, lecture notes, assignments, question papers and results. The course files of the Department of Manufacturing Engineering are listed below, semester by semester. Search for a subject by its name, code or faculty member.</p>'
  WHERE page_key = 'department.manufacturing.course' AND section_key = 'main' AND updated_by IS NULL
    AND body_html = '<p><strong>Updated Soon&#8230</strong></p>';
UPDATE page_section SET body_html = '<p>The course file is the complete teaching record of a subject: syllabus, course outcomes, lesson plan, lecture notes, assignments, question papers and results. The course files of the Department of Mechanical &amp; Smart Manufacturing are listed below, semester by semester. Search for a subject by its name, code or faculty member.</p>'
  WHERE page_key = 'department.mechandsmartmanufacturing.course' AND section_key = 'main' AND updated_by IS NULL
    AND body_html = '<p><strong>Updated Soon&#8230</strong></p>';
UPDATE page_section SET body_html = '<p>The course file is the complete teaching record of a subject: syllabus, course outcomes, lesson plan, lecture notes, assignments, question papers and results. The course files of the Department of Mechanical Engineering are listed below, semester by semester. Search for a subject by its name, code or faculty member.</p>'
  WHERE page_key = 'department.mechanical.course' AND section_key = 'main' AND updated_by IS NULL
    AND body_html = '<p><strong>Updated Soon&#8230</strong></p>';
