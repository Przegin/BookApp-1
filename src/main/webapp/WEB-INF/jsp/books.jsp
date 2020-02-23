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

        <form id="searchForm" align="center" action="/books/1/title/asc?search=${search}" method="GET">
            <br>
                <label for="search">Wyszukaj: </label>
                <input type="text" id="search" name="search">
                <input type="submit" value="Szukaj"/>
            <br>
        </form>

        <table border="5F0F40" cellpadding="6" cellspacing="2" align="center">
            <tr>

                <td width="90" align="center">
                    <b><s:message code="book.cover"/></b>
                </td>

                <td width="70" align="center">
                    <b><s:message code="book.title"/></b>

                    <a href="/books/1/title/asc?search=${search}">
                    <img src="/resources/images/up.png" width="10" height="10"/>
                    </a>

                    <a href="/books/1/title/desc?search=${search}">
                    <img src="/resources/images/down.png" width="10" height="10"/>
                    </a>
                </td>

                <td width="100" align="center">
                    <b><s:message code="book.author"/></b>

                    <a href="/books/1/author/asc?search=${search}">
                    <img src="/resources/images/up.png" width="10" height="10"/>
                    </a>

                    <a href="/books/1/author/desc?search=${search}">
                    <img src="/resources/images/down.png" width="10" height="10"/>
                    </a>
                </td>

                <td width="50" align="center">
                    <b><s:message code="book.pages"/></b>

                    <a href="/books/1/pages/asc?search=${search}">
                    <img src="/resources/images/up.png" width="10" height="10"/>
                    </a>

                    <a href="/books/1/pages/desc?search=${search}">
                    <img src="/resources/images/down.png" width="10" height="10"/>
                    </a>
                </td>

                <td width="300" align="center">
                    <b><s:message code="book.rate"/></b>

                    <a href="/books/1/rate/asc?search=${search}">
                    <img src="/resources/images/up.png" width="10" height="10"/>
                    </a>

                    <a href="/books/1/rate/desc?search=${search}">
                    <img src="/resources/images/down.png" width="10" height="10"/>
                    </a>
                </td>

                <td width="100" align="center">
                    <b><s:message code="book.isfav"/></b>

                    <a href="/books/1/favorite/asc?search=${search}">
                    <img src="/resources/images/up.png" width="10" height="10"/>
                    </a>

                    <a href="/books/1/favorite/desc?search=${search}">
                    <img src="/resources/images/down.png" width="10" height="10"/>
                    </a>
                </td>

                <td width="120" align="center">
                    <b><s:message code="book.toRead"/></b>

                    <a href="/books/1/toread/asc?search=${search}">
                    <img src="/resources/images/up.png" width="10" height="10"/>
                    </a>

                    <a href="/books/1/toread/desc?search=${search}">
                    <img src="/resources/images/down.png" width="10" height="10"/>
                    </a>
                </td>

                <td width="85" align="center">
                    <b><s:message code="book.srocen"/></b>

                    <a href="/books/1/avr/asc?search=${search}">
                    <img src="/resources/images/up.png" width="10" height="10"/>
                    </a>

                    <a href="/books/1/avr/desc?search=${search}">
                    <img src="/resources/images/down.png" width="10" height="10"/>
                    </a>
                </td>

            </tr>
            <c:forEach var="book" items="${booksList }">
                <tr>
                    <td align="center" width="70" height="110" align="left"><img src="data:image/jpg;base64,${book.coverimage}" width="70" height="110"/></td>
                    <td align="left"><c:out value="${book.title }"/></td>
                    <td align="left"><c:out value="${book.author }"/></td>
                    <td align="center"><c:out value="${book.pages }"/></td>
                    <td align="center">
                        <c:set var="rate" value="${book.userrate }"/>

                        <c:choose>
                            <c:when test="${rate > 0}">
                                <a href="/addrate/1/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/addrate/1/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/Star.jpg" width="20" height="20"/>
                                </a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${rate > 1}">
                                <a href="/addrate/2/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/addrate/2/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/Star.jpg" width="20" height="20"/>
                                </a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${rate > 2}">
                                <a href="/addrate/3/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/addrate/3/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/Star.jpg" width="20" height="20"/>
                                </a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${rate > 3}">
                                <a href="/addrate/4/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/addrate/4/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/Star.jpg" width="20" height="20"/>
                                </a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${rate > 4}">
                                <a href="/addrate/5/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/addrate/5/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/Star.jpg" width="20" height="20"/>
                                </a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${rate > 5}">
                                <a href="/addrate/6/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/addrate/6/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/Star.jpg" width="20" height="20"/>
                                </a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${rate > 6}">
                                <a href="/addrate/7/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/addrate/7/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/Star.jpg" width="20" height="20"/>
                                </a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${rate > 7}">
                                <a href="/addrate/8/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/addrate/8/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/Star.jpg" width="20" height="20"/>
                                </a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${rate > 8}">
                                <a href="/addrate/9/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/addrate/9/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/Star.jpg" width="20" height="20"/>
                                </a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${rate > 9}">
                                <a href="/addrate/10/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/addrate/10/book/${book.id }/${currentPage}/${sort}/${order}?search=${search}">
                                <img src="/resources/images/Star.jpg" width="20" height="20"/>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td align="center">
                        <c:set var="isFavorite" value="${book.isfav }"/>
                        <c:choose>
                            <c:when test="${isFavorite == true}">
                                <a href="/favorite/${book.id}/books/${currentPage}/${sort}/${order}?search=${search}" >
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/favorite/${book.id}/books/${currentPage}/${sort}/${order}?search=${search}" >
                                <img src="/resources/images/Star.jpg" width="20" height="20"/>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td align="center">
                        <c:set var="isToRead" value="${book.istoread }"/>
                        <c:choose>
                            <c:when test="${isToRead == true}">
                                <a href="/toread/${book.id}/books/${currentPage}/${sort}/${order}?search=${search}" >
                                <img src="/resources/images/fillStar.jpg" width="20" height="20"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/toread/${book.id}/books/${currentPage}/${sort}/${order}?search=${search}" >
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
                        onclick="window.location.href='${pageContext.request.contextPath}/books/1/${sort}/${order}?search=${search}'"
                        value="1"/>&nbsp;&nbsp;&nbsp;
                    </c:if>

                    <c:if test="${currentPage > 2}">
                        <input type="button"
                        onclick="window.location.href='${pageContext.request.contextPath}/books/${currentPage - 2}/${sort}/${order}?search=${search}'"
                        value="${currentPage - 2}"/>&nbsp;
                    </c:if>

                    <c:if test="${currentPage > 1}">
                        <input type="button"
                        onclick="window.location.href='${pageContext.request.contextPath}/books/${currentPage - 1}/${sort}/${order}?search=${search}'"
                        value="${currentPage - 1}"/>&nbsp;
                    </c:if>

                    <input type="button" style=background-color:grey;color:black;width:20px;
                   value="${currentPage}"/>&nbsp;

                    <c:if test="${currentPage < totalPages}">
                        <input type="button"
                        onclick="window.location.href='${pageContext.request.contextPath}/books/${currentPage + 1}/${sort}/${order}?search=${search}'"
                        value="${currentPage + 1}"/>&nbsp;
                    </c:if>

                    <c:if test="${currentPage+1 < totalPages}">
                        <input type="button"
                        onclick="window.location.href='${pageContext.request.contextPath}/books/${currentPage + 2}/${sort}/${order}?search=${search}'"
                        value="${currentPage + 2}"/>&nbsp;&nbsp;&nbsp;
                    </c:if>

                    <c:if test="${currentPage+2 < totalPages}">
                        <input type="button"
                        onclick="window.location.href='${pageContext.request.contextPath}/books/${totalPages}/${sort}/${order}?search=${search}'"
                        value="${totalPages}"/>&nbsp;
                    </c:if>
                </td>
            </tr>
        </table>
    </body>
</html>


