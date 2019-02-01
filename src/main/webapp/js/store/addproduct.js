$(document).ready(function () {

    $("#addProduct").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    // Get form
    var form = $('#addProductForm')[0];

    var data = new FormData(form);

    //data.append("CustomField", "This is some extra data, testing");

    $("#addProduct").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/store/addProduct",
        data: {
            productDetails:JSON.stringify(getProductDetails())
        },
        //contentType: false,
        //cache: false,
        timeout: 600000,
        success: function (data, textStatus, xhr) {

             $("#addProduct").prop("disabled", false);
        },
        error: function (e) {

            $("#addProduct").prop("disabled", false);

        }
    });

}

function getProductDetails(){
    var productJsonObj = {};
        productJsonObj.productName = $("#p_name").val();
        productJsonObj.productQuantity = $("#p_quantity").val();
        productJsonObj.productDescription = $("#p_description").val();
        productJsonObj.vendorName = $("#v_name").val();
        productJsonObj.productType = $("#p_type").val();
        return productJsonObj;
}