<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp"/>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>About Electrical & Electronics Engg</h3></div>

            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
           <aside class="col-sm-3" role="complementary">
                                                         <div class="region region-sidebar-second">
                                               <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                                       <h2 class="block-title"> In the Department of Electrical & Electronics Engg.</h2>

                                                 <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                                <ul class="menu nav">
                                               <li class="first leaf  menu-mlid-1154"><a href="http://localhost/department/eee/about" class="sf-depth-2 active">About Electrical & Electronics Engg.</a></li>
                                               <li class="leaf menu-mlid-1121"><a href="http://localhost/department/eee/vision" class="sf-depth-2">Vision & Mission</a></li>
                                               <li class="leaf menu-mlid-1156"><a href="http://localhost/department/eee/faculty" class="sf-depth-2">Faculty of Electrical & Electronics Engg.</a></li>
                                               <li class="leaf menu-mlid-1294"><a href="http://localhost/department/eee/labs" class="sf-depth-2">Labs & Infrastructure</a></li>
                                               <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/question" class="sf-depth-2">Question Bank</a></li>
                                               <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/awards" class="sf-depth-2">awards & Recognition</a></li>
                                               <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/programs" class="sf-depth-2">Program Activities</a></li>
                                               <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/students" class="sf-depth-2">Student of  Electrical & Electronics Engg.</a></li>
                                               <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/notice" class="sf-depth-2">Notice</a></li>
                                               <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/weekly" class="sf-depth-2">Weekly Test Result</a></li>
                                               <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/programs" class="sf-depth-2">Course File</a></li>
                                                <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/staff" class="sf-depth-2">Staff of  Electrical & Electronics Engg.</a></li>
                                               <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/attendance" class="sf-depth-2">Attendance Summary</a></li>
                                                  </ul></div>

                                               </section>
                                                 </div>
                                                     </aside>
            <div class="col-md-6">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                    <div style="font-family: Arial, Helvetica, sans-serif; font-size: 16px; text-align: justify;"><h3>
                        Introduction</h3>
                        <p>Electrical engineering is a professional engineering discipline that generally
                            deals with the study and application of electricity, electronics, and
                            electromagnetism. Electrical engineering has now subdivided into a wide range of
                            subfields including electronics, digital computers, computer engineering, power
                            engineering, telecommunications, control systems, radiofrequency engineering,
                            signal processing, instrumentation, and microelectronics. Many of these
                            subdisciplines overlap with other engineering branches, spanning a huge number
                            of specializations such as hardware engineering, power electronics,
                            electromagnetics & waves, microwave engineering, nanotechnology,
                            electrochemistry, renewable energies, mechatronics, electrical materials science,
                            and much more. Electrical engineering branch is basically pillar of all branch. It
                            gives energy to drive all engineering equipment .
                            The Department of Electronics and Electrical Engineering of Katihar
                            College of Engineering, Katihar is ready to prepare well qualified, motivated,
                            application oriented young and dynamic EEE graduates under the guidance of
                            highly qualified faculties who inspire students, ignite students’ imagination and
                            enrich them with their priceless knowledge and experience</p></div>
                </div>
            </div>


        <div class="heroCol col-sm-3">
                                <div class="panel panel-default">
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
                                </div>
                                </div>
                            </div>
   </div>


<jsp:include page="/jsp/footer.jsp"/>
