<jsp:include page="admin/HeaderDashboard.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<div class="container mx-auto px-4 py-6">
    <div class="flex justify-center">
        <div class="bg-white shadow-lg rounded-lg p-6 max-w-lg w-full">
            <div class="text-center mb-6">
                <h2 class="text-xl font-bold text-gray-800">
                    <i class="fa fa-sign-in"></i> Add Staff
                </h2>
            </div>

            <form method="POST" enctype="multipart/form-data" id="addFacultyForm" data-action="/auth/uploadfile/addstaff" class="space-y-6">
                <div class="relative">
                    <label for="facultyName" class="block text-sm font-medium text-gray-700">Enter Name</label>
                    <input type="text" id="facultyName" name="facultyName" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm" autocomplete="off">
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
                    <label for="slideImage" class="block text-sm font-medium text-gray-700">Choose Profile Picture</label>
                    <input type="file" name="slideImage" id="slideImage" class="mt-1 block w-full text-sm text-gray-500 file:py-2 file:px-4 file:border file:border-gray-300 file:rounded-md file:text-sm file:font-semibold file:bg-blue-50 hover:file:bg-blue-100">
                </div>

                <div id="form_message" class="text-red-500 text-sm"></div>
                <div id="msg" class="text-red-500 text-sm"></div>

                <button type="submit" id="btnSubmit" class="w-full py-2 px-4 bg-green-500 text-white font-semibold rounded-md shadow-sm hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-green-500">
                    Submit
                </button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
