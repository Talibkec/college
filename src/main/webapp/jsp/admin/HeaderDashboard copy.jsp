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
    <link rel="stylesheet"
          href="/jsp/admin/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">

    <!-- Theme style -->
    <link rel="stylesheet" href="/jsp/admin/dist/css/adminlte.min.css">
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light" style="z-index:0">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
            </li>

        </ul>
    </nav>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4" style="z-index: 0">
        <!-- Brand Logo -->

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user panel (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <img src="http://keck.ac.in/6/image.jpg" class="img-circle elevation-2" alt="User Image">
                </div>
                <div class="info">
                    <a href="#" class="d-block">Logged In User Name  </a>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                    data-accordion="false">
                    <!-- Add icons to the links using the .nav-icon class
                         with font-awesome or any other icon font library -->
                    <li class="nav-item has-treeview menu-open">
                        <a href="/auth/uploadDashboard" class="nav-link active">
                            <i class="nav-icon fas fa-tachometer-alt"></i>
                            <p>
                                Dashboard
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="/auth/uploadDashboard" class="nav-link ">
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
                                <a href="/auth/uploadfile/uploadslideimage" class="nav-link">
                                    <i class="ion ion-image "></i>
                                    <p>Upload Slider Image</p>
                                </a>
                            </li>

                            <li class="nav-item">
                                <a href="/auth/uploadfile/uploadslideimage" class="nav-link">
                                    <i class="ion ion-image "></i>
                                    <p>Upload Gallery Image</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/auth/uploadfile/addfaculty" class="nav-link">
                                    <i class="ion ion-person-add "></i>
                                    <p>Add Faculty</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/auth/uploadfile/addstaff" class="nav-link">
                                    <i class="ion ion-person-add "></i>
                                    <p>Add Staff</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/auth/registration" class="nav-link">
                                    <i class="ion ion-person-add "></i>
                                    <p>User Registration</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/auth/uploadfile/achievementslideimage" class="nav-link">
                                    <i class="ion ion-trophy "></i>
                                    <p>Add Achievements</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/auth/uploadfile/updateAdministration" class="nav-link">
                                    <p>Assign Administration Role</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/auth/uploadDashboard" class="nav-link">
                                    <i class="ion ion-person "></i>
                                    <p>Assign HOD Role</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/auth/uploadDownload" class="nav-link">
                                    <i class="ion ion-person "></i>
                                    <p>Upload Downloads</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/auth/uploadResponsibilityDoc" class="nav-link">
                                    <i class="ion ion-person "></i>
                                    <p>Upload Responsibility Doc</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/auth/uploadHoliday" class="nav-link">
                                    <i class="ion ion-person "></i>
                                    <p>Upload Holiday </p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="/auth/uploadAICTEapproval" class="nav-link">
                                    <i class="ion ion-person "></i>
                                    <p>Upload AICTE approval</p>
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

  
