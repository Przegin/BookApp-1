<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
		<title><s:message code="menu.name"/></title>
	</head>
	<body>
		<%@include file="/WEB-INF/incl/menu.app" %>

		<h2 align="center"><s:message code="menu.users"/></h2>

		<form id="searchForm" align="center" action="/admin/users/${currentPage}?search=${search}" method="GET" style="margin:20px">

			<label for="search">Wyszukaj: </label>
			<input type="text" id="search" name="search" style="width:500px;">
			<input type="submit" value="Szukaj"/>

		</form>

		<br>

		<table align="center" width="600" border="0" cellpadding="6" cellspacing="2" style="backdrop-filter: blur(2px) brightness(90%); box-shadow: 0px 0px 10px 0px #242424; padding:20px">

			<tr>
				<td width="190" align="center" style="font-weight:bold"><s:message code="register.name"/></td>
				<td width="190" align="center" style="font-weight:bold"><s:message code="register.lastName"/></td>
				<td width="200" align="center" style="font-weight:bold"><s:message code="register.email"/></td>
				<td width="100" align="center" style="font-weight:bold"><s:message code="profil.czyAktywny"/></td>
				<td width="120" align="center" style="font-weight:bold"><s:message code="profil.rola"/></td>
				<td width="50"></td>
			</tr>

		<c:forEach var="u" items="${userList }">
				<tr style="margin:20px">
					<td width="190" align="center"><c:out value="${u.name }" /></td>
					<td width="190" align="center"><c:out value="${u.lastname }" /></td>
					<td width="200" align="center"><c:out value="${u.email }" /></td>
					<td align="center">
						<c:choose>
							<c:when test="${u.active == 1 }">
								<font color="green"><s:message code="word.tak"/></font>
							</c:when>
							<c:otherwise>
								<font color="red"><s:message code="word.nie"/></font>
							</c:otherwise>
						</c:choose>
					</td>
					<td width="100" align="center">
					<c:choose>
						<c:when test="${u.nrRoli == 1 }">
							<font color="green"><s:message code="word.admin"/></font>
						</c:when>
						<c:otherwise>
							<s:message code="word.user"/>
						</c:otherwise>
					</c:choose>
					</td>
					<td width="50" align="center">
					<c:choose>
						<c:when test="${u.nrRoli == 1 }">
						<img src="/resources/images/admindelete.png" width="16" height="16"/>
						</c:when>
						<c:otherwise>
							<a href="/delete/${u.id }/${currentPage}?search=${search}">
								<img src="/resources/images/userdelete.png" width="16" height="16"/>
							</a>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>

		<br>
		<br>

		<table width="800" border="0" cellpadding="6" cellspacing="0">
			<tr>
				<td align="left">
					<s:message code="info.page"/> ${currentPage} <s:message code="info.from"/> ${totalPages}
				</td>
				<td align="center">

					<c:if test="${currentPage > 3}">
						<input type="button"
							   onclick="window.location.href='${pageContext.request.contextPath}/admin/users/1?search=${search}'"
							   value="1"/>&nbsp;&nbsp;&nbsp;
					</c:if>

					<c:if test="${currentPage > 2}">
						<input type="button"
							   onclick="window.location.href='${pageContext.request.contextPath}/admin/users/${currentPage - 2}?search=${search}'"
							   value="${currentPage - 2}"/>&nbsp;
					</c:if>

					<c:if test="${currentPage > 1}">
						<input type="button"
							   onclick="window.location.href='${pageContext.request.contextPath}/admin/users/${currentPage - 1}?search=${search}'"
							   value="${currentPage - 1}"/>&nbsp;
					</c:if>

					<input type="button" style=background-color:grey;color:black;width:20px;
						   value="${currentPage}"/>&nbsp;

					<c:if test="${currentPage < totalPages}">
						<input type="button"
							   onclick="window.location.href='${pageContext.request.contextPath}/admin/users/${currentPage + 1}?search=${search}'"
							   value="${currentPage + 1}"/>&nbsp;
					</c:if>

					<c:if test="${currentPage+1 < totalPages}">
						<input type="button"
							   onclick="window.location.href='${pageContext.request.contextPath}/admin/users/${currentPage + 2}?search=${search}'"
							   value="${currentPage + 2}"/>&nbsp;&nbsp;&nbsp;
					</c:if>

					<c:if test="${currentPage+2 < totalPages}">
						<input type="button"
							   onclick="window.location.href='${pageContext.request.contextPath}/admin/users/${totalPages}?search=${search}'"
							   value="${totalPages}"/>&nbsp;
					</c:if>
				</td>
			</tr>
		</table>
	</body>
</html>