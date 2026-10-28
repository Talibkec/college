<jsp:include page="HeaderDashboard.jsp" />
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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

                    <c:if test="${empty labDepts}"><p class="text-red-700" id="no-lab-department">You are not the HOD of a department, so you cannot upload lab documents.</p></c:if>
                    <div class="flex flex-col">
                        <label for="department" class="text-gray-700 font-medium mb-2">Choose Department</label>
                        <select id="deptId" name='deptId' class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                            <c:if test="${labDepts.contains('1')}"><option value="1"${selectedDept eq '1' ? ' selected' : ''}>Computer Sc. & Engineering</option></c:if>
                            <c:if test="${labDepts.contains('2')}"><option value="2"${selectedDept eq '2' ? ' selected' : ''}>Civil Engineering</option></c:if>
                            <c:if test="${labDepts.contains('3')}"><option value="3"${selectedDept eq '3' ? ' selected' : ''}>Mechanical Engineering</option></c:if>
                            <c:if test="${labDepts.contains('4')}"><option value="4"${selectedDept eq '4' ? ' selected' : ''}>Electrical & Electronics Engg.</option></c:if>
                            <c:if test="${labDepts.contains('5')}"><option value="5"${selectedDept eq '5' ? ' selected' : ''}>Applied Sc. & Humanities</option></c:if>
                            <c:if test="${labDepts.contains('6')}"><option value="6"${selectedDept eq '6' ? ' selected' : ''}>Electronics Engineering (VLSI Design and Technology)</option></c:if>
                            <c:if test="${labDepts.contains('7')}"><option value="7"${selectedDept eq '7' ? ' selected' : ''}>Mechanical and Manufacturing Engineering</option></c:if>
                            <c:if test="${labDepts.contains('8')}"><option value="8"${selectedDept eq '8' ? ' selected' : ''}>Manufacturing Engineering</option></c:if>
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
