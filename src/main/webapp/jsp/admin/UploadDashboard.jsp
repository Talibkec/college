<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="HeaderDashboard.jsp"/>
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">Dashboard</h1>
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
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-info">
              <div class="inner">
                <h4>Upload Notice/ News</h4>

                
              </div>
              <div class="icon">
                <i class="ion ion-upload"></i>
              </div>
              <a href="http://localhost/auth/uploadfile/upload" class="small-box-footer">Click Here <i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-success">
              <div class="inner">
                <h4>Upload Slider Image<sup style="font-size: 20px"></sup></h4>

                
              </div>
              <div class="icon">
                <i class="ion ion-image"></i>
              </div>
              <a href="http://localhost/auth/uploadfile/uploadslideimage" class="small-box-footer">Click Here <i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
                               <!-- small box -->
                                <div class="small-box bg-warning">
                                  <div class="inner">
                                    <h4>Upload Gallery Image</h4>
                                  </div>
                                  <div class="icon">
                                  <i class="ion ion-image"></i>
                                 </div>
                                 <a href="http://localhost/auth/uploadfile/uploadslideimage" class="small-box-footer">Click Here<i class="fas fa-arrow-circle-right"></i></a>
                                </div>
                                </div>
                               <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-danger">
              <div class="inner">
                <h4>User Registrations</h4>

                
              </div>
              <div class="icon">
                <i class="ion ion-person-add"></i>
              </div>
              <a href="${contextPath}/auth/registration" class="small-box-footer">Click Here <i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <div class="col-lg-3 col-6">
                      <!-- small box -->
                      <div class="small-box bg-info">
                        <div class="inner">
                          <h4>Add Faculty</h4>
                         </div>
                        <div class="icon">
                          <i class="ion ion-person-add"></i>
                        </div>
                        <a href="/auth/uploadfile/addfaculty" class="small-box-footer">Click Here <i class="fas fa-arrow-circle-right"></i></a>
                      </div>
                    </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-success">
              <div class="inner">
                <h4>Add Achievements</h4>
              </div>
              <div class="icon">
                <i class="ion ion-trophy"></i>
              </div>
              <a href="/auth/uploadfile/achievementslideimage" class="small-box-footer">Click Here<i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
             <div class="small-box bg-warning">
               <div class="inner">
                 <h5 style="font-size:20px">Assign Administration Role</h5>
               </div>
               <div class="icon">
               <i class="ion ion-person"></i>
              </div>
              <a href="/auth/uploadfile/updateAdministration" class="small-box-footer">Click Here<i class="fas fa-arrow-circle-right"></i></a>
             </div>
             </div>
            <!-- ./col -->
     <div class="col-lg-3 col-6">
                 <!-- small box -->
                  <div class="small-box bg-danger">
                    <div class="inner">
                      <h4>Assign HOD Role</h4>
                    </div>
                    <div class="icon">
                    <i class="ion ion-person"></i>
                   </div>
                   <a href="http://localhost/auth/hodincharge" class="small-box-footer">Click Here<i class="fas fa-arrow-circle-right"></i></a>
                  </div>
                  </div>
                 <!-- ./col -->
         
        </div>
        <!-- /.row -->
        <!-- Main row -->
        <div class="row">
          <!-- Left col -->
          <section class="col-lg-7 connectedSortable">
            <!-- Custom tabs (Charts with tabs)-->
            <div class="card">
              <div class="card-header">
                <h3 class="title">
                  
                  Notices
                </h3>
                <div class="card-tools">
                  
                </div>
               
              </div><!-- /.card-header -->
              <div class="card-body">
                <div class="tab-content p-0">
                  <!-- Morris chart - Sales -->
                    
                </div>
                <marquee direction="up" Scrolldelay=250 loop="infinite" onmouseover="this.stop();" onmouseout="this.start();">
                          <ul>
                             <li><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif"><a href="https://www.aicte-india.org/feedback/" target="_blank">Student and Faculty give feedback to AICTE</a></span></span></li>
                             <li><span style="font-size:14px"><span style="font-family:arial,helvetica,sans-serif"><a href="http://localhost/jsp/facilities/payment.pdf" target="_blank">Fee Payment Tutorial through SBI Collect</a></span></span></li>
       
                        <c:forEach items="${noticeList}" var="notice">
                            <c:if test = "${ notice.noticeType == 'News' or notice.noticeType == 'Notice'}">
                             <div>
                               <span class="label" style="border:  solid 1px #ccc; color: #000;">
                                  <c:set var = "clazz"  value = "label label-warning"/>
                                    <c:if test = "${ notice.noticeType == 'News'}">
                                       <c:set var = "clazz"  value = "label label-primary"/>
                                    </c:if>
                                       <i class=""></i> ${notice.date} </span>&nbsp; <span class="${clazz}">${notice.noticeType}</span>
                                          <c:forEach var="item" items="${Role}">
                                             <c:if test = "${'Admin' eq item}">
                                                <span class="label label-danger"><a href="<c:url value='/auth/${notice.uploadedFileName}/${notice.id}'/>">Delete</a></span>
                                             </c:if>
                                          </c:forEach>
                                       <a href="http://localhost/${notice.id}/notice${notice.fileType}" target="_blank">${notice.headLine}</a>
                                                                      
                                          </div>
                                                </c:if>
                        </c:forEach>
                     </ul> 
               
               </marquee>
                 </div><!-- /.card-body -->
            </div>
            <!-- /.card -->

            

            <!-- TO DO List -->
            <div class="card">
              
            </div>
            <!-- /.card -->
          </section>
          <!-- /.Left col -->
          <!-- right col (We are only adding the ID to make the widgets sortable)-->
                    <!-- right col -->
        </div>
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
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
