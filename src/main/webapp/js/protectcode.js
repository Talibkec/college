
$(document).ready(function () {
    //Disable full page
    $("body").on("contextmenu",function(e){
        return false;
    });
    
    //Disable part of page
    $("#id").on("contextmenu",function(e){
        return false;
    });
    
    document.onkeydown = function(e) {
            if (e.ctrlKey && 
                (e.keyCode === 67 || 
                 e.keyCode === 86 || 
                 e.keyCode === 85 || 
                 e.keyCode === 117)) {
                return false;
            } else {
                return true;
            }
    };
    $(document).keypress("u",function(e) {
      if(e.ctrlKey)
      {
    return false;
    }
    else
    {
    return true;
    }
    });
});
