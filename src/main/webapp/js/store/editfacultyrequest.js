    $(document).ready(function(){


        var quant_err = false;
        $('#prod').css("display","none");

        $('#prodQuant').keyup(function(){
            prodQuant();
                });

        function prodQuant()
        {
            var prodQuant = $('#prodQuant').val();
            var regex = new RegExp(/[a-zA-Z\s]+$/);
            if(prodQuant <= 0 || regex.test(prodQuant))
            {
                 $('#prod').show();
                 $('#prod').html("**Please Enter the valid Quantity");
                 $('#prod').focus();
                 $('#prod').css("color","red");
                 quant_err = false;
                 return false;
            }
            else
            {
                $('#prod').css("display","none");
                quant_err = true;
                return true;
            }
        }

        $('#update').click(function(){
            quant_err = true;
            prodQuant();
            if(quant_err == true)
            {
                return ture;
            }
            else
            {
                return false;
            }

        });

    });