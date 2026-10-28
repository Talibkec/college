<jsp:include page="header.jsp" />
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- Profile editor of a faculty member (only the faculty member and Admin; FacultyProfileController). The headings are
     built by /js/manage/facultyprofile.js from the JSON below and saved with the same parameters as before. --%>
<style>
  #profile-editor .pe-input { width: 100%; padding: .5rem; border: 1px solid #d1d5db; border-radius: .375rem; background: #fff; }
  #profile-editor .pe-input:focus { outline: 2px solid #3b82f6; border-color: #3b82f6; }
  #profile-editor .pe-invalid { border-color: #dc2626; background: #fef2f2; }
  #profile-editor .pe-icon { padding: .35rem .6rem; border: 1px solid #d1d5db; border-radius: .375rem; background: #fff; color: #374151; }
  #profile-editor .pe-icon:hover:not(:disabled) { background: #f3f4f6; }
  #profile-editor .pe-icon:disabled { opacity: .35; cursor: default; }
  #profile-editor .pe-danger { color: #b91c1c; }
  #profile-editor .pe-chip { padding: .2rem .6rem; border: 1px dashed #2563eb; border-radius: 9999px; color: #1d4ed8; font-size: .875rem; }
  #profile-editor .pe-chip:hover { background: #eff6ff; }
</style>

<div class="panel-heading bg-gray-200 p-5 text-3xl flex items-center space-x-2">
  <i class="fa fa-edit"></i>
  <span>Edit Profile Details</span>
</div>

<div class="container mx-auto my-6 px-4 max-w-4xl" id="profile-editor">
  <p class="mb-4 text-gray-700">
    <c:out value="${facultyDetails.facultyName}"/> &middot; changes appear on the profile after <b>Save changes</b>.
    <a href="/facultyDetails?facultyId=${facultyDetails.facultyId}" class="text-blue-700 hover:underline ml-2"><i class="fa fa-arrow-left"></i> Back to the profile</a>
  </p>

  <div id="profile-message" class="hidden mb-4 p-3 rounded border" role="alert"></div>

  <form id="editDetailsForm" novalidate>
    <input type="hidden" id="facultyId" name="facultyId" value="${facultyDetails.facultyId}">

    <section class="bg-white border border-gray-300 rounded-lg shadow p-6 mb-6">
      <h2 class="text-xl font-semibold mb-4">Details</h2>
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <label class="block"><span class="font-semibold">Name</span>
          <input type="text" id="facultyName" name="facultyName" maxlength="255" class="pe-input mt-1" value="<c:out value='${facultyDetails.facultyName}'/>"></label>
        <label class="block"><span class="font-semibold">Designation</span>
          <input type="text" id="facultyDesignation" name="facultyDesignation" maxlength="100" list="designations" class="pe-input mt-1"
                 placeholder="Assistant Professor" value="<c:out value='${facultyDetails.facultyDesignation}'/>">
          <span class="text-sm text-gray-500">Choose from the list or type your own. Empty = "Assistant Professor".</span></label>
        <datalist id="designations">
          <option value="Professor"><option value="Associate Professor"><option value="Assistant Professor">
          <option value="Assistant Professor (Contract)"><option value="Guest Faculty"><option value="Lecturer">
          <option value="Teaching Assistant"><option value="Professor &amp; Head">
        </datalist>
        <label class="block"><span class="font-semibold">Official e-mail</span>
          <input type="email" id="facultyOfficialEmail" name="facultyOfficialEmail" maxlength="255" class="pe-input mt-1" value="<c:out value='${facultyDetails.facultyOfficialEmail}'/>"></label>
        <label class="block"><span class="font-semibold">Personal e-mail</span>
          <input type="email" id="facultyPersonalEmail" name="facultyPersonalEmail" maxlength="255" class="pe-input mt-1" value="<c:out value='${facultyDetails.facultyPersonalEmail}'/>"></label>
        <label class="block"><span class="font-semibold">Mobile number</span>
          <input type="text" id="facultyMobNo" name="facultyMobNo" maxlength="15" inputmode="numeric" class="pe-input mt-1" value="<c:out value='${facultyDetails.facultyMobNo}'/>">
          <span class="text-sm text-gray-500">Digits only.</span></label>
      </div>
    </section>

    <section class="bg-white border border-gray-300 rounded-lg shadow p-6 mb-6">
      <h2 class="text-xl font-semibold mb-1">Headings on the profile</h2>
      <p class="text-sm text-gray-600 mb-4">Each heading (e.g. Qualifications) is shown as a blue bar with its entries as
        bullet points. Use <i class="fa fa-arrow-up"></i> <i class="fa fa-arrow-down"></i> to change the order.
        Empty entries are left out.</p>
      <div id="headings" class="space-y-4"></div>
      <p id="no-headings" class="hidden text-gray-500 italic mb-2">No headings yet.</p>
      <div class="mt-4 flex flex-wrap items-center gap-2">
        <button type="button" id="add-heading" class="px-4 py-2 rounded bg-blue-600 text-white hover:bg-blue-700"><i class="fa fa-plus"></i> Add heading</button>
        <span class="text-sm text-gray-600 ml-2">Quick add:</span>
        <span id="quick-headings" class="flex flex-wrap gap-2"></span>
      </div>
    </section>

    <div class="sticky bottom-0 bg-white border-t border-gray-300 py-3 flex items-center gap-3">
      <button type="submit" id="btnSubmit" class="px-6 py-2 rounded bg-green-700 text-white font-medium hover:bg-green-800"><i class="fa fa-save"></i> Save changes</button>
      <a href="/facultyDetails?facultyId=${facultyDetails.facultyId}" class="px-4 py-2 rounded border border-gray-300 hover:bg-gray-100">Cancel</a>
      <span id="dirty-note" class="hidden text-sm text-amber-700">Unsaved changes</span>
    </div>
  </form>
</div>

<script type="application/json" id="headings-data">${headingsJson}</script>
<jsp:include page="footer.jsp" />
<script src="/js/manage/facultyprofile.js"></script>
