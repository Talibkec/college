$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

    $("#isLink").click( function( event ) {
        if($("#isLink").is(':checked')){
            $("#linkBox").css("display","block");
            $("#chooseFileBox").css("display","none");
        }
        else{
             $("#linkBox").css("display","none");
             $("#chooseFileBox").css("display","block");
        }
    })

});

function fire_ajax_submit() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);

    data.append("CustomField", "This is some extra data, testing");
    data.append("isScrollable",$("#isScrollable").is(':checked'));

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/uploadfile/facultyFileUpload",
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data, textStatus, xhr) {

             //window.location.href = "jsp/login.jsp?a=b&c=d";

                var params = data.split(",");
                $("#hfileLocation").val(params[0]);
                $("#hnoticeHeader").val(params[1]);
                $("#hnoticeType").val(params[2]);
                $("#result").text(data);
                console.log("SUCCESS : ", data);
                $("#btnSubmit").prop("disabled", false);
                history.go(-1);
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
