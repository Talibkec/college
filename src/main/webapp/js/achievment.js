$(document).ready(function() {
    $("#achievementImageUploadForm").submit(function(e) {
        e.preventDefault(); // Prevent default form submission

        var formData = new FormData(this);
        var fileInput = $("#slideImage").val(); // Get file input value

        // Check if file is selected
        if (fileInput === "") {
            $("#msg").fadeIn().text("*Please select a file");
            return;
        } else {
            $("#msg").fadeOut();
        }

        $.ajax({
            url: "/uploadAchievementImage", // Change to your backend upload endpoint
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            beforeSend: function() {
                $("#btnSubmit").prop("disabled", true).text("Uploading...");
            },
            success: function(response) {
                $("#btnSubmit").prop("disabled", false).text("Submit");

                if (response.success) {
                    alert("Image uploaded successfully!");

                    // Reset the form fields without reloading the page
                    $("#achievementImageUploadForm")[0].reset();

                    // Optional: Clear any messages
                    $("#msg").fadeOut();
                } else {
                    alert("Upload failed: " + response.message);
                }
            },
            error: function() {
                $("#btnSubmit").prop("disabled", false).text("Submit");
                alert("Upload failed. Please try again.");
            }
        });
    });
});
