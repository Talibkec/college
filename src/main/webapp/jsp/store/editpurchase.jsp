<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>

 <div style="margin-left:40%;margin-bottom:2%">
    <h2>Edit Purchase</h2>
 </div>

<div class="container">

    <form method="POST" class="form-inline" id = "editPurchaseForm" autocomplete="off">
        <div class="form-group">
            <label for="product_quantity" style="margin-right:65px">Product Quantity:</label>
            <input type="number" class="form-control" id="p_quantity" value="${purchase.quantity}">
        </div>
        </br></br>
        <div class="form-group">
            <label for="invoice" style="margin-right: 110px">Invoice/Bill</label>
           <input class="form-control" id="invoice" name="Invoice" value="${purchase.invoice}"></input>
        </div>
        </br></br>
        <div class="form-group">
                    <label for="purchasedBy" style="margin-right:90px">Purchased By</label>
                    <input type="text" id="purchasedBy" name="purchasedBy" class="form-control" value="${purchase.purchaseBy}">
                </div>
                </br></br>
        <div class="form-group">
                            <label for="Price" style="margin-right:150px">Price</label>
                            <input type="text" id="price" name="price" class="form-control" value="${purchase.price}">
        </div>
                        </br></br>
        <div class="form-group">
                                    <label for="purchaseDate" style="margin-right:150px">Purchase Date</label>

                                    <input type="text" id="purchaseDate" name="purchaseDate" class="form-control" value='<fmt:formatDate value="${purchase.purchaseDate}" pattern="dd/MM/yyyy"/>'>
                </div>
                                </br></br>
        <input type="hidden" name = "productId" id="productId" value="${purchase.product.productId}">
        <input type="hidden" name = "purchaseId" id="purchaseId" value="${purchase.purchaseId}">


        <div class="col-lg-4 text-center">
            <button id="editPurchase" name="editPurchase" class="btn btn-success" style="margin-left:500px" id="btnSubmit" type= "submit">Submit</button>
        </div>
    </form>
    <link rel="stylesheet" href="http://localhost/js/store/jquery-ui/jquery-ui.css">
        <script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
        <script src="http://localhost/js/store/jquery-ui/jquery-ui.js"></script>
    <script src="http://localhost/js/store/editpurchase.js"></script>
</body>
<html>
