
// Save UserSMRole
$(document).ready(function () {

    $("#submit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

    $("#search").click(function(event){
         event.preventDefault();
        fire_ajax_search();
    });

    $("#deleteUserRole").click(function(event){
         event.preventDefault();
        fire_ajax_delete();
        });

});
function divShow()
{
    $("#assign").css("display","block");
}

function fire_ajax_submit() {

    // POST form
    var form = $('#saveRole')[0];

    var data = new FormData(form);

    //data.append("CustomField", "This is some extra data, testing");

    $("#submit").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/auth/saveRole",
        data: {
            facultyDetails:JSON.stringify(getFacultyDetails())
        },
        //contentType: false,
        //cache: false,
        timeout: 600000,
        success: function (data, textStatus, xhr) {

             $("#submit").prop("disabled", false);
             
        },
        error: function (e) {

            $("#submit").prop("disabled", false);

        }
    });

}

function fire_ajax_delete() {

    $("#deleteUserRole").prop("disable", true);

    $.ajax({
        type:"POST",
        url: "/hod/deleteUserRole",
        data: {
            facultyDetails:JSON.stringify(getFacultyDetails())
        },
        timeout: 600000,
                success: function (data, textStatus, xhr) {

                     $("#deleteUserRole").prop("disabled", false);
                     window.location.href = "http://localhost/store/smdashboard";
                },
                error: function (e) {

                    $("#deleteUserRole").prop("disabled", false);
         }
    });

}

function fire_ajax_search() {

    // POST form


    //data.append("CustomField", "This is some extra data, testing");

    $("#search").prop("disabled", true);

    $.ajax({
        url: "/common/store/sm/searchFacultyName",
        data: {
            facultyName:$("#facultyName").val()
        },
        //contentType: false,
        //cache: false,
        timeout: 600000,
        success: function (data, textStatus, xhr) {


             $("#search").prop("disabled", false);
              if(data != "" && data != undefined){
                $("#assign").css("display", "block");
                var d = JSON.parse(data);
                $("#facultyId").val(d.id);
                $("#facultySpan").text($("#facultyName").val());
             }
        },
        error: function (e) {

            $("#search").prop("disabled", false);

        }
    });

}

function getFacultyDetails(){
    var facultyJsonObj = {};
        facultyJsonObj.userId = $("#facultyId").val();


        return facultyJsonObj;
}



//autocomplete Form

 $( "#facultyName" ).autocomplete({
       source: function( request, response ) {
       $.ajax({
            url: "/common/store/facultyNameAutocomplete",
            data: {
                facultyName:$("#facultyName").val()
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
       $("#facultyName").val(ui.item.label);
       }
    });
