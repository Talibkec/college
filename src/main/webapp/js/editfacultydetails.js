$(document).ready(function () {


    var maxField = 10; //Input fields increment limitation
    var addButton = $('.add_key'); //Add button selector
    var wrapper = $('.newPropsClass'); //Input field wrapper
    var fieldHTML = '<div class="newpropkeybox panel panel-success">' +
        '<input type="text" placeholder="Property Heading"name="propkey"style="margin-top:2%;margin-left:3%" class="propKey control-label col-md-6"/>' +
        '<a href="javascript:void(0);" class="remove_Key btn btn-danger"style="width:8%;margin-left:5%;margin-top:2%;margin-right:1%">Delete </a>' +
        '<a href="javascript:void(0);" class="add_key btn btn-success "style="margin-top:2%;WIDTH:9%">Add More</a>' +
        '<div class="propVals"><input type="text" placeholder="Property Value"name="field_name" style="margin-left:3%;margin-top:2%"value="" class="propVal control-label col-md-6"/>' +
        '<a href="javascript:void(0);" class="add_button btn btn-success" style="width:8%;margin-left:5%;margin-top:2%"><span class="glyphicon glyphicon-plus-sign"></span></a>' +
        '<a href="javascript:void(0);" class="remove_button btn btn-danger"style="width:9.5%;margin-left:1%;margin-top:2%;margin-left:0.5%"><span class="glyphicon glyphicon-remove-sign"></span></a></div></div>';
    //New input field html
    var x = 1; //Initial field counter is 1

    //Once add button is clicked
    $(wrapper).on('click', '.add_button', function (e) {
        e.preventDefault();
        $(this).parent('div').last().after('<div class="propVals"><input type="text"placeholder="Property Value" name="field_name" value=""style="margin-left:3%;margin-top:2%" class="propVal control-label col-md-6"/>' +
            '<a href="javascript:void(0);" class="add_button btn btn-success"style="width:8%;margin-left:5%;margin-top:2%;margin-right:1%"><span class="glyphicon glyphicon-plus-sign"></span></a>' +
            '<a href="javascript:void(0);" class="remove_button btn btn-danger "style="width:9%;margin-top:2%;margin-left:0.5%"><span class="glyphicon glyphicon-remove-sign"></span></a>'); //Remove field html
        x--; //Decrement field counter
    });

    $(wrapper).on('click', '.add_key', function (e) {
        //Check maximum number of input fields
        if (x < maxField) {
            x++; //Increment field counter
            $(".newpropkeybox").last().after(fieldHTML); //Add field html
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

        $("#addNewProp").css('display', 'block')


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
        $(this).parent('div').last().after('<div class="propVals"><input type="text" style="margin-left:3%" name="field_name" value="" class="propVal col-md-6"/>' +
            '<a href="javascript:void(0);" class="add_button btn btn-success "id="add_button"style ="width:8.5%;margin-left:5%;margin-bottom:2%;"><span class="glyphicon glyphicon-plus-sign"></span></a>' +
            '<a href="javascript:void(0);" class="remove_button btn btn-danger "style="width:8%;margin-bottom:2%;margin-left:2%"><span class="glyphicon glyphicon-remove-sign"></span></a>'); //Remove field html
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

    $("#addNewProp").on('click', function (e) {
        e.preventDefault();
        if ($(".newpropkeybox").children().length == 0) {
            $(".newpropkeybox").after(fieldHTML);
            $("#addNewProp").css('display', 'none')
        }
        else {
            $(".newpropkeybox").css('display', 'block')
            $("#addNewProp").css('display', 'none')
        }
    });


});

function getProps(clazz, keysOrder, facultyKeyProps, lastKeySeqNo) {
    newProps = {};
    arr = $(clazz);
    $.each(arr, function (index, item) {

        if ($(this).find('input.propKey')[0] != undefined && $(this).find('input.propKey')[0] != null) {

            var propKey = $(this).find('input.propKey')[0].value;
            if ($(this).find('input.propKey')[1] != undefined && $(this).find('input.propKey')[1] != null) {
                var propKeyId = $(this).find('input.propKey')[1].value;
            }
            else {
                var propKeyId = -9999;
            }
            lastKeySeqNo = lastKeySeqNo + 1;
            var propKeyOrder = lastKeySeqNo;

            keysOrder.push(propKey);
            var propKeyObject = {};
            var propValsArray = $(this).find('.propVals');
            var valueArray = []
            var valueKeySequenceNo = 0;
            $.each(propValsArray, function (index, item) {

                if ($(this).find('input.propVal')[0] != undefined && $(this).find('input.propVal')[0] != null) {

                    var valObject = {}
                    var valueKey = $(this).find('input.propVal')[0].value;
                    if ($(this).find('input.propVal')[1] != undefined && $(this).find('input.propVal')[1] != null) {
                        var valueId = $(this).find('input.propVal')[1].value;
                    }
                    else{
                        var valueId = -9999;
                    }

                     valueKeySequenceNo = valueKeySequenceNo + 1;
                     var valueOrder = valueKeySequenceNo;

                    valObject.keyPropVal = valueKey;
                    valObject.keyPropValuesId = valueId;
                    valObject.keyValueOrder = valueOrder;
                    valueArray.push(valObject);
                }

            });
            propKeyObject.keyPropertyName = propKey;
            propKeyObject.keyPropertyId = propKeyId;
            propKeyObject.keyPropsOrder = propKeyOrder;
            propKeyObject.keyPropVals = valueArray;
            facultyKeyProps.push(propKeyObject);

        }

    });

    return newProps;
}


function fire_ajax_submit() {

    var oldKeyOrder = [];
    var newKeyOrder = [];
    var lastKeySeqNo = 0;
    //Below order matters here.
    var facultyKeyProps = [];
    var facultyDTO = {};
    var oldProps = getProps(".oldpropkeybox", oldKeyOrder, facultyKeyProps, lastKeySeqNo);
    var newProps = getProps(".newpropkeybox", newKeyOrder, facultyKeyProps, lastKeySeqNo);
    facultyDTO.facultyKeyProps = facultyKeyProps;
    facultyDTO.facultyId = -9999;
    facultyDTO.facultyName = "";
    facultyDTO.departmentId = -9999;
    facultyDTO.facultyMobNo = -9999;
    facultyDTO.facultyOfficialEmail = "";
    facultyDTO.facultyPersonalEmail = "";
    facultyDTO.imageURL = "";
    facultyDTO.facultyPhoto = [];
    facultyDTO.user = {};
    facultyDTO.fileType = "";
    // Get form
    var form = $('#editDetailsForm')[0];

    var data = new FormData(form);
    data.append("newProps", JSON.stringify(newProps));
    data.append("oldProps", JSON.stringify(oldProps));
    data.append("oldKeyOrder", oldKeyOrder);
    data.append("newKeyOrder", newKeyOrder);
    data.append("facultyDetails",  JSON.stringify(facultyDTO));
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
