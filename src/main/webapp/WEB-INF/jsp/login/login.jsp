<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登陆页面</title>
</head>
<body>
    <h1>用户登陆</h1>
    <form action="/user/login" method="get">
        用户名：<input type="text" name="username" /></br>
        密码：<input type="password" name="password" />
        <input type="submit" value="登陆" />
    </form>
</body>
</html>