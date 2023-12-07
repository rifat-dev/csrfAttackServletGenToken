<!DOCTYPE html>
<html>
<head>
    <title>User Page</title>
</head>
<body>
    <h2>Welcome to your account, ${user.username}!</h2>
    <p>Balance: $${user.balance}</p>

    <form action="/accounts" method="post" autocomplete="off">
        Amount: <input type="number" name="amount" required><br>
        <p> </p>
        ID: <input type="number" name="account" required><br>
        <p> </p>
        <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
        <input type="submit" value="Send ->">
        <p> </p>
    </form>
    <p> </p>
    <a href="/logout">logout</a>
</body>
</html>