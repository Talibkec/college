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
<div class="jumbotron flex justify-center bg-gray-200 p-5 text-3xl m-5">
   <div><h3 class="text-2xl"> Admin Login</h3></div>
   </div>
   <div class="">
       <div class="flex justify-center">
           <div class="col-sm-offset-4 col-sm-4 border-2 border-gray-800 p-5">
               <div class="panel panel-default">
                    <div class="panel-heading"><i class="fa fa-sign-in"></i> Admin Login</div>
                        <span>${param.message}</span>
                            <div class="panel-body">
                                <form method="post" class="form form-horizontal" action="${contextPath}/login" >
                                    <div class="input-group input-group-sm ${error != null ? 'has-error' : ''}">
                                         <span>${message}</span>
                                            <div class="row" style="margin:10px 10px 10px 0px;">
                                            <div class="w-full"><input type="text" class="w-full" name="username" placeholder="Username" autofocus="true"required>
                                       </div>
                                    </div>
                                    <div class="row" style="margin:10px 10px 10px 0px;">
                                    <div class="w-full"><input type="password" class="w-full" name="password"
                                                             placeholder="Password" required></div>
                                     </div>

                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                     <div class="row" style="margin:25px 10px 10px 0px;">
                                     <div class="w-full">
                                       <button type="submit"
                                            data-loading-text="<i class='fa fa-cog fa-spin'></i> Please wait..."
                                            class="btn btn-default btnHover" style="background-color:#81cfe8"
                                            ><i class="fa fa-check"></i> Login
                                    </button>
                                </div>
                              </div>
                              <div class="row" style="margin:10px 10px 10px 0px;">
                                
                                    <a href="${contextPath}/forgotPassword" data-toggle="modal"class="btn btn-default btnHover w-full text-center" style="background-color:#81cfe8"><i
                                            class="fa fa-info-circle"></i> Forgot Password</a>
                               
                               </div>
                               <div class="row" style="margin:10px 10px 10px 0px;">
                               <div class="w-full">
                               <a href="${contextPath}/auth/registration" class="btn btn-default btnHover w-full" style=" background-color:#81cfe8"><i
                               class="fa fa-info-circle"></i>Haven't account ?Sign Up Here </a>
                               </div>
                               </div>
                                </div>
                              </form>
            </div>
        </div>
        </div>
        </div>
    <div class="flex justify-center hidden" id="modalInfo">
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
</div>
    <jsp:include page ="footer.jsp"/>
