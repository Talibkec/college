<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header1.jsp"/>

<body>

<div class="container" style="margin:50px">
        <div class="row text-center"><strong> Faculty Details</strong></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-2 text-center"><strong>Faculty Name</strong></div>

        </div>
            <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-2 text-center">${faculty.facultyName}</div>
            </div>
	</div>

<form:form autocomplete="on"  method="post">
<div class="container" style="margin:50px">
<div class="row" style="border:1px solid green;padding:10px">
${faculty.facultyId}
${faculty.facultyName}
          <div class="col-md-2 text-center" value="${faculty.facultyId}">${faculty.facultyName}</div>
          <div class="col-md-2 text-center"><select name="role">
           <c:forEach items="${roles}" var="role">
             <option value="${role.id}">${role.name}</option>
           </c:forEach>
           </select></div>
           <div class="col-lg-1 text-center"><input type="submit" class="btn btn-primary" /></div>
          </div>
</div>
<input type="hidden" id="facultyId" name="facultyId" value="NotAvailable" />

</form:form>
<script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="http://keck.ac.in/js/jquery-ui.js"></script>
<script src="http://keck.ac.in/js/store/storemanager.js"></script>
</body>
</html>