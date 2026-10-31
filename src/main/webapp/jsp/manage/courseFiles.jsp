<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %>
<jsp:include page="/jsp/header.jsp"/><kec:manageNav/>
<link rel="stylesheet" href="/css/course-files.css">
<div class="py-6 cf-page">
    <div class="flex items-center justify-between bg-gray-100 py-4 px-6">
        <div class="flex space-x-4">
            <a href="/manage" class="text-blue-600 flex items-center"><i class="fa fa-arrow-left mr-2"></i> Website content</a>
        </div>
        <div class="flex-grow text-center">
            <h3 class="text-2xl font-semibold">Course files</h3>
        </div>
    </div>

    <div class="container mx-auto mt-8 px-4">
        <c:if test="${saved}"><p class="mb-4 p-3 rounded bg-green-100 text-green-800" id="saved">Saved.</p></c:if>
        <c:if test="${not empty error}"><p class="mb-4 p-3 rounded bg-red-100 text-red-800" id="error"><c:out value="${error}"/></p></c:if>

        <c:choose>
        <c:when test="${empty dept}">
            <p class="mb-4 text-gray-700">Choose the department whose course files you want to upload or change:</p>
            <ul class="cf-list" id="course-file-sites">
                <c:forEach var="s" items="${sites}">
                    <li class="cf-card"><div class="cf-body"><h5 class="cf-subject"><c:out value="${siteNames[s]}"/></h5></div>
                        <div class="cf-actions"><a class="cf-btn cf-btn-primary" href="/manage/course-files?dept=${s}">Open</a></div></li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <div class="flex flex-wrap items-center justify-between gap-3 mb-4">
                <p class="text-gray-700 m-0">Department: <strong><c:out value="${siteNames[dept]}"/></strong>
                    <c:if test="${sites.size() > 1}"> &middot; <a class="text-blue-600" href="/manage/course-files">other department</a></c:if></p>
                <span class="flex flex-wrap gap-2">
                    <a class="cf-btn cf-btn-light" href="/manage/blocks?page=department.${dept}.course"><i class="fas fa-pen"></i> Edit the paragraph above the files</a>
                    <a class="cf-btn cf-btn-light" href="/department/${dept}/course" target="_blank" id="view-page"><i class="fas fa-external-link-alt"></i> View the page</a>
                </span>
            </div>

            <form method="post" action="/manage/course-files/save" enctype="multipart/form-data" class="cfm-form" id="form">
                <h4 class="font-semibold text-lg mb-3">
                    <c:choose>
                        <c:when test="${not empty editFile.courseFileId}"><i class="fas fa-edit"></i> Change course file</c:when>
                        <c:otherwise><i class="fas fa-plus-circle"></i> Add a course file</c:otherwise>
                    </c:choose>
                </h4>
                <input type="hidden" name="dept" value="${dept}">
                <c:if test="${not empty editFile.courseFileId}"><input type="hidden" name="id" value="${editFile.courseFileId}"></c:if>
                <div class="cfm-grid">
                    <div class="cfm-field">
                        <label for="semester">Semester *</label>
                        <select name="semester" id="semester" required>
                            <option value="">&ndash; choose &ndash;</option>
                            <c:forEach var="n" begin="1" end="${semesterCount}">
                                <option value="${n}" ${editFile.semester == n ? 'selected' : ''}>Semester ${n}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="cfm-field">
                        <label for="subjectCode">Subject code</label>
                        <input type="text" name="subjectCode" id="subjectCode" maxlength="30" value="<c:out value='${editFile.subjectCode}'/>" placeholder="e.g. 103301">
                    </div>
                    <div class="cfm-field cfm-wide">
                        <label for="subjectName">Subject name *</label>
                        <input type="text" name="subjectName" id="subjectName" maxlength="255" required value="<c:out value='${editFile.subjectName}'/>" placeholder="e.g. Electrical Machines - I">
                    </div>
                    <div class="cfm-field">
                        <label for="facultyName">Faculty</label>
                        <input type="text" name="facultyName" id="facultyName" maxlength="255" value="<c:out value='${editFile.facultyName}'/>" placeholder="who taught the subject">
                    </div>
                    <div class="cfm-field">
                        <label for="academicSession">Session</label>
                        <input type="text" name="academicSession" id="academicSession" maxlength="20" value="<c:out value='${editFile.academicSession}'/>" placeholder="e.g. 2025-26">
                    </div>
                    <div class="cfm-field cfm-wide">
                        <label for="file">Course file <c:if test="${empty editFile.fileId}">*</c:if></label>
                        <input type="file" name="file" id="file" accept=".pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx" ${empty editFile.fileId ? 'required' : ''}>
                        <p class="cfm-hint">PDF, Word, Excel or PowerPoint, at most 20 MB.
                            <c:if test="${not empty editFile.fileId}">Leave empty to keep the current file.</c:if></p>
                    </div>
                </div>
                <p class="mt-3"><label><input type="checkbox" name="visible" ${editFile == null or editFile.visible ? 'checked' : ''}> Show on the website</label></p>
                <p class="mt-3 flex gap-2">
                    <button type="submit" class="cf-btn cf-btn-primary"><i class="fas fa-save"></i> Save</button>
                    <c:if test="${not empty editFile.courseFileId}"><a class="cf-btn cf-btn-light" href="/manage/course-files?dept=${dept}">Cancel</a></c:if>
                </p>
            </form>

            <h4 class="font-semibold text-lg mb-2">Uploaded course files (${files.size()})</h4>
            <div class="overflow-x-auto">
            <table class="cfm-table" id="course-file-table">
                <thead><tr><th>Sem.</th><th>Code</th><th>Subject</th><th>Faculty</th><th>Session</th><th>File</th><th>Shown</th><th></th></tr></thead>
                <tbody>
                <c:if test="${empty files}"><tr><td colspan="8" class="text-gray-600" id="no-course-files">No course file uploaded yet. Use the form above.</td></tr></c:if>
                <c:forEach var="f" items="${files}">
                    <tr id="course-file-${f.courseFileId}" class="${f.visible ? '' : 'is-hidden'}">
                        <td>${f.semester}</td>
                        <td><c:out value="${f.subjectCode}"/></td>
                        <td><c:out value="${f.subjectName}"/></td>
                        <td><c:out value="${f.facultyName}"/></td>
                        <td><c:out value="${f.academicSession}"/></td>
                        <td><a class="text-blue-600" href="${f.fileUrl}" target="_blank"><i class="far fa-file-${f.fileKind}"></i> <c:out value="${f.fileName}"/></a> <span class="text-xs">(${f.fileSizeText})</span></td>
                        <td>${f.visible ? 'yes' : 'hidden'}</td>
                        <td class="cfm-actions">
                            <a class="cf-btn cf-btn-light" href="/manage/course-files?dept=${dept}&edit=${f.courseFileId}#form"><i class="fas fa-edit"></i> Change</a>
                            <form method="post" action="/manage/course-files/delete" onsubmit="return confirm('Delete this course file?');">
                                <input type="hidden" name="dept" value="${dept}"><input type="hidden" name="id" value="${f.courseFileId}">
                                <button type="submit" class="cf-btn cf-btn-danger"><i class="fas fa-trash-alt"></i> Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>
        </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>
