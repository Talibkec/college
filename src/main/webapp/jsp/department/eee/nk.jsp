<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/jsp/header.jsp"/>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>Naveen Kumar</h3></div>

            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
             <aside class="col-sm-3" role="complementary">
                                                                                 <div class="region region-sidebar-second">
                                                                       <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                                                               <h2 class="block-title"> Faculty of Electrical & Electronics Engg.</h2>

                                                                         <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                                                        <ul class="menu nav">
                                                                       <li class="first leaf  menu-mlid-1154"><a href="http://localhost/department/eee/principal" class="sf-depth-2 ">Prof. Ranjana Kumari</a></li>
                                                                       <li class="leaf menu-mlid-1121"><a href="http://localhost/department/eee/rk" class="sf-depth-2">Dr. Ram Kumar</a></li>
                                                                       <li class="leaf menu-mlid-1156"><a href="http://localhost/department/eee/aks" class="sf-depth-2">Amit Kumar Suman</a></li>
                                                                       <li class="leaf menu-mlid-1294"><a href="http://localhost/department/eee/ak" class="sf-depth-2">Abhijeet Kumar</a></li>
                                                                       <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/aa" class="sf-depth-2">Alauddin Ansari</a></li>
                                                                       <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/sa" class="sf-depth-2">Sajjad Ahmad</a></li>
                                                                       <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/ss" class="sf-depth-2">Saket Saurabh</a></li>
                                                                       <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/tr" class="sf-depth-2">Tarique Rashid</a></li>
                                                                       <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/rdr" class="sf-depth-2">Rajeev Dev Ranjan</a></li>
                                                                       <li class="leaf menu-mlid-1467"><a href="http://localhost/department/eee/nk" class="sf-depth-2">Naveen Kumar</a></li>

                                                                          </ul></div>

                                                                       </section>
                                                                         </div>
                                                                             </aside>

            <div class="col-md-8">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab"
                                                              data-toggle="tab"><i class="fa fa-user"></i> Profile</a>
                    </li>
                    <li role="presentation"><a href="#downloads" aria-controls="downloads" role="tab" data-toggle="tab"><i
                            class="fa fa-download"></i> Downloads</a></li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="profile"><img alt="" id="profileImage"  style="height: 200px;width:250px"
                                                                                                                                                                                               src="http://localhost/${facultyId}/image${fileExtension}"/>
                        <h2>${facultyName}</h2>
                        <p style="font-size: 130%;">Lab Assistant<br/> Department of Electrical and Electronics
                            Engineering<br></p>
                        <p><i class="fa fa-phone"></i>${facultyMobNo}&nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a href="mailto:${facultyPersonalEmail}"> <span> ${facultyPersonalEmail}</span>
               &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a href="mailto:${facultyOfficialEmail}"> <span> ${facultyOfficialEmail}</span>
                        </a></p> <br/><br/>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Qualifications</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>M.Tech(Power System)</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        </div>
                    <div role="tabpanel" class="tab-pane" id="downloads">
                        <div style="padding: 15px;">
                             <div class="">
                                                           <c:forEach items="${facultyDocument}" var="facultyDocument">
                                                           <div>
                                                            <span class="label" style="border:  solid 1px #ccc; color: #000;">
                                                             <c:set var = "clazz"  value = "label label-warning"/>
                                                              <i class=""></i> ${facultyDocument.date} </span>&nbsp;
                                                              <c:if test = "${Role == 'Faculty'}">
                                                              <span class="label label-danger"><a href="http://localhost/auth/deleteFacultyDoc/${facultyDocument.id}">Delete</a></span>
                                                              </c:if>
                                                              <a href="http://localhost/${facultyDocument.id}/image${facultyDocument.fileType}" target="_blank">${facultyDocument.headLine}</a>
                                                              </div>
                                                              </c:forEach>
                                                             </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="/jsp/header.jsp"/>
