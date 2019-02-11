<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>


<div class="container" style="margin:50px">
        <div class="row text-center"><strong> Product Details</strong></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center"><strong>Product Name</strong></div>
            <div class="col-md-3 text-center"><strong>Vendor Name</strong></div>
            <div class="col-md-2 text-center"><strong>Available Quantity</strong></div>
            <div class="col-md-2 text-center"><strong>Requested Qty</strong></div>
            <div class="col-md-2 text-center"><strong>Request</strong></div>
        </div>
         <c:forEach items="${prodList}" var="element">
            <form  action="/store/faculty/productrequest">
            <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center">${element.productName}</div>
            <div class="col-md-3 text-center" >${element.vendorName}</div>
            <div class="col-md-2 text-center">${element.availableQuantity}</div>
            <div class="col-md-2 text-center"><input type = "number" name = "requestedQty"  placeholder="Items needed"/></div>
            <div class="col-md-2 text-center"><button type="submit" value = "request"> Request</button></div>
             <input type="hidden" name = "productName" value = "${element.productName}" />
             <input type="hidden" name = "vendorName" value = "${element.vendorName}" />
             <input type="hidden" name = "productId" value = "${element.productId}" />
            </form>
        </div>
        </c:forEach>
	</div>

<script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="http://localhost/js/jquery-ui.js"></script>
<script src="http://localhost/js/store/facultyproductsearch.js"></script>
</body>
</html>