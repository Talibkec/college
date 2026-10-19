<%@ taglib prefix="c" uri="jakarta.tags.core" %><nav class="bg-white border-b px-4 py-2" id="manage-nav">
    <div class="container mx-auto flex flex-wrap gap-2">
        <c:forEach var="n" items="${manageNav}">
            <a href="${n.url}" class="px-3 py-1 rounded-md text-sm border ${n.active ? 'bg-blue-600 text-white border-blue-600' : 'text-blue-700 border-blue-200 hover:bg-blue-50'}"><i class="fa ${n.icon} mr-1"></i>${n.label}</a>
        </c:forEach>
    </div>
</nav>
