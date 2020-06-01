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
<div style="margin-left:50px" ><h3><c:if test = "${isProfilePic eq 'Yes'}">Upload Profile Details</c:if><c:if test = "${isProfilePic eq 'No'}">Upload Documents</c:if></h3></div>
</div></div><div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-2 col-sm-8">
<div class="panel panel-primary"><div class="panel-heading"> <i class="fa fa-sign-in"></i><c:if test = "${isProfilePic eq 'Yes'}">Upload Profile Details</c:if><c:if test = "${isProfilePic eq 'No'}">Upload Documents</c:if></div>

<c:if test = "${isProfilePic eq 'Yes'}" ><div class="panel-body"style="height: 300px; "></c:if>
<c:if test = "${isProfilePic eq 'No'}" ><div class ="panel-body"></c:if>

<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
    <c:if test = "${isProfilePic eq 'No'}">
        <label for="headLine" class="control-label col-sm-4 " style="text-align: right;" >Head Line</label>
        <div class="col-sm-8">
            <input type="text" name="facultydocumentsHeader" id="facultydocumentsHeader"/>
            <input type="hidden" id="facultyName" name="facultyName" value="NotAvailable" />
            <input type="hidden" id="facultyPersonalEmail" name="facultyPersonalEmail" value="NotAvailable" />
            <input type="hidden" id="facultyofficialEmail" name="facultyOfficialEmail" value="NotAvailable" />
            <input type="hidden" id="facultyMobNo" name="facultyMobNo" value="123"/>
        </div>
        <label for="headLine" class="control-label col-sm-4 " style="text-align: right;margin-top: 10px" >Is A Link? </label>
        <div class="col-sm-8">
             <input type="checkbox" name="isLink" id="isLink" style="width: 25px;height: 40px;" ></input>
        </div>
        <div style = "display:none" id ="linkBox">
            <label for="headLine" class="control-label col-sm-4 " style="text-align: right;">Link Address. </label>
            <div class="col-sm-8">
                <input type="text" name="linkAddress" id="linkAddress" /><br><br>
            </div>
        </div>
    </c:if>
    <c:if test = "${isProfilePic eq 'Yes'}" >

         <input type="hidden" name="facultydocumentsHeader" id="facultydocumentsHeader" value = "NotAvailable"/><br/><br/>
         <label for ="facultyName" class="control-label col-sm-4">Enter Name</label>
          <input type="text" id="facultyName" name="facultyName" class="control-label col-sm-4 "></br></br>
         <label for ="facultyOfficialEmail" class="control-label col-sm-4">Enter Official Email</label>
         <input type="email" id="facultyOfficialEmail" name="facultyOfficialEmail"class="control-label col-sm-4"></br></br>
         <label for ="facultyPersonalEmail"class="control-label col-sm-4">Enter Personal Email</label>
          <input type="email" id="facultyPersonalEmail" name="facultyPersonalEmail"class="control-label col-sm-4"></br></br>

         <label for ="facultyMobNo"class="control-label col-sm-4">Mobile No. </label>
         <input type="text" id="facultyMobNo" name="facultyMobNo"class="control-label col-sm-4"></br></br>


    </c:if>

        <div id="chooseFileBox" style="display:block">
            <label for="file" class="control-label col-sm-4" style="text-align: right;" >
                     <c:if test = "${isProfilePic eq 'No'}" >Choose File</c:if>
                     <c:if test = "${isProfilePic eq 'Yes'}" >Choose Profile Pic</c:if></label>
            <div class=" btn-default  col-sm-4" style="color:red">
            <input type="file" name="facultydocumentsFile" id="facultydocumentsFile"/><br/></div></div>
            <div id="msg" style="display: none; color: red;">*Please Select a file</div>
            <input type = "hidden" name = "isProfilePic" value = "${isProfilePic}" />
        </div>
        <c:if test ="${isProfilePic eq 'Yes'}">
         <div class="form-group fieldGroup">
                        <div class="input-group">
                            <input type="text" name="propertykeyname" class="form-control" placeholder="Enter Property Key Name"/>
                            <input type="text" name="propertykeyvalue" class="form-control" placeholder="Enter Property Key Value"/>
                            <div class="input-group-addon">
                                <a href="javascript:void(0)" class="btn btn-success addMore"><span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span> Add</a>
                            </div>
                        </div>
                    </div>
        </c:if>
        <input type="submit" value="Submit" id="btnSubmit" class="btn btn-primary" />
        <div class="col-sm-4"></div>
        </div>
</form>

<form method="GET" action="/" id="homePageForm">
    <input type="hidden" name="date" id="date"/><br/><br/>
    <input type="hidden" name="facultydocumentsHeader" id="facultydocumentsHeader"/><br/><br/>
    <input type="hidden" name="hfileLocation" id="hfileLocation"/><br/><br/>

</form></div>
<div class="form-group fieldGroupCopy" style="display: none;">
    <div class="input-group">
        <input type="text" name="propertykeyname[]" class="form-control" placeholder="Enter Property Key Name"/>
        <input type="text" name="propertykeyvalue[]" class="form-control" placeholder="Enter Property Key Value"/>
        <div class="input-group-addon">
            <a href="javascript:void(0)" class="btn btn-danger remove"><span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span> Remove</a>
        </div>
    </div>
</div>

</div>
</div></div></div>
<jsp:include page ="footer.jsp"/>
<script src="http://keck.ac.in/js/facultyfileUpload.js"></script>