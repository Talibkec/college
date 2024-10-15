<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="admin/HeaderDashboard.jsp"/>

<div class="container mx-auto px-4 py-8">
    <div class="flex justify-center">
        <div class="w-full max-w-md">
            <div class="bg-white shadow-md rounded-lg">
                <div class="bg-gray-100 p-4 rounded-t-lg">
                    <h3 class="text-lg font-semibold text-gray-700 flex items-center">
                        <i class="fa fa-sign-in mr-2"></i>Upload AICTE Approval Documents
                    </h3>
                </div>
                <div class="p-6">
                    <form method="POST" enctype="multipart/form-data" id="slideimageUploadForm">
                        <!-- Enter Year Field -->
                        <div class="mb-4">
                            <label for="caption" class="block text-gray-700 text-sm font-medium mb-2">Enter Year</label>
                            <input type="text" name="caption" id="caption" class="w-full border-gray-300 rounded-md shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-200"/>
                        </div>

                        <!-- Choose Document Field -->
                        <div class="mb-4">
                            <label class="block text-gray-700 text-sm font-medium mb-2">Choose Document</label>
                            <input type="file" name="slideImage" id="slideImage" class="w-full text-red-500"/>
                        </div>

                        <div id="msg" class="text-red-500 mb-4"></div>

                        <!-- Submit Button -->
                        <div class="flex justify-center">
                            <input type="submit" value="Upload" id="btnSubmit" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>

<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/js/store/jquery-ui/jquery-ui.js"></script>
<script src="/js/uploadAICTEapproval.js"></script>
