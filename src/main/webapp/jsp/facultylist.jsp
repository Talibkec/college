<%@ page import="com.college.service.UserService" %>
<%@ page import="com.college.core.model.FacultyDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.http.converter.json.GsonBuilderUtils" %>
<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron" style="
" padding-top: 24px; padding-bottom: 24px;
"></div>
<%!
    String[] branchList = new String[]{"Computer Sc. & Engineering", " Civil Engineering", "Mechanical Engineering", " Electronics & Electrical Engineering", "Applied Sc. & Humanities"};
    String[] sidebarList = new String[]{"csesidebar.jsp", "cesidebar.jsp", "mesidebar.jsp", "eeesidebar.jsp", "ashsidebar.jsp"};
%>
<%
    int depIndex;
    depIndex = Math.toIntExact(((List<FacultyDTO>) request.getAttribute("facultyList")).get(0).getDepartmentId() - 1L);
    String branchName = branchList[depIndex];
%>

<div class="container">
    <c:set var="sidebarname" value='<%="/jsp/department/sidebars/" + sidebarList[depIndex] %>'/>
    <jsp:include page="${sidebarname}"/>
    <div class="row">

        <div class="col-md-8">

            <div class="page-content"><h2>Faculty Members </h2></div>
            <hr/>


            <% int hod = 1; %>
            <c:forEach items="${facultyList}" var="faculty">

                <div class="panel panel-default">
                    <div class="panel-heading faculty-title"><b> ${faculty.facultyName} </b><% if (hod == 1) {
                        out.write(" ( HOD  " + branchName + " )");
                        hod = 0;
                    } %></div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-3 box-profile-image"><img alt=""
                                                                         height="125px"
                                                                         width="125px"
                                                                         src="${faculty.imageURL}"
                                                                         style="background-image: url(rps.JPG)"/>
                                <div class="visible-xs"><br/></div>
                            </div>

                            <div class="col-sm-6 box-profile-info"><strong>Department:</strong>
                                <%=branchName%>

                                <br/> <strong>Designation:</strong> Assistant Professor<br/> <strong>Phone
                                    Number:</strong>${faculty.facultyMobNo}<br/> <strong>Email
                                    Address:</strong> <a
                                        href="mailto:${faculty.facultyOfficialEmail}">${faculty.facultyOfficialEmail}</a>
                                <div class="visible-xs"><br/></div>
                            </div>
                            <div class="col-sm-3 box-profile-link"><a
                                    href="/facultyDetails?facultyId=${faculty.facultyId}"
                                    class="btn btn-default btn-lg"> <br
                                    class="hidden-xs"/> <i class="fa fa-link"></i> Visit<br class="hidden-xs"/>
                                Profile Page </a>
                                <br> <br>

                                <c:if test="${showDeleteBtn}">
                                    <a href="/deleteFaculty?deptno=${faculty.departmentId}&facultyId=${faculty.facultyId}">
                                        <button class="btn btn-danger mt-2 p-2"> Delete Faculty</button>
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
