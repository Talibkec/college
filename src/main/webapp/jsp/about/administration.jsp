<jsp:include page="/jsp/header.jsp"/>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>College Administration</h3></div>

            </div>
        </div>
   </div>
    <div class="container">
        <a href="administration.html"><i class="fa fa-home"></i> Home</a>
        </ul>
        <div class="row">
            <aside class="col-sm-3" role="complementary">
                                             <div class="region region-sidebar-second">
                                   <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                           <h2 class="block-title">About KEC</h2>

                                     <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                    <ul class="menu nav">
                                   <li class="first leaf  menu-mlid-1154"><a href="http://localhost/about/principal" class="sf-depth-2 active">Principal's Message</a></li>
                                   <li class="leaf menu-mlid-1121"><a href="http://localhost/about/vision" class="sf-depth-2">Vision & Mission</a></li>
                                   <li class="leaf menu-mlid-1156"><a href="http://localhost/about/allotment" class="sf-depth-2">Allotment &Surrender Report</a></li>
                                   <li class="leaf menu-mlid-1294"><a href="http://localhost/about/affiliation" class="sf-depth-2">Affiliation</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="http://localhost/about/administration" class="sf-depth-2">Administration</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="http://localhost/about/staff" class="sf-depth-2">Support Staff</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="http://localhost/about/history" class="sf-depth-2">History</a></li>

                                      </ul></div>

                                   </section>
                                     </div>
                             </aside>
                                   <div class="col-md-8">
                        <div class="page-content"><h2>Administrative Responsibilities</h2>
                                 <hr/>
                     <c:forEach items="${adminsDetails}" var="Details">
                        <div class="panel panel-default">
                           <div class="panel-heading faculty-title">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<strong>${Details.role}</strong></div>
                                <div class="panel-body">
                                   <div class="row">
                                     <div class="col-sm-3 box-profile-image"><img alt="" src="http://localhost/${Details.faculty.facultyId}/image/${fileExtension}"/>
                                        <div class="visible-xs"><br/></div>
                                     </div>
                                   <div class="col-sm-6 box-profile-info"> <strong>${Details.faculty.facultyName}</strong><br/> <strong>Designation:</strong> ${Details.designation}<br/> <strong>Duty Assigned:</strong>${Details.dutyAssigned}
                                    <br/> <strong>Phone Number:</strong>${Details.faculty.facultyMobNo}<br/>


                                        <c:forEach var="item" items="${Role}">
                                                                                           <c:if test = "${'Admin' eq item}">
                                                                                           <span class="label label-danger"><a href="<c:url value='/auth/deleteAdminRole/${Details.adminRoleId}'/>">Delete</a></span>
                                                                                        </c:if>
                                                                                       </c:forEach>
                                     <div class="visible-xs"><br/></div>

                                   </div>
                                   <div class="col-sm-3 box-profile-link"><a href="http://localhost/facultyDetails?facultyId=${Details.faculty.facultyId}"class="btn btn-default btn-lg">
                                   <br class="hidden-xs"/> <i class="fa fa-link"></i> Visit<br class="hidden-xs"/>Profile Page </a></div>




                               </div>
                           </div>
                        </div>
                     </c:forEach>
                </div>
            </div>
        </div>
    </div>
 </div>
<jsp:include page="/jsp/footer.jsp"/>