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