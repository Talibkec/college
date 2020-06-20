<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>

<form  action="/store/getProductDetails" autocomplete="on" id = "productDetailsForm">
<div class="container" style="margin:50px">
        <div class="row text-center"><strong> Product Details</strong></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-1 text-center"><strong>Quantity</strong></div>
            <div class="col-md-2 text-center"><strong>Purchase Date</strong></div>
            <div class="col-md-2 text-center"><strong>Purchase By</strong></div>
            <div class="col-md-2 text-center"><strong>Price</strong></div>
            <div class="col-md-1 text-center"><strong>Invoice</strong></div>
            <div class="col-md-2 text-center"><strong>Edit Purchase</strong></div>
            <div class="col-md-2 text-center"><strong>Delete Purchase</strong></div>
        </div>
         <c:forEach items="${purchases}" var="element">
            <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-1 text-center">${element.quantity}</div>
            <div class="col-md-2 text-center" >${element.purchaseDate}</div>
            <div class="col-md-2 text-center">${element.purchaseBy}</div>
            <div class="col-md-2 text-center">${element.price}</div>
            <div class="col-md-1 text-center">${element.invoice}</div>
            <div class="col-md-2 text-center"><a href="http://localhost/store/editPurchase?purchaseId=${element.purchaseId}">Edit Purchase</a></div>
            <div class="col-md-2 text-center"><a href="http://localhost/store/deletePurchase?purchaseId=${element.purchaseId}">Delete Purchase</a></div>
        </div>
        </c:forEach>
	</div>
</form>

<script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="http://localhost/js/jquery-ui.js"></script>
<script src="http://localhost/js/store/editpurchase.js"></script>
</body>
</html>
