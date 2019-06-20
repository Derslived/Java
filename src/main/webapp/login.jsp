<%@page import="com.cibt.kaampay.service.impl.UserServiceImpl"%>
<%@page import="com.cibt.kaampay.entity.User"%>
<%@page import="com.cibt.kaampay.service.UserService"%>
<%
    if (request.getMethod().equalsIgnoreCase("POST")) {

        UserService userService = new UserServiceImpl();
        User user = userService.login(request.getParameter("email"),
                request.getParameter("password"));
        if (user != null) {
            response.sendRedirect(request.getContextPath() + "/admin.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error");
        }

    }
%>
<%@include file="shared/header.jsp" %>
<h1> Login </h1>
<form action="" method="post">
    <div>
        <label>Email</label>
        <input type="text" name="email" required='required'/>
    </div>

    <div>
        <label>password</label>
        <input type="text" name="password" required='required'/>
    </div>

    <button type="submit">Login</button>
    <a href="register.jsp">Register</a>

</form>

<%@include file="shared/footer.jsp" %>


