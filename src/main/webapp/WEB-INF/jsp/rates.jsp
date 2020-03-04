<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>						<!-- żeby s:message działa ł, znajdzie tu te tagi i przypisze prefix "s" -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>			<!-- tgi do bezpieczeństwa i wyglądu menu -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>							
<!DOCTYPE html PUBLIC ""-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<title><s:message code="menu.name"/></title>
</head>
<body>
<%@include file="/WEB-INF/incl/menu.app" %>		



<table cellpadding="6" cellspacing="2" align="center" style="backdrop-filter: blur(2px) brightness(90%); box-shadow: 0px 0px 10px 0px #242424; padding:20px">
	<tr style="margin:20px">

		<td width="70" align="center">
			<b><s:message code="book.title"/></b>

			<a href="/rates/1/title/asc">
			<img src="/resources/images/up.png" width="10" height="10"/>
			</a>

			<a href="/rates/1/title/desc">
			<img src="/resources/images/down.png" width="10" height="10"/>
			</a>
		</td>

		<td width="100" align="center">
			<b><s:message code="book.rate"/></b>

			<a href="/rates/1/rate/asc">
			<img src="/resources/images/up.png" width="10" height="10"/>
			</a>

			<a href="/rates/1/rate/desc">
			<img src="/resources/images/down.png" width="10" height="10"/>
			</a>
		</td>
	</tr>
	<c:forEach var="rate" items="${allUserRates }">
		<tr>
			<td align="center"><c:out value="${rate.title }" />
		</td>
		<td align="center">
			<c:set var="rateVal" value="${rate.value }"/>

			<c:choose>
				<c:when test="${rateVal > 0}">
					<a href="/addrate/1/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/fillElixir.png" width="20" height="20"/>
					</a>
				</c:when>
				<c:otherwise>
					<a href="/addrate/1/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/Elixir.png" width="20" height="20"/>
					</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${rateVal > 1}">
					<a href="/addrate/2/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/fillElixir.png" width="20" height="20"/>
					</a>
				</c:when>
				<c:otherwise>
					<a href="/addrate/2/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/Elixir.png" width="20" height="20"/>
					</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${rateVal > 2}">
					<a href="/addrate/3/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/fillElixir.png" width="20" height="20"/>
					</a>
				</c:when>
				<c:otherwise>
					<a href="/addrate/3/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/Elixir.png" width="20" height="20"/>
					</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${rateVal > 3}">
					<a href="/addrate/4/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/fillElixir.png" width="20" height="20"/>
					</a>
				</c:when>
				<c:otherwise>
					<a href="/addrate/4/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/Elixir.png" width="20" height="20"/>
					</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${rateVal > 4}">
					<a href="/addrate/5/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/fillElixir.png" width="20" height="20"/>
					</a>
				</c:when>
				<c:otherwise>
					<a href="/addrate/5/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/Elixir.png" width="20" height="20"/>
					</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${rateVal > 5}">
					<a href="/addrate/6/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/fillElixir.png" width="20" height="20"/>
					</a>
				</c:when>
				<c:otherwise>
					<a href="/addrate/6/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/Elixir.png" width="20" height="20"/>
					</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${rateVal > 6}">
					<a href="/addrate/7/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/fillElixir.png" width="20" height="20"/>
					</a>
				</c:when>
				<c:otherwise>
					<a href="/addrate/7/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/Elixir.png" width="20" height="20"/>
					</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${rateVal > 7}">
					<a href="/addrate/8/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/fillElixir.png" width="20" height="20"/>
					</a>
				</c:when>
				<c:otherwise>
					<a href="/addrate/8/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/Elixir.png" width="20" height="20"/>
					</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${rateVal > 8}">
					<a href="/addrate/9/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/fillElixir.png" width="20" height="20"/>
					</a>
				</c:when>
				<c:otherwise>
					<a href="/addrate/9/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/Elixir.png" width="20" height="20"/>
					</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${rateVal > 9}">
					<a href="/addrate/10/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/fillElixir.png" width="20" height="20"/>
					</a>
				</c:when>
				<c:otherwise>
					<a href="/addrate/10/rate/${rate.bookid }/${currentPage}/${sort}/${order}?search=">
					<img src="/resources/images/Elixir.png" width="20" height="20"/>
					</a>
				</c:otherwise>
			</c:choose>
		</td>
		</tr>
	</c:forEach>
</table>

<br>
<br>

<table width="1000" border="0" cellpadding="6" cellspacing="0">
	<tr>
		<td align="left">
			<s:message code="info.page"/> ${currentPage} <s:message code="info.from"/> ${totalPages}
		</td>
		<td align="center">

			<c:if test="${currentPage > 3}">
				<input type="button"
				onclick="window.location.href='${pageContext.request.contextPath}/rates/1/${sort}/${order}'"
				value="1"/>&nbsp;&nbsp;&nbsp;
			</c:if>

			<c:if test="${currentPage > 2}">
				<input type="button"
				onclick="window.location.href='${pageContext.request.contextPath}/rates/${currentPage - 2}/${sort}/${order}'"
				value="${currentPage - 2}"/>&nbsp;
			</c:if>

			<c:if test="${currentPage > 1}">
				<input type="button"
				onclick="window.location.href='${pageContext.request.contextPath}/rates/${currentPage - 1}/${sort}/${order}'"
				value="${currentPage - 1}"/>&nbsp;
			</c:if>

			<input type="button" style=background-color:grey;color:black;width:20px;
			value="${currentPage}"/>&nbsp;

			<c:if test="${currentPage < totalPages}">
				<input type="button"
				onclick="window.location.href='${pageContext.request.contextPath}/rates/${currentPage + 1}/${sort}/${order}'"
				value="${currentPage + 1}"/>&nbsp;
			</c:if>

			<c:if test="${currentPage+1 < totalPages}">
				<input type="button"
				onclick="window.location.href='${pageContext.request.contextPath}/rates/${currentPage + 2}/${sort}/${order}'"
				value="${currentPage + 2}"/>&nbsp;&nbsp;&nbsp;
			</c:if>

			<c:if test="${currentPage+2 < totalPages}">
				<input type="button"
				onclick="window.location.href='${pageContext.request.contextPath}/rates/${totalPages}/${sort}/${order}'"
				value="${totalPages}"/>&nbsp;
			</c:if>
		</td>
	</tr>
</table>

</body>
</html>