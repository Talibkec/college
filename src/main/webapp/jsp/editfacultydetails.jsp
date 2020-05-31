<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
    <div style="margin-left:50px">
        <h3>Edit Profile Details</h3>
    </div>
</div>

<div class="container"> <br /><br />
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
            <div class="panel panel-primary">
                <div class="panel-heading"> <i class="fa fa-sign-in"></i>EditProfile Details</div>

                <div class="panel-body" style="height: 500px; ">


                    <form method="POST" enctype="multipart/form-data" id="editDetailsForm">


                        <input type="hidden" id="facultyId" value="${facultyDetails.facultyId}" name="facultyId" />
                        <label for="facultyName" class="control-label col-sm-4">Enter Name</label>
                        <input type="text" id="facultyName" value="${facultyDetails.facultyName}" name="facultyName"
                            class="control-label col-sm-4 "></br></br>
                        <label for="facultyOfficialEmail" class="control-label col-sm-4">Enter Official Email</label>
                        <input type="email" id="facultyOfficialEmail" value="${facultyDetails.facultyOfficialEmail}"
                            name="facultyOfficialEmail" class="control-label col-sm-4"></br></br>
                        <label for="facultyPersonalEmail" class="control-label col-sm-4">Enter Personal Email</label>
                        <input type="email" id="facultyPersonalEmail" value="${facultyDetails.facultyPersonalEmail}"
                            name="facultyPersonalEmail" class="control-label col-sm-4"></br></br>
                        <label for="facultyMobNo" class="control-label col-sm-4">Mobile No. </label>
                        <input type="text" id="facultyMobNo" value="${facultyDetails.facultyMobNo} " name="facultyMobNo"
                            class="control-label col-sm-4"></br></br>

                        <br><br>
                        <div class="oldPropsClass">
                            <c:forEach items="${facultyDetails.facultyKeyProps}" var="keyProp">
                                <div class="oldpropkeybox">
                                    <input type="text" name="keyProp" class="propKey"
                                        value="${keyProp.keyPropertyName}" />
                                    <a href="javascript:void(0);" class="remove_Key">Delete Property Heading</a>
                                    <c:forEach items="${keyProp.keyPropVals}" var="propVal">
                                        <div class="propVals">
                                            <input type="text" name="field_name[]" value="${propVal.keyPropVal}"
                                                class="propVal" />
                                            <a href="javascript:void(0);" class="add_button">add</a>
                                            <a href="javascript:void(0);" class="remove_button">Remove</a>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="newPropsClass">

                            <div class="newpropkeybox">
                                <input type="text" name="propkey" class="propKey" />
                                <a href="javascript:void(0);" class="remove_Key">Delete Property Heading</a>
                                <a href="javascript:void(0);" class="add_key">Add Property Heading</a>
                                <div class="propVals">
                                    <input type="text" name="field_name[]" value="" class="propVal" />
                                    <a href="javascript:void(0);" class="add_button">add</a>
                                    <a href="javascript:void(0);" class="remove_button">Remove</a>
                                </div>
                            </div>
                        </div>

                        <input type="submit" value="Submit" id="btnSubmit" class="btn btn-primary" />
                        <div class="col-sm-4"></div>
                    </form>
                </div>
                
            </div>

        </div>
    </div>
</div>
</div>
<jsp:include page="footer.jsp" />
<script src="http://localhost/js/editfacultydetails.js"></script>