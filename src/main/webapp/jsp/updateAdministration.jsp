<jsp:include page="header.jsp"/>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
<div style="margin-left:50px" ><h3>Update Administration</h3></div>
</div></div><div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-4 col-sm-4">
<div class="panel panel-default"><div class="panel-heading"> <i class="fa fa-sign-in"></i>Update Administration</div>
<div class="panel-body">

<form method="POST" enctype="multipart/form-data" id="fileUploadForm" action>


   <!-- <label for="headLine" class="control-label col-sm-4" >Head Line</label>
    <div class="col-sm-8">
        <input type="text" name="noticeHeader" id="noticeHeader"/><br/><br/>
    </div>

    <label for="noticeType" class="control-label col-sm-4" >Update Type</label>

-->



 <label for="facultyEmail" class="control-label col-sm-4" >Faculty Email:</label>
        <div class="col-sm-8">

            <input type="text" name="facultyEmail" id="facultyEmail"/><br/><br/>
        </div>





         <label for="updateDutyAssigned" class="control-label col-sm-4" >Designation:</label>
             <div class="col-sm-8">
                         <input type="text" name="updateDutyAssigned" id="updateDutyAssigned"/><br/><br/>
                     </div>

                     <label for="updateRole" class="control-label col-sm-4" >Duty Assigned:</label>
                         <div class="col-sm-8">
                                      <input type="text" name="roleValue" id="updateRole"/><br/><br/>
                                  </div>





    <!--

    <div class="col-sm-8">
            <select name = "updateType" id="updateType"><option value="updateDutyAssigned">Update duty assigned</option>
                                                        <option value="roleValue">Update Role</option>
                                                        <option value="updateMob">Update Mobile no.</option>
                                                      <option value="">Update Designatio</option>
                                                         <option value="CIVILNotice">Civil</option>
                                                        <option value="MECHNotice">Mechanical</option>
                                                        <option value="ELECNotice">Electronics</option>
                                                        <option value="ASHNotice">Applied Science</option>
                                                         <option value="BLINKINGMessage">Blink message</option>

-->




    <!--  <label for="noticeUploadDate" class="control-label col-sm-4" >Date</label>
    <div class="col-sm-8">
            <input type="text" name="date" id="noticeUploadDate"/>
    </div>

     <label for="headLine" class="control-label col-sm-4" >Scrollable</label>
        <div class="col-sm-8">
            <input type="checkbox" name="isScrollable" id="isScrollable" style="width: 25px;height: 40px;" ></input><br><br>
        </div>
    <label for="file" class="control-label col-sm-4" >Choose File</label>
    <div class=" btn-default  col-md-8" style="color:red"> <input type="file" name="noticeFile" id="noticeFile"/><br/></div></div>
  <div id="msg" style="display: none; color: red;">*Please Select a file</div>-->


     <input type="submit" value="Submit" id="btnSubmit" class="btn btn-primary" /><div class="col-sm-5"></div>

<form method="GET" action="/" id="homePageForm">
    <input type="hidden" name="facultyName" id="facultyName"/><br/><br/>
    <input type="hidden" name="updateDutyAssigned" id="updateDutyAssigned"/><br/><br/>
    <input type="hidden" name="roleValue" id="roleValue"/><br/><br/>
</form></div>
</div>
</div></div></div>
<jsp:include page ="footer.jsp"/>
<link rel="stylesheet" href="http://localhost/js/store/jquery-ui/jquery-ui.css">
<script src="http://localhost/js/store/jquery-ui/jquery-ui.js"></script>
<script src="http://localhost/js/uploadAdministration.js"></script>