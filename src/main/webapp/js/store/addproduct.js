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
        productJsonObj.productName = $("#p_name").val();
        productJsonObj.productQuantity = $("#p_quantity").val();
        productJsonObj.productDescription = $("#p_description").val();
        productJsonObj.vendorName = $("#v_name").val();
        productJsonObj.productType = $("#p_type").val();
        return productJsonObj;
}

// Form Validation
    $(document).ready(function(){

         var p_err = true;
                var v_err = true;
                var q_err = true;

        $('#pnamecheck').hide();
        $('#vnamecheck').hide();
        $('#pQantitycheck').hide();
        //Productname validatin

        $('#p_name').keyup(function(){
            pName();
        });

        function pName()
        {
            var pName = $('#p_name').val();
            var regex = new RegExp(/^[a-zA-Z\s]+$/);
            if (regex.test(pName))
            {
                $('#pnamecheck').hide();
                p_err = true;
                return true;
            }
            else
            {
                $('#pnamecheck').show();
                $('#pnamecheck').html("**Please Enter the valid Product Name.");
                $('#pnamecheck').focus();
                $('#pnamecheck').css("color","red");
                p_err = false;
                return false;
            }
        }

        $('#p_quantity').keyup(function(){
                    pQuant();
                });

                function pQuant()
                {
                    var pQuant = $('#p_quantity').val();
                    var regex = new RegExp(/[1-9]+$/);
                    if (regex.test(pQuant))
                    {
                        $('#pQuantitycheck').hide();
                        q_err = true;
                        return true;
                    }
                    else
                    {
                        $('#pQuantitycheck').show();
                        $('#pQuantitycheck').html("**Please Enter the valid Quantity.");
                        $('#pQuantitycheck').focus();
                        $('#pQuantitycheck').css("color","red");
                        q_err = false;
                        return false;
                    }
                }

        //Vendor Name validation

        $('#v_name').keyup(function(){

            vName();
        });

        function vName()
        {
            var vName = $('#v_name').val();
            var regex = new RegExp(/[a-zA-Z\s]+$/);
            if (regex.test(vName))
            {
               $('#vnamecheck').hide();
               v_err = true;
               return true;
            }
            else
            {
                $('#vnamecheck').show();
                $('#vnamecheck').html("**Please Enter the valid Vendor Name.");
                $('#vnamecheck').focus();
                $('#vnamecheck').css("color","red");
                v_err = false;
                return false;
            }
        }

        $('#submit').click(function(){
                p_err = true;
                v_err = true;
                q_err = true;

                pName();
                vName();
                pQuant();

                if(p_err == true || v_err == true || q_err == true){
                    return true;
                }
                else{
                    return false;
                }
        });


});