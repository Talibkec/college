<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- Faculty profile uploads (FacultyProfileController, same URL and field names as before):
     isProfilePic=No: a document (file) or a link for the "Downloads" tab; isProfilePic=Yes: the profile photo.
     Name, e-mails and mobile are changed on "Edit Profile Details"; empty fields here keep the stored values. --%>
<c:set var="photo" value="${isProfilePic eq 'Yes'}"/>
<div class="panel-heading bg-gray-200 p-5 text-3xl flex items-center space-x-2">
  <i class="fa ${photo ? 'fa-user' : 'fa-upload'}"></i>
  <span>${photo ? 'Update Profile Photo' : 'Upload Documents'}</span>
</div>

<div class="container mx-auto my-6 px-4 max-w-2xl">
  <p class="mb-4"><a href="/mydashboard" class="text-blue-700 hover:underline"><i class="fa fa-arrow-left"></i> Back to my profile</a></p>
  <div id="msg" class="hidden mb-4 p-3 rounded border" role="alert"></div>

  <form method="POST" enctype="multipart/form-data" id="fileUploadForm" class="bg-white border border-gray-300 rounded-lg shadow p-6 space-y-4" novalidate>
    <input type="hidden" name="isProfilePic" value="<c:out value='${isProfilePic}'/>">
    <input type="hidden" name="facultyName" value="">
    <input type="hidden" name="facultyOfficialEmail" value="">
    <input type="hidden" name="facultyPersonalEmail" value="">
    <c:choose>
      <c:when test="${photo}">
        <input type="hidden" name="facultydocumentsHeader" value="NotAvailable">
        <p class="text-sm text-gray-600">Name, designation, e-mails and mobile number are changed on
          <a href="/mydashboard" class="text-blue-700 hover:underline">your profile</a> &rarr; Edit Profile Details.</p>
      </c:when>
      <c:otherwise>
        <label class="block"><span class="font-semibold">Heading</span> (shown as the link text)
          <input type="text" name="facultydocumentsHeader" id="facultydocumentsHeader" maxlength="255" required
                 class="w-full mt-1 p-2 border border-gray-300 rounded" placeholder="e.g. List of publications 2025"></label>
        <label class="inline-flex items-center gap-2"><input type="checkbox" name="isLink" id="isLink" value="true"> This is a link to another website (no file)</label>
        <label class="block hidden" id="linkBox"><span class="font-semibold">Link address</span>
          <input type="url" name="linkAddress" id="linkAddress" maxlength="1000" class="w-full mt-1 p-2 border border-gray-300 rounded" placeholder="https://..."></label>
      </c:otherwise>
    </c:choose>

    <div id="chooseFileBox">
      <label class="block"><span class="font-semibold">${photo ? 'Photo (JPG or PNG)' : 'File'}</span>
        <input type="file" name="facultydocumentsFile" id="facultydocumentsFile" class="block mt-1"
               <c:if test="${photo}">accept=".jpg,.jpeg,.png"</c:if>></label>
      <p class="text-sm text-gray-600 mt-1">Maximum size: <b id="maxSize"></b>. <span id="size"></span></p>
    </div>

    <button type="submit" id="btnSubmit" class="w-full text-white bg-green-700 hover:bg-green-800 font-medium rounded-lg text-sm px-5 py-2.5">
      <i class="fa fa-upload"></i> ${photo ? 'Upload photo' : 'Upload'}</button>
  </form>
</div>

<jsp:include page="footer.jsp"/>
<script src="/js/facultyfileUpload.js"></script>
