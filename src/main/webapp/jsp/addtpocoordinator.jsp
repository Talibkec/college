<jsp:include page="admin/HeaderDashboard.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<div class="container mx-auto my-10">
    <div class="flex justify-center">
        <div class="w-full max-w-lg">
            <div class="bg-blue-500 text-white p-4 rounded-t">
                <i class="fa fa-sign-in"></i>&nbsp;Add Placements
            </div>
            <div class="bg-white shadow-md rounded-b p-6">
                <form method="POST" enctype="multipart/form-data" id="addFacultyForm"
                      data-action="/auth/uploadfile/addtpocoordinator">
                    <div class="mb-4">
                        <label for="studentName" class="block text-gray-700 text-sm font-bold mb-2">Enter Student Name</label>
                        <input type="text" id="studentName" name="studentName"
                               class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:border-blue-500"
                               autocomplete="off">
                    </div>

                    <div class="mb-4">
                        <label for="deptId" class="block text-gray-700 text-sm font-bold mb-2">Choose Department</label>
                        <select class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:border-blue-500"
                                id="deptId" name='deptId'>
                            <option value="1">Computer Sc. & Engineering</option>
                            <option value="2">Civil Engineering</option>
                            <option value="3">Mechanical Engineering</option>
                            <option value="4">Electrical & Electronics Engg.</option>
                            <option value="5">Applied Sc. & Humanities</option>
                            <option value="6">Electronics Engineering (VLSI Design and Technology)</option>
                        </select>
                    </div>

                    <div class="mb-4">
                        <label for="email" class="block text-gray-700 text-sm font-bold mb-2">Enter Email</label>
                        <input type="email" id="email" name="email"
                               class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:border-blue-500">
                    </div>

                    <div class="mb-4">
                        <label for="mobile" class="block text-gray-700 text-sm font-bold mb-2">Enter Mobile Number</label>
                        <input type="number" id="mobile" name="mobile"
                               class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:border-blue-500">
                    </div>

                    <div class="mb-4">
                        <label for="registrationNo" class="block text-gray-700 text-sm font-bold mb-2">Registration No.</label>
                        <input type="number" id="registrationNo" name="registrationNo"
                               class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:border-blue-500">
                    </div>

                    <div class="mb-4">
                        <label for="studentSession" class="block text-gray-700 text-sm font-bold mb-2">Enter Pass Out Year</label>
                        <input type="number" id="studentSession" name="studentSession"
                               class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:border-blue-500">
                    </div>

                    <div id="form_message"></div>
                    <div id="msg" class="text-red-500"></div>

                    <div class="flex justify-center">
                        <input type="submit" value="Submit" id="btnSubmit"
                               class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/addfaculty.js"></script>
