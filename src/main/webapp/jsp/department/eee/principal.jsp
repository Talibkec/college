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
            <jsp:include page="sidebar.jsp"/>

           <div class="col-md-8">
            <c:forEach var="item" items="${Role}">
                                    <c:if test = "${'Faculty' eq item and UserName eq 'principal'}">
                                        <strong><a href="/uploadfile/facultyFileUpload?isProfilePic=No" style="margin-right:10px">Upload Documents &nbsp;|</a></strong>
                                        <strong><a href= "/uploadfile/facultyFileUpload?isProfilePic=Yes"">Update Profile Details</a></strong>
                                 </c:if>
                          </c:forEach>
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab"
                                                              data-toggle="tab"><i class="fa fa-user"></i> Profile</a>
                    </li>
                    <li role="presentation"><a href="#downloads" aria-controls="downloads" role="tab" data-toggle="tab"><i
                            class="fa fa-download"></i> Downloads</a></li>
                    <c:forEach var="item" items="${Role}">
                                                        <c:if test = "${'Faculty' eq item and UserName eq 'principal'}">
                    <li role="presentation"><a href="#order" aria-controls="order" role="tab" data-toggle="tab"><i
                                                                     class="fa fa-angle-double-right"></i> Request</a></li>
                                                                     </c:if>
                                                                     </c:forEach>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="profile"><img alt=""  id="profileImage"width= "250px" style="height: 200px"
                     src="/${facultyId}/image${fileExtension}"/>
                        <h2>${facultyName}</h2>
                        <p style="font-size: 130%;">Principal<br/> Department of Electrical and Electronics
                            Engineering<br></p>
                        <p><i class="fa fa-phone"></i>${facultyMobNo} &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                                href="mailto:${facultyOfficialEmail}"> <span> ${facultyOfficialEmail}</span>
            &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a href="mailto:${facultyPersonalEmail}"> <span> ${facultyPersonalEmail}</span>
                        </a></p> <br/><br/>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Qualifications</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbsp &nbspM.Tech </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Area of Research</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbsp &nbspOptimal Control System</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Administrative Responsibilities</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbsp &nbspPrincipal</td>
                                </tr>
                                </tbody>
                            </table>
                        </div></div>
                   <div role="tabpanel" class="tab-pane" id="downloads">
                                           <div style="padding: 15px;">
                                               <div class="">
                                                   <c:forEach items="${facultyDocument}" var="facultyDocument">
                                                       <div>
                                                           <span class="label" style="border:  solid 1px #ccc; color: #000;">
                                                           <c:set var = "clazz"  value = "label label-warning"/>
                                                           <i class=""></i> ${facultyDocument.date} </span>&nbsp;
                                                           <c:forEach var="item" items="${Role}">
                                                                                                                         <c:if test = "${'Faculty' eq item and UserName eq 'principal' }">
                                                                                                                         <span class="label label-danger"><a href="/common/deleteFacultyDoc/${facultyDocument.id}">Delete</a></span>
                                                                                                                         </c:if>
                                                                                                                      </c:forEach>
                                                         <c:choose>
                                                                                                 <c:when test="${facultyDocument.link}">
                                                                                                   <a href="${facultyDocument.linkAddress}" target="_blank">${facultyDocument.headLine}</a>
                                                                                                 </c:when>
                                                                                                 <c:otherwise>
                                                                                                    <a href="/${facultyDocument.id}/documents${facultyDocument.fileType}" target="_blank">${facultyDocument.headLine}</a>
                                                                                                 </c:otherwise>
                                                                                               </c:choose>
                                                       </div>
                                                   </c:forEach>
                                              </div>
                                            </div>

                                           </div>
                                       <div role="tabpanel" class="tab-pane" id="order">
                                           <div style="padding: 15px;">
                                           		<a href="/fstore/faculty/order" > Order Item </a>
                                           </div>
                                            <div style="padding: 15px;">
                                                    <a href="/fstore/faculty/facultyOrderHistory" > Order History </a>
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
                                                                                      <div class="col-md-2 text-center"><a href="/fstore/faculty/editfacultyrequest?id=${request.requestId}" id = "editId${request.requestId}">Edit</a></div>
                                                                                      <div class="col-md-2 text-center"><a href="/fstore/faculty/deletefacultyrequest?requestId=${request.requestId}">Delete</a></div>
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
                       <script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
                       <script src="/js/jquery-ui.js"></script>
                       <script src="/js/facultyprofile.js"></script>
