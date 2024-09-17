<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/jsp/header.jsp" />

<div class="bg-gray-200 py-4 mt-4">
    <div class="container mx-auto px-4">
        <div class="flex items-center justify-between">
            <!-- Title Section -->
            <div class="flex-grow text-center">
                <h3 class="text-2xl font-semibold">Students Placement Coordinator</h3>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="md:flex justify-center">
        <aside class="col-sm-3" role="complementary">
            <div class="region region-sidebar-second">
                <section id="block-menu-block-3" class="block block-menu-block clearfix">
                    <h2 class="block-title">Training & Placement </h2>
                    <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                        <ul class="menu nav">
                            <li class="first leaf menu-mlid-1154"><a href="/trainingplacement/aboutplacement" class="sf-depth-2 active">About Placement</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/rulesoftrainingplacement" class="sf-depth-2">Rules of Training & Placement</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/tipsforresume" class="sf-depth-2">Tips for Resume</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/placedstudent" class="sf-depth-2">Placed Students</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/placementbrochure" class="sf-depth-2">Placement Brochure</a></li>
                        </ul>
                    </div>
                </section>
            </div>
        </aside>

<div class=" p-3">
   <div class="w-full overflow-auto">
       <div class="m-4">
           <table class="min-w-full table-auto border-collapse border border-gray-300">
               <thead class="bg-gray-100">
                   <tr>
                       <th class="border px-4 py-2 text-left">SNO</th>
                       <th class="border px-4 py-2 text-left">NAME</th>
                       <th class="border px-4 py-2 text-left">DEPARTMENT</th>
                       <th class="border px-4 py-2 text-left">EMAIL ID</th>
                       <th class="border px-4 py-2 text-left">MOBILE NUMBER</th>
                       <th class="border px-4 py-2 text-left">Registration NO</th>
                       <th class="border px-4 py-2 text-left">SESSION</th>
                   </tr>
               </thead>
               <tbody>
                   <c:forEach var="coordinator" items="${coordinators}">
                       <tr class="hover:bg-gray-100">
                           <td class="border px-4 py-2">${coordinator.coordinatorId}</td>
                           <td class="border px-4 py-2">${coordinator.studentName}</td>
                           <td class="border px-4 py-2">${coordinator.departmentId}</td>
                           <td class="border px-4 py-2">${coordinator.email}</td>
                           <td class="border px-4 py-2">${coordinator.mobile}</td>
                           <td class="border px-4 py-2">${coordinator.registrationNo}</td>
                           <td class="border px-4 py-2">${coordinator.studentSession}</td>

                       </tr>
                       <c:forEach var="item" items="${Role}">
                           <c:if test="${'Admin' eq item}">
                               <a href="<c:url value='/auth/tpoCoordinator/${coordinator.tpoCoordinatorId}'/>" class="hover:underline border px-4 py-2 bg-red-500 text-white">Delete</a>
                           </c:if>
                       </c:forEach>

                   </c:forEach>
               </tbody>
           </table>
       </div>
   </div>
</div>

    </div>
</div>

<jsp:include page="/jsp/footer.jsp" />
