<jsp:include page="header.jsp" />
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<body>
<form autocomplete="off" id = "searchFacultyNameforHod">
<div class="container" style="margin:50px">
        <div class="row text-center"><strong> Assign - Revoke HOD Role </strong></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center"><strong>Search By FacultyName</strong></div>
        </div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center"><input type="text" name="facultyName" id = "facultyNameHod" /></div>
            <div class="col-md-3 text-center"><input type="submit" value="submit" id="searchHod" class="btn btn-success" /></div>
        </div>
	</div>
</form>
<form id="saveRole" autocomplete="off">
    <div id="assign" class="container" style="margin:50px;display:none;" >
    <div class="row" style="border:1px solid green;padding:10px">
         <div class="col-md-2 text-center"><strong>FacultyName</strong></div>
         <div class="col-md-2 text-center"><strong>Select Department</strong></div>
         <div class="col-md-2 text-center"><strong>Assign HODRole</strong></div>
         <div class="col-md-2 text-center"><strong>Revoke HODRole</strong></div>
    </div>
    <div class="row" style="border:1px solid green;padding:10px display:block">
          <div class="col-md-2 text-center" value="facultyname" id="faculty"><span id="facultySpan"></span></div>
           <div class="col-md-2 text-center">
            <select name="department" class="departments">
                <c:forEach items="${departments}" var="department">
                    <option  value="${department.departmentId}" >${department.departmentName}</option>
                </c:forEach>
            </select>
            </div>
           <div class="col-md-2 text-center">
               <button id="submitHod" class="btn btn-success" type = "submit">Assign</button>
           </div>

            <div class="col-md-2 text-center">
                <button id="deleteHodRole" class="btn btn-success" type="button">Revoke</button>
                </a>
            </div>
          </div>
    </div>
    <input type="hidden" id="facultyId" name="facultyId" value="${faculty.facultyId}" />
</form>
<h4 id="success" style="margin:50px"></h4>
</body>
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/hodincharge.js"></script>

</html>
<jsp:include page="footer.jsp" />

