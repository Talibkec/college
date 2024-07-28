<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="admin/HeaderDashboard.jsp" />

<div class="container mx-auto my-12">
    <div class="flex justify-center">
        <div class="w-full max-w-md bg-white rounded-lg shadow-lg p-6">
            <div class="text-center mb-4">
                <h2 class="text-2xl font-semibold text-gray-800"><i class="fa fa-sign-in"></i> Upload Holiday Doc</h2>
            </div>
            <form method="POST" enctype="multipart/form-data" id="slideimageUploadForm" class="space-y-6">
                <div class="flex flex-col">
                    <label for="caption" class="text-gray-700 font-medium mb-2">Enter Year of Holiday</label>
                    <input type="text" name="caption" id="caption" class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="e.g., 2024" required />
                </div>

                <div class="flex flex-col">
                    <label for="slideImage" class="text-gray-700 font-medium mb-2">Choose Document</label>
                    <input type="file" name="slideImage" id="slideImage" class="w-full text-gray-700" required />
                </div>

                <div id="msg" class="text-red-500"></div>

                <div class="text-center">
                    <button type="submit" id="btnSubmit" class="bg-blue-500 text-white font-bold w-full py-2 rounded-md hover:bg-gray-600 transition duration-300">
                        Upload
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/js/store/jquery-ui/jquery-ui.js"></script>
<script src="/js/uploadHoliday.js"></script>
