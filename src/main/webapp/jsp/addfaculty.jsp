<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    //group add limit
    var maxGroup = 10;

    //add more fields group
    $(".addMore").click(function(){
        if($('body').find('.fieldGroup').length < maxGroup){
            var fieldHTML = '<div class="form-group fieldGroup">'+$(".fieldGroupCopy").html()+'</div>';
            $('body').find('.fieldGroup:last').after(fieldHTML);
        }else{
            alert('Maximum '+maxGroup+' groups are allowed.');
        }
    });

    //remove fields group
    $("body").on("click",".remove",function(){
        $(this).parents(".fieldGroup").remove();
    });
});
</script>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
<div style="margin-left:50px" ><h3>Edit Profile Details</h3></div>
</div></div><div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-2 col-sm-8">
<div class="panel panel-primary"><div class="panel-heading"> <i class="fa fa-sign-in"></i>EditProfile Details</div>

<div class="panel-body"style="height: 500px; ">


<form method="POST" enctype="multipart/form-data" id="addFacultyForm">
           <label for ="userName" class="control-label col-sm-4">Enter Faculty Id</label>
            <input type="text" id="facultyId"  name="facultyId" class="control-label col-sm-4 " required></br></br>
           <label for ="userName" class="control-label col-sm-4">Enter User Name</label>
          <input type="text" id="userName"  name="userName" class="control-label col-sm-4 " ></br></br>
          <label for ="department" class="control-label col-sm-4">Choose Department</label>
          <select class="control-label col-sm-4" id="deptId"  name='deptId'>
                  <option value ="1">Computer Sc. & Engineering</option>
                  <option value="2" >Civil Engineering</option>
                  <option value ="3">Mechanical Engineering</option>
                  <option value="4" >Electrical & Electronics Engg.</option>
                  <option value="5" >Applied Sc. & Humanities</option>
                </select><br></br>

         <label for ="facultyName" class="control-label col-sm-4">Enter Name</label>
          <input type="text" id="facultyName"  name="facultyName" class="control-label col-sm-4 "></br></br>
         <label for ="facultyOfficialEmail" class="control-label col-sm-4">Enter Official Email</label>
         <input type="email" id="facultyOfficialEmail"name="facultyOfficialEmail"class="control-label col-sm-4"></br></br>
         <label for ="facultyPersonalEmail"class="control-label col-sm-4">Enter Personal Email</label>
          <input type="email" id="facultyPersonalEmail" name="facultyPersonalEmail"class="control-label col-sm-4"></br></br>
         <label for ="facultyMobNo"class="control-label col-sm-4">Mobile No. </label>
         <input type="text" id="facultyMobNo" name="facultyMobNo"class="control-label col-sm-4"></br></br>

         <div class="form-group fieldGroup">
                        <div class="input-group">
                        <label for ="propertyName"class="control-label col-sm-4">Enter Property Key Name </label>
                            <input type="text" value="${keyprop.keyPropertyName}" name="propertykeyname[]" class="control-label col-sm-4"style="margin-left:5%" placeholder="Enter Property Key Name"/></br><br>

                           <label for ="propertyValue"class="control-label col-sm-4">Enter Property Key Value </label>
                            <input type="text"name="propertykeyvalue[]" class="control-label col-sm-4" style="margin-left:5%" placeholder="Enter Property Key Value"/>

                            <div class="input-group-addon">
                                <a href="javascript:void(0)" class="btn btn-success addMore"><span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span> Add</a>
                            </div>
                        </div>
                    </div>


        <input type="submit" value="Submit" id="btnSubmit" class="btn btn-primary" />
        <div class="col-sm-4"></div>
        </div>
</form>

</div>
<div class=" fieldGroupCopy" style="display: none;">
    <div class="">
         <label for ="propertyName"class="control-label col-sm-4">Enter Property Key Name </label>
          <input type="text" name="propertykeyname" class="control-label col-sm-4" placeholder="Enter Property Key Name"/></br></br>
          <label for ="propertyValue"class="control-label col-sm-4">Enter Property Key Value </label>
          <input type="text" name="propertykeyvalue" class="control-label col-sm-4" placeholder="Enter Property Key Value"/>
        <div class="input-group-addon">
            <a href="javascript:void(0)" class="btn btn-danger remove"><span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span> Remove</a>
        </div>
    </div>
</div>

</div>
</div></div></div>
<jsp:include page ="footer.jsp"/>
<script src="http://localhost/js/addfaculty.js"></script>