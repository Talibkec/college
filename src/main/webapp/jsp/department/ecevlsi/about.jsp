<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="/jsp/header.jsp" />

    <div class="row scrollingnews">
        <marquee style=loop="infinite" onmouseover="this.stop();" onmouseout="this.start();">
            <ul>

                <c:forEach items="${scrollingNoticeList}" var="scrollingNotice">
                    <li style="display: inline;">
                        <i class="fa fa-asterisk"></i>
                        <c:if test="${scrollingNotice.noticeAge <= 45}">
                            <img class="" src="/sites/noticeicon.gif" alt="" Related image" width="44" height="40"
                                style="display: inline" />
                        </c:if>
                        <a href="/${scrollingNotice.id}/notice${scrollingNotice.fileType}" style="display: inline"
                            target="_blank">${scrollingNotice.headLine}</a>
                        <c:forEach var="item" items="${Role}">
                            <c:if test="${'HOD' eq item and UserName eq 'hodeee'}"> <span class="label label-danger"><a
                                        style="display: inline"
                                        href="<c:url value='/hod/deleteNotice/${scrollingNotice.id}' />">Delete</a></span>
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
                     <h3 class="text-2xl font-semibold">About VLSI Design and Technology</h3>
                 </div>
                  <div class="flex space-x-4">
                                <a href="https://www.facebook.com/profile.php?id=61564963783682" target="_blank" class="text-gray-800 hover:text-blue-600">
                                                 <i class="fab fa-facebook"></i>
                                               </a>
                                               <a href="https://x.com/keckatihar" target="_blank" class="text-gray-800 hover:text-blue-400">
                                                 <i class="fab fa-twitter"></i>
                                               </a>
                                               <a href="https://www.instagram.com/keckatiharofficial/" target="_blank" class="text-gray-800 hover:text-pink-600">
                                                 <i class="fab fa-instagram"></i>
                                               </a>
                                               <a href="https://whatsapp.com/channel/0029VaeIPrnFHWptfQRrpo0Q" target="_blank" class="text-gray-800 hover:text-green-500">
                                                 <i class="fab fa-whatsapp"></i>
                                               </a>
                                               <a href="https://www.youtube.com/channel/UCJu8sFs4jAp6sZIPsa8Qs5g" target="_blank" class="text-gray-800 hover:text-red-600">
                                                 <i class="fab fa-youtube"></i>
                                               </a>
                                               <a href="https://www.linkedin.com/in/katihar-engineering-college-dstte-govt-of-bihar-327aaa326/" target="_blank" class="text-gray-800 hover:text-blue-700">
                                                 <i class="fab fa-linkedin"></i>
                                               </a>
                  </div>
             </div>
         </div>
     </div>
       <div class="container mx-auto p-4 flex flex-col md:flex-row">
           <!-- Sidebar -->
           <div class="md:w-1/4 px-4 mt-6 md:mt-0">
               <jsp:include page="sidebar.jsp" />
           </div>

           <!-- Main Content -->
           <div class="md:w-2/3 px-4 mt-6 md:mt-0">
               <div class="bg-white p-6 rounded-lg shadow">
                   <div class="text-justify text-lg font-light leading-relaxed">
                       <h3 class="text-3xl font-bold mb-4">Introduction</h3>
                       <p>
                           M.Tech. - Electronics Engineering (VLSI Design and Technology) is a 2-year program designed
                           to prepare engineering graduates who have comprehensive knowledge about Integrated circuit
                           designs, which is usually known as chip design. It aims to prepare skilled graduates who know the
                           key aspects of VLSI Design which is required in VLSI domain Industries.
                       </p>
                       <p>
                           Through the M.Tech. - Electronics Engineering (VLSI Design and Technology) program, graduates
                           will develop an in-depth understanding of semiconductor devices, VLSI circuit design and
                           verification, FPGA design, and fabrication process, along with hands-on experience with leading EDA
                           (Electronic Design & Automation) tools. The evergreen VLSI domain is for the design and
                           verification of electronics systems and circuits, and its applications are found in areas like
                           Communications, Signal & Image Processing, Space Research, and Automation Industry.
                       </p>
                       <h4 class="text-xl font-semibold mt-4">Key Highlights</h4>
                       <ul class="list-disc pl-6 mb-4">
                           <li>The India Semiconductor Mission (ISM) an initiative by the Govt of India for growth
                               of VLSI will help to have more placement opportunities in India
                           </li>
                           <li>The program includes hands-on experience with EDA (Software) tools</li>
                           <li>Includes industrial internship</li>
                           <li>Experienced & Skilled Faculty</li>
                           <li>Advanced Labs equipped</li>
                       </ul>
                       <h4 class="text-xl font-semibold mt-4">USPs of B.Tech. in Electronics Engineering (VLSI Design and Technology)</h4>
                       <ul class="list-disc pl-6 mb-4">
                           <li>New program launched in accordance with India Semiconductor Mission (ISM)</li>
                           <li>Highest paying, high-demand professional for semiconductor chip industry</li>
                           <li>Research labs for semiconductor chip design and fabrication equipped with
                               B2spice, RF Spice, Xilinx software, and FPGA boards.
                           </li>
                       </ul>
                       <h4 class="text-xl font-semibold mt-4">Career Opportunities</h4>
                       <ul class="list-disc pl-6 mb-4">
                           <li>Design Engineer</li>
                           <li>Verification Engineer</li>
                           <li>CAD Engineer</li>
                           <li>Application Engineer</li>
                           <li>Fabrication Engineer</li>
                           <li>Research & Development Engineer</li>
                       </ul>
                       <h4 class="text-xl font-semibold mt-4">Prominent Recruiters</h4>
                       <ul class="list-disc pl-6">
                           <li>Synopsys</li>
                           <li>Mentor Graphics</li>
                           <li>Cadence</li>
                           <li>Qualcomm</li>
                           <li>Intel Technologies</li>
                           <li>HCL Corporation</li>
                           <li>einfochips</li>
                           <li>Texas Instruments</li>
                           <li>Synapse Design</li>
                           <li>AMD-Xilinx India Technology</li>
                           <li>Samsung</li>
                       </ul>
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
                           <c:if test="${'HOD' eq item and UserName eq 'hodeee'}">
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
                                           <c:if test="${'HOD' eq item and UserName eq 'hodeee'}">
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
                       <a href="/department/eee/notice" class="block mt-4 text-center bg-gray-300 hover:bg-gray-400 text-black font-semibold py-2 rounded">
                           View All
                           <i class="fa fa-angle-double-right"></i>
                       </a>
                   </div>
               </div>
           </div>
       </div>

       <jsp:include page="/jsp/footer.jsp"/>

