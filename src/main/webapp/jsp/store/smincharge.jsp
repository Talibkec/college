<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="header1.jsp"/>
<body>
<form autocomplete="off" id = "searchFacultyName">
<div class="container" style="margin:50px">
        <div class="row text-center"><strong> Assign - Revoke SM Role </strong></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center"><strong>Search By FacultyName</strong></div>
        </div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center"><input type="text" name="facultyName" id = "facultyName" /></div>
            <div class="col-md-3 text-center"><input type="submit" value="submit" id="search" class="btn btn-success" /></div>
        </div>
	</div>
</form>
<form action="/store/saveRole" id="saveRole" autocomplete="off" method="POST">
    <div id="assign" class="container" style="margin:50px;display:none;" >
    <div class="row" style="border:1px solid green;padding:10px">
         <div class="col-md-2 text-center"><strong>FacultyName</strong></div>
         <div class="col-md-2 text-center"><strong>Assign SMRole</strong></div>
         <div class="col-md-2 text-center"><strong>Revoke SMRole</strong></div>
    </div>
    <div class="row" style="border:1px solid green;padding:10px display:block">
          <div class="col-md-2 text-center" value="facultyname" id="faculty"><span id="facultySpan"></span></div>
           <div class="col-md-2 text-center">
               <button id="submit" class="btn btn-success" type = "submit">Assign</button>
           </div>
            <c:forEach items="${roles}" var="role">
            </c:forEach>
            <div class="col-md-2 text-center">
                <button id="deleteUserRole" class="btn btn-success" type="button">Revoke</button>
                </a>
            </div>
          </div>
    </div>
    <input type="hidden" id="role" name="roleId" value="${role.id}" />
    <input type="hidden" id="facultyId" name="facultyId" value="${faculty.facultyId}" />
</form>
<h4 id="success" style="margin:50px"></h4>
</body>
<script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/store/smincharge.js"></script>
</html>
