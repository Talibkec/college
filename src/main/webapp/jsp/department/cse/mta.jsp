<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp"/>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>Md Talib Ahmad(Faculty)</h3></div>

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
            <div>
            <c:if test = "${Role == 'Faculty'}">
                                   <strong><i class="fa fa-news"></i> <a href="/auth/uploadfile/facultyfileupload">Upload Your Documents</a></strong>

             </c:if></div>
            <div class="col-md-8">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab"
                                                              data-toggle="tab"><i class="fa fa-user"></i> Profile</a>
                    </li>
                    <li role="presentation"><a href="#downloads" aria-controls="downloads" role="tab" data-toggle="tab"><i
                            class="fa fa-download"></i> Downloads</a></li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="profile"><img alt="" id="profileImage"
                                                                                   src="mta.jpg"
                                                                                   style="background-image: url(mta.jpg);"/>
                        <h2>Md Talib Ahmad</h2>
                        <p style="font-size: 130%;">Assistant Professor <br/> Department of Computer Science and
                            Engineering</p>
                        <p><i class="fa fa-phone"></i> 9108006551 &nbsp; | &nbsp; <i class="fa fa-envelope"></i> <a
                                href="mailto:mdtalibahmad@gmail.com"> <span>mdtalibahmad@gmail.com</span>
                        </a></p> <br/><br/><div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Qualifications</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>M.Tech </td>
                                </tr>
                                </tbody>
                            </table>
                        </div><div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Area of Research</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>1. &nbsp &nbspMachine learning <br/>2. &nbsp &nbsp Data Mining </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Professional Experience</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>6 years' corporate experience(Goldman Sachs, JDA Software, Pramati Technologies etc.)</td>
                                </tr>
                                <tr>
                                    <td>More than 6 months' teaching experience</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Skill set</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>Java, Python, Spring, Hibernate, Jquery, Angular JS, Elastic Search, Kafka, Mysql, Oracle</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>


                        <div class="panel panel-default facultyInfo">
                            <div class="panel-heading"><Strong>Administrative Responsibilities</Strong></div>
                            <table class="table table-striped">
                                <tbody>
                                <tr>
                                    <td>&nbspDesign and Development of College Website</td>
                                </tr>
                                <tr>
                                    <td>&nbspCultural & Sports</td>
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
                                				<span class="label label-danger"><a href="http://localhost/auth/${facultyDocument.uploadedFileName}/${facultyDocument.id}/>">Delete</a></span>
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
    </div><jsp:include page="/jsp/footer.jsp"/>