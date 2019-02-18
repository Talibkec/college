$(document).ready(function(){
        $.each($(".requests"), function(index){
            var editId = '#' + 'editId' + (index + 1);
            var statusId = '#' + 'statusId' + (index + 1);
            if("Approved" == $(statusId).text()){
                $(editId).css('display','none');
            }
        })
});