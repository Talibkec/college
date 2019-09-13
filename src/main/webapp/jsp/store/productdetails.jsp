<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>

<form  action="/store/getProductDetails" autocomplete="on" id = "productDetailsForm">
<div class="container" style="margin:50px">
        <div class="row text-center"><strong> Product Details</strong></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-2 text-center"><strong>Product Name</strong></div>
            <div class="col-md-2 text-center"><strong>Vendor Name</strong></div>
            <div class="col-md-1 text-center"><strong>Available Quantity</strong></div>
            <div class="col-md-1 text-center"><strong>Purchase Details</strong></div>
            <div class="col-md-2 text-center"><strong>Add Purchase</strong></div>
            <div class="col-md-2 text-center"><strong>Edit Product</strong></div>
            <div class="col-md-2 text-center"><strong>Delete Product</strong></div>


        </div>
         <c:forEach items="${prodList}" var="element">
            <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-2 text-center">${element.productName}</div>
            <div class="col-md-2 text-center" >${element.vendorName}</div>
            <div class="col-md-1 text-center">${element.availableQuantity}</div>
            <div class="col-md-1 text-center"><a href="http://localhost/store/purchasedetails?name=${element.productName}&vendor=${element.vendorName}&productQuantity=${element.productQuantity}&productId=${element.productId}">Details</a></div>
            <div class="col-md-2 text-center"><a href="http://localhost/store/addPurchase?name=${element.productName}&vendor=${element.vendorName}&productQuantity=${element.productQuantity}&productId=${element.productId}">Purchase</a></div>
            <div class="col-md-2 text-center"><a href="http://localhost/store/editProduct?productId=${element.productId}">Edit Product</a></div>
            <div class="col-md-2 text-center"><a href="http://localhost/store/deleteProduct?productId=${element.productId}">Delete Product</a></div>

        </div>
        </c:forEach>
	</div>
</form>

<script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="http://localhost/js/jquery-ui.js"></script>
<script src="http://localhost/js/store/storemanager.js"></script>
</body>
</html>