<%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/jsp/header.jsp"/>

<style>
    @import url('https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700;900&family=Source+Serif+4:ital,wght@0,300;0,400;0,600;1,400&display=swap');

    :root {
        --ink: #1a1410;
        --paper: #faf7f2;
        --cream: #f2ede3;
        --accent: #c0392b;
        --accent-dark: #96281b;
        --gold: #b8963e;
        --rule: #d4c9b0;
        --muted: #7a6f60;
        --shadow: rgba(26,20,16,0.12);
    }

    * { box-sizing: border-box; margin: 0; padding: 0; }

    body {
        background-color: var(--paper);
        color: var(--ink);
        font-family: 'Source Serif 4', Georgia, serif;
    }

    /* ── Masthead ─────────────────────────────────────── */
    .notices-masthead {
        background: var(--ink);
        padding: 40px 0 28px;
        position: relative;
        overflow: hidden;
    }
    .notices-masthead::before {
        content: '';
        position: absolute;
        inset: 0;
        background: repeating-linear-gradient(
                90deg,
                transparent,
                transparent 59px,
                rgba(255,255,255,0.03) 59px,
                rgba(255,255,255,0.03) 60px
        );
    }
    .notices-masthead .inner {
        max-width: 900px;
        margin: 0 auto;
        padding: 0 24px;
        position: relative;
    }
    .masthead-rule {
        width: 100%;
        border: none;
        border-top: 3px solid var(--accent);
        margin-bottom: 14px;
    }
    .masthead-label {
        font-family: 'Source Serif 4', serif;
        font-size: 10px;
        font-weight: 600;
        letter-spacing: 0.3em;
        text-transform: uppercase;
        color: var(--gold);
        margin-bottom: 8px;
    }
    .masthead-title {
        font-family: 'Playfair Display', serif;
        font-size: clamp(2.2rem, 5vw, 3.6rem);
        font-weight: 900;
        color: #fff;
        line-height: 1.05;
        letter-spacing: -0.02em;
    }
    .masthead-title span { color: var(--accent); }
    .masthead-sub {
        font-size: 13px;
        color: rgba(255,255,255,0.45);
        margin-top: 8px;
        letter-spacing: 0.04em;
    }
    .masthead-rule-bottom {
        margin-top: 20px;
        border: none;
        border-top: 1px solid rgba(255,255,255,0.12);
    }

    /* ── Layout ───────────────────────────────────────── */
    .notices-layout {
        max-width: 900px;
        margin: 0 auto;
        padding: 40px 24px 80px;
        display: grid;
        grid-template-columns: 1fr 260px;
        gap: 48px;
        align-items: start;
    }
    @media (max-width: 720px) {
        .notices-layout { grid-template-columns: 1fr; gap: 32px; }
        .notices-sidebar { order: -1; }
    }

    /* ── Notice Cards ─────────────────────────────────── */
    .notices-feed { display: flex; flex-direction: column; gap: 0; }

    .notice-card {
        background: #fff;
        border: 1px solid var(--rule);
        border-left: 4px solid var(--accent);
        padding: 24px 28px;
        margin-bottom: 2px;
        transition: box-shadow .2s ease, transform .15s ease;
        position: relative;
    }
    .notice-card:hover {
        box-shadow: 4px 4px 0 var(--accent), 0 8px 32px var(--shadow);
        transform: translate(-2px, -2px);
        z-index: 1;
    }

    .notice-meta {
        display: flex;
        align-items: center;
        gap: 10px;
        margin-bottom: 10px;
        flex-wrap: wrap;
    }
    .notice-badge {
        font-size: 9px;
        font-weight: 700;
        letter-spacing: 0.2em;
        text-transform: uppercase;
        background: var(--accent);
        color: #fff;
        padding: 2px 8px;
        border-radius: 2px;
    }
    .notice-date {
        font-size: 11px;
        color: var(--muted);
        letter-spacing: 0.05em;
        display: flex;
        align-items: center;
        gap: 4px;
    }
    .notice-date i { font-size: 10px; }

    .notice-headline {
        font-family: 'Playfair Display', serif;
        font-size: 1.2rem;
        font-weight: 700;
        line-height: 1.4;
        color: var(--ink);
        margin-bottom: 18px;
    }

    .notice-actions {
        display: flex;
        align-items: center;
        gap: 10px;
        flex-wrap: wrap;
    }

    .btn-read {
        display: inline-flex;
        align-items: center;
        gap: 6px;
        padding: 8px 18px;
        background: var(--ink);
        color: #fff;
        font-family: 'Source Serif 4', serif;
        font-size: 12px;
        font-weight: 600;
        letter-spacing: 0.08em;
        text-decoration: none;
        border-radius: 2px;
        transition: background .15s, transform .1s;
    }
    .btn-read:hover {
        background: var(--accent);
        transform: translateY(-1px);
        color: #fff;
        text-decoration: none;
    }

    .btn-delete {
        display: inline-flex;
        align-items: center;
        gap: 6px;
        padding: 8px 16px;
        background: transparent;
        color: var(--accent);
        border: 1px solid var(--accent);
        font-family: 'Source Serif 4', serif;
        font-size: 12px;
        font-weight: 600;
        letter-spacing: 0.08em;
        cursor: pointer;
        border-radius: 2px;
        text-decoration: none;
        transition: background .15s, color .15s, transform .1s;
    }
    .btn-delete:hover {
        background: var(--accent);
        color: #fff;
        transform: translateY(-1px);
    }

    /* ── Divider between cards ───────────────────────── */
    .notices-feed .notice-card + .notice-card {
        border-top: 1px dashed var(--rule);
    }

    /* ── Empty state ─────────────────────────────────── */
    .notices-empty {
        padding: 56px 24px;
        text-align: center;
        background: var(--cream);
        border: 1px dashed var(--rule);
    }
    .notices-empty p {
        font-style: italic;
        color: var(--muted);
        font-size: 1.05rem;
    }

    /* ── Pagination ──────────────────────────────────── */
    .pagination-bar {
        display: flex;
        justify-content: space-between;
        margin-top: 32px;
        gap: 10px;
    }
    .pag-btn {
        display: inline-flex;
        align-items: center;
        gap: 8px;
        padding: 10px 22px;
        background: var(--ink);
        color: #fff;
        font-family: 'Source Serif 4', serif;
        font-size: 12px;
        font-weight: 600;
        letter-spacing: 0.1em;
        text-decoration: none;
        border-radius: 2px;
        text-transform: uppercase;
        transition: background .15s;
    }
    .pag-btn:hover { background: var(--accent); color: #fff; text-decoration: none; }
    .pag-btn i { font-size: 13px; }

    /* ── Sidebar ─────────────────────────────────────── */
    .notices-sidebar {}
    .sidebar-block {
        border: 1px solid var(--rule);
        background: var(--cream);
        margin-bottom: 24px;
    }
    .sidebar-block-title {
        font-family: 'Playfair Display', serif;
        font-size: 11px;
        font-weight: 700;
        letter-spacing: 0.25em;
        text-transform: uppercase;
        color: var(--muted);
        padding: 12px 18px;
        border-bottom: 1px solid var(--rule);
        background: var(--ink);
        color: rgba(255,255,255,0.7);
    }
    .sidebar-block-body { padding: 16px 18px; }
    .sidebar-block-body p {
        font-size: 13px;
        line-height: 1.65;
        color: var(--muted);
        font-style: italic;
    }

    /* ── Modal overlay ───────────────────────────────── */
    .modal-overlay {
        display: none;
        position: fixed;
        inset: 0;
        background: rgba(26,20,16,0.65);
        z-index: 1000;
        align-items: center;
        justify-content: center;
    }
    .modal-overlay.active { display: flex; }
    .modal-box {
        background: #fff;
        border-top: 4px solid var(--accent);
        padding: 32px 36px;
        max-width: 400px;
        width: 90%;
        animation: slideUp .2s ease;
    }
    @keyframes slideUp {
        from { transform: translateY(20px); opacity: 0; }
        to   { transform: translateY(0);    opacity: 1; }
    }
    .modal-box h4 {
        font-family: 'Playfair Display', serif;
        font-size: 1.25rem;
        margin-bottom: 10px;
    }
    .modal-box p { font-size: 14px; color: var(--muted); margin-bottom: 24px; line-height: 1.55; }
    .modal-actions { display: flex; gap: 10px; justify-content: flex-end; }
    .btn-cancel {
        padding: 9px 20px;
        border: 1px solid var(--rule);
        background: transparent;
        font-family: 'Source Serif 4', serif;
        font-size: 13px;
        cursor: pointer;
        border-radius: 2px;
        transition: background .15s;
    }
    .btn-cancel:hover { background: var(--cream); }
    .btn-confirm-delete {
        padding: 9px 20px;
        background: var(--accent);
        color: #fff;
        border: none;
        font-family: 'Source Serif 4', serif;
        font-size: 13px;
        font-weight: 600;
        cursor: pointer;
        border-radius: 2px;
        transition: background .15s;
        text-decoration: none;
        display: inline-block;
    }
    .btn-confirm-delete:hover { background: var(--accent-dark); color: #fff; }
</style>

<!-- Masthead -->
<div class="notices-masthead">
    <div class="inner">
        <hr class="masthead-rule"/>
        <div class="masthead-label">Official Communications</div>
        <h1 class="notices-title masthead-title">Notice <span>&amp;</span> News Board</h1>
        <p class="masthead-sub">Stay informed — all official notices and news in one place</p>
        <hr class="masthead-rule-bottom"/>
    </div>
</div>

<!-- Main layout -->
<div class="notices-layout">

    <!-- Feed -->
    <main>
        <div class="notices-feed">

            <c:set var="hasItems" value="false"/>
            <c:forEach items="${noticeList}" var="notice">
                <c:if test="${notice.noticeType == 'News' or notice.noticeType == 'Notice'}">
                    <c:set var="hasItems" value="true"/>
                    <article class="notice-card">
                        <div class="notice-meta">
                            <span class="notice-badge">${notice.noticeType}</span>
                            <span class="notice-date">
                <i class="fa fa-clock-o"></i>
                ${notice.date}
              </span>
                        </div>
                        <div class="notice-headline">${notice.headLine}</div>
                        <div class="notice-actions">
                            <a href="/notice/${notice.id}" class="btn-read">
                                Read More &rarr;
                            </a>
                            <button
                                    class="btn-delete"
                                    onclick="openDeleteModal(${notice.id})"
                                    aria-label="Delete this notice">
                                <i class="fa fa-trash"></i> Delete
                            </button>
                        </div>
                    </article>
                </c:if>
            </c:forEach>

            <c:if test="${not hasItems}">
                <div class="notices-empty">
                    <p>No notices or news to display at this time.</p>
                </div>
            </c:if>

        </div>

        <!-- Pagination -->
        <c:if test="${empty pageSize}">
            <c:set var="pageSize" value="0"/>
        </c:if>
        <nav class="pagination-bar" aria-label="Pagination">
            <a href="/pagination/prev/getNotices?deptno=category&pageSize=${pageSize - 1}" class="pag-btn">
                <i class="fa fa-angle-double-left"></i> Previous
            </a>
            <a href="/pagination/next/getNotices?deptno=category&pageSize=${pageSize + 1}" class="pag-btn">
                Next <i class="fa fa-angle-double-right"></i>
            </a>
        </nav>
    </main>

    <!-- Sidebar -->
    <aside class="notices-sidebar">
        <div class="sidebar-block">
            <div class="sidebar-block-title">About This Board</div>
            <div class="sidebar-block-body">
                <p>This board publishes official notices and news. Check regularly for updates and important announcements.</p>
            </div>
        </div>
    </aside>

</div>

<!-- Delete Confirmation Modal -->
<div class="modal-overlay" id="deleteModal" role="dialog" aria-modal="true" aria-labelledby="modalTitle">
    <div class="modal-box">
        <h4 id="modalTitle">Confirm Deletion</h4>
        <p>Are you sure you want to permanently delete this notice? This action cannot be undone.</p>
        <div class="modal-actions">
            <button class="btn-cancel" onclick="closeDeleteModal()">Cancel</button>
            <a id="confirmDeleteBtn" href="#" class="btn-confirm-delete">Delete Notice</a>
        </div>
    </div>
</div>

<script>
    function openDeleteModal(id) {
        document.getElementById('confirmDeleteBtn').href = '/auth/delNotice/' + id;
        document.getElementById('deleteModal').classList.add('active');
    }
    function closeDeleteModal() {
        document.getElementById('deleteModal').classList.remove('active');
    }
    // Close on backdrop click
    document.getElementById('deleteModal').addEventListener('click', function(e) {
        if (e.target === this) closeDeleteModal();
    });
    // Close on Escape key
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape') closeDeleteModal();
    });
</script>

<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>
