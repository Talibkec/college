<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron" style
    "padding-top: 24px; padding-bottom: 24px;"></div>

    <div class="container">

        <div class="row">
            <aside class="col-sm-3" role="complementary">
                                              <div class="region region-sidebar-second">
                                    <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                            <h2 class="block-title">In the Department of Civil Engg.</h2>

                                      <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                     <ul class="menu nav">
                                    <li class="first leaf  menu-mlid-1154"><a href="http://localhost/department/civil/about" class="sf-depth-2 active">About Civil Engg.</a></li>
                                    <li class="leaf menu-mlid-1121"><a href="http://localhost/department/civil/vision" class="sf-depth-2">Vision & Mission</a></li>
                                    <li class="leaf menu-mlid-1156"><a href="http://localhost/department/civil/faculty" class="sf-depth-2">Faculty of Civil Engg.</a></li>
                                    <li class="leaf menu-mlid-1294"><a href="http://localhost/department/civil/labs" class="sf-depth-2">Labs & Infrastructure</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/question" class="sf-depth-2">Question Bank</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/awards" class="sf-depth-2">awards & Recognition</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/programs" class="sf-depth-2">Program Activities</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/students" class="sf-depth-2">Student of Civil Engg.</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/notice" class="sf-depth-2">Notice</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/weekly" class="sf-depth-2">Weekly Test Result</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/programs" class="sf-depth-2">Course File</a></li>
                                     <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/staff" class="sf-depth-2">Staff of Civil Engg.</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/attendance" class="sf-depth-2">Attendance Summary</a></li>
                                       </ul></div>

                                    </section>
                                      </div>
                                          </aside>

            <div class="col-md-8">
                <div class="page-content"><h2>Faculty Members</h2></div>
                    <hr/>

                    <c:forEach items ="${facultyList}" var ="faculty">
                    <div class="panel panel-default">
                        <div class="panel-heading faculty-title"> ${faculty.facultyName}</div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-3 box-profile-image"><img alt="" src="http://localhost/${faculty.facultyId}/image.${faculty.fileType}"
                                                                             style="background-image: url(rps.JPG)"/>
                                    <div class="visible-xs"><br/></div>
                                </div>

                                <div class="col-sm-6 box-profile-info"><strong>Department:</strong> Department of Civil
                                    Engineering<br/> <strong>Designation:</strong> Assistant Professor<br/> <strong>Phone Number:</strong>${faculty.facultyMobNo}<br/> <strong>Email
                                        Address:</strong> <a href="mailto:${faculty.facultyOfficialEmail}">${faculty.facultyOfficialEmail}</a>
                                    <div class="visible-xs"><br/></div>
                                </div>
                                <div class="col-sm-3 box-profile-link"><a href="http://localhost/facultyDetails?facultyId=${faculty.facultyId}"
                                                                          class="btn btn-default btn-lg"> <br
                                        class="hidden-xs"/> <i class="fa fa-link"></i> Visit<br class="hidden-xs"/>
                                    Profile Page </a></div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>

					<div class="panel panel-default">
                        <div class="panel-heading faculty-title">Abhishek Ranjan</div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-3 box-profile-image"><img alt="" src="http://localhost/25/image${fileExtension}"
                                                                             style="background-image: url(dummy.png)"/>
                                    <div class="visible-xs"><br/></div>
                                </div>
                                <div class="col-sm-6 box-profile-info"><strong>Department:</strong> Department of Civil
                                    Engineering<br/> <strong>Designation:</strong> Assistant Professor(Guest)<br/> <strong>Phone
                                        Number:</strong>7992284589,8235585606<br/> <strong>Email Address:</strong> <a
                                            href="mailto:finestabhi@gmail.com">finestabhi@gmail.com</a>
                                    <div class="visible-xs"><br/></div>
                                </div>
                                <div class="col-sm-3 box-profile-link"><a href="http://localhost/fecultyDetails?fa"
                                                                          class="btn btn-default btn-lg"> <br
                                        class="hidden-xs"/> <i class="fa fa-link"></i> Visit<br class="hidden-xs"/>
                                    Profile Page </a></div>
                            </div>
                        </div>
                    </div>
                <div class="panel panel-default">
                                        <div class="panel-heading faculty-title">Aale Rasul</div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-sm-3 box-profile-image"><img alt="" src="http://localhost/25/image${fileExtension}"
                                                                                             style="background-image: url(dummy.png)"/>
                                                    <div class="visible-xs"><br/></div>
                                                </div>
                                                <div class="col-sm-6 box-profile-info"><strong>Department:</strong> Department of Civil
                                                    Engineering<br/> <strong>Designation:</strong> Assistant Professor(Guest)<br/> <strong>Phone
                                                        Number:</strong>9507994379<br/> <strong>Email Address:</strong> <a
                                                            href="aalerasulnitp@gmail.com">aalerasulnitp@gmail.com</a>
                                                    <div class="visible-xs"><br/></div>
                                                </div>
                                                <div class="col-sm-3 box-profile-link"><a href="http://localhost/department/civil/ar"
                                                                                          class="btn btn-default btn-lg"> <br
                                                        class="hidden-xs"/> <i class="fa fa-link"></i> Visit<br class="hidden-xs"/>
                                                    Profile Page </a></div>
                                            </div>
                                        </div>
                                  </div>

                                  <div class="panel panel-default">
                                                                  <div class="panel-heading faculty-title">Shashank shekhar sandilya</div>
                                                                  <div class="panel-body">
                                                                      <div class="row">
                                                                          <div class="col-sm-3 box-profile-image"><img alt="" src="http://localhost/25/image${fileExtension}"
                                                                                                                       style="background-image: url(dummy.png)"/>
                                                                              <div class="visible-xs"><br/></div>
                                                                          </div>
                                                                          <div class="col-sm-6 box-profile-info"><strong>Department:</strong> Department of Civil
                                                                              Engineering<br/> <strong>Designation:</strong> Assistant Professor(Guest)<br/> <strong>Phone
                                                                                  Number:</strong>8149241838<br/> <strong>Email Address:</strong> <a
                                                                                      href="mailto:shashankshekhar@gmail.com">shashankshekhar55555@gmail.com</a>
                                                                              <div class="visible-xs"><br/></div>
                                                                          </div>
                                                                          <div class="col-sm-3 box-profile-link"><a href="http://localhost/department/civil/sss"
                                                                                                                    class="btn btn-default btn-lg"> <br
                                                                                  class="hidden-xs"/> <i class="fa fa-link"></i> Visit<br class="hidden-xs"/>
                                                                              Profile Page </a></div>
                                                                      </div>
                                                                  </div>
                                                              </div>
                                                               <div class="panel panel-default">
                                                                      <div class="panel-heading faculty-title">Krishna kant Thakur</div>
                                                                             <div class="panel-body">                                                                                                               <div class="row">
                                                                                  <div class="col-sm-3 box-profile-image"><img alt="" src="http://localhost/25/image${fileExtension}"
                                                                                                                                                                                     style="background-image: url(dummy.png)"/>
                                                                                              <div class="visible-xs"><br/></div>
                                                                                                                                        </div>
                                                                                                                                        <div class="col-sm-6 box-profile-info"><strong>Department:</strong> Department of Civil
                                                                                                                                            Engineering<br/> <strong>Designation:</strong> Assistant Professor(Guest)<br/> <strong>Phone
                                                                                                                                                Number:</strong>6202559358<br/> <strong>Email Address:</strong> <a
                                                                                                                                                    href="mailto:kk350thakur@gmail.com">kk350thakur@gmail.com</a>
                                                                                                                                            <div class="visible-xs"><br/></div>
                                                                                                                                        </div>
                                                                                                                                        <div class="col-sm-3 box-profile-link"><a href="http://localhost/department/civil/kkk"
                                                                                                                                                                                  class="btn btn-default btn-lg"> <br
                                                                                                                                                class="hidden-xs"/> <i class="fa fa-link"></i> Visit<br class="hidden-xs"/>
                                                                                                                                            Profile Page </a></div>
                                                                                                                                    </div>
                                                                                                                                </div>
                                                                                                                            </div
    </div>
    </div>
<jsp:include page="/jsp/footer.jsp"/>
