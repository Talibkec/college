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
			duplicated: true,
			pauseOnHover: true
		});
		
		$('.rwsvticker2').easyTicker({
			direction: 'up',
			visible: 1
		});

});