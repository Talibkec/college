<%@ page import="com.college.service.UserService" %>
<%@ page import="com.college.core.model.FacultyDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.http.converter.json.GsonBuilderUtils" %>
<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="jumbotron" style="
"padding-top: 24px; padding-bottom: 24px;
"></div>

<div class="container">

    <div class="row" style="display:block">
        <div class="col-md-8">
            <div class="page-content"> <h2>Invalid Department Id </h2></div>
            <hr/>


        </div>
    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>
