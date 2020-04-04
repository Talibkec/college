<jsp:include page ="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">

    </div>
    <div class="container">

        </ul>
        <div class="row">
          <aside class="col-sm-3" role="complementary">
                                                                                <div class="region region-sidebar-second">
                                                                      <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                                                              <h2 class="block-title">Faculty of Mechanical Engg.</h2>

                                                                        <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                                                       <ul class="menu nav">
                                                                      <li class="first leaf  menu-mlid-1154"><a href="http://keck.ac.in/department/mechanical/srk" class="sf-depth-2 active">Surya Kumar</a></li>
                                                                      <li class="leaf menu-mlid-1121"><a href="http://keck.ac.in/department/mechanical/jk" class="sf-depth-2">Jayant Kumar</a></li>
                                                                      <li class="leaf menu-mlid-1156"><a href="http://keck.ac.in/department/mechanical/mk" class="sf-depth-2">Mukesh Kumar</a></li>
                                                                      <li class="leaf menu-mlid-1294"><a href="http://keck.ac.in/department/mechanical/smnk" class="sf-depth-2">Suman Kumar</a></li>
                                                                      <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/ajk" class="sf-depth-2">Ajay Kumar</a></li>
                                                                      <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/ap" class="sf-depth-2">Dr Arbind Prasad</a></li>
                                                                      <li class="leaf menu-mlid-1467"><a href="http://keck.ac.in/department/mechanical/kks" class="sf-depth-2">Kanchan Kumar Sinha</a></li>
                                                                         </ul></div>

                                                                      </section>
                                                                        </div>
                                                                            </aside>


                <div class="col-md-8">
                 <c:forEach var="item" items="${Role}">
                                        <c:if test = "${'Faculty' eq item and UserName eq 'arbind'}">
                                            <strong><a href="http://keck.ac.in/uploadfile/facultyFileUpload?isProfilePic=No" style="margin-right:10px">Upload Documents &nbsp;|</a></strong>
                                            <strong><a href= "http://keck.ac.in/uploadfile/facultyFileUpload?isProfilePic=Yes"">Update Profile Details</a></strong>
                                     </c:if>
                              </c:forEach>
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab"
                                                                  data-toggle="tab"><i class="fa fa-user"></i>
                            Profile</a></li>
                        <li role="presentation"><a href="#downloads" aria-controls="downloads" role="tab"
                                                   data-toggle="tab"><i class="fa fa-download"></i> Downloads</a></li>
<c:forEach var="item" items="${Role}">
                                        <c:if test = "${'Faculty' eq item and UserName eq 'arbind'}">
                                                   <li role="presentation"><a href="#order" aria-controls="order" role="tab" data-toggle="tab"><i
                                                                                                    class="fa fa-angle-double-right"></i> Request</a></li>
                   </c:if>
                   </c:forEach>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="profile"><img alt="" id="profileImage"
                        style="height: 200px;width:250px" src="http://keck.ac.in/${facultyId}/image${fileExtension}"/>
                            <h2>${facultyName}</h2>
                            <p style="font-size: 130%;">Assistant Professor<br>Department of Mechanical Engineering<br></p>
                            <p><i class="fa fa-phone"></i>${facultyMobNo} &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                                    href="mailto:${facultyOfficialEmail}>
">
                                <span>${facultyOfficialEmail}</span>
                                 &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                                                                     href="mailto:${facultyPersonalEmail}>
                                 ">
                                                                 <span>${facultyPersonalEmail}</span></a></p> <br/><br/>
                            <div class="panel panel-default facultyInfo">
                                <div class="panel-heading"><Strong>Qualifications</Strong></div>
                                <table class="table table-striped">
                                    <tbody>
                                    <tr>
                                        <td>&nbsp&nbsp1. Ph.D IIT Guwahati</strong></td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp&nbsp2. M.Tech(CAD/CAM & Robotics), GEU Dehradun</strong></td>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>
                            <div class="panel panel-default facultyInfo">
                                <div class="panel-heading"><Strong>Area of Research</Strong></div>
                                <table class="table table-striped">
                                    <tbody>
                                    <tr>
                                        <td>1.&nbsp&nbsp Biomaterials </td>
                                    </tr>
                                    <tr>
                                        <td>2.&nbsp&nbsp Bioimplants </td>
                                    </tr>
                                    <tr>
                                        <td>3.&nbsp&nbsp Biopolymer Processings </td>
                                    </tr>
                                     <tr>
                                        <td>4.&nbsp&nbsp Manufacturing </td>
                                    </tr>

                                     <tr>
                                         <td>5.&nbsp&nbsp Welding </td>
                                     </tr>
                                      <tr>
                                         <td>6.&nbsp&nbsp Cladding of metals </td>
                                     </tr>

                                      <tr>
                                         <td>7.&nbsp&nbsp Biodegradable Plastics </td>
                                     </tr>
                                    <tr>
                                        <td>8.&nbsp&nbsp<a href ="https://scholar.google.com/citations?user=ojNV8mYAAAAJ&hl=en"target="_blank">For more click here</a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="panel panel-default facultyInfo">
                                <div class="panel-heading"><Strong>Academic Experience</Strong></div>
                                <table class="table table-striped">
                                    <tbody>
                                    <tr>
                                        <td>&nbsp&nbsp1. &nbsp&nbsp 4.5 years Teaching Experience</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="panel panel-default facultyInfo">
                                <div class="panel-heading"><Strong>Subject Taught</Strong></div>
                                <table class="table table-striped">
                                    <tbody>
                                    <tr>
                                        <td>&nbsp&nbsp1.Kinematics of Machines</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp&nbsp2.Engineering Mechanics</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp&nbsp3.Engineering drawing & Computer Graphics</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp&nbsp4.Machine Tools & Machinery</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp&nbsp5.Manufacturing by Shaping & Joining</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp&nbsp6.Workshop Technology</td>
                                    </tr>


                                     <tr>
                                         <td>&nbsp&nbsp7.Computer aided Design & Manufacturing</td>
                                     </tr>

                                    </tbody>
                                </table>
                            </div>
                            <div class="panel panel-default facultyInfo">
                                <div class="panel-heading"><Strong>Administrative Responsibilities</Strong></div>
                                <table class="table table-striped">
                                    <tbody>
                                    <tr>
                                        <td>Professor In-Charge <i>Time Table</i> </td>
                                    </tr>
                                    <tr>
                                        <td>Institute National Board of Accreditation(NBA) Coordinator </td>
                                    </tr>
                                     <tr>
                                       <td>Coordinator Student Extracurricular Activities  Board (SEAB) </td>
                                     </tr>

                                      <tr>
                                        <td>Virtual Lab Coordinator (with IIT Kanpur) </td>
                                      </tr>

                                      <tr>
                                        <td>Institute Purchase officer through GeM (Govt. e- Market) </td>
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
                                                                                                                            <c:if test = "${'Faculty' eq item and UserName eq 'arbind' }">
                                                                                                                            <span class="label label-danger"><a href="http://keck.ac.in/common/deleteFacultyDoc/${facultyDocument.id}">Delete</a></span>
                                                                                                                            </c:if>
                                                                                                                         </c:forEach>
                                                                             <c:choose>
                                                                                        <c:when test="${facultyDocument.link}">
                                                                                          <a href="{facultyDocument.linkAddress}" target="_blank">${facultyDocument.headLine}</a>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                           <a href="http://keck.ac.in/${facultyDocument.id}/documents${facultyDocument.fileType}" target="_blank">${facultyDocument.headLine}</a>
                                                                                        </c:otherwise>
                                                                                      </c:choose>
                                                           </div>
                                                       </c:forEach>
                                                  </div>
                                                </div>

                                               </div>
                                           <div role="tabpanel" class="tab-pane" id="order">
                                               <div style="padding: 15px;">
                                               		<a href="http://keck.ac.in/fstore/faculty/order" > Order Item </a>
                                               </div>
                                                <div style="padding: 15px;">
                                                        <a href="http://keck.ac.in/fstore/faculty/facultyOrderHistory" > Order History </a>
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
                                                                                          <div class="col-md-2 text-center"><a href="http://keck.ac.in/fstore/faculty/editfacultyrequest?id=${request.requestId}" id = "editId${request.requestId}">Edit</a></div>
                                                                                          <div class="col-md-2 text-center"><a href="http://keck.ac.in/fstore/faculty/deletefacultyrequest?requestId=${request.requestId}">Delete</a></div>
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