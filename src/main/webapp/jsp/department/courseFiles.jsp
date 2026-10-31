<%@ taglib prefix="c" uri="jakarta.tags.core" %><%@ taglib prefix="fn" uri="jakarta.tags.functions" %><%--
  The course files of a department site, below the editable paragraph of /department/<site>/course.
  Model from CourseFileController: courseSite, courseSemesters (semester -> files), courseFileCount, courseCanManage,
  courseTextEditor.
--%><link rel="stylesheet" href="/css/course-files.css">
<section class="cf" id="course-files" data-site="${courseSite}">
    <c:if test="${courseCanManage or courseTextEditor}">
        <div class="cf-admin" id="course-files-admin">
            <span class="cf-admin-label"><i class="fas fa-user-shield"></i> You can change this page</span>
            <span class="cf-admin-actions">
                <c:if test="${courseTextEditor}"><a href="/manage/blocks?page=department.${courseSite}.course" class="cf-btn cf-btn-light" id="edit-course-text"><i class="fas fa-pen"></i> Edit paragraph</a></c:if>
                <c:if test="${courseCanManage}"><a href="/manage/course-files?dept=${courseSite}" class="cf-btn cf-btn-primary" id="manage-course-files"><i class="fas fa-upload"></i> Upload / manage course files</a></c:if>
            </span>
        </div>
    </c:if>

    <c:choose>
        <c:when test="${courseFileCount == 0}">
            <div class="cf-empty" id="course-files-empty">
                <i class="fas fa-folder-open"></i>
                <h4>Course files will be published here soon</h4>
                <p>The department is preparing the course files of its subjects. Please check again later.</p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="cf-toolbar">
                <label class="cf-search">
                    <i class="fas fa-search" aria-hidden="true"></i>
                    <input type="search" id="cf-search" placeholder="Search subject, code or faculty" aria-label="Search course files" autocomplete="off">
                </label>
                <div class="cf-chips" id="cf-chips">
                    <button type="button" class="cf-chip is-active" data-sem="all">All <span>${courseFileCount}</span></button>
                    <c:forEach var="s" items="${courseSemesters}">
                        <button type="button" class="cf-chip" data-sem="${s.key}">Sem ${s.key} <span>${fn:length(s.value)}</span></button>
                    </c:forEach>
                </div>
            </div>

            <c:forEach var="s" items="${courseSemesters}">
                <div class="cf-semester" data-sem="${s.key}">
                    <h4 class="cf-semester-title"><span class="cf-sem-badge">${s.key}</span> Semester ${s.key}
                        <small>${fn:length(s.value)} ${fn:length(s.value) == 1 ? 'subject' : 'subjects'}</small></h4>
                    <ul class="cf-list">
                        <c:forEach var="f" items="${s.value}">
                            <li class="cf-card" id="course-file-${f.courseFileId}"
                                data-search="<c:out value='${fn:toLowerCase(f.subjectCode)} ${fn:toLowerCase(f.subjectName)} ${fn:toLowerCase(f.facultyName)}'/>">
                                <div class="cf-icon cf-${f.fileKind}" aria-hidden="true"><i class="far fa-file-${f.fileKind}"></i></div>
                                <div class="cf-body">
                                    <c:if test="${not empty f.subjectCode}"><span class="cf-code"><c:out value="${f.subjectCode}"/></span></c:if>
                                    <h5 class="cf-subject"><c:out value="${f.subjectName}"/></h5>
                                    <p class="cf-meta">
                                        <c:if test="${not empty f.facultyName}"><span><i class="fas fa-chalkboard-teacher"></i> <c:out value="${f.facultyName}"/></span></c:if>
                                        <c:if test="${not empty f.academicSession}"><span><i class="far fa-calendar-alt"></i> <c:out value="${f.academicSession}"/></span></c:if>
                                        <span><i class="far fa-file"></i> ${f.fileExtension} &middot; ${f.fileSizeText}</span>
                                    </p>
                                </div>
                                <div class="cf-actions">
                                    <c:if test="${f.pdf}"><a class="cf-btn cf-btn-light" href="${f.fileUrl}" target="_blank" rel="noopener"><i class="far fa-eye"></i> View</a></c:if>
                                    <a class="cf-btn cf-btn-primary" href="${f.fileUrl}" download><i class="fas fa-download"></i> Download</a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:forEach>
            <p class="cf-nomatch" id="cf-nomatch" hidden>No course file matches your search.</p>
            <script>
                (function () {
                    var search = document.getElementById('cf-search');
                    var chips = document.querySelectorAll('#cf-chips .cf-chip');
                    var semesters = document.querySelectorAll('#course-files .cf-semester');
                    var nomatch = document.getElementById('cf-nomatch');
                    var current = 'all';
                    function filter() {
                        var q = search.value.trim().toLowerCase();
                        var shown = 0;
                        semesters.forEach(function (sem) {
                            var inSem = 0;
                            sem.querySelectorAll('.cf-card').forEach(function (card) {
                                var ok = (current === 'all' || sem.getAttribute('data-sem') === current)
                                    && (q === '' || card.getAttribute('data-search').indexOf(q) >= 0);
                                card.hidden = !ok;
                                if (ok) inSem++;
                            });
                            sem.hidden = inSem === 0;
                            shown += inSem;
                        });
                        nomatch.hidden = shown > 0;
                    }
                    chips.forEach(function (chip) {
                        chip.addEventListener('click', function () {
                            chips.forEach(function (c) { c.classList.remove('is-active'); });
                            chip.classList.add('is-active');
                            current = chip.getAttribute('data-sem');
                            filter();
                        });
                    });
                    search.addEventListener('input', filter);
                })();
            </script>
        </c:otherwise>
    </c:choose>
</section>
