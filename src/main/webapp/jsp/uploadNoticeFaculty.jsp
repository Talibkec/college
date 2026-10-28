<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- Notice of a faculty member for the own department's notice page (FacultyProfileController,
     POST /user/api/uploadDepartmentNotice). Before, this page posted to the Admin-only notice upload and failed. --%>
<div class="panel-heading bg-gray-200 p-5 text-3xl flex items-center space-x-2">
  <i class="fa fa-bullhorn"></i><span>Upload Notice / News</span>
</div>

<div class="container mx-auto my-6 px-4 max-w-2xl">
  <p class="mb-4"><a href="/mydashboard" class="text-blue-700 hover:underline"><i class="fa fa-arrow-left"></i> Back to my profile</a></p>
  <c:choose>
    <c:when test="${empty departmentName}">
      <div class="p-3 rounded border bg-red-50 border-red-400 text-red-800">Your account has no department, so notices
        cannot be uploaded. Please ask the Admin.</div>
    </c:when>
    <c:otherwise>
      <p class="mb-4 text-gray-700">The notice appears on the notice page of
        <a href="${departmentNoticeUrl}" target="_blank" class="text-blue-700 hover:underline"><c:out value="${departmentName}"/></a>.
        College-wide notices are published by the office.</p>
      <div id="msg" class="hidden mb-4 p-3 rounded border" role="alert"></div>

      <form id="noticeForm" class="bg-white border border-gray-300 rounded-lg shadow p-6 space-y-4" novalidate>
        <label class="block"><span class="font-semibold">Headline</span>
          <input type="text" name="noticeHeader" id="noticeHeader" maxlength="255" required class="w-full mt-1 p-2 border border-gray-300 rounded"></label>
        <fieldset><legend class="font-semibold">Type</legend>
          <label class="inline-flex items-center gap-2 mr-6"><input type="radio" name="news" value="false" checked> Notice</label>
          <label class="inline-flex items-center gap-2"><input type="radio" name="news" value="true"> News</label>
        </fieldset>
        <label class="block"><span class="font-semibold">Date</span>
          <input type="date" id="noticeDate" class="mt-1 p-2 border border-gray-300 rounded"></label>
        <label class="inline-flex items-center gap-2"><input type="checkbox" name="isScrollable" id="isScrollable" value="true"> Also show in the scrolling notices</label>
        <label class="block"><span class="font-semibold">PDF file</span> (max. 5 MB)
          <input type="file" name="noticeFile" id="noticeFile" accept="application/pdf,.pdf" class="block mt-1"></label>
        <button type="submit" id="btnSubmit" class="w-full text-white bg-green-700 hover:bg-green-800 font-medium rounded-lg text-sm px-5 py-2.5">
          <i class="fa fa-upload"></i> Upload notice</button>
      </form>
    </c:otherwise>
  </c:choose>
</div>
<jsp:include page="footer.jsp"/>
<script>
(function () {
    var form = document.getElementById('noticeForm');
    if (!form) return;
    var msg = document.getElementById('msg');
    var date = document.getElementById('noticeDate');
    date.value = new Date().toISOString().substring(0, 10);
    function show(text, ok) {
        msg.textContent = text;
        msg.className = 'mb-4 p-3 rounded border ' + (ok ? 'bg-green-50 border-green-400 text-green-800' : 'bg-red-50 border-red-400 text-red-800');
    }
    form.addEventListener('submit', function (e) {
        e.preventDefault();
        var file = document.getElementById('noticeFile').files[0];
        if (!form.elements.noticeHeader.value.trim()) { show('Please enter a headline.', false); form.elements.noticeHeader.focus(); return; }
        if (!file) { show('Please choose the PDF file.', false); return; }
        if (!/\.pdf$/i.test(file.name)) { show('Only PDF files can be uploaded as notices.', false); return; }
        if (file.size > 5 * 1024 * 1024) { show('The file is larger than 5 MB.', false); return; }
        var data = new FormData(form);
        var d = date.value.split('-'); // yyyy-mm-dd -> dd/MM/yyyy, the format the server reads
        data.set('date', d.length === 3 ? d[2] + '/' + d[1] + '/' + d[0] : '');
        var button = document.getElementById('btnSubmit');
        button.disabled = true;
        show('Uploading...', true);
        fetch('/user/api/uploadDepartmentNotice', {method: 'POST', body: data, credentials: 'same-origin'})
            .then(function (r) { return r.text().then(function (t) { return {ok: r.ok, status: r.status, text: t}; }); })
            .then(function (r) {
                button.disabled = false;
                if (r.ok) {
                    show('Saved. It is shown on the department notice page.', true);
                    form.reset();
                    date.value = new Date().toISOString().substring(0, 10);
                } else {
                    show(r.text && r.text.length < 300 && r.text.indexOf('<') < 0 ? r.text : 'Upload failed (' + r.status + ').', false);
                }
            })
            .catch(function () { button.disabled = false; show('Upload failed: no connection to the server.', false); });
    });
})();
</script>
