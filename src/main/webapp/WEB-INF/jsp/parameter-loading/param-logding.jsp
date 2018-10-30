<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>参数装载测试</title>
</head>
<body>
    <form method="post" action="/submit">
        教师姓名：<input type="text" name="teacher.name"/><br/><br/>
        学生姓名：<input type="text" name="student.name"/><br/><br/>
        学生年龄：<input type="text" name="age"/><br/><br/>
        <input type="submit" value="submit"/>
    </form>
</body>
</html>