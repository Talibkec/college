<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/jsp/header.jsp"/>

<div class="container mx-auto">
    <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
       <aside class="col-span-1 p-2">
           <div class="bg-gradient-to-b from-white to-gray-50 p-6 shadow-lg rounded-lg border border-gray-200">
               <section id="block-menu-block-3" class="block block-menu-block">
                   <c:choose>
                       <c:when test="${facultyDetails.departmentId eq '1'}">
                           <h2 class="block-title text-xl font-semibold mb-4 text-gray-800">Faculty of Computer Sc. & Engg.</h2>
                       </c:when>
                       <c:when test="${facultyDetails.departmentId eq '2'}">
                           <h2 class="block-title text-xl font-semibold mb-4 text-gray-800">Faculty of Civil Engg.</h2>
                       </c:when>
                       <c:when test="${facultyDetails.departmentId eq '3'}">
                           <h2 class="block-title text-xl font-semibold mb-4 text-gray-800">Faculty of Mechanical Engg.</h2>
                       </c:when>
                       <c:when test="${facultyDetails.departmentId eq '4'}">
                           <h2 class="block-title text-xl font-semibold mb-4 text-gray-800">Faculty of Electrical & Electronics Engg.</h2>
                       </c:when>
                       <c:when test="${facultyDetails.departmentId eq '5'}">
                           <h2 class="block-title text-xl font-semibold mb-4 text-gray-800">Faculty of Applied Sc. & Humanities</h2>
                       </c:when>
                   </c:choose>
                   <div class="menu-block-wrapper">
                       <ul class="menu nav flex flex-col gap-3">
                           <c:forEach items="${facultyNameList}" var="entry">
                               <c:forEach items="${entry}" var="entryVal">
                                   <li class="leaf menu-mlid-1467">
                                       <a href="/facultyDetails?facultyId=${entryVal.value}" class="text-gray-700 hover:text-blue-700 hover:bg-gray-100 px-3 py-2 rounded-md transition-colors duration-200 ease-in-out">${entryVal.key}</a>
                                   </li>
                               </c:forEach>
                           </c:forEach>
                       </ul>
                   </div>
               </section>
           </div>
       </aside>


        <div class="col-span-2">
            <c:forEach items="${Role}" var='role'>
                <c:if test="${fUserName eq loggedInUser and 'Faculty' eq role}">
                    <div class="flex flex-col lg:flex-row gap-4 mb-4">
                        <strong><a href="/uploadfile/facultyFileUpload?isProfilePic=No" class="text-blue-700 hover:underline">Upload Documents</a></strong>
                        <strong><a href="/uploadfile/facultyFileUpload?isProfilePic=Yes" class="text-blue-700 hover:underline">Update Profile Details</a></strong>
                        <strong><a href="/uploadfile/editfacultydetails?facultyId=${facultyDetails.facultyId}" class="text-blue-700 hover:underline">Edit Profile Details</a></strong>
                        <strong><a href="/uploadNoticeFaculty" class="text-blue-700 hover:underline">Upload Notice</a></strong>
                        <strong><a href="/user/dashboard" class="text-blue-700 hover:underline">Go to Dashboard</a></strong>
                    </div>
                </c:if>
            </c:forEach>

            <ul class="nav nav-tabs flex justify-around mb-3" role="tablist">
                <li role="presentation" class="active">
                    <a href="#profile" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5" aria-controls="profile" role="tab" data-toggle="tab">
                        <i class="fa fa-user"></i> Profile
                    </a>
                </li>
                <li role="presentation">
                    <a href="#downloads" aria-controls="downloads" role="tab" data-toggle="tab" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5">
                        <i class="fa fa-download"></i> Downloads
                    </a>
                </li>
                <c:forEach var="item" items="${Role}">
                    <c:if test="${'Faculty' eq item and fUserName eq loggedInUser}">
                        <!-- Uncomment if needed
                        <li role="presentation">
                            <a href="#order" aria-controls="order" role="tab" data-toggle="tab">
                                <i class="fa fa-angle-double-right"></i> Request
                            </a>
                        </li>
                        -->
                    </c:if>
                </c:forEach>
            </ul>

            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="profile">
                    <img alt="" id="profileImage" class="h-52 w-64 object-cover mb-4" src="/${facultyDetails.facultyId}/image${fileExtension}"/>
                    <h2 class="text-2xl font-semibold mb-2">${facultyDetails.facultyName}</h2>
                    <p class="text-lg mb-4">Assistant Professor <br/>
                        <c:choose>
                            <c:when test="${facultyDetails.departmentId eq '1'}">
                                Department of Computer Sc. & Engineering
                            </c:when>
                            <c:when test="${facultyDetails.departmentId eq '2'}">
                                Department of Civil Engineering
                            </c:when>
                            <c:when test="${facultyDetails.departmentId eq '3'}">
                                Department of Mechanical Engineering
                            </c:when>
                            <c:when test="${facultyDetails.departmentId eq '4'}">
                                Department of Electrical and Electronics Engineering
                            </c:when>
                            <c:when test="${facultyDetails.departmentId eq '5'}">
                                Department of Applied Science & Humanities
                            </c:when>
                        </c:choose>
                    </p>
                    <p class="text-base mb-4">
                        <i class="fa fa-phone"></i> ${facultyDetails.facultyMobNo} &nbsp; | &nbsp;
                        <i class="fa fa-envelope"></i> <a href="mailto:${facultyDetails.facultyOfficialEmail}" class="text-blue-700 hover:underline">${facultyDetails.facultyOfficialEmail}</a> &nbsp; | &nbsp;
                        <i class="fa fa-envelope"></i> <a href="mailto:${facultyDetails.facultyPersonalEmail}" class="text-blue-700 hover:underline">${facultyDetails.facultyPersonalEmail}</a>
                    </p>

                    <c:forEach items="${facultyDetails.facultyKeyProps}" var="keyprop">
                        <div class="border border-gray-300 rounded p-4 mb-4">
                            <div class="text-white p-2 bg-blue-700 rounded">
                                <strong>${keyprop.keyPropertyName}</strong>
                            </div>
                            <c:forEach items="${keyprop.keyPropVals}" var="propVal">
                                <ul class="list-disc pl-4 mt-2">
                                    <li>${propVal.keyPropVal}</li>
                                </ul>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>

                <div role="tabpanel" class="tab-pane" id="downloads">
                    <div class="p-4">
                        <div class="space-y-4">
                            <c:forEach items="${facultyDocument}" var="facultyDocument">
                                <div class="flex flex-col md:flex-row items-start md:items-center space-y-2 md:space-y-0 md:space-x-4 p-4 border border-gray-200 rounded-lg shadow-sm bg-white">
                                    <span class="border border-gray-300 text-gray-800 px-3 py-1 rounded-md bg-gray-50">${facultyDocument.date}</span>
                                    <c:forEach var="item" items="${Role}">
                                        <c:if test="${'Faculty' eq item and fUserName eq loggedInUser}">
                                            <a href="/common/deleteFacultyDoc/${facultyDocument.id}" class="bg-red-600 text-white hover:bg-red-700 px-4 py-2 rounded-md transition duration-200 ease-in-out">
                                                Delete
                                            </a>
                                        </c:if>
                                    </c:forEach>

                                    <c:choose>
                                        <c:when test="${facultyDocument.link}">
                                            <a href="${facultyDocument.linkAddress}" target="_blank" class="text-blue-600 hover:text-blue-800 hover:bg-blue-50 px-3 py-2 rounded-md transition-colors duration-200 ease-in-out">${facultyDocument.headLine}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/${facultyDocument.id}/documents${facultyDocument.fileType}" target="_blank" class="text-blue-600 hover:text-blue-800 hover:bg-blue-50 px-3 py-2 rounded-md transition-colors duration-200 ease-in-out">${facultyDocument.headLine}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div role="tabpanel" class="tab-pane hidden" id="order">
                    <div class="p-4">
                        <a href="/fstore/faculty/order" class="text-blue-700 hover:underline">Order Item</a>
                    </div>
                    <div class="p-4">
                        <a href="/fstore/faculty/facultyOrderHistory" class="text-blue-700 hover:underline">Order History</a>
                    </div>
                    <div class="p-4">
                        <div class="grid grid-cols-7 border border-green-500 p-2">
                            <div class="col-span-2 text-center font-bold">Product Name</div>
                            <div class="col-span-1 text-center font-bold">Qty</div>
                            <div class="col-span-1 text-center font-bold">Status</div>
                            <div class="col-span-1 text-center font-bold">Requested Date</div>
                            <div class="col-span-1 text-center font-bold">Approval/Rejection Date</div>
                            <div class="col-span-1 text-center font-bold">Edit</div>
                            <div class="col-span-1 text-center font-bold">Delete</div>
                        </div>
                        <c:forEach items="${requests}" var="request">
                            <div class="grid grid-cols-7 border border-green-500 p-2">
                                <div class="col-span-2 text-center">${request.product.productName}</div>
                                <div class="col-span-1 text-center">${request.productQuantity}</div>
                                <div class="col-span-1 text-center" id="statusId${request.requestId}">${request.status}</div>
                                <div class="col-span-1 text-center">
                                    <fmt:formatDate value="${request.requestDate}" pattern="dd/MM/yyyy"/>
                                </div>
                                <div class="col-span-1 text-center">
                                    <fmt:formatDate value="${request.approvalDate}" pattern="dd/MM/yyyy"/>
                                </div>
                                <div class="col-span-1 text-center">
                                    <a href="/fstore/faculty/editfacultyrequest?id=${request.requestId}" class="text-blue-700 hover:underline">Edit</a>
                                </div>
                                <div class="col-span-1 text-center">
                                    <a href="/fstore/faculty/deletefacultyrequest?requestId=${request.requestId}" class="text-red-600 hover:underline">Delete</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp"/>
<script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/facultyprofile.js"></script>

