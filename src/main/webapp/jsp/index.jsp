<jsp:include page="header.jsp"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="item" items="${Role}">
    <c:if test = "${'Admin' eq item}">
    <strong><i class="fa fa-news"></i> <a href="/auth/uploadfile/upload">Upload Notice / News</a></strong>
    <strong><i class="fa fa-news"></i> <a href="/auth/sendMessage">Send Message</a></strong>
    </c:if>
</c:forEach>
<marquee style=  loop="infinite" onmouseover="this.stop();" onmouseout="this.start();">
<ul>
                        <li style="display: inline;float: left">
                     	    <img class="" src="http://localhost/sites/noticeicon.gif" alt="" related="" image"="" width="44" height="40">
                            <a href="https://www.aicte-india.org/feedback/" target="_blank">Click here to give feedback to A.I.C.T.E</a>
                        </li>



         <c:forEach items="${scrollingNoticeList}" var="scrollingNotice">


                    <li style= "display: inline;float: left">
                    <i class="fa fa-asterisk"></i>
                    <c:if test = "${scrollingNotice.noticeAge <= 45}">
                 	    <img class="" src="http://localhost/sites/noticeicon.gif" alt=""Related image" width="44" height="40" />
                    </c:if>
                    <a href="http://localhost/${scrollingNotice.id}/notice${scrollingNotice.fileType}" target="_blank">${scrollingNotice.headLine}</a>
                    <c:forEach var="item" items="${Role}">
                        <c:if test = "${'Admin' eq item}">
                                 <span class="label label-danger"><a href="<c:url value='/auth/${scrollingNotice.uploadedFileName}/${scrollingNotice.id}' />">Delete</a></span>
                        </c:if>
                     </c:forEach>

                    </li>

        </c:forEach>
   </ul>
</marquee>

<header role="banner" id="page-header">

  <div class="region region-header">
    <section id="block-views-nodequeue-2-block" class="block block-views clearfix">


  <div class="view view-nodequeue-2 view-id-nodequeue_2 view-display-id-block view-dom-id-567516c7928e5eb3a0d16a5d947ddcb7">



      <div class="view-content">

  <div class="skin-default">

    <div id="views_slideshow_cycle_main_nodequeue_2-block_1" class="views_slideshow_cycle_main views_slideshow_main"><div id="views_slideshow_cycle_teaser_section_nodequeue_2-block_1" class="views-slideshow-cycle-main-frame views_slideshow_cycle_teaser_section">
  <div id="views_slideshow_cycle_div_nodequeue_2-block_1_0" class="views-slideshow-cycle-main-frame-row views_slideshow_cycle_slide views_slideshow_slide views-row-1 views-row-first views-row-odd" aria-labelledby='views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_0'>
  <div class="views-slideshow-cycle-main-frame-row-item views-row views-row-0 views-row-odd views-row-first">

  <div class="views-field views-field-field-image">        <div class="field-content"><img typeof="foaf:Image" class="img-responsive" src="http://localhost/sites/default/files/img7.png" width="2000" height="550" alt="" /></div>  </div>
  <div class="views-field views-field-title">        <span class="field-content">Students @KEC Katihar</span>  </div></div>
</div>
<div id="views_slideshow_cycle_div_nodequeue_2-block_1_1" class="views-slideshow-cycle-main-frame-row views_slideshow_cycle_slide views_slideshow_slide views-row-2 views_slideshow_cycle_hidden views-row-even" aria-labelledby='views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_1'>
  <div class="views-slideshow-cycle-main-frame-row-item views-row views-row-0 views-row-odd">

  <div class="views-field views-field-field-image">        <div class="field-content"><img typeof="foaf:Image" class="img-responsive" src="http://localhost/sites/default/files/img2.JPG" width="2000" height="550" alt="" /></div>  </div>
  <div class="views-field views-field-title">        <span class="field-content">Library</span>  </div></div>
</div>
<div id="views_slideshow_cycle_div_nodequeue_2-block_1_2" class="views-slideshow-cycle-main-frame-row views_slideshow_cycle_slide views_slideshow_slide views-row-3 views_slideshow_cycle_hidden views-row-odd" aria-labelledby='views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_2'>
  <div class="views-slideshow-cycle-main-frame-row-item views-row views-row-0 views-row-odd">

  <div class="views-field views-field-field-image">        <div class="field-content"><img typeof="foaf:Image" class="img-responsive" src="http://localhost/sites/default/files/img15.jpg" width="2000" height="550" alt="" /></div>  </div>
  <div class="views-field views-field-title">        <span class="field-content">Fresher's Party @ KEC</span>  </div></div>
</div>
<div id="views_slideshow_cycle_div_nodequeue_2-block_1_3" class="views-slideshow-cycle-main-frame-row views_slideshow_cycle_slide views_slideshow_slide views-row-4 views_slideshow_cycle_hidden views-row-even" aria-labelledby='views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_3'>
  <div class="views-slideshow-cycle-main-frame-row-item views-row views-row-0 views-row-odd">

  <div class="views-field views-field-field-image">        <div class="field-content"><img typeof="foaf:Image" class="img-responsive" src="http://localhost/sites/default/files/img18.jpg" width="2000" height="550" alt="" /></div>  </div>
  <div class="views-field views-field-title">        <span class="field-content">Students @ Library</span>  </div></div>
</div>
<div id="views_slideshow_cycle_div_nodequeue_2-block_1_3" class="views-slideshow-cycle-main-frame-row views_slideshow_cycle_slide views_slideshow_slide views-row-4 views_slideshow_cycle_hidden views-row-even" aria-labelledby='views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_3'>
  <div class="views-slideshow-cycle-main-frame-row-item views-row views-row-0 views-row-odd">

  <div class="views-field views-field-field-image">        <div class="field-content"><img typeof="foaf:Image" class="img-responsive" src="http://localhost/sites/default/files/img1.jpg" width="2000" height="550" alt="" /></div>  </div>
  <div class="views-field views-field-title">        <span class="field-content">Students @ KEC</span>  </div></div>
</div>

</div>
</div>
          <div class="views-slideshow-controls-bottom clearfix">
        <div id="widget_pager_bottom_nodequeue_2-block_1" class="views-slideshow-pager-fields widget_pager widget_pager_bottom views_slideshow_pager_field">
  <div id="views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_0" class="views-slideshow-pager-field-item views_slideshow_pager_field_item views-row-odd views-row-first" aria-controls="views_slideshow_cycle_div_nodequeue_2-block_1_0">
  <div class="views-field-title">
    <div class="views-content-title">
    <a href='#slideshow-0'>Students @KEC Katihar</a>  </div>
</div>
</div>
<div id="views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_1" class="views-slideshow-pager-field-item views_slideshow_pager_field_item views-row-even" aria-controls="views_slideshow_cycle_div_nodequeue_2-block_1_1">
  <div class="views-field-title">
    <div class="views-content-title">
    <a href='#slideshow-1'>Library</a>  </div>
</div>
</div>
<div id="views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_2" class="views-slideshow-pager-field-item views_slideshow_pager_field_item views-row-odd" aria-controls="views_slideshow_cycle_div_nodequeue_2-block_1_2">
  <div class="views-field-title">
    <div class="views-content-title">
    <a href='#slideshow-2'>KEC Test</a>  </div>
</div>
</div>
<div id="views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_3" class="views-slideshow-pager-field-item views_slideshow_pager_field_item views-row-even" aria-controls="views_slideshow_cycle_div_nodequeue_2-block_1_3">
  <div class="views-field-title">
    <div class="views-content-title">
    <a href='#slideshow-3'>KEC</a>  </div>
</div>
</div>
<div id="views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_4" class="views-slideshow-pager-field-item views_slideshow_pager_field_item views-row-odd" aria-controls="views_slideshow_cycle_div_nodequeue_2-block_1_4">
  <div class="views-field-title">
    <div class="views-content-title">
    <a href='#slideshow-4'>KEC</a>  </div>
</div>
</div>
<div id="views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_5" class="views-slideshow-pager-field-item views_slideshow_pager_field_item views-row-even" aria-controls="views_slideshow_cycle_div_nodequeue_2-block_1_5">
  <div class="views-field-title">
    <div class="views-content-title">
    <a href='#slideshow-5'>KEC</a>  </div>
</div>
</div>
<div id="views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_6" class="views-slideshow-pager-field-item views_slideshow_pager_field_item views-row-odd" aria-controls="views_slideshow_cycle_div_nodequeue_2-block_1_6">
  <div class="views-field-title">
    <div class="views-content-title">
    <a href='#slideshow-6'>KEC</a>  </div>
</div>
</div>
<div id="views_slideshow_pager_field_item_bottom_nodequeue_2-block_1_7" class="views-slideshow-pager-field-item views_slideshow_pager_field_item views-row-even views-row-last" aria-controls="views_slideshow_cycle_div_nodequeue_2-block_1_7">
  <div class="views-field-title">
    <div class="views-content-title">
    <a href='#slideshow-7'>KEC</a>  </div>
</div>
</div>
</div>
<div id="views_slideshow_controls_text_nodequeue_2-block_1" class="views-slideshow-controls-text views_slideshow_controls_text">
  <span id="views_slideshow_controls_text_previous_nodequeue_2-block_1" class="views-slideshow-controls-text-previous views_slideshow_controls_text_previous">
  <a href="#" rel="prev">Previous</a>
</span>
  <span id="views_slideshow_controls_text_pause_nodequeue_2-block_1" class="views-slideshow-controls-text-pause views_slideshow_controls_text_pause  views-slideshow-controls-text-status-play"><a href="#">Pause</a></span>
  <span id="views_slideshow_controls_text_next_nodequeue_2-block_1" class="views-slideshow-controls-text-next views_slideshow_controls_text_next">
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
                        <div class="col-sm-3"style="margin-left:50px">
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
                                    href="http://gov.bih.nic.in/"target="_blank">Govt. of Bihar</a></span>
                            </li>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a
                                    href="http://bceceboard.bihar.gov.in/"target="_blank">BCECEB</a></span></li>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a
                                    href="http://akubihar.ac.in/"target="_blank">AKU, Patna</a></span></li>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a
                                    href="https://www.aicte-india.org/" target="_blank">AICTE</a></span>
                            </li>
                            <li><span style="font-family:arial,helvetica,sans-serif"><a
                                    href="https://jeemain.nic.in/webinfo/Public/Home.aspx"target="_blank">Joint Entrance Examination</a></span>
                            </li>
                            <!--	<li><a href="https://www.vidyalakshmi.co.in/Students/" target="_blank">Vidya Lakshmi Portal</a></li>-->
                        </ul>

                    </section>
                </div>
            </div>
    <div class="col-sm-5">
                    <div class="region region-home-5">
                        <section id="block-block-18" class="block block-block clearfix">

                            <h2 class="block-title">Latest Notice</h2>
                            <div class="rwsvtickercontent">
                                <div class="rwsvticker1">
                                 <ul>
                                        <li><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif"><a href="https://www.aicte-india.org/feedback/" target="_blank">Student and Faculty give feedback to AICTE</a></span></span></li>

                                     <c:forEach items="${noticeList}" var="notice">

                                     	<div>
                                     		<span class="label" style="border:  solid 1px #ccc; color: #000;">
                                     		<c:set var = "clazz"  value = "label label-warning"/>

                                     		 <c:if test = "${ notice.noticeType == 'News'}">
                                                      <c:set var = "clazz"  value = "label label-primary"/>
                                              </c:if>
                                     		<i class=""></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                                     		<c:forEach var="item" items="${Role}">
                                                <c:if test = "${'Admin' eq item}">
                                     		    <span class="label label-danger"><a href="<c:url value='/auth/${notice.uploadedFileName}/${notice.id}'/>">Delete</a></span>
                                             </c:if>
                                            </c:forEach>
                                     		<a href="http://localhost/${notice.id}/notice${notice.fileType}" target="_blank">${notice.headLine}</a>
                                     	</div>
                                     </c:forEach>
                                    </ul>

                                    </div>

                                </div>
                            </div><div class="more-link">
                                  <a href="http://localhost/category/notices">View all Notices</a></div>

                        </section>
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
                                                href="http://localhost/about/history">more</a>...</p>
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
                                                <div class="field-content"><a href="http://localhost/sites/default/files/sportkec.jpeg" target ="_blank"><img
                                                        typeof="foaf:Image" class="img-responsive"
                                                        src="http://localhost/sites/default/files/sportkec.jpeg"
                                                        width="330" height="225" alt=""
                                                        title="Pitch for a Better India"/></a></div>
                                            </div>
                                        </li>
                                        <li class="views-row views-row-2 views-row-even">
                                            <div class="views-field views-field-field-image">
                                                <div class="field-content"><a href="http://localhost/sites/default/files/sportkec1.jpeg"><img
                                                        typeof="foaf:Image" class="img-responsive"
                                                        src="http://localhost/sites/default/files/sportkec1.jpeg"
                                                        width="330" height="225" alt=""
                                                        title="Ace the Psychometric"/></a></div>
                                            </div>
                                        </li>
                                        <li class="views-row views-row-3 views-row-odd views-row-last">
                                            <div class="views-field views-field-field-image">
                                                <div class="field-content"><a href="http://localhost/sites/default/files/sportkec.jpeg"><img typeof="foaf:Image"
                                                                                                    class="img-responsive"
                                                                                                    src="http://localhost/sites/default/files/sportkec.jpeg"
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