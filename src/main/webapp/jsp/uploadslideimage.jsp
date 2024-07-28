<jsp:include page="admin/HeaderDashboard.jsp" />
<div class="container mx-auto px-4 py-6">
    <div class="bg-white shadow-md rounded-lg p-6 max-w-lg mx-auto">
        <div class="text-center mb-6">
            <h2 class="text-xl font-bold text-gray-800">
                <i class="fa fa-sign-in"></i> Upload Slide Image
            </h2>
        </div>
        <form method="POST" enctype="multipart/form-data" id="slideimageUploadForm" action="/" class="space-y-6">
            <div>
                <label for="caption" class="block text-sm font-medium text-gray-700">Caption</label>
                <input type="text" name="caption" id="caption" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm" />
            </div>

            <div class="flex flex-col">
                                <label for="slideImage" class="text-gray-700 font-medium mb-2">Choose Document</label>
                                <input type="file" name="slideImage" id="slideImage" class="w-full text-gray-700" required />
                            </div>

            <div id="msg" class="text-red-500 text-sm"></div>

            <div class="flex justify-center">
                <button type="submit" id="btnSubmit" class="w-full py-2 px-4 bg-blue-500 text-white font-semibold rounded-md shadow-sm hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    Submit
                </button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="footer.jsp" />
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/js/store/jquery-ui/jquery-ui.js"></script>
<script src="/js/slideimageupload.js"></script>
