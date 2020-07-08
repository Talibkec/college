<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp"/>

<div class="row scrollingnews">
    <marquee style=loop="infinite" onmouseover="this.stop();" onmouseout="this.start();">
        <ul>

            <c:forEach items="${scrollingNoticeList}" var="scrollingNotice">
                <li style="display: inline;">
                    <i class="fa fa-asterisk"></i>
                    <c:if test="${scrollingNotice.noticeAge <= 45}">
                        <img class="" src="http://localhost/sites/noticeicon.gif" alt="" Related image" width="44"
                        height="40" style="display: inline" />
                    </c:if>
                    <a href="http://localhost/${scrollingNotice.id}/notice${scrollingNotice.fileType}"
                       style="display: inline" target="_blank">${scrollingNotice.headLine}</a>
                    <c:forEach var="item" items="${Role}">
                        <c:if test="${'HOD' eq item and UserName eq 'hodmech'}">
                            <span class="label label-danger"><a style="display: inline"
                                                                href="<c:url value='/hod/deleteNotice/${scrollingNotice.id}' />">Delete</a></span>
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


            <marquee style=loop="infinite" onmouseover="this.stop();" onmouseout="this.start();">
                <ul>


                    <c:forEach items="${blinkMessage}" var="scrollingNotice">


<span class="blinking aligning">
                          <li style="display:inline;font-size:140%">
                                  <strong>      ${scrollingNotice.headLine}  </strong>
                                                <c:forEach var="item" items="${Role}">
                                               <c:if test="${'HOD' eq item and UserName eq 'hodmech'}">
                                                 <span class="label label-danger"><a style="display: inline"
                                                                                     href="<c:url value='/hod/deleteNotice/${scrollingNotice.id}' />">Delete</a></span>
                                                    </c:if>
                                                </c:forEach>
</li>
                                                                </span>
                    </c:forEach>


                </ul>
            </marquee>
        </div>
    </div>


</div>
</div>
</div>
<div class="container">

    <h3>About Mechanical Engg.</h3>


    <ul class="breadcrumb" style="background: none">
        <li><a href="/"><i class="fa fa-home"></i> Home</a></li>
        <li class="active">About Mechanical Engg.</li>

    </ul>


    <div class="row">


        <aside class="col-sm-3" role="complementary">
            <div class="region region-sidebar-second">
                <section id="block-menu-block-3" class="block block-menu-block clearfix">

                    <h2 class="block-title">In the Department of Mechanical Engg.</h2>

                    <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                        <ul class="menu nav">
                            <li class="first leaf  menu-mlid-1154"><a href="http://localhost/department/mechanical/mech"
                                                                      class="sf-depth-2 active">About Mechanical
                                Engg.</a></li>
                            <li class="leaf menu-mlid-1121"><a href="http://localhost/department/mechanical/vision"
                                                               class="sf-depth-2">Vision & Mission</a></li>
                            <li class="leaf menu-mlid-1156"><a href="http://localhost/faculty?deptno=3"
                                                               class="sf-depth-2">Faculty of Mechanical Engg.</a></li>
                            <li class="leaf menu-mlid-1294"><a href="http://localhost/department/mechanical/labs"
                                                               class="sf-depth-2">Labs & Infrastructure</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/mechanical/question"
                                                               class="sf-depth-2">Question Bank</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/mechanical/awards"
                                                               class="sf-depth-2">awards & Recognition</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/mechanical/programs"
                                                               class="sf-depth-2">Program Activities</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/mechanical/students"
                                                               class="sf-depth-2">Student of Mechanical Engg.</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/mechanical/notice"
                                                               class="sf-depth-2">Notice</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/mechanical/weekly"
                                                               class="sf-depth-2">Weekly Test Result</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/mechanical/programs"
                                                               class="sf-depth-2">Course File</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/mechanical/staff"
                                                               class="sf-depth-2">Staff of Mechanical Engg.</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://localhost/department/mechanical/attendance"
                                                               class="sf-depth-2">Attendance Summary</a></li>
                        </ul>
                    </div>

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
                                    <c:if test="${'HOD' eq item and UserName eq 'hodmech'}">
                                        <strong><i class="fa fa-news"></i> <a
                                                href="http://localhost/hod/uploadfile/upload">Upload Notice /
                                            News</a></strong>

                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="panel-body"
                                 style="max-height: 500px;font-family: georgia, sans-serif;font-size: 14px;text-align: justify;line-height: 1.3;">
                                <ul class="listUpdates" id="noticeBoardItems">

                                    <li style="display: block;">
                                        <div>


                                        </div>
                                    </li>

                                    <c:forEach items="${noticeList}" var="notice">
                                        <li>
                                            <div>
                                                                                                                                                                                                                                                              		<span class="label"
                                                                                                                                                                                                                                                                          style="border:  solid 1px #ccc; color: #000;">
                                                                                                                                                                                                                                                              		<c:set var="clazz"
                                                                                                                                                                                                                                                                           value="label label-warning"/>

                                                                                                                                                                                                                                                              		 <c:if test="${ notice.noticeType == 'CIVILNews'}">
                                                                                                                                                                                                                                                                               <c:set var="clazz"
                                                                                                                                                                                                                                                                                      value="label label-primary"/>
                                                                                                                                                                                                                                                                       </c:if>
                                                                                                                                                                                                                                                              		<i class="fa fa-clock-o"></i> ${notice.date} </span>&nbsp;
                                                <span class="${clazz}">${notice.noticeType}</span>
                                                <c:forEach var="item" items="${Role}">
                                                    <c:if test="${'HOD' eq item and UserName eq 'hodmech'}">
                                                        <span class="label label-danger"><a
                                                                href="<c:url value='/hod/deleteNotice/${notice.id}' />">Delete</a></span>
                                                    </c:if>
                                                </c:forEach>
                                                <a href="http://localhost/${notice.id}/notice${notice.fileType}"
                                                   target="_blank">${notice.headLine}</a>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <a href="http://localhost/department/civil/notice" class="btn btn-default">View All<i
                                        class="fa fa-double-angle-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ********************* New content ********************** -->
            <div id="ctl00_ContentPlaceHolder1_div_1 "
                 style="font-family: georgia, sans-serif; font-size: 16px; text-align: justify;"><p>
                <strong>INTRODUCTION</strong></p>
                <p>Mechanical engineering is the discipline that applies engineering physics, engineering mathematics,
                    and materials science principles to design, analyse, manufacture, and maintain mechanical systems.
                    It is one of the oldest and broadest of the engineering disciplines.It is known as the evergreen
                    branch among all the branches of engineering department</p>
                <p>The mechanical engineering field requires an understanding of core areas including mechanics,
                    dynamics, thermodynamics, materials science, structural analysis, and electricity. In addition to
                    these core principles, mechanical engineers use tools such as computer-aided design (CAD),
                    computer-aided manufacturing (CAM), and product life cycle management to design and analyse
                    manufacturing plants, industrial equipment and machinery, heating and cooling systems, transport
                    systems, aircraft, watercraft, robotics, medical devices, weapons, and others. It is the branch of
                    engineering that involves the design, production, and operation of machinery.</p>
                <p>Nowadays the scope of mechanical engineering is expanding beyond its traditional boundaries.In order
                    to contribute to the growth of quality mechanical engineers katihar engineering college has set up
                    the department of mechanical engineering in the year 2016.the faculties here are very supportive in
                    nature they hardly fails to inspire the young minds and enrich them with their knowledge and
                    experience.The management keeps a strong check on each faculties and kindle them to attend
                    workshops,seminars,conferences and faculty development program.Students enthusiastically work here
                    under the supervision of trained faculty members.</p>
            </div>
        </div>

    </div>

</div>

</div>
<jsp:include page="/jsp/footer.jsp"/>

