<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron" style
"padding-top: 24px; padding-bottom: 24px;"></div>

<div class="container">

    <div class="row">
        <aside class="col-sm-3" role="complementary">
            <div class="region region-sidebar-second">
                <section id="block-menu-block-3" class="block block-menu-block clearfix">
                    <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                        <ul class="menu nav">

                        </ul>
                    </div>

                </section>
            </div>
        </aside>

        <div class="col-md-8">

            <div class="page-content"><h2>Faculty Members </h2></div>
            <hr/>


            <c:forEach items="${facultyList}" var="faculty">
                <div class="panel panel-default">
                    <div class="panel-heading faculty-title"> ${faculty.facultyName}</div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-3 box-profile-image"><img alt=""
                                                                         src="${faculty.imageURL}"
                                                                         style="background-image: url(rps.JPG)"/>
                                <div class="visible-xs"><br/></div>
                            </div>

                            <div class="col-sm-6 box-profile-info"><strong>Department:</strong>
                                <c:if test="${faculty.departmentId eq '1'}">
                                    Computer Sc. & Engineering
                                </c:if>
                                <c:if test="${faculty.departmentId eq '2'}">
                                    Civil Engineering
                                </c:if>
                                <c:if test="${faculty.departmentId eq '3'}">
                                    Mechanical Engineering
                                </c:if>
                                <c:if test="${faculty.departmentId eq '4'}">
                                    Electronics & Electrical Engineering
                                </c:if>
                                <c:if test="${faculty.departmentId eq '5'}">
                                    Applied Sc. & Humanities
                                </c:if>

                                <br/> <strong>Designation:</strong> Assistant Professor<br/> <strong>Phone
                                    Number:</strong>${faculty.facultyMobNo}<br/> <strong>Email
                                    Address:</strong> <a href="mailto:${faculty.facultyOfficialEmail}">${faculty.facultyOfficialEmail}</a>
                                <div class="visible-xs"><br/></div>
                            </div>
                            <div class="col-sm-3 box-profile-link"><a
                                    href="http://localhost/facultyDetails?facultyId=${faculty.facultyId}"
                                    class="btn btn-default btn-lg"> <br
                                    class="hidden-xs"/> <i class="fa fa-link"></i> Visit<br class="hidden-xs"/>
                                Profile Page </a></div>
                        </div>
                    </div>
                </div>
            </c:forEach>

</div>
        </div>
    </div>
    <jsp:include page="/jsp/footer.jsp"/>
