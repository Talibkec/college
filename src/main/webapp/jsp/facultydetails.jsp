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
                    <c:if test="${facultyDetails.departmentId eq '1'}">
                        <h2 class="block-title">Faculty of Computer Sc. & Engg.</h2>
                    </c:if>
                    <c:if test="${facultyDetails.departmentId eq '2'}">
                        <h2 class="block-title">Faculty of Civil Engg.</h2>
                    </c:if>
                    <c:if test="${facultyDetails.departmentId eq '3'}">
                        <h2 class="block-title">Faculty of Mechanical Engg.</h2>
                    </c:if>
                    <c:if test="${facultyDetails.departmentId eq '4'}">
                        <h2 class="block-title"> Faculty of Electrical & Electronics Engg.</h2>
                    </c:if>
                    <c:if test="${facultyDetails.departmentId eq '5'}">
                        <h2 class="block-title"> Faculty of Applied Sc. & Humanities</h2>
                    </c:if>
                      <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                        <ul class="menu nav">
                            <c:forEach items="${facultyNameList}" var="entry">
                                <c:forEach items="${entry}" var="entryVal">
                                    <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/facultyDetails?facultyId=${entryVal.value}"class="sf-depth-2">${entryVal.key}</a></li>
                                </c:forEach>
                            </c:forEach>
                        </ul>
                    </div>
                </section>
            </div>
        </aside>

        <div class="col-md-8">
            <c:forEach items="${Role}" var='role'>
                <c:if test="${fUserName eq  loggedInUser and 'Faculty' eq role}">
                    <strong><a href="/uploadfile/facultyFileUpload?isProfilePic=No" style="margin-right:10px">Upload
                        Documents &nbsp;|</a></strong>
                    <strong><a href="http://keck.ac.in/uploadfile/facultyFileUpload?isProfilePic=Yes"">Update Profile
                        Details &nbsp;|</a></strong>
                    <strong><a href="http://keck.ac.in/uploadfile/editfacultydetails?facultyId=${facultyDetails.facultyId}"">Edit Profile Details</a></strong>
                </c:if>
            </c:forEach>


            <ul class=" nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab"
                                                          data-toggle="tab"><i class="fa fa-user"></i> Profile</a>
                </li>
                <li role="presentation"><a href="#downloads" aria-controls="downloads" role="tab"
                                           data-toggle="tab"><i class="fa fa-download"></i> Downloads</a></li>
                <c:forEach var="item" items="${Role}">
                    <c:if test="${'Faculty' eq item and fUserName eq loggedInUser}">
                        <li role="presentation"><a href="#order" aria-controls="order" role="tab"
                                                   data-toggle="tab"><i class="fa fa-angle-double-right"></i>
                            Request</a></li>
                    </c:if>
                </c:forEach>
            </ul>

            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="profile"><img alt="" id="profileImage"style="height: 200px;width:250px" src="http://keck.ac.in/${facultyDetails.facultyId}/image${fileExtension}"/>
                    <h2>${facultyDetails.facultyName}</h2>
                    <p style="font-size: 130%;">Assistant Professor <br/>
                        <c:if test="${facultyDetails.departmentId eq '1'}">
                            Department of Computer Sc. & Engineering
                        </c:if>
                        <c:if test="${facultyDetails.departmentId eq '2'}">
                            Department of Civil Engineering
                        </c:if>
                        <c:if test="${facultyDetails.departmentId eq '3'}">
                            Department of Mechanical Engineering
                        </c:if>
                        <c:if test="${facultyDetails.departmentId eq '4'}">
                            Department of Electrical and Electronics Engineering
                        </c:if>
                        <c:if test="${facultyDetails.departmentId eq '5'}">
                            Department of Applied Science & Humanities
                        </c:if>
                    <p><i class="fa fa-phone">${facultyDetails.facultyMobNo}</i> &nbsp; | &nbsp; <i
                            class="fa fa-envelope"></i> <a
                            href="mailto:${facultyDetails.facultyOfficialEmail}">
                        <span>${facultyDetails.facultyOfficialEmail}</span>
                        &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                            href="mailto:${facultyDetails.facultyPersonalEmail}">
                        <span>${facultyDetails.facultyPersonalEmail}</span>

                    </a></p> <br/><br/>

                   <c:forEach items="${facultyDetails.facultyKeyProps}" var="keyprop">
                    <div class="panel panel-default facultyInfo">
                            <div class="panel-heading">
                                <Strong>${keyprop.keyPropertyName}</Strong>
                            </div>
                            <c:forEach items="${keyprop.keyPropVals}" var="propVal">
                                <table class="table table-striped">
                                    <tbody>
                                    <tr>
                                        <td>&nbsp&nbsp&nbsp${propVal.keyPropVal}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </c:forEach>

                    </div>
                </c:forEach>
                </div>

                <div role="tabpanel" class="tab-pane" id="downloads">
                    <div style="padding: 15px;">
                        <div class="">
                            <c:forEach items="${facultyDocument}" var="facultyDocument">
                              <div>
                                <span class="label" style="border:  solid 1px #ccc; color: #000;">
                                <c:set var="clazz" value="label label-warning"/>
                                <i class=""></i> ${facultyDocument.date} </span>&nbsp;
                             <c:forEach var="item" items="${Role}">
                               <c:if test="${'Faculty' eq item and fUserName eq loggedInUser}">
                               <span class="label label-danger"><a href="http://keck.ac.in/common/deleteFacultyDoc/${facultyDocument.id}">Delete</a></span>
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
                <div role="tabpanel" class="tab-pane" id="order">
                    <div style="padding: 15px;">
                        <a href="http://keck.ac.in/fstore/faculty/order"> Order Item </a>
                    </div>
                    <div style="padding: 15px;">
                        <a href="http://keck.ac.in/fstore/faculty/facultyOrderHistory"> Order History
                        </a>
                    </div>
                    <div style="padding: 15px;">
                        <div class="row" style="border:1px solid green;padding:10px">
                            <div class="col-md-2 text-center"><strong>Product Name</strong></div>
                            <div class="col-md-1 text-center"><strong>Qty</strong></div>
                            <div class="col-md-1 text-center"><strong>Status</strong></div>
                            <div class="col-md-2 text-center"><strong>Requested Date</strong></div>
                            <div class="col-md-2 text-center"><strong>Approval/Rejection Date</strong>
                            </div>
                            <div class="col-md-2 text-center"><strong>Edit</strong></div>
                            <div class="col-md-2 text-center"><strong>Delete</strong></div>

                        </div>
                        <div class="" style>
                            <c:forEach items="${requests}" var="request">
                                <div class="row requests" style="border:1px solid green;padding:10px">
                                    <div class="col-md-2 text-center">${request.product.productName}
                                    </div>
                                    <div class="col-md-1 text-center">${request.productQuantity}</div>
                                    <div class="col-md-1 text-center" id="statusId${request.requestId}">
                                        ${request.status}
                                    </div>
                                    <div class="col-md-2 text-center">
                                        <fmt:formatDate value="${request.requestDate}"
                                                        pattern="dd/MM/yyyy"/>
                                    </div>
                                    <div class="col-md-2 text-center">
                                        <fmt:formatDate value="${request.approvalDate}"
                                                        pattern="dd/MM/yyyy"/>
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
<jsp:include page="/jsp/footer.jsp"/>
<script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="http://keck.ac.in/js/jquery-ui.js"></script>
<script src="http://keck.ac.in/js/facultyprofile.js"></script>
