<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="bg-gray-200 py-4 mt-4">
    <div class="container mx-auto px-4">
        <div class="flex items-center justify-between">
            <!-- Social Media Icons Section -->
            <div class="flex-grow text-center">
                <h3 class="text-2xl font-semibold">Notices</h3>
            </div>

        </div>
    </div>
</div>

<div class="container mx-auto px-4 my-8">
    <div class="grid grid-cols-1 lg:grid-cols-4 gap-8">
        <!-- Sidebar -->
        <div class="hidden lg:block lg:col-span-1">
            <jsp:include page="sidebar.jsp"/>
        </div>

        <!-- Notice List -->
        <div class="lg:col-span-3">
            <div>
                <c:forEach items="${noticeList}" var="notice">
                    <div class="bg-white shadow-md rounded-lg p-4 mb-6">
                        <h4 class="text-lg font-semibold mb-2">${notice.headLine}</h4>
                        <div class="flex items-center justify-between">
                            <a href="notice/${notice.id}" target="_blank" class="btn btn-primary bg-blue-500 text-white py-2 px-4 rounded">
                                Download
                            </a>
                            <span class="text-gray-500 text-sm flex items-center">
                                <i class="fa fa-clock-o mr-2"></i>${notice.date}
                            </span>
                        </div>
                    </div>
                </c:forEach>

                <c:if test="${empty pageSize}">
                    <c:set var="pageSize" value="${0}"/>
                </c:if>

                <!-- Pagination -->
                <div class="flex justify-between mt-6">
                    <a href="/pagination/prev/getNotices?deptno=cse&pageSize=${pageSize - 1}" class="btn bg-gray-300 py-2 px-4 rounded hover:bg-gray-400">
                        <i class="fa fa-angle-double-left"></i> Previous
                    </a>
                    <a href="/pagination/next/getNotices?deptno=cse&pageSize=${pageSize + 1}" class="btn bg-gray-300 py-2 px-4 rounded hover:bg-gray-400">
                        Next <i class="fa fa-angle-double-right"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp"/>
