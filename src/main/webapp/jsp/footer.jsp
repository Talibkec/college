<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><link type="text/css" rel="stylesheet" href="/css/style.css" media="all"/>
<div class="gt-footersection">
    <footer class="footer">
        <div class="region region-footer md:flex w-full justify-between  " id="footer-flex">
            <section id="block-block-3" class="flex justify-center ">


                <p><img alt="" src="/images/logo.png" style="height:90px; width:240px" />
                </p>

            </section>


            <section id="block-block-2" class="block block-block clearfix">

                <h2 class="block-title"><kec:setting key="site.short_name">KEC Katihar</kec:setting></h2>

                <p><span style="font-family:arial,helvetica,sans-serif"><kec:setting key="college.name">Katihar Engineering College, Katihar</kec:setting><br />
                        <kec:setting key="contact.address_line1">Hirdayganj</kec:setting><br />
                        <kec:setting key="contact.address_line2">Hajipur 854109</kec:setting><br />
                        <kec:setting key="contact.address_line3">Bihar India</kec:setting><br />
                        Ph.: <kec:setting key="contact.phone">06452-239122</kec:setting></span></p>

            </section>
            <section id="block-block-4" class="block block-block clearfix">


                <kec:links list="footer" var="footerLinks"/><c:choose><c:when test="${not empty footerLinks}"><c:forEach items="${footerLinks}" var="l" varStatus="s"><a href="${kec:text(l.url)}"${l.openNewTab ? ' target="_blank"' : ''}>
                    <p class="p-1 block-title"> &raquo; ${kec:text(l.label)}</p>
                </a><c:if test="${not s.last}">
                </c:if></c:forEach></c:when><c:otherwise><a href="/legal/contactus">
                    <p class="p-1 block-title"> &raquo; Contact Us</p>
                </a>
                <a href="/legal/privacypolicy">
                    <p class="p-1 block-title"> &raquo; Privacy Policy</p>
                </a>
                <a href="/legal/productandservices">
                    <p class="p-1 block-title"> &raquo; Product and Services</p>
                </a>
                <a href="/legal/termandcondition">
                    <p class="p-1 block-title"> &raquo; Terms and Conditions</p>
                </a>
                <a href="/legal/refundandcancellation">
                    <p class="p-1 block-title"> &raquo; Refund/Cancellation Policy</p>
                </a>
                <a href="/legal/feestructure">
                    <p class="p-1 block-title"> &raquo; Fee Structure </p>
                </a></c:otherwise></c:choose>
                <p class="p-1 "><kec:setting key="footer.since">Since January, 2019</kec:setting></p>

            </section>


            <section id="" class="p-12 text-center  block-block clearfix">

                <h2><kec:setting key="footer.copyright">&copy 2018 KEC Katihar</kec:setting></h2>

                <p><kec:setting key="footer.credit">Designed & Developed by</kec:setting></p> <br>
                <p><a href="/category/kecwebteam" target="_blank">


                    <button type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">KEC Web Team</button>


                    </a></p>
            </section>
        </div>
    </footer>

</div>






<!--<script type="text/javascript" src="/js/protectcode.js"></script>--><script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script src="js/main.js"></script>
<script src="js/searchFaculty.js"></script>

</body>

    </html>