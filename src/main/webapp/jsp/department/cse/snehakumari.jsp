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
            <div class="col-md-4">
                <ul class="list-group page-menu">
                    <li class="list-group-item" style="background: #eee; font-size: 16px;">Department of Computer
                        Science and Engineering
                    </li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/subodh"
                                                          style="font-size: 14px;">&nbsp;&nbsp;<i
                            class="fa fa-angle-double-right"></i> Subodh Kumar</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/dky" style="font-size: 14px;">&nbsp;&nbsp;<i
                            class="fa fa-angle-double-right"></i> Dharmveer Kumar Yadav</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/shweta" style="font-size: 14px;">&nbsp;&nbsp;<i
                            class="fa fa-angle-double-right"></i> Shweta Kumari</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/snehakumari" style="font-size: 14px;">&nbsp;&nbsp;<i
                            class="fa fa-angle-double-right"></i>Sneha Kumari</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/sjk" style="font-size: 14px;">&nbsp;&nbsp;<i
                            class="fa fa-angle-double-right"></i> Sujeet Kumar</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/mta" style="font-size: 14px;">&nbsp;&nbsp;<i
                            class="fa fa-angle-double-right"></i> Md Talib Ahmad</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/pks" style="font-size: 14px;">&nbsp;&nbsp;<i
                            class="fa fa-angle-double-right"></i> Pradeep Kumar Sharma</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/cmm" style="font-size: 14px;">&nbsp;&nbsp;<i
                            class="fa fa-angle-double-right"></i> Dr. CM Mandal</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/snjk" style="font-size: 14px;">&nbsp;&nbsp;<i
                            class="fa fa-angle-double-right"></i> Sinjan Kumar </a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/shk" style="font-size: 14px;">&nbsp;&nbsp;<i
                            class="fa fa-angle-double-right"></i> Sritosh Kumar </a></li>
                 </ul>
            </div>
            <div class="col-md-8">
            <c:forEach var="item" items="${Role}">
                <c:if test = "${'Faculty' eq item and UserName eq 'snehakumari'}">
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
                    <div role="tabpanel" class="tab-pane active" id="profile"><img alt="" id="profileImage" width= "250px" style="height: 200px"
                                                                                     src="http://localhost/${facultyId}/image${fileExtension}"/>
                        <h2>${facultyName}</h2>
                        <p style="font-size: 130%;">Assistant Professor<br/> Department of Computer Science and
                            Engineering</p>
                        <p><i class="fa fa-phone"></i> ${facultyMobNo} &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                                href="mailto:${facultyOfficialEmail}"> <span> ${facultyOfficialEmail}</span>
                           <i class="fa fa-envelope"></i> <a href="mailto:${facultyPersonalEmail}"> <span> ${facultyPersonalEmail}</span>
                        </a></p> <br/><br/>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Qualifications</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>M.Tech </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Area of Research</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>1. &nbsp&nbspDistributed Computing<br/>2.&nbsp&nbspImage Processing</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Area of Research</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>1.&nbsp Hostel Superintendent</td>
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
                                             <a href="http://localhost/${facultyDocument.id}/documents${facultyDocument.fileType}" target="_blank">${facultyDocument.headLine}</a>
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
