<%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>
<div class="py-6">
    <div class="flex items-center justify-between bg-gray-100 py-4 px-6">
        <div class="flex space-x-4">
            <a href="/" class="text-blue-600 flex items-center">
                <i class="fa fa-home mr-2"></i> Home
            </a>
        </div>
        <div class="flex-grow text-center">
            <h3 class="text-2xl font-semibold">Term and Condition</h3>
        </div>
    </div>
<div class="container">



    <div class="row"  style="margin:30px">
    <kec:section key="legal.termandcondition/main" title="Terms and Conditions: text"><p></p>
        All legal affairs to be dealt under Katihar Jurisdiction.</kec:section>

    </div>
</div>
<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>
