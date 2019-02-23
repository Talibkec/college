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
                                    <c:if test = "${'Faculty' eq item and UserName eq 'talktorashid'}">
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
                        <h2>${facultyName}</h2>
                        <p style="font-size: 130%;">Assistant Professor and Head of the Department <br/> Department of Civil
                            Engineering
                        <p><i class="fa fa-phone">${facultyMobNo}</i> &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                                href="mailto:${facultyOfficialEmail}"> <span>${facultyOfficialEmail}</span>
                                &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                                                                href="mailto:${facultyPersonalEmail}"> <span>${facultyPersonalEmail}</span>

                        </a></p> <br/><br/>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Qualifications</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbsp&nbspM.Tech-Civil Engineering(Geotechnical Engineering)-IIT Bombay, Powai, Mumbai,India(2014)</td></tr>
									<tr><td>&nbsp&nbspB.Tech-Civil Engineering-Aligarh Muslim University(AMU)(2012)</td></tr>



                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Area of Research</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbsp&nbspReduction of Earth Pressure on Retaining Wall</td>
                                </tr>
								<tr><td>&nbsp&nbspNumerical Modeling of Retaining Wall</td></tr>
								<td>&nbsp&nbspFinite Element Analysis in Geotechnical Engineering</td></tr>
								<td>&nbsp&nbspNumerical Analysis of Geosynthetic Reinforced Soil Retaining wall</td></tr>
                                </tbody>
                            </table>
                        </div>
						<div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Professional Experience</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbsp&nbsp2 years Teaching Experience</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
						<div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Publications</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>1.&nbsp&nbspRashid Mustafa, WasimAkram, SahzadaAman, and Mohd.Asif(2017); "Reduction of lateral earth pressure on rigid retaining walls using EPS geofoam inclusions", International Journal of Innovative Research in Science and Engineering,(ISSN(O):2454-9665,ISSN(P):2454-0663,Impact Factor: 2.03 ,Vol.3,Issue 04, pp.878-882)</td>
                                </tr>
								<tr><td>2.&nbsp&nbspMohd.Asif, SahzadaAman,Rashid Mustafa and WasimAkram(2017); "Analysis of self-compacting concrete using PPC and OPC with different proportions of fly ash", International Journal of Innovative Research in Science and Engineering,(ISSN(O):2454-9665,ISSN(P):2454-0663,Impact Factor: 2.03 ,Vol.3,Issue 04, pp.872-877)</td></tr>
                                </tbody>
                            </table>
                        </div>
						<div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Subjects Teached</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>Soil Mechanics-I,Engineering Hydrology,Soil Mechanics Lab,Engineering Hydrology Lab,Hydraulics &
										OpenChannel Flow,Field Measurement,Foundation Engineering,Hydraulic Machine,Highway Engineering,Survey Lab, RCC.</td>

                                </tr>
                                </tbody>
                            </table>
                        </div>
						<div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Career</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>Head of the Department, Department of Civil Engineering, KEC, Katihar, India ,Feb 2018-Till date</td>
                                </tr>
								<tr><td>Assistant professor,Department of Civil Engineering, KEC, Katihar, India ,Jan 2018-Till date</td></tr>
								<tr><td>Assistant Professor, Department of Civil Engineering, GNIOT, Greater Noida, India,Feb 2017-Oct 2017</tr></td>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Administrative Responsibilities</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbsp&nbspHead of the Department,Civil Engineering</td>
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
    <jsp:include page="/jsp/footer.jsp"/>s