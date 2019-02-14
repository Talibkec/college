<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
<div style="margin-left:50px" ><h3><c:if test = "${isProfilePic eq 'Yes'}">Upload Profile Details</c:if><c:if test = "${isProfilePic eq 'No'}">Upload Documents</c:if></h3></div>
</div></div><div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-4 col-sm-4">
<div class="panel panel-default"><div class="panel-heading"> <i class="fa fa-sign-in"></i><c:if test = "${isProfilePic eq 'Yes'}">Upload Profile Details</c:if><c:if test = "${isProfilePic eq 'No'}">Upload Documents</c:if></div>
<div class="panel-body">

<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
    <c:if test = "${isProfilePic eq 'No'}">
        <label for="headLine" class="control-label col-sm-4" >Head Line</label>
        <div class="col-sm-8">
            <input type="text" name="facultydocumentsHeader" id="facultydocumentsHeader"/><br/><br/>
        </div>
    </c:if>
    <c:if test = "${isProfilePic eq 'Yes'}" >
         <input type="hidden" name="facultydocumentsHeader" id="facultydocumentsHeader" value = "NotAvailable"/><br/><br/>
         <label for ="facultyName">Enter Name</label>
           <input type="text" id="facultyName" name="facultyName"></br></br>
         <label for ="facultyEmail">Enter Email</label>
             <input type="text" id="facultyEmail" name="facultyEmail"></br></br>
             <label for ="facultyMobNo">Mobile No. </label>
             <input type="text" id="facultyMobNo" name="facultyMobNo"></br>

    </c:if>

        <label for="file" class="control-label col-md-4" >Choose File</label>
        <div class=" btn-default  col-md-8" style="color:red">
        <input type="file" name="facultydocumentsFile" id="facultydocumentsFile"/><br/></div></div>
        <div id="msg" style="display: none; color: red;">*Please Select a file</div>
        <input type = "hidden" name = "isProfilePic" value = "${isProfilePic}" />
        <input type="submit" value="Submit" id="btnSubmit" class="btn btn-primary" /><div class="col-sm-5"></div></div>
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