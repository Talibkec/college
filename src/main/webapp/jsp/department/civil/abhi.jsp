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

                                                        <h2 class="block-title">Faculty of Civil Engg.</h2>

                                                  <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                                 <ul class="menu nav">
                                                <li class="first leaf  menu-mlid-1154"><a href="http://localhost/department/civil/rm" class="sf-depth-2 active">Rashid Mustafa</a></li>

                                                <li class="leaf menu-mlid-1294"><a href="http://localhost/department/civil/akg" class="sf-depth-2">Anil Kumar Gupta</a></li>
                                                <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/rps" class="sf-depth-2">Rana Pratap Singh</a></li>
                                                <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/abhi" class="sf-depth-2">Abhishek Ranjan</a></li>
                                                <li class="leaf menu-mlid-1467"><a href="http://localhost/department/civil/ar" class="sf-depth-2">Aamir Rahi</a></li>
                                                   </ul></div>
                                                </section>
                                                  </div>
                                        </aside>
            <div class="col-md-8">
            <c:forEach var="item" items="${Role}">
                                    <c:if test = "${'Faculty' eq item and UserName eq 'abhishek'}">
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
                    <div role="tabpanel" class="tab-pane active" id="profile"><img alt="AKG" id="profileImage"
                    style="height: 200px;width:250px" src="http://localhost/${facultyId}/image${fileExtension}"/>
                        <h2>${facultyName}</h2>
                        <p style="font-size: 130%;">Assistant Professor</p>
                        <p><i class="fa fa-phone"></i>${facultyMobNo}&nbsp; | &nbsp;, <i class="fa fa-envelope"></i> <a
                                href="mailto:${facultyOfficialEmail}"> <span>${facultyOfficialEmail}</span>
                         &nbsp; | &nbsp;, <i class="fa fa-envelope"></i> <a href="mailto:${facultyPersonalEmail}"> <span>${facultyOfficialEmail}</span>
                        </a></p> <br/><br/>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Qualifications</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbsp;&nbspM.Tech (IITISM  Dhanbad)</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Area of Research</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbsp;&nbspEarthquake, Disaster, Hazard and Risk Mitigation</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                                                </div>
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
    </div>
    </div>
<jsp:include page="/jsp/footer.jsp"/>