jQuery(window).load(function($) {
		// Animate loader off screen
	//$(".rws-pageloader").fadeOut("slow");
});

jQuery(document).ready(function($){
		/*$(".dropdown").hover(
			function() { $('.dropdown-menu', this).fadeIn("fast");
			},
			function() { $('.dropdown-menu', this).fadeOut("fast");
		});*/

		$('.dropdown').hover(function() {
		$(this).find('.dropdown-menu').first().stop(true, true).delay(250).slideDown();
		}, function() {
		$(this).find('.dropdown-menu').first().stop(true, true).delay(100).slideUp();

		});

		$('.gtslidingeffect h2').click(function() {
			$('.gtaccordin').hide();
			$(this).next('.gtaccordin').show();
		});

		$("a.lightbox-processed").tooltip();

		$('#block-block-4 div a').click(function(e) {
			e.preventdefault();
		});



		$('body').fadeIn(2000);

		/*$('.rwsvticker1').easyTicker({
			direction: 'up',
			visible: 3
		});*/

		$('.rwsvticker1').marquee({
			direction: 'up',
			speed: 20,
			duplicated: false,
			pauseOnHover: true
		});

		$('.rwsvticker2').easyTicker({
			direction: 'up',
			visible: 1
		});

		//start of the scripts to handle the image slider

		var current = 0;
		var vw = 0;
		var larrowvw = 2;
		var rarrowvw = 92;
		var callIntervalId = 0;
		var slidingImages = $('.imageSlide');

		//rest the images

		function resetSlidingImages(){
		    for(let i = 0; i < slidingImages.length; i++){
		        slidingImages[i].style.display = 'none';
		    }

		}

		$('.right-image-holder').click(function(e){

		   if (e.originalEvent !== undefined)
            {
                  clearInterval(callIntervalId);
            }

		    vw = vw + 100;
		    if( vw >= 600){
		       vw = 0;
               larrowvw = 2;
               rarrowvw = 92;
		    }
		    else{
		        larrowvw = larrowvw + 100;
		        rarrowvw = rarrowvw + 100;
		    }
		    /*$('.imageSlider').css("right", vw + 'vw' );*/
		    $(".imageSlider").animate({"right": vw + 'vw'}, "slow");
		    $('.right-image-holder').css("left", rarrowvw + 'vw');
		    $('.left-image-holder').css("left", larrowvw + 'vw');

		});
		$('.left-image-holder').click(function(e){
            if (e.originalEvent !== undefined)
             {
                clearInterval(callIntervalId);
             }

             vw = vw - 100;
             if(vw < 0){
                vw = 600 - 100;
                larrowvw = larrowvw + vw;
                rarrowvw = rarrowvw + vw;
             }
             else{
                larrowvw = larrowvw - 100;
                rarrowvw = rarrowvw - 100;
             }
           /* $('.imageSlider').css("right", vw + 'vw');*/
            $(".imageSlider").animate({"right": vw + 'vw'}, "slow");
            $('.left-image-holder').css("left", larrowvw + 'vw');
            $('.right-image-holder').css("left", rarrowvw + 'vw');
        });

        callIntervalId = setInterval(function(){$( ".right-image-holder" ).trigger( "click" ); }, 5000);

});