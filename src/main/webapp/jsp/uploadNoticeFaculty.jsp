<jsp:include page="/jsp/header.jsp"/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->

    <!-- /.content-header -->

    <div class="col-sm-offset-4 col-sm-4">
        <div class="panel panel-default"><div class="panel-heading"> <i class="fa fa-sign-in"></i>Upload Notice/News</div>
            <div class="panel-body">

                <form method="POST" enctype="multipart/form-data" id="fileUploadForm" action>
                    <label for="headLine" class="control-label col-sm-4" >Head Line</label>
                    <div class="col-sm-8">
                        <input type="text" name="noticeHeader" id="noticeHeader"/><br/><br/>
                    </div>

                    <label for="noticeType" class="control-label col-sm-4" >Notice Type</label>

                    <div class="col-sm-8">
                        <select name = "noticeType" id="noticeType"><option value="Notice">Notice</option>
                            <option value="News">News</option>
                            <option value="Tender">Tender</option>
                            <!--      <option value="CSENotice">Cse</option>
                                  <option value="CIVILNotice">Civil</option>
                                  <option value="MECHNotice">Mechanical</option>
                                  <option value="ELECNotice">Electronics</option>
                                  <option value="ASHNotice">Applied Science</option>
                                   <option value="BLINKINGMessage">Blink message</option>
                            -->

                        </select><br/><br/>
                    </div>
                    <label class="control-label col-sm-12" ></label>
                    <label for="noticeUploadDate" class="control-label col-sm-4" >Date</label>
                    <div class="col-sm-8">
                        <input type="text" name="date" id="noticeUploadDate"/>
                    </div>

                    <label for="headLine" class="control-label col-sm-4" >Scrollable</label>
                    <div class="col-sm-8">
                        <input type="checkbox" name="isScrollable" id="isScrollable" style="width: 25px;height: 40px;" ></input><br><br>
                    </div>
                    <label for="file" class="control-label col-sm-4" >Choose File</label>
                    <div class=" btn-default  col-md-8" style="color:red"> <input type="file" accept="application/pdf" name="noticeFile" id="noticeFile" onchange="validateFileType()"/><br/></div></div>
            <div id="msg" style="display: none; color: red;">* Please Select a file</div>
            <script>
                function validateFileType() {
                    var selectedFile = document.getElementById('noticeFile').files[0];
                    var allowedTypes = ['application/pdf'];

                    if (!allowedTypes.includes(selectedFile.type)) {
                        alert('Invalid file type. Please upload a PDF file.');
                        document.getElementById('noticeFile').value = '';
                    }
                }
            </script>
            <input type="submit" value="Submit" id="btnSubmit" class="btn btn-primary" /><div class="col-sm-5"></div></div>
        </form>

        <form method="GET" action="/" id="homePageForm">
            <input type="hidden" name="date" id="date"/><br/><br/>
            <input type="hidden" name="hnoticeHeader" id="hnoticeHeader"/><br/><br/>
            <input type="hidden" name="hfileLocation" id="hfileLocation"/><br/><br/>
            <input type="hidden" name="hnoticeType" id="hnoticeType"/><br/><br/>
        </form></div>
</div>
</div></div>
<jsp:include page ="footer.jsp"/>
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="/js/store/jquery-ui/jquery-ui.js"></script>
<script src="/js/fileupload.js"></script>