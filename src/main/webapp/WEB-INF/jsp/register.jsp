 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<title><s:message code="menu.name"/></title>
</head>
<body>
<%@include file="/WEB-INF/incl/menu.app" %>

<p align="center">
		<c:out value="${message }" />
</p>

	<sf:form id="usersForm" action="adduser" modelAttribute="user"		
		enctype="multipart/form-data" method="POST">

		<table width="500" border="0" cellpadding="4" cellspacing="1"
			align="center">

			<tr>
				<td width="130" align="right" ><s:message code="register.name"/></td>
				<td width="270" align="left"><sf:input path="name" id="name" name="name"
						size="28"  /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="name"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right"><s:message code="register.lastName"/></td>
				<td width="270" align="left"><sf:input path="lastname" id="lastname" name="lastname"
						size="28" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="lastname"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="register.email"/></td>
				<td width="270" align="left"><sf:input path="email" id="email" name="email" size="28"  /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="email"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="register.password"/></td>
				<td width="270" align="left"><sf:password path="password" id="password" name="password" size="28" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="password"/></font></td>
			</tr>

			<tr>
				<td colspan="2" align="center" style="background-color: transparent;">
					<input type="submit" value="<s:message code="button.register"/>" class="formbutton"/>
					<input type="button" value="<s:message code="button.cancel"/>" class="formbutton" 
						onclick="window.location.href='${pageContext.request.contextPath}/'"/>
				</td>
			</tr>

		</table>

	</sf:form>
	
	
	<sf:form id="usersForm" action="register" modelAttribute="user" enctype="multipart/form-data" method="GET">
	</sf:form>
	
	
</body>
</html>