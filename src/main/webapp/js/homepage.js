 $(document).ready(function(){
 alert("Hello");
 getUrlParameter();

 });

 function getUrlParameter() {
                 var sPageURL = window.location.search.substring(1),
                     sURLVariables = sPageURL.split('&'),
                     sParameterName,
                     i;

                 for (i = 0; i < sURLVariables.length; i++) {
                     sParameterName = sURLVariables[i].split('=');

                     if (sParameterName[0] === sParam) {
                         //return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
                     }
                 }
             };

             $(function(){
             $( "#datepicker" ).datepicker();
             //Pass the user selected date format
             $( "#format" ).change(function() {
             $( "#datepicker" ).datepicker( "option", "dateFormat", $(this).val() );
             });
             });