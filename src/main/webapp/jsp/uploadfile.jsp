<jsp:include page="admin/HeaderDashboard.jsp" />
<div class="container mx-auto px-4 py-6">
    <div class="bg-white shadow-md rounded-lg p-6 max-w-lg mx-auto">
        <div class="text-center mb-6">
            <h2 class="text-xl font-bold text-gray-800">
                <i class="fa fa-sign-in"></i> Upload Notice/News
            </h2>
        </div>

        <form method="POST" enctype="multipart/form-data" id="fileUploadForm" action="#" class="space-y-6">
            <!-- Headline -->
            <div>
                <label for="noticeHeader" class="block text-sm font-medium text-gray-700">Head Line</label>
                <input type="text" name="noticeHeader" id="noticeHeader" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm" />
            </div>

            <!-- Notice Type -->
            <div>
                <label for="noticeType" class="block text-sm font-medium text-gray-700">Notice Type</label>
                <select name="noticeType" id="noticeType" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
                    <option value="Notice">Notice</option>
                    <option value="News">News</option>
                    <option value="Tender">Tender</option>
                </select>
            </div>

            <!-- Date -->
            <div>
                <label for="noticeUploadDate" class="block text-sm font-medium text-gray-700">Date</label>
                <input type="text" name="date" id="noticeUploadDate" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm" />
            </div>

            <!-- Link -->
            <div>
                <label for="link" class="block text-sm font-medium text-gray-700">Link</label>
                <input type="text" name="link" id="link" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm" />
            </div>

            <!-- Scrollable -->
            <div class="flex items-center">
                <input type="checkbox" name="isScrollable" id="isScrollable" class="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500" />
                <label for="isScrollable" class="ml-2 text-sm font-medium text-gray-700">Scrollable</label>
            </div>

            <!-- Choose File -->

            <div class="flex flex-col">
                                <label for="slideImage" class="text-gray-700 font-medium mb-2">Choose Document</label>
                                <input type="file" name="noticeFile" id="noticeFile" class="w-full text-gray-700" required />
                            </div>

            <div id="msg" class="text-red-500 text-sm"></div>

            <div class="flex justify-center">
                <button type="submit" id="btnSubmit" class="w-full py-2 px-4 bg-blue-500 text-white font-semibold rounded-md shadow-sm hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    Submit
                </button>
            </div>
        </form>

        <form method="GET" action="/" id="homePageForm" class="hidden">
            <input type="hidden" name="date" id="date" />
            <input type="hidden" name="hnoticeHeader" id="hnoticeHeader" />
            <input type="hidden" name="hfileLocation" id="hfileLocation" />
            <input type="hidden" name="hnoticeType" id="hnoticeType" />
        </form>
    </div>
</div>

<jsp:include page="footer.jsp" />
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="/js/store/jquery-ui/jquery-ui.js"></script>
<script src="/js/fileupload.js"></script>
