<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>

 <div style="margin-left:40%;margin-bottom:2%">
    <h2>Add Purchase</h2>
 </div>

<div class="container">
            <div class="row text-center"><strong> Product Details</strong></div>
                    <div class="row" style="border:1px solid green;padding:10px">
                        <div class="col-md-4 text-center"><strong>Product Name</strong></div>
                        <div class="col-md-4 text-center"><strong>Vendor Name</strong></div>
                        <div class="col-md-4 text-center"><strong>Product Quantity</strong></div>

                    </div>
            <div class="row" style="border:1px solid green;padding:10px;margin-bottom:50px;">
                 <div class="col-md-4 text-center" ><strong>${productName}</strong></div>
                 <div class="col-md-4 text-center" ><strong>${vendorName}</strong></div>
                 <div class="col-md-4 text-center" ><strong>${productQuantity}</strong></div>

            </div>
    <form method="POST" class="form-inline" id = "addPurchaseForm">
        <div class="form-group">
            <label for="product_quantity" style="margin-right:65px">Product Quantity:</label>
            <input type="number" class="form-control" id="p_quantity" value="0">
        </div>
        </br></br>
        <div class="form-group">
            <label for="invoice" style="margin-right: 110px">Invoice/Bill</label>
           <textarea class="form-control" id="invoice" placeholder="Invoice/Bill"
                              name="Invoice"></textarea>
        </div>
        </br></br>
        <div class="form-group">
                    <label for="purchasedBy" style="margin-right:90px">Purchased By</label>
                    <input type="text" id="purchasedBy" name="purchasedBy" class="form-control" placeholder="Enter Name">
                </div>
                </br></br>
        <div class="form-group">
                            <label for="Price" style="margin-right:150px">Price</label>
                            <input type="text" id="price" name="price" class="form-control" placeholder="Price">
        </div>
                        </br></br>
        <div class="form-group">
                                    <label for="purchaseDate" style="margin-right:150px">Purchase Date</label>
                                    <input type="text" id="purchaseDate" name="purchaseDate" class="form-control" placeholder="Purchase Date">
                </div>
                                </br></br>
        <input type="hidden" name = "productId" id="productId" value="${productId}">


        <div class="col-lg-4 text-center">
            <button id="addPurchase" name="addPurchase" class="btn btn-success" style="margin-left:500px" id="btnSubmit" type= "submit">Submit
            </button>
        </div>
    </form>
    <link rel="stylesheet" href="http://localhost/js/store/jquery-ui/jquery-ui.css">
        <script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
        <script src="http://localhost/js/store/jquery-ui/jquery-ui.js"></script>
    <script src="http://localhost/js/store/addpurchase.js"></script>
</body>
<html>