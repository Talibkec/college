<jsp:include page="header1.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
<form  action="/fstore/faculty/submitFacultySearchOrder" autocomplete="off" id = "searchOrderForm" method = "POST">
<div class="container" style="margin:50px">
        <div class="row text-center"><h3> Search Product</h3></div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-4 text-center"><strong>From date</strong></div>
            <div class="col-md-4 text-center"><strong>To Date</strong></div>
            <div class="col-md-4 text-center"></div>
        </div>
        <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-4 text-center"><input type="text" name="fromDate" id = "fromDate"/></div>
            <div class="col-md-4 text-center"><input type="text" name="toDate" id = "toDate"/></div>
            <div class="col-md-4 text-center"><input type="submit" value="submit" id="searchOrder"/></div>
        </div>
	</div>
</form>

<link rel="stylesheet" href="http://keck.ac.in/js/store/jquery-ui/jquery-ui.css">
<script src="http://keck.ac.in/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="http://keck.ac.in/js/store/jquery-ui/jquery-ui.js"></script>
<script src="http://keck.ac.in/js/store/searchorder.js"></script>
</body>
</html>