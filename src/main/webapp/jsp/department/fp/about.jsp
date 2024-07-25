
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
                                               <c:if test = "${'HOD' eq item and UserName eq 'hodcse'}">                              <span class="label label-danger"><a style="display: inline" href="<c:url value='/hod/${scrollingNotice.uploadedFileName}/${scrollingNotice.id}' />">Delete</a></span>
                     </c:if>
                 </c:forEach>
                 </li>

                </c:forEach>
               </ul>
            </marquee>
    </div>

<div class="bg-gray-200 py-4 mt-4">
     <div class="container mx-auto px-4">
         <div class="flex items-center justify-between">
             <!-- Social Media Icons Section -->
             <div class="flex-grow text-center">
                 <h3 class="text-2xl font-semibold">About Food Processing</h3>
             </div>
              <div class="flex space-x-4">
                             <a
                                 href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2F#%2Fdepartment-of-civil-engineering%2Fabout-civil-engineering%2F"
                                 target="_blank"
                                 class="text-gray-800 hover:text-blue-600"
                             >
                                 <i class="fab fa-facebook"></i>
                             </a>
                             <a
                                 href="https://twitter.com/home?status=About+Civil+Engg.+-+https%3A%2F%2F#%2Fdepartment-of-civil-engineering%2Fabout-civil-engineering%2F"
                                 target="_blank"
                                 class="text-gray-800 hover:text-blue-400"
                             >
                                 <i class="fab fa-twitter"></i>
                             </a>
              </div>
         </div>
     </div>
 </div>

<div class="container mx-auto p-4 flex flex-col md:flex-row">
        <!-- Sidebar -->
        <div class="md:w-1/4 px-4 mt-6 md:mt-0">
            <jsp:include page="sidebar.jsp"/>
        </div>

        <!-- Introduction Section -->
        <div class="md:w-2/3 px-4 mt-6 md:mt-0">
            <div class="bg-white p-6 rounded-lg shadow">
                <div class="text-justify text-lg font-light leading-relaxed">
                    <div id="ctl00_ContentPlaceHolder1_div_1">
                        <h3 class="text-3xl font-bold mb-4">Introduction</h3>
                        <p>
                            Food Processing Engineering is a specialized field within engineering that focuses on the transformation of raw food ingredients into finished products that are safe, nutritious, and appealing. This discipline integrates principles from various engineering and scientific fields, including chemical engineering, mechanical engineering, and microbiology, to optimize and innovate food processing techniques. Key areas covered in Food Processing Engineering programs include:
                        </p>
                        <ul class="list-disc pl-6 mb-4">
                            <li>Food Chemistry</li>
                            <li>Food Microbiology</li>
                            <li>Food Processing Technologies</li>
                            <li>Food Quality Assurance</li>
                            <li>Packaging Technology</li>
                            <li>Nutritional Science</li>
                        </ul>
                        <p>
                            The Food Processing Engineering branch is rapidly evolving, driven by the increasing demand for innovative and efficient food production methods to meet global needs. As a response to this growing industry, Katihar College of Engineering has established a Department of Food Processing Engineering from the academic session 2022/23. The department offers a B.Tech program with an annual intake of 60 students and is working towards expanding its intake capacity.
                        </p>
                        <p>
                            The Department of Food Processing Engineering at Katihar Engineering College, Katihar, is dedicated to preparing highly skilled, motivated, and application-oriented graduates. The department is guided by a team of highly qualified faculty members who inspire students, stimulate their creativity, and impart valuable knowledge and experience. Faculty members are actively involved in research in areas such as Food Safety and Quality, Sustainable Food Processing, Food Biotechnology, and Innovative Packaging Solutions. The management ensures rigorous oversight of faculty performance and encourages participation in workshops, seminars, conferences, and professional development programs.
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <!-- News/Updates Section -->
        <div class="md:w-1/3 px-4 mt-6 md:mt-0">
            <div class="bg-white shadow-lg rounded-lg overflow-hidden">
                <div class="bg-gray-800 p-4 text-white">
                    <strong>
                        <i class="fa fa-newspaper"></i> Latest News / Updates
                    </strong>
                    <c:forEach var="item" items="${Role}">
                        <c:if test="${'HOD' eq item and UserName eq 'hodfood'}">
                            <strong>
                                <i class="fa fa-newspaper"></i>
                                <a href="/hod/uploadfile/upload" class="text-white hover:underline">
                                    Upload Notice / News
                                </a>
                            </strong>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="p-4" style="max-height: 500px; overflow-y: auto;">
                    <ul class="space-y-2" id="noticeBoardItems">
                        <c:forEach items="${noticeList}" var="notice">
                            <li class="border-b pb-2">
                                <div class="flex flex-col lg:flex-row gap-2 items-center space-x-2">
                                    <span class="border border-gray-300 text-black px-2 py-1 rounded whitespace-nowrap">
                                        <c:set var="clazz" value="bg-yellow-400 text-black"/>
                                        <c:if test="${notice.noticeType == 'News'}">
                                            <c:set var="clazz" value="bg-blue-500 text-white"/>
                                        </c:if>
                                        <i class="fa fa-clock-o"></i> ${notice.date}
                                    </span>
                                    <span class="${clazz} rounded px-2 py-1">
                                        ${notice.noticeType}
                                    </span>
                                    <c:forEach var="item" items="${Role}">
                                        <c:if test="${'HOD' eq item and UserName eq 'hodfood'}">
                                            <span class="bg-red-500 text-white px-2 py-1 rounded">
                                                <a href="<c:url value='/hod/deleteNotice/${notice.id}' />" class="text-white hover:underline">
                                                    Delete
                                                </a>
                                            </span>
                                        </c:if>
                                    </c:forEach>
                                    <a href="/${notice.id}/notice${notice.fileType}" target="_blank" class="text-blue-600 hover:text-blue-800">
                                        ${notice.headLine}
                                    </a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    <a href="/department/foodprocessing/notice" class="block mt-4 text-center bg-gray-300 hover:bg-gray-400 text-black font-semibold py-2 rounded">
                        View All
                        <i class="fa fa-angle-double-right"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/jsp/footer.jsp"/>


