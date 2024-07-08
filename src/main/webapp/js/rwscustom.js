
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

		

		//start of the scripts to handle the image slider

		var current = 0;
		var vw = 0;
		var larrowvw = 2;
		var rarrowvw = 92;
		var callIntervalId = 0;
		var slidingImages = $('.imageSlide');
		var ar = 550 /2000;
		var height =  $(window).width() *ar;
		$('.imageSlider').css('height', height + 'px');


		//rest the images

		resetSlidingImages();
		function resetSlidingImages(){
		    for(let i = 1; i < slidingImages.length; i++){
		        slidingImages[i].style.display = 'none';
		    }

		}

		$('.right-image-holder').click(function(e){

		    if (e.originalEvent !== undefined)
            {
                  clearInterval(callIntervalId);
            }
		   if( current < slidingImages.length)
		        slidingImages[current].style.display = 'none';
		   current = (current + 1) % 6;
		   if( current >= slidingImages.length){
		        current = 0;
		   }
		   if( current < slidingImages.length){
		         slidingImages[current].style.display = 'block';

            }

		});
		$('.left-image-holder').click(function(e){
            if (e.originalEvent !== undefined)
             {
                clearInterval(callIntervalId);
             }

             if( current < slidingImages.length)
             		slidingImages[current].style.display = 'none';
             current = current -1;
             if( current < 0 ){
                current = slidingImages.length - 1;
             }
             if( current < slidingImages.length){
             	    slidingImages[current].style.display = 'block';
             }

        });

        callIntervalId = setInterval(function(){$( ".right-image-holder" ).trigger( "click" ); }, 5000);
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
});
