<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<jsp:include page="/jsp/header.jsp"/><kec:manageNav/>
<link rel="stylesheet" href="/js/vendor/quill-2.0.3/quill.snow.css">
<div class="py-6">
    <div class="flex items-center justify-between bg-gray-100 py-4 px-6">
        <div class="flex space-x-4">
            <a href="/manage" class="text-blue-600 flex items-center"><i class="fa fa-arrow-left mr-2"></i> Website content</a>
        </div>
        <div class="flex-grow text-center">
            <h3 class="text-2xl font-semibold">Page texts</h3>
        </div>
    </div>

    <div class="container mx-auto mt-8 px-4">
        <c:if test="${saved}"><p class="mb-4 p-3 rounded bg-green-100 text-green-800" id="saved">Saved.</p></c:if>
        <div class="flex flex-col md:flex-row gap-6">
            <div class="md:w-1/4">
                <h4 class="font-semibold mb-2">Texts you can change</h4>
                <input type="search" id="section-filter" placeholder="Find a page or text" class="w-full mb-3 px-2 py-1 border rounded text-sm">
                <ul class="space-y-1" id="section-list">
                    <c:set var="lastPage" value=""/>
                    <c:forEach var="s" items="${sections}">
                        <c:if test="${empty area or area eq s.areaCode}">
                            <c:if test="${s.pageKey ne lastPage}">
                                <li class="pt-2 text-xs uppercase text-gray-500" data-page="${s.pageKey}">${s.pageUrl}</li>
                                <c:set var="lastPage" value="${s.pageKey}"/>
                            </c:if>
                            <li data-page="${s.pageKey}"><a class="${section.sectionId == s.sectionId ? 'font-bold' : 'text-blue-600'}" href="/manage/sections?id=${s.sectionId}"><c:out value="${s.title}"/></a>
                                <c:if test="${not s.visible}"><span class="text-sm text-gray-500">(hidden)</span></c:if></li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>

            <div class="md:w-3/4">
                <c:choose>
                    <c:when test="${empty section}">
                        <p class="text-gray-700">Choose a text on the left.</p>
                    </c:when>
                    <c:otherwise>
                        <h4 class="text-xl font-semibold mb-1"><c:out value="${section.title}"/></h4>
                        <p class="mb-3 text-sm text-gray-600">Shown on <a class="text-blue-600" href="${section.pageUrl}" target="_blank">${section.pageUrl}</a>
                            &middot; <a class="text-blue-600" href="/manage/blocks?page=${section.pageKey}" id="page-blocks-link">add headings, text, images or documents to this page</a>
                            <c:if test="${not empty section.updatedBy}">&middot; last changed by <c:out value="${section.updatedBy}"/></c:if></p>

                        <form method="post" action="/manage/sections/save" id="section-form">
                            <input type="hidden" name="id" value="${section.sectionId}">
                            <div class="mb-2 flex gap-2">
                                <button type="button" id="mode-html" class="px-3 py-1 border rounded bg-gray-200">HTML</button>
                                <button type="button" id="mode-visual" class="px-3 py-1 border rounded">Visual editor</button>
                            </div>
                            <p id="visual-note" class="hidden mb-2 text-sm text-yellow-800 bg-yellow-50 p-2 rounded">
                                The visual editor keeps text, headings, lists, links and bold/italic, but removes the page's
                                layout classes and styles. To keep the exact layout, edit in HTML mode.</p>
                            <textarea name="html" id="html" rows="18" class="w-full p-2 border rounded font-mono text-sm"><c:out value="${html}"/></textarea>
                            <div id="visual" class="hidden bg-white" style="min-height: 300px"></div>
                            <label class="inline-flex items-center gap-2 mt-2"><input type="checkbox" name="visible" <c:if test="${section.visible}">checked</c:if>> show this text on the page</label>
                            <p class="text-sm text-gray-500 mt-1">Scripts, forms and unsafe links are removed when saving. Embedded YouTube videos and Google Maps are allowed.</p>
                            <div class="mt-3 flex gap-2">
                                <button type="submit" class="px-4 py-2 bg-blue-500 text-white font-semibold rounded-md">Save</button>
                                <button type="submit" formaction="/manage/sections/preview" formtarget="_blank" class="px-4 py-2 border rounded-md">Preview</button>
                                <a href="/manage/sections?id=${section.sectionId}" class="px-4 py-2 border rounded-md">Discard changes</a>
                            </div>
                        </form>

                        <h4 class="text-lg font-semibold mt-8 mb-2">History</h4>
                        <c:choose>
                            <c:when test="${empty history}"><p class="text-gray-600" id="no-history">Not changed yet: the page shows the original text.</p></c:when>
                            <c:otherwise>
                                <table class="min-w-full border bg-white text-sm" id="history">
                                    <thead class="bg-gray-100"><tr><th class="p-2 border text-left">When</th><th class="p-2 border text-left">Who</th><th class="p-2 border text-left">What</th><th class="p-2 border"></th></tr></thead>
                                    <tbody>
                                    <c:forEach var="h" items="${history}" varStatus="st">
                                        <tr>
                                            <td class="p-2 border"><fmt:formatDate value="${h.changedAt}" pattern="dd.MM.yyyy HH:mm"/></td>
                                            <td class="p-2 border"><c:out value="${h.changedBy}"/></td>
                                            <td class="p-2 border"><c:out value="${h.action}"/></td>
                                            <td class="p-2 border whitespace-nowrap">
                                                <c:if test="${not st.first}">
                                                    <form method="post" action="/manage/sections/restore" class="inline" onsubmit="return confirm('Restore this version?');">
                                                        <input type="hidden" name="id" value="${section.sectionId}"><input type="hidden" name="logId" value="${h.logId}">
                                                        <button type="submit" class="text-blue-600">Restore this version</button>
                                                    </form>
                                                </c:if>
                                                <c:if test="${st.last}">
                                                    <form method="post" action="/manage/sections/restore" class="inline ml-2" onsubmit="return confirm('Restore the original text?');">
                                                        <input type="hidden" name="id" value="${section.sectionId}"><input type="hidden" name="logId" value="${h.logId}"><input type="hidden" name="before" value="1">
                                                        <button type="submit" class="text-blue-600">Restore the original text</button>
                                                    </form>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<script src="/js/vendor/quill-2.0.3/quill.js"></script>
<script>
    (function () {
        var filter = document.getElementById('section-filter');
        filter.addEventListener('input', function () {
            var q = filter.value.toLowerCase();
            document.querySelectorAll('#section-list li').forEach(function (li) {
                var page = li.getAttribute('data-page') || '';
                var group = document.querySelectorAll('#section-list li[data-page="' + page + '"]');
                var text = Array.prototype.map.call(group, function (x) { return x.textContent; }).join(' ').toLowerCase();
                li.style.display = !q || text.indexOf(q) >= 0 ? '' : 'none';
            });
        });
    })();
    (function () {
        var form = document.getElementById('section-form');
        if (!form) return;
        var textarea = document.getElementById('html'), visual = document.getElementById('visual'), quill = null;
        function showHtml() {
            if (quill) { textarea.value = quill.root.innerHTML; }
            visual.classList.add('hidden');
            var toolbar = document.querySelector('.ql-toolbar'); if (toolbar) toolbar.classList.add('hidden');
            textarea.classList.remove('hidden'); document.getElementById('visual-note').classList.add('hidden');
            document.getElementById('mode-html').classList.add('bg-gray-200'); document.getElementById('mode-visual').classList.remove('bg-gray-200');
        }
        function showVisual() {
            if (!quill) {
                quill = new Quill(visual, {theme: 'snow', modules: {toolbar: [[{header: [2, 3, 4, false]}], ['bold', 'italic', 'underline'],
                        [{list: 'ordered'}, {list: 'bullet'}], ['link'], ['clean']]}});
            }
            quill.clipboard.dangerouslyPasteHTML(textarea.value); // only shown in the editor; the server sanitises on save
            textarea.classList.add('hidden'); visual.classList.remove('hidden');
            var toolbar = document.querySelector('.ql-toolbar'); if (toolbar) toolbar.classList.remove('hidden');
            document.getElementById('visual-note').classList.remove('hidden');
            document.getElementById('mode-visual').classList.add('bg-gray-200'); document.getElementById('mode-html').classList.remove('bg-gray-200');
        }
        document.getElementById('mode-html').addEventListener('click', showHtml);
        document.getElementById('mode-visual').addEventListener('click', showVisual);
        form.addEventListener('submit', function () {
            if (quill && textarea.classList.contains('hidden')) { textarea.value = quill.root.innerHTML; }
        });
    })();
</script>
<jsp:include page="/jsp/footer.jsp"/>
