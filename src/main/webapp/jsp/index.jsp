<jsp:include page="header.jsp"/>
  <div id="demo-content">
    
    		<div id="loader-wrapper">
    			<div id="loader"></div>
    
    			<div class="loader-section section-left"></div>
                <div class="loader-section section-right"></div>
    
    		</div>
    		<style>
    		#demo-content{
    		padding-bottom:1px
    		}
    		</style>
  
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:forEach var="item" items="${Role}">
        <c:if test = "${'Admin' eq item}">
        <strong><i class="fa fa-news"></i> <a href="/auth/uploadfile/upload">Upload Notice / News</a></strong>|
        <strong><i class="fa fa-news"></i> <a href="/auth/sendMessage">Send Message</a></strong>|
        <strong><i class="fa fa-news"></i> <a href="auth/uploadfile/uploadslideimage">Upload Slider Image / Gallery Image</a></strong>|
        <strong><i class="fa fa-news"></i> <a href="auth/uploadfile/addfaculty">Add Faculty</a></strong>|
        <strong><i class="fa fa-news"></i> <a href="auth/uploadfile/updateAdministration">Assign Administration Role</a></strong>
        |<strong><i class="fa fa-news"></i> <a href="auth/uploadfile/achievementslideimage">Add a Achievement</a></strong>
        |<strong><i class="fa fa-news"></i> <a href="/auth/hodincharge">Assign-Revoke HOD</a></strong>

        </c:if>
    </c:forEach>
    <div class="row scrollingnews">
        <marquee style=  loop="infinite" onmouseover="this.stop();" onmouseout="this.start();">
            <ul style="padding-top:3%">
                <li style="display:inline;">
                    <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="40">
                    <a href="https://tpo.keckatihar.in" target="_blank">Placement Portal of Katihar Engineering College </a>
                </li>

                <li style="display:inline;">
                    <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="40">
                    <a href="https://payments.billdesk.com/bdcollect/bd/katiharenggclg/11292" target="_blank">Pay Registration Fee here </a>
                </li>

                <li style="display:inline;">
                    <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="40">
                    <a href="https://payments.billdesk.com/bdcollect/bd/katiharenggclg/11312" target="_blank">Pay  Annual Fee here </a>
                </li>

                <li style="display:inline;">
                    <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="40">
                    <a href="https://payments.billdesk.com/bdcollect/bd/katiharenggclg/11313" target="_blank">Pay Examination Fee  here </a>
                </li>

                <li style="display:inline;">
                    <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="40">
                    <a href="https://payments.billdesk.com/bdcollect/bd/katiharenggclg/11314" target="_blank">Pay Library Fine here </a>
                </li>

                <li style="display:inline;">
                    <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="40">
                    <a href="https://payments.billdesk.com/bdcollect/bd/katiharenggclg/11315" target="_blank">Pay  Miscellaneous Fee here </a>
                </li>



               <li style="display:inline;">
                  <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="40">
                  <a href="https://www.aicte-india.org/feedback/" target="_blank">Click here to give feedback to A.I.C.T.E</a>
               </li>



                <span>&nbsp;</span>
                     
               <li style="display:inline">
                                 <img class="" src="/sites/noticeicon.gif" alt="" related="" image"="" width="44" height="40">
                                 <a href="/jsp/facilities/payment.pdf" target="_blank">Click here for Fee Payment Tutorial</a>
                </li>



                <c:forEach items="${scrollingNoticeList}" var="scrollingNotice">

                 <li style= "display: inline;">
                 <i class="fa fa-asterisk"></i>
                 <c:if test = "${scrollingNotice.noticeAge <= 45}">
                    <img class="" src="/sites/noticeicon.gif" alt=" Related image" width="44" height="40" style="display: inline" />
                 </c:if>
                 <a href="/notice/${scrollingNotice.id}" style="display: inline" target="_blank">${scrollingNotice.headLine}</a>

                 <c:forEach var="item" items="${Role}">
                     <c:if test = "${'Admin' eq item}">
                              <span class="label label-danger"><a style="display: inline" href="<c:url value='/auth/${scrollingNotice.uploadedFileName}/${scrollingNotice.id}' />">Delete</a></span>
                     </c:if>

                 </c:forEach>
                 </li>

                </c:forEach>
               </ul>
            </marquee>
    </div>

     <!-- Starting of image slider. -->
<style>
<c:forEach items ="${imageList}" var ="image">
.slide${image.imageSlideId}{
    background-image : url(/${image.imageSlideId}/slideImage${image.fileType});
}</c:forEach>
</style>
    <div class = "imageSlider">
<c:forEach items="${imageList}" var="image">
        <div class = "left-image-holder"></div>
        <div class = "imageSlide slide${image.imageSlideId}">
            <div class = "slider-content">
                <span class = "imageText">${image.caption}</span>
                <c:forEach var="item" items="${Role}">
                     <c:if test = "${'Admin' eq item}">
                          <span class="label label-danger"><a href="<c:url value='/auth/deleteSlideImage/${image.imageSlideId}'/>">Delete</a></span>
                      </c:if>
                </c:forEach>

           </div><div class = "right-image-holder"></div>
        </div>

</c:forEach>
    <div class="gt-newsblocksection">
        <div class="container">
            <div class="row">
                            <div class="col-sm-3">
                    <div class="region region-home-4">
                        <section id="block-block-12" class="block block-block clearfix">

                            <h2 class="block-title">Important Links</h2>

                            <ul class="borderneeded">
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
                                    <div class="rwsvticker1" style="height:260px">
                                     <ul>
                                            <li><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif"><a href="https://www.aicte-india.org/feedback/" target="_blank">Student and Faculty give feedback to AICTE</a></span></span></li>
                                            <li><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif"><a href="/jsp/facilities/payment.pdf" target="_blank">Fee Payment Tutorial through SBI Collect</a></span></span></li>

                                         <c:forEach items="${noticeList}" var="notice">
                                         <c:if test = "${ notice.noticeType == 'News' or notice.noticeType == 'Notice'}">
                                            <div>
                                                <span class="label" style="border:  solid 1px #ccc; color: #000;">
                                                <c:set var = "clazz"  value = "label label-warning"/>

                                                 <c:if test = "${ notice.noticeType == 'News'}">
                                                          <c:set var = "clazz"  value = "label label-primary"/>
                                                   </c:if>
                                                <i class=""></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                                                
                                                <c:forEach var="item" items="${Role}">
                                                    <c:if test = "${'Admin' eq item}">
                                                    <span class="label label-danger"><a href="<c:url value='/auth/delNotice/${notice.id}'/>">Delete</a></span>
                                                 </c:if>
                                                </c:forEach>
                                                <a href="notice/${notice.id}" target="_blank">${notice.headLine}</a>
                                               
                                              
                                            </div>
                                          </c:if>
                                         </c:forEach>
                                        </ul>


                                    </div>
                                     <div class="" style="margin-left:33%;margin-top:9%"><a class="box-link" href="/category/notices">View all Notices</a></div>

                                </div>


                            </section>

                        </div></div>



              <div class="col-sm-3">

                                  <div class="region region-home-5">
                                          <section id="block-block-12" class="block block-block clearfix">
              
                                              <h2 class="block-title">Latest Tenders</h2>
                                              <div class="rwsvtickercontent1">
                                                  <div class="rwsvticker1" style="height:260px">
                                                   <ul>


                                                       <c:forEach items="${noticeList}" var="notice">
                                                       <c:if test = "${notice.noticeType == 'Tendor'}">
                                                          <div>
                                                              <span class="label" style="border:  solid 1px #ccc; color: #000;">
                                                              <c:set var = "clazz"  value = "label label-warning"/>
              
                                                               <c:if test = "${ notice.noticeType == 'Tender'}">
                                                                        <c:set var = "clazz"  value = "label label-primary"/>
                                                                 </c:if>
                                                              <i class=""></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                                                              <c:forEach var="item" items="${Role}">
                                                                  <c:if test = "${'Admin' eq item}">
                                                                  <span class="label label-danger"><a href="<c:url value='/auth/${notice.uploadedFileName}/${notice.id}'/>">Delete</a></span>
                                                               </c:if>
                                                              </c:forEach>
                                                              <a href="/${notice.id}/notice${notice.fileType}" target="_blank">${notice.headLine}</a>

                                                          </div>
                                                         </c:if>
                                                         </c:forEach>
                                                        </ul>
                                                        </div>
                                                                            <div style="margin-top:18%;margin-left:28%"><a class="box-link" href="/category/tenders">View all Tenders</a></div>


                                                    </div>
                                                </div>

                                            </section>
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
                                        <div class="field-item even" property="content:encoded"><p style="padding: 40px;"><strong>Katihar Engineering College (KEC), Katihar </strong>with the advent of technology and the emergence of notable education in our society, had decided to adapt the prevailing need of light. In the mission to achieve the eminence in the field, the establishment of Katihar Engineering College was laid in the year 2016. The college is enriched with a pollyannaish environment, highly motivated and enthusiastic students guided by well qualified professional from different prestigious colleges of our country. The college was established with three branches- Civil Engineering, Mechanical Engineering and Computer Science and Engineering. Each branch has a number of renowned professors with high qualifications and experience in teaching or their respective industries. The college consists of a central library, Computer Labs, Training and Placement Cell, Programmer's Club etc, with the exceptional support staffs and infrastructure. All the labs and other infrastructure is in highly conditional states serving at their best to provide the best facilities to all the members of the college. Student of the college have exhibited their skill at different platform and gathered many awards from premium institutes. It is situated 3 km from Katihar Railway Station and there is a good transportation facility available from there. Currently, the own campus for the college is under-construction in Hajipur, Katihar near NH 31. It is expected to complete soon.<a
                                                    href="/about/history">more</a>...</p>
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


    <div class="gt-galleryvideosection" style="margin-top:10px;margin-bottom:10px">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="region region-home-gallery">
                        <section id="block-views-nodequeue-10-block" class="block block-views clearfix">

                            <h2 class="block-title">Achievements</h2>

                            <div class="view view-nodequeue-10 view-id-nodequeue_10 view-display-id-block view-dom-id-092f3c9d9a1a277eabf025598077161b">


                                <div class="view-content ">
                                <c:forEach items="${achievementImageList}" var="achievementImage">
                                <div class="item-list ">
                                        <ul class="achievementSlider">
                                            <li class="views-row views-row-1 views-row-odd views-row-first">
                                                <div class="views-field views-field-field-image">
                                                    <div class="field-content"><a href="/${achievementImage.achievementSlideId}/achievementImage${achievementImage.fileType}" target ="_blank"><img
                                                            typeof="foaf:Image" class="img-responsive"
                                                            src="/${achievementImage.achievementSlideId}/achievementImage${achievementImage.fileType}"
                                                            width="80%" height="80%" alt=""
                                                            title="${achievementImage.achievementCaption} "/></a>
                                                            <span class="caption">
                                                               ${achievementImage.achievementCaption}
                                                            </span>
                                                          <c:forEach var="item" items="${Role}">
                                                                    <c:if test = "${'Admin' eq item}">
                                                                 <span class="label label-danger">
                                                                 <a style="display: inline" href="<c:url value='/auth/deleteAchievementImage/${achievementImage.achievementSlideId}' />">Delete</a></span>
                                                              </c:if>
                                                          </c:forEach>
                                                            </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="gt-galleryvideosection" style="margin-top:10px;margin-bottom:10px">
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
                                                        <div class="field-content"><a href="/images/sportkec.jpeg" target ="_blank"><img
                                                                typeof="foaf:Image" class="img-responsive"
                                                                src="/images/sportkec.jpeg"
                                                                width="330" height="225" alt=""
                                                                title="Pitch for a Better India"/></a></div>
                                                    </div>
                                                </li>
                                                <li class="views-row views-row-2 views-row-even">
                                                    <div class="views-field views-field-field-image">
                                                        <div class="field-content"><a href="/images/sportkec1.jpeg"><img
                                                                typeof="foaf:Image" class="img-responsive"
                                                                src="/images/sportkec1.jpeg"
                                                                width="330" height="225" alt=""
                                                                title="Ace the Psychometric"/></a></div>
                                                    </div>
                                                </li>
                                                <li class="views-row views-row-3 views-row-odd views-row-last">
                                                    <div class="views-field views-field-field-image">
                                                        <div class="field-content"><a href="/images/sportkec.jpeg"><img typeof="foaf:Image"
                                                                                                                        class="img-responsive"
                                                                                                                        src="/images/sportkec.jpeg"
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
                                        <a href="/gallery/photogallery">
                                            View All </a>
                                    </div>


                                </div>
                            </section>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="region region-home-video" style="">
                            <section id="block-block-20" class="block block-block clearfix">

                                <h2 class="block-title">Video Gallery</h2>

                                <ul>
                                    <li>
                                        <div class="views-field views-field-field-image">
                                            <div class="field-content embed-responsive embed-responsive-16by9">
                                                <iframe frameborder="0" height="551"
                                                        src="https://www.youtube.com/embed/1zoSI1yATFI" width="1050"></iframe>
                                            </div>
                                        </div>
                                        </li></ul>
                                        <div class="views-field views-field-title" style="margin-left:32%;">College Tour<br/></div>






                                                   <div style="margin-top:4%">           <a class="box-link" href="/gallery/mediagallery" style="margin-left:39%;margin-top:10%">View All </a></div>




                            </section>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    <jsp:include page="footer.jsp"/>

