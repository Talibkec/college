<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<jsp:include page ="header.jsp"/>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
<div style="margin-left:10%"><h2> Create Account</h1></div>
</div>
<div class="container"> <br /><br /><div class="row"><div class="col-sm-offset-4 col-sm-4"><div class="panel panel-default"><div class="panel-heading"> <i class="fa fa-sign-in"></i>Create Accoount</div>


    <div class="panel-body">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="role">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:select class="form-control" path="role" >
                            <option  value="admin" name="roles" >Admin</option>
                            <option value="faculty" name="roles">Faculty</option>
                            <option value="hod" name="roles">HOD</option>
                        </form:select>
                        <form:errors path="role"></form:errors>
                    </div>
         </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

        </div></div></div></div></div>
    <jsp:include page ="footer.jsp"/>