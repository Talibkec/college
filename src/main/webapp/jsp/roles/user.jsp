<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../admin/HeaderDashboard.jsp" />

<div class="container mx-auto p-6">
    <div class="max-w-xl mx-auto bg-white p-8 rounded-lg shadow-md">
        <h2 class="text-2xl font-bold text-gray-800 mb-4">Roles of <c:out value="${user.username}"/></h2>
        <p class="text-gray-600 mb-4">Current roles:
            <c:forEach var="r" items="${user.roles}" varStatus="s"><c:out value="${r.name}"/><c:if test="${not s.last}">, </c:if></c:forEach>
        </p>
        <form method="post" action="/auth/roles/user/${user.id}" class="space-y-2">
            <c:forEach var="role" items="${assignableRoles}">
                <label class="block">
                    <input type="checkbox" name="role" value="<c:out value='${role.name}'/>" <c:if test="${held.contains(role.name)}">checked</c:if>>
                    <c:out value="${role.name}"/>
                </label>
            </c:forEach>
            <p class="text-sm text-gray-500">Admin, Faculty, HOD and the store roles stay as they are.</p>
            <div class="flex gap-4 pt-2">
                <button type="submit" class="px-4 py-2 bg-blue-500 text-white font-semibold rounded-md">Save</button>
                <a href="/auth/roles" class="px-4 py-2 border rounded-md">Cancel</a>
            </div>
        </form>
    </div>
</div>

<jsp:include page="../footer.jsp" />
