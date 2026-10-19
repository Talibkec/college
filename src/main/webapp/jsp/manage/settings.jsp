<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %>
<jsp:include page="/jsp/header.jsp"/><kec:manageNav/>
<div class="py-6">
    <div class="flex items-center justify-between bg-gray-100 py-4 px-6">
        <div class="flex space-x-4">
            <a href="/manage" class="text-blue-600 flex items-center"><i class="fa fa-arrow-left mr-2"></i> Website content</a>
        </div>
        <div class="flex-grow text-center">
            <h3 class="text-2xl font-semibold">Website texts</h3>
        </div>
    </div>

    <div class="container mx-auto mt-8 px-4">
        <p class="mb-4 text-gray-700">
            These texts appear on many pages (header, footer, contact pages). Plain text only; HTML tags are shown as
            text. Character references such as <code>&amp;copy</code> are allowed.
            "Use the page's original text" shows the text that is built into the page.
        </p>
        <c:if test="${saved}"><p class="mb-4 p-3 rounded bg-green-100 text-green-800" id="saved">Saved.</p></c:if>
        <c:if test="${not empty errors}"><p class="mb-4 p-3 rounded bg-red-100 text-red-800" id="errors">Nothing was saved. Please correct the marked fields.</p></c:if>

        <form method="post" action="/manage/settings">
            <c:if test="${not empty area}"><input type="hidden" name="area" value="<c:out value='${area}'/>"></c:if>
            <table class="min-w-full border bg-white">
                <thead class="bg-gray-100">
                <tr><th class="text-left p-2 border">Text</th><th class="text-left p-2 border">Value</th><th class="p-2 border">Use the page's original text</th></tr>
                </thead>
                <tbody>
                <c:forEach var="s" items="${settings}">
                    <c:if test="${empty area or area eq s.areaCode}">
                        <tr id="setting-<c:out value='${s.settingKey}'/>">
                            <td class="p-2 border align-top">
                                <strong><c:out value="${s.description}"/></strong><br>
                                <span class="text-sm text-gray-500"><c:out value="${s.settingKey}"/> (<c:out value="${s.valueType}"/>)</span>
                                <c:if test="${not empty s.updatedBy}"><br><span class="text-sm text-gray-500">changed by <c:out value="${s.updatedBy}"/></span></c:if>
                            </td>
                            <td class="p-2 border align-top">
                                <c:set var="v" value="${entered.containsKey(s.settingKey) ? entered[s.settingKey] : s.settingValue}"/>
                                <input type="text" class="w-full px-2 py-1 border rounded" maxlength="1000"
                                       name="value:<c:out value='${s.settingKey}'/>" value="<c:out value='${v}'/>">
                                <c:if test="${not empty errors[s.settingKey]}"><span class="text-red-700 text-sm"><c:out value="${errors[s.settingKey]}"/></span></c:if>
                            </td>
                            <td class="p-2 border text-center align-top">
                                <input type="checkbox" name="original:<c:out value='${s.settingKey}'/>" <c:if test="${s.settingValue == null}">checked</c:if>>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="mt-4 px-4 py-2 bg-blue-500 text-white font-semibold rounded-md">Save</button>
        </form>
        <script>
            // typing a value means "use this value", not the page's original text
            document.querySelectorAll('input[name^="value:"]').forEach(function (input) {
                input.addEventListener('input', function () {
                    var box = document.querySelector('input[name="original:' + input.name.substring(6) + '"]');
                    if (box) box.checked = false;
                });
            });
        </script>
    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>
