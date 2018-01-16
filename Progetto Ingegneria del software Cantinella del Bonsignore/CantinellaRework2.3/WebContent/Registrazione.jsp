<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
input,
select,
textarea {
  width: 100%;
}

.formreg{
margin: 0% 30% 0% 30%;
}

.jumbotron {
      margin-bottom: 0;
    }
    
fieldeset{
	margin-bottom: 5%;

}
</style>



<title>Regisrazione</title>
</head>


<body>

<div class="jumbotron">
  <div class="container text-center" class="prova">
    <h1>Compila il modulo di registrazione utente</h1>      
    <p>Ed inizia subito ad acquistare i nostri vini</p>
  </div>
</div>

<%@ include file="Header.jsp"%>
	<form class="formreg" name="registration" onsubmit="return formValidation();"   action="RegistrationController" method="post">
	
	
	
   <div class="form-group">
   <label for="user">User</label>
   <input type="text"  class="form-control" id="user" name="user" placeholder="Inserisci il tuo user...">
<%

if(session.getAttribute("errore") != null)
{
	if( session.getAttribute("errore").equals("user"))
	{
		
%>
	<p style="color:red;"> Username già in uso</p>
	
<%
	}
}
%>	
   
  </div>
  <div class="form-group">
   <label for="password">Password</label>
   <input type="text"  class="form-control" id="password" name="password" placeholder="Inserisci la tua password...">
  </div>
  <div class="form-group">
   <label for="nome">Nome</label>
   <input type="text"  class="form-control" id="nome" name="nome" placeholder="Inserisci il nome...">
  </div>
  <div class="form-group">
   <label for="cognome">Cognome</label>
   <input type="text" class="form-control" id="cognome" name="cognome" placeholder="Inserisci il cognome...">
  </div>
  <div class="form-group">
   <label for="cf">Codice Fiscale</label>
   <input type="text" class="form-control" id="cf" name="cf" placeholder="Inserisci il tuo codice fiscale...">
  <%

  if(session.getAttribute("errore") != null)
  {
  	if( session.getAttribute("errore").equals("cf"))
  	{

%>
	<p style="color:red;"> Codice fiscale gia in uso</p>
	
<%
  	}
}
%>	
  </div>
  <div class="form-group">
   <label for="email">Email</label>
   <input type="email" class="form-control" id="email" name="email" placeholder="Inserisci l'indirizzo email...">
  </div>
   
   <div class="form-group">
   <span> <label style="font-weight: bold;"><input type="radio" name="rad" checked="checked" onclick="function_hidden()" value="client" >Sono Un Cliente </label> 
			<label style="font-weight: bold;"><input type="radio" name="rad" onclick="function_show()" value="seller" >Sono Un Venditore </label>
		</span>

		<div id="log" class="hidden">
			<br>
			<p class="label_reg">
				<label>Nome Azienda:<br> <input type="text" id="nome_azienda" name="nome_azienda" class="form-control"></label>
			</p>
			<p class="label_reg">
				<label>Partita IVA:<br> <input type="text" name="partita_iva" class="form-control"></label>
			</p>
			<br>
		</div>
	</div>
		
		
<button type="submit" class="btn btn-default">Invio</button>
<hr>
 
 
</form>

<%@ include file="footer.jsp"%>



<script type="text/javascript"> 
		function function_show() {
			var x = document.getElementById("log");

			x.className = "show";
		}
		function function_hidden() {
			var x = document.getElementById("log");

			x.className = "hidden";
		}
		
		
		function formValidation() {

			
			var user = document.registration.user;
			
			var pass = document.registration.password;
			var name = document.registration.nome;
			var surname = document.registration.cognome;

			var cf = document.registration.cf;

			var email = document.registration.email;
			var radio = document.registration.rad;
			
			
				if ( user_validation(user, 5, 30) && 
					pass_validation(pass) &&  
					allLetter(name) && 
					allLetter2(surname) && 
					alphanumeric(cf) && 
					validateEmail(email)) 
				{
					if(radio.value=="seller")
					{
						var nomeAzienda = document.registration.nome_azienda;
						var partita_iva = document.registration.partita_iva;
						if(NomeAzienda(nomeAzienda)&& controllaPIVA(partita_iva.value) )
							{
								return true;
							}
					}
						return true;
				}
	
			return false;
			}
		
		//Check Username
		function user_validation(user, min, max) {
			var letters = /^[0-9a-zA-Z]+$/;
			var user_length = user.value.length;
			
			if ( user_length == 0 || user_length >= max || user_length < min) 
			{

				alert("Username non valido / lunghezza compresa fra" + min
						+ "  " + max);
				document.getElementById("user").className= "invalidate" ;
				user.focus(); 
				return false;
				
			}else if(user.value.match(letters) )
					{
						document.getElementById("user").className= "validate" ;
						return true;
						
					}else{
							alert("Username non rispetta il formato, può avere solo lettere e numeri" );
							document.getElementById("user").className= "invalidate" ;
							user.focus(); 
							return false;
						}
					
			
		}
		
		//Check Password

		function pass_validation(pass) {
			
			var passlen = pass.value.length;  
			if ( passlen==0 || passlen<6 || passlen>30 )  
			{  
					alert("La password non puo' essere vuota / la lunghezza deve essere compresa fra 6 e 30");  
					document.getElementById("password").className= "invalidate" ;
					return false; 		 
			}  
			document.getElementById("password").className= "validate" ;
			return true;  
		}
		
		//Check name

		function allLetter(name) {
			var letters = /^[A-Za-z]+$/;
			var name_length = name.value.length;
			if ( (name.value.match(letters)) && ( (name_length>2) && (name_length<30))  ) {
				
					document.getElementById("nome").className= "validate" ;
					return true;
				
				
			} else {
				document.getElementById("nome").className= "invalidate" ;
				alert("Il nome non puo' essere vuoto/  la lunghezza deve essere compresa fra 2 e 30 e composta sole lettere ");
				name.focus();
				return false;
			}
		}

		//Check surname
		function allLetter2(surname) {
			var letters = /^[A-Za-z]+$/;
			var surname_length = surname.value.length;
			if ( (surname.value.match(letters)) && ( (surname_length>2) && (surname_length<30) && (surname.value != "  ")  )  ) {
				
					document.getElementById("cognome").className= "validate" ;
					return true;
				
				
			}else {
				document.getElementById("cognome").className= "invalidate" ;
				alert("Il cognome non puo' essere vuoto/  la lunghezza deve essere compresa fra 2 e 30 e composta sole lettere");
				
				cognome.focus();
				return false;
			}
		}
		
	
		//Check cf
		function alphanumeric(cf) {
			var letters = /^[0-9a-zA-Z]+$/;
			if (cf.value.match(letters) && cf.value.length == 16) {
				document.getElementById("cf").className= "validate" ;
				return true;
			} else {
				document.getElementById("cf").className= "invalidate" ;
				alert("il codice fiscale deve contenere 16 caratteri alfanumerici");
			
				cf.focus();
				return false;
			}
		}
		

		function validateEmail(email) {
			var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			if (email.value.match(mailformat)) {
				document.getElementById("email").className= "validate" ;
				return true;
			} else {
				document.getElementById("email").className= "invalidate" ;
				alert("hai inserito una email non valida");
			
				email.focus();
				return false;
			}
		}

		//Check email
		function NomeAzienda(nomeAzienda) {
			var letters = /^[A-Za-z]+$/;
			var name_length = nomeAzienda.value.length;
			if ((name_length>2) && (name_length<30) ) {
				document.getElementById("nome_azienda").className= "validate" ;
					return true;
				
				
			} else {
				document.getElementById("nome_azienda").className= "invalidate" ;
				alert("Il nome dell'azienda non puo' essere vuoto/  la lunghezza deve essere compresa fra 2 e 30  ");
				return false;
			}
		}
		
		//Check P.IVA
		function controllaPIVA( piva )
		{
		    if ( piva.length == 11 )
		    {
		        var codiceUFFICIOiva = parseInt( piva.substr( 0, 3 ) ) ;
		        if ( codiceUFFICIOiva <= 0 || codiceUFFICIOiva > 121 ) {
		        	
		        	return false ;
		        }
		    
		        var X = 0 ;
		        var Y = 0 ;
		        var Z = 0 ;
		    
		        // cifre posto dispari ... ma per un array indicizzato a zero, la prima cifra ha indice zero ... appunto !
		        X += parseInt( piva.charAt(0) ) ;
		        X += parseInt( piva.charAt(2) ) ;
		        X += parseInt( piva.charAt(4) ) ;
		        X += parseInt( piva.charAt(6) ) ;
		        X += parseInt( piva.charAt(8) ) ;

		        // cifre posto pari ... ma per un array indicizzato a zero, la prima cifra ha indice uno ...
		        Y += 2 * parseInt( piva.charAt(1) ) ;    if ( parseInt( piva.charAt(1) ) >= 5 ) Z++ ;
		        Y += 2 * parseInt( piva.charAt(3) ) ;    if ( parseInt( piva.charAt(3) ) >= 5 ) Z++ ;
		        Y += 2 * parseInt( piva.charAt(5) ) ;    if ( parseInt( piva.charAt(5) ) >= 5 ) Z++ ;
		        Y += 2 * parseInt( piva.charAt(7) ) ;    if ( parseInt( piva.charAt(7) ) >= 5 ) Z++ ;
		        Y += 2 * parseInt( piva.charAt(9) ) ;    if ( parseInt( piva.charAt(9) ) >= 5 ) Z++ ;
		        
		        var T = ( X + Y + Z ) % 10 ;

		        var C = ( 10 - T ) % 10 ;

		        return ( piva.charAt( piva.length - 1 ) == C ) ? true : false ;
		    }
		    else
		    	{
		    	alert("partita iva non valida, la lunghezza deve essere di 11 caratteri");
		    	return false ;
		    	}
		}
		
</script>




</body>
</html>