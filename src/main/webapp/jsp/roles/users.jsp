<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %>
<jsp:include page="../admin/HeaderDashboard.jsp" /><kec:manageNav/>

<div class="container mx-auto p-6">
    <div class="bg-white p-8 rounded-lg shadow-md">
        <h2 class="text-2xl font-bold text-gray-800 mb-2">Assign roles</h2>
        <p class="text-gray-600 mb-4">
            Website roles (SubAdmin, Registrar, TPO, ...) decide which parts of the website a user may update; see
            <a class="text-blue-600" href="/auth/roles/areas">who may edit what</a>.
            Admin, Faculty, HOD and the store roles are not changed here (User Registration, Assign HOD Role).
        </p>
        <c:if test="${not empty saved}">
            <p class="mb-4 p-3 rounded bg-green-100 text-green-800" id="saved">Roles of <c:out value="${saved}"/> saved.</p>
        </c:if>
        <form method="get" action="/auth/roles" class="mb-4 flex gap-2">
            <input type="text" name="q" value="<c:out value='${q}'/>" placeholder="Search user name"
                   class="px-4 py-2 border rounded-md">
            <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-md">Search</button>
        </form>
        <table class="min-w-full border">
            <thead class="bg-gray-100">
            <tr><th class="text-left p-2 border">User name</th><th class="text-left p-2 border">Roles</th><th class="p-2 border"></th></tr>
            </thead>
            <tbody>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td class="p-2 border"><c:out value="${u.username}"/></td>
                    <td class="p-2 border"><c:forEach var="r" items="${u.roles}" varStatus="s"><c:out value="${r.name}"/><c:if test="${not s.last}">, </c:if></c:forEach></td>
                    <td class="p-2 border"><a class="text-blue-600" href="/auth/roles/user/${u.id}">Change</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../footer.jsp" />
