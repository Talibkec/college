$(document).ready( function(){
    $("#searchProductForm").submit(function(){

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

//validation
$(document).ready(function(){

    var p_err = true;
    var v_err = true;
    var pr_err = true;

    $('#valid').css("display","none");

    $('#prodName').keyup(function(){
        prodName();
    });
    function prodName(){
        var prodName = $('#prodName').val();
        var regex = new RegExp(/[a-zA-Z0-9\s]+$/);
        if(regex.test(prodName))
        {
             $('#valid').css("display","none");
                        p_err = true;
                        return true;

        }
        else
        {
            $('#valid').show();
             $('#valid').html("**Please Enter the valid Quantity");
             $('#valid').focus();
             $('#valid').css("color","red");
             p_err = false;
             return false;
        }
    }

     $('#vendorName').keyup(function(){
            vendorName();
        });
        function vendorName(){
            var vendorName = $('#vendorName').val();
            var regex = new RegExp(/[a-zA-Z0-9\s]+$/);
            if(regex.test(vendorName))
            {
                 $('#valid').css("display","none");
                            v_err = true;
                            return true;
            }
            else
            {
                $('#valid').show();
                 $('#valid').html("**Please Enter the valid Quantity");
                 $('#valid').focus();
                 $('#valid').css("color","red");
                 v_err = false;
                 return false;
            }
        }

         $('#productId').keyup(function(){
                productId();
            });
            function productId(){
                var productId = $('#productId').val();
                var regex = new RegExp(/[0-9]+$/);
                if(regex.test(productId))
                {
                     $('#valid').css("display","none");
                     pr_err = true;
                     return true;
                }
                else
                {
                    $('#valid').show();
                     $('#valid').html("**Please Enter the valid Quantity");
                     $('#valid').focus();
                     $('#valid').css("color","red");
                     pr_err = false;
                     return false;
                }
            }

            $('#getProductDetails').click(function(){
                  p_err = true;
                  v_err = true;
                  pr_err = true;

                  prodName();
                  vendorName();
                  productId();
                  $('#valid').css("display","none");
                  if(p_err == true || v_err == true || pr_err == true)
                  {
                    return true;
                  }
                  else
                  {
                    return false;
                  }

            });
});