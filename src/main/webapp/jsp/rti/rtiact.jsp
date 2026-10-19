<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>

<div class="jumbotron flex justify-center">
    <div class="">
        <div class="row">
            <div class="col-md-10 col-sm-8" ><h3 class="text-2xl">RTI act 2005</h3></div>
        </div>
    </div>
</div>
<div class="container">
    <div class="md:flex justify-center">
         <aside class="col-sm-3" role="complementary"  style="">
                        <div class="region region-sidebar-second">
                          <section id="block-menu-block-3" class="block block-menu-block clearfix">
                             <h2 class="block-title">Right To Information</h2>
                            <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                                <ul class="menu nav"><kec:links list="rti_sidebar" var="sidebarLinks"/><c:choose><c:when test="${not empty sidebarLinks}"><c:forEach items="${sidebarLinks}" var="l" varStatus="s">
                                    <li class="${s.first ? 'first leaf' : 'leaf'}"><a href="${kec:text(l.url)}" class="sf-depth-2${s.first ? ' active' : ''}"${l.openNewTab ? ' target="_blank"' : ''}>${kec:text(l.label)}</a></li></c:forEach>
                                 </c:when><c:otherwise>
                                    <li class="first leaf  menu-mlid-1154"><a href="/rti/rtiapplication" class="sf-depth-2 active">RTI Application Form</a></li>
                                      <li class="leaf menu-mlid-1121"><a href="/rti/rtistatus" class="sf-depth-2">RTI Status</a></li>
                                      <li class="leaf menu-mlid-1121"><a href="/rti/publicinformationcell" class="sf-depth-2">Public Information Cell</a></li>
                                       <li class="leaf menu-mlid-1121"><a href="/rti/rtiact" class="sf-depth-2">RTI Act</a></li>
                                 </c:otherwise></c:choose></ul>
                            </div>
                          </section>
                          </div>
                       </aside>
        <div class="col-md-8 p-3"  style="">
            <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">

                <div>
                    <object data="/wp-content/uploads/2018/rti-act-2.pdf" type="application/pdf" width="700" height="1000">
                        <embed src="/wp-content/uploads/2018/rti-act-2.pdf" type="application/pdf">
                    </object>
                </div>
            </div>
            <i class="fa fa-download"></i>
            <a href="/wp-content/uploads/2018/rti-act-2.pdf">
            <button type="button" class="btn btn-success" data-dismiss="modal">Download the RTI Act 2005</button></a>
        </div>
    </div>
</div>

<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>
