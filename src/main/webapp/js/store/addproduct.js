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
             window.location.href = "http://localhost/store/smdashboard";
        },
        error: function (e) {

            $("#addProduct").prop("disabled", false);

        }
    });

}

function getProductDetails(){
    var productJsonObj = {};
        productJsonObj.productName = $("#prodName").val();
        productJsonObj.productQuantity = $("#quantity").val();
        productJsonObj.productDescription = $("#p_description").val();
        productJsonObj.vendorName = $("#vendorName").val();
        productJsonObj.productType = $("#p_type").val();
        return productJsonObj;
}

// Form Validation
   $(document).ready(function(){

       var p_err = true;
       var v_err = true;
       var q_err = true;

       $('#valid').css("display","none");

       $('#prodName').keyup(function(){
           prodName();
       });
       function prodName(){
           var prodName = $('#prodName').val();
           var regex = new RegExp(/[a-zA-Z0-9\s]+$/);
           if(regex.test(prodName))
           {
                 $('#valid').hide();
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

            $('#quantity').keyup(function(){
                   quantity();
               });
               function quantity(){
                   var quantity = $('#quantity').val();
                   var regex = new RegExp(/[0-9]+$/);
                   if(regex.test(quantity))
                   {
                        $('#valid').hide();
                        q_err = true;
                        return true;
                   }
                   else
                   {
                       $('#valid').show();
                        $('#valid').html("**Please Enter the valid Quantity");
                        $('#valid').focus();
                        $('#valid').css("color","red");
                        q_err = false;
                        return false;
                   }
               }

               $('#addProduct').click(function(){
                     p_err = true;
                     v_err = true;
                     q_err = true;

                     prodName();
                     vendorName();
                     quantity();
                     $('#valid').css("display","none");
                     if(p_err == true || v_err == true || q_err == true)
                     {
                       return true;
                     }
                     else
                     {
                       return false;
                     }

               });
   });
