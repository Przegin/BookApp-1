 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>				<!-- importujemy lib tagów do komunikatów -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>	<!-- security tags, żeby includowany plik menu.app zadziałał -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>		<!-- zestaw tagów do konstruowania i obsługi komentarzy, ustawiamy tamp pola do obsługi walidacji -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>					<!-- sterowanie wyświetlania np zawartości tabel, z bazy komunikatów -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<title><s:message code="menu.name"/></title>								<!-- rejestracja, wyświetlanie na stronie inf -->
</head>
<body>
<%@include file="/WEB-INF/incl/menu.app" %>								<!-- menu importjemy do każdej strony, razem z tagami -->

<p align="center">
		<c:out value="${message }" />									<!-- tag c do wyświetlania zawartości tego co wraca z kontrolera, out to jedno z poleceń zawartych w jstl core -->
</p>																	<!-- sam formularz -->

	<sf:form id="usersForm" action="adduser" modelAttribute="user"		
		enctype="multipart/form-data" method="POST">					<!-- sf, tagi forlmularzy springowych-->
																		<!-- akcje dla formularza, modelAtribute - przekazuje na jakim modelu formularza pracujemy - user -->
		<table width="500" border="0" cellpadding="4" cellspacing="1"
			align="center">

			<tr>
				<td width="130" align="right" ><s:message code="register.name"/></td>
				<td width="270" align="left"><sf:input path="name" id="name" name="name"
						size="28"  /></td>							<!-- name odpowiada temu, co mamy w klasie user itd -->
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="name"/></font></td>		<!-- sf errors, walidator będzie szukał tych pól z tą samą nazwą, wróci do tego pola i wyświetli komunikat -->
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
					<input type="submit" value="<s:message code="button.register"/>" class="formbutton"/>			<!-- przyciski do akcji, u nas będą umieszczane w kontrolerze -->
					<input type="button" value="<s:message code="button.cancel"/>" class="formbutton" 
						onclick="window.location.href='${pageContext.request.contextPath}/'"/>						<!-- przekierowanie do str głównej op cancel -->
				</td>
			</tr>

		</table>

	</sf:form>
	
	
	<sf:form id="usersForm" action="register" modelAttribute="user" enctype="multipart/form-data" method="GET">
	</sf:form>
	
	
</body>
</html>