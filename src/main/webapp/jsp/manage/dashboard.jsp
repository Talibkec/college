<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/jsp/header.jsp"/>
<div class="py-6">
    <div class="flex items-center justify-between bg-gray-100 py-4 px-6">
        <div class="flex space-x-4">
            <a href="/" class="text-blue-600 flex items-center"><i class="fa fa-home mr-2"></i> Home</a>
        </div>
        <div class="flex-grow text-center">
            <h3 class="text-2xl font-semibold">Manage website content</h3>
        </div>
    </div>

    <div class="container mx-auto mt-8 px-4">
        <p class="mb-6 text-gray-700">
            You can update the following parts of the website.
            <strong>Edit</strong>: add, change and delete entries.
            <strong>Add only</strong>: add entries and delete your own.
            The edit screens are added area by area; an area without a link has no screen yet.
        </p>

        <c:if test="${isAdmin}">
            <div class="mb-6 flex flex-wrap gap-4">
                <a href="/auth/roles" class="px-4 py-2 bg-blue-600 text-white rounded-md">Assign roles to users</a>
                <a href="/auth/roles/areas" class="px-4 py-2 bg-blue-600 text-white rounded-md">Who may edit what</a>
                <a href="/auth/uploadDashboard" class="px-4 py-2 bg-gray-600 text-white rounded-md">Admin dashboard</a>
            </div>
        </c:if>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6" id="content-areas">
            <c:forEach var="a" items="${areas}">
                <div class="bg-white p-6 rounded-lg shadow-md" data-area="<c:out value='${a.area.areaCode}'/>">
                    <h4 class="text-xl font-semibold mb-2"><c:out value="${a.area.label}"/></h4>
                    <p class="text-gray-600 mb-3"><c:out value="${a.area.description}"/></p>
                    <c:choose>
                        <c:when test="${a.edit}"><span class="inline-block px-2 py-1 text-sm rounded bg-green-100 text-green-800">Edit</span></c:when>
                        <c:otherwise><span class="inline-block px-2 py-1 text-sm rounded bg-yellow-100 text-yellow-800">Add only</span></c:otherwise>
                    </c:choose>
                    <c:if test="${a.scope eq 'own_department'}"><span class="inline-block px-2 py-1 text-sm rounded bg-gray-100">your department</span></c:if>
                    <c:if test="${a.scope eq 'own_record'}"><span class="inline-block px-2 py-1 text-sm rounded bg-gray-100">your own entry</span></c:if>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>
