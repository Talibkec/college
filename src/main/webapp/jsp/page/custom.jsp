<%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- New pages /page/<name> (CustomPageController): title and sidebar from the main menu, content = page blocks. --%>

<div class="jumbotron py-6"></div>

<div class="container mx-auto px-4">
    <div class="md:flex justify-between space-y-4 md:space-y-0">
        <c:if test="${not empty sidebarLinks}">
        <aside class="md:w-1/4 w-full p-4 bg-white rounded-lg shadow-lg" id="page-sidebar">
            <div class="region region-sidebar-second">
                <section class="block block-menu-block clearfix">
                    <h2 class="block-title text-xl font-bold mb-4">${kec:text(sidebarTitle)}</h2>
                    <ul class="menu nav space-y-2"><c:forEach items="${sidebarLinks}" var="l" varStatus="s">
                        <li class="${s.first ? 'first leaf' : 'leaf'}"><a href="${kec:text(l.url)}" class="sf-depth-2 text-blue-500 hover:underline${l.url eq pageUrl ? ' active font-bold' : ''}"${l.openNewTab ? ' target="_blank"' : ''}>${kec:text(l.label)}</a></li></c:forEach>
                    </ul>
                </section>
            </div>
        </aside>
        </c:if>

        <div class="${empty sidebarLinks ? 'w-full' : 'md:w-3/4 w-full'} p-4">
            <div class="page-content bg-white p-6 rounded-lg shadow-lg">
                <h2 class="text-2xl font-bold mb-4" id="page-title">${kec:text(pageTitle)}</h2>
                <hr class="mb-4">
                <kec:blocks/>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp"/>
