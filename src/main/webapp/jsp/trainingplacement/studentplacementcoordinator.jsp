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

<div class="container mx-auto py-8">
    <div class="md:flex justify-center">
        <!-- Sidebar -->
        <aside class="w-full md:w-1/4 p-4">
            <div class="bg-white shadow-lg rounded-lg">
                <section id="block-menu-block-3" class="p-4">
                    <h2 class="text-lg font-semibold mb-4">Training & Placement</h2>
                    <ul class="space-y-2">
                        <li><a href="/trainingplacement/aboutplacement" class="text-blue-600 hover:underline">About Placement</a></li>
                        <li><a href="/trainingplacement/rulesoftrainingplacement" class="text-blue-600 hover:underline">Rules of Training & Placement</a></li>
                        <li><a href="/trainingplacement/tipsforresume" class="text-blue-600 hover:underline">Tips for Resume</a></li>
                        <li><a href="/trainingplacement/placedstudent" class="text-blue-600 hover:underline">Placed Students</a></li>
                        <li><a href="/trainingplacement/placementbrochure" class="text-blue-600 hover:underline">Placement Brochure</a></li>
                    </ul>
                </section>
            </div>
        </aside>

        <!-- Coordinator Table -->
        <div class="w-full md:w-3/4 p-4">
            <div class="overflow-auto">
                <table class="min-w-full border border-gray-300 table-auto">
                    <thead class="bg-gray-100">
                        <tr>
                            <th class="border px-4 py-2 text-left">SNO</th>
                            <th class="border px-4 py-2 text-left">NAME</th>
                            <th class="border px-4 py-2 text-left">DEPARTMENT</th>
                            <th class="border px-4 py-2 text-left">EMAIL ID</th>
                            <th class="border px-4 py-2 text-left">MOBILE NUMBER</th>
                            <th class="border px-4 py-2 text-left">Registration NO</th>
                            <th class="border px-4 py-2 text-left">SESSION</th>
                            <th class="border px-4 py-2 text-left">ACTION</th>
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
                                <td class="border px-4 py-2">
                                    <c:forEach var="item" items="${Role}">
                                        <c:if test="${'Admin' eq item}">
                                            <a href="<c:url value='/auth/tpoCoordinator/${coordinator.coordinatorId}'/>" class="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600">Delete</a>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp" />
