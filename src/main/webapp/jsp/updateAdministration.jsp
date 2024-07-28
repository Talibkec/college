<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="admin/HeaderDashboard.jsp" />

<div class="container mx-auto my-12 px-4">
    <div class="flex justify-center">
        <div class="w-full max-w-lg bg-white rounded-lg shadow-lg p-6">
            <h2 class="text-2xl font-semibold text-gray-800 mb-6 flex items-center">
                <i class="fa fa-sign-in mr-2"></i> Update
            </h2>

            <form method="POST" enctype="multipart/form-data" id="updateAdministrationForm" class="space-y-6">
                <div class="flex flex-col">
                    <label for="facultyEmail" class="text-gray-700 font-medium mb-2">Enter Official Email of Faculty</label>
                    <input type="email" id="facultyEmail" name="facultyEmail" placeholder="Faculty Official Email"
                        class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </div>

                <div class="flex flex-col">
                    <label for="role" class="text-gray-700 font-medium mb-2">Enter Role</label>
                    <input type="text" id="role" name="role" placeholder="Role Value"
                        class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </div>

                <div class="flex flex-col">
                    <label for="designation" class="text-gray-700 font-medium mb-2">Enter Designation</label>
                    <input type="text" id="designation" name="designation" placeholder="Designation"
                        class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </div>

                <div class="flex flex-col">
                    <label for="dutyAssigned" class="text-gray-700 font-medium mb-2">Assign Administration Duty</label>
                    <input type="text" id="dutyAssigned" name="updateDutyAssigned" placeholder="Administration Duty"
                        class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </div>

                <div class="flex justify-center">
                    <button type="submit" id="btnSubmit" class="bg-green-500 text-white font-bold w-full rounded-md py-2 hover:bg-gray-600 transition duration-300">
                        Submit
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
<script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/uploadAdministration.js"></script>
