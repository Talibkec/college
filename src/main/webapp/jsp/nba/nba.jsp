<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp" />

<style>
    td {
        padding: 10px !important;
    }
    .btn-open {
        display: inline-flex;
        align-items: center;
        gap: 6px;
        padding: 5px 14px;
        border-radius: 6px;
        background: #1a7a4a;
        color: #fff !important;
        font-size: 13px;
        font-weight: 500;
        text-decoration: none !important;
    }
    .btn-open:hover {
        background: #145c38;
        color: #fff !important;
    }
    .btn-delete {
        display: inline-flex;
        align-items: center;
        gap: 6px;
        padding: 5px 14px;
        border-radius: 6px;
        background: #c0392b;
        color: #fff !important;
        font-size: 13px;
        font-weight: 500;
        text-decoration: none !important;
        border: none;
        cursor: pointer;
    }
    .btn-delete:hover {
        background: #962d22;
        color: #fff !important;
    }
</style>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-sm-8"><h3>NBA Documents</h3></div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                <h3 data-animation="wobble" data-animation-delay="0s">Downloads Resources</h3>
                <div data-animation="bounceInDown" data-animation-delay="0s">
                    <table style="width: 100%; padding: 10px;">
                        <tbody>
                        <tr bgcolor="#7eec35">
                            <td><strong>SL. No.</strong></td>
                            <td><strong>Year</strong></td>
                            <td><strong>Documents</strong></td>
                            <td>Actions</td>
                            <td>
                                <c:if test="${showDeleteBtn}">
                                    <strong>Delete</strong>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>

                        <c:set var="count" value='1'/>
                        <c:forEach items="${allDocuments}" var="document">
                            <tr bgcolor="#e9feea">
                                <td>${count}</td>
                                <c:set var="count" value="${count+1}"/>
                                <td>${document.date}</td>
                                <td>${document.fileName}</td>

                                    <%-- Open button - opens in new tab --%>
                                <td align="center">
                                    <a href="/facilities/document/${document.id}"
                                       target="_blank"
                                       rel="noopener noreferrer"
                                       class="btn-open">
                                        <svg width="13" height="13" viewBox="0 0 16 16" fill="none"
                                             stroke="currentColor" stroke-width="2">
                                            <path d="M7 3H3a1 1 0 00-1 1v9a1 1 0 001 1h9a1 1 0 001-1V9"/>
                                            <path d="M10 2h4v4"/>
                                            <line x1="14" y1="2" x2="7" y2="9"/>
                                        </svg>
                                        Open
                                    </a>
                                </td>

                                    <%-- Delete button - only shown if showDeleteBtn is true --%>
                                <td align="center">
                                    <c:if test="${showDeleteBtn}">
                                        <a href="/facilities/deletedocument/${document.id}"
                                           class="btn-delete">
                                            <svg width="13" height="13" viewBox="0 0 16 16" fill="none"
                                                 stroke="currentColor" stroke-width="2">
                                                <polyline points="3 6 13 6"/>
                                                <path d="M5 6V4a1 1 0 011-1h4a1 1 0 011 1v2"/>
                                                <rect x="3" y="6" width="10" height="8" rx="1"/>
                                            </svg>
                                            Delete
                                        </a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <p>&nbsp;</p>
                    <p>&nbsp;</p>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp" />