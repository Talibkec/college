<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="header.jsp"/>

<div class="bg-gray-200 py-8">
    <div class="container mx-auto px-4">
        <div class="flex items-center justify-center">
            <div class="text-center">
                <h3 class="text-3xl font-semibold">Login</h3>
            </div>
        </div>
    </div>
</div>

<div class="py-10">
    <div class="flex justify-center">
        <div class="w-full max-w-sm bg-white border-2 border-gray-300 p-8 rounded-lg shadow-md">
            <div class="mb-6 text-center">
                <h4 class="text-xl font-bold"><i class="fa fa-sign-in"></i> Admin Login</h4>
            </div>
            <form method="post" action="${contextPath}/login">
                <div class="mb-4">
                    <span class="text-red-500">${param.message}</span>
                    <div class="relative">
                        <input type="text" name="username" placeholder="Username"
                               class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                               autofocus required>
                    </div>
                </div>
                <div class="mb-4">
                    <span class="text-red-500">${message}</span>
                    <div class="relative">
                        <input type="password" name="password" placeholder="Password"
                               class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                               required>
                    </div>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="mb-4">
                    <button type="submit"
                            class="w-full bg-blue-500 text-white font-bold py-2 px-4 rounded-md hover:bg-blue-600 transition-colors duration-300">
                        <i class="fa fa-check"></i> Login
                    </button>
                </div>

                <div class="mb-4">
                    <a href="${contextPath}/forgotPassword"
                       class="block text-center bg-gray-300 text-black font-bold py-2 px-4 rounded-md hover:bg-gray-400 transition-colors duration-300">
                        <i class="fa fa-info-circle"></i> Forgot Password
                    </a>
                </div>

                <div>
                    <a href="${contextPath}/auth/registration"
                       class="block text-center bg-gray-300 text-black font-bold py-2 px-4 rounded-md hover:bg-gray-400 transition-colors duration-300">
                        <i class="fa fa-info-circle"></i> Haven't account? Sign Up Here
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="flex justify-center hidden" id="modalInfo">
    <div class="max-w-md w-full bg-white rounded-lg shadow-md">
        <div class="p-4">
            <h4 class="text-lg font-bold">Information!</h4>
            <p class="mt-2">Please contact site admin to reset and get a new password for your account.</p>
            <hr class="my-4"/>
            <div class="text-right">
                <button type="button"
                        class="px-4 py-2 bg-gray-300 text-black font-bold rounded-md hover:bg-gray-400 transition-colors duration-300">
                    <i class="fa fa-remove"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
