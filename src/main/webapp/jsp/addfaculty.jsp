<jsp:include page="admin/HeaderDashboard.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


    <div class=" panel-primary">
        <div class="panel-heading"> <i class="fa fa-sign-in"></i>&nbsp;Add Faculty</div>
        <div class="container"> <br /><br />
            <div class="row">
                <div class="panel">
                    <div class="panel-body" style="height:100%; ">
                        <form method="POST" enctype="multipart/form-data" id="addFacultyForm"
                            data-action="/auth/uploadfile/addfaculty">
                            <label for="facultyName" class="control-label col-sm-4">Enter Name</label>
                            <input type="text" id="facultyName" name="facultyName"
                                class="control-label col-sm-4 ui-autocomplete-input" autocomplete="off"></br></br>
                            <label for="department" class="control-label col-sm-4">Choose Department</label>
                            <select class="control-label col-sm-4" id="deptId" name='deptId'>
                                <option value="1">Computer Sc. & Engineering</option>
                                <option value="2">Civil Engineering</option>
                                <option value="3">Mechanical Engineering</option>
                                <option value="4">Electrical & Electronics Engg.</option>
                                <option value="5">Applied Sc. & Humanities</option>
                                <option value="6">Electronics Engineering (VLSI Design and Technology)</option>
                                <option value="7">Mechanical and Manufacturing Engineering</option>
                          
                                <option value="8">Manufacturing Engineering</option>
                          
                            </select><br></br>
                            <label for="facultyOfficialEmail" class="control-label col-sm-4">Enter Official
                                Email</label>
                            <input type="email" id="facultyOfficialEmail" name="facultyOfficialEmail"
                                class="control-label col-sm-4"></br></br>
                            <label for="facultyPersonalEmail" class="control-label col-sm-4">Enter Personal
                                Email</label>
                            <input type="email" id="facultyPersonalEmail" name="facultyPersonalEmail"
                                class="control-label col-sm-4"></br></br>
                            <label for="facultyMobNo" class="control-label col-sm-4">Mobile No. </label>
                            <input type="number" id="facultyMobNo" name="facultyMobNo"
                                class="control-label col-sm-4"></br></br>
                            <label for="userName" class="control-label col-sm-4">Enter User Name</label>



                            <input type="text" id="userName" name="userName"
                                class="control-label col-sm-4 ui-autocomplete-input" autocomplete="off"></br></br>
                            <div id="form_message"></div>
                            <input type="submit" value="Submit" id="btnSubmit" class="btn btn-success"
                                style="" />
                            <div class="col-sm-4"></div>
                    </div>
                    </form>

                </div>

            </div>
        </div>
    </div>
    </div>
    <jsp:include page="footer.jsp" />
    <link rel="stylesheet" href="/js/store/jquery-ui/jquery-ui.css">
    <script src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>
    <script src="/js/jquery-ui.js"></script>
    <script src="/js/addfaculty.js"></script>