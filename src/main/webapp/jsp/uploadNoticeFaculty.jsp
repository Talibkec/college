<jsp:include page="/jsp/header.jsp"/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->

    <!-- /.content-header -->

    <div class="col-sm-offset-4 col-sm-4 md:flex justify-center">
        <div class="panel panel-default border border-1 p-2"  style="min-width:400px;"><div class="panel-heading text-2xl m-3"> <i class="fa fa-sign-in"></i>Upload Notice/News</div>
            <div class="panel-body">

                <form method="POST" enctype="multipart/form-data" id="fileUploadForm" action>
                    <div class="flex justify-around" >
                    <label style="min-width:50%" for="headLine" class="control-label col-sm-4" >Head Line</label>
                    <div style="min-width:50%"  class="col-sm-8">
                        <input type="text" class="w-full" name="noticeHeader" id="noticeHeader"/><br/><br/>
                    </div>
                   </div><div class="flex justify-around" >
                    <label style="min-width:50%"  for="noticeType" class="control-label col-sm-4" >Notice Type</label>

                    <div  style="min-width:50%" class="col-sm-8">
                        <select class="w-full" name = "noticeType" id="noticeType"><option value="Notice">Notice</option>
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
                     </div><div class="flex justify-around" >
                    <label class="control-label col-sm-12" ></label>
                    <label style="min-width:50%"  for="noticeUploadDate" class="control-label col-sm-4" >Date</label>
                    <div style="min-width:50%"  class="col-sm-8">
                        <input  class="w-full" type="text" name="date" id="noticeUploadDate"/>
                    </div>

                     </div><div class="flex justify-around" >

                    <label style="min-width:50%"  for="headLine" class="control-label col-sm-4" >Scrollable</label>
                    <div  style="min-width:50%" class="col-sm-8">
                        <input class="w-full" type="checkbox" name="isScrollable" id="isScrollable" style="width: 25px;height: 40px;" ></input><br><br>
                    </div>
                     </div><div class="flex justify-around" >

                    <div  style="min-width:50%" class=" btn-default  col-md-8" style="color:red"> <input type="file" accept="application/pdf" name="noticeFile" id="noticeFile" onchange="validateFileType()"/><br/></div></div>
             </div>
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
       <button type="submit" class="focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800 w-full my-2">Submit</button>   </form>

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
