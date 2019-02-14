<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>

<form  action="/store/getProductDetails" autocomplete="on" id = "searchProductForm">
<div class="container" style="margin:50px">
        <div class="row text-center"><strong> Search Product</strong></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center"><strong>Search By ProductName</strong></div>
            <div class="col-md-3 text-center"><strong>Search By Vendor Name</strong></div>
            <div class="col-md-3 text-center"><strong>Search By Product Id</strong></div>
        </div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center"><input type="text" name="prodName" id = "prodName" /></div>
            <div class="col-md-3 text-center" ><input type="text" name="vendorName" id = "vendorName" /></div>
            <div class="col-md-3 text-center"><input type="text" name="productId" id = "productId"/></div>
            <div class="col-md-3 text-center"><input type="submit" value="submit" id="getProductDetails"/></div>
        </div>
	</div>
</form>
<c:if test = "${productFound == false}">
    <div class="row">
        <div class="text-center"><a href="http://localhost/store/addProduct" class="btn btn-success">Add Product</a><div>
    </div>
</c:if>

<c:if test = "${requestSize gt 0}">
<div class="container" style="margin:50px">
<div style="padding: 15px;">
    <div class="row" style="border:1px solid green;padding:10px">
        <div class="col-md-2 text-center"><strong>Product Name</strong></div>
        <div class="col-md-2 text-center"><strong>Qty</strong></div>
        <div class="col-md-2 text-center"><strong>Status</strong></div>
        <div class="col-md-2 text-center"><strong>Requested Date</strong></div>
        <div class="col-md-2 text-center"><strong>Approval / Cancellation Date</strong></div>
        <div class="col-md-2 text-center"><strong>Close</strong></div>
    </div>
<div class="" style>
     <c:forEach items="${request}" var="req">
        <div class="row" style="border:1px solid green;padding:10px">
           <div class="col-md-2 text-center">${req.product.productName}</div>
           <div class="col-md-2 text-center">${req.productQuantity}</div>
           <div class="col-md-2 text-center">${req.status}</div>
           <div class="col-md-2 text-center"><fmt:formatDate value="${req.requestDate}" pattern="dd/MM/yyyy"/></div>
           <div class="col-md-2 text-center"><fmt:formatDate value="${req.approvalDate}" pattern="dd/MM/yyyy"/></div>
           <div class="col-md-2 text-center"><a href="http://localhost/store/faculty/closefacultyrequest?requestId=${req.requestId}">Close</a></div>
        </div>
     </c:forEach>
  </div>
</div>
</div>
</c:if>

<script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
 <script src="http://localhost/js/jquery-ui.js"></script>
<script src="http://localhost/js/store/storkeeper.js"></script>
</body>
</html>