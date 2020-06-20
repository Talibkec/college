
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
                                               <c:if test = "${'HOD' eq item and UserName eq 'hodcse'}">                              <span class="label label-danger"><a style="display: inline" href="<c:url value='/hod/${scrollingNotice.uploadedFileName}/${scrollingNotice.id}' />">Delete</a></span>
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
                <div class="col-md-10 col-sm-8"><h3>About CSE</h3></div>
                <div class="col-md-2 col-sm-4" style="font-size: 24px; line-height: 40px;"><span
                        style="display: inline-block; line-height: 40px; vertical-align: middle;"><i
                        </i> </span> <a
                        href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2F#%2Fdepartment-of-cse%2Fabout-cse%2F"
                        target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i
                        </i></a> <a
                        href="https://twitter.com/home?status=About+Information+Technology+-+https%3A%2F%2F#%2Fdepartment-of-cse%2Fabout-cse%2F"
                        target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i
                        </i> </a></div>
            </div>
        </div>
    </div>
    <div class="container">
     <aside class="col-sm-3" role="complementary">
                      <div class="region region-sidebar-second">
            <section id="block-menu-block-3" class="block block-menu-block clearfix">

                    <h2 class="block-title">In this section</h2>

              <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
             <ul class="menu nav">
            <li class="first leaf  menu-mlid-1154"><a href="http://localhost/department/cse/about" class="sf-depth-2 active">About CSE</a></li>
            <li class="leaf menu-mlid-1121"><a href="http://localhost/department/cse/vision" class="sf-depth-2">Vision & Mission</a></li>
            <li class="leaf menu-mlid-1156"><a href="http://localhost/faculty?deptno=1" class="sf-depth-2">Faculty of CSE</a></li>
            <li class="leaf menu-mlid-1294"><a href="http://localhost/department/cse/labs" class="sf-depth-2">Labs & Infrastructure</a></li>
            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/question" class="sf-depth-2">Question Bank</a></li>
            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/awards" class="sf-depth-2">awards & Recognition</a></li>
            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/programs" class="sf-depth-2">Program Activities</a></li>
            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/students" class="sf-depth-2">Student of CSE</a></li>
            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/notice" class="sf-depth-2">Notice</a></li>
            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/weekly" class="sf-depth-2">Weekly Test Result</a></li>
            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/programs" class="sf-depth-2">Course File</a></li>
             <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/staff" class="sf-depth-2">Staff of CSE</a></li>
            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/attendance" class="sf-depth-2">Attendance Summary</a></li>
               </ul></div>

            </section>
              </div>
                  </aside>

            <div class="col-sm-6">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                    <div style="font-family: georgia, sans-serif; font-size: 16px; text-align: justify;">
                        <div id="ctl00_ContentPlaceHolder1_div_1"><p><strong>INTRODUCTION</strong></p>Computer Science and Engineering is a sub field of electronics engineering that covers only digital aspects of electronics engineering like computer architecture, processor designing, computer networks etc. This department integrates the fields of computer engineering and computer science. CSE programs include core subjects of computer science such as <strong>operating systems,theory of computation, design and analysis of algorithms, data structures and database systems.</strong><p>CSE branch is highly demanding and fastest growing subject. It will make human life better than ever before and that’s why a whole world has entered into new race named, “Development of computer technology”. As a contribution to this race Katihar college of Engineering has established a department of computer science and engineering from the academic session 2016/17. The department offers Btech programme with the annual intake of 60 students and are working to increase the intake capabilities.</p><p>The Department of Computer Science and Engineering of Katihar Engineering College, Katihar is ready to prepare well qualified, motivated, application oriented young and dynamic Computer Science graduates under the guidance of highly qualified faculties who inspire students, ignite students’ imagination and enrich them with their priceless knowledge and experience. The faculty of the Department are actively engaged in research in the areas of Data & Knowledge Management, Software Engineering, Image Processing, Cryptography, Data Mining, and Artificial Intelligence. Management keeps strong check on faculties and provoke them to attend workshops, seminars, conferences and faculty development program.</p></div>
                        </div>
                </div>
            </div>
            <div class="heroCol col-sm-3">
            <div class="panel panel-default">
            <div class="panel-heading">
            <strong>
             <i class="fa fa-news"></i> Latest News / Updates</strong>
                             <c:forEach var="item" items="${Role}">
                                 <c:if test = "${'HOD' eq item and UserName eq 'hodcse'}">
                                 <strong><i class="fa fa-news"></i> <a href="http://localhost/hod/uploadfile/upload">Upload Notice / News</a></strong>
                             </c:if>
                             </c:forEach>
                             </div>
                         <div class="panel-body" style="max-height: 500px;">
                             <ul class="listUpdates" id="noticeBoardItems">

                                 <li style="display: block;">
                                     <div>

                                     </div>
                                 </li>

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
                                               <c:if test = "${'HOD' eq item and UserName eq 'hodcse'}">
                                                 <span class="label label-danger"><a href="<c:url value='/hod/deleteNotice/${notice.id}' />">Delete</a></span>
                                             </c:if>
                                             </c:forEach>
                                              <a href="http://localhost/${notice.id}/notice${notice.fileType}" target="_blank">${notice.headLine}</a>
                                         </div>
                                     </li>
                                 </c:forEach>
                             </ul>
                             <a href="http://localhost/department/cse/notice" class="btn btn-default">View All<i class="fa fa-double-angle-right"></i></a>
            </div>
            </div>
            </div>
            </div>
        </div>
    </div>
<jsp:include page="/jsp/footer.jsp"/>

