<%@page import="com.cibt.kaampay.entity.User"%>
<%@page import="com.cibt.kaampay.service.impl.UserServiceImpl"%>
<%@page import="com.cibt.kaampay.service.UserService"%>
<%
    if(request.getMethod().equalsIgnoreCase("POST")){
        
    UserService userService = new UserServiceImpl();
    User user = new User();
    user.setEmail(request.getParameter("email"));
    user.setPassword(request.getParameter("password"));
    userService.save(user);
    response.sendRedirect(request.getContextPath()+ "/login.jsp");
    
    }
 %>

<%@include file="shared/header.jsp" %>
<h1>Register</h1>

<form action="" method="post">
    <div>
        <label>Email</label>
        <input type="text" name="email" required='required'/>
    </div>
    
     <div>
        <label>password</label>
        <input type="text" name="password" required='required'/>
    </div>
    
    <button type="submit">Register</button>

</form>
<%@include file="shared/footer.jsp" %>
