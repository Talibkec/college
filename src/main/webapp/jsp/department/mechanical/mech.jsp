<jsp:include page="/jsp/header.jsp"/>


<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>About Mechanical Engg.</h3></div>

            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
            <div class="col-md-4">
                <ul class="list-group page-menu">
                    <li class="list-group-item" style="background: #eee; font-size: 20px;">Department of Mechanical
                        Engineering
                    </li>
                    <li class="list-group-item active"><a href="http://localhost/department/mech">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> About Mechanical Engg.</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/vision">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Vision and Mission</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/faculty">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Faculty of Mechanical Engg.</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/labs">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Labs & Infrastructure</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/question">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Question Bank</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/awards">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Awards and Recognition</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/students">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Students of Mechanical Engineering</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/notice">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Notice</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/programs">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Program/Activities</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/weekly">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Weekly Test Result</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/course">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Course File</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/attendance">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Attendance</a></li>
                </ul>
            </div>

<div class="col-md-4">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                    <div style="font-family: georgia, sans-serif; font-size: 16px; text-align: justify;">
                        <div id="ctl00_ContentPlaceHolder1_div_1"><p><strong>INTRODUCTION</strong></p><p>Mechanical engineering is the discipline that applies engineering physics, engineering mathematics, and materials science principles to design, analyse, manufacture, and maintain mechanical systems. It is one of the oldest and broadest of the engineering disciplines.It is known as the evergreen branch among all the branches of engineering department</p><p>The mechanical engineering field requires an understanding of core areas including mechanics, dynamics, thermodynamics, materials science, structural analysis, and electricity. In addition to these core principles, mechanical engineers use tools such as computer-aided design (CAD), computer-aided manufacturing (CAM), and product life cycle management to design and analyse manufacturing plants, industrial equipment and machinery, heating and cooling systems, transport systems, aircraft, watercraft, robotics, medical devices, weapons, and others. It is the branch of engineering that involves the design, production, and operation of machinery.</p><p>Nowadays the scope of mechanical engineering is expanding beyond its traditional boundaries.In order to contribute to the growth of quality mechanical engineers katihar engineering college has set up the department of mechanical engineering in the year 2016.the faculties here are very supportive in nature they hardly fails to inspire the young minds and enrich them with their knowledge and experience.The management keeps a strong check on each faculties and kindle them to attend workshops,seminars,conferences and faculty development program.Students enthusiastically work here under the supervision of trained faculty members.</p></div>
                        </div>
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



