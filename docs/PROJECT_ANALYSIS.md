# Project Analysis – KEC College Web Application

_Phase 1 analysis, 2026-10-15, branch `kec_claude`. No code was changed for this document._
_Credentials and personal contact data are deliberately **not** reproduced here; files are referenced instead._

---

## 1. What the application does

This is the public website **and** a small ERP for **Katihar Engineering College (KEC), Katihar, Bihar**. It consists of:

* **Public website**: home page with image/achievement sliders and a scrolling notice board, plus About, Academic, nine department mini-sites, Facilities, Training & Placement, Approvals (AICTE/AKU), NBA, RTI, Gallery, Legal/fee pages, a grievance form and the "KEC Web Team" page.
* **Content management** behind login: notices/tenders/news, slider and gallery images, downloadable documents (AICTE, downloads, NBA, holidays, lab docs, responsibility docs), faculty/staff profiles and documents, administration roles, placements, TPO coordinators, course seats, HOD assignment, and bulk SMS.
* **Faculty self-service**: profile editing, profile photo, documents, uploading notices and slides, and password reset by e-mail.
* **Attendance/leave integration** (with an external mobile app via Firebase): attendance-report PDF+CSV by mail, leave-application PDF by mail, and leave-status mail plus a Google Calendar event.
* **Store management** (inventory, purchases, faculty product requests). **Out of scope – untouched** (see §5).

### User roles (table `test_role`, join table `user_role`)

| Role | Users (local copy) | Landing page after login | Access (from `WebSecurityConfig`) |
|---|---|---|---|
| `Admin` | 3 | `/auth/uploadDashboard` | `/auth/**`, `/uploadfile/**` |
| `HOD` | 19 | `/hod/hodDashboard` | `/hod/**`, `/auth/uploadLabDoc/**`, `/auth/api/uploadLabDoc/**`, `/uploadfile/**` |
| `Faculty` | 49 | `/facultyDetails?facultyId=<own id>` | `/user/**`, `/fstore/**`, `/common/**`, `/uploadfile/**` |
| `SM` (store manager) | 1 | `/store/smdashboard` | `/store/**`, `/sk/**`, `/common/**`, `/uploadfile/**` |
| `SK` (store keeper) | 1 | `/sk/storekeeper` | `/sk/**`, `/uploadfile/**` |
| anonymous | – | – | everything else (there is **no** `anyRequest().authenticated()`) |
| `CHANGE_PASSWORD_PRIVILEGE` (temporary, set during password reset) | – | – | `/user/updatePassword*`, `/user/savePassword*`, `/updatePassword*` |

All 53 stored passwords are BCrypt (`$2a$`).

### Architecture

* A single Maven **WAR** module. Package `com.college` plus a stray top-level package `firebase.bean`.
* Spring MVC controllers return `ModelAndView`s with **JSP** views (`src/main/webapp/jsp/**`, resolved by a custom `InternalResourceViewResolver` with prefix `/jsp/` and no suffix). Thymeleaf is on the classpath but there are **no Thymeleaf templates** (the `/thymeleaf` → `sample.html` endpoint is broken).
* Layers: `core/controller` → `service` (interface + `*Impl`) → `repository` (Spring Data JPA) → `core/entity`. DTOs live in `core/model` and are mapped with ModelMapper.
* Files (notices, images, PDFs) are stored as **BLOBs** (`mediumblob`) in MySQL and streamed by controllers.
* `Application` uses `@EnableAutoConfiguration(exclude = MultipartAutoConfiguration)` with a `CommonsMultipartResolver` bean, plus `@EnableScheduling` (password-token purge, daily at 05:00) and `@EnableCaching` (home-page images).
* Embedded Tomcat via `spring-boot:run`. The WAR is deployable to an external Tomcat (`SpringBootServletInitializer`).
* Static assets live under `src/main/webapp/{css,js,images,sites,wp-content,modules,misc}`, kept from a former WordPress/Drupal site. Many legacy URLs are preserved.

---

## 2. Tech stack – current versions (resolved by Maven)

| Area | Artifact | Version |
|---|---|---|
| Runtime | Java | 8 (compiler source/target 8) |
| Boot | spring-boot-starter-parent | **1.3.3.RELEASE** |
| Spring | spring-core / spring-webmvc | 4.2.5.RELEASE |
| Security | spring-security-core/web/config | 4.0.3.RELEASE |
| Security taglibs | spring-security-taglibs | **5.2.2.RELEASE** (pinned, mismatched with core 4.0.3) |
| Data | spring-data-jpa | 1.9.4.RELEASE |
| ORM | hibernate-core / entitymanager | 4.3.11.Final |
| Validation | hibernate-validator | 5.2.4.Final |
| Servlet container | tomcat-embed-core / jasper | **8.0.3** (forced by the `tomcat.version` override, `provided` scope) |
| Views | javax.servlet:jstl | 1.2 |
| Views | thymeleaf / thymeleaf-spring4 | 2.1.4.RELEASE (unused) |
| JSON | jackson-databind | 2.6.5 |
| JSON | gson | 2.8.2 |
| Upload | commons-fileupload | 1.3.3 |
| DB driver | mysql-connector-java → relocated to com.mysql:mysql-connector-j | 8.0.33 (`com.mysql.jdbc.Driver` class name, deprecated) |
| DB | MySQL server (local) | 8.0.46, `lower_case_table_names=0` |
| Mapping | modelmapper | 2.3.0 |
| Backup | com.smattme:mysql-backup4j | 1.0.1 |
| Mail | spring-boot-starter-mail (javax.mail 1.5.5, plus a stray javax.mail:mail 1.5.0-b01) | – |
| Firebase | firebase-admin | 6.11.0 |
| Google | google-api-client | 1.23.0 |
| Google | google-api-services-calendar | v3-rev305-1.23.0 |
| Google | google-oauth-client (+ -jetty, -java6) | 1.23.0 |
| Google | google-http-client-jackson (**Jackson 1**) / -jackson2 | 1.19.0 |
| PDF | com.lowagie:itext 4.2.2 → **relocation to com.itextpdf:itextpdf 5.5.6 (AGPL)** | 5.5.6 |
| PDF | com.itextpdf:kernel / layout (iText 7, AGPL) | 7.0.2. **Declared but not imported anywhere.** |
| XML | javax.xml.bind:jaxb-api | 2.3.1 |
| Front-end | org.webjars:jquery | 2.2.4. **Not referenced anywhere.** Pages load jQuery/Bootstrap from CDNs and from local `js/`. |
| Logging | logback-classic / slf4j | 1.1.5 / 1.7.16 |
| Build | Maven | 3.6.3 (no wrapper) |

---

## 3. Complete functionality inventory

Abbreviations. **C** = controller (in `com.college.core.controller`). **V** = view under `webapp/jsp/`. **anon** = no login required.
External: **MAIL** = SMTP, **FB** = Firebase, **GCAL** = Google Calendar, **PDF** = iText, **UP** = multipart upload, **SMS** = Textlocal HTTP API.
"⚠ broken today" marks endpoints that already fail on the current version (the view JSP doesn't exist, etc.). These are baseline facts, not upgrade regressions.

### 3.1 Public website

| Module | Feature | URL(s) → controller method | Roles | Templates | DB tables | External |
|---|---|---|---|---|---|---|
| Home | Home page: slider, achievements, notices, scrolling notices | `/`, `/welcome` → `UserController.welcome` | anon | `index.jsp`, `header.jsp`, `footer.jsp` | noticeboard, image_slide, achievement_slide | – |
| Home | Alternate home | `/2`, `/welcome2` → `UserController.welcome2` | anon | `index2.jsp` ⚠ missing | same | – |
| Home | Cached static home image | `/homePageImage/{imageName}/{imageType}` → `DashboardController.image` | anon | – (reads `/sites/default/files/compressed/*`) | – | cache |
| Home | Slide image bytes | `/{id}/slideImage` → `UserController.getimages` | anon | – | image_slide | – |
| Home | Achievement image bytes / list | `/{id}/achievementImage` → `UserController.getachievementImage`; `/api/achievements/flag/{flag}` → `AchievementImageSlider.getAchievementImagesByFlag` | anon | – | achievement_slide | – |
| About | History, Vision, Principal, Allotment, Affiliation | `/about/{history,vision,principal,allotment,affiliation}` → `AboutController.get*` | anon | `about/*.jsp` | – | – |
| About | Administration (roles list + responsibility docs) | `/about/administration` → `AboutController.getResponsibilityDoc` | anon (delete links for Admin) | `about/administration.jsp` | administration, faculty, responsibility_doc | – |
| About | Staff list / delete | `/about/staff` → `getStaff`; `/about/deleteStaff?staffId=` → `deleteStaff` (manual role check) | anon / Admin | `about/staff.jsp` | staff | – |
| Academic | Admission (course seats) | `/academic/admission` → `CourseController.getCourses` (GET). `AcademicController.getAdmission` has the same path. | anon | `academic/admission.jsp` | no_of_seat | – |
| Academic | Regulations, Calendar, Attendance, Notice (govt), Payment tutorial, Rules, Academic council, Anti-ragging, Syllabus, University results, Mentors, adminsValue | `/academic/{regulations,calender,attendance,notice,paymenttutorial,rules,academiccouncil,antiragging,syllabus,universityresults,mentors,adminsValue}` → `AcademicController.get*` | anon | `academic/*.jsp` | – | – |
| Academic | Holidays list | `/academic/holidays` → `AcademicController.getHolidays` | anon (delete for Admin/HOD) | `academic/holidays.jsp` | holiday | – |
| Academic | Lab documents per dept | `/academic/lab?dept_id=` → `AcademicController.getLabDocuments`. ⚠ The header menu links `/academic/lab?id=1` (wrong parameter name → 400). | anon | `academic/labs.jsp` | lab_document | – |
| Departments | 9 department sites: about, vision, students, notice, labs, question, awards, programs, weekly, course, attendance (+faculty/staff/principal where present) | `/department/{cse,civil,mechanical,ece,eee,ecevlsi,ash,fp,manufacturing,mechandsmartmanufacturing}/*` → `CseController`, `CivilController`, `MechanicalController` (about is `/department/mechanical/mech`), `EceController`, `EeeController`, `EceVlsiController`, `AppliedController` (ash), `FPController`, `ManufacturingController`, `MechanicalAndSmartManufacturingController` | anon | `department/<dept>/*.jsp`, `sidebar.jsp` | noticeboard (dept notices), faculty | – |
| Departments | Per-faculty profile shortcuts (e.g. `/department/cse/dky`, `/department/ece/rk`, …, ~75 URLs) | `*Controller.get<Initials>` → `FacultyHelper.getFacultyDetails(username)` | anon | ⚠ **target JSPs don't exist**: broken today | faculty | – |
| Departments | Some about/attendance/staff pages | `/department/ash/attendance`, `/department/{manufacturing,mechanical,mechandsmartmanufacturing}/staff`, `/department/ash/{pk,vk,bnm,snk}` | anon | ⚠ JSP missing | – | – |
| Notices | All notices / tenders + paging | `/category/notices`, `/category/tenders` → `notice.getNotices/getTenders`; `/pagination/{next,prev}/getNotices?deptno=&pageSize=`, `/pagination/{next,prev}/getTenders?pageSize=` → `UserController` | anon | `category/notices.jsp`, `category/tenders.jsp`, `department/*/notice.jsp` | noticeboard | – |
| Notices | Download notice file | `/notice/{id}` → `UserController.getdocuments` | anon | – | noticeboard | – |
| Faculty | Faculty list per department | `/faculty?deptno=` → `CommonResourceController.faculty` (in the store package) | anon | `facultylist.jsp` (scriptlets) | faculty, department | – |
| Faculty | Faculty profile | `/facultyDetails?facultyId=` → `CommonResourceController` | anon (edit for owner/Admin/HOD) | `facultydetails.jsp` | faculty, faculty_key_props, faculty_keyprop_values, facultydocuments | – |
| Faculty | Faculty photo / documents bytes | `/{id}/image`, `/{id}/documents` → `FileUploadFacultycontroller` | anon | – | faculty, facultydocuments | – |
| Facilities | Bank, Central library, Club, Contact us, Computer centre, Guest house, Gym, Health centre, Hostels, Placement brochure, Sports, Startup cell, Useful links, Wi-Fi, Medical | `/facilities/*` → `FacilitiesController.get*` | anon | `facilities/*.jsp` | – | – |
| Facilities | Downloads list / file / delete | `/facilities/downloads`, `/facilities/document/{id}`, `/facilities/deletedocument/{id}` (manual role check) | anon / Admin | `facilities/downloads.jsp` | download (doc_type='download') | – |
| T&P | About, rules, tips for resume, brochure, student coordinators | `/trainingplacement/{aboutplacement,rulesoftrainingplacement,tipsforresume,placementbrochure}` → `TrainingPlacementController`; `/trainingplacement/studentplacementcoordinator` → `CommonResourceController` | anon | `trainingplacement/*.jsp` | tpo_coordinator | – |
| T&P | Placed students list / delete | `/trainingplacement/placedstudent`, `/trainingplacement/deletePlacement?placementId=` (manual role check) | anon / Admin | `trainingplacement/placedstudent.jsp` | placements | – |
| Approvals | AKU approval, Mandatory disclosure | `/approval/akuapproval`, `/approval/mandatorydisclosure` → `ApprovalController` | anon | `approval/*.jsp` | – | – |
| Approvals | AICTE approval list / file / delete | `/approval/aicteapproval`, `/approval/document/{id}`, `/approval/deletedocument/{id}` | anon / Admin | `approval/aicteapproval.jsp` | aicte_documents | – |
| Docs | Holiday / Lab / Responsibility doc file + delete | `/approval/holiday/{id}`, `/approval/deleteHoliday/{id}`, `/approval/lab/{id}`, `/approval/deleteLab/{id}`, `/approval/responsibilityDoc/{id}`, `/approval/deleteResponsibilityDoc/{id}` (manual role checks) | anon / Admin, HOD | – | holiday, lab_document, responsibility_doc | – |
| Grievance | Grievance form → mail to principal | `/approval/grievance`, `/approval/raisequery` (params `name,email,phone,query,registration`) | anon | `approval/grievance.jsp` | – | MAIL |
| NBA | NBA documents | `/nba/nba` → `NBAController.getNBA` | anon | `nba/nba.jsp` | download (doc_type='nba') | – |
| RTI | Public info cell, RTI Act, Application, Status | `/rti/*` → `RtiController` | anon | `rti/*.jsp` | – | – |
| RTI | Legacy WordPress URL | `/wp-content/themes/kec/rti-kec/rti-act` → `DashboardController.approvalkec` | anon | ⚠ view `rti-kec/rti-act` missing | – | – |
| Gallery | Photo gallery (static list), Media gallery | `/gallery/photogallery`, `/gallery/mediagallery` → `GalleryController` | anon | `gallery/*.jsp` (scriptlets) | – | – |
| Gallery | Gallery image bytes | `/{id}/galleryImage` → `UserController.getimage` | anon | – | gallery_image | – |
| Legal | Contact, Privacy, Fee structure, T&C, Products & services, Refund | `/legal/{contactus,privacypolicy,feestructure,termandcondition,productandservices,refundandcancellation}` → `LegalController` | anon | `*.jsp` at jsp root | – | – |
| Misc | KEC web team, Departments index | `/category/kecwebteam`, `/category/department` → `notice` | anon | `category/*.jsp` | – | – |
| Misc | Thymeleaf demo | `/thymeleaf?name=` → `UserController.thymleaf` | anon | ⚠ `sample.html` missing | – | – |
| Misc | Test exception | `/exception` → `DashboardController.testException` | anon | – | – | – |
| Errors | Error page | `/error` → `CustomErrorController` | – | `error-404.jsp`, `error-403.jsp` | – | – |

### 3.2 Authentication & accounts

| Feature | URL(s) → method | Roles | Templates | DB tables | External |
|---|---|---|---|---|---|
| Login form / processing (Spring Security form login, params `username`/`password`) | GET `/login` → `UserController.login`; POST `/login` (Security filter) → `KECUrlAuthenticationSuccessHandler` role redirect | anon | `login.jsp` | test_user, test_role, user_role | – |
| Logout | `/logout` → `/` | any | – | – | – |
| User registration | GET/POST `/auth/registration` → `UserController.registration` (`UserValidator`) | Admin | `registration.jsp` | test_user, user_role | – |
| Forgot password page | `/forgotPassword` | anon | `forgotPassword.jsp` | – | – |
| Send reset link | POST `/user/resetPassword` (param `email`) → `PasswordResetController.resetPassword` | anon (permitAll) | – | faculty, password_reset_token | MAIL |
| Open reset link | GET `/user/changePassword?id=&token=` → sets `CHANGE_PASSWORD_PRIVILEGE` in the security context → redirect `/jsp/updatePassword.jsp` | anon | `updatePassword.jsp` | password_reset_token | – |
| Save new password | POST `/jsp/user/savePassword` (param `newPassword`) | session from previous step | – | test_user | – |
| Purge expired tokens | `TokensPurgeTask.purgeExpired` (`@Scheduled`, `purge.cron.expression` = 05:00 daily) | – | – | password_reset_token | – |
| Assign HOD role | `/auth/hodincharge` (page), POST `/auth/hod/saveRole` (param `facultyDetails` JSON) → `DashboardController` | Admin | `hodincharge.jsp` | user_role, department | – |

### 3.3 Admin (`/auth/**`)

| Feature | URL(s) → method | Templates | DB tables | External |
|---|---|---|---|---|
| Admin dashboard | `/auth/uploadDashboard` → `AdminController.getUploadDashboard` | `admin/UploadDashboard.jsp`, `admin/HeaderDashboard.jsp` | – | – |
| Upload notice / tender / news | GET `/auth/uploadfile/upload` → `DashboardController`; POST `/auth/api/upload` (params `noticeFile,noticeHeader,noticeType,link,isScrollable,date`) → `FileUploadCintroller.uploadFile` | `uploadfile.jsp` | noticeboard | UP |
| Delete notice | `/auth/delNotice/{id}` | – | noticeboard | – |
| Slider images | GET `/auth/uploadfile/uploadslideimage`, POST `/auth/api/uploadslideimage` → `SlideImageController`; delete `/auth/deleteSlideImage/{id}` | `uploadslideimage.jsp` | image_slide | UP |
| Achievement images | GET `/auth/uploadfile/achievementslideimage`, POST `/auth/api/achievementSlideImage` → `AchievementImageSlider`; delete `/auth/deleteAchievementImage/{id}` | `uploadAchievementImage.jsp` | achievement_slide | UP |
| Gallery images | GET `/auth/uploadfile/uploadgalleryimage`, POST `/auth/api/uploadgalleryimage` → `SlideImageController.uploadFiles`; delete `/auth/galleryImageDelete/{id}` | `uploadGalleryImage.jsp` | gallery_image | UP |
| AICTE approval docs | GET `/auth/uploadAICTEapproval`, POST `/auth/api/uploadAICTEapproval` | `uploadAICTEapproval.jsp` | aicte_documents | UP |
| Downloads | GET `/auth/uploadDownload`, POST `/auth/api/uploadDownload` | `uploadDownload.jsp` | download | UP |
| NBA docs | GET `/auth/nba/uploadNBA`, POST `/auth/api/nba/uploadNBA` → `NBAController` | `nba/uploadNBA.jsp` | download | UP |
| Responsibility docs | GET `/auth/uploadResponsibilityDoc`, POST `/auth/api/uploadResponsibilityDoc` | `uploadResponsibilityDoc.jsp` | responsibility_doc | UP |
| Holidays | GET `/auth/uploadHoliday`, POST `/auth/api/uploadHoliday` | `uploadHoliday.jsp` | holiday | UP |
| Lab docs (Admin + HOD) | GET `/auth/uploadLabDoc`, POST `/auth/api/uploadLabDoc` | `admin/uploadLabDoc.jsp` | lab_document | UP |
| Administration roles | GET `/auth/uploadfile/updateAdministration`; POST **`/api/uploadAdministration`** (⚠ not under `/auth`, so anonymous) → `AdministrationController`; delete `/auth/deleteAdminRole/{id}` | `updateAdministration.jsp` | administration, faculty | – |
| Add faculty | GET/POST `/auth/uploadfile/addfaculty` → `CommonResourceController` | `addfaculty.jsp` | faculty, test_user, user_role | UP (photo) |
| Add staff | GET/POST `/auth/uploadfile/addstaff` → `CommonResourceController` | `addstaff.jsp` | staff, test_user | UP |
| Placements | `/auth/addPlacement` → `AdminController`; GET/POST `/auth/uploadfile/addPlacements` → `CommonResourceController` | `addPlacement.jsp`, `addPlacements.jsp` | placements | – |
| TPO coordinators | GET/POST `/auth/uploadfile/addtpocoordinator`; delete `/auth/tpoCoordinator/{id}` | `addtpocoordinator.jsp` | tpo_coordinator | – |
| Course seats | `/auth/course`, POST `/auth/uploadfile/coursecreator`, delete `/auth/course/{id}` (GET) → `CourseController` | `coursecreator.jsp` | no_of_seat | – |
| Bulk SMS | GET/POST `/auth/sendMessage` (params `smsGroup`, message, number) → `DashboardController` + `MessageSender` | `sms.jsp` | – (`SMS.properties` lists) | SMS |
| Legacy admin URL | `/wp-content/themes/kec/admin/homepage` | ⚠ view `admin/adminhomepage` missing | – | – |
| Generic dashboard | `/dashboard` | ⚠ `dashboard.jsp` missing | – | – |

### 3.4 HOD (`/hod/**`)

| Feature | URL(s) → method | Templates | DB tables | External |
|---|---|---|---|---|
| HOD dashboard | `/hod/hodDashboard` → `AdminController` | `admin/HodDashboard.jsp` | – | – |
| Upload dept notice | GET `/hod/uploadfile/upload`; POST `/hod/api/upload` → `HODFileUploadController` | `HODFileUpload.jsp` | noticeboard | UP |
| Delete notice (+ file on disk) | `/hod/{fileName}/{id}` → `UserController.deleteItem` | – | noticeboard | disk |
| Add faculty to dept | `/hod/addfaculty`, POST `/hod/uploadfile/addfaculty` → `AdminController.addFaculty` | `admin/HodAddFaculty.jsp` | faculty, test_user, user_role | UP |
| Lab docs | see Admin | | | |
| Delete faculty | `/deleteFaculty?facultyId=&deptno=` → `CommonResourceController` (manual role/dept check) | – | faculty | – |

### 3.5 Faculty (`/user/**`, `/uploadfile/**`)

| Feature | URL(s) → method | Templates | DB tables | External |
|---|---|---|---|---|
| Own profile (landing) | `/facultyDetails?facultyId=` | `facultydetails.jsp` | faculty, … | – |
| Faculty dashboard | `/user/dashboard` → `UserDashboard` | `user/dashboard.jsp`, `user/HeaderDashboard.jsp` | – | – |
| Edit profile / key properties | GET/POST `/uploadfile/editfacultydetails` → `CommonResourceController` | `editfacultydetails.jsp` | faculty, faculty_key_props, faculty_keyprop_values | – |
| Upload document / profile photo | GET `/uploadfile/facultyFileUpload?isProfilePic=`, POST `/uploadfile/facultyFileUpload` | `facultyfileupload.jsp` | faculty, facultydocuments | UP |
| Delete own document | `/common/deleteFacultyDoc/{id}` → `FileUploadFacultycontroller` | – | facultydocuments | – |
| Upload notice / slide | GET `/user/uploadfile/upload`, POST `/user/api/upload`; GET `/user/uploadfile/uploadslideimage`, POST `/user/api/uploadslideimage` → `UserDashboard` | `uploadfileByFaculty.jsp`, `uploadslideimageByFaculty.jsp` | noticeboard, image_slide | UP |
| Other upload pages | `/user/{addPlacement,uploadAICTEapproval,uploadDownload,uploadResponsibilityDoc,uploadHoliday}`, `/uploadNoticeFaculty` | various | – | – |
| Autocomplete helpers | `/user/userNameAutocomplete`, `/faculty/facultyNameAutocompletes`, `/faculty/facultyNameAutocompletes123`, `/faculty/searchFacultyNames` → `CommonResourceController` | – (JSON) | test_user, faculty | – |

### 3.6 Firebase attendance / leave integration (called by the external attendance app)

| Feature | URL → method | Roles | DB / data | External |
|---|---|---|---|---|
| Attendance report: reads Firebase RTDB `attendance` between dates, builds PDF + CSV, mails it | GET `/rn?params=<json>` → `firebase.FacultyGenerateReport` (+ `FirebaseDocumentHelper`, `DocUtils`) | ⚠ anon | Firebase RTDB | FB, PDF, MAIL. The recipient is hard-coded to a personal address, not the faculty member. |
| Leave application PDF by mail | GET `/rl?params=<json>` → `firebase.LeaveMail` | ⚠ anon | – | PDF, MAIL |
| Leave status mail + calendar event | GET `/rs?params=<json>` → `firebase.LeaveStatusMail` (+ `CalendarService`) | ⚠ anon | – | MAIL, GCAL (service account, P12 key) |
| Firebase init | `firebase.bean.FirebaseSetup` `@PostConstruct` (bean in `BeanConfig`) | – | `kecattendance-account-info.json` | FB |

### 3.7 DB backup

| Feature | Where | Status |
|---|---|---|
| MySQL dump by mail | `com.college.backup.KECDBBackup` (mysql-backup4j) | **Not wired.** It isn't a Spring bean and has no `@Scheduled`; it only runs through its own `main()`. It has hard-coded DB name, user, password and SMTP credentials. |

### 3.8 Cross-cutting

* `CustomExceptionHandler` (`@ControllerAdvice`) catches **every** exception and returns `void`, so most errors become an empty **HTTP 200** page. Tests must assert content, not just status.
* `WebConfig` registers a `ByteArrayHttpMessageConverter` for jpeg/png/gif/pdf.
* Messages: `messages_en*.properties`, `validation*.properties`.

---

## 4. Database

Tables in `keckacin_college2` (approx. rows): achievement_slide 4, administration 26, aicte_documents 1, department 7, download 5, faculty 46, faculty_key_props 132, faculty_keyprop_values 453, faculty_property 0, facultydocuments 239, gallery_image 0, holiday 0, image_slide 22, lab_document 6, no_of_seat 0, noticeboard 805, order_college 17, password_reset_token 3, placements 0, product 1, property 0, purchase 2, request 1, responsibility_doc 0, staff 2, test_role 5, test_user 53, tpo_coordinator 0, user_role 73.

* **Naming:** Hibernate 4 / Boot 1.3 `SpringNamingStrategy` produces lower_snake_case (`@Table(name="FACULTY")` → `faculty`, `TpoCoordinator` → `tpo_coordinator`, `keyPropertyId` → `key_property_id`). Boot 2+'s `CamelCaseToUnderscoresNamingStrategy` gives the same result. It must stay that way.
* **IDs:** every entity uses `GenerationType.AUTO`, and every table has `AUTO_INCREMENT`. Hibernate 4 maps AUTO to IDENTITY on MySQL. Hibernate 5+ would switch to a `hibernate_sequence` table, so IDs must be pinned to `IDENTITY` (pre-approved in CLAUDE.md). Several entities use `generator = "xxxId"` with no matching generator definition, which newer Hibernate rejects.
* **`spring.jpa.hibernate.ddl-auto=update` is active today.** At every startup Hibernate tries to `ALTER` the production schema (e.g. it currently fails to add FK `staff.username → test_user`). CLAUDE.md requires `none`/`validate`. **`validate` would fail** (e.g. `faculty_keyprop_values.key_value_order` is `decimal(3,0)` but mapped as `Integer`). **Proposal: `none`**. This needs your confirmation.
* `lower_case_table_names=0`, so native queries using `Faculty`/`Product` (capitalised) fail on this server. They're unused, except `ProductRepository` (store).

---

## 5. Store management – OUT OF SCOPE, UNTOUCHED

URLs: `/store/**` (SM), `/sk/**` (SK, SM), `/fstore/faculty/**` (Faculty), `/common/store/**` (SM, Faculty).

| Layer | Files |
|---|---|
| Controllers | `core/controller/StoreController.java`, `core/controller/store/ProductController.java`, `StoreFacultyController.java`, `StoreKeeperController.java`, `RequestOrderTransformer.java`, and the `/common/store/*` endpoints in `CommonResourceController.java` |
| Entities | `core/entity/Product.java`, `Purchase.java`, `Order.java`, `Request.java` |
| Models | `core/model/Product.java`, `ProductDTO`, `ProductDetails`, `ProductAttribute`, `PurchaseDTO`, `OrderDTO`, `CustomerOrder`, `Customer`, `RequestDTO` |
| Repos / services | `ProductRepository`, `PurchaseRepository`, `OrderRepository`, `RequestRepository`; `ProductService(Impl)`, `PurchaseService(Impl)`, `OrderService(Impl)`, `RequestService(Impl)`; `ProductTransformer` |
| Views / JS | `jsp/store/**` (17 JSPs), `js/store/**` |
| Config | `employee.properties` (used by `StoreFacultyController`) |
| Tables | product, purchase, order_college, request |

**⚠ Boundary problem:** `core/controller/store/CommonResourceController.java` sits in the store package but also hosts **core, non-store features**:
- `/facultyDetails`, which is the Faculty login landing page
- `/faculty?deptno=`
- edit faculty details and faculty file upload
- add faculty, staff, placements and TPO coordinators
- delete faculty
- the student placement coordinator page
- the faculty autocomplete endpoints

Also, the core `UserServiceImpl` uses the store's `ProductTransformer`. These files can't simply be left alone during the jakarta/Boot 3+ stages, because they won't compile. See the decision request at the end.

---

## 6. Hard-coded content that should become dynamic (later phase – list only)

| Where | What |
|---|---|
| `header.jsp`, `header2.jsp`, `admin/HeaderDashboard.jsp`, `user/HeaderDashboard.jsp` | The whole navigation menu (≈70 links incl. departments, facilities, external placement portal URL), top bar links, logo paths |
| `footer.jsp`, `footer2.jsp`, `footer copy.jsp` | Address, phone, e-mail, social links, copyright year |
| `index.jsp` | Contact details, welcome text, quick links, marquee content, embedded Drive/YouTube links |
| `feestructure.jsp` | The full fee table (registration ₹2500, university reg. ₹2100, tuition ₹120, … per year/category) |
| `contactus.jsp`, `facilities/contactus.jsp`, `refundandcancellation.jsp`, `approval/grievance.jsp` | Contact names, phones, e-mails |
| `about/principal.jsp`, `about/history.jsp`, `about/vision.jsp`, `about/affiliation.jsp`, `about/allotment.jsp` | Principal name, photo and message; history; vision/mission text |
| `department/*/about.jsp` (9 depts), `department/*/faculty.jsp`, `department/*/staff.jsp`, `*/programs.jsp`, `*/labs.jsp`, `*/awards.jsp`, `*/students.jsp` | HOD name/contact, faculty lists with **personal e-mail addresses and phone numbers** (25 JSPs contain e-mails/phones), lab lists, programmes, intake |
| `trainingplacement/placedstudent.jsp` (83 year mentions), `studentplacementcoordinator.jsp`, `aboutplacement.jsp` | Placed-student tables by year, coordinator names, TPO contact |
| `category/kecwebteam.jsp`, `category/webteamswiper.jsp` | Team member names, photos (`category/*.jpg`), e-mails |
| `gallery/photogallery.jsp` | Image file names and captions in a scriptlet array (`/wp-content/uploads/2018/...`) |
| `facultylist.jsp` | HOD flag / branch names in scriptlets |
| `academic/*.jsp` | Calendar, holiday text, syllabus links, results links, mentor lists, council MoM links |
| `rti/*.jsp`, `approval/*.jsp` | PIO names, RTI fees and addresses, approval PDFs (`approval/*.pdf`) |
| `facilities/*.jsp` | Hostel/guest-house/library/bank details, Wi-Fi info, useful links |
| Dropdowns | Departments 1–8 (`addfaculty.jsp`, `HodAddFaculty.jsp`, `addPlacement(s).jsp`, `addtpocoordinator.jsp`, `coursecreator.jsp`, `admin/uploadLabDoc.jsp`); notice types (`uploadfile.jsp`, `HODFileUpload.jsp`, `uploadNoticeFaculty.jsp`, `uploadfileByFaculty.jsp`). The notice-type values are **inconsistent** (`CIVILNotice` vs `CivilNotice`, `MECHNotice` vs `MechNotice`). SMS groups in `sms.jsp`. |
| Java | `NoticeBoardHelper` (dept code → notice type/view mapping), `KECUrlAuthenticationSuccessHandler` (role → URL), department controllers (faculty username per URL), `DocUtils` ("The Principal, KEC Katihar", logo path), `LeaveStatusMail` (calendar id, location, `-07:00` time zone!), mail sender/recipient addresses in 6 classes |
| Images | 205 hard-coded `/wp-content`, `/sites`, `/images` paths in JSPs; faculty photos in `jsp/gallery/facultypic/*.jpg` and `jsp/category/*.jpg` |
| Config | `SMS.properties` (phone-number lists), `employee.properties` |

---

## 7. Non-standard / risky code (later phase – list only)

### Security (high – recommend fixing soon after the upgrade, with your approval)
1. **Secrets in git** (history included):
   - DB and SMTP passwords in `application.properties`. Moved out on this branch, but they're still in history, so **rotate them**.
   - Firebase service account `kecattendance-account-info.json`.
   - Google service account `online-attendance-accountInfo.json` and `.p12`.
   - Textlocal **SMS API key** in `MessageSender.java`.
   - DB and SMTP passwords in `KECDBBackup.java`.
2. **Unauthenticated endpoints that change data or send mail:**
   - `/api/uploadAdministration`
   - `/rn`, `/rl`, `/rs`. These are open mail relays, and `/rs` also writes to the calendar.
   - `/approval/raisequery`
3. Most delete operations are **GET** requests and **CSRF is disabled** globally.
4. Authorization is often done by hand inside controllers (`accessAllow` loops) rather than in the security config. There's no `anyRequest().authenticated()`.
5. All JSPs are under `webapp/jsp`, not `WEB-INF`, so they can be requested directly (`/jsp/...`).
6. The password-reset link is built from the request host with plain `http://`.
7. The `/jsp/user/savePassword` cast fails with a 500 when there's no reset session.
8. File download headers use the user-supplied file name: `Content-Type: application/<ext>`, `content-disposition` without quoting. Uploads have no type or size validation.
9. `/hod/{fileName}/{id}` deletes a file on disk using a path built from a URL segment (path traversal).

### Robustness
1. `CalendarService` calls **`System.exit(1)`** on any credential error, which stops the whole server.
2. `CustomExceptionHandler` swallows all exceptions and returns a blank HTTP 200.
3. `printStackTrace()`/`System.out` everywhere. Empty `catch` in `ApprovalController.raiseQuery`. `FirebaseSetup` swallows init failure.
4. **Mutable state in singleton controllers:** `FacultyGenerateReport`, `LeaveMail` and `LeaveStatusMail` store request data in public fields, which is a race condition under concurrent requests.
5. `static SimpleDateFormat` in `FileUploadCintroller` isn't thread-safe.
6. Fixed temp files: `java.io.tmpdir/AttendanceReport.pdf`, `RequestLeave.pdf`, and `test.csv` in the working directory. Concurrent requests overwrite each other.
7. The leave calendar event uses time zone `-07:00` (US Pacific) instead of IST.
8. JPQL with a stray `)` in `FacultyRepository` (`= :facultyName)`, `= :deptno)`) and in `StaffRepository`. Hibernate 6+ rejects these at startup (handled in stage 3d).
9. `ddl-auto=update` on production (see §4).
10. About 85 controller endpoints point to JSPs that don't exist (see §3).
11. Duplicate mapping `/academic/admission` (in `AcademicController` and `CourseController`).

### Code structure
1. **Field injection:** 175 `@Autowired` fields, no constructor injection.
2. Business logic in controllers: role checks, JSON parsing with Gson, mail composition, PDF building.
3. **Duplicated code:**
   - 10 almost identical department controllers (~200 lines each).
   - `welcome`/`welcome2`.
   - Notice-filter helpers copied in several controllers.
   - Copy-paste upload handlers in `SlideImageController`/`UserDashboard`/`HODFileUploadController`.
   - Duplicate JSPs (`* copy.jsp`, `old/*.jsp`, `admin/*` vs `user/*`).
4. **JSP scriptlets** in `facultylist.jsp` and `gallery/photogallery.jsp`. JSP imports of service classes (`UserService`, `GsonBuilderUtils`).
5. **Hard-coded paths:** `/wp-content/uploads/notice/`, `/sites/default/files/compressed/`, `external`, `tokens`, `/credentials.json`.
6. **Missing validation:** commented-out empty-file checks in uploads; no `@Valid` on DTOs except registration.
7. **Wrong class names / placement:**
   - `FileUploadCintroller`, `notice` (lower-case class), `HolidaytServiceImpl`.
   - Core features inside the `store` package.
   - A top-level package `firebase.bean` outside `com.college`.
8. **Unused or mismatched dependencies:**
   - iText 7 kernel/layout and the jQuery webjar are unused.
   - `spring-security-taglibs` 5.2.2 runs on Security 4.0.3.
   - Two `javax.mail` jars.
   - `tomcat.version` is forced to 8.0.3, an old release with known CVEs.
9. `ApplicationInitializer` (`WebApplicationInitializer`) creates a second, unused `DispatcherServlet` context when deployed as a WAR.

---

## 8. Upgrade plan

**Target: Spring Boot 4.1.1** (latest GA on Maven Central, checked 2026-10-15; 4.1.x BOM: Spring Framework 7.0.9, Security 7.1.1, Hibernate 7.4.5, Tomcat 11.0.24, Jakarta Servlet 6.1, JSTL 3.0.2, Thymeleaf 3.1.5, Jackson 3.1.5, mysql-connector-j 9.7.0)
**on Java 25** (latest LTS; supported by Boot 4.1).

> **JDK 25 isn't installed.** Install it with: `sudo apt install openjdk-25-jdk` (Ubuntu 22.04 candidate `25.0.4.1+1-1~22.04.4`). Until then stage 3c can use JDK 21 and switch to 25 when it's available.

Each stage ends with: build → all Phase 2 tests green → app start against the local DB → commit.

| Stage | From → To | Main work |
|---|---|---|
| **3a** | 1.3.3 → **1.5.22.RELEASE** (Java 8) | Remove the `tomcat.version` override (→ Tomcat 8.5). `start-class` already fixed. Keep `spring-boot-maven-plugin`. `SpringBootServletInitializer` moves to `org.springframework.boot.web.support`. Run flags stay `-Drun.*`. Re-check JSP/JSTL and multipart. |
| **3b** | 1.5 → **2.7.18** (Java 8/11) | Temporarily add `spring-boot-properties-migrator`, rename properties (`spring.view.*` → `spring.mvc.view.*`, `spring.datasource.testWhileIdle` etc. → `spring.datasource.hikari.*` since HikariCP becomes the default pool), then remove the migrator. **Hibernate 5.6:** keep naming (`CamelCaseToUnderscoresNamingStrategy` default + `SpringImplicitNamingStrategy`), `spring.jpa.hibernate.use-new-id-generator-mappings=false` → then explicit `IDENTITY`. **Security 5.7:** keep the `BCryptPasswordEncoder` bean (hashes are `$2a$`, no `{bcrypt}` prefix needed), drop the pinned taglibs version. `WebMvcConfigurerAdapter` → `WebMvcConfigurer`. Spring Data 2: `findOne` → `findById`, `new PageRequest` → `PageRequest.of`. `ErrorController.getErrorPath` deprecated. Thymeleaf 3 (no templates, only a starter bump). Run flags become `-Dspring-boot.run.*`. |
| **3c** | 2.7 → **3.5.16**, Java → **25** (or 21 until 25 is installed) | `javax.*` → `jakarta.*` in Java code (servlet, persistence, annotation, mail, validation). JSP taglib URIs → `jakarta.tags.core`/`fmt`/`functions`; JSTL → `jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api` + `org.glassfish.web:jakarta.servlet.jsp.jstl`. `WebSecurityConfigurerAdapter` → `SecurityFilterChain` bean; `antMatchers` → `requestMatchers`; `authorizeRequests` → `authorizeHttpRequests` with `FORWARD`/`ERROR` dispatches permitted (JSP forwards). **Security context must be saved explicitly** in the password-reset flow (`SecurityContextRepository`), otherwise reset breaks. `commons-fileupload` → standard servlet multipart (drop `CommonsMultipartResolver` and the `MultipartAutoConfiguration` exclusion); **set `spring.servlet.multipart.max-file-size`/`max-request-size`**, because the commons resolver had no limit and the servlet default is 1 MB. `mysql-connector-java` → `com.mysql:mysql-connector-j`, driver `com.mysql.cj.jdbc.Driver`, drop the explicit dialect. Hibernate 6: explicit `IDENTITY`, drop undefined `generator=` names, fix the malformed JPQL. Spring MVC 6: trailing-slash matching off by default, so keep old behaviour with a `UrlHandlerFilter`/config if links use trailing slashes. Remove versions managed by the Boot BOM. |
| **3d** | 3.5 → **4.1.1** | Follow the Boot 4.0 + 4.1 migration guides: modular starters (`spring-boot-starter-webmvc` etc.); Jackson 3 default (we mostly use Gson); Security 7 (removed deprecated DSL); Hibernate 7; Tomcat 11 + Jasper for JSP (WAR packaging kept); `spring-boot-properties-migrator` again temporarily. |
| **3e** | 3rd-party libraries | firebase-admin 6.11 → **9.11.0**; Google Calendar → `google-api-services-calendar v3-rev20260925-2.0.0` + `google-api-client 2.9.1` + `google-auth-library-oauth2-http 1.54.0` (`GoogleCredential` → `ServiceAccountCredentials` + `HttpCredentialsAdapter`, `JacksonFactory` → `GsonFactory`; **drop Jackson-1 `google-http-client-jackson`** and the unused `google-oauth-client-jetty`); modelmapper → **3.2.7**; gson → Boot-managed (2.13.x/2.14); mysql-backup4j → **1.3.0**; jQuery webjar unused, so **keep 2.2.4 or remove it (your call)**, because the pages use CDN jQuery. Bumping it doesn't change anything users see; drop `jaxb-api` if unused. **iText: ⏸ ask before replacing** (see licensing below). |

**iText licensing:** the code really uses **iText 5.5.6 (`com.itextpdf.text.*`, AGPL-3.0)**, pulled in through the `com.lowagie:itext:4.2.2` relocation POM. iText 7.0.2 (`kernel`, `layout`, also AGPL) is declared but unused. Running AGPL iText in a publicly reachable web app without a commercial licence means AGPL obligations apply. Options to decide later:
1. **OpenPDF 3.0.5** (LGPL/MPL, iText-4 API fork; package renames needed, behaviour almost identical).
2. Keep iText 5 / move to iText 9.8 core (AGPL or commercial licence).
3. Apache PDFBox (rewrite of `DocUtils`/`FirebaseDocumentHelper`).

In every case the unused iText 7.0.2 dependencies can be removed.

---

## 9. Manual test checklist

Run on the local DB, after each stage and finally on the end result. Use one account per role (Admin, HOD, Faculty, SM, SK) and an anonymous browser.

### Public (anonymous)
- [ ] `/` home: slider images, achievement slider, notice list, scrolling notices, header menu, footer
- [ ] `/homePageImage/<name>/<type>` image (home page images load)
- [ ] Click a notice → `/notice/{id}` opens the file (PDF/image)
- [ ] `/category/notices`, `/category/tenders`, next/prev paging (`/pagination/next|prev/getNotices?deptno=CSE&pageSize=1`, `getTenders`)
- [ ] About: history, vision, principal, allotment, affiliation, administration (roles + responsibility docs), staff
- [ ] Academic: admission (seat table), regulations, calendar, holidays (list + open file), attendance, syllabus, rules, payment tutorial, anti-ragging, academic council, notice, university results, mentors, `/academic/lab?dept_id=1` (open lab doc)
- [ ] Each of the 10 department sites: about (dept notices), vision, students, notice, labs, question, awards, programs, weekly, course, attendance, faculty/staff/principal where linked
- [ ] `/faculty?deptno=1..8` faculty list (photos via `/{id}/image`); open a profile `/facultyDetails?facultyId=…` (documents via `/{id}/documents`)
- [ ] Facilities: all 15 pages; downloads list and download a file
- [ ] T&P: about, rules, tips, brochure, placed students, student coordinators
- [ ] Approvals: AKU, AICTE (open a doc), mandatory disclosure; NBA page (open a doc)
- [ ] Grievance: submit form → mail arrives at the principal address (use a test SMTP)
- [ ] RTI 4 pages; Gallery photo + media; Legal 6 pages; KEC web team; departments index
- [ ] 404 page for an unknown URL; 403 page for a forbidden one
- [ ] Protected URL (e.g. `/auth/uploadDashboard`) redirects to `/login`

### Login / account
- [ ] Login as each role → correct landing page (Admin → `/auth/uploadDashboard`, HOD → `/hod/hodDashboard`, Faculty → own `/facultyDetails`, SM → `/store/smdashboard`, SK → `/sk/storekeeper`)
- [ ] Wrong password → error on `/login`; logout → `/` with session cleared
- [ ] Forgot password → mail with link → link opens update page → new password saved → login with new password works; expired/invalid token → message
- [ ] Admin: `/auth/registration` creates a user with a role

### Admin
- [ ] Upload notice (file + header + type + scrollable + date) → visible on home/department page; with link only; delete notice
- [ ] Upload/delete slide image; achievement image; gallery image
- [ ] Upload/open/delete: AICTE doc, download, NBA doc, responsibility doc, holiday, lab doc
- [ ] Update administration role / delete it
- [ ] Add faculty (with photo), add staff, delete staff
- [ ] Add placements / delete; add TPO coordinator / delete
- [ ] Course seats: add / delete; check the admission page
- [ ] HOD in-charge: assign HOD role → department HOD name updated
- [ ] Send SMS page loads; send to "individual" (with a test number or the provider's test mode)
- [ ] Large upload (> 1 MB and ~10 MB) still works

### HOD
- [ ] HOD dashboard; upload dept notice → visible on dept page; delete it
- [ ] Add faculty to dept; delete faculty of own dept (other dept refused)
- [ ] Upload/delete lab document; delete holiday

### Faculty
- [ ] Landing on own profile; dashboard
- [ ] Edit profile details and key properties
- [ ] Upload profile photo; upload document and link; delete own document
- [ ] Upload notice / slide from the faculty dashboard
- [ ] Autocomplete fields (faculty name/email) respond

### Integrations
- [ ] `/rn?params=<sample json>` → attendance PDF+CSV mail (needs Firebase test data)
- [ ] `/rl?params=<json>` → leave application PDF mail
- [ ] `/rs?params=<json>` → leave status mail + Google Calendar event
- [ ] App starts without Firebase/Google network access (no `System.exit`)
- [ ] Daily token purge runs (or trigger manually) without error

### Store (regression only – module untouched)
- [ ] SM: dashboard, add product, add/edit/delete purchase, purchase details by dates, search orders, approve/reject faculty request, SM in-charge role
- [ ] SK: store keeper page, close faculty request
- [ ] Faculty: `/fstore/faculty/order`, product request, edit/delete request, order history

---

## 10. Decisions needed from you (⏸)

> 2026-10-15: the user answered "yes" to items 1–4 below (proposals accepted). Item 5 is pending the JDK install.

1. **Store files and compile-only changes.** From stage 3b/3c, store code won't compile without mechanical changes: `javax`→`jakarta` imports, `findOne`→`findById`, `new PageRequest`→`PageRequest.of`. Commenting it out would **break store management**, which violates "no functionality may break". **Proposal:** allow *purely mechanical, compile-required* edits in store files (imports and renamed API calls only, no logic, no refactoring), each listed in `UPGRADE_NOTES.md`. Also allow `CommonResourceController`'s **non-store** endpoints to be treated as in scope.
2. **`ddl-auto`.** Switch from `update` to `none` (recommended; `validate` fails on existing column types). This stops automatic schema changes on production.
3. **Production deployment.** Credentials now come from env vars. The production server needs `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `MAIL_USERNAME`, `MAIL_PASSWORD` (or its own `application-local.properties` with the `local` profile) **before** the next deploy.
4. **Multipart limits for 3c.** Is 20 MB file / 25 MB request OK? It was unlimited before.
5. **JDK 25.** Please run `sudo apt install openjdk-25-jdk` when convenient.
