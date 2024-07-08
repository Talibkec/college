<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Admin | Dashboard</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/jsp/admin/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Tempusdominus Bbootstrap 4 -->
  <link rel="stylesheet" href="/jsp/admin/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">

  <!-- Theme style -->
  <link rel="stylesheet" href="/jsp/admin/dist/css/adminlte.min.css">
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" id="bar"href="#" role="button"><i class="fa fa-bars"></i></a>
      </li>
    </ul>
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="/jsp/gallery/facultypic/mta.jp" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block">Head of Department </a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item has-treeview menu-open">
            <a href="/hod/hodDashboard" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Dashboard
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="/hod/hodDashboard" class="nav-link ">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Dashboard</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/auth/uploadfile/upload" class="nav-link">
                  <i class="ion ion-upload "></i>
                  <p>Upload Notice/News</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/hod/addfaculty" class="nav-link">
                  <i class="ion ion-person-add "></i>
                  <p>Add Faculty</p>
                </a>
              </li>

            </ul>
          </li>
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">
              Add Faculty
            </h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">


            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-2 col-sm-8">
      <div class="panel panel-primary"><div class="panel-heading"> <i class="fa fa-sign-in"></i>&nbsp;Add Faculty</div>
        <div class="panel-body"style="height:100%; ">
          <form  method="POST" enctype="multipart/form-data" id="addFacultyForm" data-action="/hod/uploadfile/addfaculty">
            <label for ="facultyName" class="control-label col-sm-4">Enter Name</label>
            <input type="text" id="facultyName"  name="facultyName" class="control-label col-sm-4 ui-autocomplete-input" autocomplete="off"></br></br>
            <label for ="department" class="control-label col-sm-4">Choose Department</label>
            <select class="control-label col-sm-4" id="deptId"  name='deptId'>
              <option value ="1">Computer Sc. & Engineering</option>
              <option value="2" >Civil Engineering</option>
              <option value ="3">Mechanical Engineering</option>
              <option value="4" >Electrical & Electronics Engg.</option>
              <option value="5" >Applied Sc. & Humanities</option>
              <option value="6" >Electronics Engineering (VLSI Design and Technology)</option>
            </select><br></br>
            <label for ="facultyOfficialEmail" class="control-label col-sm-4">Enter Official Email</label>
            <input type="email" id="facultyOfficialEmail"name="facultyOfficialEmail"class="control-label col-sm-4"></br></br>
            <label for ="facultyPersonalEmail"class="control-label col-sm-4">Enter Personal Email</label>
            <input type="email" id="facultyPersonalEmail" name="facultyPersonalEmail"class="control-label col-sm-4"></br></br>
            <label for ="facultyMobNo"class="control-label col-sm-4">Mobile No. </label>
            <input type="number" id="facultyMobNo" name="facultyMobNo"class="control-label col-sm-4"></br></br>
            <label for ="userName" class="control-label col-sm-4">Enter User Name</label>
            <input type="text" id="userName" name="userName" class="control-label col-sm-4 ui-autocomplete-input"autocomplete="off" ></br></br>
            <div id="form_message"></div>
            <input type="submit" value="Submit" id="btnSubmit" class="btn btn-success" style="margin-left:10%" />
            <div class="col-sm-4"></div>
        </div>
        </form>

      </div>

    </div>
    </div></div>
  </div>



    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->


<jsp:include page="../footer.jsp"/>

<script src="/js/addfaculty.js"></script>
