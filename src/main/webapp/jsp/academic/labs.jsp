<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp" />
<div class="container mx-auto py-8">
    <div class="jumbotron flex justify-center w-full mb-4">
        <div>
            <h3 class="text-2xl font-semibold text-gray-800">List of Labs</h3>
        </div>
    </div>
    <div class="flex justify-center">
        <aside class="col-sm-3" role="complementary" style="">
            <div class="region region-sidebar-second">
                <section id="block-menu-block-3" class="block block-menu-block clearfix">
                    <h2 class="block-title">Academics</h2>
                    <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                        <ul class="menu nav">
                            <li class="first leaf menu-mlid-1154"><a href="/academic/admission"
                                    class="sf-depth-2 active">UG Admission</a></li>
                            <li class="leaf menu-mlid-1121"><a href="/academic/regulations" class="sf-depth-2">Academic
                                    Regulations</a></li>
                            <li class="leaf menu-mlid-1156"><a href="/academic/calender" class="sf-depth-2">Academic
                                    Calender</a></li>
                            <li class="leaf menu-mlid-1294"><a href="/academic/holidays" class="sf-depth-2">List of
                                    Holidays</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/ attendance"
                                    class="sf-depth-2">Attendance</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/notice" class="sf-depth-2">Notice From
                                    Govt.</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/rules" class="sf-depth-2">Disciplinary
                                    Rules</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/academiccouncil"
                                    class="sf-depth-2">MoM of Academic Council</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/antiragging"
                                    class="sf-depth-2">Anti-Ragging</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/universityresults"
                                    class="sf-depth-2">University Result</a></li>
                            <li class="leaf menu-mlid-1467"><a href="/academic/mentors"
                                    class="sf-depth-2">Mentors List</a></li>
                        </ul>
                    </div>
                </section>
            </div>
        </aside>
        <div class="w-full max-w-2xl p-3">
            <div class="page-content">
                <table class="w-full table-auto border-collapse">
                    <thead>
                        <tr class="bg-green-500 text-white">
                            <th class="px-4 py-2">SI. No.</th>
                            <th class="px-4 py-2">
                                <c:if test="${showDeleteBtn}">
                                    Delete
                                </c:if>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="count" value='1' />
                        <c:set var="embedpdf" value='true' />
                        <c:forEach items="${allDocuments}" var="document">
                            <c:if test="${embedpdf}">
                                <tr>
                                    <td colspan="4">
                                        <div class="mb-4">
                                            <object data="https://drive.google.com/viewerng/viewer?embedded=true&url=http://keck.ac.in/approval/lab/${document.id}"
                                                type="application/pdf" width="100%" height="500">
                                                <embed src="https://drive.google.com/viewerng/viewer?embedded=true&url=http://keck.ac.in/approval/lab/${document.id}"
                                                    type="application/pdf">
                                            </object>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>
                            <c:set var="embedpdf" value="${false}" />
                            <tr class="bg-green-100">
                                <td class="border px-4 py-2 text-center">${count}</td>
                                <c:set var="count" value="${count+1}" />
                                <td class="border px-4 py-2 text-center">${document.fileName}</td>
                                <td class="border px-4 py-2 text-center">
                                    <a href="/approval/lab/${document.id}" class="inline-block">
                                        <button type="button"
                                            class="bg-green-500 text-white font-bold px-4 py-2 rounded hover:bg-green-600 transition duration-300">
                                            <i class="fa fa-download mr-2"></i>Download
                                        </button>
                                    </a>
                                </td>
                                <td class="border px-4 py-2 text-center">
                                    <c:if test="${showDeleteBtn}">
                                        <a href="/approval/deleteLab/${document.id}" class="inline-block">
                                            <button class="bg-red-500 text-white font-bold px-4 py-2 rounded hover:bg-red-600 transition duration-300">
                                                Delete Document
                                            </button>
                                        </a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp" />
