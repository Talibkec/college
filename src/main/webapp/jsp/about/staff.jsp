<%@ page import="com.college.service.UserService" %>
<%@ page import="com.college.core.model.StaffDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.http.converter.json.GsonBuilderUtils" %>
<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;"></div>


<div class="container">


    <aside class="col-sm-3" role="complementary">
        <div class="region region-sidebar-second">
            <section id="block-menu-block-3" class="block block-menu-block clearfix">

                <h2 class="block-title">About KEC</h2>

                <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                    <ul class="menu nav">
                        <li class="first leaf  menu-mlid-1154"><a href="/about/principal" class="sf-depth-2 active">Principal's Message</a></li>
                        <li class="leaf menu-mlid-1121"><a href="/about/vision" class="sf-depth-2">Vision & Mission</a></li>
                        <li class="leaf menu-mlid-1156"><a href="/about/allotment" class="sf-depth-2">Allotment &Surrender Report</a></li>
                        <li class="leaf menu-mlid-1294"><a href="/about/affiliation" class="sf-depth-2">Affiliation</a></li>
                        <li class="leaf menu-mlid-1467"><a href="/about/administration" class="sf-depth-2">Administration</a></li>
                        <li class="leaf menu-mlid-1467"><a href="/about/staff" class="sf-depth-2">Support Staff</a></li>
                        <li class="leaf menu-mlid-1467"><a href="/about/history" class="sf-depth-2">History</a></li>

                    </ul></div>

            </section>
        </div>
    </aside>


    <div class="row" style="display:block">

        <div class="col-md-8">

            <div class="page-content"><h2>Support  Staff </h2></div>
            <hr/>
            <c:if test="${userType == 2}">
                <a href="auth/uploadfile/addStaff">
                    <button class="btn btn-primary mt-2 p-2"> Add Staff</button>
                </a>
            </c:if>



            <c:forEach items="${staffList}" var="staff">

                <div class="panel panel-default" style="width:100%">
                    <div class="panel-heading faculty-title"><b> ${staff.staffName} </b></div>
                    <div class="panel-body">
                        <div class="row" style="display:block ">
                            <div class="col-sm-3 box-profile-image"><img alt=""
                                                                         height="125px"
                                                                         width="125px"
                                                                         src="${staff.imageURL}"
                                                                         />
                                <div class="visible-xs"><br/></div>
                            </div>
                          <div class="col-sm-6">
                              <strong>Phone Number:</strong>${staff.staffMobNo}<br/>
                              <strong>Email Address:</strong> <a href="mailto:${staff.staffOfficialEmail}">${staff.staffOfficialEmail}</a>
                                <div class="visible-xs"><br/></div>
                          </div>
                            <div class="col-sm-3 box-profile-link"><a
                                    href="/staffDetails?staffId=${staff.staffId}" class="btn btn-default btn-lg"> <br class="hidden-xs"/> <i class="fa fa-link"></i> Visit<br class="hidden-xs"/>
                                Profile Page </a>
                                <br> <br>
                                <c:if test="${userType > 0}">
                                    <a href="/deleteStaff?staffId=${staff.staffId}">
                                        <button class="btn btn-danger mt-2 p-2"> Delete Staff</button>
                                    </a>
                                </c:if>
                            </div>
                          </div>
                        </div>
                    </div>

            </c:forEach>

        </div>
    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>
