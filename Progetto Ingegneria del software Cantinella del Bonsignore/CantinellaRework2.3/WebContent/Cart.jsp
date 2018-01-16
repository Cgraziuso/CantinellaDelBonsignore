<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
	import="java.util.*,it.Cantinella.model.bean.*, it.Cantinella.model.connection.*"%>	
	
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>IL TUO CARRELLO</title>
</head>
<body>
<%@ include file="Header.jsp"%>

<div class="main_cart_container">


	<header>
		<%
			if (session.getAttribute("userName") != null) {
		%>
		<h2>
			Il tuo carrello <i> <%=session.getAttribute("userName")%></i>
		</h2>
		<%
			} else {
		%>
		<h2>Effettua il login per accedere al carrello</h2>
		<%
			}
		%>
	</header>
	
	<div class="profile_det wine_det_profile">
	<table class="table">
		
			<tr>
				<th>Prodotto</th>
				<th>Nome</th>
				<th>Quantita'</th>
				<th>Prezzo</th>
			</tr>
		
		<%
			Cart cart = (Cart) session.getAttribute("cart");
			String tot = "0";
			float prz; 
			float prz2;
			if (cart != null) {
				List<Vino> wines = cart.getWinesList();
				for (Vino winebean : wines) {
		%>

		<!-- Carrello -->

		<tbody>
			<tr>
				<td><img class="img_description_cart" alt=""
					src="<%=winebean.getImmagine()%>" width="150px"></td>
				<td><%=winebean.getNome()%></td>
				<td><%=winebean.getQuantita()%></td>
				<td><%=winebean.getPrezzo()%> &euro;
					<form action="CartController" method="post" class="form_catalog">
						<input type="hidden" name="action" value="deleteCart"> 
						<input type="hidden" name="wine_id" value="<%=winebean.getIdVino()%>">
						<input type="hidden" name="quantita" value="<%=winebean.getQuantita()%>">

						<button type="submit" name="deleteToCart" class="wine_button"
							value="add">Remove</button>

					</form>
					</td>

			</tr>
		</tbody>


		<%
		 prz = Float.parseFloat(winebean.getPrezzo());
		prz2 = Float.parseFloat(tot);
		
		prz2 = prz2 + prz;
		
		tot= ""+ prz2;
				}
			} else {
		%>
		<tbody>
			<tr>
				<td>Nessun prodotto nel carrello</td>
			</tr>
		</tbody>

		<%
			}
		%>
		<tfoot>
			<tr>
				<td colspan="4" style="text-align: right;">Totale: <%=tot%>&euro;
				</td>
			</tr>

		</tfoot>

	</table>
	</div>
	<div class="back_div">
		<a class="back" href="./Catalogo.jsp"> <img class="back_img" alt="Catalog" src="image/icon/back_arrow.png">Torna al catalogo</a>
	</div>
	
	<div  style="float:right;">

	<%
	if(cart!=null)
	{
		if( cart.getWineNumber()>0)
		{
	%>
	
		<form action="">
			<img class="cart_logo" alt="cart" src="image/social_icon/cart_catalog.png" width="35px" height="35px">
			<a data-toggle="modal" data-target="#ordine-modal">Completa l'acquisto</a>
		</form>
		
		
	<%
		}else{
			
			
	%>
	 
	<%
		}
	}
	%>
	
	
	</div>
	
	<br>
	<br>

</div>

<!-- Inserisci indirizzo --> 
  <div class="modal fade" id="ordine-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	  <div class="modal-dialog">
			<div class="loginmodal-container">
			
			
					<h1>Concludi Ordine</h1><br>
					
				  <form name="ordine" onsubmit="return formValidation();" action="OrdineController" method="get">
						<input type="text" name="email" placeholder="email" id="email" >
						
						<input type="text" name="indirizzo" placeholder="indirizzo" id="indirizzo">
					
						<input type="text" name="zipcode" placeholder="zip code" id="zipcode">
					
						
					 	<input type="hidden" name="user" value="<%=session.getAttribute("userName")%>">
						<input type="hidden" name="totale" value="<%=tot%>">
						<input type="hidden" name="sort" value="acquisto">
					 <button type="submit" class="btn btn-default" >COMPLETA ORDINE CON PAYPAL</button>
					 <%
					 
					 session.setAttribute("carrello", cart);
					 %>
				  </form>
					
				  <div class="login-help">
					<img class="back_img" alt="Paypal" src="image/social_icon/paypal.png" width="20px">
				  </div>
				  
				  
			 </div>
		 </div>
  </div>




<%@ include file="footer.jsp"%>


<script type="text/javascript"> 
		
		
		

		
		
		function formValidation() {

			
			var indirizzo = document.ordine.indirizzo;
			
			var zipcode = document.ordine.zipcode;

			var email = document.ordine.email;
			
			alert ("ind "+ indirizzo.value.length + " zipcode "+ zipcode.value.length + " email " + email);

				if (indirizzoValidation(indirizzo, 50, 5)) 
				{ 
					if (allnumeric(zipcode))
					{
						if (ValidateEmail(email))
						{
							return true;
						}
					}
				}
				return false;
				}
	
			
		//Check indirizzo
		

	function indirizzoValidation(uid, mx, my) {
			alert (uid.value.length)
		var uid_len = uid.value.length;
		if (uid_len == 0 || uid_len >= 50 || uid_len <5) {
			alert("indirizzo errato, deve essere lungo massimo  " + mx
					+ "caratteri e minimo" + my + "caratteri");
			uid.focus();
			return false;
		}
		return true;
	}

	//Check Password

	function allnumeric(uzip) {

		var numbers = /^[0-9]{5}/;
		if (uzip.value.match(numbers)) {
			return true;
		} else {
			alert('lo zip code deve avere solo numeri ed una grandezza massima di 5 caratteri');
			uzip.focus();
			return false;
		}
	}

	//Check email

	function ValidateEmail(uemail) {
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if (uemail.value.match(mailformat)) {
			return true;
		} else {
			alert("Hai inserito una email errata");
			uemail.focus();
			return false;
		}
	}
	
	
	
</script>


</body>

</html>