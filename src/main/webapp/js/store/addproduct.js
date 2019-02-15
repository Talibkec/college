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

        $('#pnamecheck').hide();
        $('#vnamecheck').hide();

        var pname_err = true;
        var vname_err = true;

        //Productname validatin

        $('#p_name').keyup(function(){

            pname_check();
        });

        function pname_check()
        {
            var pname_val = $('#p_name').val();
            var regex = new RegExp(/^[a-zA-Z\s]+$/);
            if (regex.test(pname_val))
            {
                $('#pnamecheck').hide();
            }
            else
            {
                $('#pnamecheck').show();
                $('#pnamecheck').html("**Please Enter the valid Product Name.");
                $('#pnamecheck').focus();
                $('#pnamecheck').css("color","red");
                pname_err = false;
                return false;
            }

            if ((pname_val.length < 3) || (pname_val.length > 10))
            {
                $('#pnamecheck').show();
                $('#pnamecheck').html("**Enter the length of Name between 3 and 10.");
                $('#pnamecheck').focus();
                $('#pnamecheck').css("color","red");
                pname_err = false;
                return false;
            }
            else
            {
                $('#pnamecheck').hide();
            }
        }

        //Vendor Name validation

        $('#v_name').keyup(function(){

            vname_check();
        });

        function vname_check()
        {
            var vname_val = $('#v_name').val();
            var regex = new RegExp(/^[a-zA-Z\s]+$/);
            if (regex.test(vname_val))
            {
               $('#vnamecheck').hide();
            }
            else
            {
                $('#vnamecheck').show();
                $('#vnamecheck').html("**Please Enter the valid Vendor Name.");
                $('#vnamecheck').focus();
                $('#vnamecheck').css("color","red");
                vname_err = false;
                return false;
            }

            if ((pname_val.length < 3) || (pname_val.length > 10))
                        {
                            $('#pnamecheck').show();
                            $('#pnamecheck').html("**Enter the length of Name between 3 and 10.");
                            $('#pnamecheck').focus();
                            $('#pnamecheck').css("color","red");
                            pname_err = false;
                            return false;
                        }
                        else
                        {
                            $('#pnamecheck').hide();
                        }
        }

        $('#addProduct').click(function(){
                var pname_err = true;
                var vname_err = true;
                pname_check();
                vname_check();

                if((pname_err == true) && (vname_err == true)){
                    return true;
                }
                else{
                    return false;
                }
        });


    });