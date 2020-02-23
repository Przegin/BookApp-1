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
<title><s:message code="menu.register"/></title>								<!-- rejestracja, wyświetlanie na stronie inf -->
</head>
<body>
<%@include file="/WEB-INF/incl/menu.app" %>								<!-- menu importjemy do każdej strony, razem z tagami -->

<h2><s:message code="menu.register"/></h2>

<p align="center">
		<c:out value="${message }" />									<!-- tag c do wyświetlania zawartości tego co wraca z kontrolera, out to jedno z poleceń zawartych w jstl core -->
</p>																	<!-- sam formularz -->

	<sf:form id="booksForm" action="addnewbook" modelAttribute="book"
		enctype="multipart/form-data" method="POST">					<!-- sf, tagi forlmularzy springowych-->
																		<!-- akcje dla formularza, modelAtribute - przekazuje na jakim modelu formularza pracujemy - user -->
		<table width="500" border="0" cellpadding="4" cellspacing="1"
			align="center">

			<tr>
				<td width="130" align="right" ><s:message code="book.cover"/></td>
				<td width="270" align="left"><sf:input path="file" id="file" name="file" type="file"
													   accept="image/*"  size="28"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="file"/></font></td>		<!-- sf errors, walidator będzie szukał tych pól z tą samą nazwą, wróci do tego pola i wyświetli komunikat -->
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="book.title"/></td>
				<td width="270" align="left"><sf:input path="title" id="title" name="title"
						size="28"  /></td>							<!-- name odpowiada temu, co mamy w klasie user itd -->
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="title"/></font></td>		<!-- sf errors, walidator będzie szukał tych pól z tą samą nazwą, wróci do tego pola i wyświetli komunikat -->
			</tr>

			<tr>
				<td width="130" align="right"><s:message code="book.author"/></td>
				<td width="270" align="left"><sf:input path="author" id="author" name="author"
						size="28" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="author"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="book.pages"/></td>
				<td width="270" align="left"><sf:input path="temppages" id="temppages" name="temppages" size="28"  /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="temppages"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="book.csex"/></td>
					<td width="270" align="left">
					<select path="csex" id="csex" name="csex">
						<option value="M">Mężczyzna</option>
						<option value="K">Kobieta</option>
						<option value="N">Nieznany</option>
						<option value="G">Grupa</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="csex"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="book.victimnr"/></td>
				<td width="270" align="left">
					<select path="victimnr" id="victimnr" name="victimnr">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2-4">Od 2 do 4</option>
						<option value="5<">5 lub więcej</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="victimnr"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="book.motive"/></td>
				<td width="270" align="left">
					<select path="motive" id="motive" name="motive">
						<option value="miłość">Miłość</option>
						<option value="pieniądze">Pieniądze</option>
						<option value="zemsta">Zemsta</option>
						<option value="zazdrość<">Zazdrość</option>
						<option value="strach<">Strach</option>
						<option value="szantaż<">Szantaż</option>
						<option value="nieznany<">Nieznany</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="motive"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="book.crimetype"/></td>
				<td width="270" align="left">
					<select path="crimetype" id="crimetype" name="crimetype">
						<option value="nóż">Nóż</option>
						<option value="pistolet">Pistolet</option>
						<option value="trucizna">Trucizna</option>
						<option value="narzędzie<">Narzędzie</option>
						<option value="porwanie<">Porwanie</option>
						<option value="kradzież<">Kradzież</option>
						<option value="saute<">Saute</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="crimetype"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="book.crimescene"/></td>
				<td width="270" align="left">
					<select path="crimescene" id="crimescene" name="crimescene">
						<option value="otwarta">Otwarta</option>
						<option value="zamknięta">Zamknięta</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="crimescene"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="book.invexecutor"/></td>
				<td width="270" align="left">
					<select path="invexecutor" id="invexecutor" name="invexecutor">
						<option value="detektyw">Detektyw</option>
						<option value="policja">Jednostka policyjna lub inspektor</option>
						<option value="wysłannik">Wysłannik</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="invexecutor"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="book.dsex"/></td>
				<td width="270" align="left">
					<select path="dsex" id="dsex" name="dsex">
						<option value="M">Mężczyzna</option>
						<option value="K">Kobieta</option>
						<option value="P">Para detektywów</option>
						<option value="G">Grupa osób lub jednostka</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="dsex"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="book.storybcg"/></td>
				<td width="270" align="left">
					<select path="storybcg" id="storybcg" name="storybcg">
						<option value="miasto">Miasto</option>
						<option value="domostwo">Wieś lub posiadłość</option>
						<option value="podróż">W podróży</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="storybcg"/></font></td>
			</tr>

			<tr>
				<td width="130" align="right" ><s:message code="book.vibe"/></td>
				<td width="270" align="left">
					<select path="vibe" id="vibe" name="vibe">
						<option value="przygodowy">Przygodowy</option>
						<option value="szpiegowski">Szpiegowski</option>
						<option value="sielanka">Sielanka</option>
						<option value="noir">Noir</option>
						<option value="slasher">Slasher</option>
						<option value="sprzed laty">Sprzed laty</option>
						<option value="nadprzyrodzony">Nadprzyrodzony</option>
						<option value="historyczny">Historyczny</option>
						<option value="miasto">Miejski</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><font color="red"><sf:errors path="vibe"/></font></td>
			</tr>

			<tr>
				<td colspan="2" align="center" bgcolor="#fff">
					<input type="submit" value="<s:message code="button.add"/>" class="formbutton"/>			<!-- przyciski do akcji, u nas będą umieszczane w kontrolerze -->
					<input type="button" value="<s:message code="button.cancel"/>" class="formbutton" 
						onclick="window.location.href='${pageContext.request.contextPath}/'"/>						<!-- przekierowanie do str głównej op cancel -->
				</td>
			</tr>

		</table>

	</sf:form>

</body>
</html>