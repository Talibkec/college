<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%-- department name of the list (FacultyListView); fails for an empty list, like the former scriptlet --%>
<c:set var="branchName" value="${facultyListView.branchName}"/>

<style>
    .page-content {
        padding: 20px;
        padding-left: 60px;
        text-decoration: bold;
        background-color: #eeeeee;
        font-size: x-large;
    }
</style>

<!-- Header Section -->
<div class="bg-gray-200 py-4 mt-4">
    <div class="container mx-auto px-4">
        <div class="flex items-center justify-center">
            <h3 class="text-2xl font-semibold">Faculty Members</h3>
        </div>
    </div>
</div>

<!-- Main Content -->
<div class="container mx-auto py-6 px-4">
    <div class="md:flex justify-center">
        <div class="w-full md:w-3/4 lg:w-2/3 p-4 bg-white shadow-lg rounded-lg">

            <hr class="my-4"/>

            <!-- Add Faculty Button based on User Type -->
            <c:if test="${userType == 2}">
                <a href="auth/uploadfile/addfaculty">
                    <button class="w-full md:w-auto text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-4 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">
                        Add Faculty
                    </button>
                </a>
            </c:if>
            <c:if test="${userType == 1}">
                <a href="hod/addfaculty">
                    <button class="w-full md:w-auto text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-4 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">
                        Add Faculty
                    </button>
                </a>
            </c:if>

            <%-- the first entry is the HOD (FacultyProfileService puts the HOD first) --%>
            <!-- Faculty List -->
            <c:forEach items="${facultyList}" var="faculty" varStatus="status">
                <div class="bg-gray-100 border border-gray-200 rounded-lg p-4 mb-4">
                    <div class="flex flex-col md:flex-row justify-between">
                        <!-- Faculty Image -->
                        <div class="flex-shrink-0 mb-4 md:mb-0">
                            <img src="${faculty.imageURL}" alt="Faculty Image" class="h-full w-32  object-cover border border-gray-300">
                        </div>
                        <!-- Faculty Info -->
                        <div class="flex-grow md:pl-4">
                            <h4 class="text-lg font-semibold text-gray-800">
                                ${faculty.facultyName}
                                <c:if test="${status.first}"> ( HOD  ${branchName} )</c:if>
                            </h4>
                            <p class="text-sm text-gray-600"><strong>Department:</strong> ${branchName}</p>
                            <p class="text-sm text-gray-600"><strong>Designation:</strong> Assistant Professor</p>
                            <p class="text-sm text-gray-600"><strong>Phone Number:</strong> ${faculty.facultyMobNo}</p>
                            <p class="text-sm text-gray-600"><strong>Email Address:</strong> <a href="mailto:${faculty.facultyOfficialEmail}" class="text-blue-500">${faculty.facultyOfficialEmail}</a></p>
                        </div>
                        <!-- Profile and Delete Buttons -->
                        <div class="flex flex-col items-start mt-4 md:mt-0 md:items-end">
                            <a href="/facultyDetails?facultyId=${faculty.facultyId}" class="mb-2">
                                <button class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">
                                    <i class="fa fa-link"></i> Profile
                                </button>
                            </a>
                            <c:if test="${userType > 0}">
                                <a href="/deleteFaculty?deptno=${faculty.departmentId}&facultyId=${faculty.facultyId}" class="mt-2">
                                    <button class="text-white bg-red-600 hover:bg-red-700 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-red-500 dark:hover:bg-red-600 focus:outline-none dark:focus:ring-red-700">
                                        Delete
                                    </button>
                                </a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp"/>
