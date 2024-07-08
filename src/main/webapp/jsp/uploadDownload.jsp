<jsp:include page="admin/HeaderDashboard.jsp" />

<div class="container"> <br /><br />
    <div class="row">
        <div class="panel-heading"> <i class="fa fa-sign-in"></i>Upload Downloadable Resource</div>
            
        <div class="col-sm-offset-4 col-sm-4">
            <div class="panel panel-default">
                <div class="panel-body">

                    <form method="POST" enctype="multipart/form-data" id="slideimageUploadForm">
                        <label for="caption" class="control-label col-sm-4">Enter Title of File</label>
                        <div class="col-sm-8">
                            <input type="text" name="caption" id="caption" /><br /><br />
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
<jsp:include page="footer.jsp" />
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/js/store/jquery-ui/jquery-ui.js"></script>
<script src="/js/uploadDownload.js"></script>