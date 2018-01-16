
 <%@ page contentType="text/html; charset=ISO-8859-1"
	import="java.util.*,it.Cantinella.model.bean.*,it.Cantinella.model.control.*" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <title>Ordini effettuati</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 10000px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
  </style>
</head>
<body>
<%

	Collection<?> ordini = (Collection<?>) request.getAttribute("adminOrdini");

	if (ordini == null) {
		response.sendRedirect("./OrdineController?sort=admin");
		return;
	}

%>


<%@ include file="Header.jsp"%>
  
  
  
 
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-1 sidenav">
    
	 
    </div>
    <div class="col-sm-10 text-left"> 
      <h1>Welcome</h1>
      <p>IN QUESTA SEZIONE PUOI VEDERE GLI ORDINI EFFETTUATI SUL SITO</p>
      <hr>
      <div class="profile_det wine_det_profile">

		<%
		float Guadagno = 0.0f;
		
		%>

		<table class="table">
		<tr>
			<th>Id Ordine</th>
			<th>Username Cliente       </th>
			<th>Prodotti </th>
			<th>Totale </th>
			<th>Totale guadagno sito  </th>
			<th>Indirizzo Consegna   </th>
			<th>Email   </th>
			<th>Zip Code  </th>
		</tr>
      
      <%
			if (ordini != null && ordini.size() != 0) {
				Iterator<?> iter = ordini.iterator();
				while (iter.hasNext()) {
					Ordine ordine = (Ordine) iter.next();
					
			%>
	
	
		
			<tr>
				<td><p><%=ordine.getIdOrdine() %> </p></td>
				<td><p><%=ordine.getUsernameCliente()%> </p></td>
				<td><p><%=ordine.getDescrizione()%>      </p></td>
				<td><p><%=ordine.getTotale()%>    </p>  </td>
				<td><p><%=ordine.getPercentuale()%>    </p>  </td>
				<td><p><%=ordine.getIndirizzo()%>    </p>  </td>
				<td><p><%=ordine.getEmailTracciamento()%>    </p>  </td>
				<td><p><%=ordine.getZipCode()%>   </p>  </td>
				
			
			</tr>
			<%
			Guadagno= Guadagno + Float.parseFloat(ordine.getPercentuale());
			
				}
			}
			%>

		</table>
		<p> il guadagno del uto sito è di <%= Guadagno %></p>
	</div>

    </div>
    <div class="col-sm-1 sidenav">
      
    </div>
  </div>
</div>

<%@ include file="footer.jsp"%>

<script>




</script>




</body>
</html>