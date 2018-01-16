<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>proviamo</title>
<link rel="stylesheet" href="css/tabDesc.css" type="text/css" />
</head>
<body>
<div id="fuori">


<h1 id = "ultraId" >Info Vino</h1>
	<table class="tab">
		<tr class ="prime">
			<td>NOME</td>
			
			<td>ANNATA</td>
			<td>PREZZO</td>
			

		</tr>
	
		<tr>
			<td><%=(String)session.getAttribute("wineName")%></td>
			
			<td><%=(String)session.getAttribute("wineYear")%></td>
			<td><%=(String)session.getAttribute("winePrice")%></td>
			

		</tr>

	</table >
	
		<table class="tab">
		<tr class ="prime">
			<td>Descrizione</td>
			

		</tr>
	
		<tr>
			<td><%=(String)session.getAttribute("descriz")%></td>
			

		</tr>

	</table>
	<div class= "butty">
	<button class="butt" id="backBotton" onClick="pressButton()">Back Catalog</button>
	</div>

</div>
</body>
</html>