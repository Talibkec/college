<%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>Labs & Infrastructure</h3></div>

        </div>
    </div>
    <div class="container">

        <div class="row">
            <jsp:include page="sidebar.jsp"/>
            <div class="col-md-8">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;"><kec:section key="department.ash.labs/main" title="ASH Labs: laboratory list"><p><span
                        style="color: #0000ff;"><strong>Departmental Laboratories</strong></span></p>
                    <ol>

                        <li>Applied Physics</li>
                        <li>Applied Chemistry</li>
                        <li>Environmental Science</li>

                    </ol></kec:section>
                </div>
            </div>
        </div>
    </div>
<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>
