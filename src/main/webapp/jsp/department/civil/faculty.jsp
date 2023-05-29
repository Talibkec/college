<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron" style
    "padding-top: 24px; padding-bottom: 24px;"></div>

    <div class="container">

        <div class="row">
            <jsp:include page="../sidebars/cesidebar.jsp"/>

            <div class="col-md-8">
                <div class="page-content"><h2>Faculty Members</h2></div>
                    <hr/>

                    <c:forEach items ="${facultyList}" var ="faculty">
                    <div class="panel panel-default">
                        <div class="panel-heading faculty-title"> ${faculty.facultyName}</div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-3 box-profile-image"><img alt="" src="/${faculty.facultyId}/image.${faculty.fileType}"
                                                                             style="background-image: url(rps.JPG)"/>
                                    <div class="visible-xs"><br/></div>
                                </div>

                                <div class="col-sm-6 box-profile-info"><strong>Department:</strong> Department of Civil
                                    Engineering<br/> <strong>Designation:</strong> Assistant Professor<br/> <strong>Phone Number:</strong>${faculty.facultyMobNo}<br/> <strong>Email
                                        Address:</strong> <a href="mailto:${faculty.facultyOfficialEmail}">${faculty.facultyOfficialEmail}</a>
                                    <div class="visible-xs"><br/></div>
                                </div>
                                <div class="col-sm-3 box-profile-link"><a href="/facultyDetails?facultyId=${faculty.facultyId}"
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
                                <div class="col-sm-3 box-profile-image"><img alt="" src="/25/image${fileExtension}"
                                                                             style="background-image: url(dummy.png)"/>
                                    <div class="visible-xs"><br/></div>
                                </div>
                                <div class="col-sm-6 box-profile-info"><strong>Department:</strong> Department of Civil
                                    Engineering<br/> <strong>Designation:</strong> Assistant Professor(Guest)<br/> <strong>Phone
                                        Number:</strong>7992284589,8235585606<br/> <strong>Email Address:</strong> <a
                                            href="mailto:finestabhi@gmail.com">finestabhi@gmail.com</a>
                                    <div class="visible-xs"><br/></div>
                                </div>
                                <div class="col-sm-3 box-profile-link"><a href="/fecultyDetails?fa"
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
                                                <div class="col-sm-3 box-profile-image"><img alt="" src="/25/image${fileExtension}"
                                                                                             style="background-image: url(dummy.png)"/>
                                                    <div class="visible-xs"><br/></div>
                                                </div>
                                                <div class="col-sm-6 box-profile-info"><strong>Department:</strong> Department of Civil
                                                    Engineering<br/> <strong>Designation:</strong> Assistant Professor(Guest)<br/> <strong>Phone
                                                        Number:</strong>9507994379<br/> <strong>Email Address:</strong> <a
                                                            href="aalerasulnitp@gmail.com">aalerasulnitp@gmail.com</a>
                                                    <div class="visible-xs"><br/></div>
                                                </div>
                                                <div class="col-sm-3 box-profile-link"><a href="/department/civil/ar"
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
                                                                          <div class="col-sm-3 box-profile-image"><img alt="" src="/25/image${fileExtension}"
                                                                                                                       style="background-image: url(dummy.png)"/>
                                                                              <div class="visible-xs"><br/></div>
                                                                          </div>
                                                                          <div class="col-sm-6 box-profile-info"><strong>Department:</strong> Department of Civil
                                                                              Engineering<br/> <strong>Designation:</strong> Assistant Professor(Guest)<br/> <strong>Phone
                                                                                  Number:</strong>8149241838<br/> <strong>Email Address:</strong> <a
                                                                                      href="mailto:shashankshekhar@gmail.com">shashankshekhar55555@gmail.com</a>
                                                                              <div class="visible-xs"><br/></div>
                                                                          </div>
                                                                          <div class="col-sm-3 box-profile-link"><a href="/department/civil/sss"
                                                                                                                    class="btn btn-default btn-lg"> <br
                                                                                  class="hidden-xs"/> <i class="fa fa-link"></i> Visit<br class="hidden-xs"/>
                                                                              Profile Page </a></div>
                                                                      </div>
                                                                  </div>
                                                              </div>
                                                               <div class="panel panel-default">
                                                                      <div class="panel-heading faculty-title">Krishna kant Thakur</div>
                                                                             <div class="panel-body">                                                                                                               <div class="row">
                                                                                  <div class="col-sm-3 box-profile-image"><img alt="" src="/25/image${fileExtension}"
                                                                                                                                                                                     style="background-image: url(dummy.png)"/>
                                                                                              <div class="visible-xs"><br/></div>
                                                                                                                                        </div>
                                                                                                                                        <div class="col-sm-6 box-profile-info"><strong>Department:</strong> Department of Civil
                                                                                                                                            Engineering<br/> <strong>Designation:</strong> Assistant Professor(Guest)<br/> <strong>Phone
                                                                                                                                                Number:</strong>6202559358<br/> <strong>Email Address:</strong> <a
                                                                                                                                                    href="mailto:kk350thakur@gmail.com">kk350thakur@gmail.com</a>
                                                                                                                                            <div class="visible-xs"><br/></div>
                                                                                                                                        </div>
                                                                                                                                        <div class="col-sm-3 box-profile-link"><a href="/department/civil/kkk"
                                                                                                                                                                                  class="btn btn-default btn-lg"> <br
                                                                                                                                                class="hidden-xs"/> <i class="fa fa-link"></i> Visit<br class="hidden-xs"/>
                                                                                                                                            Profile Page </a></div>
                                                                                                                                    </div>
                                                                                                                                </div>
                                                                                                                            </div
    </div>
    </div>
<jsp:include page="/jsp/footer.jsp"/>
