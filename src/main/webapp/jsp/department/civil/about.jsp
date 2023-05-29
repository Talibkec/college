
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/jsp/header.jsp"/>

 <div class="row scrollingnews">
         <marquee style=  loop="infinite" onmouseover="this.stop();" onmouseout="this.start();">
             <ul>

                 <c:forEach items="${scrollingNoticeList}" var="scrollingNotice">
                  <li style= "display: inline;">
                  <i class="fa fa-asterisk"></i>
                  <c:if test = "${scrollingNotice.noticeAge <= 45}">
                     <img class="" src="/sites/noticeicon.gif" alt="" Related image" width="44" height="40" style="display: inline" />
                  </c:if>
                  <a href="/${scrollingNotice.id}/notice${scrollingNotice.fileType}" style="display: inline" target="_blank">${scrollingNotice.headLine}</a>
                  <c:forEach var="item" items="${Role}">
                                               <c:if test = "${'HOD' eq item and UserName eq 'hodcivil'}">
                                                                      <span class="label label-danger"><a style="display: inline" href="<c:url value='/hod/deleteNotice/${scrollingNotice.id}' />">Delete</a></span>
                      </c:if>
                  </c:forEach>
                  </li>

                 </c:forEach>
                </ul>
             </marquee>
     </div>




 <div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
         <div class="container">
             <div class="row">


                                   <marquee style=  loop="infinite" onmouseover="this.stop();" onmouseout="this.start();">                 <ul>


                                               <c:forEach items="${blinkMessage}" var="scrollingNotice">


<span class="blinking aligning">
                          <li style="display:inline;font-size:140%" >
                                  <strong>      ${scrollingNotice.headLine}  </strong>
                                                <c:forEach var="item" items="${Role}">
                                               <c:if test = "${'HOD' eq item and UserName eq 'hodcivil'}">
                                                 <span class="label label-danger"><a style="display: inline" href="<c:url value='/hod/deleteNotice/${scrollingNotice.id}' />">Delete</a></span>
                                                    </c:if>
                                                </c:forEach>
</li>
                                                                </span>
                                               </c:forEach>






                                              </ul>  </marquee>
</div>
                                   </div>







                 <div class="col-md-2 col-sm-4" style="font-size: 24px; line-height: 40px;"><span
                         style="display: inline-block; line-height: 40px; vertical-align: middle;"><i
                     </i> </span> <a
                         href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2F#%2Fdepartment-of-civil-engineering%2Fabout-civil-engineering%2F"
                         target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i
                     </i></a> <a
                         href="https://twitter.com/home?status=About+Civil+Engg.+-+https%3A%2F%2F#%2Fdepartment-of-civil-engineering%2Fabout-civil-engineering%2F"
                         target="_blank" style="font-size: 30px; color: #333; vertical-align: middle;"><i
                     </i> </a></div>
             </div>
         </div>
     </div>
     <div class="container">

        <h3>About Civil Engg.</h3>


         <ul class="breadcrumb" style="background: none">
             <li><a href="/"><i class="fa fa-home"></i> Home</a></li>
             <li class="active">About Civil Engg.</li>

         </ul>



         <div class="row">



             <jsp:include page="../sidebars/cesidebar.jsp"/>

                         <div class="col-md-9">
                             <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">

                             <div>

                            <!-- ********************* New content ********************** -->
                                                                                                                                               <div class="heroCol col-sm-5 wrapping-text-div">
                                                                                                                                                                        <div class="panel panel-default">
                                                                                                                                                                        <div class="panel-heading">
                                                                                                                                                                        <strong>
                                                                                                                                                                         <i class="fa fa-news"></i> Latest News / Updates</strong>
                                                                                                                                                                                                                                                   <c:forEach var="item" items="${Role}">
                                                                                                                                                                                                                                                                          <c:if test = "${'HOD' eq item and UserName eq 'hodcivil'}">
                                                                                                                                                                                                                                                                 <strong><i class="fa fa-news"></i> <a href="/hod/uploadfile/upload">Upload Notice / News</a></strong>

                                                                                                                                                                                                                                                             </c:if>
                                                                                                                                                                                                                                                             </c:forEach></div>
                                                                                                                                                                                                                                                         <div class="panel-body" style="max-height: 500px;font-family: georgia, sans-serif;font-size: 14px;text-align: justify;line-height: 1.3;">
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

                                                                                                                                                                                                                                                              		 <c:if test = "${ notice.noticeType == 'CIVILNews'}">
                                                                                                                                                                                                                                                                               <c:set var = "clazz"  value = "label label-primary"/>
                                                                                                                                                                                                                                                                       </c:if>
                                                                                                                                                                                                                                                              		<i class="fa fa-clock-o"></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                                                                                                                                                                                 <c:forEach var="item" items="${Role}">
                                                                                                                                                                                                                                  <c:if test = "${'HOD' eq item and UserName eq 'hodcivil'}">
                                                                                                                                                                                                                                   <span class="label label-danger"><a href="<c:url value='/hod/deleteNotice/${notice.id}' />">Delete</a></span>
                                                                                                                                                                                                                                                                             </c:if>
                                                                                                                                                                                                                                                                             </c:forEach>
                                                                                                                                                                                                                                                                            <a href="/${notice.id}/notice${notice.fileType}" target="_blank">${notice.headLine}</a>
                                                                                                                                                                                                                                                                         </div>
                                                                                                                                                                                                                                                                     </li>
                                                                                                                                                                                                                                                                 </c:forEach>
                                                                                                                                                                                                                                                             </ul>
                                                                                                                                                                                                                                                             <a href="/department/civil/notice" class="btn btn-default">View All<i class="fa fa-double-angle-right"></i></a>
                                                                                                                                                                        </div>
                                                                                                                                                                        </div>
                                                                                                                                                                        </div>
                                                                                                                                                                        </div>
                                                                                                                                                                    </div>
                                                                                                                                               <!-- ********************* New content ********************** -->
                                <div id="ctl00_ContentPlaceHolder1_div_1 " style="font-family: georgia, sans-serif; font-size: 16px; text-align: justify;" ><p><strong>INTRODUCTION</strong></p><p>Katihar Engineering College,katihar was established in 2016 and the Civil Engineering Department is part of the institute since its inception. The Department has grown tremendously over the years and is now recognized as one of the major engineering departments in the state of Bihar. Department of civil Engineering offers Bachelors in Technology, have an annual intake 60. The Department of Civil Engineering with its multifaceted faculty from different field like Geotechnical, Environmental and structural engineering maintains high quality teaching and instruction at UG level.</span><br/>
                                                                                                                   Currently it comprises of various sub divisions like <br/>(1)Building Technology and Construction Management (BTCM)<br/> (2) Environmental and water resources engineering (EWRE)<br/>(3) Geotechnical engineering (GT)<br/>(4) Structural engineering (ST)<br/> (5) Transportation engineering (TR) .
                                        </p>
                                </div>
                             </div>

                         </div>

                         </div>

                 </div>
             <jsp:include page="/jsp/footer.jsp"/>

