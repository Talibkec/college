<jsp:include page="/jsp/header.jsp" />


    <div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>Contact Us</h3></div>

        </div>
    </div>
    <div class="container">
        <ul class="breadcrumb" style="background: none">
            <li><i class="fa fa-home"></i> Home</a></li>
            <li class="active">Contact Us</li>
        </ul>
        <div class="row">
            <div class="col-md-4 col-sm-5"><h4><strong>Location:</strong></h4>
                <address>Katihar Engineering College<br/>
                    <small>Katihar Bihar, India<br/> Pin &#8211; 854106</small>
                </address>
                <address>Call us on: 06452-239122</address>
                <h4></h4>
                <p>&nbsp;</p></div>
            <div class="col-md-8 col-sm-7">
                <div class="panel panel-default">
                    <div class="panel-heading"><span style="font-size: 18px;"><strong>Contact Us</strong></span></div>
                    <div class="panel-body">
                        <form method="post" class="form-horizontal" onsubmit="doContact(this); return false;"><input
                                type="hidden" name="action" value="ajaxhandler"> <input type="hidden" name="xaction"
                                                                                        value="contact">
                            <div class="form-group"><label class="control-label col-sm-3">Name:</label>
                                <div class="col-sm-9"><input type="text" name="fullName" class="form-control"
                                                             placeholder="Your Full Name" required></div>
                            </div>
                            <div class="form-group"><label class="control-label col-sm-3">Phone Number:</label>
                                <div class="col-sm-9"><input type="tel" name="phoneNumber" class="form-control" required
                                                             placeholder="Your Mobile Number"></div>
                            </div>
                            <div class="form-group"><label class="control-label col-sm-3">Email Address:</label>
                                <div class="col-sm-9"><input type="email" name="emailAddress" class="form-control"
                                                             placeholder="( Optional )"></div>
                            </div>
                            <div class="form-group"><label class="control-label col-sm-3">Your Query:</label>
                                <div class="col-sm-9"><textarea class="form-control" name="query" style="height: 100px;"
                                                                placeholder="Enter your query / message here"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="text-right">
                                    <button type="submit" class="btn btn-default btn-lg"
                                            data-loading-text="<i class='fa fa-cog fa-spin'></i> Please wait..."><i
                                            class="fa fa-check"></i> Submit
                                    </button> &nbsp;&nbsp;
                                    &nbsp;&nbsp;
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<jsp:include page="/jsp/footer.jsp" />