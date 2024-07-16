<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp"/>

<div class="flex p-4 bg-gray-200 text-3xl p-5 w-full">
    AICTE Approval

</div>
<style>
    td {
        padding: 10px;
    }
</style>
    <div class="jumbotron flex justify-center">
        <div class="">
          
        </div>
    </div>
    <div class="container">
        <div class="md:flex justify-center">
     <aside class="col-sm-3" role="complementary"  style="">
        <div class="region region-sidebar-second">
          <section id="block-menu-block-3" class="block block-menu-block clearfix">
             <h2 class="block-title">Approval</h2>
            <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                <ul class="menu nav">
                    <li class="first leaf  menu-mlid-1154"><a href="/approval/aicteapproval" class="sf-depth-2 active">AICTE Approval</a></li>
                      <li class="leaf menu-mlid-1121"><a href="/approval/akuapproval" class="sf-depth-2">AKU Approval</a></li>
                    <li class="first leaf  menu-mlid-1178"><a href="/approval/grievance" class="sf-depth-2 active">Grievance</a></li>
                    <li class="first leaf  menu-mlid-1179"><a href="/approval/mandatorydisclosure" class="sf-depth-2 active">Mandatory Disclosure</a></li>
                 </ul>
            </div>
          </section>
          </div>
       </aside>

            <div class="col-md-8 p-3"  style="">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;"><p><img
                        class="img-responsive" src="/jsp/approval/img.jpg" alt="AICTE" width="639" height="150"/></p>
                    <div data-animation="bounceInDown" data-animation-delay="0s">
                        <table class="w-full p-3" border="1">
                            <tbody>
                            <tr bgcolor="#7eec35">
                                <td><strong>#</strong></td>
                                <td><strong>Year</strong></td>
                                <td><strong>Documents</strong></td>
                                <td></td>
                            </tr>
                            </tbody>
                            <c:forEach items ="${allDocuments}" var ="document">


                                <tr>
                                    <td width="40">${document.id}</td>
                                    <td width="134">${document.title}</td>
                                    <td width="134">${document.fileName}</td>

                                    <td class="flex justify-center">
                                        <i class="fa fa-download"></i>
                                        <a href="/approval/document/${document.id}">
                                            <button type="button" class="btn btn-success" data-dismiss="modal">Download
                                            </button></a>
                                    </td><td>
                                        <c:if test="${showDeleteBtn}">
                                            <a href="/approval/deletedocument/${document.id}">
                                                <button class="btn btn-danger mt-2 p-2"> Delete Document</button>
                                            </a>
                                        </c:if>

                                    </td>
                                </tr>

                            </c:forEach>




                        </table>
                        </div>
                </div>
            </div>
        </div>
    </div>

<jsp:include page="/jsp/footer.jsp"/>
