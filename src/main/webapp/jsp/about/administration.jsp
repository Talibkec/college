<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron flex justify-center w-full">
        <div class="">
            <div class="">
                <div class=""><h3 class="text-2xl" >College Administration</h3></div>

            </div>
        </div>
   </div>
    <div class="w-full flex justify-center">
        <a href="administration.html"><i class="fa fa-home"></i> Home</a>
        </ul>
        <div class="m-2 p-3 ">
            <aside class="col-sm-3" role="complementary">
                                             <div class="region region-sidebar-second">
                                   <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                           <h2 class="block-title">About KEC</h2>

                                     <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                    <ul class="menu nav">
                                   <li class="first leaf  menu-mlid-1154"><a href="/about/principal" class="sf-depth-2 active">Principal's Message</a></li>
                                   <li class="leaf menu-mlid-1121"><a href="/about/vision" class="sf-depth-2">Vision & Mission</a></li>
                                   <li class="leaf menu-mlid-1156"><a href="/about/allotment" class="sf-depth-2">Allotment &Surrender Report</a></li>
                                   <li class="leaf menu-mlid-1294"><a href="/about/affiliation" class="sf-depth-2">Affiliation</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="/about/administration" class="sf-depth-2">Administration</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="/about/staff" class="sf-depth-2">Support Staff</a></li>
                                   <li class="leaf menu-mlid-1467"><a href="/about/history" class="sf-depth-2">History</a></li>

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
                                     <div class="col-sm-3 box-profile-image"><img alt="" src="/${Details.faculty.facultyId}/image/${fileExtension}"/>
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
                                   <div class="col-sm-3 box-profile-link"><a href="/facultyDetails?facultyId=${Details.faculty.facultyId}"class="btn btn-default btn-lg">
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
