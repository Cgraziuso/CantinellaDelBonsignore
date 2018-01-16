<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="it.Cantinella.model.connection.DMConnectionPool"%>
<%@ page import="it.Cantinella.model.DAO.CancellUserDAO"%>
<%@ page errorPage="ErrorPage.jsp"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    .navbar-brand {
      margin-bottom: 0;
      border-radius: 0;
      text-transform: uppercase;
      font-size: xx-large;
      font-style: italic;
    }
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
    
  .carousel-inner img {
      width: 100%; /* Set width to 100% */
      margin: auto;
      min-height:200px;
      max-height: 800px;
  }
  
  .carousel-caption{
  color:
  
  }

  /* Hide the carousel text when the screen is less than 600 pixels wide */
  @media (max-width: 600px) {
    .carousel-caption {
      display: none; 
    }
  }
  </style>









<link href="css/cancellUser.css" rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; cherset=UTF-8" />
<title>Insert title here</title>
</head>
<body id="boody">
<%@ include file="Header.jsp"%>
	<h1>SELEZIONA UTENTE DA ELIMINARE</h1>
	<blockquote>
		
	<table id="tub" >
	<tr>
		<td><button class="top" onclick="window.location.href='/CantinellaRework2.3/Home.jsp'">Back</button></td>
			<td>Codice Fiscale</td>
			<td >Nome</td>
			<td >Cognome</td>
			<td	>user</td>
			<td >password</td>
			<td >email</td>
			<td	>nomeAzienda</td>
			<td >piva</td>
		</tr>
	</table>
	
	</blockquote>
	<h3 id="h3">OFF</h3>
	<section>
		<div id="divi">
			<table id="tab">

				<%
					Connection conny = DMConnectionPool.getConnection();
					CancellUserDAO use = new CancellUserDAO(conny);
					ResultSet res = use.ListaUser();
					int zero = 0;
					while (res.next()) {
						String CF = res.getString("cf");
						String nome = res.getString("nome");
						String cognome = res.getString("cognome");
						String username = res.getString("user");
						String password = res.getString("password");
						String email = res.getString("email");
						String nomeAzienda = res.getString("nomeAzienda");
						String partitaIva = res.getString("piva");
						
					if(!username.equals("Amministratore"))
					{
						out.print("<tr ><td>	<button class=" + "top" + " type=" + "button" + "  onclick=" + "funzione(" + zero
								+ ",'tab')" + " >Delete</button></td><td><span id=" + zero + " class=" + "fiscale" + ">" + CF
								+ "</span></td><td class = " + "nome" + ">" + nome 
								+ "</td><td class = " + "cognome" + ">"+ cognome 
								+ "</td><td class = " + "username" + ">"+ username
								+ "</td><td class = " + "password" + ">"+ password 
								+ "</td><td class = " + "email" + ">"+ email
								+ "</td><td class = " + "nomeAzienda" + ">"+ nomeAzienda
								+ "</td><td class = " + "partitaIva" + ">"+ partitaIva
								+ "</td></tr>");
						zero++;
					}
					}

					conny.close();
				%>

			</table>
			
		</div>
		
	</section>
	<blockquote id="blok"></blockquote>
	<script type="text/javascript">
		function funzione(uno, tab) {

			var h3 = document.getElementById("h3");

			if (h3.innerHTML == "OFF") {
				var tab = document.getElementById(tab);

				var celle = tab.getElementsByTagName('span');
				var cof = celle[uno].innerHTML;

				String(cof);
				console.log(cof);

				var my_form = document.createElement('FORM');

				my_form.action = "DelUserController";
				my_form.id = "wow";
				my_form.name = 'myForm';
				my_form.method = 'POST';

				var inpuy = document.createElement("input");
				inpuy.setAttribute("type", "text");
				inpuy.setAttribute("name", "inputCF");

				inpuy.setAttribute("value", celle[uno].innerHTML);

				var buttoy = document.createElement("input");
				buttoy.setAttribute("type", "submit");
				buttoy.setAttribute("class", "top");
				buttoy.setAttribute("value", "Confirm Delete");

				var buttoy2 = document.createElement("input");
				buttoy2.setAttribute("type", "button");
				buttoy2.setAttribute("class", "top");
				buttoy2.setAttribute("value", "Annull");
				buttoy2.setAttribute("onclick", "annull()");

				var bok = document.getElementById("blok");

				bok.appendChild(my_form);

				my_form.appendChild(inpuy);
				my_form.appendChild(buttoy);

				my_form.appendChild(buttoy2);
				h3.innerHTML = "ON";
			}

		}

		function annull() {

			var h3 = document.getElementById("h3");
			h3.innerHTML = "OFF";

			var frem = document.getElementById("blok");

			frem.removeChild(frem.childNodes[0]);

		}
	</script>

<%@ include file="footer.jsp"%>
</body>
</html>