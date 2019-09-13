
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/jsp/header.jsp"/>
 <div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
         <div class="container">
             <div class="row">
                 <div class="col-md-10 col-sm-8"><h3>About Civil Engg.</h3></div>
                 <div class="col-md-2 col-sm-4" style="font-size: 24px; line-height: 40px;"><span
                         style="display: inline-block; line-height: 40px; vertical-align: middle;"><i
                     </i> </span> <a
                         href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2F#%2Fdepartment-of-civil-engineering%2Fabout-civil-engineering%2F"
                         target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i
                     </i></a> <a
                         href="https://twitter.com/home?status=About+Civil+Engg.+-+https%3A%2F%2F#%2Fdepartment-of-civil-engineering%2Fabout-civil-engineering%2F"
                         target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i
                     </i> </a></div>
             </div>
         </div>
     </div>
     <div class="container">
         <ul class="breadcrumb" style="background: none">
             <li><a href="/"><i class="fa fa-home"></i> Home</a></li>
             <li class="active">About Civil Engg.</li>
         </ul>
         <div class="row">

             <aside class="col-sm-3" role="complementary">
                                   <div class="region region-sidebar-second">
                         <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                 <h2 class="block-title">In the Department of Civil Engg.</h2>

                           <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                          <ul class="menu nav">
                         <li class="first leaf  menu-mlid-1154"><a href="http://keck.ac.in/department/civil/about" class="sf-depth-2 active">About Civil Engg.</a></li>
                         <li class="leaf menu-mlid-1121"><a href="http://keck.ac.in/department/civil/vision" class="sf-depth-2">Vision & Mission</a></li>
                         <li class="leaf menu-mlid-1156"><a href="http://keck.ac.in/department/civil/faculty" class="sf-depth-2">Faculty of Civil Engg.</a></li>
                         <li class="leaf menu-mlid-1294"><a href="http://keck.ac.in/department/civil/labs" class="sf-depth-2">Labs & Infrastructure</a></li>
                         <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/question" class="sf-depth-2">Question Bank</a></li>
                         <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/awards" class="sf-depth-2">awards & Recognition</a></li>
                         <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/programs" class="sf-depth-2">Program Activities</a></li>
                         <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/students" class="sf-depth-2">Student of Civil Engg.</a></li>
                         <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/notice" class="sf-depth-2">Notice</a></li>
                         <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/weekly" class="sf-depth-2">Weekly Test Result</a></li>
                         <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/programs" class="sf-depth-2">Course File</a></li>
                          <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/staff" class="sf-depth-2">Staff of Civil Engg.</a></li>
                         <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/attendance" class="sf-depth-2">Attendance Summary</a></li>
                            </ul></div>

                         </section>
                           </div>
                               </aside>

                         <div class="col-md-9">
                             <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">

                             <div>

                            <!-- ********************* New content ********************** -->
                                                                                                                                               <div class="heroCol col-sm-5 wrapping-text-div">
                                                                                                                                                                        <div class="panel panel-default">
                                                                                                                                                                        <div class="panel-heading">
                                                                                                                                                                        <strong>
                                                                                                                                                                         <i class="fa fa-news"></i> Latest News / Updates</strong>
                                                                                                                                                                                                                                                   <c:forEach var="item" items="${Role}">
                                                                                                                                                                                                                                                                          <c:if test = "${'HOD' eq item and UserName eq 'hodcivil'}">
                                                                                                                                                                                                                                                                 <strong><i class="fa fa-news"></i> <a href="http://keck.ac.in/hod/uploadfile/upload">Upload Notice / News</a></strong>

                                                                                                                                                                                                                                                             </c:if>
                                                                                                                                                                                                                                                             </c:forEach></div>
                                                                                                                                                                                                                                                         <div class="panel-body" style="max-height: 500px;font-family: georgia, sans-serif;font-size: 14px;text-align: justify;line-height: 1.3;">
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
                                                                                                                                                                                                                                                                               <c:if test = "${'HOD' eq item}">
                                                                                                                                                                                                                                                                                 <span class="label label-danger"><a href="<c:url value='/hod/${notice.uploadedFileName}/${notice.id}' />">Delete</a></span>
                                                                                                                                                                                                                                                                             </c:if>
                                                                                                                                                                                                                                                                             </c:forEach>
                                                                                                                                                                                                                                                                            <a href="http://keck.ac.in/${notice.id}/notice${notice.fileType}" target="_blank">${notice.headLine}</a>
                                                                                                                                                                                                                                                                         </div>
                                                                                                                                                                                                                                                                     </li>
                                                                                                                                                                                                                                                                 </c:forEach>
                                                                                                                                                                                                                                                             </ul>
                                                                                                                                                                                                                                                             <a href="http://keck.ac.in/department/civil/notice" class="btn btn-default">View All<i class="fa fa-double-angle-right"></i></a>
                                                                                                                                                                        </div>
                                                                                                                                                                        </div>
                                                                                                                                                                        </div>
                                                                                                                                                                        </div>
                                                                                                                                                                    </div>
                                                                                                                                               <!-- ********************* New content ********************** -->
                                <div id="ctl00_ContentPlaceHolder1_div_1 " style="font-family: georgia, sans-serif; font-size: 16px; text-align: justify;" ><p><strong>INTRODUCTION</strong></p><p>Katihar Engineering College,katihar was established in 2016 and the Civil Engineering Department is part of the institute since its inception. The Department has grown tremendously over the years and is now recognized as one of the major engineering departments in the state of Bihar. Department of civil Engineering offers Bachelors in Technology, have an annual intake 60. The Department of Civil Engineering with its multifaceted faculty from different field like Geotechnical, Environmental and structural engineering maintains high quality teaching and instruction at UG level.</span><br/>
                                                                                                                   Currently it comprises of various sub divisions like <br/>(1)Building Technology and Construction Management (BTCM)<br/> (2) Environmental and water resources engineering (EWRE)<br/>(3) Geotechnical engineering (GT)<br/>(4) Structural engineering (ST)<br/> (5) Transportation engineering (TR) .
                                        </p>
                                </div>
                             </div>

                         </div>

                         </div>

                 </div>
             <jsp:include page="/jsp/footer.jsp"/>

