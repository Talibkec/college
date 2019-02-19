<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
<div style="margin-left:50px" ><h3><c:if test = "${isProfilePic eq 'Yes'}">Upload Profile Details</c:if><c:if test = "${isProfilePic eq 'No'}">Upload Documents</c:if></h3></div>
</div></div><div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-2 col-sm-8">
<div class="panel panel-primary"><div class="panel-heading"> <i class="fa fa-sign-in"></i><c:if test = "${isProfilePic eq 'Yes'}">Upload Profile Details</c:if><c:if test = "${isProfilePic eq 'No'}">Upload Documents</c:if></div>

<c:if test = "${isProfilePic eq 'Yes'}" ><div class="panel-body"style="height: 300px; "></c:if>
<c:if test = "${isProfilePic eq 'No'}" ><div class ="panel-body"></c:if>

<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
    <c:if test = "${isProfilePic eq 'No'}">
        <label for="headLine" class="control-label col-sm-4 " >Head Line</label>
        <div class="col-sm-8">
            <input type="text" name="facultydocumentsHeader" id="facultydocumentsHeader"/></br></br>
            <input type="hidden" id="facultyName" name="facultyName" value="NotAvailable" />
            <input type="hidden" id="facultyPersonalEmail" name="facultyPersonalEmail" value="NotAvailable" />
            <input type="hidden" id="facultyofficialEmail" name="facultyOfficialEmail" value="NotAvailable" />
            <input type="hidden" id="facultyMobNo" name="facultyMobNo" value="123"/>
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

        <label for="file" class="control-label col-sm-4" ><c:if test = "${isProfilePic eq 'No'}" >Choose File</c:if><c:if test = "${isProfilePic eq 'Yes'}" >Choose Profile Pic</c:if></label>
        <div class=" btn-default  col-sm-4" style="color:red">
        <input type="file" name="facultydocumentsFile" id="facultydocumentsFile"/><br/></div></div>
        <div id="msg" style="display: none; color: red;">*Please Select a file</div>
        <input type = "hidden" name = "isProfilePic" value = "${isProfilePic}" />
        <input type="submit" value="Submit" id="btnSubmit" class="btn btn-primary" />
        <div class="col-sm-4"></div>
        </div>
</form>

<form method="GET" action="/" id="homePageForm">
    <input type="hidden" name="date" id="date"/><br/><br/>
    <input type="hidden" name="facultydocumentsHeader" id="facultydocumentsHeader"/><br/><br/>
    <input type="hidden" name="hfileLocation" id="hfileLocation"/><br/><br/>

</form></div>
</div>
</div></div></div>
<jsp:include page ="footer.jsp"/>
<script src="http://localhost/js/facultyfileUpload.js"></script>