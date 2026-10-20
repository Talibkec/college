<%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>

<div class="jumbotron flex justify-center">
        <div class="">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3 class="text-3xl">Students of Mechanical Engineering</h3></div>

            </div>
        </div>
    </div>
    <div class="container">

        <div class="md:flex justify-center">
            <jsp:include page="sidebar.jsp"/>

            <div class="col-md-8 p-3" style=""><kec:section key="department.mechandsmartmanufacturing.students/main" title="Mechanical & Smart Manufacturing Students: text"><strong>Updated Soon&#8230</strong></kec:section>
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;"></div>
            </div>
        </div>
    </div>
<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>
