<%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>Course File</h3></div>
            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
            <jsp:include page="fpsidebar.jsp"/>

            <div class="col-md-9 cf-page">
                <div class="cf-intro"><kec:section key="department.fp.course/main" title="Food Processing Course File: text"><p>The course file is the complete teaching record of a subject: syllabus, course outcomes, lesson plan, lecture notes, assignments, question papers and results. The course files of the Department of Food Processing are listed below, semester by semester. Search for a subject by its name, code or faculty member.</p></kec:section></div>
                <jsp:include page="/jsp/department/courseFiles.jsp"/>
            </div>
        </div>
    </div>
<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>
