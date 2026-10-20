<%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>
<div class="jumbotron flex justify-center">
        <div class="">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3 class="text-2xl">Weekly Test Result</h3></div>

            </div>
        </div>
    </div>
    <div class="container">

        <div class="md:flex justify-center">
            <jsp:include page="sidebar.jsp"/>

            
            <div class="p-3" style="min-width:600px">
                <kec:section key="department.mechandsmartmanufacturing.weekly/main" title="Mechanical & Smart Manufacturing Weekly Test Result: text"><p><strong>Updated Soon&#8230</strong></p></kec:section>
            </div>
        </div>
    </div>


<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>
