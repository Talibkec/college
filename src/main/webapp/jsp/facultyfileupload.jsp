<jsp:include page="header.jsp"/>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
<div style="margin-left:50px" ><h3>Upload File</h3></div>
</div></div><div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-4 col-sm-4">
<div class="panel panel-default"><div class="panel-heading"> <i class="fa fa-sign-in"></i>Upload Notice/News</div>
<div class="panel-body">

<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
    <label for="headLine" class="control-label col-sm-4" >Head Line</label>
    <div class="col-sm-8">
        <input type="text" name="facultydocumentsHeader" id="facultydocumentsHeader"/><br/><br/>
    </div>


        <label for="file" class="control-label col-md-4" >Choose File</label>
    <div class=" btn-default  col-md-8" style="color:red">
    <input type="file" name="facultydocumentsFile" id="facultydocumentsFile"/><br/></div></div>
    <div id="msg" style="display: none; color: red;">*Please Select a file</div>
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