
// Save UserSMRole
$(document).ready(function () {


    $("#submitHod").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

    $("#searchHod").click(function(event){
         event.preventDefault();
        fire_ajax_search();
    });

    $("#deleteHodRole").click(function(event){
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

    $("#submitHod").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/auth/hod/saveRole",
        data: {
            facultyDetails:JSON.stringify(getFacultyDetails())
        },
        //contentType: false,
        //cache: false,
        timeout: 600000,
        success: function (data, textStatus, xhr) {

             $("#submitHod").prop("disabled", false);
             
        },
        error: function (e) {

            $("#submitHod").prop("disabled", false);

        }
    });

}

function fire_ajax_delete() {

    $("#deleteHodRole").prop("disable", true);

    $.ajax({

        url: "/auth/hod/deleteHodRole",
        data: {
            facultyDetails:JSON.stringify(getFacultyDetails())
        },
        timeout: 600000,
                success: function (data, textStatus, xhr) {

                     $("#deleteHodRole").prop("disabled", false);
                     window.location.href = "http://localhost/auth/hodincharge";
                },
                error: function (e) {

                    $("#deleteHodRole").prop("disabled", false);
         }
    });

}

function fire_ajax_search() {

    // POST form


    //data.append("CustomField", "This is some extra data, testing");

    $("#searchHod").prop("disabled", true);

    $.ajax({
        url: "/common/store/sm/searchFacultyName",
        data: {
            facultyName:$("#facultyNameHod").val()
        },
        //contentType: false,
        //cache: false,
        timeout: 600000,
        success: function (data, textStatus, xhr) {


             $("#searchHod").prop("disabled", false);
              if(data != "" && data != undefined){
                $("#assign").css("display", "block");
                var d = JSON.parse(data);
                $("#facultyId").val(d.id);
                $("#facultySpan").text($("#facultyNameHod").val());
             }
        },
        error: function (e) {

            $("#searchHod").prop("disabled", false);

        }
    });

}

function getFacultyDetails(){
    var facultyJsonObj = {};
        facultyJsonObj.facultyName = $("#facultyNameHod").val();
        facultyJsonObj.userId = $("#facultyId").val();
        facultyJsonObj.departmentId = $("select.departments").children("option:selected").val();


        return facultyJsonObj;
}

function departmentName(){
            var selectedDepartments = $(this).children("option:selected").val();
            return selectedDepartments;
}



//autocomplete Form

 $( "#facultyNameHod" ).autocomplete({
       source: function( request, response ) {
       $.ajax({
            url: "/common/store/facultyNameAutocomplete",
            data: {
                facultyName:$("#facultyNameHod").val()
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
       $("#facultyNameHod").val(ui.item.label);
       }
    });
