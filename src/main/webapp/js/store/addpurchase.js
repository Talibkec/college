$(document).ready(function () {

    $("#addPurchase").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

    $( function() {
                   $( "#purchaseDate" ).datepicker({dateFormat: 'dd/mm/yy'});
        } );

});

function fire_ajax_submit() {

    // Get form
    var form = $('#addPurchaseForm')[0];

    var data = new FormData(form);

    data.append("purchaseDate", $("#purchaseDate").val());

    $("#addPurchase").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/store/addPurchase",
        data: {
            purchaseDetails:JSON.stringify(getPurchaseDetails()),
            purchaseDate:$("#purchaseDate").val()
        },
        //contentType: false,
        //cache: false,
        timeout: 600000,
        success: function (data, textStatus, xhr) {

             $("#addPurchase").prop("disabled", false);
              window.location.replace("/store/smdashboard");
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
        purchaseJsonObj.purchaseDate = $("#purchaseDate").val();
        var product = {}
                product.productId = $("#productId").val();
                purchaseJsonObj.product = product;
        return purchaseJsonObj;
}