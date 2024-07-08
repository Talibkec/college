'<jsp:include page="/jsp/header.jsp"/>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-sm-8"><h3> Contact Us </h3></div>

        </div>
    </div>
</div>
<div class="container">

    <a href="/"><i class="fa fa-home"></i> Home</a>

    <div class="row">
        <div class="container"><br><br>
            <div class="row">
                <div class="col-sm-offset-4 col-sm-4">
                    <div class="panel panel-default">
                        <div class="panel-heading"><i class="fa fa-sign-in"></i>Contact us</div>
                        <span></span>
                        <div class="panel-body">
                            <form method="post" class="form form-horizontal" action="/contactus">
                                <div class="input-group input-group-sm ">
                                    <span></span>
                                    <div class="row" style="margin:10px 10px 10px 0px;">
                                        <div class="col-sm-12"><input type="text" class="form-control" name="username"
                                                                      placeholder="Email" autofocus="" required="">
                                        </div>
                                    </div>
                                        <div class="row" style="margin:10px 10px 10px 0px;">
                                            <div class="col-sm-12"><input type="text" class="form-control"
                                                                          name="name" placeholder="Name"
                                                                          autofocus="" required="">
                                            </div>
                                        </div>
                                            <div class="row" style="margin:10px 10px 10px 0px;">
                                                <div class="col-sm-12"><input type="number" class="form-control"
                                                                              name="phone" placeholder="Phone"
                                                                              autofocus="true" required="">
                                                </div>
                                            </div>
                                            <div class="row" style="margin:10px 10px 10px 0px;">
                                                <div class="col-sm-12"><input type="number" class="form-control"
                                                                              name="registrationNo" placeholder="Registration No"
                                                                               required="">
                                                </div>
                                            </div>
                                                <div class="row" style="margin:10px 10px 10px 0px;">
                                                    <div class="col-sm-12"><textarea rows="6" cols="5"
                                                                                     class="form-control"
                                                                                     name="message"
                                                                                     placeholder="Type your message here"
                                                                                     required=""></textarea></div>
                                                </div>

                                                <input type="hidden" name="" value="">
                                                <div class="row" style="margin:25px 10px 10px 0px;">
                                                    <div class="col-sm-12">
                                                        <button type="submit"
                                                                data-loading-text="<i class='fa fa-cog fa-spin'></i> Please wait..."
                                                                class="btn btn-default btnHover"
                                                                style="padding:6px 100px; background-color:#81cfe8"><i
                                                                class="fa fa-check"></i>Send Message
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="row" style="margin:25px 10px 10px 0px;">
                                                    <div class="col-sm-12">
                                                        Contact mail :-<a href=mailto: principalranjanakri84@gmail.com">
                                                        principalranjanakri84@gmail.com  </a><br>
                                                        Contact phone:-<a href="tel:06452-239122" class="Blondie">
                                                        06452-239122 </a>
                                                    </div>
                                                </div>
                                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="modalInfo">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header"><h4 class="modal-title">Information!</h4></div>
                        <div class="modal-body"> Please contact site admin to reset and get new password for your
                            account.
                            <hr>
                            <div class="text-right">
                                <button type="button" class="btn btn-default" data-dismiss="modal"><i
                                        class="fa fa-remove"></i>
                                    Close
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>
