<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,it.Cantinella.model.bean.*, it.Cantinella.model.control.*"%>


<!DOCTYPE html >
<html lang="it">
<head>
<title>CATALOGO</title>
<link rel="stylesheet" href="catalog.css" type="text/css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
/* Remove the navbar's default rounded borders and increase the bottom margin */
.navbar {
	margin-bottom: 50px;
	border-radius: 0;
}

/* Remove the jumbotron's default bottom margin */
.jumbotron {
	margin-bottom: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 25px;
}

</style>
</head>


<body> 

	<div class="jumbotron">
		<div class="container text-center">
			<h1>SCEGLI I TUOI ARTICOLI</h1>
			<p>CLICCA COL MOUSE SULL'IMMAGINE DEL CARRELLO</p>
		</div>
	</div>

	<%@ include file="Header.jsp"%>

<div id="fiori" class="show">
<div class="container" >
  <div id="myDIV" class="dropdown">
    <div>Cerca i vini tramite FILTRO</div>
   
    <a href="CatalogController?sort=nome" class="btn btn-primary" role="button"> Ordina per nome  </a>
    <a href="CatalogController?sort=rosso" class="btn btn-primary" role="button"> Rossi  </a>
    <a href="CatalogController?sort=bianco" class="btn btn-primary" role="button"> Bianchi  </a>
    
  </div>
</div>
</div>
<br>

<%

	Collection<?> products = (Collection<?>) request.getAttribute("products");

	if (products == null) {
		response.sendRedirect("./CatalogController?sort=catalogo");
		return;
	}

%>


<div class = "hidden" id="ultraId" >

<h1>ci sono</h1>

</div>


<div id="mio"class="show">
	<div class="container" id="containerdasost" >
		<div class="row">
		<%
			if (products != null && products.size() != 0)
				{
					Iterator<?> iter = products.iterator();
					while (iter.hasNext())
					{
						Vino vino = (Vino) iter.next();
					
		%>
			<div class="col-sm-4">
				<div class="panel panel-primary">
					<div class="panel-heading"><%=vino.getNome()%></div>
		<%
						if (vino.getImmagine() != null)
						{
							String img = vino.getImmagine();
		%>
					<div class="panel-body">
						<img alt="<%=vino.getNome()%>" src="<%=img%>"  width="250x" onclick="loadDoc( <%=vino.getIdVino()%>,'<%=vino.getNome()%>' ,<%=vino.getPrezzo()%>, '<%=vino.getAnno()%>' ,'<%=vino.getImmagine()%>')" class="img-responsive" >
					</div>
		<%
						}
    	%>
					<div class="panel-footer">
						Annata:<%=vino.getAnno()%>
					</div>
					<div class="panel-footer">
						<p>
							Prezzo:<%=vino.getPrezzo()%>&euro;
							Quantità:<%=vino.getQuantita()%>
						</p>
					</div>
					
					
					<%
					if (session.getAttribute("userName") != null) {
						
					%>
					<div class="panel-footer"  style="float:left;">
					
					
					
					<form action="FeedbackController"  style="float:left;" method="get">
								<input type="hidden" name="wine_id"
									value="<%=vino.getIdVino()%>"> <input type="hidden"
									name="cf_user" value="<%=session.getAttribute("userName")%>">
								<input type="hidden" name="opt" value="insert" >
								<button class="like_button" type="submit">
									<img alt="Like" src="./image/icon/like.png">
								</button>
					</form>
						
						<p  style="float:left;">FeedBack:<%=vino.getFeedback()%> </p>
						
					<form action="FeedbackController" style="float:left;" method="get">
								<input type="hidden" name="wine_id"
									value="<%=vino.getIdVino()%>"> <input type="hidden"
									name="cf_user" value="<%=session.getAttribute("userName")%>">
								<input type="hidden" name="opt" value="delete">
								<button class="like_button" type="submit">
									<img alt="Like" src="./image/icon/dislike.png">
								</button>
					</form>
					
					</div>
					
					<%
					if( (session.getAttribute("erroreLike") != null) &&  (session.getAttribute("erroreLike").equals("gia_inserito"))  )
					  {
					  	if( session.getAttribute("erroreLikeid").equals(vino.getIdVino())  )
					  	{
					
					%>
					<div class="panel-footer">
					<p style="color:red;">Like gia inserito</p>
					</div>
					
					<%
					  	}
					  }else if( (session.getAttribute("erroreLike") != null) &&  (session.getAttribute("erroreLike").equals("non_esiste"))  )
					  {
						  	if( session.getAttribute("erroreLikeid").equals(vino.getIdVino())  )
						  	{
					
					
					%>
					
					<div class="panel-footer" >
					<p style="color:red;">Non hai mai inserito questo Like</p>
					</div>
					
					<%
						  	}
					  }
					%>
					
					
					<div class="panel-footer" >
					<%
						if(vino.getQuantita()!=0)
						{
					%>
					<img class="cart_logo" alt="cart"
						src="image/social_icon/cart_catalog.png" width="35px"
						height="35px">
					<button name="addToCart" class="wine_button" value="add_wine"
						onclick="addToCartAjax( 'add',<%=vino.getIdVino()%>, '<%=vino.getNome()%>', '<%=vino.getPrezzo()%>', '<%=vino.getImmagine()%>', 1, index()) " >Add
						to Cart </button>
					<%
						}else{
					%>	
						<img class="cart_logo" alt="cart"
						src="image/social_icon/cart_catalog.png" width="35px"
						height="35px">
						<button  name="addToCart" class="wine_button" value="add_wine">Non disponibile</button>
						<br>
						<%
						}
					%>
					</div>

		<%
					}else{
		%>
					
					<div class="panel-footer" >
					
					<p >FeedBack:<%=vino.getFeedback()%> </p>
					<p>Effettua il login per aggiungere al carrello</p>
					
					</div>

		<%
		}
		%>

			</div>
			</div>

			<%
				} 
			}else{
    %>
			<h1>NESSUN PRODOTTO</h1>

			<%
			}
		%>


		</div>
	</div>
	</div>
	<br>
	<br>

	<%@ include file="footer.jsp"%>




<script >
	
function addToCartAjax(add,wineId, wineName , winePrice ,  wineImg, wineQuantity, index){

	var ajax_request = new XMLHttpRequest();

	ajax_request.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 200) {
			
			var i = parseInt(document.getElementById("NumArticle").innerHTML) + 1;
			document.getElementById("NumArticle").innerHTML = i;
		}
	};
	ajax_request.open("POST","CartController",true);
	
	ajax_request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	ajax_request.send("action="+add+"&wine_name="+wineName+"&wine_id="+wineId+"&wine_prize="+winePrice+"&wine_img="+wineImg+"&wine_quant="+wineQuantity);
	
	
	
};


function index(){
	var index = parseInt(document.getElementById("NumArticle").innerHTML);
	 console.log(index);
	 return index;
}
function function_show(y) {
	var x = document.getElementById(y);

	x.className = "show";
	
}
function function_hidden(y) {
	var x = document.getElementById(y);

	x.className = "hidden";
	
}


function loadDoc(wineId, wineName , winePrice , wineYear,wineImg )
{

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {	//quando la richiesta e pronta si fa qua
		if (this.readyState == 4 && this.status == 200) {
			
			function_show("ultraId");
			function_hidden("mio");
			function_hidden("fiori");
			document.getElementById("ultraId").innerHTML =this.responseText;
			document.body.style.background ="-webkit-linear-gradient(left, #B0C4DE, #708090)";
		}
	};  
	xhttp.open("GET","DescriptionController?wineName="+wineName+"&wineId="+wineId+"&winePrice="+winePrice+"&wineYear="+wineYear+"&wineImg="+wineImg+"&dammiDescrizione=TieniDescrizione", true);
	xhttp.send();

}
function pressButton(){
	
	function_hidden("ultraId");
	function_show("mio");
	function_show("fiori");
	document.body.style.background = "#ffffff";
	
}





</script>







</body>
</html>