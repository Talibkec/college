<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %>
<jsp:include page="/jsp/header.jsp"/><kec:manageNav/>
<div class="py-6">
    <div class="flex items-center justify-between bg-gray-100 py-4 px-6">
        <div class="flex space-x-4">
            <a href="/manage" class="text-blue-600 flex items-center"><i class="fa fa-arrow-left mr-2"></i> Website content</a>
        </div>
        <div class="flex-grow text-center">
            <h3 class="text-2xl font-semibold">Menus and links</h3>
        </div>
    </div>

    <div class="container mx-auto mt-8 px-4">
        <form method="get" action="/manage/links" class="mb-4 flex flex-wrap gap-2 items-center">
            <label for="list" class="font-semibold">List:</label>
            <select id="list" name="list" class="px-3 py-2 border rounded" onchange="this.form.submit()">
                <c:forEach var="l" items="${lists}">
                    <option value="${l.key}" <c:if test="${l.key eq list}">selected</c:if>><c:out value="${l.value}"/></option>
                </c:forEach>
            </select>
            <noscript><button type="submit" class="px-3 py-2 border rounded">Show</button></noscript>
        </form>

        <c:if test="${saved}"><p class="mb-4 p-3 rounded bg-green-100 text-green-800" id="saved">Saved.</p></c:if>
        <c:if test="${not empty error}"><p class="mb-4 p-3 rounded bg-red-100 text-red-800" id="error"><c:out value="${error}"/></p></c:if>

        <div class="mb-4 p-3 rounded bg-gray-100" id="list-state">
            <c:choose>
                <c:when test="${enabled}">
                    The website shows this list <strong>from the database</strong> (the entries below).
                </c:when>
                <c:otherwise>
                    This list is <strong>switched off</strong>: the website still shows the links built into the pages.
                    Check the entries below and the <a class="text-blue-600" href="/manage/links/preview" target="_blank">preview</a>, then switch it on.
                </c:otherwise>
            </c:choose>
            <form method="post" action="/manage/links/enabled" class="inline">
                <input type="hidden" name="list" value="${list}">
                <input type="hidden" name="enabled" value="${enabled ? 'no' : 'yes'}">
                <button type="submit" class="ml-2 px-3 py-1 border rounded bg-white">${enabled ? 'Switch off (show the built-in links)' : 'Switch on'}</button>
            </form>
            <c:if test="${list eq 'main_menu'}">
                <p class="text-sm text-gray-600 mt-2">The main menu feeds the desktop menu, the mobile menu and the side menu.
                    Today the mobile menu differs from the desktop menu; after switching on, all three show these entries.
                    Login, Logout and Dashboard are added automatically.</p>
            </c:if>
        </div>

        <table class="min-w-full border bg-white mb-6">
            <thead class="bg-gray-100">
            <tr><th class="text-left p-2 border">Label</th><th class="text-left p-2 border">Link</th>
                <c:if test="${list eq 'main_menu'}"><th class="text-left p-2 border">Column heading</th></c:if>
                <th class="p-2 border">New tab</th><th class="p-2 border">Visible</th><th class="p-2 border">Order</th><th class="p-2 border"></th></tr>
            </thead>
            <tbody>
            <c:forEach var="i" items="${items}">
                <tr id="link-${i.linkId}" class="${i.visible ? '' : 'text-gray-400'}">
                    <td class="p-2 border"><c:if test="${i.parentId != null}">&nbsp;&nbsp;&nbsp;&#8627; </c:if><c:out value="${i.label}"/></td>
                    <td class="p-2 border text-sm"><c:out value="${i.url}"/></td>
                    <c:if test="${list eq 'main_menu'}"><td class="p-2 border text-sm"><c:out value="${i.groupLabel}"/></td></c:if>
                    <td class="p-2 border text-center">${i.openNewTab ? 'yes' : ''}</td>
                    <td class="p-2 border text-center">${i.visible ? 'yes' : 'no'}</td>
                    <td class="p-2 border text-center whitespace-nowrap">
                        <form method="post" action="/manage/links/move" class="inline"><input type="hidden" name="list" value="${list}"><input type="hidden" name="linkId" value="${i.linkId}"><input type="hidden" name="direction" value="-1"><button type="submit" title="Up">&#9650;</button></form>
                        <form method="post" action="/manage/links/move" class="inline"><input type="hidden" name="list" value="${list}"><input type="hidden" name="linkId" value="${i.linkId}"><input type="hidden" name="direction" value="1"><button type="submit" title="Down">&#9660;</button></form>
                    </td>
                    <td class="p-2 border whitespace-nowrap">
                        <a class="text-blue-600" href="/manage/links?list=${list}&edit=${i.linkId}#form">Edit</a>
                        <form method="post" action="/manage/links/delete" class="inline" onsubmit="return confirm('Delete this entry<c:if test="${i.parentId == null}"> and its sub-entries</c:if>?');">
                            <input type="hidden" name="list" value="${list}"><input type="hidden" name="linkId" value="${i.linkId}">
                            <button type="submit" class="text-red-600 ml-2">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <h4 class="text-xl font-semibold mb-2" id="form">${editItem != null && editItem.linkId != null ? 'Change entry' : 'Add an entry'}</h4>
        <form method="post" action="/manage/links/save" class="bg-white p-4 border rounded space-y-3 max-w-2xl">
            <input type="hidden" name="list" value="${list}">
            <c:if test="${editItem != null && editItem.linkId != null}"><input type="hidden" name="linkId" value="${editItem.linkId}"></c:if>
            <label class="block">Label <input type="text" name="label" maxlength="255" required class="w-full px-2 py-1 border rounded" value="<c:out value='${editItem.label}'/>"></label>
            <label class="block">Link (a page of this site starting with /, or http(s)://...)
                <input type="text" name="url" maxlength="1000" required class="w-full px-2 py-1 border rounded" value="<c:out value='${editItem.url}'/>"></label>
            <c:if test="${list eq 'main_menu'}">
                <label class="block">Show under (empty = top level)
                    <select name="parentId" class="w-full px-2 py-1 border rounded">
                        <option value="">&ndash; top level &ndash;</option>
                        <c:forEach var="p" items="${parents}">
                            <c:if test="${editItem == null || p.linkId != editItem.linkId}">
                                <option value="${p.linkId}" <c:if test="${editItem.parentId == p.linkId}">selected</c:if>><c:out value="${p.label}"/></option>
                            </c:if>
                        </c:forEach>
                    </select></label>
                <label class="block">Column heading in the submenu (e.g. "Under Graduates"; optional)
                    <input type="text" name="groupLabel" maxlength="100" class="w-full px-2 py-1 border rounded" value="<c:out value='${editItem.groupLabel}'/>"></label>
            </c:if>
            <c:if test="${list eq 'social'}">
                <label class="block">Icon (Font Awesome classes, e.g. "fab fa-facebook")
                    <input type="text" name="iconClass" maxlength="100" class="w-full px-2 py-1 border rounded" value="<c:out value='${editItem.iconClass}'/>"></label>
                <label class="block">Link classes (e.g. "text-gray-800 hover:text-blue-600")
                    <input type="text" name="cssClass" maxlength="100" class="w-full px-2 py-1 border rounded" value="<c:out value='${editItem.cssClass}'/>"></label>
            </c:if>
            <label class="inline-flex items-center gap-2"><input type="checkbox" name="openNewTab" <c:if test="${editItem.openNewTab}">checked</c:if>> open in a new tab</label>
            <label class="inline-flex items-center gap-2 ml-4"><input type="checkbox" name="visible" <c:if test="${editItem == null || editItem.visible}">checked</c:if>> visible</label>
            <div>
                <button type="submit" class="px-4 py-2 bg-blue-500 text-white font-semibold rounded-md">Save</button>
                <a href="/manage/links?list=${list}" class="ml-2 px-4 py-2 border rounded-md">Cancel</a>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>
