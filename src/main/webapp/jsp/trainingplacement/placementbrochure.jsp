<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp" />

<div class="bg-gray-200 py-6">
    <div class="container mx-auto">
        <div class="text-center">
            <h3 class="text-3xl font-semibold">Placement Brochure</h3>
        </div>
    </div>
</div>

<div class="container mx-auto px-4 py-8">
    <div class="md:flex justify-center">
        <!-- Sidebar (Unchanged) -->
        <aside class="col-sm-3" role="complementary">
            <div class="region region-sidebar-second">
                <section id="block-menu-block-3" class="block block-menu-block clearfix">
                    <h2 class="block-title">Training & Placement</h2>
                    <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                        <ul class="menu nav"><kec:links list="tp_sidebar" var="sidebarLinks"/><c:choose><c:when test="${not empty sidebarLinks}"><c:forEach items="${sidebarLinks}" var="l" varStatus="s">
                            <li class="${s.first ? 'first leaf' : 'leaf'}"><a href="${kec:text(l.url)}" class="sf-depth-2${s.first ? ' active' : ''}"${l.openNewTab ? ' target="_blank"' : ''}>${kec:text(l.label)}</a></li></c:forEach>
                        </c:when><c:otherwise>
                            <li class="first leaf menu-mlid-1154"><a href="/trainingplacement/aboutplacement" class="sf-depth-2 active">About Placement</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/rulesoftrainingplacement" class="sf-depth-2">Rules of Training & Placement</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/tipsforresume" class="sf-depth-2">Tips for Resume</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/placedstudent" class="sf-depth-2">Placed Students</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/placementbrochure" class="sf-depth-2">Placement Brochure</a></li>
                        </c:otherwise></c:choose></ul>
                    </div>
                </section>
            </div>
        </aside>

        <!-- Main Content -->
        <div class="col-md-8 p-3">
            <div class="bg-white shadow-lg rounded-lg p-6">
                <div class="prose max-w-full">
                    <kec:section key="trainingplacement.placementbrochure/main" title="Placement Brochure: document"><object data="/jsp/trainingplacement/brochure.pdf"
                            type="application/pdf"
                            width="100%"
                            height="1000"
                            class="w-full">
                        <embed src="/jsp/trainingplacement/brochure.pdf"
                               type="application/pdf">
                    </object></kec:section>
                </div>
            </div>
        </div>
    </div>
</div>

<kec:blocks/><jsp:include page="/jsp/footer.jsp" />
