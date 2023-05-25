<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp"/>


    <div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>AICTE Approval</h3></div>

            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
     <aside class="col-sm-3" role="complementary">
        <div class="region region-sidebar-second">
          <section id="block-menu-block-3" class="block block-menu-block clearfix">
             <h2 class="block-title">Approval</h2>
            <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                <ul class="menu nav">
                    <li class="first leaf  menu-mlid-1154"><a href="http://localhost/approval/aicteapproval" class="sf-depth-2 active">AICTE Approval</a></li>
                      <li class="leaf menu-mlid-1121"><a href="http://localhost/approval/akuapproval" class="sf-depth-2">AKU Approval</a></li>
                    <li class="first leaf  menu-mlid-1178"><a href="http://localhost/approval/aicteapproval" class="sf-depth-2 active">Grievance</a></li>
                    <li class="first leaf  menu-mlid-1179"><a href="http://localhost/approval/aicteapproval" class="sf-depth-2 active">Mandatory Disclosure</a></li>
                 </ul>
            </div>
          </section>
          </div>
       </aside>

            <div class="col-md-8">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;"><p><img
                        class="img-responsive" src="http://localhost/jsp/approval/img.jpg" alt="AICTE" width="639" height="68"/></p>
                    <h3 data-animation="wobble" data-animation-delay="0s"> AICTE Approval</h3>
                    <div data-animation="bounceInDown" data-animation-delay="0s">
                        <table style="height: 250px;" width="400" border="1">
                            <tbody>
                            <tr bgcolor="#7eec35">
                                <td><strong>SL. No.</strong></td>
                                <td><strong>Year</strong></td>
                                <td width="472" align="center"><strong>Documents</strong></td>
                            </tr>
                            </tbody>
                            <c:forEach items ="${allDocuments}" var ="document">


                                <tr bgcolor="#e9feea">
                                    <td width="40">${document.id}</td>
                                    <td width="134">${document.title}</td>
                                    <td width="134">${document.fileName}</td>

                                    <td width="472" align="center">
                                        <i class="fa fa-download"></i>
                                        <a href="http://localhost/approval/document/${document.id}">
                                            <button type="button" class="btn btn-success" data-dismiss="modal">Download
                                            </button></a>

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

<jsp:include page="/jsp/footer.jsp"/>
