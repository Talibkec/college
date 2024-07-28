<jsp:include page="admin/HeaderDashboard.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<div class="container mx-auto px-4 py-6">
    <div class="flex justify-center">
        <div class="bg-white shadow-lg rounded-lg p-6 max-w-lg w-full">
            <div class="text-center mb-6">
                <h2 class="text-xl font-bold text-gray-800">
                    <i class="fa fa-sign-in"></i> Add Faculty
                </h2>
            </div>

            <form method="POST" enctype="multipart/form-data" id="addFacultyForm" data-action="/auth/uploadfile/addfaculty" class="space-y-6">
                <div class="relative">
                    <label for="facultyName" class="block text-sm font-medium text-gray-700">Enter Name</label>
                    <input type="text" id="facultyName" name="facultyName" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm" autocomplete="off">
                </div>

                <div class="relative">
                    <label for="deptId" class="block text-sm font-medium text-gray-700">Choose Department</label>
                    <select id="deptId" name="deptId" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
                        <option value="1">Computer Sc. & Engineering</option>
                        <option value="2">Civil Engineering</option>
                        <option value="3">Mechanical Engineering</option>
                        <option value="4">Electrical & Electronics Engg.</option>
                        <option value="5">Applied Sc. & Humanities</option>
                        <option value="6">Electronics Engineering (VLSI Design and Technology)</option>
                        <option value="7">Mechanical and Manufacturing Engineering</option>
                        <option value="8">Manufacturing Engineering</option>
                    </select>
                </div>

                <div class="relative">
                    <label for="facultyOfficialEmail" class="block text-sm font-medium text-gray-700">Enter Official Email</label>
                    <input type="email" id="facultyOfficialEmail" name="facultyOfficialEmail" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
                </div>

                <div class="relative">
                    <label for="facultyPersonalEmail" class="block text-sm font-medium text-gray-700">Enter Personal Email</label>
                    <input type="email" id="facultyPersonalEmail" name="facultyPersonalEmail" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
                </div>

                <div class="relative">
                    <label for="facultyMobNo" class="block text-sm font-medium text-gray-700">Mobile No.</label>
                    <input type="number" id="facultyMobNo" name="facultyMobNo" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
                </div>

                <div class="relative">
                    <label for="userName" class="block text-sm font-medium text-gray-700">Enter User Name</label>
                    <input type="text" id="userName" name="userName" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm" autocomplete="off">
                </div>

                <div id="form_message" class="text-red-500 text-sm"></div>

                <button type="submit" id="btnSubmit" class="w-full py-2 px-4 bg-blue-500 text-white font-semibold rounded-md shadow-sm hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-green-500">
                    Submit
                </button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/addfaculty.js"></script>
