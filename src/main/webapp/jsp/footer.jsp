<div class="gt-footersection">
    <footer class="footer container">
        <div class="region region-footer">
            <section id="block-block-3" class="block block-block clearfix">


                <p><img alt="" src="/sites/default/files/logo.png" style="height:90px; width:240px"/>
                </p>

            </section>


            <section id="block-block-2" class="block block-block clearfix">

                <h2 class="block-title">KEC Katiihar</h2>

                <p><span style="font-family:arial,helvetica,sans-serif">Katihar Engineering College, Katihar<br/>
Bheriya Rahika, PO - B.M.P - 07<br/>
Katihar 854106<br/>
Bihar India<br/>
Ph.: 06452-239122</span></p>

            </section>
             <section id="block-block-4" class="block block-block clearfix">

                            <h2 class="block-title">Visitor Counter</h2>

                            <p>Since January, 2019</p>
                            <p><a href="" target="_blank"><img alt="Web Counter"
                                                                                           src=""
                                                                                           title="Web Counter"/></a></p>

                        </section>

        </div>
<section id="block-block-4" class="block block-block clearfix">

                            <h2 class="block-title">&copy 2018 KEC Katihar</h2>

                            <p >Designed & Developed by</p>
                            <p><a href="http://keck.ac.in/category/kecwebteam" target="_blank">KEC Web Team</p></a>
                        </section>

    </footer>

</div>

<script src="http://keck.ac.in/sites/all/themes/bootstrap/js/bootstrap.js"></script>
<script src="http://keck.ac.in/sites/all/themes/bootstrap/js/powr.js"></script>
<script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script><script type="text/javascript" src="http://keck.ac.in/js/protectcode.js"></script> <script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/bootstrap/dist/js/bootstrap.min.js"></script> <script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/waypoints/lib/jquery.waypoints.min.js"></script> <script>$(document).ready(function(){
            $('#homePageSlider').on('slid.bs.carousel', function ( event ) {
                var slide = event.relatedTarget;
                var slideInfoAnimation = $(slide).find('.slideInfo').attr('data-animation');
                $(slide).find('.slideInfo').addClass('animated ' + slideInfoAnimation);
            });
            $('.linkBox > a').on('mouseenter', function(){
                var elem = $(this).find('span.content > span.contentWrapper');
                $(elem).addClass('animated ' + $(elem).attr('data-hover-animation'));
            });
            $('.linkBox > a').on('mouseleave', function(){
                var elem = $(this).find('span.content > span.contentWrapper');
                $(elem).removeClass('animated ' + $(elem).attr('data-hover-animation'));
            });

            //element animation
            $('*[data-animation]').each( function() {
                var osElement = $(this),
                    osAnimationClass = osElement.attr('data-animation'),
                    osAnimationDelay = osElement.attr('data-animation-delay');
                osAnimationRepeat = osElement.attr('data-animation-repeat');

                if(osAnimationClass == "") return;

                osElement.css({
                    '-webkit-animation-delay':              osAnimationDelay,
                    '-moz-animation-delay':                 osAnimationDelay,
                    'animation-delay':                      osAnimationDelay,
                    '-webkit-animation-iteration-count' :   osAnimationRepeat,
                    'animation-iteration-count':            osAnimationRepeat
                });

                osElement.waypoint(function() {
                    osElement.addClass('animated').addClass(osAnimationClass);
                },{
                    triggerOnce: true,
                    offset: '85%'
                });
            });
        });</script> <script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/nivo-lightbox/dist/nivo-lightbox.min.js"></script> <script>var brandTimer = null;
        $(document).ready(function(){
            $('.list-brands1 > li, .list-brands2 > li').each(function(){
                $(this).attr('data-width', $(this).width() + 30);
            });
            window.setTimeout(scrollNews, 4000);
            brandTimer = window.setTimeout(scrollBrands, 4000);
            $('.list-brands1 > li, .list-brands2 > li').on('mouseenter', function(){
                if(brandTimer != null){
                    window.clearTimeout(brandTimer);
                    brandTimer = null;
                }
            });
            $('.list-brands1 > li, .list-brands2 > li').on('mouseleave', function(){
                if(brandTimer == null){
                    brandTimer = window.setTimeout(scrollBrands, 4000);
                }
            });
            $('a[data-lightbox-gallery=photogallery]').nivoLightbox({effect: 'fadeScale'});

            //ticker
            var listUpdatesWidth = 0;
            $('#list-updates li').each(function(i, e){
                listUpdatesWidth += ($(e).outerWidth(true) + 5);
            });
            $('#list-updates').width(listUpdatesWidth);
            $('#list-updates').css({marginLeft: $('#ticker-wrapper').width()});
            tickerTimer = window.setInterval(moveTicker, 40);

            $('#ticker-wrapper, #list-updates').mouseenter(function(){
                if(tickerTimer != null){
                    window.clearInterval(tickerTimer);
                    tickerTimer = null;
                }
            });
            $('#ticker-wrapper, #list-updates').mouseout(function(){
                if(tickerTimer == null){
                    tickerTimer = window.setInterval(moveTicker, 20);
                }
            });
        });
        function scrollNews(){
            var firstNews = $('.listUpdates > li').eq(0);
            $(firstNews).slideUp(function(){
                $(firstNews).appendTo('.listUpdates');
                $(firstNews).slideDown();
            });
            window.setTimeout(scrollNews, 4000);
        }
        function scrollBrands(){
            brandTimer = null;
            var firstBrand1 = $('.list-brands1 > li').eq(0);
            var firstBrand2 = $('.list-brands2 > li').eq(0);
            $(firstBrand1).one('animationend oAnimationEnd mozAnimationEnd webkitAnimationEnd', function(){
                $(firstBrand1).removeClass('animated bounceOutLeft');
                $(firstBrand1).appendTo('.list-brands1');
            });
            $(firstBrand1).addClass('animated bounceOutLeft');
            $(firstBrand2).one('animationend oAnimationEnd mozAnimationEnd webkitAnimationEnd', function(){
                $(firstBrand2).removeClass('animated bounceOutLeft');
                $(firstBrand2).appendTo('.list-brands2');
            });
            $(firstBrand2).addClass('animated bounceOutLeft');
            brandTimer = window.setTimeout(scrollBrands, 4000);
        }
        function moveTicker(){
            if($('#ticker-wrapper').length == 0) return;
            var marginLeft = parseInt($('#list-updates').css('margin-left').replace(/[^-\d\.]/g, ''));
            var tickerOffset = ($('#ticker-wrapper').width() - 25);
            var remainingWidth = ($('#list-updates').width() + marginLeft +  tickerOffset);
            if($('#ticker-wrapper').width() >= remainingWidth){
                $('#list-updates').css({marginLeft: $('#ticker-wrapper').width()});
            }else{
                marginLeft += -1;
                $('#list-updates').css({marginLeft: marginLeft});
            }
        }
</script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
 <script type='text/javascript' src="wp-includes/js/wp-embed.min.js?ver=4.9.8"></script>
<script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/jquery.cookie/jquery.cookie.js"></script>
 <script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/waypoints/lib/shortcuts/sticky.min.js">
</script>
 <script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/bootstrap-sweetalert/dist/sweetalert.min.js"></script>
<script>$(document).ready(function(){
        new Waypoint.Sticky({
            element: $('.navbar.navbar-default')[0]
        });
        $('#modal-inquiry-form').on('hidden.bs.modal', function(){
            $.cookie('hide-inquiry-form', '1', {path: '/'});
        });
    });
    function changeTheme(theme){
        $('#theme').attr('href', 'changetheme/css/' + theme);
        $.cookie('selected-theme', theme, {path: '/', expires: 30});
    }
    function doInquiry(form){
        $(form).find('button[type="submit"]').button('loading');
        $.post(
            'https://#/wp-admin/admin-ajax.php',
            $(form).serialize(),
            function(d){
                if(d.error){
                    swal('Alert!', d.errorMessage, 'error');
                }else if(d.success){
                    swal('Inquiry Submitted!', 'Thanks for contacting us.', 'success');
                    $('#modal-inquiry-form').modal('hide');
                }
            },
            'json'
        ).always(function(){
            $(form).find('button[type="submit"]').button('reset');
        });
        return false;
    }
</script>



</body>
</html>
