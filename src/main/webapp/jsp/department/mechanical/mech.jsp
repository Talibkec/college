<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp"/>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>About Mechanical Engg.</h3></div>

            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
             <aside class="col-sm-3" role="complementary">
                                                                     <div class="region region-sidebar-second">
                                                           <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                                                   <h2 class="block-title">In the Department of Mechanical Engg.</h2>

                                                             <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                                            <ul class="menu nav">
                                                           <li class="first leaf  menu-mlid-1154"><a href="http://keck.ac.in/department/mechanical/mech" class="sf-depth-2 active">About Mechanical Engg.</a></li>
                                                           <li class="leaf menu-mlid-1121"><a href="http://keck.ac.in/department/mechanical/vision" class="sf-depth-2">Vision & Mission</a></li>
                                                           <li class="leaf menu-mlid-1156"><a href="http://keck.ac.in/department/mechanical/faculty" class="sf-depth-2">Faculty of Mechanical Engg.</a></li>
                                                           <li class="leaf menu-mlid-1294"><a href="http://keck.ac.in/department/mechanical/labs" class="sf-depth-2">Labs & Infrastructure</a></li>
                                                           <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/question" class="sf-depth-2">Question Bank</a></li>
                                                           <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/awards" class="sf-depth-2">awards & Recognition</a></li>
                                                           <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/programs" class="sf-depth-2">Program Activities</a></li>
                                                           <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/students" class="sf-depth-2">Student of Mechanical Engg.</a></li>
                                                           <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/notice" class="sf-depth-2">Notice</a></li>
                                                           <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/weekly" class="sf-depth-2">Weekly Test Result</a></li>
                                                           <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/programs" class="sf-depth-2">Course File</a></li>
                                                            <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/staff" class="sf-depth-2">Staff of Mechanical Engg.</a></li>
                                                           <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/attendance" class="sf-depth-2">Attendance Summary</a></li>
                                                              </ul></div>

                                                           </section>
                                                             </div>
                                                                 </aside>


<div class="col-md-6">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                    <div style="font-family: georgia, sans-serif; font-size: 16px; text-align: justify;">
                        <div id="ctl00_ContentPlaceHolder1_div_1"><p><strong>INTRODUCTION</strong></p><p>Mechanical engineering is the discipline that applies engineering physics, engineering mathematics, and materials science principles to design, analyse, manufacture, and maintain mechanical systems. It is one of the oldest and broadest of the engineering disciplines.It is known as the evergreen branch among all the branches of engineering department</p><p>The mechanical engineering field requires an understanding of core areas including mechanics, dynamics, thermodynamics, materials science, structural analysis, and electricity. In addition to these core principles, mechanical engineers use tools such as computer-aided design (CAD), computer-aided manufacturing (CAM), and product life cycle management to design and analyse manufacturing plants, industrial equipment and machinery, heating and cooling systems, transport systems, aircraft, watercraft, robotics, medical devices, weapons, and others. It is the branch of engineering that involves the design, production, and operation of machinery.</p><p>Nowadays the scope of mechanical engineering is expanding beyond its traditional boundaries.In order to contribute to the growth of quality mechanical engineers katihar engineering college has set up the department of mechanical engineering in the year 2016.the faculties here are very supportive in nature they hardly fails to inspire the young minds and enrich them with their knowledge and experience.The management keeps a strong check on each faculties and kindle them to attend workshops,seminars,conferences and faculty development program.Students enthusiastically work here under the supervision of trained faculty members.</p></div>
                        </div>
                </div>
            </div>
            <div class="heroCol col-sm-3">
            <div class="panel panel-default">
            <div class="panel-heading">
            <strong>
            <i class="fa fa-news"></i> Latest News / Updates</strong>
                                                                                          <c:forEach var="item" items="${Role}">
                                                                                                                           <c:if test = "${'HOD' eq item and UserName eq 'hodmech'}">
                                                                                              <strong><i class="fa fa-news"></i> <a href="http://keck.ac.in/hod/uploadfile/upload">Upload Notice / News</a></strong>

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
                                                                                                             <c:if test = "${'HOD' eq item}">
                                                                                                              <span class="label label-danger"><a href="<c:url value='/auth/${notice.uploadedFileName}/${notice.id}' />">Delete</a></span>
                                                                                                          </c:if>
                                                                                                          </c:forEach>
                                                                                                         <a href="http://keck.ac.in/${notice.id}/notice${notice.fileType}" target="_blank">${notice.headLine}</a>
                                                                                                      </div>
                                                                                                  </li>
                                                                                              </c:forEach>
                                                                                          </ul>
                                                                                          <a href="http://keck.ac.in/department/mechanical/notice" class="btn btn-default">View All<i class="fa fa-double-angle-right"></i></a>
            </div>
            </div>
            </div>
            </div>
        </div>
    </div>
<jsp:include page="/jsp/footer.jsp"/>







