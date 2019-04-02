<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">

    <div class="container">

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
            <div class="row">
                                                <div class="col-sm-8">
                                                    <c:forEach items="${noticeList}" var="notice">

                                                        <div class="post-entry panel panel-default">
                                                            <div class="panel-heading"></h4></div>
                                                            <div class="panel-body">
                                                                <div class="text-left" style="font-size: 120%; line-height: 150%;"> ${notice.headLine}</div>
                                                                <br/> <a href="http://keck.ac.in/${notice.id}/notice${notice.fileType}" target="_blank"
                                                                    class="btn btn-default pull-right">Download</a> <span class="label label-default"
                                                                                                                           style="font-size: 100%"><i
                                                                    class="fa fa-clock-o"></i>${notice.date} </span>
                                                                <div class="clearfix"></div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                       <c:if test="${empty pageSize}">
                                                                                                <c:set var = "pageSize" value = "${0}"/>
                                                                                            </c:if>

                                                   <ul class="pager">
                                                    <li><a href="/pagination/prev/getNotices?deptno=civil&pageSize=${pageSize - 1}"><i class="fa fa-angle-double-left"></i> Previous</a></li>
                                                     <li><a href="/pagination/next/getNotices?deptno=civil&pageSize=${pageSize + 1}">Next <i class="fa fa-angle-double-right"></i></a>
                                                     </li>
                                                    </ul>
                                                </div>
                                            </div>
            </div>
        </div>
    </div>
<jsp:include page="/jsp/footer.jsp"/>