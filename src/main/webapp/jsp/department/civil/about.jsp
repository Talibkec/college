<jsp:include page="/jsp/header.jsp"/>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>About Civil Engg.</h3></div>
                <div class="col-md-2 col-sm-4" style="font-size: 24px; line-height: 40px;"><span
                        style="display: inline-block; line-height: 40px; vertical-align: middle;"><i
                    </i> </span> <a
                        href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2F#%2Fdepartment-of-civil-engineering%2Fabout-civil-engineering%2F"
                        target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i
                    </i></a> <a
                        href="https://twitter.com/home?status=About+Civil+Engg.+-+https%3A%2F%2F#%2Fdepartment-of-civil-engineering%2Fabout-civil-engineering%2F"
                        target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i
                    </i> </a></div>
            </div>
        </div>
    </div>
    <div class="container">
        <ul class="breadcrumb" style="background: none">
            <li><a href="/"><i class="fa fa-home"></i> Home</a></li>
            <li class="active">About Civil Engg.</li>
        </ul>
        <div class="row">
            <div class="col-md-4">
                <ul class="list-group page-menu">
                    <li class="list-group-item" style="background: #eee; font-size: 20px;">Department of Civil
                        Engineering
                    </li>
                    <li class="list-group-item active"><a href="http://localhost/department/civil/about">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> About Civil Engg.</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/civil/vision">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Vision and Mission</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/civil/faculty">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Faculty of Civil Engg.</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/civil/students">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Student of Civil Engg.</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/civil/notice">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i>Notice</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/civil/labs">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Labs & Infrastructure</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/civil/question">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Question Bank</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/civil/awards">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Awards and Recognition</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/civil/programs">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Program/Activities</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/civil/weekly">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Weekly Test Result</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/civil/course">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Course File</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/civil/attendance">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Attendance</a></li>
                </ul>
            </div>
                        <div class="col-md-4">
                            <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                                <div style="font-family: georgia, sans-serif; font-size: 16px; text-align: justify;">
                                    <div id="ctl00_ContentPlaceHolder1_div_1"><p><strong>INTRODUCTION</strong></p><p>Katihar Engineering College,katihar was established in 2016 and the Civil Engineering Department is part of the institute since its inception. The Department has grown tremendously over the years and is now recognized as one of the major engineering departments in the state of Bihar. Department of civil Engineering offers Bachelors in Technology, have an annual intake 60. The Department of Civil Engineering with its multifaceted faculty from different field like Geotechnical, Environmental and structural engineering maintains high quality teaching and instruction at UG level.</span><br/>
                                                                                                                  Currently it comprises of various sub divisions like <br/>(1)Building Technology and Construction Management (BTCM)<br/> (2) Environmental and water resources engineering (EWRE)<br/>(3) Geotechnical engineering (GT)<br/>(4) Structural engineering (ST)<br/> (5) Transportation engineering (TR) .
                            </p></div></div>
                        </div>
                        </div>
                        <div class="heroCol col-sm-4">
                        <div class="panel panel-default">
                        <div class="panel-heading">
                        <strong>
                         <i class="fa fa-news"></i> Latest News / Updates</strong>
                         <c:if test = "${Role == 'HOD'}">
                             <strong><i class="fa fa-news"></i> <a href="/auth/uploadfile/upload">Upload Notice / News</a></strong>
                             <strong><i class="fa fa-news"></i> <a href="/auth/sendMessage">Send Message</a></strong>
                         </c:if></div>
                        <div class="panel-body" style="max-height: 500px;">
                         <ul class="listUpdates" id="noticeBoardItems">

                         <li style="display: block;">
                          	<div>
                          		<span class="label" style="border:  solid 1px #ccc; color: #000;">
                         		<i class="fa fa-clock-o"></i> 2019-01-03 </span>&nbsp; <span class="label label-primary">News</span><a href="https://www.aicte-india.org/feedback/" target="_blank">Faculties and student may give feedback to A.I.C.T.E here</a>
                         		<span class="label" style="border:  solid 1px #ccc; color: #000;">
                                             		<i class="fa fa-clock-o"></i> 2019-01-03 </span>&nbsp; <span class="label label-primary">News</span><a href="https://www.aicte-india.org/feedback/" target="_blank">Faculties and student may give feedback to A.I.C.T.E here</a>
                          	</div>
                          </li>

                         <c:forEach items="${noticeList}" var="notice">
                           <li>
                         	<div>
                         		<span class="label" style="border:  solid 1px #ccc; color: #000;">
                         		<c:set var = "clazz"  value = "label label-warning"/>
                         		<c:set var = "filePath"  value = "http://localhost/wp-content/uploads/notice/"/>
                         		 <c:if test = "${ notice.noticeType == 'News'}">
                                          <c:set var = "clazz"  value = "label label-primary"/>
                                  </c:if>
                         		<i class="fa fa-clock-o"></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                         		<c:if test = "${Role == 'Admin'}">
                         		    <span class="label label-danger"><a href="<c:url value='/auth/${notice.uploadedFileName}/${notice.id}' />">Delete</a></span>
                                 </c:if>
                         		<a href="${filePath}${notice.uploadedFileName}" target="_blank">${notice.headLine}</a>
                         	</div>
                         </li>
                         </c:forEach>
                         </ul>
                         <a href="http://localhost/category/notices" class="btn btn-default">View All<i class="fa fa-double-angle-right"></i></a>
                        </div>
                        </div>
                        </div>
                        </div>
                    </div>
                </div>
            <jsp:include page="/jsp/footer.jsp"/>
