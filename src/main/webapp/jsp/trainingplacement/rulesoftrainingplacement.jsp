<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp" />


    <div class="jumbotron justify-center flex">
        <div class="">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3 class="text-2xl">Rules of Training &#038; Placement</h3></div>

            </div>
        </div>
    </div>
    </div>
    <div class="container">
        <div class="md:flex justify-center">
            <aside class="col-sm-3" role="complementary"  style="">
                                    <div class="region region-sidebar-second">
                                      <section id="block-menu-block-3" class="block block-menu-block clearfix">
                                         <h2 class="block-title">Training & Placement </h2>
                                        <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                            <ul class="menu nav"><kec:links list="tp_sidebar" var="sidebarLinks"/><c:choose><c:when test="${not empty sidebarLinks}"><c:forEach items="${sidebarLinks}" var="l" varStatus="s">
                                                <li class="${s.first ? 'first leaf' : 'leaf'}"><a href="${kec:text(l.url)}" class="sf-depth-2${s.first ? ' active' : ''}"${l.openNewTab ? ' target="_blank"' : ''}>${kec:text(l.label)}</a></li></c:forEach>
                                             </c:when><c:otherwise>
                                                <li class="first leaf  menu-mlid-1154"><a href="/trainingplacement/aboutplacement" class="sf-depth-2 active">About Placement</a></li>
                                                  <li class="leaf menu-mlid-1121"><a href="/trainingplacement/rulesoftrainingplacement" class="sf-depth-2">Rules of Training & Placement</a></li>
                                                  <li class="leaf menu-mlid-1121"><a href="/trainingplacement/tipsforresume" class="sf-depth-2">Tips for Resume</a></li>

                                                <li class="leaf menu-mlid-1121"><a href="/trainingplacement/placedstudent" class="sf-depth-2">Placed Students</a></li>
                                                   <li class="leaf menu-mlid-1121"><a href="/trainingplacement/placementbrochure" class="sf-depth-2">Placement Brochure</a></li>
                                             </c:otherwise></c:choose></ul>
                                        </div>
                                      </section>
                                      </div>
                                   </aside>

            <div class="col-md-8 p-3"  style="">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                    <p>
                    <div>
                        <object data="/jsp/trainingplacement/sample.pdf" type="application/pdf" width="700" height="1000">
                            <embed src="/jsp/trainingplacement/sample.pdf" type="application/pdf">
                        </object>
                    </div>
                    </p>
                </div>
            </div>
        </div>
    </div>

<jsp:include page="/jsp/footer.jsp" />
