<jsp:include page="header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
<div style="margin-left:50px" ><h3>Upload File</h3></div>
</div></div><div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-4 col-sm-4">
<div class="panel panel-default"><div class="panel-heading"> <i class="fa fa-sign-in"></i>Upload Notice/News</div>
<div class="panel-body">

<form method="POST" enctype="multipart/form-data" id="fileUploadForm" action>
    <label for="headLine" class="control-label col-sm-4" >Head Line</label>${Role}${UserName}
    <div class="col-sm-8">
        <input type="text" name="noticeHeader" id="noticeHeader"/><br/><br/>
    </div>

    <label for="noticeType" class="control-label col-sm-4" >Notice Type</label>
<c:forEach var="item" items="${Role}">
    <c:if test = "${'HOD' eq item and UserName eq 'hodcse'}">
    <div class="col-sm-8">
            <select name = "noticeType" id="noticeType"><option value="CSENotice">CSE</option>
                                                         <option value="News">News</option>
            </select><br/><br/>
<c:forEach var="item" items="${Role}">
    <c:if test = "${'HOD' eq item and UserName eq 'hodcivil'}">
    <div class="col-sm-8">
            <select name = "noticeType" id="noticeType"><option value="CivilNotice">Civil</option>
                                                         <option value="News">News</option>
            </select><br/><br/>
<c:forEach var="item" items="${Role}">
    <c:if test = "${'HOD' eq item and UserName eq 'hodmech'}">
    <div class="col-sm-8">
            <select name = "noticeType" id="noticeType"><option value="MechNotice">Mechanical Notice</option>
                                                         <option value="News">News</option>
            </select><br/><br/>
    </div>
    </c:if>
    </c:forEach>
<c:forEach var="item" items="${Role}">
    <c:if test = "${'HOD' eq item and UserName eq 'hodeee'}">
    <div class="col-sm-8">
            <select name = "noticeType" id="noticeType"><option value="ELECNotice">EEE Notice</option>
                                                         <option value="News">News</option>
            </select><br/><br/>
    <label class="control-label col-sm-12" ></label>
    <label for="noticeUploadDate" class="control-label col-sm-4" >Date</label>
    <div class="col-sm-8">
            <input type="text" name="date" id="noticeUploadDate"/>
    </div>

    <label for="headLine" class="control-label col-sm-4" >Scrollable</label>
        <div class="col-sm-8">
            <input type="checkbox" name="isScrollable" id="isScrollable" style="width: 25px;height: 40px;" ></input><br><br>
        </div>
    <label for="file" class="control-label col-sm-4" >Choose File</label>
    <div class=" btn-default  col-md-8" style="color:red"> <input type="file" name="noticeFile" id="noticeFile"/><br/></div></div>
    <div id="msg" style="display: none; color: red;">*Please Select a file</div>
    <input type="submit" value="Submit" id="btnSubmit" class="btn btn-primary" /><div class="col-sm-5"></div></div>
</form>

<form method="GET" action="/" id="homePageForm">
    <input type="hidden" name="date" id="date"/><br/><br/>
    <input type="hidden" name="hnoticeHeader" id="hnoticeHeader"/><br/><br/>
    <input type="hidden" name="hfileLocation" id="hfileLocation"/><br/><br/>
    <input type="hidden" name="hnoticeType" id="hnoticeType"/><br/><br/>
</form></div>
</div>
</div></div></div>
<jsp:include page ="footer.jsp"/>
<link rel="stylesheet" href="http://localhost/js/store/jquery-ui/jquery-ui.css">
<script src="http://localhost/js/store/jquery-ui/jquery-ui.js"></script>
<script src="http://localhost/js/HODFileUpload.js"></script>