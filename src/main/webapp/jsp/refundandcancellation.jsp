<%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>

<div class="pt-4 pb-6">
    <div class="flex items-center justify-between bg-gray-200 py-4 px-6">
        <div class="flex space-x-4">
            <a href="/" class="text-blue-600 flex items-center">
                <i class="fa fa-home mr-2"></i> Home
            </a>
        </div>
        <div class="flex-grow text-center">
            <h3 class="text-2xl font-semibold">Refund and Cancellation</h3>
        </div>
    </div>

    <div class="container mx-auto mt-8 px-6">
        <div class="bg-white p-6 rounded-lg shadow-md">
            <kec:section key="legal.refundandcancellation/main" title="Refund and Cancellation: text (phone and e-mail are contact settings)"><p class="text-center">For refund or cancellation policy, please contact the college</p></kec:section>
            <ul class="mt-3 text-center">
                <li>Phone: <kec:setting key="contact.phone">06452-239122</kec:setting></li>
                <li>E-mail: <kec:setting key="contact.email">principal@keck.ac.in</kec:setting></li>
            </ul>
        </div>
    </div>
</div>

<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>

