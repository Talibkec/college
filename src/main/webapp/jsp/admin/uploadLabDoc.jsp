<jsp:include page="HeaderDashboard.jsp" />

<div class="container mx-auto py-12">
    <div class="flex justify-center">
        <div class="w-full max-w-lg bg-white rounded-lg shadow-lg p-8 ">
            <div class="mb-6 text-center">
                <h2 class="text-2xl font-semibold text-gray-800"><i class="fa fa-sign-in"></i> Upload Lab Doc</h2>
            </div>
            <div class="bg-white p-6 rounded-lg shadow-md">
                <form method="POST" enctype="multipart/form-data" id="slideimageUploadForm" class="space-y-6">
                    <div class="flex flex-col">
                        <label for="caption" class="text-gray-700 font-medium mb-2">Enter caption of Lab Doc</label>
                        <input type="text" name="caption" id="caption" class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" required />
                    </div>

                    <div class="flex flex-col">
                        <label for="department" class="text-gray-700 font-medium mb-2">Choose Department</label>
                        <select id="deptId" name='deptId' class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
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

                    <div class="flex flex-col">
                        <label for="slideImage" class="text-gray-700 font-medium mb-2">Choose Document</label>
                        <input type="file" name="slideImage" id="slideImage" class="w-full text-gray-700" required />
                    </div>

                    <div id="msg" class="text-red-500"></div>

                    <div class="text-center">
                        <button type="submit" id="btnSubmit" class="bg-blue-500 text-white font-bold w-full py-2 rounded-md hover:bg-gray-600 transition duration-300">Upload</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp" />
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/js/store/jquery-ui/jquery-ui.js"></script>
<script src="/js/uploadLabDoc.js"></script>
