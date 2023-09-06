
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
                                               <c:if test = "${'HOD' eq item and UserName eq 'hodeee'}">                              <span class="label label-danger"><a style="display: inline" href="<c:url value='/hod/deleteNotice/${scrollingNotice.id}' />">Delete</a></span>
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
                <div class="col-md-10 col-sm-8"><h3>About  Electronics Engineering (VLSI Design and Technology )</h3></div>

            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
            <jsp:include page="../sidebars/ecesidebar.jsp"/>
            <div class="col-md-6">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                    <div style="font-family: Arial, Helvetica, sans-serif; font-size: 16px; text-align: justify;"><h3>
                        Introduction</h3>
                        B.Tech. - Electronics Engineering (VLSI Design and Technology) is a 4-year program designed to
                        prepare engineering graduates who have comprehensive knowledge about Integrated circuit
                        designs, which is usually known as chip design. It aims to prepare skilled graduates who know the
                        key aspects of VLSI Design which is required in VLSI domain Industries.
                        Through B.Tech. - Electronics Engineering (VLSI Design and Technology) program, graduates will
                        develop an in-depth understanding of semiconductor devices, VLSI circuit design and verification,
                        FPGA design, and fabrication process, along with hands-on experience with leading EDA
                        (Electronic Design & Automation) tools. The evergreen VLSI domain is for the design and
                        verification of electronics systems and circuits and its applications are found in areas like
                        Communications, Signal & Image Processing, Space Research and Automation Industry.

                        • The India Semiconductor Mission (ISM) an initiative by Govt of India for growth
                        of VLSI will help to have more placement opportunities in India
                        • The program includes hands-on experience on EDA (Software) tools
                        • Includes industrial internship
                        • Experienced & Skilled Faculty
                        • Advance Labs equipped


                        USPs of B. Tech. in Electronics Engineering (VLSI Design and Technology)
                        • New program launched in accordance with India Semiconductor Mission
                        (ISM)
                        • Highest paying, high demand professional for semiconductor chip industry
                        • Research labs for semiconductor chip design and fabrication equipped with
                        B2spice, RF Spice, Xilinx software and FPGA boards.
                        Career Opportunities
                        • Design engineer
                        • Verification engineer
                        • CAD engineer
                        • Application Engineer
                        • Fabrication Engineer
                        • Research & Development Engineer
                        Prominent Recruiters
                        • Synopsys
                        • Mentor Graphics
                        • Cadence
                        • Qualcomm
                        • Intel Technologies
                        • HCL Corporation
                        • einfochips
                        • Texas Instruments
                        • Synapse Design
                        • AMD-Xilinx India Technology
                        • Samsung
                        LABORATORIES
                        DIGITAL ELECTRONICS & MICROPROCESSOR LAB
                        This lab provides the basic exposure to sequential and combinations circuits-based experiments
                        and 8086 microprocessor-based programming using kit-based experiments.
                        ELECTRONIC CIRCUIT DESIGN LAB
                        This laboratory aims to familiarize the student with working of electronic devices and designing of
                        basic electronic circuits. The students analyse the circuit outputs and learns to record data.
                        VLSI & EMBEDDED SYSTEM DESIGN LAB
                        This Lab provides competency areas in VLSI Design & Embedded Systems including IC designing
                        concepts & Embedded system designing concepts to analyse and design complex systems
                        containing hardware and software components.
                        SIMULATION LAB
                        The Simulation lab emphasizes mainly on simulation of electrical systems such as Neural
                        networks, Control systems, MATLAB software, B2Spice simulation etc.
                    </div>
                </div>
            </div>


        <div class="heroCol col-sm-3">
                                <div class="panel panel-default">
                                <div class="panel-heading">
                                <strong>

                                                                                                               <i class="fa fa-news"></i> Latest News / Updates</strong>
                                                                                                           <c:forEach var="item" items="${Role}">
                                                                                                               <c:if test = "${'HOD' eq item  and UserName eq 'hodeee'}">

                                                                                                               <strong><i class="fa fa-news"></i> <a href="/hod/uploadfile/upload">Upload Notice / News</a></strong>

                                                                                                           </c:if>
                                                                                                           </c:forEach></div>
                                                                                                       <div class="panel-body" style="max-height: 500px;">
                                                                                                           <ul class="listUpdates" id="noticeBoardItems">



                                                                                                               <c:forEach items="${noticeList}" var="notice">
                                                                                                                   <li>
                                                                                                                       <div>
                                                                                                            		<span class="label" style="border:  solid 1px #ccc; color: #000;">
                                                                                                            		<c:set var = "clazz"  value = "label label-warning"/>

                                                                                                            		 <c:if test = "${ notice.noticeType == 'News'}">
                                                                                                                             <c:set var = "clazz"  value = "label label-primary"/>
                                                                                                                     </c:if>

                                                                                                            		<i class="fa fa-clock-o"></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                                                                                                                          <c:forEach var="item" items="${Role}">
                                               <c:if test = "${'HOD' eq item and UserName eq 'hodeee'}">
                                                                                                                                                                              <span class="label label-danger"><a href="<c:url value='/hod/deleteNotice/${notice.id}' />">Delete</a></span>
                                                                                                                           </c:if>
                                                                                                                           </c:forEach>
                                                                                                                           <a href="/${notice.id}/notice${notice.fileType}" target="_blank">${notice.headLine}</a>
                                                                                                                       </div>
                                                                                                                   </li>
                                                                                                               </c:forEach>
                                                                                                           </ul>
                                                                                                           <a href="/department/eee/notice" class="btn btn-default">View All<i class="fa fa-double-angle-right"></i></a>
                                </div>
                                </div>
                                </div>
                                </div>
                            </div>
   </div>


<jsp:include page="/jsp/footer.jsp"/>
