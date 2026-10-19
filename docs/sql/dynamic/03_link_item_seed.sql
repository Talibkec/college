-- Menus and link lists (findings §1.2 T3, steps 4-5). Values = today's links in the JSPs.
-- Each list is seeded only if it has no rows yet (re-running never duplicates or overwrites entries).
-- top_bar, social, footer render byte-identical; the sidebars look identical (only whitespace differs);
-- main_menu is switched OFF (links.main_menu.enabled = 'no') until an Admin previews and switches it on.

SET @seed = (SELECT COUNT(*) = 0 FROM link_item WHERE list_code = 'top_bar');
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'top_bar', 'menu', 'Home', '/', 0, NULL, NULL, 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'top_bar', 'menu', 'Anti Ragging', '/academic/antiragging', 0, NULL, NULL, 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'top_bar', 'menu', 'RTI', '/rti/rtiact', 0, NULL, NULL, 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'top_bar', 'menu', 'AICTE', '/approval/aicteapproval', 1, NULL, NULL, 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'top_bar', 'menu', 'KEC Web Team', '/category/kecwebteam', 1, NULL, NULL, 50 FROM DUAL WHERE @seed;

SET @seed = (SELECT COUNT(*) = 0 FROM link_item WHERE list_code = 'social');
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'social', 'menu', 'Facebook', 'https://www.facebook.com/profile.php?id=61564963783682', 1, 'fab fa-facebook', 'text-gray-800 hover:text-blue-600', 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'social', 'menu', 'Twitter', 'https://x.com/keckatihar', 1, 'fab fa-twitter', 'text-gray-800 hover:text-blue-400', 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'social', 'menu', 'Instagram', 'https://www.instagram.com/keckatiharofficial/', 1, 'fab fa-instagram', 'text-gray-800 hover:text-pink-600', 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'social', 'menu', 'Whatsapp', 'https://whatsapp.com/channel/0029VaeIPrnFHWptfQRrpo0Q', 1, 'fab fa-whatsapp', 'text-gray-800 hover:text-green-500', 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'social', 'menu', 'Youtube', 'https://www.youtube.com/channel/UCJu8sFs4jAp6sZIPsa8Qs5g', 1, 'fab fa-youtube', 'text-gray-800 hover:text-red-600', 50 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'social', 'menu', 'Linkedin', 'https://www.linkedin.com/in/katihar-engineering-college-dstte-govt-of-bihar-327aaa326/', 1, 'fab fa-linkedin', 'text-gray-800 hover:text-blue-700', 60 FROM DUAL WHERE @seed;

SET @seed = (SELECT COUNT(*) = 0 FROM link_item WHERE list_code = 'footer');
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'footer', 'menu', 'Contact Us', '/legal/contactus', 0, NULL, NULL, 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'footer', 'menu', 'Privacy Policy', '/legal/privacypolicy', 0, NULL, NULL, 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'footer', 'menu', 'Product and Services', '/legal/productandservices', 0, NULL, NULL, 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'footer', 'menu', 'Terms and Conditions', '/legal/termandcondition', 0, NULL, NULL, 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'footer', 'menu', 'Refund/Cancellation Policy', '/legal/refundandcancellation', 0, NULL, NULL, 50 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'footer', 'menu', 'Fee Structure ', '/legal/feestructure', 0, NULL, NULL, 60 FROM DUAL WHERE @seed;

SET @seed = (SELECT COUNT(*) = 0 FROM link_item WHERE list_code = 'about_sidebar');
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'about_sidebar', 'menu', 'Principal''s Message', '/about/principal', 0, NULL, NULL, 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'about_sidebar', 'menu', 'Vision & Mission', '/about/vision', 0, NULL, NULL, 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'about_sidebar', 'menu', 'Allotment & Surrender Report', '/about/allotment', 0, NULL, NULL, 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'about_sidebar', 'menu', 'Affiliation', '/about/affiliation', 0, NULL, NULL, 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'about_sidebar', 'menu', 'Administration', '/about/administration', 0, NULL, NULL, 50 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'about_sidebar', 'menu', 'Support Staff', '/about/staff', 0, NULL, NULL, 60 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'about_sidebar', 'menu', 'History', '/about/history', 0, NULL, NULL, 70 FROM DUAL WHERE @seed;

SET @seed = (SELECT COUNT(*) = 0 FROM link_item WHERE list_code = 'academic_sidebar');
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'academic_sidebar', 'menu', 'UG Admission', '/academic/admission', 0, NULL, NULL, 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'academic_sidebar', 'menu', 'Academic Regulations', '/academic/regulations', 0, NULL, NULL, 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'academic_sidebar', 'menu', 'Academic Calender', '/academic/calender', 0, NULL, NULL, 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'academic_sidebar', 'menu', 'List of Holidays', '/academic/holidays', 0, NULL, NULL, 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'academic_sidebar', 'menu', 'Attendance', '/academic/attendance', 0, NULL, NULL, 50 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'academic_sidebar', 'menu', 'Notice From Govt.', '/academic/notice', 0, NULL, NULL, 60 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'academic_sidebar', 'menu', 'Disciplinary Rules', '/academic/rules', 0, NULL, NULL, 70 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'academic_sidebar', 'menu', 'MoM of Academic Council', '/academic/academiccouncil', 0, NULL, NULL, 80 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'academic_sidebar', 'menu', 'Anti Ragging', '/academic/antiragging', 0, NULL, NULL, 90 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'academic_sidebar', 'menu', 'University Result', '/academic/universityresults', 0, NULL, NULL, 100 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'academic_sidebar', 'menu', 'Mentors List', '/academic/mentors', 0, NULL, NULL, 110 FROM DUAL WHERE @seed;

SET @seed = (SELECT COUNT(*) = 0 FROM link_item WHERE list_code = 'facilities_sidebar');
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'facilities_sidebar', 'menu', 'Bank', '/facilities/bank', 0, NULL, NULL, 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'facilities_sidebar', 'menu', 'Computer Center', '/facilities/csecenter', 0, NULL, NULL, 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'facilities_sidebar', 'menu', 'Central Library', '/facilities/centrallibrary', 0, NULL, NULL, 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'facilities_sidebar', 'menu', 'Hostels', '/facilities/hostels', 0, NULL, NULL, 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'facilities_sidebar', 'menu', 'Sports Facilities', '/facilities/sportsfacilities', 0, NULL, NULL, 50 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'facilities_sidebar', 'menu', 'Clubs', '/facilities/clubs', 0, NULL, NULL, 60 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'facilities_sidebar', 'menu', 'Health Center', '/facilities/healthcenter', 0, NULL, NULL, 70 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'facilities_sidebar', 'menu', 'Wi Fi', '/facilities/wifi', 0, NULL, NULL, 80 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'facilities_sidebar', 'menu', 'Medical Facilities', '/facilities/medicalfacilities', 0, NULL, NULL, 90 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'facilities_sidebar', 'menu', 'Startup Cell', '/facilities/startupcell', 0, NULL, NULL, 100 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'facilities_sidebar', 'menu', 'Gymnasium', '/facilities/gymnasium', 0, NULL, NULL, 110 FROM DUAL WHERE @seed;

SET @seed = (SELECT COUNT(*) = 0 FROM link_item WHERE list_code = 'tp_sidebar');
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'tp_sidebar', 'menu', 'About Placement', '/trainingplacement/aboutplacement', 0, NULL, NULL, 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'tp_sidebar', 'menu', 'Rules of Training & Placement', '/trainingplacement/rulesoftrainingplacement', 0, NULL, NULL, 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'tp_sidebar', 'menu', 'Tips for Resume', '/trainingplacement/tipsforresume', 0, NULL, NULL, 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'tp_sidebar', 'menu', 'Placed Students', '/trainingplacement/placedstudent', 0, NULL, NULL, 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'tp_sidebar', 'menu', 'Placement Brochure', '/trainingplacement/placementbrochure', 0, NULL, NULL, 50 FROM DUAL WHERE @seed;

SET @seed = (SELECT COUNT(*) = 0 FROM link_item WHERE list_code = 'approval_sidebar');
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'approval_sidebar', 'menu', 'AICTE Approval', '/approval/aicteapproval', 0, NULL, NULL, 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'approval_sidebar', 'menu', 'AKU Approval', '/approval/akuapproval', 0, NULL, NULL, 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'approval_sidebar', 'menu', 'Grievance', '/approval/grievance', 0, NULL, NULL, 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'approval_sidebar', 'menu', 'Mandatory Disclosure', '/approval/mandatorydisclosure', 0, NULL, NULL, 40 FROM DUAL WHERE @seed;

SET @seed = (SELECT COUNT(*) = 0 FROM link_item WHERE list_code = 'rti_sidebar');
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'rti_sidebar', 'menu', 'RTI Application Form', '/rti/rtiapplication', 0, NULL, NULL, 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'rti_sidebar', 'menu', 'RTI Status', '/rti/rtistatus', 0, NULL, NULL, 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'rti_sidebar', 'menu', 'Public Information Cell', '/rti/publicinformationcell', 0, NULL, NULL, 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, open_new_tab, icon_class, css_class, sort_order) SELECT 'rti_sidebar', 'menu', 'RTI Act', '/rti/rtiact', 0, NULL, NULL, 40 FROM DUAL WHERE @seed;

SET @seed = (SELECT COUNT(*) = 0 FROM link_item WHERE list_code = 'main_menu');
INSERT INTO link_item (list_code, area_code, label, url, sort_order) SELECT 'main_menu', 'menu', 'Home', '/', 10 FROM DUAL WHERE @seed;
SET @parent = LAST_INSERT_ID();
INSERT INTO link_item (list_code, area_code, label, url, sort_order) SELECT 'main_menu', 'menu', 'About KEC', '/about/history', 20 FROM DUAL WHERE @seed;
SET @parent = LAST_INSERT_ID();
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'History', '/about/history', 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Administration', '/about/administration', 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Principal''s Message', '/about/principal', 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Vision & Mission', '/about/vision', 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Affiliation', '/about/affiliation', 50 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Allotment & Surrender Report', '/about/allotment', 60 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, sort_order) SELECT 'main_menu', 'menu', 'Academic', '/academic/admission', 30 FROM DUAL WHERE @seed;
SET @parent = LAST_INSERT_ID();
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Admission', '/academic/admission', 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Academic Regulations', '/academic/regulations', 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Academic Calender', '/academic/calender', 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Computer Center Labs', '/academic/lab?id=1', 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'List of Holidays', '/academic/holidays', 50 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Attendance', '/academic/attendance', 60 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Syllabus', '/academic/syllabus', 70 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Disciplinary Rules', '/academic/rules', 80 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Online Fee Payment Tutorial', '/academic/paymenttutorial', 90 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Anti Ragging', '/academic/antiragging', 100 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'MoM of Academic Council', '/academic/academiccouncil', 110 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Notice from Govt.', '/academic/notice', 120 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, sort_order) SELECT 'main_menu', 'menu', 'Departments', '/category/department', 40 FROM DUAL WHERE @seed;
SET @parent = LAST_INSERT_ID();
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, 'Under Graduates', 'Civil Engineering', '/department/civil/about', 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, 'Under Graduates', 'Mechanical Engineering', '/department/mechanical/mech', 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, 'Under Graduates', 'Mechanical and Smart Manufacturing', '/department/mechandsmartmanufacturing/about', 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, 'Under Graduates', 'Electronics Engineering (VLSI Design and Technology)', '/department/ece/about', 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, 'Under Graduates', 'Computer Sc. & Engineering', '/department/cse/about', 50 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, 'Under Graduates', 'Electrical & Electronics Engg.', '/department/eee/about', 60 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, 'Under Graduates', 'Applied Sc. & Humanities', '/department/ash/about', 70 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, 'Under Graduates', 'Food Processing', '/department/fp/about', 80 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, 'Post Graduates', 'Electronics Engineering (VLSI Design and Technology)', '/department/ecevlsi/about', 90 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, 'Post Graduates', 'Manufacturing Engineering', '/department/manufacturing/about', 100 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, sort_order) SELECT 'main_menu', 'menu', 'Facilities', '/facilities/csecenter', 50 FROM DUAL WHERE @seed;
SET @parent = LAST_INSERT_ID();
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Bank', '/facilities/bank', 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Central Library', '/facilities/centrallibrary', 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Computer Centre', '/facilities/csecenter', 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Club', '/facilities/club', 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Guest House', '/facilities/guesthouse', 50 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Gymnasium', '/facilities/gymnasium', 60 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Health Centre', '/facilities/healthcenter', 70 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Hostels', '/facilities/hostels', 80 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Medical Facilities', '/facilities/medicalfacilities', 90 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Placement', '/facilities/placementbrochure', 100 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Sports Facilities', '/facilities/sportsfacilities', 110 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Startup cell', '/facilities/startupcell', 120 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Useful Link', '/facilities/usefulllink', 130 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Wi Fi', '/facilities/wifi', 140 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, sort_order) SELECT 'main_menu', 'menu', 'Training & Placement', '/trainingplacement/placementbrochure', 60 FROM DUAL WHERE @seed;
SET @parent = LAST_INSERT_ID();
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'About Placement', '/trainingplacement/aboutplacement', 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Placement Brouchure', '/trainingplacement/placementbrochure', 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Rules of training & Placement', '/trainingplacement/rulesoftrainingplacement', 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Tips for Resume', '/trainingplacement/tipsforresume', 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Placed Students', '/trainingplacement/placedstudent', 50 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Student Placement Coordinator', '/trainingplacement/studentplacementcoordinator', 60 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Placement Portal', 'https://tpo.keckatihar.in', 70 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, sort_order) SELECT 'main_menu', 'menu', 'Approval', '/approval/akuapproval', 70 FROM DUAL WHERE @seed;
SET @parent = LAST_INSERT_ID();
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'AICTE Aproval', '/approval/aicteapproval', 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'AKU Approval', '/approval/akuapproval', 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Grievance', '/approval/grievance', 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Mandatory Disclosure', '/approval/mandatorydisclosure', 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, sort_order) SELECT 'main_menu', 'menu', 'RTI', '/rti/rtiact', 80 FROM DUAL WHERE @seed;
SET @parent = LAST_INSERT_ID();
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'RTI Act 2005', '/rti/rtiact', 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'RTI Application Form', '/rti/rtiapplication', 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'RTI Status', '/rti/rtistatus', 30 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Public Information Cell', '/rti/publicinformationcell', 40 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, sort_order) SELECT 'main_menu', 'menu', 'Gallery', '/gallery/mediagallery', 90 FROM DUAL WHERE @seed;
SET @parent = LAST_INSERT_ID();
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'photo Gallery', '/gallery/photogallery', 10 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, parent_id, group_label, label, url, sort_order) SELECT 'main_menu', 'menu', @parent, NULL, 'Media Gallery', '/gallery/mediagallery', 20 FROM DUAL WHERE @seed;
INSERT INTO link_item (list_code, area_code, label, url, sort_order) SELECT 'main_menu', 'menu', 'NBA', '/nba/nba', 100 FROM DUAL WHERE @seed;
SET @parent = LAST_INSERT_ID();
INSERT INTO link_item (list_code, area_code, label, url, sort_order) SELECT 'main_menu', 'menu', 'Downloads', '/facilities/downloads', 110 FROM DUAL WHERE @seed;
SET @parent = LAST_INSERT_ID();

-- main menu stays as it is in the page until an Admin switches it on in /manage/links (decision B2: the mobile
-- menu then shows the same entries as the desktop menu).
INSERT IGNORE INTO site_setting (setting_key, setting_value, value_type, area_code, description) VALUES
  ('links.main_menu.enabled', 'no', 'switch', 'menu', 'Show ''Main menu (desktop, mobile and side menu)'' from the database');
