<jsp:include page="header.jsp" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<div class="panel-heading bg-gray-200 p-5 text-3xl"> <i class="fa fa-sign-in"></i> &nbsp;Edit Profile Details</div>


<div class="container mx-auto p-4">
    <div class="max-w-2xl mx-auto bg-white shadow-lg rounded-lg p-6">
        <h2 class="text-2xl sm:text-3xl font-semibold mb-6 text-gray-800 flex items-center">
            <i class="fa fa-sign-in mr-2"></i> Edit Profile Details
        </h2>

        <form method="POST" enctype="multipart/form-data" id="editDetailsForm">
            <input type="hidden" id="facultyId" value="${facultyDetails.facultyId}" name="facultyId" />

            <!-- Name -->
            <div class="mb-4 flex flex-col">
                <label for="facultyName" class="text-base sm:text-lg font-medium text-gray-700 mb-1">Name</label>
                <input type="text" id="facultyName" name="facultyName" value="${facultyDetails.facultyName}" placeholder="Enter Name" class="form-input border border-gray-300 rounded-md shadow-sm px-4 py-2 w-full" />
            </div>

            <!-- Official Email -->
            <div class="mb-4 flex flex-col">
                <label for="facultyOfficialEmail" class="text-base sm:text-lg font-medium text-gray-700 mb-1">Official Email</label>
                <input type="email" id="facultyOfficialEmail" name="facultyOfficialEmail" value="${facultyDetails.facultyOfficialEmail}" placeholder="Enter Official Email" class="form-input border border-gray-300 rounded-md shadow-sm px-4 py-2 w-full" />
            </div>

            <!-- Personal Email -->
            <div class="mb-4 flex flex-col">
                <label for="facultyPersonalEmail" class="text-base sm:text-lg font-medium text-gray-700 mb-1">Personal Email</label>
                <input type="email" id="facultyPersonalEmail" name="facultyPersonalEmail" value="${facultyDetails.facultyPersonalEmail}" placeholder="Enter Personal Email" class="form-input border border-gray-300 rounded-md shadow-sm px-4 py-2 w-full" />
            </div>

            <!-- Mobile No -->
            <div class="mb-4 flex flex-col">
                <label for="facultyMobNo" class="text-base sm:text-lg font-medium text-gray-700 mb-1">Mobile No</label>
                <input type="text" id="facultyMobNo" name="facultyMobNo" value="${facultyDetails.facultyMobNo}" placeholder="Enter Mobile No." class="form-input border border-gray-300 rounded-md shadow-sm px-4 py-2 w-full" />
            </div>

            <!-- Existing Properties -->
            <div class="mb-6">
                <c:forEach items="${facultyDetails.facultyKeyProps}" var="keyProp">
                    <div class="border border-gray-300 rounded-lg p-4 mb-4">
                        <div class="flex flex-col sm:flex-row items-start sm:items-center mb-4">
                            <input type="text" name="keyProp" placeholder="Enter Property Heading" value="${keyProp.keyPropertyName}" class="form-input border border-gray-300 rounded-md shadow-sm px-4 py-2 flex-1 w-full sm:w-3/4" />
                            <a href="javascript:void(0);" class="mt-2 sm:mt-0 sm:ml-4 text-red-600 hover:text-red-800 font-medium remove_Key">Delete Property Heading</a>
                        </div>
                        <c:forEach items="${keyProp.keyPropVals}" var="propVal">
                            <div class="flex flex-col sm:flex-row items-start sm:items-center mb-2">
                                <input type="text" name="field_name" placeholder="Enter Property Value" value="${fn:escapeXml(propVal.keyPropVal)}" class="form-input border border-gray-300 rounded-md shadow-sm px-4 py-2 flex-1 w-full sm:w-3/4" />
                                <a href="javascript:void(0);" class="mt-2 sm:mt-0 sm:ml-4 text-green-600 hover:text-green-800 font-medium add_button"><span class="material-symbols-outlined">add</span></a>
                                <a href="javascript:void(0);" class="mt-2 sm:mt-0 sm:ml-2 text-red-600 hover:text-red-800 font-medium remove_button"><span class="material-symbols-outlined">remove</span></a>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>

            <!-- Add New Property Button -->
            <div class="text-center mb-6">
                <button type="button" id="addNewProp" class="bg-green-600 hover:bg-green-700 text-white font-semibold py-2 px-4 rounded-lg flex items-center justify-center">
                    <span class="material-symbols-outlined">add</span> Add New Property
                </button>
            </div>

            <!-- New Properties -->
            <div class="newPropsClass">
                <div class="newpropkeybox hidden border border-gray-300 rounded-lg p-4 mb-4">
                    <div class="flex flex-col sm:flex-row items-start sm:items-center mb-4">
                        <input type="text" name="propkey" placeholder="Property Heading" class="form-input border border-gray-300 rounded-md shadow-sm px-4 py-2 flex-1 w-full sm:w-3/4" />
                        <a href="javascript:void(0);" class="mt-2 sm:mt-0 sm:ml-4 text-red-600 hover:text-red-800 font-medium remove_Key" id="deleteNewProps">Delete</a>
                        <a href="javascript:void(0);" class="mt-2 sm:mt-0 sm:ml-2 text-green-600 hover:text-green-800 font-medium add_key">Add More</a>
                    </div>
                    <div class="flex flex-col sm:flex-row items-start sm:items-center mb-2">
                        <input type="text" name="field_name" placeholder="Property Value" class="form-input border border-gray-300 rounded-md shadow-sm px-4 py-2 flex-1 w-full sm:w-3/4" />
                        <a href="javascript:void(0);" class="mt-2 sm:mt-0 sm:ml-4 text-green-600 hover:text-green-800 font-medium add_button"><span class="material-symbols-outlined">add</span></a>
                        <a href="javascript:void(0);" class="mt-2 sm:mt-0 sm:ml-2 text-red-600 hover:text-red-800 font-medium remove_button"><span class="material-symbols-outlined">remove</span></a>
                    </div>
                </div>
            </div>

            <!-- Submit Button -->
            <div class="text-center">
                <input type="submit" value="Submit" id="btnSubmit" class="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded-lg" />
            </div>
        </form>
    </div>
</div>


<jsp:include page="footer.jsp" />
<script src="/js/editfacultydetails.js"></script>
