$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        event.preventDefault();

        fire_ajax_submit();
    });

    $('#addFacultyForm').on('submit', function (e) {
        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: $(this).data('action'),
            data: new FormData(this),
            processData: false,
            contentType: false,
            success: function (response) {
                console.log('Response:', response);
                $("#form_message").css({ color: 'green' }).html(response);
            },
            error: function (err) {
                console.error('Error:', err);
                $("#form_message").css({ color: 'red' }).html("An error occurred. Please try again.");
            },
        });
    });
});

function fire_ajax_submit() {

    var form = $('#addFacultyForm')[0];
    var data = new FormData(form);

    if (
        $("#branchName").val() === '' ||
        $("#regularSeat").val() === '' ||
        $("#lateralSeat").val() === ''
    ) {
        $("#form_message").css({ color: 'red' }).html("All fields are required, including Branch Name!");
        return false;
    }


    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: form.getAttribute("data-action"),
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (response) {
            console.log("SUCCESS:", response);
            $("#form_message").css({ color: 'green' }).html(response);
            $("#btnSubmit").prop("disabled", false);
        },
        error: function (err) {
            console.error("ERROR:", err);
            $("#form_message").css({ color: 'red' }).html("Failed to add course. Please try again.");
            $("#btnSubmit").prop("disabled", false);
        }
    });
}
