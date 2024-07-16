<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp" />

<style>
    td {
        padding: 10px !important;
    }
</style>
<div class="jumbotron flex justify-center w-full" style="">
    <div class="">
        <div class="row">
            <div class="col-md-10 col-sm-8"><h3 class="text-2xl">Administrative Responsibility </h3></div>

        </div>
    </div>
</div>
<div class="p-5 ">
    <div class="row">


        <div class="col-md-12">
            <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                <h3 data-animation="wobble" data-animation-delay="0s" class="text-2xl"> Responsibility Docs</h3>
                <div data-animation="bounceInDown" data-animation-delay="0s">
                    <table style="width: 100%; padding: 10px;">
                        <tbody>
                        <tr bgcolor="#7eec35">
                            <td><strong>SL. No.</strong></td>
                            <td><strong>Year</strong></td>
                            <td >Download</td>
                            <td>
                            <c:if test="${showDeleteBtn}">
                                Delete
                            </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <c:set var="count" value='1'/>
                        <c:set var="embedpdf" value='true'/>
                        <c:forEach items="${allDocuments}" var ="document">

                            <c:if test="${embedpdf}">

                                <div>
                                    <object data="https://drive.google.com/viewerng/viewer?embedded=true&url=http://keck.ac.in/approval/responsibilityDoc/${document.id}" type="application/pdf" width="700" height="1000">
                                        <embed src="https://drive.google.com/viewerng/viewer?embedded=true&url=http://keck.ac.in/approval/responsibilityDoc/${document.id}" type="application/pdf">
                                    </object>
                                </div>

                            </c:if>
                            <c:set var="embedpdf" value="${false}"/>
                            <tr bgcolor="#e9feea">
                                <td>${count}</td>
                                <c:set var="count" value="${count+1}"/>
                                <td>${document.fileName}</td>
<%--                                <td >${document.uploadedBy}</td>--%>

                                <td align="center">
                                    <i class="fa fa-download"></i>
                                    <a href="/approval/responsibilityDoc/${document.id}">
                                        <button type="button" class="btn btn-success" data-dismiss="modal">Download
                                        </button></a>
                                </td><td>
                                <c:if test="${showDeleteBtn}">
                                    <a href="/approval/deleteResponsibilityDoc/${document.id}">
                                        <button class="btn btn-danger mt-2 p-2"> Delete Document</button>
                                    </a>
                                </c:if>

                            </td>
                            </tr>

                        </c:forEach>




                    </table>
                    <p>&nbsp;</p>
                    <p>&nbsp;</p></div>
            </div>
        </div>
    </div>
</div>



<jsp:include page="/jsp/footer.jsp" />