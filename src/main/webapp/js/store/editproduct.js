$(document).ready(function () {

    $("#editProduct").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });



});

function fire_ajax_submit() {

    // Get form
    var form = $('#editProductForm')[0];

    var data = new FormData(form);

    //data.append("purchaseDate", $("#purchaseDate").val());

    $("#addroduct").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/store/addProduct",
        data: {
            productDetails:JSON.stringify(getProductDetails()),
            //purchaseDate:$("#purchaseDate").val()
        },
        //contentType: false,
        //cache: false,
        timeout: 600000,
        success: function (data, textStatus, xhr) {

              $("#editProduct").prop("disabled", false);
              window.location.replace("/store/smdashboard");
        },
        error: function (e) {

            $("#editProduct").prop("disabled", false);

        }
    });

}

function getProductDetails(){
    var productJsonObj = {};
        productJsonObj.productId = $("#productId").val();
        productJsonObj.productName = $("#p_name").val();
        productJsonObj.productQuantity = $("#p_quantity").val();
        productJsonObj.productDescription = $("#p_description").val();
        productJsonObj.vendorName = $("#v_name").val();
        productJsonObj.productType = $("#p_type").val();
        return productJsonObj;
}