<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>

<div class="container" style="margin:50px">
<div style="padding: 15px;">
    <div class="row" style="border:1px solid green;padding:10px">
        <div class="col-md-2 text-center"><strong>Faculty Name</strong></div>
        <div class="col-md-2 text-center"><strong>Product Name</strong></div>
        <div class="col-md-2 text-center"><strong>Qty</strong></div>
        <div class="col-md-2 text-center"><strong>Requested Date</strong></div>
        <div class="col-md-2 text-center"><strong>Approval Date</strong></div>
        <div class="col-md-2 text-center"><strong>Delivery Date</strong></div>
    </div>
<div class="" style>
     <c:forEach items="${orders}" var="order">
        <div class="row" style="border:1px solid green;padding:10px">
           <div class="col-md-2 text-center">${order.facultyName}</div>
           <div class="col-md-2 text-center">${order.product.productName}</div>
           <div class="col-md-2 text-center">${order.productQuantity}</div>
           <div class="col-md-2 text-center"><fmt:formatDate value="${order.requestDate}" pattern="dd/MM/yyyy"/></div>
           <div class="col-md-2 text-center"><fmt:formatDate value="${order.approval}" pattern="dd/MM/yyyy"/></div>
           <div class="col-md-2 text-center"><fmt:formatDate value="${order.deliveryDate}" pattern="dd/MM/yyyy"/></div>
        </div>
     </c:forEach>
  </div>
</div>
</div>


 <link rel="stylesheet" href="http://keck.ac.in/js/store/jquery-ui/jquery-ui.css">
 <script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
 <script src="http://keck.ac.in/js/store/jquery-ui/jquery-ui.js"></script>
</body>
</html>