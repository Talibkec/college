$(document).ready(function () {
    $( function() {
           $( "#fromDatepicker" ).datepicker({dateFormat: 'dd/mm/yy'});
    } );

    $( function() {
               $( "#toDatepicker" ).datepicker({dateFormat: 'dd/mm/yy'});
    } );
});