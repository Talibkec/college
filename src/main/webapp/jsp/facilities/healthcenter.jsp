<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>


<div class="jumbotron flex justify-center w-full">
    <div class="">
        <div class="row">
            <div class="col-md-10 col-sm-8"><h3 class="text-2xl">Health Centre</h3></div>
</div>
    </div>
</div>
<div class="container">
    <div class="md:flex justify-center">
        <aside class="col-sm-3" role="complementary"  style="">
                                                             <div class="region region-sidebar-second">
                                                   <section id="block-menu-block-3" class="block block-menu-block clearfix">

                                                           <h2 class="block-title">Facilities </h2>

                                                     <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                                    <ul class="menu nav"><kec:links list="facilities_sidebar" var="sidebarLinks"/><c:choose><c:when test="${not empty sidebarLinks}"><c:forEach items="${sidebarLinks}" var="l" varStatus="s">
                                                   <li class="${s.first ? 'first leaf' : 'leaf'}"><a href="${kec:text(l.url)}" class="sf-depth-2${s.first ? ' active' : ''}"${l.openNewTab ? ' target="_blank"' : ''}>${kec:text(l.label)}</a></li></c:forEach>
                                                      </c:when><c:otherwise>
                                                   <li class="first leaf  menu-mlid-1154"><a href="/facilities/bank" class="sf-depth-2 active">Bank</a></li>
                                                   <li class="leaf menu-mlid-1121"><a href="/facilities/csecenter" class="sf-depth-2">Computer Center</a></li>
                                                   <li class="leaf menu-mlid-1156"><a href="/facilities/centrallibrary" class="sf-depth-2">Central Library</a></li>
                                                   <li class="leaf menu-mlid-1294"><a href="/facilities/hostels" class="sf-depth-2">Hostels</a></li>
                                                   <li class="leaf menu-mlid-1467"><a href="/facilities/sportsfacilities" class="sf-depth-2">Sports Facilities</a></li>
                                                   <li class="leaf menu-mlid-1467"><a href="/facilities/clubs" class="sf-depth-2">Clubs</a></li>
                                                   <li class="leaf menu-mlid-1467"><a href="/facilities/healthcenter" class="sf-depth-2">Health Center</a></li>
                                                   <li class="leaf menu-mlid-1467"><a href="/facilities/wifi" class="sf-depth-2">Wi Fi</a></li>
                                                   <li class="leaf menu-mlid-1467"><a href="/facilities/medicalfacilities" class="sf-depth-2">Medical Facilities</a></li>
                                                   <li class="leaf menu-mlid-1467"><a href="/facilities/startupcell" class="sf-depth-2">Startup Cell</a></li>
                                                   <li class="leaf menu-mlid-1467"><a href="/facilities/gymnasium" class="sf-depth-2">Gymnasium</a></li>

                                                      </c:otherwise></c:choose></ul></div>

                                                   </section>
                                                     </div>
                                                         </aside>
        <div class="col-md-8 p-3 "  style="">
            <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;"><kec:section key="facilities.healthcenter/main" title="Health Centre: text"><p>Updated Soon&#8230;</p></kec:section>
            </div>
        </div>
    </div>
</div>

<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>
