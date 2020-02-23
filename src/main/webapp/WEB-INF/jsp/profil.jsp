<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>				<!-- importujemy lib tagów do komunikatów -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>	<!-- security tags, żeby includowany plik menu.app zadziałał -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>					<!-- sterowanie wyświetlania np zawartości tabel, z bazy komunikatów -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<title><s:message code="profil.userDane"/></title>
</head>
<body>
<%@include file="/WEB-INF/incl/menu.app" %>
<div align="center">
	<h2><s:message code="profil.userDane"/></h2>
</div>

<table width="500" border="0" cellpadding="4" cellspacing="1" align="center">

	<tr>
		<td width="130" align="right" >
			<s:message code="register.email"/>
		</td>
		<td width="270" align="left">
			<c:out value="${user.email }"/>			<!-- sięgamy po atrybut -->
		</td>
	</tr>
	
	<tr>
		<td width="130" align="right" >
			<s:message code="register.name"/>
		</td>
		<td width="270" align="left">
			<c:out value="${user.name }"/>
		</td>
	</tr>
	
	<tr>
		<td width="130" align="right" >
			<s:message code="register.lastName"/>
		</td>
		<td width="270" align="left">
			<c:out value="${user.lastname }"/>
		</td>
	</tr>

<table align="center">
	<tr>
		<td colspan="2" align="center" bgcolor="#fff">
			<input type="button" value="<s:message code="button.edycjaProfilu"/>" 
					onclick="window.location.href='${pageContext.request.contextPath}/editprofile'"/>
		</td>
	
		<td colspan="2" align="center" bgcolor="#fff">
			<input type="button" value="<s:message code="button.zmianaHasla"/>" 
					onclick="window.location.href='${pageContext.request.contextPath}/editpassword'"/>
		</td>
	</tr>
</table>

</body>
</html>