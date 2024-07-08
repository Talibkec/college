<jsp:include page="admin/HeaderDashboard.jsp" />
</div>
</div>
<div class="container"> <br /><br />
  <div class="row">
    <div class="panel-heading"> <i class="fa fa-sign-in"></i>Upload Achievement Image</div>
       
    <div class="col-sm-offset-4 col-sm-4">
      <div class="panel panel-default">
        <div class="panel-body">

          <form method="POST" enctype="multipart/form-data" id="achievementImageUploadForm" action="/">
            <label for="caption" class="control-label col-sm-4">Caption</label>
            <div class="col-sm-8">
              <input type="text" name="caption" id="caption" /><br /><br />
            </div>
            <label for="file" class="control-label col-sm-4">Choose File</label>
            <div class=" btn-default  col-md-8" style="color:red"> <input type="file" name="slideImage"
                id="slideImage" /><br /></div>
       
        <div id="msg" style="display: none; color: red;">*Please Select a file</div>
        <input type="submit" value="Submit" id="btnSubmit" class="btn btn-primary" />
        <div class="col-sm-5"></div>
      </div>

      </form>
    </div>
      <form method="GET" action="/" id="homePageForm">



      </form>
    </div>
  </div>
</div>
</div>
</div>
<jsp:include page="footer.jsp" />
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/js/store/jquery-ui/jquery-ui.js"></script>
<script src="/js/achievementImageupload.js"></script>