<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/jsp/header.jsp"/>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">

    </div>
    <div class="container">

        <div class="row">
           <aside class="col-sm-3" role="complementary">
                                                                    <div class="region region-sidebar-second">
                                                          <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                                                  <h2 class="block-title">Faculty of of Applied Sc. & Humanities</h2>

                                                            <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                                           <ul class="menu nav">
                                                          <li class="first leaf  menu-mlid-1154"><a href="http://localhost/department/ash/bnm" class="sf-depth-2 active">Dr. B. N. Mahto</a></li>
                                                          <li class="leaf menu-mlid-1121"><a href="http://localhost/department/ash/pk" class="sf-depth-2">Promod Kumar</a></li>
                                                          <li class="leaf menu-mlid-1156"><a href="http://localhost/department/ash/vk" class="sf-depth-2">Dr. Vipin Kumar</a></li>
                                                          <li class="leaf menu-mlid-1294"><a href="http://localhost/department/ash/snk" class="sf-depth-2">Sunil Kumar</a></li>

                                                             </ul></div>

                                                          </section>
                                                            </div>
                                                                </aside>
            <div class="col-md-8">
            <c:forEach var="item" items="${Role}">
                                                <c:if test = "${'Faculty' eq item and UserName eq 'pramod'}">
                                                    <strong><a href="/auth/uploadfile/facultyfileupload?isProfilePic=No" style="margin-right:10px">Upload Documents &nbsp;|</a></strong>
                                                    <strong><a href= "http://localhost/auth/uploadfile/facultyfileupload?isProfilePic=Yes"">Update Profile Details</a></strong>
                                             </c:if>
                                      </c:forEach>
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab"
                                                              data-toggle="tab"><i class="fa fa-user"></i> Profile</a>
                    </li>
                    <li role="presentation"><a href="#downloads" aria-controls="downloads" role="tab" data-toggle="tab"><i
                            class="fa fa-download"></i> Downloads</a></li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="profile"><img alt="" id="profileImage"
                     style="height: 200px;width:250px"src="http://localhost/${facultyId}/image${fileExtension}"/>
                        <h2>Dr. Promod Kumar</h2>
                        <p style="font-size: 130%;">Assistant Professor <br/> Department of Applied Science & Humanities</p>
                        <p><i class="fa fa-phone"></i> 9108006551 &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                                href="mailto:promodbhoola@gmail.com"> <span>promodbhoola@gmail.com</span>
                        </a></p> <br/><br/><div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Qualifications</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>Ph.D(Mathematics) </td>
                                </tr>
                                </tbody>
                            </table>
                        </div><div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Area of Research</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>1. &nbsp &nbspDuality Theory(Pure Mathematics) </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Administrative Responsibilities</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>1. &nbspHOD(Mathematics)</td>

                                </tr>
                                </tbody>
                            </table>
                        </div></div>
                    <div role="tabpanel" class="tab-pane" id="downloads">
                        <div style="padding: 15px;">
                            <div class="text-center">
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
    </div>
<jsp:include page="/jsp/footer.jsp"/>