<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="admin/HeaderDashboard.jsp" />

<div class="container mx-auto p-6">
    <div class="max-w-md mx-auto bg-white p-8 rounded-lg shadow-md">
        <div class="text-center mb-6">
            <h2 class="text-2xl font-bold text-gray-800">Create Your Account</h2>
            <i class="fa fa-sign-in text-gray-500"></i>
        </div>

        <form:form method="POST" modelAttribute="userForm" class="space-y-4">
            <spring:bind path="username">
                <div class="relative">
                    <form:input type="text" path="username" class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                placeholder="Username" autofocus="true"></form:input>
                    <form:errors path="username" cssClass="text-red-500 text-sm absolute bottom-0 left-0"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="relative">
                    <form:input type="password" path="password" class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                placeholder="Password"></form:input>
                    <form:errors path="password" cssClass="text-red-500 text-sm absolute bottom-0 left-0"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="relative">
                    <form:input type="password" path="passwordConfirm" class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                                placeholder="Confirm your password"></form:input>
                    <form:errors path="passwordConfirm" cssClass="text-red-500 text-sm absolute bottom-0 left-0"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="role">
                <div class="relative">
                    <form:select class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" path="role">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}">${role.name}</option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="role" cssClass="text-red-500 text-sm absolute bottom-0 left-0"></form:errors>
                </div>
            </spring:bind>

            <button type="submit" class="w-full px-4 py-2 bg-blue-500 text-white font-semibold rounded-md hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500">
                Submit
            </button>
        </form:form>
    </div>
</div>

<jsp:include page="footer.jsp" />
