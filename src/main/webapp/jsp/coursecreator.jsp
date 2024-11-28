<jsp:include page="admin/HeaderDashboard.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<div class="container mx-auto my-10">
    <div class="flex justify-center">
        <div class="w-full max-w-lg">
            <div class="bg-blue-500 text-white p-4 rounded-t">
                <i class="fa fa-sign-in"></i>&nbsp;Add Course Seat
            </div>
            <div class="bg-white shadow-md rounded-b p-6">
                <form method="POST" enctype="multipart/form-data" id="addFacultyForm" data-action="/auth/uploadfile/coursecreator">
                    <div class="mb-4">
                        <label for="branchName" class="block text-gray-700 text-sm font-bold mb-2">Choose Department</label>
                        <select class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:border-blue-500"
                                id="branchName" name='branchName'>
                            <option value="Computer Sc. & Engineering">Computer Sc. & Engineering</option>
                            <option value="Civil Engineering">Civil Engineering</option>
                            <option value="Mechanical Engineering">Mechanical Engineering</option>
                            <option value="Electrical & Electronics Engg.">Electrical & Electronics Engg.</option>
                            <option value="Applied Sc. & Humanities">Applied Sc. & Humanities</option>
                            <option value="Electronics Engineering (VLSI Design and Technology)">Electronics Engineering (VLSI Design and Technology)</option>
                        </select>
                    </div>

                    <div class="mb-4">
                        <label for="regularSeat" class="block text-gray-700 text-sm font-bold mb-2">Regular seat</label>
                        <input type="number" id="regularSeat" name="regularSeat"
                               class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:border-blue-500">
                    </div>

                    <div class="mb-4">
                        <label for="lateralSeat" class="block text-gray-700 text-sm font-bold mb-2">Lateral Seat</label>
                        <input type="number" id="lateralSeat" name="lateralSeat"
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
<script src="/js/addcourse.js"></script>

