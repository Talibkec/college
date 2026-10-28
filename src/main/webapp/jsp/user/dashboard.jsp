<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/jsp/header.jsp"/>
<%-- Faculty dashboard (/user/dashboard, Faculty only; UserDashboard). Before 2026-10-28 this page used the Admin
     dashboard frame, whose cards and sidebar all led to Admin-only screens (access denied for Faculty). --%>
<div class="panel-heading bg-gray-200 p-5 text-3xl flex items-center space-x-2">
  <i class="fa fa-th-large"></i><span>My Dashboard</span>
</div>

<div class="container mx-auto my-6 px-4">
  <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4" id="faculty-actions">
    <c:if test="${facultyId != null}">
      <a href="/facultyDetails?facultyId=${facultyId}" class="block p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-50">
        <h5 class="text-lg font-bold"><i class="fa fa-user"></i> My profile</h5>
        <p class="text-sm text-gray-600">Your public profile page; add headings, text, images and links with "Add to this page".</p></a>
      <a href="/uploadfile/editfacultydetails?facultyId=${facultyId}" class="block p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-50">
        <h5 class="text-lg font-bold"><i class="fa fa-edit"></i> Edit profile details</h5>
        <p class="text-sm text-gray-600">Name, designation, e-mail, mobile and the headings (qualifications, research, ...).</p></a>
    </c:if>
    <a href="/uploadfile/facultyFileUpload?isProfilePic=No" class="block p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-50">
      <h5 class="text-lg font-bold"><i class="fa fa-upload"></i> Upload documents</h5>
      <p class="text-sm text-gray-600">Files (max. 5 MB) or links for the "Downloads" tab of your profile.</p></a>
    <a href="/uploadfile/facultyFileUpload?isProfilePic=Yes" class="block p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-50">
      <h5 class="text-lg font-bold"><i class="fa fa-camera"></i> Update profile photo</h5>
      <p class="text-sm text-gray-600">JPG or PNG, max. 5 MB.</p></a>
    <a href="/uploadNoticeFaculty" class="block p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-50">
      <h5 class="text-lg font-bold"><i class="fa fa-bullhorn"></i> Upload notice / news</h5>
      <p class="text-sm text-gray-600">PDF for the notice page of <c:out value="${empty departmentName ? 'your department' : departmentName}"/>.</p></a>
    <a href="/manage" class="block p-5 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-50">
      <h5 class="text-lg font-bold"><i class="fa fa-globe"></i> Website content</h5>
      <p class="text-sm text-gray-600">Add content to the pages of your department<c:if test="${not empty departmentName}"> (<c:out value="${departmentName}"/>)</c:if>.</p></a>
  </div>

  <section class="mt-8 bg-white border border-gray-200 rounded-lg shadow p-5">
    <h3 class="text-xl font-semibold mb-3">Latest college notices</h3>
    <ul class="space-y-2">
      <c:forEach items="${noticeList}" var="notice">
        <c:if test="${notice.noticeType == 'News' or notice.noticeType == 'Notice'}">
          <li><span class="text-sm text-gray-500">${notice.date}</span>
            <span class="text-xs px-2 py-0.5 rounded ${notice.noticeType == 'News' ? 'bg-green-100 text-green-800' : 'bg-blue-100 text-blue-800'}">${notice.noticeType}</span>
            <a href="/notice/${notice.id}" target="_blank" class="text-blue-700 hover:underline"><c:out value="${notice.headLine}"/></a></li>
        </c:if>
      </c:forEach>
    </ul>
  </section>
</div>
<jsp:include page="/jsp/footer.jsp"/>
