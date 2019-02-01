<jsp:include page="header1.jsp"/>
<body>

 <div style="margin-left:40%;margin-bottom:2%">
    <h2>Add Product</h2>
 </div>

<div class="container">
    <form method="POST" class="form-inline" id = "addProductForm">

        <div class="form-group">
            <label for="product_name" style="margin-right:70px">PRODUCT NAME: </label>
            <input type="text" class="form-control" id="p_name" placeholder="Enter product name" name="ProductName">
        </div>
        </br></br>
        <div class="form-group">
            <label for="product_quantity" style="margin-right:65px">Product Quantity:</label>
            <input type="number" class="form-control" id="p_quantity" value="0">
        </div>
        </br></br>
        <div class="form-group">
            <label for="product_description" style="margin-right: 50px">Product Description</label>
           <textarea class="form-control" id="p_description" placeholder="Write the Description here"
                              name="Description"></textarea>
        </div>
        </br></br>
        <div class="form-group">
            <label for="vendor_name" style="margin-right:95px">Vendor name</label>
            <input type="text" id="v_name" name="vendor_name" class="form-control" placeholder="Vendor's Name">
        </div>
        </br></br>

        <div class="form-group">
            <label for="Product_type" style="margin-right: 90px">Product_type:</label>
            <select name="product_type" id="p_type" class="form-control">
                <option value="Consumable" name="Consumable" id="consumable" value = "consumable" style="display:block">Consumable</option>
                <option value="Non-Consumable" name="nonconsumable" id="nonconsumable" value = "nonconsumable" style="display:block">Non-Consumable</option>
            </select>
        </div>
        </br>
        </br>

        <div class="col-lg-4 text-center">
            <button id="addProduct" name="addProduct" class="btn btn-success" style="margin-left:500px" type= "submit">Submit
            </button>
        </div>

    </form>

<script src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="http://localhost/js/store/addproduct.js"></script>
</body>
</html>