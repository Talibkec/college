$(document).ready(function () {


    var maxField = 10; //Input fields increment limitation
    var addButton = $('.add_key'); //Add button selector
    var wrapper = $('.newPropsClass'); //Input field wrapper
    var fieldHTML = '<div class="newpropkeybox panel panel-success"><input type="text" placeholder="Property Heading"name="propkey"style="margin-top:2%;margin-left:3%" class="propKey control-label col-md-6"/>' +
        '<a href="javascript:void(0);" class="remove_Key btn btn-warning" style="margin-left:5%;margin-top:2%">Delete</a>' +
        '<a href="javascript:void(0);" class="add_key btn btn-primary"style="margin-top:2%;margin-left:1%">Add Property Heading</a>' +
        '<div class="propVals"><input type="text" placeholder="Property Value"name="field_name[]" style="margin-left:3%;margin-top:2%"value="" class="propVal control-label col-md-6"/>' +
        '<a href="javascript:void(0);" class="add_button btn btn-info" style="margin-left:5%;margin-right:1%;margin-top:2%"><span class="glyphicon glyphicon-plus-sign"></span>Add</a>' +
        '<a href="javascript:void(0);" class="remove_button btn btn-danger"style="margin-top:2%"><span class="glyphicon glyphicon-remove-sign"></span>Remove</a></div></div>';
    //New input field html
    var x = 1; //Initial field counter is 1

    //Once add button is clicked
    $(wrapper).on('click', '.add_button', function (e) {
        e.preventDefault();
        $(this).parent('div').append('<div class="propVals"><input type="text"placeholder="Property Value" name="field_name[]" value=""style="margin-left:3%;margin-top:2%" class="propVal control-label col-md-6"/>' +
            '<a href="javascript:void(0);" class="add_button btn btn-info"style="margin-left:5%;margin-top:2%;margin-right:1%"><span class="glyphicon glyphicon-plus-sign"></span>Add</a>' +
            '<a href="javascript:void(0);" class="remove_button btn btn-danger"style="margin-top:2%" ><span class="glyphicon glyphicon-remove-sign"></span>Remove</a></div>'); //Remove field html
        x--; //Decrement field counter
    });

    $(wrapper).on('click', '.add_key', function (e) {
        //Check maximum number of input fields
        if (x < maxField) {
            x++; //Increment field counter
            $(".newpropkeybox").after(fieldHTML); //Add field html
        }

    });

    //Once remove button is clicked
    $(wrapper).on('click', '.remove_button', function (e) {
        e.preventDefault();
        $(this).parent('div').remove(); //Remove field html
        x--; //Decrement field counter
    });

    //Once remove button is clicked
    $(wrapper).on('click', '.remove_Key', function (e) {
        e.preventDefault();
        $(this).parent('div').remove(); //Remove field html
        x--; //Decrement field counter
    });

     $(".oldPropsClass").on('click', '.remove_Key', function (e) {
            e.preventDefault();
            $(this).parent('div').remove(); //Remove field html
            x--; //Decrement field counter
     });

    $(".oldPropsClass").on('click', '.add_button', function (e) {
        e.preventDefault();
        $(this).parent('div').append('<div class="propVals"><input type="text" style="margin-left:3%" name="field_name[]" value="" class="propVal col-md-6"/>' +
            '<a href="javascript:void(0);" class="add_button btn btn-info "style="margin-left:5%;margin-bottom:2%;margin-right:1%"><span class="glyphicon glyphicon-plus-sign"></span>Add</a>' +
            '<a href="javascript:void(0);" class="remove_button btn btn-danger" style="margin-bottom:2%"><span class="glyphicon glyphicon-remove-sign"></span>Remove</a></div>'); //Remove field html
        x--; //Decrement field counter
    });

    $(".oldPropsClass").on('click', '.remove_button', function (e) {
        e.preventDefault();
        $(this).parent('div').remove(); //Remove field html
        x--; //Decrement field counter
    });



    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });



});

function getProps(clazz) {
    newProps = {};
    arr = $(clazz);
    $.each(arr, function (index, item) {
        
        var propKey = $(this).find('input.propKey')[0].value;
        var propVals = $(this).find('input.propVal')
            .map(function () { return $(this).val(); }).get();
        newProps[propKey] = propVals;

    });

    return newProps;
}


function fire_ajax_submit() {

    var newProps = getProps(".newpropkeybox");
    var oldProps = getProps(".oldpropkeybox");

    // Get form
    var form = $('#editDetailsForm')[0];

    var data = new FormData(form);
    data.append("newProps", JSON.stringify(newProps));
    data.append("oldProps", JSON.stringify(oldProps));
    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        contentType: "application/json",
        url: "/uploadfile/editfacultydetails",
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data, textStatus, xhr) {

            //window.location.href = "jsp/login.jsp?a=b&c=d";

            var params = data.split(",");
            $("#hfileLocation").val(params[0]);
            $("#hnoticeHeader").val(params[1]);
            $("#hnoticeType").val(params[2]);
            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);
            history.go(-1);
        },
        error: function (e) {

            $("#result").text(e.responseText);
            $("#msg").css({ "display": "block", "color": "red" });
            $("#msg").html(e.responseText)
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}
