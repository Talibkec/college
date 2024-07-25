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
                <h3 class="text-2xl font-semibold">About CSE</h3>
            </div>
             <div class="flex space-x-4">
                            <a
                                href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2F#%2Fdepartment-of-cse%2Fabout-cse%2F"
                                target="_blank"
                                class="text-gray-800 hover:text-blue-600"
                            >
                                <i class="fab fa-facebook"></i>
                            </a>
                            <a
                                href="https://twitter.com/home?status=About+Information+Technology+-+https%3A%2F%2F#%2Fdepartment-of-cse%2Fabout-cse%2F"
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
                    <p><strong class="text-3xl font-bold">INTRODUCTION</strong></p>
                    <p>Computer Science and Engineering is a sub-field of electronics engineering that covers only digital aspects of electronics engineering like computer architecture, processor designing, computer networks, etc. This department integrates the fields of computer engineering and computer science. CSE programs include core subjects of computer science such as:</p>
                    <ul class="list-disc pl-6">
                        <li>Operating systems</li>
                        <li>Theory of computation</li>
                        <li>Design and analysis of algorithms</li>
                        <li>Data structures</li>
                        <li>Database systems</li>
                    </ul>
                    <p>CSE branch is highly demanding and fastest growing subject. It will make human life better than ever before and that's why a whole world has entered into new race named, "Development of computer technology". As a contribution to this race Katihar college of Engineering has established a department of computer science and engineering from the academic session 2016/17. The department offers Btech programme with the annual intake of 60 students and are working to increase the intake capabilities.</p>
                    <p>The Department of Computer Science and Engineering of Katihar Engineering College, Katihar is ready to prepare well qualified, motivated, application oriented young and dynamic Computer Science graduates under the guidance of highly qualified faculties who inspire students, ignite students' imagination and enrich them with their priceless knowledge and experience. The faculty of the Department are actively engaged in research in the areas of Data & Knowledge Management, Software Engineering, Image Processing, Cryptography, Data Mining, and Artificial Intelligence. Management keeps strong check on faculties and provoke them to attend workshops, seminars, conferences and faculty development program.</p>
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
                  <c:if test="${'HOD' eq item and UserName eq 'hodcse'}">
                      <strong>
                          <i class="fa fa-newspaper"></i>
                          <a
                              href="/hod/uploadfile/upload"
                              class="text-white hover:underline"
                          >
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
                                  <c:if test="${'HOD' eq item and UserName eq 'hodcse'}">
                                      <span class="bg-red-500 text-white px-2 py-1 rounded">
                                          <a
                                              href="<c:url value='/hod/deleteNotice/${notice.id}' />"
                                              class="text-white hover:underline"
                                          >
                                              Delete
                                          </a>
                                      </span>
                                  </c:if>
                              </c:forEach>
                              <a
                                  href="/${notice.id}/notice${notice.fileType}"
                                  target="_blank"
                                  class="text-blue-600 hover:text-blue-800"
                              >
                                  ${notice.headLine}
                              </a>
                          </div>
                      </li>
                  </c:forEach>
              </ul>
              <a
                  href="/department/cse/notice"
                  class="block mt-4 text-center bg-gray-300 hover:bg-gray-400 text-black font-semibold py-2 rounded"
              >
                  View All
                  <i class="fa fa-angle-double-right"></i>
              </a>
          </div>
      </div>
  </div>
</div>


<jsp:include page="/jsp/footer.jsp"/>
