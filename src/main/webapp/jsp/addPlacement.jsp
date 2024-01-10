<jsp:include page="admin/HeaderDashboard.jsp"/>

<div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-4 col-sm-4">
    <div class="panel panel-default"><div class="panel-heading"> <i class="fa fa-sign-in"></i>Upload Downloadable Resource</div>
        <div class="panel-body">
            <form method="POST" enctype="multipart/form-data" id="slideimageUploadForm">



                <label for="caption" class="control-label col-sm-4" >Student Name </label>
                <div class="col-sm-8">
                    <input type="text" name="name" id="caption"/><br/><br/>
                </div>


                <label for="caption" class="control-label col-sm-4" >Company Name</label>
                <div class="col-sm-8">
                    <input type="text" name="company" id="caption"/><br/><br/>
                </div>

                <label for="caption" class="control-label col-sm-4" >Registration Number</label>
                <div class="col-sm-8">
                    <input type="text" name="registration_no" id="caption"/><br/><br/>
                </div>

                <label for ="caption" class="control-label col-sm-4">Choose Department</label>
                <div class="col-sm-8">
                <select class="control-label col-sm-4" id="deptId"  name='department'>
                    <option value ="1">Computer Sc. & Engineering</option>
                    <option value="2" >Civil Engineering</option>
                    <option value ="3">Mechanical Engineering</option>
                    <option value="4" >Electrical & Electronics Engg.</option>
                    <option value="5" >Applied Sc. & Humanities</option>
                    <option value="6" >Electronics Engineering (VLSI Design and Technology)</option>
                </select><br>
                </div>


                <label for="caption" class="control-label col-sm-4" >Session </label>
                <div class="col-sm-8">
                    <input type="text" name="session" id="session"/><br/><br/>
                </div>






                <div id="msg" style="color: red;"></div>
                <input type="submit" value="Upload" id="btnSubmit" class="btn btn-primary" /><div class="col-sm-5"></div>
            </form>

        </div>
    </div>
</div>
</div>
</div>
</div></div></div>
<jsp:include page ="footer.jsp"/>
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/js/store/jquery-ui/jquery-ui.js"></script>
<script src="/js/uploadForm.js"></script>
