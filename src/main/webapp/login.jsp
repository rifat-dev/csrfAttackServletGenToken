<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="/" method="post" autocomplete="off">
        Username: <input type="text" name="username" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
        <input type="submit" value="Login">
    </form>
</body>
</html>
