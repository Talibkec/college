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
            <div class="row elements" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center">${element.productName}</div>
            <div class="col-md-3 text-center" >${element.vendorName}</div>
            <div class="col-md-2 text-center" id ="availableQuantity${element.productName}">${element.availableQuantity}</div>
            <div class="col-md-2 text-center"><input type = "number" name = "requestedQty" id="requestedQty"  placeholder="Items needed"/></div>
            <div class="col-md-2 text-center"><button type="submit" value = "request" id="request"> Request</button></div>
             <input type="hidden" name = "productName" value = "${element.productName}" />
             <input type="hidden" name = "vendorName" value = "${element.vendorName}" />
             <input type="hidden" name = "productId" value = "${element.productId}" />
             <h5 id="print"></h5>
            </form>
        </div>
        </c:forEach>
	</div>

<link rel="stylesheet" href="http://localhost/js/store/jquery-ui/jquery-ui.css">
<script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="http://localhost/js/store/jquery-ui/jquery-ui.js"></script>
</body>
</html>