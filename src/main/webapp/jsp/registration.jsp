<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8"><link rel="shortcut icon" href="http://localhost/favicon.png" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<meta name="viewport" content="width=device-width, initial-scale=1">
<script data-cfasync="false" id="ao_optimized_gfonts"
type="text/javascript">WebFontConfig={google:{families:['Lato:300,400'] },classes:false, events:false, timeout:1500};
    (
        function()
    {
        var wf = document.createElement('script');
        wf.src='https://ajax.googleapis.com/ajax/libs/webfont/1/webfont.js';
        wf.type='text/javascript';wf.async='true';
        var s=document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(wf, s);
    }
    )
();
</script>
<link type="text/css" media="all" href="../wp-content/cache/autoptimize/css/autoptimize_e5f671b1828a9e81941de090a06f0ed0.css" rel="stylesheet" />
<title>
Katihar Engineering College</title>
<link href="../wp-content/themes/kec/assets/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="../wp-content/themes/kec/assets/vendors/bootstrap/dist/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../wp-content/themes/kec/assets/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<link href="../wp-content/themes/kec/assets/css/editor.css" rel="stylesheet" />
<link href="../wp-content/themes/kec/assets/css/common.css" rel="stylesheet" />
<link id="theme" href="../wp-content/themes/kec/assets/css/default.css" rel="stylesheet" />
<link href="../wp-content/themes/kec/assets/vendors/bootstrap-sweetalert/dist/sweetalert.css" rel="stylesheet" />
 <!--[if lt IE 9]>
 <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
 <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
 <![endif]-->
<link href='https://fonts.gstatic.com' crossorigin='anonymous' rel='preconnect' />
<link href='https://ajax.googleapis.com' rel='preconnect' />
<link href='https://fonts.googleapis.com' rel='preconnect' />
<link rel='stylesheet' id='uaf_client_css-css'wp-content/uploads/useanyfont/uaf.css' type='text/css' media='all' />
<link rel='https://api.w.org/' href='https://#/wp-json/' />
<link rel="EditURI" type="application/rsd+xml" title="RSD" href="https://#/xmlrpc.php?rsd" />
<link rel="wlwmanifest" type="application/wlwmanifest+xml" href="https://#/wp-includes/wlwmanifest.xml" />
<meta name="generator" content="WordPress 4.9.8" />
<link rel="alternate" type="application/json+oembed" href="https://#/wp-json/oembed/1.0/embed?url=https%3A%2F%2F#%2F" />
<link rel="alternate" type="text/xml+oembed" href="https://#/wp-json/oembed/1.0/embed?url=https%3A%2F%2F#%2F&#038;format=xml" />
<link rel="stylesheet" href="../wp-content/themes/kec/assets/vendors/nivo-lightbox/dist/nivo-lightbox.min.css" />
<link rel="stylesheet" href="../wp-content/themes/kec/assets/vendors/nivo-lightbox/themes/default/default.css" />
<script src="../wp-content/themes/kec/assets/vendors/jquery.cookie/jquery.cookie.js></script>
<script src="../wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js></script>
<script src="../wp-content/themes/kec/assets/vendors/bootstrap-sweetalert/dist/sweetalert.js></script>
<script src="../wp-content/themes/kec/assets/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../wp-content/themes/kec/assets/vendors/waypoints/lib/jquery.waypoints.min.js></scripts>
<script src="../wp-content/themes/kec/assets/vendors/waypoints/lib/shortcuts/sticky.min.js></script>
<script src="../wp-content/themes/kec/assets/vendors/nivo-lightbox/dist/nivo-lightbox.min.js></script>
</head>

<body class="home page-template-default page page-id-2"><div id="fb-root"></div> <script>(function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.9";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));</script>
 <header>
<div class="top-header">
<div class="container" style="position: relative;">
<ul style="list-style-type: none; position: absolute; top: -1px; right: 10px; z-index: 50;">
<li style="display: inline-block; vertical-align: top; margin-top: 5px;">Color Scheme:</li>
<li style="display: inline-block; padding: 1px;">
<span onclick="changeTheme('default.css')" style="display: inline-block; width: 25px; height: 25px; cursor: pointer; background-color: #3253ca;"></span></li>
<li style="display: inline-block; padding: 1px;">
<span onclick="changeTheme('theme1.css')" style="display: inline-block; width: 25px; height: 25px; cursor: pointer; background-color: #6E1A98;"></span></li>
<li style="display: inline-block; padding: 1px;">
<span onclick="changeTheme('theme2.css')" style="display: inline-block; width: 25px; height: 25px; cursor: pointer; background-color: #F0C463;"></span></li>
</ul>
<div class="visible-xs"><br /><br /></div>
<div class="row">
<div class="col-sm-7">
<div class="logo-left pull-left"><img alt="" src="logo1.jpeg" class="img-responsive" style="height: 125px; margin-right: 5px;width:425px;" /></div> <a href="https://#/" class="logo-right pull-left"></a>
<div class="clearfix"></div>
</div>
<div class="col-sm-5 text-right">
<div class="top-header-menu-wrapper">
<ul class="top-header-menu">
<li><a href="/">Home</a></li><li><a href="#modal-inquiry-form" data-toggle="modal">Inquiry Form</a></li>
<li><a href="../about-kec/search-student.html"><i class="fa fa-search"></i> &nbsp;Search Student</a></li>
<li><a href="../approval-kec/aicte-approval.html" target="_blank">AICTE</a></li>
<li><a href="../category/kec-web-team.html" target="_blank">KEC Web Team</a></li>
</ul>
</div>
</div>
</div>
</div>
</div>
<nav class="navbar navbar-default">
<div class="container">
<div class="navbar-header">
 <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
<span class="sr-only">Toggle navigation</span>
<span class="icon-bar"></span>
 <span class="icon-bar"></span>
<span class="icon-bar"></span>
 </button>
</div>
<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
<ul class="nav navbar-nav">
<li class="menubar-logo hidden-xs">
<img class="img-responsive" alt="" src="logo1.jpeg" />
</a>
</li>
<li><a style="font-weight: 600;" href="/">Home</a></li>
<li class="dropdown">
<a style="font-weight: 600;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">About KEC <span class="fa fa-angle-down"></span></a>
<ul class="dropdown-menu">
<li><a href="../about-kec/history.html">History</a></li>
<li><a href="../about-kec/principal-message.html">Principal's Message</a></li>
<li><a href="../about-kec/administration.html">Administration</a></li>
<li><a href="../about-kec/vision.html">Vision & Mission</a></li>
<li><a href="../about-kec/affiliation.html">Affiliation</a></li>
<li><a href="../about-kec/allotment-surrender.html">Allotment and Surrender Report</a></li>
<li><a href="../about-kec/visit-us.html">Visit Us</a></li>
<li><a href="contact-us.html">Contact Us</a></li></ul></li>
<li class="dropdown"> <a style="font-weight: 600;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Academic <span class="fa fa-angle-down"></span></a>
<ul class="dropdown-menu">
<li>
<a href="../academic-kec/ug-admission.html">UG Admission</a></li>
<li><a href="../academic-kec/academic-regulations.html">Academic Regulations</a></li>
<li><a href="../academic-kec/academic-calender.html">Academic Calender</a></li>
<li><a href="../academic-kec/list-of-holidays.html">List of Holidays</a></li>
<li><a href="../academic-kec/attendance.html">Attendance</a></li>
<li><a href="../academic-kec/syllabus.html">Syllabus</a></li>
<li><a href="../academic-kec/disciplinary-rules.html/">Disciplinary Rules</a></li>
<li><a href="../academic-kec/anti-ragging.html">Anti Ragging</a></li>
<li><a href="../academic-kec/mom-of-academic-council.html/">MoM of Academic Council</a></li>
<li><a href="../academic-kec/notice-from-govt.html">Notice from Govt.</a></li>
</ul>
</li>
<li class="dropdown">
<a style="font-weight: 600;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Department <span class="fa fa-angle-down"></span></a>
<ul class="dropdown-menu">
<li><a href="../department-kec/civil-about.html">Civil Engineering</a></li>
<li><a href="../department-kec/mech-about.html">Mechanical Engineering</a></li>
<li><a href="../department-kec/eee-about.html">Electrical & Electronics Engg.</a></li>
<li><a href="../department-kec/cse-about.html">Computer Sc. & Engineering</a></li> <li><a href="../department-kec/ash-about.html">Applied Sc. & Humanities</a></li>
</ul>
</li>
<li class="dropdown">
 <a style="font-weight: 600;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Facilities & Services <span class="fa fa-angle-down"></span></a>
<ul class="dropdown-menu">
<li>
<a href="../training-placement-kec/placement-brochure.html">Placement</a></li>
<li><a href="../facilities-services-kec/cse-center.html">Computer Centre</a></li>
<li><a href="../facilities-services-kec/startup-cell.html">Startup Cell</a></li>
<li><a href="../facilities-services-kec/centtral-library.html">Central Library</a></li>
<li><a href="../facilities-services-kec/useful-link.html">Useful-Link</a></li>
<li><a href="../facilities-services-kec/hostels.html">Hostels</a></li>
<li><a href="../facilities-services-kec/sports-facilities.html">Sports Facilities</a></li>
<li><a href="../facilities-services-kec/medical-facilities.html">Medical Facilities</a></li>
<li><a href="../facilities-services-kec/bank.html">Bank</a></li>
<li><a href="../facilities-services-kec/guest-house.html">Guest House</a></li>
<li><a href="../facilities-services-kec/club.html">Club</a></li>
<li><a href="../facilities-services-kec/gymnasium.html">Gymnasium</a></li>
<li><a href="../facilities-services-kec/health-center.html">Health Center</a></li>
<li><a href="../facilities-services-kec/wi-fi.html">Wi Fi</a></li>
</ul>
</li>
<li class="dropdown">
 <a style="font-weight: 600;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Training & Placement <span class="fa fa-angle-down"></span></a>
<ul class="dropdown-menu">
<li><a href="../training-placement-kec/about-placement.html">About Placement</a></li>
<li><a href="../training-placement-kec/placement-brochure.html">Placement Brochure</a></li>
<li><a href="../training-placement-kec/rules-of-training-placement.html">Rules of Training &#038; Placement</a></li>
<li><a href="../training-placement-kec/tips-for-resume.html">Tips for Resume</a></li>
<li><a href="../training-placement-kec/student-placement-coordinator.html">Students Placement Coordinator</a></li>
</ul>
</li>
<li class="dropdown"> <a style="font-weight: 600;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Gallery <span class="fa fa-angle-down"></span></a>
<ul class="dropdown-menu">
<li><a href="../gallery-kec/media-gallery.html">Media Gallery</a></li>
<li><a href="../gallery-kec/photo-gallery.html">Photo Gallery</a></li>
<li><a href="../gallery-kec/video-gallery.html/">Video Gallery</a></li>
</ul>
</li>
<li class="dropdown">
<a style="font-weight: 600;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Approval <span class="fa fa-angle-down"></span></a>
<ul class="dropdown-menu">
<li><a href="../approval-kec/aicte-approval.html">AICTE Approval</a></li>

<li><a href="../approval-kec/aku-approval.html">AKU Approval</a></li>
</ul>
</li>
<li class="dropdown">
 <a style="font-weight: 600;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">RTI <span class="fa fa-angle-down"></span></a>
<ul class="dropdown-menu">
<li><a href="../rti-kec/rti-act.html">RTI act 2005</a></li>
<li><a href="../rti-kec/rti-application.html">RTI Application Form</a></li>
<li><a href="../rti-kec/rti-status.html">RTI Status</a></li>
<li><a href="../rti-kec/public-information-cell.html">Public Information Cell</a></li>
</ul>
</li>
<li class="dropdown">
<a style="font-weight: 600;" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Login <span class="fa fa-angle-down"></span></a>
<ul class="dropdown-menu">
<li><a href="https://accounts.google.com?</li>
<li><a href="/login">Admin</a></li> <li><a href="https://localhost:2096/webmail" target="_blank">Mail Login</a></li> <li><a href="/logout">Logout</a></li>
<li><a href="../category/student.html/">Student</a></li>
<li><a href="../category/faculty-login.html">Faculty</a></li>
</ul>
</li>
</ul>
</div>
</div>
</nav>
</header>
<div class="modal fade" id="modal-inquiry-form">
<div class="modal-dialog">
<form method="post" onsubmit="return doInquiry(this)">
<input type="hidden" name="action" value="ajaxhandler" />
 <input type="hidden" name="xaction" value="postInquiry" />
<div class="modal-content">
<div class="modal-header">
<button class="close" type="button" data-dismiss="modal">&times;</button>
<h4 class="modal-title">Inquiry Form</h4><p>If you have any questions, please do ask us by filling the form below.</p></div>
<div class="modal-body">
<div class="form-group">
<label class="control-label">Your Name:</label> <input type="text" name="fullName" class="form-control" required /></div>
<div class="form-group">
 <label class="control-label">Phone Number:</label>
<input type="tel" name="phoneNumber" class="form-control" required />
</div>
<div class="form-group">
<label class="control-label">Email Address:</label>
<input type="email" name="emailAddress" class="form-control" /></div>
<div class="form-group">
 <label class="control-label">Message:</label>
<textarea name="message" class="form-control" style="height: 150px;" required placeholder="Please enter your query here..."></textarea>
</div>
</div>
<div class="modal-footer">


<button type="submit" data-loading-text="<i class='fa fa-cog fa-spin'></i> Please wait..." class="btn btn-success"><i class="fa fa-check"></i> Submit</button> <button type="button" data-dismiss="modal" class="btn btn-default"><i class="fa fa-times"></i> Cancel</button></div></div></form></div></div>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;"><div class="container"><div class="row"><div class="col-md-10 col-sm-8"><h3>Create Account</h3></div><div class="col-md-2 col-sm-4" style="font-size: 24px; line-height: 40px;"> <span style="display: inline-block; line-height: 40px; vertical-align: middle;"><i class="fa fa-share-alt"></i> Share:</span> <a href="" target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i class="fa fa-facebook-square"></i></a> <a href="" target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i class="fa fa-twitter-square"></i> </a></div></div></div></div><div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-4 col-sm-4"><div class="panel panel-default"><div class="panel-heading"> <i class="fa fa-sign-in"></i>Create Accoount</div>


    <div class="panel-body">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

        </div></div></div></div></div>
    <div class="menuFooter clearfix">
        <div class="container">
            <div class="row clearfix">
                <div class="col-sm-3 col-xs-6">
                    <ul class="menuLink clearfix">
                        <li>
                            <a href="../academic-kec/academic-calender.html" data-toggle="modal">Academic Calendar</a>
                        </li>
                        <li>
                            <a href="../category/student-attendance.html">Student Attendance</a>
                        </li>
                        <li><a href="../category/requirement.html">Requirement</a></li>
                        <li>
                            <a href="../about-kec/administration.html">Phone Directory</a>
                        </li>
                        <li>
                            <a href="../training-placement-kec/placement-brochure.html"> Placement </a>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-3 col-xs-6 borderLeft clearfix">
                    <ul class="menuLink clearfix">
                        <li>
                            <a href="../facilities-services-kec/hostels.html">Hostels</a>
                        </li>
                        <li>
                            <a href="../category/accounts.html">Accounts</a>
                        </li>
                        <li>
                            <a href="../gallery-kec/photo-gallery.html">Photo Gallery</a>
                        </li>
                        <li>
                            <a href="../category/students.html"> Students</a>
                        </li>
                        <li>
                            <a href="../category/alumni.html"> Alumni </a>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-3 col-xs-6 borderLeft clearfix">
                    <div class="footer-address">
                        <h5>Location:</h5>
                        <address>Katihar Engineering College <br/>
                            <small> Katihar, Bihar, India<br/> Pin - 854106</small>
                        </address>
                        <a href="https://goo.gl/maps/ftDss6HxLSK2"><span class="place"><i class="fa fa-map-marker"></i>Campus</span></a>
                    </div>
                </div>
                <div class="col-sm-3 col-xs-6 borderLeft clearfix">
                    <div class="socialArea clearfix">
                        <h5>Find us on:</h5>
                        <ul class="list-inline ">


                            <li>
                                <a target="_blank"
                                   href="https://www.facebook.com/Katihar-Engineering-College-115715436047971/"><i
                                        class="fa fa-facebook"></i>
                                </a>
                            </li>

                        </ul>
                    </div>
                    <div class="contactNo clearfix"><h5><i class="fa fa-mobile-phone"></i> Call us on:</h5>
                        <h3>06452-239122</h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer clearfix" style="background-color: #333; color: #fff; padding: 15px 0;">
        <div class="container">
            <div class="row clearfix">
                <div class="col-sm-5 col-xs-12 copyRight"><p>&copy 2018 Copyright KEC, Katihar </a>
                </p>
                </div>
                <div class="col-sm-2 col-xs-12 text-center">
                    <div id="text-visitors" style="font-size:11px;">VISITORS :</div>
                </div>
                <div class="col-sm-5 col-xs-12 privacy_policy">
                    <a href="../about-kec/contact-us.html" style="color: #fff;">Contact us</a> | <a
                        href="https://#/privacy/" style="color: #fff;">Privacy Policy</a>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="text-center">
 <span style="font-size: 11px; color: #666;"> <h5> <strong> Designed and Developed by KEC Web Team</strong></h5>
 </span>
            </div>
        </div>
    </div>
    </div>
    <script src="../wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script><script type="text/javascript" src="/js/protectcode.js"></script>
    <script src="../wp-content/themes/kec/assets/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="../wp-content/themes/kec/assets/vendors/waypoints/lib/jquery.waypoints.min.js"></script>
    <script>$(document).ready(function(){
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
        });
    </script>
    <script src="../wp-content/themes/kec/assets/vendors/nivo-lightbox/dist/nivo-lightbox.min.js"></script>
    <script>var brandTimer = null;
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
    <script type='text/javascript' src='../wp-includes/js/wp-embed.min.js?ver=4.9.8'></script>
    <script src="../wp-content/themes/kec/assets/vendors/jquery.cookie/jquery.cookie.js"></script>
    <script src="../wp-content/themes/kec/assets/vendors/waypoints/lib/shortcuts/sticky.min.js">
    </script>
    <script src="../wp-content/themes/kec/assets/vendors/bootstrap-sweetalert/dist/sweetalert.min.js"></script>
    <script>$(document).ready(function(){
        new Waypoint.Sticky({
            element: $('.navbar.navbar-default')[0]
        });
        $('#modal-inquiry-form').on('hidden.bs.modal', function(){
            $.cookie('hide-inquiry-form', '1', {path: '/'});
        });
    });
    function changeTheme(theme){
        $('#theme').attr('href', '../changetheme/css/' + theme);
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