<%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>Programs and Activities</h3></div>
                            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">

                <jsp:include page="sidebar.jsp"/>          <div class="col-md-8">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;"><kec:section key="department.eee.programs/main" title="EEE Programs/Activities: text"><p>Updated Soon&#8230;</p></kec:section>
                </div>
            </div>
        </div>
    </div>
<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>
