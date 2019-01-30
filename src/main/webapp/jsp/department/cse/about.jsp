<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/jsp/header.jsp"/>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>About CSE</h3></div>
                <div class="col-md-2 col-sm-4" style="font-size: 24px; line-height: 40px;"><span
                        style="display: inline-block; line-height: 40px; vertical-align: middle;"><i
                        </i> </span> <a
                        href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2F#%2Fdepartment-of-cse%2Fabout-cse%2F"
                        target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i
                        </i></a> <a
                        href="https://twitter.com/home?status=About+Information+Technology+-+https%3A%2F%2F#%2Fdepartment-of-cse%2Fabout-cse%2F"
                        target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i
                        </i> </a></div>
            </div>
        </div>
    </div>
    <div class="container">
        <ul class="breadcrumb" style="background: none">
            <li><a href="/"><i class="fa fa-home"></i> Home</a></li>
            <li class="active">About CSE</li>
        </ul>
        <div class="row">
            <div class="col-md-4">
                <ul class="list-group page-menu">
                    <li class="list-group-item" style="background: #eee; font-size: 20px;">Department of CSE</li>
                    <li class="list-group-item active"><a href="http://localhost/department/cse/about">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> About CSE</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/vision">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Vision and Mission</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/faculty">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Faculty of CSE</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/labs">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Labs & Infrastructure</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/question">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Question Bank</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/awards">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Awards and Recognition</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/programs">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Program/Activities</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/students">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Students of CSE</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/notice">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Notice</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/weekly">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Weekly Test Result</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/course">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Course File</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/staff">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Staff of CSE</a></li>
                    <li class="list-group-item"><a href="http://localhost/department/cse/attendance.html">&nbsp;&nbsp; <i
                            class="fa fa-angle-double-right"></i> Attendance Summary Sheet</a></li>
                </ul>
            </div>
            <div class="col-md-4">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                    <div style="font-family: georgia, sans-serif; font-size: 16px; text-align: justify;">
                        <div id="ctl00_ContentPlaceHolder1_div_1"><p><strong>INTRODUCTION</strong></p>Computer Science and Engineering is a sub field of electronics engineering that covers only digital aspects of electronics engineering like computer architecture, processor designing, computer networks etc. This department integrates the fields of computer engineering and computer science. CSE programs include core subjects of computer science such as <strong>operating systems,theory of computation, design and analysis of algorithms, data structures and database systems.</strong><p>CSE branch is highly demanding and fastest growing subject. It will make human life better than ever before and that’s why a whole world has entered into new race named, “Development of computer technology”. As a contribution to this race Katihar college of Engineering has established a department of computer science and engineering from the academic session 2016/17. The department offers Btech programme with the annual intake of 60 students and are working to increase the intake capabilities.</p><p>The Department of Computer Science and Engineering of Katihar Engineering College, Katihar is ready to prepare well qualified, motivated, application oriented young and dynamic Computer Science graduates under the guidance of highly qualified faculties who inspire students, ignite students’ imagination and enrich them with their priceless knowledge and experience. The faculty of the Department are actively engaged in research in the areas of Data & Knowledge Management, Software Engineering, Image Processing, Cryptography, Data Mining, and Artificial Intelligence. Management keeps strong check on faculties and provoke them to attend workshops, seminars, conferences and faculty development program.</p></div>
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
