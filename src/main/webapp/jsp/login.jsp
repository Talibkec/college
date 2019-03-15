<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<jsp:include page ="header.jsp"/>
    <div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div style="margin-left:80px"><h3> Admin Login</h3></div>
    </div>
    <div class="container"><br/><br/>
        <div class="row">
            <div class="col-sm-offset-4 col-sm-4">
                <div class="panel panel-default">
                    <div class="panel-heading"><i class="fa fa-sign-in"></i> Admin Login</div>
                    <span>${param.message}</span>
                    <div class="panel-body">
                        <form method="post" class="form form-horizontal" action="${contextPath}/login" >
                         <div class="form-group ${error != null ? 'has-error' : ''}">
                            <span>${message}</span>
                            <div class="form-group"><label class="control-label col-sm-4">Username</label>
                                <div class="col-sm-8"><input type="text" class="form-control" name="username" placeholder="Username" autofocus="true"required>
                                </div>
                            </div>
                            <div class="form-group"><label class="control-label col-sm-4">Password</label>
                                <div class="col-sm-8"><input type="password" class="form-control" name="password"
                                                             placeholder="Password" required></div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <hr/>
                            <div class="row">
                                <div class="col-sm-6">
                                    <button type="submit"
                                            data-loading-text="<i class='fa fa-cog fa-spin'></i> Please wait..."
                                            class="btn btn-default" style="margin-right:10px"><i class="fa fa-check"></i> Login
                                    </button>
                                </div>
                                <div class="col-sm-6">
                                    <a href="${contextPath}/forgotPassword" data-toggle="modal" class="btn btn-default"><i
                                            class="fa fa-info-circle"></i> Forgot Password</a></div>
                            </div>
                            </div>
                        </form><br/><div class="col-sm-8">
                        <a href="${contextPath}/auth/registration"  class="btn btn-default"style="margin-left:30px"><i
                                class="fa fa-info-circle"></i>Haven't account ?Sign Up Here</a></div>
                    </div>
                </div>
            </div>
        </div>
        <br/><br/></div>
    <div class="modal fade" id="modalInfo">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header"><h4 class="modal-title">Information!</h4></div>
                <div class="modal-body"> Please contact site admin to reset and get new password for your account.
                    <hr/>
                    <div class="text-right">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-remove"></i>
                            Close
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page ="footer.jsp"/>