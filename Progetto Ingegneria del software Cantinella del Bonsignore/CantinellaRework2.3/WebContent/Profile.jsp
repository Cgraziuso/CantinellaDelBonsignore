
 <%@ page contentType="text/html; charset=ISO-8859-1"
	import="java.util.*,it.Cantinella.model.bean.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <title>Profile</title>
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
 
 	
	Utente client = (Utente) request.getAttribute("client");
	Collection<?> productsCF = (Collection<?>) request.getAttribute("productsCF");

	if (client == null) {
		response.sendRedirect("./ProfileController");
		return;
	}
%>

<%@ include file="Header.jsp"%>
  
  
  
 
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
    
    <%
		if (client.isSeller()) {
	%>
    
      <form name="AddArticolo" onsubmit="return Validazione();"   action="AddDelModController?azione=aggiungi" method="post">
  		
  			 <div class="panel-footer">
  				 <label for="pwd"> Inserire Articolo</label>
  				 <p>Compila la form  </p>
  			</div>
  			
    		<label for="nome">Nome:</label>
    		<input type="text" class="form-control" id="nome" name="nome" required="required">
  		
  			<label for="descrizione">Descrizione:</label>
    		<input type="text" class="form-control" id="descrizione" name="descrizione" required="required">
  		
   		 	<label for="anno">Anno:</label>
    		<input type="number" class="form-control" id="anno" name="anno" required>
    		
    		<label for="prezzo">Prezzo:</label>
    		<input type="number" step="0.01" class="form-control" id="prezzo" name="prezzo" required="required">
    		
    		<label for="tipo">Tipo:</label>
    		<%-- <input type="text" class="form-control" id="tipo" name="tipo">--%>
    		
    		<select id="tipo" name="tipo"  class="selectpicker show-menu-arrow form-control" required="required">
      		 <option selected>rosso</option>
      		 <option>bianco</option>
   		    </select>
    		
    		<label for="immagine">Url Immaggine:</label>
    		<input type="text" class="form-control" id="immagine" name="immagine" required="required">
    		
    		<label for="quantita">Quantita':</label>
    		<input type="number" class="form-control" id="quantita" name="quantita" required="required">
 
 		<input type="hidden" name="cf" value="<%=client.getCf()%>">
  		<button type="submit" class="btn btn-default">Aggiungi</button>
	 </form>
	 
	 <%
		}
	%>
	 
    </div>
    <div class="col-sm-8 text-left"> 
      <h1>Welcome</h1>
      <p>IN QUESTA SEZIONE PUOI VEDERE I TUOI DATI</p>
      <hr>
      <div class="profile_det">
		<table class="table">
			<tr>
				<td>NOME e COGNOME</td>
				<td><%=client.getNome()%> <%=client.getCognome()%></td>
			</tr>

			<tr>
				<td>EMAIL</td>
				<td><%=client.getEmail()%></td>
			</tr>
			<tr>
				<td>CF</td>
				<td><%=client.getCf()%></td>
			</tr>
			<tr>
				<td>Nome Azienda</td>
				<td><%=client.getNomeAzienda()%></td>
			</tr>
			<tr>
				<td>P.IVA</td>
				<td><%=client.getPiva()%></td>
			</tr>
		</table>
		<hr>
	</div>
	<%
		if (!productsCF.isEmpty()) {
	%>
	
	<div class="profile_det wine_det_profile">

		<h1 style="text-align: center">In basso sono riportati i tuoi prodotti</h1>
		<table class="table">
		<tr>
			<th>Foto vino</th>
			<th>Nome         </th>
			<th>Annata       </th>
			<th>Quantita     </th>
			<th>Prezzo      </th>
			<th>Opzioni      </th>
		</tr>
			<%
				Iterator<?> iter = productsCF.iterator();
					
					while (iter.hasNext()) {
						Vino wine = (Vino) iter.next();
						String wineDescription = wine.getDescrizione();
			%>
			
			<tr>
				<td><img class="wine_image" alt="<%=wine.getNome()%>"
					src="<%=wine.getImmagine()%>" height="300px" width="300"></td>
				<td><p><%=wine.getNome()%>      </td>
				<td><p><%=wine.getAnno()%>      </td>
				<td><%=wine.getQuantita()%>    </td>
				<td><%=wine.getPrezzo()%>    </td>
				
				<td>	
					<form action="./AddDelModController" >
						<input type="hidden" name="azione" value="elimina">
						<input type ="hidden" name="idwine" value="<%=wine.getIdVino()%>"> 
						<button  id="buttonLogin" type="submit">Rimuovi</button>
					</form> 
					<hr>
					
					
					<form name="nmodifica"   action="./AddDelModController"  >
						<label >Nuovo Prezzo (<100000 e massimo 7 caratteri):</label>
						<input type="number" step="0.01"class="form-control" id="prezzo" name="prezzo">
						
    					<label>Nuova Quantita' ( massimo 6 caratteri):</label>
    					<input type="number" class="form-control" id="quantita" name="quantita">
    					
						<input type ="hidden" name="idwine" value="<%=wine.getIdVino()%>"> 
						<input type="hidden" name="azione" value="modifica">
						
						<button  id="buttonLogin" type="submit">Modifica</button>
					</form> 
					
				</td>
			
			</tr>
			<%
				}
			%>

		</table>
	</div>
	<%
		}
	%>
	
    </div>
    <div class="col-sm-2 sidenav">
      <%-- <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div>--%>
    </div>
  </div>
</div>

<%@ include file="footer.jsp"%>

<script type="text/javascript">

function Validazione()
{	
	var tipo = document.AddArticolo.tipo;
	var name = document.AddArticolo.nome;
	var descri = document.AddArticolo.descrizione;
	var anno = document.AddArticolo.anno;
	var prezzo = document.AddArticolo.prezzo;
	var url = document.AddArticolo.immagine;
	var quant = document.AddArticolo.quantita;
	var letters = /^[0-9a-zA-Z]+$/;
	var letters2 = /^[0-9]/;
	var letters3 = /^[0-9a-zA-Z.\s,:- ]+$/;
	var letters4 = /^[0-9.,]+$/;
	alert("opzione="+ name.value.length + " opzione="+tipo.value.length  + " descr="+descri.value.length  + " anno="+anno.value.length  + " prezzo="+prezzo.value.length  + " url="+url.value.length  + " quant="+quant.value.length);
	
	//NOME VINO
	if ((name == "") || name.value.length<4 || name.value.length > 30) {
		   alert("Il campo Nome non puo' essere vuoto/ la lunghezza deve essere compresa ra 6 e 30 caratteri.");
		   document.modulo.name.focus();
		   return false;
		}else if( ! (name.value.match(letters)) )
			{
			 alert("Il campo Nome non rispetta il formato, solo numeri e lettere.");
			 return false;
			}
	
	
	//DESCRIZIONE VINO
	if (descri.value.length<30 || descri.value.length>500 || (descri == "") ) {
			alert("La lunghezza del campo descrizione deve essere almeno 30 caratteri massimo 500." + descri.value );
			document.AddArticolo.descrizione.focus();
			return false;
		}else if( ! (descri.value.match(letters3)) )
			{
			 alert("Il campo descrizione non rispetta il formato, solo lettere e numeri.");
			 return false;
			}
	
	//ANNO VINO	
	if (anno.value.length!=4 || anno.value >2018 ) {
	   alert("La lunghezza del campo anno deve essere 4 caratteri/ non superiore al 2018." + anno.value );
	   document.AddArticolo.prezzo.focus();
	   return false;
	}else if( ! (anno.value.match(letters2)) )
		{
		 alert("Il campo anno non rispetta il formato, solo numeri.");
		 return false;
		}
	
	if (prezzo.value==0 ||  (prezzo== "") || prezzo.value.length>7 || prezzo.value>100000 ) {
				alert("Il campo prezzo deve essere un numero maggiore di 1 e minore di 7 numeri (<100000 euro) ." + prezzo.value );
				document.AddArticolo.prezzo.focus();
				return false;
			}else if( ! (prezzo.value.match(letters4)) )
			{
				 alert("Il campo prezzo non rispetta il formato, solo numeri e virgola.");
				 return false;
				}
	if (  (url== "") || url.value.length<9 || url.value.length>300 ) {
			alert("Il campo immagine deve contenere un url di amlmeno 9 caratteri massimo 300." + url.value );
			document.AddArticolo.immagine.focus();
			return false;
			}
	if (quant.value==0 ||  (quant== "") || quant.value.length<1 ||  quant.value.length>6) {
					alert("Il campo quantita' deve essere maggiore di 1 e minore di 6 numeri ." + quant.value );
					document.AddArticolo.quantita.focus();
					return false;
	}else if( ! (quant.value.match(letters2)) )
		{
			alert("Il campo quantita' non rispetta il formato, solo numeri.");
			return false;
		}
	
	
}

</script>





</body>
</html>
