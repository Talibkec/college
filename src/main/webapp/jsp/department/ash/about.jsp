
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp"/>

<div class="row scrollingnews">
        <marquee style=  loop="infinite" onmouseover="this.stop();" onmouseout="this.start();">
            <ul>

                <c:forEach items="${scrollingNoticeList}" var="scrollingNotice">
                 <li style= "display: inline;">
                 <i class="fa fa-asterisk"></i>
                 <c:if test = "${scrollingNotice.noticeAge <= 45}">
                    <img class="" src="/sites/noticeicon.gif" alt="" Related image" width="44" height="40" style="display: inline" />
                 </c:if>
                 <a href="/${scrollingNotice.id}/notice${scrollingNotice.fileType}" style="display: inline" target="_blank">${scrollingNotice.headLine}</a>
                 <c:forEach var="item" items="${Role}">
                                               <c:if test = "${'HOD' eq item and UserName eq 'hodash'}">
                                                                             <span class="label label-danger"><a style="display: inline" href="<c:url value='/hod/deleteNotice/${scrollingNotice.id}' />">Delete</a></span>
                     </c:if>
                 </c:forEach>
                 </li>

                </c:forEach>
               </ul>
            </marquee>
    </div>



<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>About Applied Science and Humanities</h3></div>

            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
            <jsp:include page="../sidebars/ashsidebar.jsp"/>

           <div class="col-md-6">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                    <div style="font-family: georgia, sans-serif; font-size: 16px; text-align: justify;">
                        <div id="ctl00_ContentPlaceHolder1_div_1"><p><strong>INTRODUCTION</strong></p>This department
                            deals with the all the basic concepts required to excel in the field of engineering. All the
                            concept of science, mathematics, humanities and management is being revisited recalled and
                            interpreted so as to ease the concepts which is required to excel in the field. The
                            department teaches the subjects of Chemistry, English, Mathematics, Physics and Mathematics
                            in the B.Tech Programs. There are number of well qualified and experienced faculty members
                            available at the service of the department.
                            The aspects such as technical communication, technical report writing, industrial economics,
                            Accountancy, Industrial organization, their behavior and Physcology is being taught under
                            the section of humanities
                            The department of Applied Science consists of Applied Physics, Applied Chemistry and Applied
                            Mathematics. This section deals with the concepts such as semiconductor physics, optics,
                            Electrostatic , modern physics, Solutions and their colligative properties, Polymers and
                            practical aspects such as Titration of chemicals, testing the PH value of solutions and many
                            more.
                            Applied Mathematics offers courses to undergraduate and post-graduate students of various
                            engineering disciplines. The syllabi have been designed in the areas of Applied Mathematics,
                            Computational Techniques and Statistical testing to impart the sound knowledge of the
                            various mathematical tools used and their applications in the engineering disciplines.This
                            department deals with the all the basic concepts required to excel in the field of
                            engineering. All the concept of science, mathematics, humanities and management is being
                            revisited recalled and interpreted so as to ease the concepts which is required to excel in
                            the field. The department teaches the subjects of Chemistry, English, Mathematics, Physics
                            and Mathematics in the B.Tech Programs. There are number of well qualified and experienced
                            faculty members available at the service of the department.
                            The aspects such as technical communication, technical report writing, industrial economics,
                            Accountancy, Industrial organization, their behavior and Physcology is being taught under
                            the section of humanities
                            The department of Applied Science consists of Applied Physics, Applied Chemistry and Applied
                            Mathematics. This section deals with the concepts such as semiconductor physics, optics,
                            Electrostatic , modern physics, Solutions and their colligative properties, Polymers and
                            practical aspects such as Titration of chemicals, testing the PH value of solutions and many
                            more.
                            Applied Mathematics offers courses to undergraduate and post-graduate students of various
                            engineering disciplines. The syllabi have been designed in the areas of Applied Mathematics,
                            Computational Techniques and Statistical testing to impart the sound knowledge of the
                            various mathematical tools used and their applications in the engineering disciplines.
                            This department deals with the all the basic concepts required to excel in the field of
                            engineering. All the concept of science, mathematics, humanities and management is being
                            revisited recalled and interpreted so as to ease the concepts which is required to excel in
                            the field. The department teaches the subjects of Chemistry, English, Mathematics, Physics
                            and Mathematics in the B.Tech Programs. There are number of well qualified and experienced
                            faculty members available at the service of the department.
                            The aspects such as technical communication, technical report writing, industrial economics,
                            Accountancy, Industrial organization, their behavior and Physcology is being taught under
                            the section of humanities
                            The department of Applied Science consists of Applied Physics, Applied Chemistry and Applied
                            Mathematics. This section deals with the concepts such as semiconductor physics, optics,
                            Electrostatic , modern physics, Solutions and their colligative properties, Polymers and
                            practical aspects such as Titration of chemicals, testing the PH value of solutions and many
                            more.
                            Applied Mathematics offers courses to undergraduate and post-graduate students of various
                            engineering disciplines. The syllabi have been designed in the areas of Applied Mathematics,
                            Computational Techniques and Statistical testing to impart the sound knowledge of the
                            various mathematical tools used and their applications in the engineering disciplines.



                            </div>
                    </div>
                </div></div>

                <div class="heroCol col-sm-3">
                                        <div class="panel panel-default">
                                        <div class="panel-heading">
                                        <strong>
                                         <i class="fa fa-news"></i> Latest News / Updates </strong>
                                                                                            <c:forEach var="item" items="${Role}">
                                                                                                   <c:if test = "${'HOD' eq item and UserName eq 'hodash'}">
                                                                                                   <strong><i class="fa fa-news"></i> <a href="/hod/uploadfile/upload">Upload Notice / News</a></strong>

                                                                                               </c:if></c:forEach></div>

                                                                                           <div class="panel-body" style="max-height: 500px;">
                                                                                               <ul class="listUpdates" id="noticeBoardItems">



                                                                                                   <c:forEach items="${noticeList}" var="notice">
                                                                                                       <li>
                                                                                                           <div>
                                                                                                		<span class="label" style="border:  solid 1px #ccc; color: #000;">
                                                                                                		<c:set var = "clazz"  value = "label label-warning"/>
                                                                                                		<c:set var = "filePath"  value = "/wp-content/uploads/notice/"/>
                                                                                                		 <c:if test = "${ notice.noticeType == 'News'}">
                                                                                                                 <c:set var = "clazz"  value = "label label-primary"/>
                                                                                                         </c:if>
                                                                                                		<i class="fa fa-clock-o"></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                                                                                                              <c:forEach var="item" items="${Role}">
                                               <c:if test = "${'HOD' eq item and UserName eq 'hodash'}">
                                                                                                                                                                  <span class="label label-danger"><a href="<c:url value='/hod/deleteNotice/${notice.id}' />">Delete</a></span>
                                                                                                               </c:if>
                                                                                                               </c:forEach>
                                                                                                                 <a href="/${notice.id}/notice${notice.fileType}" target="_blank">${notice.headLine}</a>
                                                                                                           </div>
                                                                                                       </li>
                                                                                                   </c:forEach>
                                                                                               </ul>
                                                                                               <a href="/department/ash/notice" class="btn btn-default">View All<i class="fa fa-double-angle-right"></i></a>
                                        </div>
                                        </div>
                                        </div>
                                        </div>
                                    </div>

            </div>
        </div>


<jsp:include page="/jsp/footer.jsp"/>
