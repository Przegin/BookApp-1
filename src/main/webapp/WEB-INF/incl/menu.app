<table width="100%" border="0" cellpadding="4" class="tableMenuBG" bgcolor="#5F0F40">
	<tr>
		<td align="left" width="900">
			<a href="/"><s:message code="menu.mainPage"/></a>&nbsp;
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a href="/admin"><s:message code="menu.adminPage"/></a>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_USER')">
				<a href="/books/1/title/asc?search="><s:message code="menu.ksiazki"/></a>
				<a href="/rates/1/title/asc"><s:message code="menu.oceny"/></a>
				<a href="/favorites/1/title/asc?search="><s:message code="menu.ulubione"/></a>
				<a href="/bookstoread/1/title/asc?search="><s:message code="menu.doPrzeczytania"/></a>
				<a href="/globalranking/1"><s:message code="menu.rankingGlobal"/></a>
			    <a href="/recommendation/1"><s:message code="menu.polecane"/></a>
			</sec:authorize>
		</td>
				
		<td align="right">
		<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
			<a href="/login"><s:message code="menu.login"/></a>&nbsp;
			<a href="/register"><s:message code="menu.register"/></a>&nbsp;
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<a href="/profil"><s:message code="menu.profil"/></a>
			<a href="/logout"><s:message code="menu.logout"/></a>
		</sec:authorize>
		</td>
	</tr>
</table>
			