<%@ taglib prefix="c" uri="jakarta.tags.core" %><section class="container mx-auto p-4 kec-blocks" id="page-blocks">
    <style>
        .kec-blocks .kec-text ul { list-style: disc; padding-left: 2rem; }
        .kec-blocks .kec-text ol { list-style: decimal; padding-left: 2rem; }
        .kec-blocks .kec-text p { margin-bottom: .75rem; }
        .kec-blocks .kec-text a { color: #2563eb; text-decoration: underline; }
        .kec-blocks .kec-text h2 { font-size: 1.5rem; font-weight: 600; margin: 1rem 0 .5rem; }
        .kec-blocks .kec-text h3 { font-size: 1.25rem; font-weight: 600; margin: .75rem 0 .5rem; }
        .kec-blocks .kec-text table td, .kec-blocks .kec-text table th { border: 1px solid #d1d5db; padding: .25rem .5rem; }
    </style>
    <c:if test="${pageBlocksEditor}">
        <div class="text-right mb-2"><a href="/manage/blocks?page=${pageBlocksKey}" class="text-sm text-blue-600 hover:underline" id="edit-page-blocks"><i class="fa fa-edit"></i> Edit this page (add headings, text, images, documents)</a></div>
    </c:if>
    <c:forEach var="b" items="${pageBlocks}">
        <c:choose>
            <c:when test="${b.blockType eq 'heading'}"><h2 class="text-2xl font-semibold text-gray-800 mt-6 mb-3"><c:out value="${b.heading}"/></h2></c:when>
            <c:when test="${b.blockType eq 'subheading'}"><h3 class="text-xl font-semibold text-gray-700 mt-4 mb-2"><c:out value="${b.heading}"/></h3></c:when>
            <c:when test="${b.blockType eq 'text'}"><div class="font-sans text-base text-justify text-gray-700 mb-4 kec-text">${b.bodyHtml}</div></c:when>
            <c:when test="${b.blockType eq 'image' and b.fileId != null}">
                <figure class="my-4 text-center">
                    <img src="${b.fileUrl}" alt="<c:out value='${b.heading}'/>" loading="lazy"
                         class="inline-block h-auto rounded shadow ${b.display eq 'small' ? 'max-w-xs' : (b.display eq 'full' ? 'w-full' : 'max-w-xl w-full')}">
                    <c:if test="${not empty b.heading}"><figcaption class="text-sm text-gray-600 mt-1"><c:out value="${b.heading}"/></figcaption></c:if>
                </figure>
            </c:when>
            <c:when test="${b.blockType eq 'document' and b.fileId != null}">
                <div class="my-4">
                    <c:if test="${not empty b.heading}"><p class="font-semibold text-gray-800 mb-2"><c:out value="${b.heading}"/></p></c:if>
                    <c:if test="${b.pdf and b.display ne 'link'}">
                        <object data="${b.fileUrl}" type="application/pdf" class="w-full border rounded" style="height: 650px;">
                            <p><a href="${b.fileUrl}" class="text-blue-600 underline">Open the document</a></p>
                        </object>
                    </c:if>
                    <p class="mt-1"><i class="fa fa-file"></i>
                        <a href="${b.fileUrl}" class="text-blue-600 hover:underline" <c:if test="${b.pdf}">target="_blank"</c:if>>Download <c:out value="${b.fileName}"/></a>
                        <span class="text-sm text-gray-500">(${b.fileSizeText})</span></p>
                </div>
            </c:when>
        </c:choose>
    </c:forEach>
</section>
