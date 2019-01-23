<jsp:include page="/jsp/header.jsp" />

 <div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>AKU Approval</h3></div>

            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
         <div class="col-md-4">
                        <ul class="list-group page-menu">
                            <li class="list-group-item" style="background: #eee; font-size: 20px;">Approval</li>
                            <li class="list-group-item"><a href="http://localhost/approval/aicteapproval">&nbsp;&nbsp; <i
                                    class="fa fa-angle-double-right"></i> AICTE Approval</a></li>
                            <li class="list-group-item"><a href="http://localhost/approval/akuapproval">&nbsp;&nbsp; <i
                                                        class="fa fa-angle-double-right"></i> AKU Approval</a></li>
                        </ul>
                    </div>
            <div class="col-md-8 col-md-offset-2">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300; margin-left:700 ">
                    <p>
                        <embed src="http://localhost/jsp/approval/aku.pdf" width="700px" height="700px"></embed>
                    </p>
                     <p><strong>AKU Regulation &#8212;-</strong></p>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-download"></i>
                       <a href="http://localhost/jsp/approval/aku.pdf">Download</a>
                                                                                </button>
                    </div>
            </div>
        </div>
    </div>

<jsp:include page="/jsp/footer.jsp" />
