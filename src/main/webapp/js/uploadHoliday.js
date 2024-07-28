$(document).ready(function () {
    $("#btnSubmit").click(function (event) {
        // Stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submit();
    });
});

function fire_ajax_submit() {
    // Get form
    var form = $('#slideimageUploadForm')[0];
    var data = new FormData(form);

    data.append("CustomField", "This is some extra data, testing");
    data.append("isScrollable", $("#isScrollable").is(':checked'));

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/auth/api/uploadHoliday",
        data: data,
        processData: false, // Prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        success: function (data, textStatus, xhr) {
            var params = data.split(",");
            $("#hfileLocation").val(params[0]);
            $("#hnoticeHeader").val(params[1]);
            $("#hnoticeType").val(params[2]);
            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);
            $("#homePageForm").submit();
            $("#msg").html(data).css("color", "green");

            // Auto-refresh the page after 2 seconds
            setTimeout(function () {
                location.reload(); // This will refresh the page
            }, 2000); // Adjust time as needed
        },
        error: function (e) {
            $("#msg").css("color", "red");
            $("#result").text(e.responseText);
            $("#msg").css({"display": "block", "color": "red"}).html(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
        }
    });
}
