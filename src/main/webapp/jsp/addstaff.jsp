<jsp:include page="admin/HeaderDashboard.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-2 col-sm-8">
    <div class="panel panel-primary"><div class="panel-heading"> <i class="fa fa-sign-in"></i>&nbsp;Add Staff</div>
        <div class="panel-body"style="height:100%; ">
            <form  method="POST" enctype="multipart/form-data" id="addFacultyForm" data-action="/auth/uploadfile/addstaff">
                <label for ="facultyName" class="control-label col-sm-4">Enter Name</label>
                <input type="text" id="facultyName"  name="facultyName" class="control-label col-sm-4 ui-autocomplete-input" autocomplete="off"></br></br>

                <label for ="facultyOfficialEmail" class="control-label col-sm-4">Enter Official Email</label>
                <input type="email" id="facultyOfficialEmail"name="facultyOfficialEmail" class="control-label col-sm-4"></br></br>
                <label for ="facultyPersonalEmail" class="control-label col-sm-4">Enter Personal Email</label>
                <input type="email" id="facultyPersonalEmail" name="facultyPersonalEmail" class="control-label col-sm-4"></br></br>
                <label for ="facultyMobNo" class="control-label col-sm-4">Mobile No. </label>
                <input type="number" id="facultyMobNo" name="facultyMobNo" class="control-label col-sm-4"></br></br>


                <label class="control-label col-sm-4" >Choose Profile Picture</label>

                <div class="btn-default col-md-8" style="color:red">

                    <input type="file" name="slideImage" id="slideImage"/><br/></div>




                <div id="form_message"></div>
                <div id="msg" style="color: red;"></div>
                <input type="submit" value="Submit" id="btnSubmit" class="btn btn-success" style="margin-left:10%" />
                <div class="col-sm-4"></div>
        </div>
        </form>

    </div>

</div>
</div></div>
</div>
<jsp:include page ="footer.jsp"/>
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/addfaculty.js"></script>
