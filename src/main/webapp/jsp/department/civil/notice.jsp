<jsp:include page="/jsp/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">

    <div class="">

        <div class="md:flex justify-center">
            <jsp:include page="sidebar.jsp"/>
            
                                                <div class="col-sm-8  p-3"  style="">
                                                    <c:forEach items="${noticeList}" var="notice">

                                                        <div class="post-entry panel panel-default">
                                                            <div class="panel-heading"></h4></div>
                                                            <div class="panel-body">
                                                                <div class="text-left" style="font-size: 120%; line-height: 150%;"> ${notice.headLine}</div>
                                                                <br/> <a href="/${notice.id}/notice${notice.fileType}" target="_blank"
                                                                    class="btn btn-default pull-right">Download</a> <span class="label label-default"
                                                                                                                           style="font-size: 100%"><i
                                                                    class="fa fa-clock-o"></i>${notice.date} </span>
                                                                <div class="clearfix"></div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                       <c:if test="${empty pageSize}">
                                                                                                <c:set var = "pageSize" value = "${0}"/>
                                                                                            </c:if>

                                                   <ul class="pager">
                                                    <li><a href="/pagination/prev/getNotices?deptno=civil&pageSize=${pageSize - 1}"><i class="fa fa-angle-double-left"></i> Previous</a></li>
                                                     <li><a href="/pagination/next/getNotices?deptno=civil&pageSize=${pageSize + 1}">Next <i class="fa fa-angle-double-right"></i></a>
                                                     </li>
                                                    </ul>
                                                </div>
                                            
            </div>
        </div>
    </div>
<jsp:include page="/jsp/footer.jsp"/>
