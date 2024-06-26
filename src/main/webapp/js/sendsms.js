$(document).ready(function () {

    $("#btnSubmit").click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submit();
    });

    $("#smsGroup").change(function () {
            if($("#smsGroup").val() == 'individual'){
    			$('#numberLabel').css('display','block');
    			$('#numberInput').css('display','block');
    	    }else{
    		    $("#numberInput").val("");
    		    $('#numberLabel').css('display','none');
    		    $('#numberInput').css('display','none');
    		}
    	});
});

function fire_ajax_submit() {

    // Get form
    var form = $('#sendMessageForm')[0];

    var data = new FormData(form);

    data.append("CustomField", "This is some extra data, testing");

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/auth/sendMessage",
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

           var params = data.split(",");
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);
        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}