<jsp:include page="admin/HeaderDashboard.jsp"/>

</div></div><div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-4 col-sm-4">
<div class="panel panel-default"><div class="panel-heading"> <i class="fa fa-sign-in"></i>Upload Slide Image</div>
<div class="panel-body">

<form method="POST" enctype="multipart/form-data" id="slideimageUploadForm" action="/">
    <label for="caption" class="control-label col-sm-4" >Caption</label>
    <div class="col-sm-8">
        <input type="text" name="caption" id="caption"/><br/><br/>
    </div>
      <label for="file" class="control-label col-sm-4" >Choose File</label>
    <div class=" btn-default  col-md-8" style="color:red"> <input type="file" name="slideImage" id="slideImage"/><br/></div></div>
    <div id="msg" style="display: block; color: red;"></div>
    <input type="submit" value="Submit" id="btnSubmit" class="btn btn-primary" /><div class="col-sm-5"></div></div>
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
<script>
$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });




});

function fire_ajax_submit() {

    // Get form
    var form = $('#slideimageUploadForm')[0];

    var data = new FormData(form);

    data.append("CustomField", "This is some extra data, testing");
    data.append("isScrollable",$("#isScrollable").is(':checked'));

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/user/api/uploadslideimage",
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        success: function (data, textStatus, xhr) {

             //window.location.href = "jsp/login.jsp?a=b&c=d";

                var params = data.split(",");
                $("#hfileLocation").val(params[0]);
                $("#hnoticeHeader").val(params[1]);
                $("#hnoticeType").val(params[2]);
                $("#result").text(data);
                console.log("SUCCESS : ", data);
                $("#btnSubmit").prop("disabled", false);
                $("#homePageForm").submit();
            $("#msg").html(data);
            $("#msg").css("color" , "green");
        },
        error: function (e) {

            $("#result").text(e.responseText);
            $("#msg").css({"display":"block", "color":"red"});
            $("#msg").html(e.responseText)
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}


</script>
