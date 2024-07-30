<%@ page import="com.college.service.UserService" %>
<%@ page import="com.college.core.model.StaffDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.http.converter.json.GsonBuilderUtils" %>
<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="jumbotron py-6"></div>

<div class="container mx-auto px-4">
    <div class="md:flex justify-between space-y-4 md:space-y-0">

        <!-- Sidebar -->
        <aside class="md:w-1/4 w-full p-4 bg-white rounded-lg shadow-lg">
            <div class="region region-sidebar-second">
                <section id="block-menu-block-3" class="block block-menu-block clearfix">
                    <h2 class="block-title text-xl font-bold mb-4">About KEC</h2>
                    <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                        <ul class="menu nav space-y-2">
                            <li class="first leaf menu-mlid-1154"><a href="/about/principal" class="sf-depth-2 active text-blue-500 hover:underline">Principal's Message</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/about/vision" class="sf-depth-2 text-blue-500 hover:underline">Vision & Mission</a></li>
                            <li class="leaf menu-mlid-1156"><a href="/about/allotment" class="sf-depth-2 text-blue-500 hover:underline">Allotment & Surrender Report</a></li>
                            <li class="leaf menu-mlid-1294"><a href="/about/affiliation" class="sf-depth-2 text-blue-500 hover:underline">Affiliation</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/about/administration" class="sf-depth-2 text-blue-500 hover:underline">Administration</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/about/staff" class="sf-depth-2 text-blue-500 hover:underline">Support Staff</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/about/history" class="sf-depth-2 text-blue-500 hover:underline">History</a></li>
                        </ul>
                    </div>
                </section>
            </div>
        </aside>

        <!-- Main Content -->
       <div class="md:w-3/4 w-full p-4">
           <div class="page-content bg-white p-6 rounded-lg shadow-lg">
               <h2 class="text-2xl font-bold mb-4">Support Staff</h2>
               <hr class="mb-4">
               <c:if test="${userType == 2}">
                   <a href="auth/uploadfile/addStaff">
                       <button class="btn btn-primary mt-2 p-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Add Staff</button>
                   </a>
               </c:if>
               <div class="grid grid-cols-1 lg:grid-cols-2 gap-4">
                   <c:forEach items="${staffList}" var="staff">
                       <div class="panel panel-default bg-white rounded-lg shadow-lg">
                           <div class="panel-heading faculty-title p-4 bg-gray-100 rounded-t-lg">
                               <b>${staff.staffName}</b>
                           </div>
                           <div class="panel-body p-4">
                               <div class="md:flex items-center">
                                   <div class="md:w-1/4 w-full mb-4 md:mb-0">
                                       <img alt="${staff.staffName}" class="rounded-lg" height="125px" width="125px" src="${staff.imageURL}">
                                   </div>
                                   <div class="md:w-2/4 w-full px-4">
                                       <p><strong>Phone Number:</strong> ${staff.staffMobNo}</p>
                                       <p><strong>Email Address:</strong> <a href="mailto:${staff.staffOfficialEmail}" class="text-blue-500 hover:underline">${staff.staffOfficialEmail}</a></p>
                                   </div>
                                   <div class="md:w-1/4 w-full mt-4 md:mt-0">
                                       <c:if test="${userType > 0}">
                                           <a href="/about/deleteStaff?staffId=${staff.staffId}">
                                               <button class="btn btn-danger mt-2 p-2 bg-red-500 text-white rounded-lg hover:bg-red-600">Delete Staff</button>
                                           </a>
                                       </c:if>
                                   </div>
                               </div>
                           </div>
                       </div>
                   </c:forEach>
               </div>
           </div>
       </div>

    </div>
</div>

<jsp:include page="/jsp/footer.jsp"/>
