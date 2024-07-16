<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp"/>

    <div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container bg-gray-200 p-5 align-start text-3xl">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>Notices</h3></div>

            </div>
        </div>
    </div>
    <div class="container">

        </ul>
        <br/>
        <div class="row">
            <div class="col-sm-8">
                <c:forEach items="${noticeList}" var="notice">
                 <c:if test = "${ notice.noticeType == 'News' or notice.noticeType == 'Notice'}">
                    <div class="post-entry panel panel-default border border-1 m-2">
                        <div class="panel-heading p-3" style="background:#eee"></a></h4></div>
                        <div class="panel-body p-3">
                            <div class="text-left" style="font-size: 120%; line-height: 150%;"> ${notice.headLine}</div>
                            <br/>
                            <a href="/notice/${notice.id}"
                                class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                                     >Read More</a>
                            <span class="label label-default"
                                                                                       style="font-size: 100%"><i
                                class="fa fa-clock-o"></i>${notice.date} </span>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </c:if>    
                </c:forEach>
                <c:if test="${empty pageSize}">
                     <c:set var = "pageSize" value = "${0}"/>
                 </c:if>
                <ul class="pager flex justify-around ">
                    <li class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"><a href="/pagination/prev/getNotices?deptno=category&pageSize=${pageSize - 1}"><i class="fa fa-angle-double-left"></i> Previous</a></li>
                    <li class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"><a href="/pagination/next/getNotices?deptno=category&pageSize=${pageSize + 1}">Next <i class="fa fa-angle-double-right"></i></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <jsp:include page="/jsp/footer.jsp"/>
