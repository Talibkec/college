$(document).ready(function () {
    $("#btnSubmit").click(function (event) {
        // Stop form from being submitted normally
        event.preventDefault();

        // Disable the button immediately to prevent multiple clicks
        $(this).prop("disabled", true);

        // Call the function to submit the form data via AJAX
        fire_ajax_submit();
    });
});

function fire_ajax_submit() {
    // Get form
    var form = $('#slideimageUploadForm')[0];

    var data = new FormData(form);

    // Append additional data
    data.append("CustomField", "This is some extra data, testing");
    data.append("isScrollable", $("#isScrollable").is(':checked'));

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/auth/api/uploadLabDoc",
        data: data,
        processData: false, // Don't process the data into a query string
        contentType: false, // Let FormData set the content type
        cache: false,
        success: function (data, textStatus, xhr) {
            // Handle success
            var params = data.split(",");
            $("#hfileLocation").val(params[0]);
            $("#hnoticeHeader").val(params[1]);
            $("#hnoticeType").val(params[2]);
            $("#result").text(data);

            console.log("SUCCESS : ", data);


            $("#homePageForm").submit();

            $("#msg").html(data);
            $("#msg").css("color", "green");

            $("#btnSubmit").prop("disabled", false);


            setTimeout(function () {
                location.reload();
            }, 3000);
        },
        error: function (e) {
            // Handle errors
            $("#msg").css("color", "red");
            $("#result").text(e.responseText);
            $("#msg").css({ "display": "block", "color": "red" });
            $("#msg").html(e.responseText);

            console.log("ERROR : ", e);

            // Re-enable the button after error
            $("#btnSubmit").prop("disabled", false);
        }
    });
}
