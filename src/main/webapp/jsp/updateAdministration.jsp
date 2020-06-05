<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-2 col-sm-8">
<div class="panel panel-primary"><div class="panel-heading"> <i class="fa fa-sign-in"></i>&nbsp;Update Administration Details</div>
<div class="panel-body"style="height:100%; ">
<form method="POST" enctype="multipart/form-data" id="updateAdministrationForm">
         <label for ="facultyOfficialEmail" class="control-label col-sm-4">Enter Official Email of Faculty</label>
         <input type="email" id="facultyEmail"name="facultyEmail"placeholder="Faculty Official Email"class="control-label col-sm-4"></br></br>
         <label for ="designation" class="control-label col-sm-4">Enter Designation</label>
         <input type="email" id="dutyAssigned"name="updateDutyAssigned"placeholder="Desgnation"class="control-label col-sm-4"></br></br>
         <label for ="DustyAssigned" class="control-label col-sm-4">Assign Admministration duty</label>
         <input type="email" id="roleValue"name="roleValue"placeholder="Administration Role"class="control-label col-sm-4"></br></br>
         <input type="submit" value="Submit" id="btnSubmit" class="btn btn-success" style="margin-left:10%" />
         <div class="col-sm-4"></div>
        </div>
</form>

</div>

</div>
</div></div></div>
<jsp:include page ="footer.jsp"/>
<script src="http://localhost/js/uploadAdministration.js"></script>

