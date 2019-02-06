$(document).ready(function () {

    $("#addPurchase").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    // Get form
    var form = $('#addPurchaseForm')[0];

    var data = new FormData(form);

    //data.append("CustomField", "This is some extra data, testing");

    $("#addPurchase").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/store/addPurchase",
        data: {
            purchaseDetails:JSON.stringify(getPurchaseDetails())
        },
        //contentType: false,
        //cache: false,
        timeout: 600000,
        success: function (data, textStatus, xhr) {

             $("#addPurchase").prop("disabled", false);
        },
        error: function (e) {

            $("#addPurchase").prop("disabled", false);

        }
    });

}

function getPurchaseDetails(){
    var purchaseJsonObj = {};
        purchaseJsonObj.quantity = $("#p_quantity").val();
        purchaseJsonObj.purchaseBy = $("#purchasedBy").val();
        purchaseJsonObj.price = $("#price").val();
        purchaseJsonObj.invoice = $("#invoice").val();

        var product = {}
                product.productId = $("#productId").val();
                purchaseJsonObj.productDTO = product;

        return purchaseJsonObj;
}