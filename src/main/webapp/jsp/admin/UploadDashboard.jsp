<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <jsp:include page="HeaderDashboard.jsp" />
  <!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
  <!-- Content Header (Page header) -->
  <div class="content-header">
    <div class="container-fluid">
      <div class="flex justify-center w-full">
        <div class="col-sm-6">
          <h1 class="text-2xl font-semibold">Quick Dashboard</h1>
        </div><!-- /.col -->
        <div class="col-sm-6">
          <ol class="breadcrumb float-sm-right">
            <!-- Breadcrumbs can go here -->
          </ol>
        </div><!-- /.col -->
      </div><!-- /.row -->
    </div><!-- /.container-fluid -->
  </div>
  <!-- /.content-header -->

  <!-- Main content -->
  <section class="content">
    <div class="container-fluid">
      <!-- Grid layout for the stat boxes -->
      <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 p-3">
        <!-- Notice/ News Box -->
        <div class="p-6 bg-white border border-gray-200 rounded-lg shadow-lg dark:bg-gray-800 dark:border-gray-700">
          <a href="#">
            <h5 class="mb-2 text-xl font-bold tracking-tight text-gray-900 dark:text-white">Notice/ News</h5>
          </a>
          <a href="/auth/uploadfile/upload" class="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            Click Here
            <svg class="rtl:rotate-180 w-4 h-4 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
            </svg>
          </a>
        </div>

        <!-- Slider Box -->
        <div class="p-6 bg-white border border-gray-200 rounded-lg shadow-lg dark:bg-gray-800 dark:border-gray-700">
          <a href="#">
            <h5 class="mb-2 text-xl font-bold tracking-tight text-gray-900 dark:text-white">Slider</h5>
          </a>
          <a href="/auth/uploadfile/uploadslideimage" class="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            Click Here
            <svg class="rtl:rotate-180 w-4 h-4 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
            </svg>
          </a>
        </div>

        <!-- Gallery Box -->
        <div class="p-6 bg-white border border-gray-200 rounded-lg shadow-lg dark:bg-gray-800 dark:border-gray-700">
          <a href="#">
            <h5 class="mb-2 text-xl font-bold tracking-tight text-gray-900 dark:text-white">Gallery</h5>
          </a>
          <a href="/auth/uploadfile/uploadgalleryimage" class="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            Click Here
            <svg class="rtl:rotate-180 w-4 h-4 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
            </svg>
          </a>
        </div>

        <!-- User Box -->
        <div class="p-6 bg-white border border-gray-200 rounded-lg shadow-lg dark:bg-gray-800 dark:border-gray-700">
          <a href="#">
            <h5 class="mb-2 text-xl font-bold tracking-tight text-gray-900 dark:text-white">User</h5>
          </a>
          <a href="${contextPath}/auth/registration" class="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            Click Here
            <svg class="rtl:rotate-180 w-4 h-4 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
            </svg>
          </a>
        </div>

        <!-- Faculty Box -->
        <div class="p-6 bg-white border border-gray-200 rounded-lg shadow-lg dark:bg-gray-800 dark:border-gray-700">
          <a href="#">
            <h5 class="mb-2 text-xl font-bold tracking-tight text-gray-900 dark:text-white">Faculty</h5>
          </a>
          <a href="/auth/uploadfile/addfaculty" class="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            Click Here
            <svg class="rtl:rotate-180 w-4 h-4 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
            </svg>
          </a>
        </div>

        <!-- Administration Box -->
        <div class="p-6 bg-white border border-gray-200 rounded-lg shadow-lg dark:bg-gray-800 dark:border-gray-700">
          <a href="#">
            <h5 class="mb-2 text-xl font-bold tracking-tight text-gray-900 dark:text-white">Administration</h5>
          </a>
          <a href="/auth/uploadfile/updateAdministration" class="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            Click Here
            <svg class="rtl:rotate-180 w-4 h-4 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
            </svg>
          </a>
        </div>

        <!-- Achievements Box -->
        <div class="p-6 bg-white border border-gray-200 rounded-lg shadow-lg dark:bg-gray-800 dark:border-gray-700">
          <a href="#">
            <h5 class="mb-2 text-xl font-bold tracking-tight text-gray-900 dark:text-white">Achievements</h5>
          </a>
          <a href="/auth/uploadfile/achievementslideimage" class="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            Click Here
            <svg class="rtl:rotate-180 w-4 h-4 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
            </svg>
          </a>
        </div>


        <!-- HOD Box -->
        <div class="p-6 bg-white border border-gray-200 rounded-lg shadow-lg dark:bg-gray-800 dark:border-gray-700">
          <a href="#">
            <h5 class="mb-2 text-xl font-bold tracking-tight text-gray-900 dark:text-white">HOD</h5>
          </a>
          <a href="/auth/hodincharge" class="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
            Click Here
            <svg class="rtl:rotate-180 w-4 h-4 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
            </svg>
          </a>
        </div>
      </div>
    </div><!-- /.container-fluid -->
  </section>
  <!-- /.content -->
</div>

      <div class="flex flex-col lg:flex-row">
        <!-- Left col -->
        <section class="lg:w-full p-4">
          <!-- Custom tabs (Charts with tabs) -->
          <div class="bg-white border border-gray-200 rounded-lg p-4 shadow-lg">
            <div class="flex justify-between items-center p-4 border-b border-gray-300">
              <h3 class="text-2xl font-semibold text-gray-800">Notices</h3>
              <div class="card-tools">
                <!-- Additional tools can go here -->
              </div>
            </div>
            <div class="p-5 border-2 border-gray-700 rounded-xl overflow-hidden">
              <div class="overflow-y-auto h-80"> <!-- Adjust height as needed -->
                <ul class="space-y-2">
                  <li class="text-gray-800 text-sm">
                    <a href="https://www.aicte-india.org/feedback/" class="hover:underline" target="_blank">Student and Faculty give feedback to AICTE</a>
                  </li>
                  <li class="text-gray-800 text-sm">
                    <a href="/jsp/facilities/payment.pdf" class="hover:underline" target="_blank">Fee Payment Tutorial through SBI Collect</a>
                  </li>

                  <c:forEach items="${noticeList}" var="notice">
                    <c:if test="${ notice.noticeType == 'News' or notice.noticeType == 'Notice'}">
                      <li class="flex items-center space-x-2 text-gray-800 text-sm">
                        <span class="border border-gray-300 text-gray-600 px-2 py-1 rounded-lg">
                          <c:set var="clazz" value="bg-yellow-300 text-gray-800" />
                          <c:if test="${ notice.noticeType == 'News'}">
                            <c:set var="clazz" value="bg-blue-300 text-gray-800" />
                          </c:if>
                          <i class="mr-1"></i>${notice.date}
                        </span>
                        <span class="${clazz} px-2 py-1 rounded-lg">${notice.noticeType}</span>
                        <c:forEach var="item" items="${Role}">
                          <c:if test="${'Admin' eq item}">
                            <form action="<c:url value='/auth/delNotice/${notice.id}'/>" method="post" class="ml-4">
                              <button type="submit" class="text-red-600 hover:bg-red-100 px-3 py-1 rounded-lg border border-red-500">Delete</button>
                            </form>
                          </c:if>
                        </c:forEach>
                        <a href="/${notice.id}/notice${notice.fileType}" class="text-blue-600 hover:underline" target="_blank">${notice.headLine}</a>
                      </li>
                    </c:if>
                  </c:forEach>
                </ul>
              </div>
            </div>
          </div>
        </section>
      </div>




  <jsp:include page="../footer.jsp" />