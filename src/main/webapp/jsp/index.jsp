<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header role="banner" id="page-header">
<c:forEach var="item" items="${Role}">
    ${item}
    <c:if test = "${'Admin' eq item}">
    <strong><i class="fa fa-news"></i> <a href="/auth/uploadfile/upload">Upload Notice / News</a></strong>
    <strong><i class="fa fa-news"></i> <a href="/auth/sendMessage">Send Message</a></strong>
    </c:if>
</c:forEach>
    <marquee><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif">
           <a href="https://www.aicte-india.org/feedback/" target="_blank">Student and Faculty give feedback to AICTE</a></span></span></li></marquee>
    <c:forEach items="${scrollingNoticeList}" var="notice">
        	<c:set var = "filePath"  value = "http://localhost/wp-content/uploads/notice/"/>
        	<c:if test = "${notice.noticeAge < 2}">
        	    <li>
                    <i class="fa fa-asterisk"></i>
                 	<img class="" src="NewFlashing.gif" alt=""Related image" width="44" height="40" />
                    <a href="${filePath}${notice.uploadedFileName}" target="_blank">${notice.headLine}</a>
                    <c:if test = "${Role == 'Admin'}">
                                 <span class="label label-danger"><a href="<c:url value='/auth/${notice.uploadedFileName}/${notice.id}' />">Delete</a></span>
                             </c:if>
                </li>
             </c:if>
             <c:if test = "${notice.noticeAge >= 45}">
                 <li>
                     <i class="fa fa-asterisk"></i>
                     <a href="${filePath}${notice.uploadedFileName}" target="_blank">${notice.headLine}</a>
                     <c:if test = "${Role == 'Admin'}">
                                  <span class="label label-danger"><a href="<c:url value='/auth/${notice.uploadedFileName}/${notice.id}' />">Delete</a></span>
                              </c:if>
                 </li>
             </c:if>

        </c:forEach>
    <div class="region region-header">
        <section id="block-views-nodequeue-2-block" class="block block-views clearfix">


            <div class="view view-nodequeue-2 view-id-nodequeue_2 view-display-id-block view-dom-id-adc75da57891ed7e5b0d70e0b788ead9">


                <div class="view-content">

                    <div class="skin-default">

                        <div id="views_slideshow_cycle_main_nodequeue_2-block_1"
                             class="views_slideshow_cycle_main views_slideshow_main">
                            <div id="views_slideshow_cycle_teaser_section_nodequeue_2-block_1"
                                 class="views-slideshow-cycle-main-frame views_slideshow_cycle_teaser_section">
                                <div id="views_slideshow_cycle_div_nodequeue_2-block_1_0"
                                     class="views-slideshow-cycle-main-frame-row views_slideshow_cycle_slide views_slideshow_slide views-row-1 views-row-first views-row-odd"
                                     aria-labelledby='views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_0'>
                                    <div class="views-slideshow-cycle-main-frame-row-item views-row views-row-0 views-row-odd views-row-first">

                                        <div class="views-field views-field-field-image">
                                            <div class="field-content"><img typeof="foaf:Image" class="img-responsive"
                                                                            src="http://localhost/sites/default/files/img1.jpg"
                                                                            width="2000" height="550" alt=""/></div>
                                        </div>
                                        <div class="views-field views-field-title"><span class="field-content">Library </span>
                                        </div>
                                    </div>
                                </div>

                            <div id="views_slideshow_controls_text_nodequeue_2-block_1"
                                 class="views-slideshow-controls-text views_slideshow_controls_text">
  <span id="views_slideshow_controls_text_previous_nodequeue_2-block_1"
        class="views-slideshow-controls-text-previous views_slideshow_controls_text_previous">
  <a href="#" rel="prev">Previous</a>
</span>
                                <span id="views_slideshow_controls_text_pause_nodequeue_2-block_1"
                                      class="views-slideshow-controls-text-pause views_slideshow_controls_text_pause  views-slideshow-controls-text-status-play"><a
                                        href="#">Pause</a></span>
                                <span id="views_slideshow_controls_text_next_nodequeue_2-block_1"
                                      class="views-slideshow-controls-text-next views_slideshow_controls_text_next">
  <a href="#" rel="next">Next</a>
</span>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </section>
    </div>
</header> <!-- /#page-header -->

<div class="gt-newsblocksection">
    <div class="container">
        <div class="row">
            <div class="col-sm-5">
                <div class="region region-home-1">
                    <section id="block-block-18" class="block block-block clearfix">

                        <h2 class="block-title">Latest Notice</h2>
                        <div class="rwsvtickercontent">
                            <div class="rwsvticker1">


                                <ul>
                                    <li>
                                    <span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif">
                                    <a href="https://www.aicte-india.org/feedback/" target="_blank">Student and Faculty give feedback to AICTE</a></span></span></li>
                                        <div class="panel-body" style="max-height: 500px;">
                                 <ul class="listUpdates" id="noticeBoardItems">

                                 <li style="display: block;">
                                  	<div>


                                  	</div>
                                  </li>
                                 <c:forEach items="${noticeList}" var="notice">
                                   <li>
                                 	<div>
                                 		<span class="label" style="border:  solid 1px #ccc; color: #000;">
                                 		<c:set var = "clazz"  value = "label label-warning"/>
                                 		<c:set var = "filePath"  value = "http://localhost/wp-content/uploads/notice/"/>
                                 		 <c:if test = "${ notice.noticeType == 'News'}">m
                                                  <c:set var = "clazz"  value = "label label-primary"/>
                                          </c:if>
                                 		<i class=""></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                                 		<c:if test = "${Role == 'Admin'}">
                                 		    <span class="label label-danger"><a href="<c:url value='/auth/${notice.uploadedFileName}/${notice.id}'/>">Delete</a></span>
                                         </c:if>
                                 		<a href="${filePath}${notice.uploadedFileName}" target="_blank">${notice.headLine}</a>
                                 	</div>
                                 </li>
                                 </c:forEach>
                                </ul>

                                </div>

                            </div>
                        </div><div class="more-link">
                              <a href="http://localhost/category/notices">View all Notices</a></div>

                    </section>
                </div>
            </div>

            <div class="col-sm-3">
                <div class="region region-home-4">
                    <section id="block-block-12" class="block block-block clearfix">

                        <h2 class="block-title">Important Links</h2>

                        <ul>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a href="http://socialwelfare.bih.nic.in/"
                                                                                        target="_blank">Social Welfare Dept. Bhar</a></span>
                            </li>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a
                                    href="http://dstbihar.gov.in/" target="_blank">DST Bihar</a></span></li>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a href="http://www.educationbihar.gov.in/login.aspx?ReturnUrl=%2fDefault.aspx">Education Dept. Bihar</a></span>
                            </li>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a
                                    href="http://gov.bih.nic.in/">Govt. of Bihar</a></span>
                            </li>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a
                                    href="http://bceceboard.bihar.gov.in/">BCECEB</a></span></li>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a
                                    href="http://akubihar.ac.in/">AKU, Patna</a></span></li>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a
                                    href="https://www.aicte-india.org/" target="_blank">AICTE</a></span>
                            </li>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a
                                    href="https://jeemain.nic.in/webinfo/Public/Home.aspx">Joint Entrance Examination</a></span>
                            </li>
                            <!--	<li><a href="https://www.vidyalakshmi.co.in/Students/" target="_blank">Vidya Lakshmi Portal</a></li>-->
                        </ul>

                    </section>
                </div>
            </div>
            <div class="col-sm-3">
                            <div class="region region-home-2">
                                <section id="block-views-nodequeue-12-block" class="block block-views clearfix">

                                    <h2 class="block-title">Tenders</h2>

                                    <div class="view view-nodequeue-12 view-id-nodequeue_12 view-display-id-block view-dom-id-1584ef8e1537a413a20e095a49a0704e">


                                        <div class="view-content">
                                            <div class="item-list">
                                                <ul>
                                                    <li class="views-row views-row-1 views-row-odd views-row-first">
                                                        <div class="views-field views-field-title"><span class="field-content"><a
                                                                href="/tender-procurement-installation-pinball-disc-tribometer-system">No tenders.</a></span>
                                                        </div>
                                                    </li>

                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </div>

        </div>
    </div>
</div>

<div class="gtmaincontenthome">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <a id="main-content"></a>
                <h1 class="page-header">About KEC</h1>

                <div class="region region-content">
                    <section id="block-system-main" class="block block-system clearfix">


                        <article id="node-1" class="node node-page clearfix" about="/home" typeof="foaf:Document">
                            <header>
                                <span property="dc:title" content="About Us" class="rdf-meta element-hidden"></span>
                            </header>
                            <div class="field field-name-body field-type-text-with-summary field-label-hidden">
                                <div class="field-items">
                                    <div class="field-item even" property="content:encoded"><p><strong>Katihar Engineering College (KEC), Katihar </strong>with the advent of technology and the emergence of notable education in our society, had decided to adapt the prevailing need of light. In the mission to achieve the eminence in the field, the establishment of Katihar Engineering College was laid in the year 2016. The college is enriched with a pollyannaish environment, highly motivated and enthusiastic students guided by well qualified professional from different prestigious colleges of our country. The college was established with three branches- Civil Engineering, Mechanical Engineering and Computer Science and Engineering. Each branch has a number of renowned professors with high qualifications and experience in teaching or their respective industries. The college consists of a central library, Computer Labs, Training and Placement Cell, Programmer's Club etc, with the exceptional support staffs and infrastructure. All the labs and other infrastructure is in highly conditional states serving at their best to provide the best facilities to all the members of the college. Student of the college have exhibited their skill at different platform and gathered many awards from premium institutes. Presently the college functions in Government Polytechnic, Bheriya Rhekia, Katihar. It is situated 3 km from Katihar Railway Station and there is a good transportation facility available from there. Currently, the own campus for the college is under-construction in Hajipur, Katihar near NH 31. It is expected to complete soon.<a
                                                href="about-kec/history.html">more</a>...</p>
                                    </div>
                                </div>
                            </div>
                        </article>

                    </section>
                </div>
            </div>
            <div class="col-sm-6">
                &nbsp;
            </div>
        </div>
    </div>
</div>


<div class="main-container container" style="display:none;">
    <div class="row">

        <section class="col-sm-12">

            <div class="alert alert-block alert-dismissible alert-danger messages error">
                <a class="close" data-dismiss="alert" href="#">&times;</a>
                <h4 class="element-invisible">Error message</h4>
                <em class="placeholder">Notice</em>: unserialize(): Error at offset 1090 of 9489 bytes in <em
                    class="placeholder">views_db_object-&gt;load_row()</em> (line <em class="placeholder">2265</em> of
                <em class="placeholder">/var/www/html/sites/all/modules/contrib/views/includes/view.inc</em>).
            </div>

        </section>


    </div>
</div>


<div class="gt-galleryvideosection">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <div class="region region-home-gallery">
                    <section id="block-views-nodequeue-10-block" class="block block-views clearfix">

                        <h2 class="block-title">Gallery</h2>

                        <div class="view view-nodequeue-10 view-id-nodequeue_10 view-display-id-block view-dom-id-092f3c9d9a1a277eabf025598077161b">


                            <div class="view-content">
                                <div class="item-list">
                                    <ul>
                                        <li class="views-row views-row-1 views-row-odd views-row-first">
                                            <div class="views-field views-field-field-image">
                                                <div class="field-content"><a href="/pitch-better-india"><img
                                                        typeof="foaf:Image" class="img-responsive"
                                                        src="http://localhost/sites/default/files/styles/our_gallery_home_page/public/Pitch%20for%20a%20Better%20India_1.jpg?itok=U6XG7P1q"
                                                        width="330" height="225" alt=""
                                                        title="Pitch for a Better India"/></a></div>
                                            </div>
                                        </li>
                                        <li class="views-row views-row-2 views-row-even">
                                            <div class="views-field views-field-field-image">
                                                <div class="field-content"><a href="/ace-psychometric"><img
                                                        typeof="foaf:Image" class="img-responsive"
                                                        src="http://localhost/sites/default/files/styles/our_gallery_home_page/public/Ace%20the%20Psychometric_1.jpg?itok=4GiuxxhB"
                                                        width="330" height="225" alt=""
                                                        title="Ace the Psychometric"/></a></div>
                                            </div>
                                        </li>
                                        <li class="views-row views-row-3 views-row-odd views-row-last">
                                            <div class="views-field views-field-field-image">
                                                <div class="field-content"><a href="/workshop"><img typeof="foaf:Image"
                                                                                                    class="img-responsive"
                                                                                                    src="http://localhost/sites/default/files/styles/our_gallery_home_page/public/Disassembly%20Workshop_01.JPG?itok=29HGx6Gr"
                                                                                                    width="330"
                                                                                                    height="225" alt=""
                                                                                                    title="Machine Assembly and Disassembly Workshop"/></a>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>


                            <div class="more-link">
                                <a href="http://localhost/gallery/photogallery">
                                    View All </a>
                            </div>


                        </div>
                    </section>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="region region-home-video">
                    <section id="block-block-20" class="block block-block clearfix">

                        <h2 class="block-title">Video Gallery</h2>

                        <ul>
                            <li>
                                <div class="views-field views-field-field-image">
                                    <div class="field-content embed-responsive embed-responsive-16by9">
                                        <iframe frameborder="0" height="551"
                                                src="https://www.youtube.com/embed/DykZZgT9UHE" width="1050"></iframe>
                                    </div>
                                </div>
                                <div class="views-field views-field-title">Fresher's Party Event
                                </div>
                            </li>
                        </ul>
                        <div class="more-link"><a href="http://localhost/gallery/mediagallery">View All </a></div>

                    </section>
                </div>
            </div>

        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>