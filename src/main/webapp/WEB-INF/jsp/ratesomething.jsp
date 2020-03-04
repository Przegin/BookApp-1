<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>			
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>				
<!DOCTYPE html PUBLIC ""-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jsfile.js"></script>
        <title><s:message code="menu.name"/></title>
    </head>
    <body>
        <h2 align="center">Oceń kilka książek aby otrzmać rekomendacje</h2>
        <br>
        <a href="/books/1/title/asc?search=">
            <input type="button" align="center" value="<s:message code="menu.ksiazki"/>" class="formbutton" />
        </a>
    </body>
</html>