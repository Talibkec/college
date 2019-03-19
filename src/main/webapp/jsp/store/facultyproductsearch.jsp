<jsp:include page="header1.jsp"/>
<body>

<form  action="/fstore/faculty/getProductDetails" autocomplete="on" id = "searchProductForm">
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
            <h5 id="valid"></h5>
        </div>
	</div>
</form>

<script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
 <script src="http://localhost/js/jquery-ui.js"></script>
<script src="http://localhost/js/store/facultyproductsearch.js"></script>
</body>
</html>