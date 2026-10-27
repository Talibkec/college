<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %>
<jsp:include page="../admin/HeaderDashboard.jsp" /><kec:manageNav/>

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
            <p class="text-sm text-gray-500">Admin, Faculty and the store roles stay as they are.</p>

            <div class="border-t pt-4 mt-4" id="hod">
                <h3 class="text-lg font-semibold text-gray-800 mb-2">HOD</h3>
                <input type="hidden" name="hodSection" value="1">
                <label class="block font-medium">
                    <input type="checkbox" name="hod" value="HOD" <c:if test="${held.contains('HOD')}">checked</c:if>>
                    HOD
                </label>
                <p class="text-sm text-gray-500 mb-2">HOD of the branch(es) below. Ticking a branch gives the HOD role; it
                    replaces the current HOD of that branch. An HOD always covers the own faculty department as well.</p>
                <c:forEach var="site" items="${departmentSites}">
                    <label class="block">
                        <input type="checkbox" name="hodSite" value="<c:out value='${site.siteCode}'/>" <c:if test="${site.hodUsername eq user.username}">checked</c:if>>
                        <c:out value="${site.fullName}"/> (<c:out value="${site.shortName}"/>, <c:out value="${site.menuGroup}"/>)
                        <span class="text-sm text-gray-500">
                            &ndash; HOD: <c:out value="${empty site.hodUsername ? 'none' : site.hodUsername}"/>
                            <c:if test="${not empty ownDeptId and site.deptId eq ownDeptId}">&middot; own department</c:if>
                        </span>
                    </label>
                </c:forEach>
            </div>
            <div class="flex gap-4 pt-2">
                <button type="submit" class="px-4 py-2 bg-blue-500 text-white font-semibold rounded-md">Save</button>
                <a href="/auth/roles" class="px-4 py-2 border rounded-md">Cancel</a>
            </div>
        </form>
    </div>
</div>

<jsp:include page="../footer.jsp" />
