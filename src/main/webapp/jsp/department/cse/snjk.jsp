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
                        <c:if test = "${'Faculty' eq item and UserName eq 'sinjan.kumar'}">
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
                                                                                    width= "250px" style="height: 200px"
                                                                                     src="http://localhost/${facultyId}/image${fileExtension}"/>
                        <h2>${facultyName}</h2>
                        <p style="font-size: 130%;">Assistant Professor <br/> Department of Computer Science and
                            Engineering</p>
                        <p><i class="fa fa-phone"></i>${facultyMobNo} &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                                href="mailto:${facultyOfficialEmail}"> <span>${facultyOfficialEmail}</span>
                          <i class="fa fa-envelope"></i> <a href="mailto:${facultyPersonalEmail}"> <span>${facultyPersonalEmail}</span>
                        </a></p> <br/><br/>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Qualifications</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>M.Tech(Delhi Technological University, Delhi) </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Area of Research</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
									<td>1.&nbspDeep Learning </td></tr>
                                    <tr><td>2.&nbspArtificial Intelligence </td></tr>
                                   <tr> <td>3.&nbspInternet of Things </td></tr>

                                </tr>
                                </tbody>
                            </table>
                        </div>
						<div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Research and Development Activities</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>E Yantra Lab Setup in co-ordination with IIT Bombay</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Skill Set</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbsp;1. Embedded System</td>
                                </tr>
                                <tr>
                                    <td>&nbsp2. Linux Kernel</td>
                                </tr>
                                <tr>
                                    <td>&nbsp3. Advanced C/C++</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
						<div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Professional Experience</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>6 years experience in top-notch Product Based Companies(Samsung,Qualcomm)</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Administrative Responsibilities </Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbsp1.Website Section </td>
                                    <td>&nbsp2. Internet Section</td>
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
