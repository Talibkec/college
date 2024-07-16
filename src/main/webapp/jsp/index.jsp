<jsp:include page="header.jsp"/>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row scrollingnews">
    <marquee style="" loop="infinite">
        <div class="flex gap-1">
            <div class="flex gap-1">
                <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="0">
                <a href="https://youtu.be/cYMNEnq52p8" target="_blank">VIDEO FOR EVC AICTE </a>
            </div>

            <div class="flex gap-1">
                <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="0">
                <a href="https://tpo.keckatihar.in" target="_blank">Placement Portal of Katihar Engineering College </a>
            </div>

            <div class="flex gap-1">
                <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="0">
                <a href="https://payments.billdesk.com/bdcollect/bd/katiharenggclg/11292" target="_blank">Pay
                    Registration Fee here </a>
            </div>

            <div class="flex gap-1">
                <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="0">
                <a href="https://payments.billdesk.com/bdcollect/bd/katiharenggclg/11312" target="_blank">Pay Annual Fee
                    here </a>
            </div>

            <div class="flex gap-1">
                <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="0">
                <a href="https://payments.billdesk.com/bdcollect/bd/katiharenggclg/11313" target="_blank">Pay
                    Examination Fee here </a>
            </div>

            <div class="flex gap-1">
                <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="0">
                <a href="https://payments.billdesk.com/bdcollect/bd/katiharenggclg/11314" target="_blank">Pay Library
                    Fine here </a>
            </div>

            <div class="flex gap-1">
                <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="0">
                <a href="https://payments.billdesk.com/bdcollect/bd/katiharenggclg/11315" target="_blank">Pay
                    Miscellaneous Fee here </a>
            </div>


            <div class="flex gap-1">
                <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="0">
                <a href="https://www.aicte-india.org/feedback/" target="_blank">Click here to give feedback to
                    A.I.C.T.E</a>
            </div>


            <div class="flex gap-1">
                <img class="" src="/sites/noticeicon.gif" alt="" related="" image="" width="44" height="0">
                <a href="/jsp/facilities/payment.pdf" target="_blank">Click here for Fee Payment Tutorial</a>
            </div>


            <c:forEach items="${scrollingNoticeList}" var="scrollingNotice">

                <div class="flex gap-1">
                    <i class="fa fa-asterisk"></i>
                    <c:if test="${scrollingNotice.noticeAge <= 45}">
                        <img class="" src="/sites/noticeicon.gif" alt=" Related image" width="44" height="0"
                             style="display: inline"/>
                    </c:if>
                    <a href="/notice/${scrollingNotice.id}" style="display: inline"
                       target="_blank">${scrollingNotice.headLine}</a>

                    <c:forEach var="item" items="${Role}">
                        <c:if test="${'Admin' eq item}">
                            <span class="label label-danger"><a style="display: inline"
                                                                href="<c:url value='/auth/${scrollingNotice.uploadedFileName}/${scrollingNotice.id}' />">Delete</a></span>
                        </c:if>

                    </c:forEach>
                </div>

            </c:forEach>
        </div>
    </marquee>
</div>


<div id="default-carousel" class="relative w-full" data-carousel="slide">
    <!-- Carousel wrapper -->
    <div class="relative h-56 overflow-hidden rounded-lg md:h-96">
        <!-- Item 1 -->


        <c:forEach items="${imageList}" var="image">

            <div class="hidden duration-700 ease-in-out" data-carousel-item>
                <img src="/${image.imageSlideId}/slideImage${image.fileType}"
                     class="absolute block w-full -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2" alt="..." style="min-height:50vh">
                <c:forEach var="item" items="${Role}">
                    <c:if test="${'Admin' eq item}">
                        <span class="label label-danger"><a
                                href="<c:url value='/auth/deleteSlideImage/${image.imageSlideId}'/>">Delete</a></span>
                    </c:if>
                </c:forEach>
            </div>


        </c:forEach>


    </div>
    <!-- Slider indicators -->
    <div class="absolute z-30 flex -translate-x-1/2 bottom-5 left-1/2 space-x-3 rtl:space-x-reverse">


        <c:forEach items="${imageList}" var="image">

            <button type="button" class="w-3 h-3 rounded-full" style="background:#444" aria-current="true"
                    aria-label="Slide 1" data-carousel-slide-to="0"></button>


        </c:forEach>


    </div>
    <!-- Slider controls -->
    <button type="button"
            class="absolute top-0 start-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none"
            data-carousel-prev>
        <span style="background:#777"
              class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
            <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true"
                 xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M5 1 1 5l4 4"/>
            </svg>
            <span class="sr-only">Previous</span>
        </span>
    </button>
    <button type="button"
            class="absolute top-0 end-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none"
            data-carousel-next>
        <span style="background:#777"
              class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
            <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true"
                 xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="m1 9 4-4-4-4"/>
            </svg>
            <span class="sr-only">Next</span>
        </span>
    </button>
</div>


<!-- Starting of image slider. -->


<style>
    .headers {
        margin-top: 0;
        text-align: center;
        text-transform: uppercase;
        font-size: 24px;
        line-height: 40px;
        padding: 5px 10px 60px 10px;
        color: white;
    }

    .link {
        margin: 0.5rem;
    }
</style>

<div class="notice md:flex  justify-around p-4">

    <div class="p-5 basis-1/3  border-2 border-grey-800 importantlinks h-[50vh]" style="flex-basis: 33%;">
        <div class=" headers linkheader" style="background: url(/images/home-c-bg-title.png) no-repeat top center;">
            Important Links
        </div>
        <div class="link">
            &raquo; <a href="http://socialwelfare.bih.nic.in/"
                       target="_blank">Social Welfare Dept. Bhar</a>
        </div>
        <div class="link">
            &raquo; <a
                href="http://dstbihar.gov.in/" target="_blank">DST Bihar</a>
        </div>
        <div class="link">
            &raquo; <a href="http://www.educationbihar.gov.in/login.aspx?ReturnUrl=%2fDefault.aspx">Education Dept.
            Bihar</a>
        </div>
        <div class="link">
            &raquo; <a
                href="http://gov.bih.nic.in/" target="_blank">Govt. of Bihar</a>
        </div>
        <div class="link">
            &raquo; <a
                href="http://bceceboard.bihar.gov.in/" target="_blank">BCECEB</a>
        </div>
        <div class="link">
            &raquo; <a
                href="http://akubihar.ac.in/" target="_blank">AKU, Patna</a>
        </div>
        <div class="link">
            &raquo; <a
                href="https://www.aicte-india.org/" target="_blank">AICTE</a>
        </div>
        <div class="link">
            &raquo; <a
                href="https://jeemain.nic.in/webinfo/Public/Home.aspx" target="_blank">Joint Entrance Examination</a>
        </div>
    </div>


    <div class="relative p-5 basis-1/3 border-2 border-grey-800 notice" style="flex-basis: 33%;">
        <div class=" headers linkheader" style="background: url(/images/home-c-bg-title.png) no-repeat top center;">
            Latest Notices
        </div>
        <marquee direction="up" loop="infinite" onmouseover="this.pause();" onmouseout="this.resume();"
                 scrollamount="2">


            <c:forEach items="${noticeList}" var="notice">
                <c:if test="${ notice.noticeType == 'News' or notice.noticeType == 'Notice'}">
                    <div>
                       <span class="label" style="border:  solid 1px #ccc; color: #000;">
                       <c:set var="clazz" value="label label-warning"/>

                        <c:if test="${ notice.noticeType == 'News'}">
                            <c:set var="clazz" value="label label-primary"/>
                        </c:if>
                       <i class=""></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>

                        <c:forEach var="item" items="${Role}">
                            <c:if test="${'Admin' eq item}">
                                <span class="bg-red-600 rounded-xl m-1 p-1 text-white"><a
                                        href="<c:url value='/auth/delNotice/${notice.id}'/>">Delete</a></span>
                            </c:if>
                        </c:forEach>
                        <style>
                            .underline {
                                text-decoration: underline;
                                cursor: pointer;
                            }

                            .underline:hover {
                                color: blue;
                            }
                        </style>
                        <a onclick="gotoNotice('notice/${notice.id}' , '${notice.link}')" target="_blank"
                           class="underline">${notice.headLine} </a>

                        <script>
                            function gotoNotice(id, link) {
                                if (link != '') {
                                    window.location.href = link;
                                } else {
                                    window.location.href = id;
                                }

                            }
                        </script>


                    </div>
                </c:if>
            </c:forEach>


        </marquee>
        <div class="w-full absolute left-0 bottom-0 flex justify-center">
            <a href="/category/notices">
            <button type="button"
                    class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">
                View All Notices
            </button>
        </a>
        </div>

    </div>

    <div class="relative p-5 basis-1/3 tenders border-2 border-grey-800" style="flex-basis: 33%;">
        <div class=" headers linkheader" style="background: url(/images/home-c-bg-title.png) no-repeat top center;">
            Latest Tenders
        </div>
        <marquee direction="up" loop="infinite" onmouseover="this.stop();" onmouseout="this.start();">
            <c:forEach items="${noticeList}" var="notice">
                <c:if test="${notice.noticeType == 'Tendor'}">
                    <div>
                       <span class="label" style="border:  solid 1px #ccc; color: #000;">
                       <c:set var="clazz" value="label label-warning"/>

                        <c:if test="${ notice.noticeType == 'Tender'}">
                            <c:set var="clazz" value="label label-primary"/>
                        </c:if>
                       <i class=""></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                        <c:forEach var="item" items="${Role}">
                            <c:if test="${'Admin' eq item}">
                                <span class="bg-red p-1 text-white"><a
                                        href="<c:url value='/auth/${notice.uploadedFileName}/${notice.id}'/>">Delete</a></span>
                            </c:if>
                        </c:forEach>
                        <a href="/${notice.id}/notice${notice.fileType}" target="_blank">${notice.headLine}</a>

                    </div>
                </c:if>
            </c:forEach>
        </marquee>
        <div class="w-full absolute bottom-0 left-0  flex justify-center">
            <a href="/category/tenders">
            <button type="button"
                    class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">
                View All Tenders
            </button>
        </a>
        </div>
    </div>


</div>


<style>
    .gtmaincontenthome {
        width: 100%;
        clear: both;
        overflow: hidden;
        background: url(/images/college.webp) no-repeat top center;
        background-size: cover;
        min-height: 300px;
        padding: 40px 0;
    }
</style>

<div class="md:flex justify-between">
    <div class="gtmaincontenthome basis-1/2" style="min-width:50%">
        <div class="">
            <div class="row">

                <div class="col-sm-6">
                    &nbsp;
                </div>
            </div>
        </div>
    </div>


    <div class="p-5 basis-1/2" style="min-width:50%">
        <a id="main-content"></a>
        <h1 class="p-5 text-3xl" style="color: rgb(34, 34, 34);">About KEC</h1>

        <div class="region region-content">
            <section id="block-system-main" class="block block-system clearfix">


                <article id="node-1" class="node node-page clearfix" about="/home" typeof="foaf:Document">
                    <header>
                        <span property="dc:title" content="About Us" class="rdf-meta element-hidden"></span>
                    </header>
                    <div class="field field-name-body field-type-text-with-summary field-label-hidden">
                        <div class="field-items">
                            <div class="field-item even" property="content:encoded"><p class="p-3" style=""><strong>Katihar
                                Engineering College (KEC), Katihar </strong>with the advent of technology and the
                                emergence of notable education in our society, had decided to adapt the prevailing need
                                of light. In the mission to achieve the eminence in the field, the establishment of
                                Katihar Engineering College was laid in the year 2016. The college is enriched with a
                                pollyannaish environment, highly motivated and enthusiastic students guided by well
                                qualified professional from different prestigious colleges of our country. The college
                                was established with three branches- Civil Engineering, Mechanical Engineering and
                                Computer Science and Engineering. Each branch has a number of renowned professors with
                                high qualifications and experience in teaching or their respective industries. The
                                college consists of a central library, Computer Labs, Training and Placement Cell,
                                Programmer's Club etc, with the exceptional support staffs and infrastructure. All the
                                labs and other infrastructure is in highly conditional states serving at their best to
                                provide the best facilities to all the members of the college. Student of the college
                                have exhibited their skill at different platform and gathered many awards from premium
                                institutes. It is situated 3 km from Katihar Railway Station and there is a good
                                transportation facility available from there. Currently, the own campus for the college
                                is under-construction in Hajipur, Katihar near NH 31. It is expected to complete soon.<a
                                        href="/about/history">more</a>...</p>
                            </div>
                        </div>
                    </div>
                </article>

            </section>
        </div>
    </div>
</div>


<div class="gt-galleryvideosection p-3 q" style="margin-top:10px;margin-bottom:10px">


    <section id="block-views-nodequeue-10-block" class="block block-views clearfix">

        <h2 class="text-3xl p-5 block-title w-full p-3">Achievements</h2>

        <div class="view view-nodequeue-10 view-id-nodequeue_10 view-display-id-block view-dom-id-092f3c9d9a1a277eabf025598077161b">


            <div class="view-content ">
                <c:forEach items="${achievementImageList}" var="achievementImage">
                    <div class="item-list ">
                        <ul class="achievementSlider">
                            <li class="views-row views-row-1 views-row-odd views-row-first">
                                <div class="views-field views-field-field-image">
                                    <div class="field-content">
                                        <a href="/${achievementImage.achievementSlideId}/achievementImage${achievementImage.fileType}"
                                           target="_blank"><img
                                                typeof="foaf:Image" class="img-responsive"
                                                src="/${achievementImage.achievementSlideId}/achievementImage${achievementImage.fileType}"
                                                width="400" height="500" alt=""
                                                title="${achievementImage.achievementCaption} "/></a>
                                        <span class="caption text-center w-full p-3">
                                                ${achievementImage.achievementCaption}
                                        </span>
                                        <c:forEach var="item" items="${Role}">
                                            <c:if test="${'Admin' eq item}">
                                                                 <span class="label label-danger m-2">
                                                                 <a style="display: inline"
                                                                    class="focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
                                                                    href="<c:url value='/auth/deleteAchievementImage/${achievementImage.achievementSlideId}' />">Delete</a></span>
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

<div class="gt-galleryvideosection" style="margin-top:10px;margin-bottom:10px">
    <div class="">
        <div class="">
            <div class="">
                <div class="region region-home-gallery">
                    <section id="block-views-nodequeue-10-block" class="block block-views clearfix">

                        <h2 class="text-3xl p-5">Gallery</h2>


                        <div class="">


                            <div class="view-content ">
                                <div class="item-list">
                                    <ul class="md:flex w-full justify-around">
                                        <li class="views-row views-row-1 views-row-odd views-row-first">
                                            <div class="views-field views-field-field-image">
                                                <div class="field-content"><a href="/images/sportkec.jpeg"
                                                                              target="_blank"><img
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
                                                <div class="field-content"><a href="/images/sportkec.jpeg"><img
                                                        typeof="foaf:Image"
                                                        class="img-responsive"
                                                        src="/images/sportkec.jpeg"
                                                        width="330"
                                                        height="225" alt=""
                                                        title="Machine Assembly and Disassembly Workshop"/></a>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                    <div class="w-full flex justify-center">
                                        <a lass="box-link border-1 border-grey-800" style="border: 2px solid grey;
                                                padding: 10px;" href="/gallery/photogallery">
                                            View All </a>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </section>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="region region-home-video" style="">
                    <section id="block-block-20" class="block block-block clearfix">

                        <h2 class="text-3xl p-5">Video Gallery</h2>

                        <ul>
                            <li>
                                <div class="views-field views-field-field-image">
                                    <div class="p-5 field-content embed-responsive embed-responsive-16by9">
                                        <iframe frameborder="0" height="200"
                                                src="https://www.youtube.com/embed/1zoSI1yATFI" width="200"></iframe>
                                    </div>
                                </div>
                            </li>
                        </ul>


                        <div class="flex justify-center"><a class="box-link" href="/gallery/mediagallery">View All </a>
                        </div>


                    </section>
                </div>
            </div>

        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

