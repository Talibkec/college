<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-2 col-sm-8">
<div class="panel panel-primary"><div class="panel-heading"> <i class="fa fa-sign-in"></i>&nbsp;Update Administration Details</div>
<div class="panel-body"style="height:100%; ">
<form method="POST" enctype="multipart/form-data" id="updateAdministrationForm">
         <label for ="facultyOfficialEmail" class="control-label col-sm-4">Enter Official Email of Faculty</label>
         <input type="email" id="facultyEmail"name="facultyEmail"placeholder="Faculty Official Email"class="control-label col-sm-4"></br></br>
         <label for ="role" class="control-label col-sm-4">Enter Role</label>
         <input type="role" id="role"name="role"placeholder="Role Value"class="control-label col-sm-4"></br></br>

         <label for ="designation" class="control-label col-sm-4">Enter Designation</label>
         <input type="text" id="designation"name="designation"placeholder="Designation"class="control-label col-sm-4"></br></br>

         <label for ="dutyAssigned" class="control-label col-sm-4">Assign Administration duty</label>
         <input type="dutyAssigned" id="dutyAssigned"name="updateDutyAssigned"placeholder="Administration Duty"class="control-label col-sm-4"></br></br>
         <input type="submit" value="Submit" id="btnSubmit" class="btn btn-success" style="margin-left:10%" />
         <div class="col-sm-4"></div>
        </div>
</form>

</div>

</div>
</div></div></div>
<jsp:include page ="footer.jsp"/>
<script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="http://localhost/js/jquery-ui.js"></script>
<script src="http://localhost/js/uploadAdministration.js"></script>



