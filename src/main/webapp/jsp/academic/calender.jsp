<jsp:include page="/jsp/header.jsp"/>

<div class="bg-gradient-to-r from-blue-500 to-blue-700 py-10">
    <div class="container mx-auto px-4">
        <div class="text-center mb-6">
            <h3 class="text-4xl font-bold text-white">Academic Calendar</h3>
            <p class="text-lg text-blue-100 mt-2">Stay updated with the latest academic schedules</p>
        </div>
    </div>
</div>

<div class="container mx-auto px-4 py-8">
    <nav class="mb-6">
        <ul class="flex space-x-2 text-gray-700">
            <li>
                <a href="/" class="flex items-center text-blue-600 hover:underline">
                    <i class="fa fa-home mr-1"></i> Home
                </a>
            </li>
            <li class="text-gray-500">/</li>
            <li class="text-gray-600">Academic Calendar</li>
        </ul>
    </nav>

    <div class="flex flex-col lg:flex-row -mx-4">
        <!-- Sidebar -->
        <aside class="col-sm-3" role="complementary">
            <div class="region region-sidebar-second">
                <section id="block-menu-block-3" class="block block-menu-block clearfix">
                    <h2 class="block-title">Academics</h2>
                    <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                        <ul class="menu nav">
                            <li class="first leaf menu-mlid-1154"><a href="/academic/admission" class="sf-depth-2 active">UG Admission</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/academic/regulations" class="sf-depth-2">Academic Regulations</a></li>
                            <li class="leaf menu-mlid-1156"><a href="/academic/calender" class="sf-depth-2">Academic Calendar</a></li>
                            <li class="leaf menu-mlid-1294"><a href="/academic/holidays" class="sf-depth-2">List of Holidays</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/attendance" class="sf-depth-2">Attendance</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/notice" class="sf-depth-2">Notice From Govt.</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/rules" class="sf-depth-2">Disciplinary Rules</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/academiccouncil" class="sf-depth-2">MoM of Academic Council</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/antiragging" class="sf-depth-2">Anti Ragging</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/universityresults" class="sf-depth-2">University Result</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/mentors" class="sf-depth-2">Mentors List</a></li>
                        </ul>
                    </div>
                </section>
            </div>
        </aside>

        <!-- Main Content -->
        <div class="w-full sm:w-3/4 px-4">
            <div class="bg-white shadow-lg rounded-lg p-6">
                <div class="leading-relaxed text-lg font-light text-gray-700">
                    <ul class="list-disc pl-5 space-y-4">

                        <li class="flex items-center hover:bg-blue-50 p-2 rounded transition duration-300 ease-in-out">
                            <div class="flex-shrink-0">
                                <i class="fa fa-calendar-alt text-blue-600 mr-2"></i>
                            </div>
                            <p>
                                <span class="font-semibold">Bihar Engineering University Calendar:</span>
                                <a href="http://beu-bih.ac.in/BEUP/Academic_Calendar.aspx" target="_blank" class="text-blue-600 hover:underline">Download</a>
                            </p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp"/>
