-- Site settings (findings §1.2 T1, step 3). Values = today's texts in the JSPs, so the pages look the same.
-- The pages keep these texts as fallback (<kec:setting> body), a NULL value shows the built-in text again.
-- Idempotent: INSERT IGNORE never overwrites a value changed in /manage/settings.
INSERT IGNORE INTO site_setting (setting_key, setting_value, value_type, area_code, description) VALUES
  ('site.title', 'KATIHAR ENGINEERING COLLEGE, KATIHAR', 'text', 'site', 'Browser tab title (all pages)'),
  ('college.name', 'Katihar Engineering College, Katihar', 'text', 'site', 'College name (page description, footer, fee structure heading)'),
  ('site.short_name', 'KEC Katihar', 'text', 'site', 'Short name (footer heading, title of shared links)'),
  ('contact.phone', '06452-239122', 'phone', 'site', 'College phone (footer, contact pages, grievance, refund policy)'),
  ('contact.email', 'principal@keck.ac.in', 'email', 'site', 'College e-mail (grievance, refund policy)'),
  ('principal.email', 'principalranjanakri84@gmail.com', 'email', 'site', 'Principal''s e-mail (Contact Us page linked in the footer)'),
  ('contact.address_line1', 'Hirdayganj', 'text', 'site', 'Footer address, line 2 (after the college name)'),
  ('contact.address_line2', 'Hajipur 854109', 'text', 'site', 'Footer address, line 3 (PIN: decision D9 pending)'),
  ('contact.address_line3', 'Bihar India', 'text', 'site', 'Footer address, line 4'),
  ('contact.pin', '854109', 'text', 'site', 'PIN code in the fee structure heading (decision D9 pending)'),
  ('footer.since', 'Since January, 2019', 'text', 'site', 'Footer: ''Since ...'' line'),
  ('footer.copyright', '&copy 2018 KEC Katihar', 'text', 'site', 'Footer: copyright line'),
  ('footer.credit', 'Designed & Developed by', 'text', 'site', 'Footer: text above the KEC Web Team button');
