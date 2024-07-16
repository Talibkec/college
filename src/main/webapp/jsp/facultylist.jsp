<%@ page import="com.college.service.UserService" %>
<%@ page import="com.college.core.model.FacultyDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.http.converter.json.GsonBuilderUtils" %>
<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;"></div>
<%!
    String[] branchList = new String[]{"Computer Sc. & Engineering", " Civil Engineering", "Mechanical Engineering", " Electronics & Electrical Engineering", "Applied Sc. & Humanities", "Electronics Engineering (VLSI Design and Technology)" ,  "Food Processing"};
    String[] sidebarList = new String[]{"csesidebar.jsp", "cesidebar.jsp", "mesidebar.jsp", "eeesidebar.jsp", "ashsidebar.jsp", "ecesidebar.jsp" , "fpsidebar.jsp"};
%>
<%

        int depIndex;
        depIndex = Math.toIntExact(((List<FacultyDTO>) request.getAttribute("facultyList")).get(0).getDepartmentId() - 1L);
        String branchName = branchList[depIndex];

%>

<style>
    .page-content {
        padding: 20px;
        padding-left: 60px;
        text-decoration: bold;
        background-color: #eeeeee;
        font-size: x-large;
    }
</style>
<div class="page-content"><h2>Faculty Members </h2></div>

<div class="container">
    
    <div class="md:flex justify-center" >
       
        <div class="col-md-8 p-3"  style="min-width: 500px;">
           
            <hr/>
            <c:if test="${userType == 2}">
                <a href="auth/uploadfile/addfaculty">
                    <button class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"> Add Faculty</button>
                </a>
            </c:if>
            <c:if test="${userType == 1}">
                <a href="hod/addfaculty">
                    <button class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"> Add Faculty</button>
                </a>
            </c:if>
            <% int hod = 1; %>
            <c:forEach items="${facultyList}" var="faculty">
                <div class="panel panel-default w-full border border-1 rounded-3xl m-2" >
                    <div class="panel-heading faculty-title p-1 text-white" style="background:#285690"><b> ${faculty.facultyName} </b><% if (hod == 1) {
                        out.write(" ( HOD  " + branchName + " )");
                        hod = 0;
                    } %></div>
                    <div class="panel-body">
                        <div class="flex justify-between">
                            <div class="p-2 box-profile-image"><img alt=""
                                                                         height="125px"
                                                                         width="125px"
                                                                         src="${faculty.imageURL}"
                                                                         style="background-image: url(rps.JPG)"/>
                                <div class="visible-xs"><br/></div>
                            </div>
                            <div class="p-2"><strong>Department:</strong>
                                <%=branchName%>

                                <br/> <strong>Designation:</strong> Assistant Professor<br/> <strong>Phone
                                    Number:</strong>${faculty.facultyMobNo}<br/> <strong>Email
                                    Address:</strong> <a
                                        href="mailto:${faculty.facultyOfficialEmail}">${faculty.facultyOfficialEmail}</a>
                                <div class="visible-xs"><br/></div>
                            </div>
                            <div class="p-1">
                                

                                <a
                                    href="/facultyDetails?facultyId=${faculty.facultyId}"
                                    class="btn btn-accent"> <br
                                    class="hidden-xs"/> 
                                   
                           


                            <button type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">  <i class="fa fa-link"></i>   Profile</button>

                        </a>
                        <br>
                                <c:if test="${userType > 0}">
                                    <a href="/deleteFaculty?deptno=${faculty.departmentId}&facultyId=${faculty.facultyId}">
                                        <button class="btn btn-danger mt-2 p-2"> Delete</button>
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
