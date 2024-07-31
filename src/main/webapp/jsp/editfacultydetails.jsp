<jsp:include page="header.jsp" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style>
@media (max-width:767px) {
  .add_button {
    width: 100%;
  }
  .remove_button {
    width: 8%;
  }
}
</style>

<div class="panel-heading bg-gray-200 p-5 text-3xl flex items-center space-x-2">
  <i class="fa fa-sign-in"></i>
  <span>Edit Profile Details</span>
</div>

<div class="container mx-auto my-6 rounded-md">
  <div class="row">
    <div class="col-sm-8 my-6">

         <div class="border border-gray-300 p-6 bg-white shadow-lg rounded-lg min-w-[600px]">
                <form method="POST" enctype="multipart/form-data" id="editDetailsForm">
                  <input type="hidden" id="facultyId" value="${facultyDetails.facultyId}" name="facultyId" class="w-full mb-4">

                  <div class="flex flex-col mb-4">
                    <label for="facultyName" class="mb-2 font-semibold">Name</label>
                    <input type="text" id="facultyName" value="${facultyDetails.facultyName}" name="facultyName" class="w-full p-2 border border-gray-300 rounded">
                  </div>

                  <div class="flex flex-col mb-4">
                    <label for="facultyOfficialEmail" class="mb-2 font-semibold">Official Email</label>
                    <input type="email" id="facultyOfficialEmail" value="${facultyDetails.facultyOfficialEmail}" name="facultyOfficialEmail" class="w-full p-2 border border-gray-300 rounded">
                  </div>

                  <div class="flex flex-col mb-4">
                    <label for="facultyPersonalEmail" class="mb-2 font-semibold">Personal Email</label>
                    <input type="email" id="facultyPersonalEmail" value="${facultyDetails.facultyPersonalEmail}" name="facultyPersonalEmail" class="w-full p-2 border border-gray-300 rounded">
                  </div>

                  <div class="flex flex-col mb-4">
                    <label for="facultyMobNo" class="mb-2 font-semibold">Mobile No</label>
                    <input type="text" id="facultyMobNo" value="${facultyDetails.facultyMobNo}" name="facultyMobNo" class="w-full p-2 border border-gray-300 rounded">
                  </div>

            <div class="oldPropsClass">
              <c:forEach items="${facultyDetails.facultyKeyProps}" var="keyProp">
                <div class="oldpropkeybox">
                  <div class="panel panel-success my-6">
                    <input type="text" name="keyProp" placeholder="Enter Property Heading" class="propKey col-md-6 my-2 ml-3" value="${keyProp.keyPropertyName}">
                    <input type="hidden" name="keyPropertyId" class="propKey col-md-6 my-2 ml-3" value="${keyProp.keyPropertyId}">
                    <input type="hidden" name="keyPropsOrder" class="propKey col-md-6 my-2 ml-3" value="${keyProp.keyPropsOrder}">
                    <a href="javascript:void(0);" class="remove_Key btn btn-danger my-2 ml-5 mb-2">Delete Property Heading</a>

                    <c:forEach items="${keyProp.keyPropVals}" var="propVal">
                      <div class="propVals">
                        <c:set var="keyVals" value="${propVal.keyPropVal}"/>
                        <input type="text" name="field_name" placeholder="Enter Property Value" class="propVal col-md-6 my-2 ml-3" value="${fn:escapeXml(keyVals)}">
                        <input type="hidden" name="keyPropValuesId" class="propVal col-md-6 my-2 ml-3" value="${propVal.keyPropValuesId}">
                        <input type="hidden" name="keyValueOrder" class="propVal col-md-6 my-2 ml-3" value="${propVal.keyValueOrder}">
                          <a href="javascript:void(0);" class="add_button btn btn-success ml-5 my-2 mb-2">
                                                 <button type="button" class="w-20 border-2 rounded"><span class="glyphicon glyphicon-plus-sign"></span>Add</button>
                                               </a>
                                               <a href="javascript:void(0);" class="remove_button btn btn-danger my-2 ml-2 mb-2">
                                                 <button type="button" class="w-20"><span class="glyphicon glyphicon-remove-sign"></span>Delete</button>
                                               </a>
                      </div>
                    </c:forEach>
                  </div>
                </div>
              </c:forEach>
            </div>

            <div class="text-center my-3" id="addNewProp">
              <button class="w-full btn btn-success focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800">
                <span class="material-symbols-outlined"></span> Add New Property
              </button>
            </div>

            <div class="newPropsClass">
              <div class="newpropkeybox" style="display:none">
                <div class="panel panel-success">
                  <input type="text" name="propkey" class="propKey col-md-6 my-2 ml-3" placeholder="Property Heading">
                  <div class="flex items-center space-x-2 mb-2">
                     <a href="javascript:void(0);" class="remove_Key btn btn-danger ml-5 my-2 mr-1">
                                       <button type="button" class="w-20">Delete</button>
                                     </a>
                                     <a href="javascript:void(0);" class="add_key btn btn-success">
                                       <button type="button" class="w-20 border-2">Add More</button>
                                     </a>
                  </div>


                  <div class="propVals mb-4">
                    <input type="text" name="field_name" class="propVal col-md-6 my-2 ml-3" placeholder="Property Value">
                     <a href="javascript:void(0);" class="add_button btn btn-success ml-5 my-2">
                                         <button type="button" class="w-20 border-2 h-full">Add</button>
                                       </a>
                                       <a href="javascript:void(0);" class="remove_button btn btn-danger my-2 ml-0.5">
                                         <button type="button" class="w-24"></span>Delete</button>
                     </a>
                  </div>
                </div>
              </div>
            </div>

            <button type="submit" value="Submit" id="btnSubmit" class="w-full btn btn-info focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800 mt-2">submit</button>
          </form>
        </div>

    </div>
  </div>
</div>

<jsp:include page="footer.jsp" />
<script src="/js/editfacultydetails.js"></script>
