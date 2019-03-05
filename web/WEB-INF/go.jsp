<%--
  Created by IntelliJ IDEA.
  User: liuxj
  Date: 2017-12-28
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title> let'sGo</title></head>
<body>
${msg}
<br/>
<form action="/recognition" method="post" enctype="multipart/form-data">
    <input type="file" name="pics"/>
    <input type="submit" value="识别"/>
</form>
</body>
</html>
