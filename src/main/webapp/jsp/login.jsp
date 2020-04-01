<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page ="header.jsp"/>
<style>
        .btnHover:hover{
            background-color:#ccccccad !Important
            }
</style>
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
                                    <div class="input-group input-group-sm ${error != null ? 'has-error' : ''}">
                                         <span>${message}</span>
                                            <div class="row" style="margin:10px 10px 10px 0px;">
                                            <div class="col-sm-12"><input type="text" class="form-control" name="username" placeholder="Username" autofocus="true"required>
                                       </div>
                                    </div>
                                    <div class="row" style="margin:10px 10px 10px 0px;">
                                    <div class="col-sm-12"><input type="password" class="form-control" name="password"
                                                             placeholder="Password" required></div>
                                     </div>

                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                     <div class="row" style="margin:25px 10px 10px 0px;">
                                     <div class="col-sm-12">
                                       <button type="submit"
                                            data-loading-text="<i class='fa fa-cog fa-spin'></i> Please wait..."
                                            class="btn btn-default btnHover" style="padding:6px 125.5px; background-color:#81cfe8"
                                            ><i class="fa fa-check"></i> Login
                                    </button>
                                </div>
                              </div>
                              <div class="row" style="margin:10px 10px 10px 0px;">
                                <div class="col-sm-12">
                                    <a href="${contextPath}/forgotPassword" data-toggle="modal"class="btn btn-default btnHover" style="padding:6px 90.5px; background-color:#81cfe8"><i
                                            class="fa fa-info-circle"></i> Forgot Password</a>
                                </div>
                               </div>
                               <div class="row" style="margin:10px 10px 10px 0px;">
                               <div class="col-sm-12">
                               <a href="${contextPath}/auth/registration" class="btn btn-default btnHover" style="padding:6px 48px;  background-color:#81cfe8"><i
                               class="fa fa-info-circle"></i>Haven't account ?Sign Up Here </a>
                               </div>
                               </div>
                                </div>
                              </form>
            </div>
        </div>
        </div>
        </div>
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