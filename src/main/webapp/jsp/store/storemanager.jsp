<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>

<form  action="/store/getProductDetails" autocomplete="on" id = "searchProductForm">
<div class="container" style="margin:50px">
        <div class="row text-center"><h3> Search Product</h3></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center"><strong>Search By ProductName</strong></div>
            <div class="col-md-3 text-center"><strong>Search By Vendor Name</strong></div>
            <div class="col-md-2 text-center"><strong>Search By Product Id</strong></div>
            <div class="col-md-2 text-center"></div>
            <div class="col-md-2 text-center"></div>
        </div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center"><input type="text" name="prodName" id = "prodName" /></div>
            <div class="col-md-3 text-center" ><input type="text" name="vendorName" id = "vendorName" /></div>
            <div class="col-md-2 text-center"><input type="text" name="productId" id = "productId"/></div>
            <div class="col-md-2 text-center"><input type="submit" value="submit" id="getProductDetails" class="btn btn-success"/></div>
            <div class="col-md-2 text-center"><a href="http://localhost/store/orderPage" class="btn btn-success">Search Order</a></div>
        </div>
        <h5 id="valid"></h5>
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
        <div class="col-md-1 text-center"><strong>Qty</strong></div>
        <div class="col-md-1 text-center"><strong>Status</strong></div>
        <div class="col-md-2 text-center"><strong>Requested Date</strong></div>
        <div class="col-md-2 text-center"><strong>Approval / Cancellation Date</strong></div>
        <div class="col-md-2 text-center"><strong>Approve</strong></div>
        <div class="col-md-2 text-center"><strong>Cancel</strong></div>
    </div>
<div class="" style>
     <c:forEach items="${request}" var="req">
        <div class="row" style="border:1px solid green;padding:10px">
           <div class="col-md-2 text-center">${req.product.productName}</div>
           <div class="col-md-1 text-center">${req.productQuantity}</div>
           <div class="col-md-1 text-center">${req.status}</div>
           <div class="col-md-2 text-center"><fmt:formatDate value="${req.requestDate}" pattern="dd/MM/yyyy"/></div>
           <div class="col-md-2 text-center"><fmt:formatDate value="${req.approvalDate}" pattern="dd/MM/yyyy"/></div>
           <div class="col-md-2 text-center"><a href="http://localhost/store/approvefacultyrequest?requestId=${req.requestId}" class="btn btn-success">Approve</a></div>
           <div class="col-md-2 text-center"><a href="http://localhost/store/rejectfacultyrequest?requestId=${req.requestId}" class="btn btn-success">Cancel</a></div>
        </div>
     </c:forEach>
  </div>
</div>
</div>
</c:if>

<form  action="/store/getPurchaseBtweenDates" autocomplete="on" id = "searchProductForm">
<div class="container" style="margin:50px">
        <div class="row text-center"><h3> Search Purchase</h3></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-4 text-center"><strong>From Date</strong></div>
            <div class="col-md-4 text-center"><strong>To Date</strong></div>
            <div class="col-md-4 text-center"><strong></strong></div>
        </div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-4 text-center"><input type="text" name="fromDate" id = "fromDate" /></div>
            <div class="col-md-4 text-center" ><input type="text" name="toDate" id = "toDate" /></div>
            <div class="col-md-4 text-center"><button value="submit" type="submit" class="btn btn-success"> Search Purchase</button></div>
        </div>
	</div>
</div>
</form>
<div class="container" style="margin:50px">
    <div class="row text-center"><h3> Assign - Revoke SM Role </h3></div>
    <div class="row" style="border:1px solid green;padding:10px">
        <div class="col-md-4 text-center">
        <a href="http://localhost/store/smincharge">
            <input type="submit" name="smincharge" id = "smincharge" value="SM Incharge" class="btn btn-success" />
        </a></div>
    </div>

 <link rel="stylesheet" href="http://localhost/js/store/jquery-ui/jquery-ui.css">
 <script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
 <script src="http://localhost/js/store/jquery-ui/jquery-ui.js"></script>
<script src="http://localhost/js/store/storemanager.js"></script>
</body>
</html>
