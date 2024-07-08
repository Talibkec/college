var serverContext = '';
$(document).ready(function () {
    $('form').submit(function(event) {
        savePass(event);
    });

    $(":password").keyup(function(){
        if($("#password").val() != $("#matchPassword").val()){
            $("#globalError").show().html(/*[[#{PasswordMatches.user}]]*/);
        }else{
            $("#globalError").html("").hide();
        }
    });
});

function savePass(event){
    event.preventDefault();
    if($("#password").val() != $("#matchPassword").val()){
        $("#globalError").show().html(/*[[#{PasswordMatches.user}]]*/);
        return;
    }
    var formData= $('form').serialize();
    $.post(serverContext + "user/savePassword",formData ,function(data){

        $("#globalError").show().html(data.message);
        if(data.error == null){
            window.location.href = serverContext + "/login?lang=en?message=" + data.message;
        }
    })
    .fail(function(data) {
        if(data.responseJSON.error.indexOf("InternalError") > -1){
            window.location.href = serverContext + "/updatePassword.jsp?lang=en?message=" + data.responseJSON.message;
        }
        else{
            var errors = $.parseJSON(data.responseJSON.message);
            $.each( errors, function( index,item ){
                $("#globalError").show().html(item.defaultMessage);
            });
            errors = $.parseJSON(data.responseJSON.error);
            $.each( errors, function( index,item ){
                $("#globalError").show().append(item.defaultMessage+"<br/>");
            });
        }
    });
}