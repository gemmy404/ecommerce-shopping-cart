<%@page import="data.User"%>
<%
    String error = (String) request.getSession().getAttribute("error");
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        response.sendRedirect("index.jsp");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <%@include file="includes/header.jsp" %>
</head>
<body>
    <%@include file="includes/navbar.jsp" %>
    <div class="container">
        <div class="row justify-content-center align-items-center" style="height: 90vh;">
            <div class="col-12 col-md-8 col-lg-6 text-center">
                <div class="card">
                    <div class="card-body">
                        <h1 class="display-4 text-danger">Error</h1>
                        <p class="lead">Sorry, we couldn't process your request.</p>
                        
                        <% if (error.equals("login")) { %>
                        <p>The email or password that you've entered is incorrect.</p>
                        <a href="login.jsp" class="btn btn-primary">Try Again</a>
                        <% } else if (error.equals("register")) { %>
                        <p>That email is taken. Try another</p>
                        <a href="register.jsp" class="btn btn-primary">Try Again</a>
                        <% } else {response.sendRedirect("index.jsp");} %>
                        <a href="index.jsp" class="btn btn-secondary">Return to Home</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="includes/footer.jsp" %>
</body>
</html>
