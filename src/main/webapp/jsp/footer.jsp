<div class="gt-footersection">
    <footer class="footer container">
        <div class="region region-footer">
            <section id="block-block-3" class="block block-block clearfix">


                <p><img alt="" src="http://localhost/images/logo.png" style="height:90px; width:240px"/>
                </p>

            </section>


            <section id="block-block-2" class="block block-block clearfix">

                <h2 class="block-title">KEC Katihar</h2>

                <p><span style="font-family:arial,helvetica,sans-serif">Katihar Engineering College, Katihar<br/>
Hirdayganj<br/>
Hajipur 854109<br/>
Bihar India<br/>
Ph.: 06452-239122</span></p>

            </section>
            <section id="block-block-4" class="block block-block clearfix">

                <div class="googlemap"><a
                        href="https://www.google.co.in/maps/place/KATIHAR+ENGINEERING+COLLEGE+(NEW+CAMPUS)/@25.5687892,87.5372664,17z/data=!3m1!4b1!4m5!3m4!1s0x39f0015cd2753a37:0x599f1653bdd10604!8m2!3d25.5687844!4d87.5394551?hl=en"
                        target="_blank">
                    <i class="fa  fa-globe" aria-hidden="true" style="font-size:300%;margin-left:5%"></i></a>
                    <span></span>

                </div>

                <a href="/legal/contactus"><p class="block-title">Contact Us</p></a>
                <a href="/legal/privacypolicy"><p class="block-title">Privacy Policy</p></a>
                <a href="/legal/productandservices"><p class="block-title">Product and Services</p></a>
                <a href="/legal/termandcondition"><p class="block-title">Terms and Conditions</p></a>
                <a href="/legal/refundandcancellation"><p class="block-title">Refund/Cancellation Policy</p></a>
               <p>Since January, 2019</p>

            </section>

        </div>
        <section id="block-block-4" class="block block-block clearfix">

            <h2 class="block-title">&copy 2018 KEC Katihar</h2>

            <p>Designed & Developed by</p>
            <p><a href="http://localhost/category/kecwebteam" target="_blank">KEC Web Team</p></a>
        </section>
    </footer>

</div>

<!--<script type="text/javascript" src="http://localhost/js/protectcode.js"></script>-->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.min.js"
        integrity="sha512-CEiA+78TpP9KAIPzqBvxUv8hy41jyI3f2uHi7DGp/Y/Ka973qgSdybNegWFciqh6GrN2UePx2KkflnQUbUhNIA=="
        crossorigin="anonymous"></script>
<script>$(document).ready(function () {
    $('#homePageSlider').on('slid.bs.carousel', function (event) {
        var slide = event.relatedTarget;
        var slideInfoAnimation = $(slide).find('.slideInfo').attr('data-animation');
        $(slide).find('.slideInfo').addClass('animated ' + slideInfoAnimation);
    });
    $('.linkBox > a').on('mouseenter', function () {
        var elem = $(this).find('span.content > span.contentWrapper');
        $(elem).addClass('animated ' + $(elem).attr('data-hover-animation'));
    });
    $('.linkBox > a').on('mouseleave', function () {
        var elem = $(this).find('span.content > span.contentWrapper');
        $(elem).removeClass('animated ' + $(elem).attr('data-hover-animation'));
    });

    //element animation
    $('*[data-animation]').each(function () {
        var osElement = $(this),
            osAnimationClass = osElement.attr('data-animation'),
            osAnimationDelay = osElement.attr('data-animation-delay');
        osAnimationRepeat = osElement.attr('data-animation-repeat');

        if (osAnimationClass == "") return;

        osElement.css({
            '-webkit-animation-delay': osAnimationDelay,
            '-moz-animation-delay': osAnimationDelay,
            'animation-delay': osAnimationDelay,
            '-webkit-animation-iteration-count': osAnimationRepeat,
            'animation-iteration-count': osAnimationRepeat
        });

        osElement.waypoint(function () {
            osElement.addClass('animated').addClass(osAnimationClass);
        }, {
            triggerOnce: true,
            offset: '85%'
        });
    });
});

</script>
<script src="http://localhost/css/nivo-lightbox/dist/nivo-lightbox.min.js"></script>
<script>var brandTimer = null;
$(document).ready(function () {
    $('.list-brands1 > li, .list-brands2 > li').each(function () {
        $(this).attr('data-width', $(this).width() + 30);
    });
    window.setTimeout(scrollNews, 4000);
    brandTimer = window.setTimeout(scrollBrands, 4000);
    $('.list-brands1 > li, .list-brands2 > li').on('mouseenter', function () {
        if (brandTimer != null) {
            window.clearTimeout(brandTimer);
            brandTimer = null;
        }
    });
    $('.list-brands1 > li, .list-brands2 > li').on('mouseleave', function () {
        if (brandTimer == null) {
            brandTimer = window.setTimeout(scrollBrands, 4000);
        }
    });
    $('a[data-lightbox-gallery=photogallery]').nivoLightbox({effect: 'fadeScale'});

    //ticker
    var listUpdatesWidth = 0;
    $('#list-updates li').each(function (i, e) {
        listUpdatesWidth += ($(e).outerWidth(true) + 5);
    });
    $('#list-updates').width(listUpdatesWidth);
    $('#list-updates').css({marginLeft: $('#ticker-wrapper').width()});
    tickerTimer = window.setInterval(moveTicker, 40);

    $('#ticker-wrapper, #list-updates').mouseenter(function () {
        if (tickerTimer != null) {
            window.clearInterval(tickerTimer);
            tickerTimer = null;
        }
    });
    $('#ticker-wrapper, #list-updates').mouseout(function () {
        if (tickerTimer == null) {
            tickerTimer = window.setInterval(moveTicker, 20);
        }
    });
});

function scrollNews() {
    var firstNews = $('.listUpdates > li').eq(0);
    $(firstNews).slideUp(function () {
        $(firstNews).appendTo('.listUpdates');
        $(firstNews).slideDown();
    });
    window.setTimeout(scrollNews, 4000);
}

function scrollBrands() {
    brandTimer = null;
    var firstBrand1 = $('.list-brands1 > li').eq(0);
    var firstBrand2 = $('.list-brands2 > li').eq(0);
    $(firstBrand1).one('animationend oAnimationEnd mozAnimationEnd webkitAnimationEnd', function () {
        $(firstBrand1).removeClass('animated bounceOutLeft');
        $(firstBrand1).appendTo('.list-brands1');
    });
    $(firstBrand1).addClass('animated bounceOutLeft');
    $(firstBrand2).one('animationend oAnimationEnd mozAnimationEnd webkitAnimationEnd', function () {
        $(firstBrand2).removeClass('animated bounceOutLeft');
        $(firstBrand2).appendTo('.list-brands2');
    });
    $(firstBrand2).addClass('animated bounceOutLeft');
    brandTimer = window.setTimeout(scrollBrands, 4000);
}

function moveTicker() {
    if ($('#ticker-wrapper').length == 0) return;
    var marginLeft = parseInt($('#list-updates').css('margin-left').replace(/[^-\d\.]/g, ''));
    var tickerOffset = ($('#ticker-wrapper').width() - 25);
    var remainingWidth = ($('#list-updates').width() + marginLeft + tickerOffset);
    if ($('#ticker-wrapper').width() >= remainingWidth) {
        $('#list-updates').css({marginLeft: $('#ticker-wrapper').width()});
    } else {
        marginLeft += -1;
        $('#list-updates').css({marginLeft: marginLeft});
    }
}


</script>
<script src="https://apis.google.com/js/platform.js" async defer></script>

<script src="http://localhost/css//jquery.cookie/jquery.cookie.js"></script>
<script src="http://localhost/css//waypoints/lib/shortcuts/sticky.min.js">
</script>
<script src="http://localhost/css/bootstrap-sweetalert/dist/sweetalert.min.js"></script>
<style>


    @import url("https://cdnjs.cloudflare.com/ajax/libs/superfish/1.7.10/js/superfish.js");


</style>
<link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css"
      media="all"/>
<link type="text/css" rel="stylesheet" href="http://localhost/css/superfish/css/superfish.css" media="all"/>
<link type="text/css" rel="stylesheet" href="http://localhost/css/style.css" media="all"/>
<style>


    @import url("https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css");


</style>
<!-- HTML5 element support for IE6-8 -->
<!--[if lt IE 9]>
<script src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv-printshiv.min.js"></script>
<![endif]-->
<script type="text/javascript" src="http://localhost/css/contrib/seckit/js/seckit.document_write.js"></script>
<link type="text/css" rel="stylesheet" id="seckit-clickjacking-no-body" media="all"
      href="http://localhost/css/contrib/seckit/css/seckit.no_body.css"/>
<!-- stop SecKit protection -->

<!-- Jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://localhost/js/jquery.once.js"></script>
<script src="http://localhost/js/drupal.js"></script>
<script src="http://localhost/css/superfish/sftouchscreen.js"></script>
<script src="http://localhost/css/superfish/sfsmallscreen.js"></script>
<script src="http://localhost/css/superfish/supposition.js"></script>
<script src="http://localhost/css/superfish/superfish.js"></script>
<script src="http://localhost/css/superfish/supersubs.js"></script>
<script src="http://localhost/css//superfish/modules/superfish/superfish.js"></script>
<script src="http://localhost/js/jquery-ui.min.js"></script>
<script src="http://localhost/js/rwscustom.js"></script>
<script src="http://localhost/js/jquery-migrate-1.3.0.min.js"></script>
<script async src="http://localhost/js/jquery.easy-ticker.js"></script>
<script async src="http://localhost/js/jquery.marquee.min.js"></script>
<!--<script type="text/javascript" src="http://localhost/js/protectcode.js"></script>-->

<script src="js/main.js"></script>


<!--Jquery-->
<script>
    jQuery.extend(Drupal.settings, {
        "basePath": "\/",
        "pat  hPrefix": "",
        "ajaxPageState": {
            "theme": "rwsiitj", "theme_token": "y8fEiiqSZ0NPyQGr01dhhsl9D2bQDDisb_lupW_NGCQ", "css": {
                "modules\/system\/system.base.css": 1,
                "sites\/all\/modules\/contrib\/views_slideshow\/views_slideshow.css": 1,
                "sites\/all\/modules\/date\/date_api\/date.css": 1,
                "sites\/all\/modules\/date\/date_popup\/themes\/datepicker.1.7.css": 1,
                "modules\/field\/theme\/field.css": 1,
                "modules\/node\/node.css": 1,
                "sites\/all\/modules\/contrib\/views\/css\/views.css": 1,
                "sites\/all\/modules\/contrib\/ckeditor\/css\/ckeditor.css": 1,
                "sites\/all\/modules\/contrib\/ctools\/css\/ctools.css": 1,
                "sites\/all\/modules\/contrib\/views_slideshow\/views_slideshow_controls_text.css": 1,
                "sites\/all\/modules\/contrib\/views_slideshow\/contrib\/views_slideshow_cycle\/views_slideshow_cycle.css": 1,
                "sites\/all\/modules\/contrib\/jcarousel\/skins\/default\/jcarousel-default.css": 1,
                "sites\/all\/libraries\/superfish\/css\/superfish.css": 1,
                "https:\/\/maxcdn.bootstrapcdn.com\/bootstrap\/3.3.7\/css\/bootstrap.css": 1,
                "sites\/all\/themes\/bootstrap\/css\/3.3.5\/overrides.min.css": 1,
                "sites\/all\/themes\/rwsiitj\/css\/style.css": 1,
                "sites\/all\/themes\/rwsiitj\/js\/jqueryui\/jquery-ui.min.css": 1,
                "sites\/all\/themes\/rwsiitj\/font-awesome\/css\/font-awesome.min.css": 1
            }, "js": {
                "sites\/all\/themes\/bootstrap\/js\/bootstrap.js": 1,
                "\/\/ajax.googleapis.com\/ajax\/libs\/jquery\/1.9.1\/jquery.js": 1,
                "0": 1,
                "misc\/jquery.once.js": 1,
                "misc\/drupal.js": 1,
                "sites\/all\/modules\/contrib\/views_slideshow\/js\/views_slideshow.js": 1,
                "https:\/\/maxcdn.bootstrapcdn.com\/bootstrap\/3.3.7\/js\/bootstrap.js": 1,
                "1": 1,
                "sites\/all\/libraries\/jquery.cycle\/jquery.cycle.all.js": 1,
                "sites\/all\/modules\/contrib\/views_slideshow\/contrib\/views_slideshow_cycle\/js\/views_slideshow_cycle.js": 1,
                "sites\/all\/modules\/contrib\/jcarousel\/js\/jcarousel.js": 1,
                "sites\/all\/modules\/contrib\/jcarousel\/js\/jquery.jcarousel.min.js": 1,
                "sites\/all\/libraries\/superfish\/jquery.hoverIntent.minified.js": 1,
                "sites\/all\/libraries\/superfish\/sftouchscreen.js": 1,
                "sites\/all\/libraries\/superfish\/sfsmallscreen.js": 1,
                "sites\/all\/libraries\/superfish\/supposition.js": 1,
                "sites\/all\/libraries\/superfish\/superfish.js": 1,
                "sites\/all\/libraries\/superfish\/supersubs.js": 1,
                "sites\/all\/modules\/superfish\/superfish.js": 1,
                "sites\/all\/themes\/rwsiitj\/js\/jquery-migrate-1.3.0.min.js": 1,
                "sites\/all\/themes\/rwsiitj\/js\/jquery.easy-ticker.js": 1,
                "sites\/all\/themes\/rwsiitj\/js\/jquery.marquee.min.js": 1,
                "sites\/all\/themes\/rwsiitj\/js\/rwscustom.js": 1,
                "sites\/all\/themes\/rwsiitj\/js\/jqueryui\/jquery-ui.min.js": 1
            }
        },
        "jcarousel": {
            "ajaxPath": "\/jcarousel\/ajax\/views",
            "carousels": {
                "jcarousel-dom-1": {
                    "view_options": {
                        "view_args": "",
                        "view_path": "node\/1",
                        "view_base_path": "events",
                        "view_display_id": "block",
                        "view_name": "nodequeue_14",
                        "jcarousel_dom_id": 1
                    },
                    "wrap": "circular",
                    "skin": "default",
                    "responsive": 1,
                    "scroll": 1,
                    "autoPause": 1,
                    "start": 1,
                    "selector": ".jcarousel-dom-1"
                },
                "jcarousel-dom-2": {
                    "view_options": {
                        "view_args": "",
                        "view_path": "node\/1",
                        "view_base_path": "faculty",
                        "view_display_id": "block",
                        "view_name": "nodequeue_9",
                        "jcarousel_dom_id": 2
                    },
                    "wrap": "circular",
                    "skin": "default",
                    "responsive": 1,
                    "scroll": 1,
                    "auto": 5,
                    "autoPause": 1,
                    "animation": "slow",
                    "start": 1,
                    "selector": ".jcarousel-dom-2"
                }
            }
        },
        "lightbox2": {
            "rtl": 0,
            "file_path": "\/(\\w\\w\/)public:\/",
            "default_image": "\/sites\/all\/modules\/lightbox2\/images\/brokenimage.jpg",
            "border_size": 10,
            "font_color": "000",
            "box_color": "fff",
            "top_position": "",
            "overlay_opacity": "0.8",
            "overlay_color": "000",
            "disable_close_click": true,
            "resize_sequence": 0,
            "resize_speed": 400,
            "fade_in_speed": 400,
            "slide_down_speed": 600,
            "use_alt_layout": false,
            "disable_resize": false,
            "disable_zoom": false,
            "force_show_nav": false,
            "show_caption": true,
            "loop_items": false,
            "node_link_text": "View Image Details",
            "node_link_target": false,
            "image_count": "Image !current of !total",
            "video_count": "Video !current of !total",
            "page_count": "Page !current of !total",
            "lite_press_x_close": "press \u003Ca href=\u0022#\u0022 onclick=\u0022hideLightbox(); return FALSE;\u0022\u003E\u003Ckbd\u003Ex\u003C\/kbd\u003E\u003C\/a\u003E to close",
            "download_link_text": "",
            "enable_login": false,
            "enable_contact": false,
            "keys_close": "c x 27",
            "keys_previous": "p 37",
            "keys_next": "n 39",
            "keys_zoom": "z",
            "keys_play_pause": "32",
            "display_image_size": "original",
            "image_node_sizes": "()",
            "trigger_lightbox_classes": "",
            "trigger_lightbox_group_classes": "",
            "trigger_slideshow_classes": "",
            "trigger_lightframe_classes": "",
            "trigger_lightframe_group_classes": "",
            "custom_class_handler": 0,
            "custom_trigger_classes": "",
            "disable_for_gallery_lists": true,
            "disable_for_acidfree_gallery_lists": true,
            "enable_acidfree_videos": true,
            "slideshow_interval": 5000,
            "slideshow_automatic_start": true,
            "slideshow_automatic_exit": true,
            "show_play_pause": true,
            "pause_on_next_click": false,
            "pause_on_previous_click": true,
            "loop_slides": false,
            "iframe_width": 600,
            "iframe_height": 400,
            "iframe_border": 1,
            "enable_video": false,
            "useragent": "Mozilla\/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit\/537.36 (KHTML, like Gecko) Chrome\/71.0.3578.98 Safari\/537.36"
        },
        "viewsSlideshow": {
            "nodequeue_2-block_1": {
                "methods": {
                    "goToSlide": ["viewsSlideshowPager", "viewsSlideshowSlideCounter", "viewsSlideshowCycle"],
                    "nextSlide": ["viewsSlideshowPager", "viewsSlideshowSlideCounter", "viewsSlideshowCycle"],
                    "pause": ["viewsSlideshowControls", "viewsSlideshowCycle"],
                    "play": ["viewsSlideshowControls", "viewsSlideshowCycle"],
                    "previousSlide": ["viewsSlideshowPager", "viewsSlideshowSlideCounter", "viewsSlideshowCycle"],
                    "transitionBegin": ["viewsSlideshowPager", "viewsSlideshowSlideCounter"],
                    "transitionEnd": []
                }, "paused": 0
            }
        },
        "viewsSlideshowPager": {
            "nodequeue_2-block_1": {
                "bottom": {
                    "type": "viewsSlideshowPagerFields",
                    "master_pager": "0"
                }
            }
        },
        "viewsSlideshowPagerFields": {"nodequeue_2-block_1": {"bottom": {"activatePauseOnHover": 0}}},
        "viewsSlideshowControls": {"nodequeue_2-block_1": {"bottom": {"type": "viewsSlideshowControlsText"}}},
        "viewsSlideshowCycle": {
            "#views_slideshow_cycle_main_nodequeue_2-block_1": {
                "num_divs": 7,
                "id_prefix": "#views_slideshow_cycle_main_",
                "div_prefix": "#views_slideshow_cycle_div_",
                "vss_id": "nodequeue_2-block_1",
                "effect": "scrollHorz",
                "transition_advanced": 1,
                "timeout": 5000,
                "speed": 700,
                "delay": 2000,
                "sync": 1,
                "random": 0,
                "pause": 1,
                "pause_on_click": 0,
                "play_on_hover": 0,
                "action_advanced": 0,
                "start_paused": 0,
                "remember_slide": 0,
                "remember_slide_days": 1,
                "pause_in_middle": 0,
                "pause_when_hidden": 0,
                "pause_when_hidden_type": "full",
                "amount_allowed_visible": "",
                "nowrap": 0,
                "pause_after_slideshow": 0,
                "fixed_height": 1,
                "items_per_slide": 1,
                "wait_for_image_load": 1,
                "wait_for_image_load_timeout": 3000,
                "cleartype": 0,
                "cleartypenobg": 0,
                "advanced_options": "{}"
            }
        },
        "urlIsAjaxTrusted": {"\/": true},
        "superfish": {
            "1": {
                "id": "1",
                "sf": {
                    "animation": {"opacity": "show", "height": "show"},
                    "speed": "\u0027fast\u0027",
                    "autoArrows": true,
                    "dropShadows": true,
                    "disableHI": false
                },
                "plugins": {
                    "touchscreen": {"mode": "always_active"},
                    "smallscreen": {
                        "mode": "window_width",
                        "addSelected": false,
                        "menuClasses": false,
                        "hyperlinkClasses": false,
                        "title": "Main menu"
                    },
                    "supposition": true,
                    "bgiframe": false,
                    "supersubs": {"minWidth": "12", "maxWidth": "27", "extraWidth": 1}
                }
            }
        },
        "bootstrap": {
            "anchorsFix": "0",
            "anchorsSmoothScrolling": "0",
            "formHasError": 1,
            "popoverEnabled": 1,
            "popoverOptions": {
                "animation": 1,
                "html": 0,
                "placement": "right",
                "selector": "",
                "trigger": "click",
                "triggerAutoclose": 1,
                "title": "",
                "content": "",
                "delay": 0,
                "container": "body"
            },
            "tooltipEnabled": 1,
            "tooltipOptions": {
                "animation": 1,
                "html": 0,
                "placement": "auto left",
                "selector": "",
                "trigger": "hover focus",
                "delay": 0,
                "container": "body"
            }
        }
    });


</script>

<script>
    $(document).ready(function () {
        $(this).scrollTop(0);
    });
</script>
</body>
</html>
