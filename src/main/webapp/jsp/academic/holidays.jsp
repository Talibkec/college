<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp"/>
<div class="jumbotron flex justify-center w-full">
        <div class="">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3 class="text-2xl">List of Holidays</h3></div>
            </div>
        </div>
    </div>
    <div class="container">

        <div class="md:flex justify-center">
            <aside class="col-sm-3" role="complementary"  style="">
                                                                            <div class="region region-sidebar-second">
                                                                  <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                                                          <h2 class="block-title">Academics</h2>

                                                                    <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                                                   <ul class="menu nav">
                                                                  <li class="first leaf  menu-mlid-1154"><a href="/academic/admission" class="sf-depth-2 active">UG Admission</a></li>
                                                                  <li class="leaf menu-mlid-1121"><a href="/academic/regulations" class="sf-depth-2">Academic Regulations</a></li>
                                                                  <li class="leaf menu-mlid-1156"><a href="/academic/calender" class="sf-depth-2">Academic Calender</a></li>
                                                                  <li class="leaf menu-mlid-1294"><a href="/academic/holidays" class="sf-depth-2">List of Holidays</a></li>
                                                                  <li class="leaf menu-mlid-1467"><a href="/academic/ attendance" class="sf-depth-2">Attendance</a></li>
                                                                  <li class="leaf menu-mlid-1467"><a href="/academic/notice" class="sf-depth-2">Notice From Govt.</a></li>
                                                                  <li class="leaf menu-mlid-1467"><a href="/academic/rules" class="sf-depth-2">Disciplinary Rules</a></li>
                                                                  <li class="leaf menu-mlid-1467"><a href="/academic/academiccouncil" class="sf-depth-2">MoM of Academic Council</a></li>
                                                                  <li class="leaf menu-mlid-1467"><a href="/academic/antiragging" class="sf-depth-2">Anti Ragging</a></li>
                                                                  <li class="leaf menu-mlid-1467"><a href="/academic/universityresults" class="sf-depth-2">University Result</a></li>
                                                                  <li class="leaf menu-mlid-1467"><a href="/academic/mentors" class="sf-depth-2">Mentors List</a></li>

                                                                     </ul></div>

                                                                  </section>
                                                                    </div>
                                                                        </aside>

            <div class="col-md-8 p-3"  style="">

                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">

                    <table style="width: 100%; padding: 10px;">



                        <tbody>
                        <tr bgcolor="#7eec35">
                            <td><strong>SL. No.</strong></td>
                            <td><strong>Year</strong></td>
                            <td >Download</td>
                            <td>
                                <c:if test="${showDeleteBtn}">
                                    Delete
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <c:set var="count" value='1'/>
                        <c:set var="embedpdf" value='true'/>
                        <c:forEach items="${allDocuments}" var ="document">

                            <c:if test="${embedpdf}">

                                <div>
                                    <object data="https://drive.google.com/viewerng/viewer?embedded=true&url=https://keck.ac.in/approval/holiday/${document.id}" type="application/pdf" width="700" height="1000">
                                        <embed src="https://drive.google.com/viewerng/viewer?embedded=true&url=https://keck.ac.in/approval/holiday/${document.id}" type="application/pdf">
                                    </object>
                                </div>

                            </c:if>
                            <c:set var="embedpdf" value="${false}"/>
                            <tr bgcolor="#e9feea">
                                <td>${count}</td>
                                <c:set var="count" value="${count+1}"/>
                                <td>${document.fileName}</td>
                                    <%--                                <td >${document.uploadedBy}</td>--%>

                                <td align="center">
                                    <i class="fa fa-download"></i>
                                    <a href="/approval/holiday/${document.id}">
                                        <button type="button" class="btn btn-success" data-dismiss="modal">Download
                                        </button></a>
                                </td><td>
                                <c:if test="${showDeleteBtn}">
                                    <a href="/approval/deleteHoliday/${document.id}">
                                        <button class="btn btn-danger mt-2 p-2"> Delete Document</button>
                                    </a>
                                </c:if>

                            </td>
                            </tr>

                        </c:forEach>




                    </table>



                </div>
            </div>
        </div>
    </div>


<jsp:include page="/jsp/footer.jsp"/>
