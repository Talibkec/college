<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="admin/HeaderDashboard.jsp" />

<div class="container mx-auto my-12 px-4">
    <div class="flex justify-center">
        <div class="w-full max-w-lg bg-white rounded-lg shadow-lg p-6">
            <h2 class="text-2xl font-semibold text-gray-800 mb-4 flex items-center">
                <i class="fa fa-sign-in mr-2"></i> Upload Achievement Image
            </h2>

            <form method="POST" enctype="multipart/form-data" id="achievementImageUploadForm" action="/" class="space-y-6">
                <div class="flex flex-col">
                    <label for="caption" class="text-gray-700 font-medium mb-2">Caption</label>
                    <input type="text" name="caption" id="caption" class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="Enter caption" />
                </div>

                <div class="flex flex-col">
                    <label for="slideImage" class="text-gray-700 font-medium mb-2">Choose File</label>
                    <input type="file" name="slideImage" id="slideImage" class="w-full text-gray-700" />
                </div>

                <div id="msg" class="text-red-500 hidden">*Please Select a file</div>
                <select name="displayFlag" required>
                    <option value="0">Desktop (6000x4000)</option>
                    <option value="1">Mobile (1080x1920)</option>
                </select>
                <div class="text-center">
                    <button type="submit" id="btnSubmit" class="bg-blue-500 text-white font-bold w-full py-2 rounded-md hover:bg-gray-600 transition duration-300">
                        Submit
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/js/store/jquery-ui/jquery-ui.js"></script>
<script src="/js/achievementImageupload.js"></script>
