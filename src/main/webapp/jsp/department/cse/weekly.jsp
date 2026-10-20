<%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>Weekly Test Result</h3></div>

            </div>
        </div>
    </div>
    <div class="row">

        <jsp:include page="sidebar.jsp"/>

            <div class="col-md-8"><kec:section key="department.cse.weekly/main" title="CSE Weekly Test Result: text"><p><strong>Updated Soon &#8230</strong></p></kec:section></div>
        </div>
    </div>
<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>
