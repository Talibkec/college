<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/jsp/header.jsp"/>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>${facultyName}</h3></div>

            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
            <aside class="col-sm-3" role="complementary">
                                              <div class="region region-sidebar-second">
                                    <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                            <h2 class="block-title">Faculty of CSE</h2>

                                      <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                     <ul class="menu nav">
                                    <li class="first leaf  menu-mlid-1154"><a href="http://localhost/department/cse/subodh" class="sf-depth-2 active">Subodh Kumar</a></li>
                                    <li class="leaf menu-mlid-1121"><a href="http://localhost/department/cse/dky" class="sf-depth-2">Dharmveer Kumar Yadav</a></li>
                                    <li class="leaf menu-mlid-1156"><a href="http://localhost/department/cse/shweta" class="sf-depth-2">Shweta Kumari</a></li>
                                    <li class="leaf menu-mlid-1294"><a href="http://localhost/department/cse/snehakumari" class="sf-depth-2">Sneha Kumari</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/sjk" class="sf-depth-2">Sujeet Kumar</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/mta" class="sf-depth-2">Md Talib Ahmad</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/pks" class="sf-depth-2">Pradeep Kumar Sharma</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/cmm" class="sf-depth-2">Dr. C. M. Mandal</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/snjk" class="sf-depth-2">Sinjan Kumar</a></li>
                                    <li class="leaf menu-mlid-1467"><a href="http://localhost/department/cse/shk" class="sf-depth-2">Sritosh Kumar</a></li>
                                       </ul></div>
                                    </section>
                                      </div>
                            </aside>
            <div class="col-md-8">
            <c:forEach var="item" items="${Role}">
                        <c:if test = "${'Faculty' eq item and UserName eq 'pradeep'}">
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
                    <li role="presentation"><a href="#order" aria-controls="order" role="tab" data-toggle="tab"><i
                                                                    class="fa fa-angle-double-right"></i> Request</a></li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="profile"><img alt="" id="profileImage" style="height: 200px;width:250px"
                                src="http://localhost/${facultyId}/image${fileExtension}"/>
                                <h2>${facultyName}</h2>
                        <p style="font-size: 130%;">Assistant Professor <br/> Department of Computer Science and
                            Engineering</p>
                        <p><i class="fa fa-phone"></i> ${facultyMobNo} &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                                href="${facultyOfficialEmail}"> <span>${facultyOfficialEmail}</span>
                                <i class="fa fa-envelope"></i> <a
                                                                href="${facultyPersonalEmail}"> <span>${facultyPersonalEmail}</span>
                        </a></p> <br/><br/>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Qualifications</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>Ph.D (Pursuing from IIT Guwahati)</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Area of Research</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>1. &nbsp &nbspNetwork-On-Chip <br/>2. &nbsp &nbspGraph Theory<br/>3.&nbsp &nbspWireless Sensor Network</td>

                                </tr>
                                </tbody>
                            </table>
                        </div>
						<div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Professional Experience</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>1 year Teaching Experience</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Administrative Responsibilities</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbspInternet Section</td>
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
                                        <div role="tabpanel" class="tab-pane" id="order">
                                            <div style="padding: 15px;">
                                            		<a href="http://localhost/fstore/faculty/order" > Order Item </a>
                                            </div>
                                             <div style="padding: 15px;">
                                                     <a href="http://localhost/fstore/faculty/facultyOrderHistory" > Order History </a>
                                             </div>
                                            <div style="padding: 15px;">
                                                 <div class="row" style="border:1px solid green;padding:10px">
                                                    <div class="col-md-2 text-center"><strong>Product Name</strong></div>
                                                    <div class="col-md-1 text-center"><strong>Qty</strong></div>
                                                    <div class="col-md-1 text-center"><strong>Status</strong></div>
                                                    <div class="col-md-2 text-center"><strong>Requested Date</strong></div>
                                                    <div class="col-md-2 text-center"><strong>Approval/Rejection Date</strong></div>
                                                    <div class="col-md-2 text-center"><strong>Edit</strong></div>
                                                    <div class="col-md-2 text-center"><strong>Delete</strong></div>

                                                     </div>
                                                 <div class="" style>
                                                    <c:forEach items="${requests}" var="request">
                                                                            <div class="row requests" style="border:1px solid green;padding:10px">
                                                                                       <div class="col-md-2 text-center">${request.product.productName}</div>
                                                                                       <div class="col-md-1 text-center">${request.productQuantity}</div>
                                                                                       <div class="col-md-1 text-center" id ="statusId${request.requestId}" >${request.status}</div>
                                                                                       <div class="col-md-2 text-center"><fmt:formatDate value="${request.requestDate}" pattern="dd/MM/yyyy"/></div>
                                                                                       <div class="col-md-2 text-center"><fmt:formatDate value="${request.approvalDate}" pattern="dd/MM/yyyy"/></div>
                                                                                       <div class="col-md-2 text-center"><a href="http://localhost/fstore/faculty/editfacultyrequest?id=${request.requestId}" id = "editId${request.requestId}">Edit</a></div>
                                                                                       <div class="col-md-2 text-center"><a href="http://localhost/fstore/faculty/deletefacultyrequest?requestId=${request.requestId}">Delete</a></div>
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
                        <script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
                        <script src="http://localhost/js/jquery-ui.js"></script>
                        <script src="http://localhost/js/facultyprofile.js"></script>