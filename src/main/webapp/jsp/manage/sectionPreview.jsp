<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/jsp/header.jsp"/>
<div class="py-6">
    <div class="bg-yellow-100 text-yellow-900 p-3 text-center" id="preview-banner">
        Preview of &quot;<c:out value="${section.title}"/>&quot; &ndash; not saved. Close this tab to go back.
    </div>
    <div class="container mx-auto p-4">
        <div class="bg-white p-6 rounded-lg shadow-lg font-sans text-base text-justify text-gray-700" id="preview">
            ${previewHtml}
        </div>
    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>
