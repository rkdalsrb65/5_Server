<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 결과 페이지</title>
</head>
<body>
    <%= request.getParameter("inputId") %> <br>
    <%= request.getParameter("inputPw") %> <br>

    <% String res = (String)request.getAttribute("r"); %>

    <h3><%= res %></h3>

    
</body>
</html>