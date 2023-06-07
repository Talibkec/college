<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp" />


<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-sm-8"><h3>Downloads </h3></div>

        </div>
    </div>
</div>
<div class="container">
    <div class="row">


        <div class="col-md-12">
            <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                <h3 data-animation="wobble" data-animation-delay="0s"> Downloads Resources</h3>
                <div data-animation="bounceInDown" data-animation-delay="0s">
                    <table style="width: 100%; padding: 10px;">
                        <tbody>
                        <tr bgcolor="#7eec35">
                            <td><strong>SL. No.</strong></td>
                            <td><strong>Year</strong></td>
                            <td ><strong>Documents</strong></td>
                            <td ></td>
                        </tr>
                        </tbody>
                        <c:forEach items="${allDocuments}" var ="document">


                            <tr bgcolor="#e9feea">
                                <td width="40">${document.id}</td>
                                <td width="134">${document.fileName}</td>
                                <td width="134">${document.fileName}</td>

                                <td width="472" align="center">
                                    <i class="fa fa-download"></i>
                                    <a href="/facilities/document/${document.id}">
                                        <button type="button" class="btn btn-success" data-dismiss="modal">Download
                                        </button></a>
                                </td><td>
                                <c:if test="${showDeleteBtn}">
                                    <a href="/facilities/deletedocument/${document.id}">
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