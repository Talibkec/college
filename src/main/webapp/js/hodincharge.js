
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
             window.alert(data);
        },
        error: function (e) {

            $("#submitHod").prop("disabled", false);
            window.alert(data);
        }
    });

}

// function fire_ajax_delete() {
//
//     $("#deleteHodRole").prop("disable", true);
//
//     $.ajax({
//
//         url: "/auth/hod/deleteHodRole",
//         data: {
//             facultyDetails:JSON.stringify(getFacultyDetails())
//         },
//         timeout: 600000,
//                 success: function (data, textStatus, xhr) {
//
//                      $("#deleteHodRole").prop("disabled", false);
//                      window.location.href = "/auth/hodincharge";
//                 },
//                 error: function (e) {
//
//                     $("#deleteHodRole").prop("disabled", false);
//          }
//     });
//
// }

function fire_ajax_search() {


    let id = $("#facultyId").val();
    if(id != "" && id != undefined){
        $("#assign").css("display", "block");


        $("#facultySpan").text($("#facultyNameHod").val());
    }


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
            url: "/faculty/facultyNameAutocompletes123",
            data: {
                facultyName:$("#facultyNameHod").val()
            },
            success: function( data ) {
                let list = [];
                // $.each(data, function (index, ele){
                //     let obj = {}
                //     obj.name = ele.facultyName;
                //     obj.id = ele.facultyId;
                //     list.push(obj)
                // })
                response( $.map( data, function( item ) {
                    return {
                        label: item.facultyName,
                        value: item.facultyId
                    }
                }));
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
       $("#facultyId").val(ui.item.value);
       $("#assign").css("display", "block");
       $("#facultySpan").text(ui.item.label);
       }
    });
