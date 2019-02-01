$(document).ready( function(){
    $("#searchProductForm").submit(function(){
        alert("Hello");
        return true;
    });

    $( "#prodName" ).autocomplete({
       source: function( request, response ) {
       $.ajax({
            url: "/store/getProductName",
            data: {
                prodName: $('#prodName').val()
            },
            success: function( data ) {
                response( data );
            },
            error: function (xhr, status) {
                alert(status);
            }
       });
       },
       minLength: 1,
       select: function( event, ui ) {
       event.preventDefault(); //preventing default methods
       $("#prodName").val(ui.item.label);
       }
    });

     $( "#vendorName" ).autocomplete({
           source: function( request, response ) {
           $.ajax({
                url: "/store/getVendorName",
                data: {
                    vendorName: $('#vendorName').val()
                },
                success: function( data ) {
                    response( data );
                },
                error: function (xhr, status) {
                    alert(status);
                }
           });
           },
           minLength: 1,
           select: function( event, ui ) {
           event.preventDefault(); //preventing default methods
           $("#vendorName").val(ui.item.label);
           }
        });
});