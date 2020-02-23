<sec:authorize access="hasRole('ROLE_ADMIN')">
	<table width="100%" border="0" cellpadding="8" cellspacing="4" class="tableMenuBg" bgcolor="#5F0F40">
		<tr>
			<td align="left">
				<a href="/admin/users/1?search="><s:message code="menu.users"/></a>&nbsp;&nbsp;
				<a href="/admin/books/1/title/asc?search="><s:message code="admin.ksiazki"/></a>&nbsp;&nbsp;
				<a href="/admin/addbook"><s:message code="admin.add"/></a>&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</sec:authorize>