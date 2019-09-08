<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="http://localhost/js/store/jquery-ui/jquery-ui.css">
    <script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
    <script src="http://localhost/js/store/jquery-ui/jquery-ui.js"></script>
    <script src="http://localhost/js/store/searchpurchase.js"></script>
<body>

 <div style="margin-left:40%;margin-bottom:2%">
    <h2>Add Purchase</h2>
 </div>

<div class="container">
    <form action="/store/purchasedetail" id = "requesPurchaseDetailForm" autocomplete="off">
            <div class="row text-center"><strong> Product Details</strong></div>
                    <div class="row" style="border:1px solid green;padding:10px">
                        <div class="col-md-2 text-center"><strong>Product Name</strong></div>
                        <div class="col-md-2 text-center"><strong>Vendor Name</strong></div>
                        <div class="col-md-2 text-center"><strong>Product Quantity</strong></div>
                        <div class="col-md-2 text-center"><strong>From Date</strong></div>
                        <div class="col-md-2 text-center"><strong>To Date</strong></div>
                        <div class="col-md-2 text-center"><strong>Get Request</strong></div>

                    </div>
            <div class="row" style="border:1px solid green;padding:10px;margin-bottom:50px;">
                 <div class="col-md-2 text-center" ><strong>${productName}</strong></div>
                 <div class="col-md-2 text-center" ><strong>${vendorName}</strong></div>
                 <div class="col-md-2 text-center" ><strong>${productQuantity}</strong></div>
                 <div class="col-md-2 text-center" ><input type="text" id="fromDatepicker" name = "fromDatepicker" /></div>
                 <div class="col-md-2 text-center" ><input type="text" id="toDatepicker" name = "toDatepicker"/></div>
                 <div class="col-md-2 text-center" ><button type="submit" value="submit">Get Request</button></div>
                 <input type="hidden" name="productId" id="productId" value="${productId}" />
            </div>
         </form>

</body>
<html>