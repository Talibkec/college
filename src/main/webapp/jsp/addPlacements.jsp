<jsp:include page="admin/HeaderDashboard.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<div class="container"><br/><br/>
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
            <div class="panel panel-primary">
                <div class="panel-heading"><i class="fa fa-sign-in"></i>&nbsp;Add Placements</div>
                <div class="panel-body" style="height:100%; ">
                    <form method="POST" enctype="multipart/form-data" id="addFacultyForm"
                          data-action="/auth/uploadfile/addPlacements">
                        <label for="facultyName" class="control-label col-sm-4">Enter Student Name</label>
                        <input type="text" id="facultyName" name="studentName"
                               class="control-label col-sm-4 ui-autocomplete-input" autocomplete="off"></br></br>

                        <label for ="department" class="control-label col-sm-4">Choose Department</label>
                        <select class="control-label col-sm-4" id="deptId"  name='deptId'>
                            <option value ="1">Computer Sc. & Engineering</option>
                            <option value="2" >Civil Engineering</option>
                            <option value ="3">Mechanical Engineering</option>
                            <option value="4" >Electrical & Electronics Engg.</option>
                            <option value="5" >Applied Sc. & Humanities</option>
                            <option value="6" >Electronics Engineering (VLSI Design and Technology)</option>
                        </select><br></br>
                        <label for="facultyOfficialEmail" class="control-label col-sm-4">Enter Company Naame</label>
                        <input type="text" id="facultyOfficialEmail" name="companyName"
                               class="control-label col-sm-4"></br></br>
                        <label for="facultyPersonalEmail" class="control-label col-sm-4">Enter Pass Out year</label>
                        <input type="number" id="facultyPersonalEmail" name="studentSession"
                               class="control-label col-sm-4"></br></br>
                        <label for="facultyMobNo" class="control-label col-sm-4">Registration No. </label>
                        <input type="number" id="facultyMobNo" name="registrationNo"
                               class="control-label col-sm-4"></br></br>



                        <div id="form_message"></div>
                        <div id="msg" style="color: red;"></div>
                        <input type="submit" value="Submit" id="btnSubmit" class="btn btn-success"
                               style="margin-left:10%"/>
                        <div class="col-sm-4"></div>
                </div>
                </form>

            </div>

        </div>
    </div>
</div>
</div>
<jsp:include page="footer.jsp"/>
<link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
<script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/addfaculty.js"></script>
