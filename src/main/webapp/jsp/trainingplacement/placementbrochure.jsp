<jsp:include page="/jsp/header.jsp" />

<div class="bg-gray-200 py-6">
    <div class="container mx-auto">
        <div class="text-center">
            <h3 class="text-3xl font-semibold">Placement Brochure</h3>
        </div>
    </div>
</div>

<div class="container mx-auto px-4 py-8">
    <div class="md:flex justify-center">
        <!-- Sidebar (Unchanged) -->
        <aside class="col-sm-3" role="complementary">
            <div class="region region-sidebar-second">
                <section id="block-menu-block-3" class="block block-menu-block clearfix">
                    <h2 class="block-title">Training & Placement</h2>
                    <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                        <ul class="menu nav">
                            <li class="first leaf menu-mlid-1154"><a href="/trainingplacement/aboutplacement" class="sf-depth-2 active">About Placement</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/rulesoftrainingplacement" class="sf-depth-2">Rules of Training & Placement</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/tipsforresume" class="sf-depth-2">Tips for Resume</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/placedstudent" class="sf-depth-2">Placed Students</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/trainingplacement/placementbrochure" class="sf-depth-2">Placement Brochure</a></li>
                        </ul>
                    </div>
                </section>
            </div>
        </aside>

        <!-- Main Content -->
        <div class="col-md-8 p-3">
            <div class="bg-white shadow-lg rounded-lg p-6">
                <div class="prose max-w-full">
                    <object data="https://drive.google.com/viewerng/viewer?embedded=true&url=http://keck.ac.in/jsp/trainingplacement/brochure.pdf"
                            type="application/pdf"
                            width="100%"
                            height="1000"
                            class="w-full">
                        <embed src="https://drive.google.com/viewerng/viewer?embedded=true&url=http://keck.ac.in/jsp/trainingplacement/brochure.pdf"
                               type="application/pdf">
                    </object>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp" />
