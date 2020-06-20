<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>

<form action="/fstore/faculty/updateRequest"  method="POST">
<div class="container" style="margin:50px">
        <div class="row text-center"><strong> Product Details</strong></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center"><strong>Product Name</strong></div>
            <div class="col-md-3 text-center"><strong>Vendor Name</strong></div>
            <div class="col-md-3 text-center"><strong>Requested Qty</strong></div>
            <div class="col-md-3 text-center"><strong>Update</strong></div>
        </div>
            <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center">${request.product.productName}</div>
            <div class="col-md-3 text-center" >${request.product.vendorName}</div>
            <div class="col-md-3 text-center"><input type = "text" name = "requestedQty" value="${request.productQuantity}" id="prodQuant" ></input></div>
            <input type="hidden" name = "requestId" value="${request.requestId}" />
            <div class="col-md-3 text-center"><input type="submit" value="update" id="update" ></div>
            <h5 id="prod"></h5>
        </div>
	</div>
</form>

<script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="http://localhost/js/jquery-ui.js"></script>
<script src="http://localhost/js/store/editfacultyrequest.js"></script>
</body>
</html>
