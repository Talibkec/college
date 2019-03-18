<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp"/>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>About Applied Science and Humanities</h3></div>

            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
           <aside class="col-sm-3" role="complementary">
                                             <div class="region region-sidebar-second">
                                   <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                           <h2 class="block-title">In the Department of Applied Sc. & Humanities</h2>

                                     <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                    <ul class="menu nav">
                                   <li class="first leaf  menu-mlid-1154"><a href="http://localhost/department/ash/about" class="sf-depth-2 active">About Applied Sc. & Humanities</a></li>
                                   <li class="leaf menu-mlid-1121"><a href="http://localhost/department/ash/vision" class="sf-depth-2">Vision & Mission</a></li>
                                   <li class="leaf menu-mlid-1156"><a href="http://localhost/department/ash/faculty" class="sf-depth-2">Faculty of Applied Sc. & Humanities</a></li>
                                   <li class="leaf menu-mlid-1294"><a href="http://localhost/department/ash/labs" class="sf-depth-2">Labs & Infrastructure</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="http://localhost/department/ash/question" class="sf-depth-2">Question Bank</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="http://localhost/department/ash/awards" class="sf-depth-2">Awards & Recognition</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="http://localhost/department/ash/programs" class="sf-depth-2">Program Activities</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="http://localhost/department/ash/students" class="sf-depth-2">Student of Applied Sc. & Humanities</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="http://localhost/department/ash/notice" class="sf-depth-2">Notice</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="http://localhost/department/ash/weekly" class="sf-depth-2">Weekly Test Result</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="http://localhost/department/ash/programs" class="sf-depth-2">Course File</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/ash/staff" class="sf-depth-2">Staff of Applied Sc. & Humanities</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="http://localhost/department/ash/attendance" class="sf-depth-2">Attendance Summary</a></li>
                                      </ul></div>

                                   </section>
                                     </div>
                                         </aside>

                                      <div class="col-sm-9" style="display:block;">
                                           <div class="panel panel-default" style="float:right; height:273px; margin:10px 20px; width:400px" >
                                                  <div class="panel-heading">
                                                      <strong>
                                                          <i class="fa fa-news"></i> Latest News / Updates</strong>
                                                      <c:if test = "${Role == 'HOD'}">
                                                          <strong><i class="fa fa-news"></i> <a href="/auth/uploadfile/upload">Upload Notice / News</a></strong>
                                                          <strong><i class="fa fa-news"></i> <a href="/auth/sendMessage">Send Message</a></strong>
                                                      </c:if></div>
                                                  <div class="panel-body" style="max-height: 500px;">
                                                      <ul class="listUpdates" id="noticeBoardItems">

                                                          <li style="display: block;">
                                                              <div>
                                                        		<span class="label" style="border:  solid 1px #ccc; color: #000;">
                                                       		<i class="fa fa-clock-o"></i> 2019-01-03 </span>&nbsp; <span class="label label-primary">News</span><a href="https://www.aicte-india.org/feedback/" target="_blank">Faculties and student may give feedback to A.I.C.T.E here</a>
                                                                  <span class="label" style="border:  solid 1px #ccc; color: #000;">
                                                                           		<i class="fa fa-clock-o"></i> 2019-01-03 </span>&nbsp; <span class="label label-primary">News</span><a href="https://www.aicte-india.org/feedback/" target="_blank">Faculties and student may give feedback to A.I.C.T.E here</a>
                                                              </div>
                                                          </li>

                                                          <c:forEach items="${noticeList}" var="notice">
                                                              <li>
                                                                  <div>
                                                       		<span class="label" style="border:  solid 1px #ccc; color: #000;">
                                                       		<c:set var = "clazz"  value = "label label-warning"/>
                                                       		<c:set var = "filePath"  value = "http://localhost/wp-content/uploads/notice/"/>
                                                       		 <c:if test = "${ notice.noticeType == 'News'}">
                                                                        <c:set var = "clazz"  value = "label label-primary"/>
                                                                </c:if>
                                                       		<i class="fa fa-clock-o"></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                                                                      <c:if test = "${Role == 'Admin'}">
                                                                          <span class="label label-danger"><a href="<c:url value='/auth/${notice.uploadedFileName}/${notice.id}' />">Delete</a></span>
                                                                      </c:if>
                                                                      <a href="${filePath}${notice.uploadedFileName}" target="_blank">${notice.headLine}</a>
                                                                  </div>
                                                              </li>
                                                          </c:forEach>
                                                      </ul>
                                                      <a href="http://localhost/category/notices" class="btn btn-default">View All<i class="fa fa-double-angle-right"></i></a>
                                                  </div>
                                              </div>


           <span style="font-family: georgia, sans-serif; font-size: 16px; text-align: justify; line-height: 175%; font-size: 125%; font-weight: 300;"><strong>INTRODUCTION</strong>This department
                            deals with the all the basic concepts required to excel in the field of engineering. All the
                            concept of science, mathematics, humanities and management is being revisited recalled and
                            interpreted so as to ease the concepts which is required to excel in the field. The
                            department teaches the subjects of Chemistry, English, Mathematics, Physics and Mathematics
                            in the B.Tech Programs. There are number of well qualified and experienced faculty members
                            available at the service of the department.
                            The aspects such as technical communication, technical report writing, industrial economics,
                            Accountancy, Industrial organization, their behavior and Physcology is being taught under
                            the section of humanities
                            The department of Applied Science consists of Applied Physics, Applied Chemistry and Applied
                            Mathematics. This section deals with the concepts such as semiconductor physics, optics,
                            Electrostatic , modern physics, Solutions and their colligative properties, Polymers and
                            practical aspects such as Titration of chemicals, testing the PH value of solutions and many
                            more.
                            Applied Mathematics offers courses to undergraduate and post-graduate students of various
                            engineering disciplines. The syllabi have been designed in the areas of Applied Mathematics,
                            Computational Techniques and Statistical testing to impart the sound knowledge of the
                            various mathematical tools used and their applications in the engineering disciplines.This
                            department deals with the all the basic concepts required to excel in the field of
                            engineering. All the concept of science, mathematics, humanities and management is being
                            revisited recalled and interpreted so as to ease the concepts which is required to excel in
                            the field. The department teaches the subjects of Chemistry, English, Mathematics, Physics
                            and Mathematics in the B.Tech Programs. There are number of well qualified and experienced
                            faculty members available at the service of the department.
                            The aspects such as technical communication, technical report writing, industrial economics,
                            Accountancy, Industrial organization, their behavior and Physcology is being taught under
                            the section of humanities
                            The department of Applied Science consists of Applied Physics, Applied Chemistry and Applied
                            Mathematics. This section deals with the concepts such as semiconductor physics, optics,
                            Electrostatic , modern physics, Solutions and their colligative properties, Polymers and
                            practical aspects such as Titration of chemicals, testing the PH value of solutions and many
                            more.
                            Applied Mathematics offers courses to undergraduate and post-graduate students of various
                            engineering disciplines. The syllabi have been designed in the areas of Applied Mathematics,
                            Computational Techniques and Statistical testing to impart the sound knowledge of the
                            various mathematical tools used and their applications in the engineering disciplines.
                            This department deals with the all the basic concepts required to excel in the field of
                            engineering. All the concept of science, mathematics, humanities and management is being
                            revisited recalled and interpreted so as to ease the concepts which is required to excel in
                            the field. The department teaches the subjects of Chemistry, English, Mathematics, Physics
                            and Mathematics in the B.Tech Programs. There are number of well qualified and experienced
                            faculty members available at the service of the department.
                            The aspects such as technical communication, technical report writing, industrial economics,
                            Accountancy, Industrial organization, their behavior and Physcology is being taught under
                            the section of humanities
                            The department of Applied Science consists of Applied Physics, Applied Chemistry and Applied
                            Mathematics. This section deals with the concepts such as semiconductor physics, optics,
                            Electrostatic , modern physics, Solutions and their colligative properties, Polymers and
                            practical aspects such as Titration of chemicals, testing the PH value of solutions and many
                            more.
                            Applied Mathematics offers courses to undergraduate and post-graduate students of various
                            engineering disciplines. The syllabi have been designed in the areas of Applied Mathematics,
                            Computational Techniques and Statistical testing to impart the sound knowledge of the
                            various mathematical tools used and their applications in the engineering disciplines.



                            </div>
                    </div>
                </div></div>



            </div>
        </div>


<jsp:include page="/jsp/footer.jsp"/>