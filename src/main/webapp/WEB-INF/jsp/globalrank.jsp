<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!-- żeby s:message działa ł, znajdzie tu te tagi i przypisze prefix "s" -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!-- tgi do bezpieczeństwa i wyglądu menu -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC ""-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<script src="${pageContext.request.contextPath}/resources/js/jsfile.js"></script>
<title><s:message code="menu.mainPage"/></title>
</head>
<body>
<%@include file="/WEB-INF/incl/menu.app" %>


<table border="5F0F40" cellpadding="6" cellspacing="2" align="center">
<tr>
<td width="90" align="center"><b><s:message code="book.rankplace"/></b></td>
<td width="90" align="center"><b><s:message code="book.cover"/></b></td>
<td width="70" align="center"><b><s:message code="book.title"/></b></td>
<td width="100" align="center"><b><s:message code="book.author"/></b></td>
<td width="50" align="center"><b><s:message code="book.isfav"/></b></td>
<td width="50" align="center"><b><s:message code="book.toRead"/></b></td>
<td width="50" align="center"><b><s:message code="book.srocen"/></b></td>
</tr>

<c:forEach var="book" items="${booksList }">
    <tr>
    <td align="left"><c:out value="${book.rankplace }"/></td>
    <td align="center" width="70" height="110" align="left"><img src="data:image/jpg;base64,${book.coverimage}" width="70" height="110"/></td>
    <td align="left"><c:out value="${book.title }"/></td>
    <td align="left"><c:out value="${book.author }"/></td>

    <td align="center">
    <c:set var="isFavorite" value="${book.isfav }"/>
    <c:choose>
        <c:when test="${isFavorite == true}">
            <a href="/favorite/${book.id}/global/${currentPage}/x/x?search=" >
            <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
            </a>
        </c:when>
        <c:otherwise>
            <a href="/favorite/${book.id}/global/${currentPage}/x/x?search=" >
            <img src="/resources/images/Star.jpg" width="20" height="20"/>
            </a>
        </c:otherwise>
    </c:choose>
    </td>

    <td align="center">
    <c:set var="isToRead" value="${book.istoread }"/>
    <c:choose>
        <c:when test="${isToRead == true}">
            <a href="/toread/${book.id}/global/${currentPage}/x/x?search=" >
            <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
            </a>
        </c:when>
        <c:otherwise>
            <a href="/toread/${book.id}/global/${currentPage}/x/x?search=" >
            <img src="/resources/images/Star.jpg" width="20" height="20"/>
            </a>
        </c:otherwise>
    </c:choose>
    </td>

    <td align="center">
    <c:set var="avrate" value="${book.avrate }"/>
    <c:choose>
        <c:when test="${avrate == 0}">
            <c:out value="-"/>
        </c:when>
        <c:otherwise>
            <c:out value="${book.avrate }"/>
        </c:otherwise>
    </c:choose>
    </td>

    </tr>
</c:forEach>
</table>

<table width="1000" border="0" cellpadding="6" cellspacing="0">
    <tr>
        <td align="left">
            <s:message code="info.page"/> ${currentPage} <s:message code="info.from"/> ${totalPages}
        </td>

        <td align="center">

            <c:if test="${currentPage > 3}">
                <input type="button"
                onclick="window.location.href='${pageContext.request.contextPath}/globalranking/1'"
                value="1"/>&nbsp;&nbsp;&nbsp;
            </c:if>

            <c:if test="${currentPage > 2}">
                <input type="button"
                onclick="window.location.href='${pageContext.request.contextPath}/globalranking/${currentPage - 2}'"
                value="${currentPage - 2}"/>&nbsp;
            </c:if>

            <c:if test="${currentPage > 1}">
                <input type="button"
                onclick="window.location.href='${pageContext.request.contextPath}/globalranking/${currentPage - 1}'"
                value="${currentPage - 1}"/>&nbsp;
            </c:if>

            <input type="button" style=background-color:grey;color:black;width:20px;
            value="${currentPage}"/>&nbsp;

            <c:if test="${currentPage < totalPages}">
                <input type="button"
                onclick="window.location.href='${pageContext.request.contextPath}/globalranking/${currentPage + 1}'"
                value="${currentPage + 1}"/>&nbsp;
            </c:if>

            <c:if test="${currentPage+1 < totalPages}">
                <input type="button"
                onclick="window.location.href='${pageContext.request.contextPath}/globalranking/${currentPage + 2}'"
                value="${currentPage + 2}"/>&nbsp;&nbsp;&nbsp;
            </c:if>

            <c:if test="${currentPage+2 < totalPages}">
                <input type="button"
                onclick="window.location.href='${pageContext.request.contextPath}/globalranking/${totalPages}'"
                value="${totalPages}"/>&nbsp;
            </c:if>
        </td>
    </tr>
</table>

</body>
</html>


