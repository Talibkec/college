<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/jsp/header.jsp" />
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
                            <li class="first leaf  menu-mlid-1154"><a href="http://keck.ac.in/department/civil/rm"
                                    class="sf-depth-2 active">Rashid Mustafa</a></li>

                            <li class="leaf menu-mlid-1294"><a href="http://keck.ac.in/department/civil/akg"
                                    class="sf-depth-2">Anil Kumar Gupta</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/rps"
                                    class="sf-depth-2">Rana Pratap Singh</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/abhi"
                                    class="sf-depth-2">Abhishek Ranjan</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/ar"
                                    class="sf-depth-2">Aale rasul</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/kkk"
                                    class="sf-depth-2">krishna kant Thakur</a></li>
                            <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/civil/sss"
                                    class="sf-depth-2">Shanhank shekhar sandilya</a></li>
                        </ul>
                    </div>
                </section>
            </div>
        </aside>
        <div class="col-md-8">
            <c:forEach var="item" items="${Role}">
                <c:if test="${'Faculty' eq item and UserName eq 'krishna'}">
                    <strong><a href="http://keck.ac.in/uploadfile/facultyFileUpload?isProfilePic=No"
                            style="margin-right:10px">Upload Documents &nbsp;|</a></strong>
                    <strong><a href="http://keck.ac.in/uploadfile/facultyFileUpload?isProfilePic=Yes"">Update Profile Details</a></strong>
                                 </c:if>
                          </c:forEach>
                <ul class=" nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab"
                                    data-toggle="tab"><i class="fa fa-user"></i> Profile</a>
                            </li>
                            <li role="presentation"><a href="#downloads" aria-controls="downloads" role="tab"
                                    data-toggle="tab"><i class="fa fa-download"></i> Downloads</a></li>
                            <c:forEach var="item" items="${Role}">
                                <c:if test="${'Faculty' eq item and UserName eq 'krishna'}">
                                    <li role="presentation"><a href="#order" aria-controls="order" role="tab"
                                            data-toggle="tab"><i class="fa fa-angle-double-right"></i> Request</a></li>
                                </c:if>
                            </c:forEach>
                            </ul>
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="profile"><img alt="kkk"
                                        id="profileImage" style="height: 200px;width:250px"
                                        src="http://keck.ac.in/${facultyId}/image${fileExtension}" />
                                    <h2>${facultyName}</h2>
                                    <p style="font-size: 130%;">Assistant Professor</p>
                                    <p><i class="fa fa-phone"></i>${facultyMobNo}&nbsp; | &nbsp; <i
                                            class="fa fa-envelope"></i> <a href="mailto:${facultyOfficialEmail}">
                                            <span>${facultyOfficialEmail}</span>
                                            &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                                                href="mailto:${facultyPersonalEmail}">
                                                <span>${facultyPersonalEmail}</span>
                                            </a></p> <br /><br />
                                    <div class="panel panel-default facultyInfo">
                                        <div class="panel-heading"><Strong>Qualifications</Strong></div>
                                        <table class="table table-striped">
                                            <tbody>
                                                <tr>
                                                    <td>&nbsp;&nbspB.Tech (Indian Institue of technology and management)
                                                    </td>
                                                <tr>
                                                    <td>&nbsp;&nbspM.Tech (NIT silchar)</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="panel panel-default facultyInfo">
                                        <div class="panel-heading"><Strong>Area of Research</Strong></div>
                                        <table class="table table-striped">
                                            <tbody>
                                                <tr>
                                                    <td>&nbsp;&nbspStrength evaluation of lime stablised Rice Husk Ash
                                                        blended clayey soil </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="panel panel-default facultyInfo">
                                        <div class="panel-heading"><Strong>Professional Experience</Strong></div>
                                        <table class="table table-striped">
                                            <tbody>
                                                <tr>
                                                    <td>&nbsp;&nbsp2 years Teaching Experience</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="panel panel-default facultyInfo">
                                        <div class="panel-heading"><Strong>Subjects Taught</Strong></div>
                                        <table class="table table-striped">
                                            <tbody>
                                                <tr>
                                                    <td> Surveying</td>
                                                </tr>
                                                <tr>
                                                    <td>Engineering Mechanics</td>
                                                </tr>
                                                <tr>
                                                    <td>Enviromrntal Engineering</td>
                                                </tr>
                                                <tr>
                                                    <td>Structural Analysis</td>
                                                </tr>

                                                <tr>
                                                    <td>Soil Mechanics</td>
                                                </tr>
                                                <tr>
                                                    <td>Strength of Material</td>
                                                </tr>
                                                <tr>
                                                    <td>Engineering Geology</td>
                                                </tr>
                                                <tr>
                                                    <td>AutoCAD in civil Engineering</td>
                                                </tr>

                                                <td><b><u>LAB Subjects</u> </b></td>
                                                </tr>
                                                <tr>
                                                    <td>Soil Mechanics Lab</td>
                                                </tr>
                                                <tr>
                                                    <td>Surveying Lab</td>
                                                </tr>
                                                <tr>
                                                    <td>Building Material lab</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div class="panel panel-default facultyInfo">
                                        <div class="panel-heading"><Strong>Career</Strong></div>
                                        <table class="table table-striped">
                                            <tbody>
                                                <tr>
                                                    <td>Assistant professor(Guest),Department of Civil Engineering, KEC,
                                                        Katihar, India ,sep-2019-Till date</td>
                                                </tr>
                                                <tr>
                                                    <td>lecturer, Department of Civil Engineering,IITM Kanpur(UPTP), up,
                                                        India,sep 2014-july 2016
                                                </tr>
                                                </td>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div role="tabpanel" class="tab-pane" id="downloads">
                                        <div style="padding: 15px;">
                                            <div class="">
                                                <c:forEach items="${facultyDocument}" var="facultyDocument">
                                                    <div>
                                                        <span class="label"
                                                            style="border:  solid 1px #ccc; color: #000;">
                                                            <c:set var="clazz" value="label label-warning" />
                                                            <i class=""></i> ${facultyDocument.date} </span>&nbsp;
                                                        <c:forEach var="item" items="${Role}">
                                                            <c:if test="${'Faculty' eq item and UserName eq 'krishna'}">
                                                                <span class="label label-danger"><a
                                                                        href="http://keck.ac.in/common/deleteFacultyDoc/${facultyDocument.id}">Delete</a></span>
                                                            </c:if>
                                                        </c:forEach>

                                                    <c:choose>
                                                        <c:when test="${facultyDocument.link}">
                                                            <a href="${facultyDocument.linkAddress}"
                                                                target="_blank">${facultyDocument.headLine}</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="http://keck.ac.in/${facultyDocument.id}/documents${facultyDocument.fileType}"
                                                                target="_blank">${facultyDocument.headLine}</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="" style>
                                        <c:forEach items="${requests}" var="request">
                                            <div class="row requests" style="border:1px solid green;padding:10px">
                                                <div class="col-md-2 text-center">${request.product.productName}</div>
                                                <div class="col-md-1 text-center">${request.productQuantity}</div>
                                                <div class="col-md-1 text-center" id="statusId${request.requestId}">
                                                    ${request.status}</div>
                                                <div class="col-md-2 text-center">
                                                    <fmt:formatDate value="${request.requestDate}"
                                                        pattern="dd/MM/yyyy" />
                                                </div>
                                                <div class="col-md-2 text-center">
                                                    <fmt:formatDate value="${request.approvalDate}"
                                                        pattern="dd/MM/yyyy" />
                                                </div>
                                                <div class="col-md-2 text-center"><a
                                                        href="http://keck.ac.in/fstore/faculty/editfacultyrequest?id=${request.requestId}"
                                                        id="editId${request.requestId}">Edit</a></div>
                                                <div class="col-md-2 text-center"><a
                                                        href="http://keck.ac.in/fstore/faculty/deletefacultyrequest?requestId=${request.requestId}">Delete</a>
                                                </div>
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
<jsp:include page="/jsp/footer.jsp" />
<script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="http://keck.ac.in/js/jquery-ui.js"></script>
<script src="http://keck.ac.in/js/facultyprofile.js"></script>