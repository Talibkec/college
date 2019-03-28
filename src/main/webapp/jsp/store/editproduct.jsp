<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>

 <div style="margin-left:40%;margin-bottom:2%">
    <h2>Edit Product</h2>
 </div>

<div class="container">

    <form method="POST" class="form-inline" id = "editProductForm" autocomplete="off">
        <div class="form-group">
            <label for="productName" style="margin-right:65px">Product Name:</label>
            <input type="text" class="form-control" id="p_name" value="${product.productName}">
        </div>
        </br></br>
        <div class="form-group">
            <label for="product_quantity" style="margin-right: 110px">Product Quantity</label>
           <input class="form-control" id="p_quantity" name="product_quantity" value="${product.productQuantity}"></input>
        </div>
        </br></br>
        <div class="form-group">
                    <label for="product_description" style="margin-right:90px">Product Description</label>
                    <textarea type="text" id="p_description" name="product_description" class="form-control" value="${product.productDescription}"></textarea>
                </div>
                </br></br>
        <div class="form-group">
                            <label for="vendor_name" style="margin-right:150px">Vendor Name</label>
                            <input type="text" id="v_name" name="vendor_name" class="form-control" value="${product.vendorName}">
        </div>
                        </br></br>
        <div class="form-group">
            <label for="Product_type" style="margin-right:150px">Product Type</label>

            <select name="product_type" id="p_type" class="form-control">
                            <option value="Consumable" name="Consumable" id="consumable" value = "${product.productType}" style="display:block">Consumable</option>
                            <option value="Non-Consumable" name="nonconsumable" id="nonconsumable" value = "${product.productType}" style="display:block">Non-Consumable</option>
                        </select>

                </div>
                                </br></br>
         <input type="hidden" name = "productId" id="productId" value="${product.productId}">

        <div class="col-lg-4 text-center">

            <button id="editProduct" name="editProduct" class="btn btn-success" style="margin-left:500px" id="btnSubmit" type= "submit">Submit</button>
        </div>
    </form>
    <link rel="stylesheet" href="http://keck.ac.in/js/store/jquery-ui/jquery-ui.css">
        <script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
        <script src="http://keck.ac.in/js/store/jquery-ui/jquery-ui.js"></script>
    <script src="http://keck.ac.in/js/store/editproduct.js"></script>
</body>
<html>