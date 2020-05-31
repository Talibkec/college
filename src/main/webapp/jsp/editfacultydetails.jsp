<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.keyprops:{
    margin-bottom:5%
}
</style>
<div class="container"> <br /><br />
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
            <div class="panel panel-primary">
                <div class="panel-heading"> <i class="fa fa-sign-in"></i> &nbsp;EditProfile Details</div>

                <div class="panel-body" style="height: 100%">


                    <form method="POST" enctype="multipart/form-data" id="editDetailsForm">


                       </br><input type="hidden" id="facultyId" value="${facultyDetails.facultyId}" name="facultyId" />
                        <div>
                        <label for="facultyName" class="control-label col-sm-4">Enter Name</label>
                        <input type="text" id="facultyName" value="${facultyDetails.facultyName}" name="facultyName"
                            class="control-label col-sm-4 "></br></br>
                         </div>
                        <label for="facultyOfficialEmail" class="control-label col-sm-4">Enter Official Email</label>
                        <input type="email" id="facultyOfficialEmail" value="${facultyDetails.facultyOfficialEmail}"
                            name="facultyOfficialEmail" class="control-label col-sm-4"></br></br>
                        <label for="facultyPersonalEmail" class="control-label col-sm-4">Enter Personal Email</label>
                        <input type="email" id="facultyPersonalEmail" value="${facultyDetails.facultyPersonalEmail}"
                            name="facultyPersonalEmail" class="control-label col-sm-4"></br></br>
                        <label for="facultyMobNo" class="control-label col-sm-4">Mobile No. </label>
                        <input type="text" id="facultyMobNo" value="${facultyDetails.facultyMobNo} " name="facultyMobNo"
                            class="control-label col-sm-4"></br>

                        <div class="oldPropsClass" ">
                            <c:forEach items="${facultyDetails.facultyKeyProps}" var="keyProp">
                            <div class="oldpropkeybox" >
                            <div class="panel panel-success"style="margin-top:5%">
                            <input type="text" name="keyProp"style="margin-top:2%;margin-left:3%" class="propKey col-md-6" value="${keyProp.keyPropertyName}" ></input>
                            <a href="javascript:void(0);" class="remove_Key btn btn-warning" style ="margin-top:2%;margin-left:5%;margin-bottom:2%">Delete Property Heading</a>
                            <c:forEach items="${keyProp.keyPropVals}" var="propVal">
                               <div class="propVals">
                                <input type="textarea"name="field_name[]" style="margin-left:3%;"value="${propVal.keyPropVal}"class="propVal col-md-6" />
                                <a href="javascript:void(0);" class="add_button btn btn-info"style ="margin-left:5%;margin-bottom:2%"><span class="glyphicon glyphicon-plus-sign"></span>Add</a>
                                <a href="javascript:void(0);" class="remove_button btn btn-danger"style="margin-bottom:2%"><span class="glyphicon glyphicon-remove-sign"></span>Remove</a>
                               </div>
                            </c:forEach>
                            </div>
                            </div>
                            </c:forEach>
                        </div>
                        <div class="newPropsClass">

                            <div class="newpropkeybox">
                            <div class ="panel panel-success">
                                <input type="text" name="propkey"style="margin-left:3%;margin-top:2%"placeholder="Property Heading" class="propKey control-label col-md-6" />
                                <a href="javascript:void(0);" class="remove_Key btn btn-warning" style="margin-left:5%;margin-top:2%">Delete </a>
                                <a href="javascript:void(0);"style="margin-top:2%" class="add_key btn btn-primary" >Add Property Heading </a>
                                <div class="propVals">
                                    <input type="text"style="margin-left:3%;margin-top:2%" placeholder="Property Value"name="field_name[]" value="" class="propVal control-label col-md-6" />
                                    <a href="javascript:void(0);" class="add_button btn btn-info"style="margin-left:5%;margin-top:2%"><span class="glyphicon glyphicon-plus-sign"></span>Add</a>
                                    <a href="javascript:void(0);" class="remove_button btn btn-danger " style="margin-top:2%"><span class="glyphicon glyphicon-remove-sign"></span>Remove</a>
                                </div>
                            </div>
                            </div>
                        </div>
                        <input type="submit" value="Submit" id="btnSubmit" class="btn btn-success"style="margin-top:5%;margin-left:10%" />
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