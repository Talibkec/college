<jsp:include page="HeaderDashboard.jsp" />

<div class="container"> <br /><br />
    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
            <div class="panel-heading"> <i class="fa fa-sign-in"></i>Upload Lab Doc</div>
              
            <div class="panel panel-default">
                 <div class="panel-body">

                    <form method="POST" enctype="multipart/form-data" id="slideimageUploadForm">
                        <label for="caption" class="control-label col-sm-4">Enter caption of Lab Doc</label>
                        <div class="col-sm-8">
                            <input type="text" name="caption" id="caption" /><br /><br />
                        </div>



                        <div class="col-sm-8">
                            <label for="department" class="control-label col-sm-4">Choose Department</label>
                            <select class="control-label col-sm-4" id="deptId" name='deptId'>
                                <option value="1">Computer Sc. & Engineering</option>
                                <option value="2">Civil Engineering</option>
                                <option value="3">Mechanical Engineering</option>
                                <option value="4">Electrical & Electronics Engg.</option>
                                <option value="5">Applied Sc. & Humanities</option>
                                <option value="6">Electronics Engineering (VLSI Design and Technology)</option>
                                <option value="7">Mechanical and Manufacturing Engineering</option>
                          
                                <option value="8">Manufacturing Engineering</option>
                          
                            </select><br></br>
                        </div>


                        <label class="control-label col-sm-4">Choose Document</label>
                        <div class=" btn-default  col-md-8" style="color:red"> <input type="file" name="slideImage"
                                id="slideImage" /><br /></div>
                        <div id="msg" style="color: red;"></div>
                        <input type="submit" value="Upload" id="btnSubmit" class="btn btn-primary" />
                        <div class="col-sm-5"></div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
<jsp:include page="../footer.jsp" />
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/js/store/jquery-ui/jquery-ui.js"></script>
<script src="/js/uploadLabDoc.js"></script>