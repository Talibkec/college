<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %>
<jsp:include page="/jsp/header.jsp"/><kec:manageNav/>
<link rel="stylesheet" href="/js/vendor/quill-2.0.3/quill.snow.css">
<div class="py-6">
    <div class="flex items-center justify-between bg-gray-100 py-4 px-6">
        <div class="flex space-x-4">
            <a href="/manage" class="text-blue-600 flex items-center"><i class="fa fa-arrow-left mr-2"></i> Website content</a>
        </div>
        <div class="flex-grow text-center">
            <h3 class="text-2xl font-semibold">Page content: headings, text, images, documents</h3>
        </div>
    </div>

    <div class="container mx-auto mt-8 px-4">
        <c:if test="${saved}"><p class="mb-4 p-3 rounded bg-green-100 text-green-800" id="saved">Saved.</p></c:if>
        <c:if test="${not empty error}"><p class="mb-4 p-3 rounded bg-red-100 text-red-800" id="error"><c:out value="${error}"/></p></c:if>

        <c:choose>
        <c:when test="${empty page}">
            <%-- choose a page --%>
            <p class="mb-4 text-gray-700">Open the page on the website and click <strong>"Edit this page"</strong> below its content,
                or enter the address of the page here:</p>
            <form method="get" action="/manage/blocks" class="mb-6 flex flex-wrap gap-2">
                <input type="text" name="url" value="<c:out value='${url}'/>" placeholder="/about/history" class="px-3 py-2 border rounded w-80">
                <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-md">Open</button>
            </form>
            <h4 class="font-semibold mb-2">Pages with added content you can change</h4>
            <c:choose>
                <c:when test="${empty pages}"><p class="text-gray-600" id="no-pages">None yet.</p></c:when>
                <c:otherwise>
                    <ul class="list-disc pl-6" id="pages">
                        <c:forEach var="p" items="${pages}"><li><a class="text-blue-600" href="/manage/blocks?page=${p}">${p}</a></li></c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <p class="mb-4 text-gray-700">Page <a class="text-blue-600" href="${pageUrl}" target="_blank">${pageUrl}</a>.</p>

            <c:if test="${not empty sections}">
                <div class="mb-6 p-4 rounded bg-blue-50" id="page-sections">
                    <h4 class="font-semibold mb-2">Existing text of this page</h4>
                    <ul class="list-disc pl-6">
                        <c:forEach var="s" items="${sections}">
                            <li><a class="text-blue-600" href="/manage/sections?id=${s.sectionId}"><i class="fa fa-edit"></i> <c:out value="${s.title}"/></a>
                                <c:if test="${not s.visible}"><span class="text-sm text-gray-500">(hidden)</span></c:if></li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <h4 class="font-semibold mb-2">Added content</h4>
            <p class="mb-4 text-gray-700">
                The content below is shown on the page under its existing text, in this order.
                <c:if test="${access eq 'A'}"><br><span class="text-sm">You can add content and change or delete what you added.</span></c:if></p>

            <table class="min-w-full border bg-white mb-6" id="blocks">
                <thead class="bg-gray-100"><tr><th class="p-2 border text-left">Content</th><th class="p-2 border">Shown</th><th class="p-2 border">Order</th><th class="p-2 border"></th></tr></thead>
                <tbody>
                <c:if test="${empty blocks}"><tr><td colspan="4" class="p-3 text-gray-600" id="no-blocks">Nothing added yet. Use the buttons below.</td></tr></c:if>
                <c:forEach var="b" items="${blocks}">
                    <tr id="block-${b.blockId}" class="${b.visible ? '' : 'text-gray-400'}">
                        <td class="p-2 border">
                            <span class="text-xs uppercase text-gray-500">${b.blockType}</span><br>
                            <c:choose>
                                <c:when test="${b.blockType eq 'heading'}"><strong class="text-lg"><c:out value="${b.heading}"/></strong></c:when>
                                <c:when test="${b.blockType eq 'subheading'}"><strong><c:out value="${b.heading}"/></strong></c:when>
                                <c:when test="${b.blockType eq 'text'}"><div class="text-sm max-h-24 overflow-hidden">${b.bodyHtml}</div></c:when>
                                <c:when test="${b.blockType eq 'image'}"><img src="${b.fileUrl}" alt="" class="h-16 inline-block"> <c:out value="${b.heading}"/> <span class="text-xs">(${b.display})</span></c:when>
                                <c:when test="${b.blockType eq 'document'}"><i class="fa fa-file"></i> <c:out value="${empty b.heading ? b.fileName : b.heading}"/> <span class="text-xs">(<c:out value="${b.fileName}"/>, ${b.fileSizeText}, ${b.pdf and b.display ne 'link' ? 'shown in the page' : 'download link'})</span></c:when>
                            </c:choose>
                        </td>
                        <td class="p-2 border text-center">${b.visible ? 'yes' : 'hidden'}</td>
                        <td class="p-2 border text-center whitespace-nowrap">
                            <c:if test="${changeable.contains(b.blockId)}">
                                <form method="post" action="/manage/blocks/move" class="inline"><input type="hidden" name="page" value="${page}"><input type="hidden" name="blockId" value="${b.blockId}"><input type="hidden" name="direction" value="-1"><button type="submit" title="Up">&#9650;</button></form>
                                <form method="post" action="/manage/blocks/move" class="inline"><input type="hidden" name="page" value="${page}"><input type="hidden" name="blockId" value="${b.blockId}"><input type="hidden" name="direction" value="1"><button type="submit" title="Down">&#9660;</button></form>
                            </c:if>
                        </td>
                        <td class="p-2 border whitespace-nowrap">
                            <c:if test="${changeable.contains(b.blockId)}">
                                <a class="text-blue-600" href="/manage/blocks?page=${page}&edit=${b.blockId}#form">Edit</a>
                                <form method="post" action="/manage/blocks/delete" class="inline" onsubmit="return confirm('Delete this content?');">
                                    <input type="hidden" name="page" value="${page}"><input type="hidden" name="blockId" value="${b.blockId}">
                                    <button type="submit" class="text-red-600 ml-2">Delete</button>
                                </form>
                            </c:if>
                            <c:if test="${not changeable.contains(b.blockId)}"><span class="text-xs text-gray-500">added by <c:out value="${b.createdBy}"/></span></c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="mb-4 flex flex-wrap gap-2" id="add-buttons">
                <span class="font-semibold self-center">Add:</span>
                <a class="px-3 py-2 border rounded bg-white" href="/manage/blocks?page=${page}&add=heading#form">Heading</a>
                <a class="px-3 py-2 border rounded bg-white" href="/manage/blocks?page=${page}&add=subheading#form">Subheading</a>
                <a class="px-3 py-2 border rounded bg-white" href="/manage/blocks?page=${page}&add=text#form">Text</a>
                <a class="px-3 py-2 border rounded bg-white" href="/manage/blocks?page=${page}&add=image#form">Image</a>
                <a class="px-3 py-2 border rounded bg-white" href="/manage/blocks?page=${page}&add=document#form">Document (PDF, Word, Excel, ...)</a>
            </div>

            <c:if test="${not empty formType}">
                <c:set var="isNew" value="${editBlock == null || editBlock.blockId == null}"/>
                <h4 class="text-xl font-semibold mb-2" id="form">${isNew ? 'Add' : 'Change'}:
                    ${formType eq 'heading' ? 'heading' : formType eq 'subheading' ? 'subheading' : formType eq 'text' ? 'text' : formType eq 'image' ? 'image' : 'document'}</h4>
                <form method="post" action="/manage/blocks/save" enctype="multipart/form-data" id="block-form" class="bg-white p-4 border rounded space-y-3 max-w-3xl">
                    <input type="hidden" name="page" value="${page}">
                    <input type="hidden" name="type" value="<c:out value='${formType}'/>">
                    <c:if test="${not isNew}"><input type="hidden" name="blockId" value="${editBlock.blockId}"></c:if>

                    <c:choose>
                        <c:when test="${formType eq 'heading' or formType eq 'subheading'}">
                            <label class="block">${formType eq 'heading' ? 'Heading' : 'Subheading'}
                                <input type="text" name="heading" maxlength="255" required class="w-full px-2 py-1 border rounded" value="<c:out value='${editBlock.heading}'/>"></label>
                        </c:when>
                        <c:when test="${formType eq 'text'}">
                            <div class="flex gap-2">
                                <button type="button" id="mode-visual" class="px-3 py-1 border rounded bg-gray-200">Editor</button>
                                <button type="button" id="mode-html" class="px-3 py-1 border rounded">HTML</button>
                            </div>
                            <div id="visual" class="bg-white" style="min-height: 220px"></div>
                            <textarea name="html" id="html" rows="12" class="hidden w-full p-2 border rounded font-mono text-sm"><c:out value="${editBlock.bodyHtml}"/></textarea>
                            <p class="text-sm text-gray-500">Bold, italic, lists, links and sub-headings. Scripts and unsafe links are removed when saving.</p>
                        </c:when>
                        <c:when test="${formType eq 'image'}">
                            <label class="block">Image file (JPG, PNG, GIF or WebP, at most 20 MB)<c:if test="${not isNew}"> &ndash; leave empty to keep the current image</c:if>
                                <input type="file" name="file" accept="image/jpeg,image/png,image/gif,image/webp" class="block mt-1" <c:if test="${isNew}">required</c:if>></label>
                            <c:if test="${not isNew and editBlock.fileId != null}"><img src="/content-file/${editBlock.fileId}" alt="" class="h-24"></c:if>
                            <label class="block">Caption (optional)
                                <input type="text" name="heading" maxlength="255" class="w-full px-2 py-1 border rounded" value="<c:out value='${editBlock.heading}'/>"></label>
                            <label class="block">Size
                                <select name="display" class="px-2 py-1 border rounded">
                                    <option value="small" <c:if test="${editBlock.display eq 'small'}">selected</c:if>>small</option>
                                    <option value="medium" <c:if test="${empty editBlock.display or editBlock.display eq 'medium'}">selected</c:if>>medium</option>
                                    <option value="full" <c:if test="${editBlock.display eq 'full'}">selected</c:if>>full width</option>
                                </select></label>
                        </c:when>
                        <c:otherwise>
                            <label class="block">Document (PDF, .doc, .docx, .xls, .xlsx, .ppt, .pptx; at most 20 MB)<c:if test="${not isNew}"> &ndash; leave empty to keep the current file</c:if>
                                <input type="file" name="file" accept=".pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx" class="block mt-1" <c:if test="${isNew}">required</c:if>></label>
                            <label class="block">Title (optional)
                                <input type="text" name="heading" maxlength="255" class="w-full px-2 py-1 border rounded" value="<c:out value='${editBlock.heading}'/>"></label>
                            <label class="block">Show
                                <select name="display" class="px-2 py-1 border rounded">
                                    <option value="embed" <c:if test="${editBlock.display ne 'link'}">selected</c:if>>PDF inside the page (with download link)</option>
                                    <option value="link" <c:if test="${editBlock.display eq 'link'}">selected</c:if>>only a download link</option>
                                </select></label>
                            <p class="text-sm text-gray-500">Word, Excel and PowerPoint files are always shown as a download link.</p>
                        </c:otherwise>
                    </c:choose>

                    <label class="inline-flex items-center gap-2"><input type="checkbox" name="visible" <c:if test="${editBlock == null || editBlock.visible}">checked</c:if>> show on the page</label>
                    <div>
                        <button type="submit" class="px-4 py-2 bg-blue-500 text-white font-semibold rounded-md">Save</button>
                        <a href="/manage/blocks?page=${page}" class="ml-2 px-4 py-2 border rounded-md">Cancel</a>
                    </div>
                </form>
            </c:if>
        </c:otherwise>
        </c:choose>
    </div>
</div>
<script src="/js/vendor/quill-2.0.3/quill.js"></script>
<script>
    (function () {
        var visual = document.getElementById('visual');
        if (!visual) return;
        var textarea = document.getElementById('html');
        var quill = new Quill(visual, {theme: 'snow', modules: {toolbar: [[{header: [3, 4, false]}], ['bold', 'italic', 'underline'],
                [{list: 'ordered'}, {list: 'bullet'}], ['link'], ['clean']]}});
        quill.clipboard.dangerouslyPasteHTML(textarea.value); // only shown in the editor; the server sanitises on save
        var htmlMode = false;
        function toolbar() { return document.querySelector('.ql-toolbar'); }
        document.getElementById('mode-html').addEventListener('click', function () {
            if (htmlMode) return;
            textarea.value = quill.root.innerHTML; htmlMode = true;
            visual.classList.add('hidden'); if (toolbar()) toolbar().classList.add('hidden'); textarea.classList.remove('hidden');
            this.classList.add('bg-gray-200'); document.getElementById('mode-visual').classList.remove('bg-gray-200');
        });
        document.getElementById('mode-visual').addEventListener('click', function () {
            if (!htmlMode) return;
            quill.clipboard.dangerouslyPasteHTML(textarea.value); htmlMode = false;
            textarea.classList.add('hidden'); visual.classList.remove('hidden'); if (toolbar()) toolbar().classList.remove('hidden');
            this.classList.add('bg-gray-200'); document.getElementById('mode-html').classList.remove('bg-gray-200');
        });
        document.getElementById('block-form').addEventListener('submit', function () {
            if (!htmlMode) textarea.value = quill.root.innerHTML;
        });
    })();
</script>
<jsp:include page="/jsp/footer.jsp"/>
