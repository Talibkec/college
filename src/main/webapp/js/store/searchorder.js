$(document).ready( function(){

     $( function() {
          $( "#fromDate" ).datepicker({dateFormat: 'dd/mm/yy'});
     } );

      $( function() {
               $( "#toDate" ).datepicker({dateFormat: 'dd/mm/yy'});
      } );

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

     $( "#facultyName" ).autocomplete({
           source: function( request, response ) {
           $.ajax({
                url: "/store/searchFacultyName",
                data: {
                    facultyName: $('#facultyName').val()
                },
                success: function( data ) {
                    response( data );
                },
                error: function (xhr, status) {
                    $("#facultyName").val();
                }
           });
           },
           minLength: 1,
           select: function( event, ui ) {
                event.preventDefault(); //preventing default methods
                $("#facultyName").val(ui.item.label);
           }
        });



});

//code is related to form validation

$(document).ready(function(){

    var p_err = true;
    var f_err = true;
    $('#valid').hide();

    $('#prodName').keyup(function(){
        prodName();
    });
    function prodName(){
        var prodName = $('#prodName').val();
        var regex = new RegExp(/^[a-zA-Z\s]+$/);
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

     $('#facultyName').keyup(function(){
            facultyName();
        });
        function facultyName(){
            var facultyName = $('#facultyName').val();
            var regex = new RegExp(/^[a-zA-Z\s]+$/);
            if(regex.test(facultyName))
            {
                 $('#valid').hide();
                            f_err = true;
                            return true;
            }
            else
            {
                $('#valid').show();
                 $('#valid').html("**Please Enter the valid Quantity");
                 $('#valid').focus();
                 $('#valid').css("color","red");
                 f_err = false;
                 return false;
            }
        }
            $('#searchOrder').click(function(){
                  p_err = true;
                  f_err = true;
                  prodName();
                  facultyName();
                  if(p_err == true || f_err == true)
                  {
                    return true;
                  }
                  else
                  {
                    return false;
                  }
            });
});