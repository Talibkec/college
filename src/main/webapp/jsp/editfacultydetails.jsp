<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
@media (max-width:767px){
.add_button{
width:100%
}
.remove_button:{
width:8%
}   
}

</style>
<div class="container">
    <div class="row">
        <div class=" col-sm-8"></br></br>
            <div class="panel panel-primary"style="width:140%">
                <div class="panel-heading"> <i class="fa fa-sign-in"></i> &nbsp;EditProfile Details</div>

                <div class="panel-body" style="height: 100%">


                    <form method="POST" enctype="multipart/form-data" id="editDetailsForm">
                    <div>
                          <input type="hidden" placeholder="Faculty Id"id="facultyId" value="${facultyDetails.facultyId}" name="facultyId"
                                 class=" col-md-6 "style="margin-left:3%"></br></br>
                    </div>

                        <div>
                        <input type="text" placeholder="Enter Name"id="facultyName" value="${facultyDetails.facultyName}" name="facultyName"
                            class=" col-md-6 "style="margin-left:3%"></br></br>
                         </div>

                        <input type="email"placeholder="Enter official Email" id="facultyOfficialEmail" value="${facultyDetails.facultyOfficialEmail}"
                            name="facultyOfficialEmail" class=" col-md-6"style="margin-left:3%"></br></br>

                        <input type="email"placeholder="Enter Personal Email" id="facultyPersonalEmail" value="${facultyDetails.facultyPersonalEmail}"
                            name="facultyPersonalEmail" class=" col-md-6"style="margin-left:3%"></br></br>

                        <input type="text"placeholder="Enter Mobile No." id="facultyMobNo" value="${facultyDetails.facultyMobNo} " name="facultyMobNo"
                            class=" col-md-6"style="margin-left:3%"></br>

                        <div class="oldPropsClass" ">
                            <c:forEach items="${facultyDetails.facultyKeyProps}" var="keyProp">
                            <div class="oldpropkeybox" >
                            <div class="panel panel-success"style="margin-top:5%">
                            <input type="text" name="keyProp" placeholder="Enter Property Heading"style="margin-top:2%;margin-left:3%" class="propKey col-md-6" value="${keyProp.keyPropertyName}" ></input>
                            <input type="hidden" name="keyPropertyId" placeholder="Enter Property Heading"style="margin-top:2%;margin-left:3%" class="propKey col-md-6" value="${keyProp.keyPropertyId}" ></input>
                            <input type="hidden" name="keyPropsOrder" placeholder="Enter Property Heading"style="margin-top:2%;margin-left:3%" class="propKey col-md-6" value="${keyProp.keyPropsOrder}" ></input>

                            <a href="javascript:void(0);" class="remove_Key btn btn-danger" style ="margin-top:2%;margin-left:5%;margin-bottom:2%">Delete Property Heading</a>

                            <c:forEach items="${keyProp.keyPropVals}" var="propVal">
                               <div class="propVals">
                                <input type="textarea "name="field_name" placeholder="Enter Property Value"style="margin-left:3%;"value="${propVal.keyPropVal}"class="propVal col-md-6" />
                                <input type="hidden" name="keyPropValuesId" placeholder="Enter Property Value"style="margin-left:3%;"value="${propVal.keyPropValuesId}"class="propVal col-md-6" />
                                <input type="hidden" name="keyValueOrder" placeholder="Enter Property Value"style="margin-left:3%;"value="${propVal.keyValueOrder}"class="propVal col-md-6" />
                                <a href="javascript:void(0);" class="add_button btn btn-success "id="add_button"style ="width:8%;margin-left:5%;margin-bottom:2%;"><span class="glyphicon glyphicon-plus-sign"></span></a>
                                <a href="javascript:void(0);" class="remove_button btn btn-danger "style="width:8%;margin-bottom:2%;margin-left:2%"><span class="glyphicon glyphicon-remove-sign"></span></a>
                               </div>
                            </c:forEach>
                            </div>
                            </div>
                            </c:forEach>
                        </div>
                        <div style="text-align:center"id="addNewProp"style="display:block">
                        <button class="btn btn-success"><span class="glyphicon glyphicon-plus-sign">Add New Property</span></button>
                        </div>
                        <div class="newPropsClass">
                            <div class="newpropkeybox" style="display:none">
                            <div class ="panel panel-success">
                                <input type="text" name="propkey"style="margin-left:3%;margin-top:2%"placeholder="Property Heading" class="propKey  col-md-6" />
                                <a href="javascript:void(0);" class="remove_Key btn btn-danger"id="deleteNewProps" style="width:8%;margin-left:5%;margin-top:2%;margin-right:1%">Delete </a>
                                <a href="javascript:void(0);"style="margin-top:2%;width:9%" class="add_key btn btn-success add_keYAlign" >Add More </a>

                                <div class="propVals">
                                    <input type="text"style="margin-left:3%;margin-top:2%" placeholder="Property Value"name="field_name" value="" class="propVal  col-md-6" />
                                    <a href="javascript:void(0);" class="add_button btn btn-success"style="width:8%;margin-left:5%;margin-top:2%"><span class="glyphicon glyphicon-plus-sign"></span></a>
                                    <a href="javascript:void(0);" class="remove_button btn btn-danger " style="width:9.5%;margin-top:2%;margin-left:0.5%"><span class="glyphicon glyphicon-remove-sign"></span></a>
                                </div>
                            </div>
                            </div>
                        </div>
                        <input type="submit" value="Submit" id="btnSubmit" class="btn btn-info"style="margin-top:5%;margin-left:10%" />
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
