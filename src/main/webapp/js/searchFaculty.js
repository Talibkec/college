
// Save UserSMRole
$(document).ready(function () {


    $("#search").click(function(event){
         event.preventDefault();
        fire_ajax_search();
    });


});
function divShow()
{
    $("#assign").css("display","block");
}

function fire_ajax_search() {

    // POST form

    //data.append("CustomField", "This is some extra data, testing");

    $("#search").prop("disabled", true);
    $("#tempid").show();// show message
    $.ajax({
        url: "/faculty/searchFacultyNames",
        data: {
            facultyName:$("#facultyNames").val()
        },

        //contentType: false,
        cache: true,
        timeout: 600000,
        success: function (data, textStatus, xhr) {

             $("#search").prop("disabled", false);
              if(data != "" && data != undefined){

                $("#assign").css("display", "block");
                var d = JSON.parse(data);
                var facultyId = d.id;
                $("#facultyId").val(d.id);
                $("#facultySpan").text($("#facultyNames").val());

                window.location.href="http://localhost/facultyDetails?facultyId="+facultyId;

             }
        },
        error: function (e) {

            $("#search").prop("disabled", false);

        }
    });

}



//autocomplete Form
 $( "#facultyNames" ).autocomplete({
       source: function( request, response ) {
       $.ajax({
            url: "/faculty/facultyNameAutocompletes",
            data: {
                facultyName:$("#facultyNames").val()
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
       $("#facultyNames").val(ui.item.label);
       },
      
       
    });
