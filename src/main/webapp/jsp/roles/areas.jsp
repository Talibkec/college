<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="../admin/HeaderDashboard.jsp" />

<div class="container mx-auto p-6">
    <div class="bg-white p-8 rounded-lg shadow-md overflow-x-auto">
        <h2 class="text-2xl font-bold text-gray-800 mb-2">Who may edit what</h2>
        <p class="text-gray-600 mb-4">
            E = edit (add, change, delete), A = add only (and delete own entries), &ndash; = no access.
            Admin may always edit everything. HOD and Faculty only get their own department on department areas.
            "System" is Admin only.
        </p>
        <c:if test="${saved}"><p class="mb-4 p-3 rounded bg-green-100 text-green-800" id="saved">Saved.</p></c:if>
        <form method="post" action="/auth/roles/areas">
            <table class="min-w-full border text-sm">
                <thead class="bg-gray-100">
                <tr>
                    <th class="text-left p-2 border">Area</th>
                    <c:forEach var="role" items="${roles}"><th class="p-2 border"><c:out value="${role}"/></th></c:forEach>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="area" items="${areas}">
                    <tr>
                        <td class="p-2 border" title="<c:out value='${area.description}'/>"><c:out value="${area.label}"/></td>
                        <c:forEach var="role" items="${roles}">
                            <td class="p-1 border text-center">
                                <c:choose>
                                    <c:when test="${area.areaCode eq 'system'}">&ndash;</c:when>
                                    <c:otherwise>
                                        <c:set var="cell" value="${matrix[area.areaCode.concat('|').concat(role)]}"/>
                                        <select name="cell:<c:out value='${area.areaCode}'/>|<c:out value='${role}'/>">
                                            <option value="" <c:if test="${empty cell}">selected</c:if>>&ndash;</option>
                                            <option value="A" <c:if test="${cell.access eq 'A'}">selected</c:if>>A</option>
                                            <option value="E" <c:if test="${cell.access eq 'E'}">selected</c:if>>E</option>
                                        </select>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="mt-4 px-4 py-2 bg-blue-500 text-white font-semibold rounded-md">Save</button>
        </form>
    </div>
</div>

<jsp:include page="../footer.jsp" />
