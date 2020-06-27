
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp"/>

<div class="row scrollingnews">
        <marquee style=  loop="infinite" onmouseover="this.stop();" onmouseout="this.start();">
            <ul>

                <c:forEach items="${scrollingNoticeList}" var="scrollingNotice">
                 <li style= "display: inline;">
                 <i class="fa fa-asterisk"></i>
                 <c:if test = "${scrollingNotice.noticeAge <= 45}">
                    <img class="" src="http://localhost/sites/noticeicon.gif" alt=""Related image" width="44" height="40" style="display: inline" />
                 </c:if>
                 <a href="http://localhost/${scrollingNotice.id}/notice${scrollingNotice.fileType}" style="display: inline" target="_blank">${scrollingNotice.headLine}</a>
                 <c:forEach var="item" items="${Role}">
                                               <c:if test = "${'HOD' eq item and UserName eq 'hodeee'}">                              <span class="label label-danger"><a style="display: inline" href="<c:url value='/hod/deleteNotice/${scrollingNotice.id}' />">Delete</a></span>
                     </c:if>
                 </c:forEach>
                 </li>

                </c:forEach>
               </ul>
            </marquee>
    </div>



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
                                               <li class="leaf menu-mlid-1156"><a href="http://localhost/faculty?deptno=4" class="sf-depth-2">Faculty of Electrical & Electronics Engg.</a></li>
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
                                                                                                           <c:forEach var="item" items="${Role}">
                                                                                                               <c:if test = "${'HOD' eq item  and UserName eq 'hodeee'}">

                                                                                                               <strong><i class="fa fa-news"></i> <a href="http://localhost/hod/uploadfile/upload">Upload Notice / News</a></strong>

                                                                                                           </c:if>
                                                                                                           </c:forEach></div>
                                                                                                       <div class="panel-body" style="max-height: 500px;">
                                                                                                           <ul class="listUpdates" id="noticeBoardItems">



                                                                                                               <c:forEach items="${noticeList}" var="notice">
                                                                                                                   <li>
                                                                                                                       <div>
                                                                                                            		<span class="label" style="border:  solid 1px #ccc; color: #000;">
                                                                                                            		<c:set var = "clazz"  value = "label label-warning"/>

                                                                                                            		 <c:if test = "${ notice.noticeType == 'News'}">
                                                                                                                             <c:set var = "clazz"  value = "label label-primary"/>
                                                                                                                     </c:if>

                                                                                                            		<i class="fa fa-clock-o"></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                                                                                                                          <c:forEach var="item" items="${Role}">
                                               <c:if test = "${'HOD' eq item and UserName eq 'hodeee'}">
                                                                                                                                                                              <span class="label label-danger"><a href="<c:url value='/hod/deleteNotice/${notice.id}' />">Delete</a></span>
                                                                                                                           </c:if>
                                                                                                                           </c:forEach>
                                                                                                                           <a href="http://localhost/${notice.id}/notice${notice.fileType}" target="_blank">${notice.headLine}</a>
                                                                                                                       </div>
                                                                                                                   </li>
                                                                                                               </c:forEach>
                                                                                                           </ul>
                                                                                                           <a href="http://localhost/department/eee/notice" class="btn btn-default">View All<i class="fa fa-double-angle-right"></i></a>
                                </div>
                                </div>
                                </div>
                                </div>
                            </div>
   </div>


<jsp:include page="/jsp/footer.jsp"/>
