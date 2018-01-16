<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Contact us</title>
<style >
.red{
    color:red;
    }
.form-area
{
    background-color: #FAFAFA;
	padding: 10px 40px 60px;
	margin: 10px 0px 60px;
	border: 1px solid GREY;
	}

.form{
		margin-right: 5%;
}
.container2{
		margin-left: 20%;
}
.prova{
	margin-left: 35%;

}

 .jumbotron {
      margin-bottom: 0;
    }
</style>
</head>
<body>


<div class="jumbotron">
  <div class="container2" class="prova">
    <h1>Dacci la tua opinione, inviaci un'email!</h1>      
    <p>cercheremo di risponderti quanto prima</p>
  </div>
</div>


<%@ include file="Header.jsp"%>


<div class="container">
<div class="col-md-10">
<form name="contattami" onsubmit="return ValidazioneContact();"   id="contact-form" method="post" action="mailto:andrea.carpentiere@hotmail.it" role="form">

    <div class="messages"></div>

    <div class="controls">

        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="form_name">Nome *</label>
                    <input id="form_name" type="text" name="name" class="form-control" placeholder="Iserisci il tuo nome" required="required" data-error="Firstname is required.">
                    <div class="help-block with-errors"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="form_lastname">Telefono</label>
                    <input id="form_cell" type="number" name="tel" class="form-control" placeholder="Inserisci il tuo numero di telefono" data-error="Lastname is required.">
                    <div class="help-block with-errors"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="form_email">Email *</label>
                    <input id="form_email" type="email" name="email" class="form-control" placeholder="Inserisci la tua email" required="required"  data-error="Valid email is required.">
                    <div class="help-block with-errors"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="form_phone">Oggetto</label>
                    <input id="form_subject" type="text" name="object" class="form-control" placeholder="Oggetto email">
                    <div class="help-block with-errors"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="form-group">
                    <label for="form_message">Messaggio *</label>
                    <textarea id="form_message" name="message" class="form-control" placeholder="Messaggio" rows="4" required="required"  data-error="Please,leave us a message."></textarea>
                    <div class="help-block with-errors"></div>
                </div>
            </div>
            <div class="col-md-12">
                <input type="submit" class="btn btn-success btn-send" value="Send message">
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
            <hr>
        <img class="user_logo" alt="User" src="image/social_icon/facebook.png" width="35px" height="35px">
        <img class="user_logo" alt="User" src="image/social_icon/istagram.png" width="35px" height="35px">
        <img class="user_logo" alt="User" src="image/social_icon/twitter.png" width="35px" height="35px">
        <img class="user_logo" alt="User" src="image/social_icon/google_plus.png" width="35px" height="35px">
            </div>
        </div>
    </div>

</form>

</div>
</div>
<hr>

<%@ include file="footer.jsp"%>
</body>



<script type="text/javascript">
$(document).ready(function(){ 
    $('#characterLeft').text('140 characters left');
    $('#message').keydown(function () {
        var max = 140;
        var len = $(this).val().length;
        if (len >= max) {
            $('#characterLeft').text('You have reached the limit');
            $('#characterLeft').addClass('red');
            $('#btnSubmit').addClass('disabled');            
        } 
        else {
            var ch = max - len;
            $('#characterLeft').text(ch + ' characters left');
            $('#btnSubmit').removeClass('disabled');
            $('#characterLeft').removeClass('red');            
        }
    });    
})


function ValidazioneContact()
{	
	//alert("ci entra");
	var name = document.contattami.name;
	var email = document.contattami.email;
	var tel = document.contattami.tel;
	var ogg = document.contattami.object;
	var mess = document.contattami.message;
	//alert("nome="+ name.value +" email="+ email.value +" oggetto="+ ogg.value + " tel=" + tel.value + " mess="+ mess.value  );
	/*var letters = /^[0-9a-zA-Z]+$/;
	var letters2 = /^[0-9]/;
	var letters3 = /^[0-9a-zA-Z.,:- ]+$/;
	var letters4 = /^[0-9.,]+$/;*/
	
	if ((name == "") || name.value.length<2 || name.value.length > 30) {
		   alert("Il campo Nome non puo' essere vuoto/ la lunghezza deve essere compresa Tra 2 e 30 caratteri.");
		   document.contattami.name.focus();
		   return false;
		}else if( ! (name.value.match(/^[0-9a-zA-Z ]+$/)) )
			{
			 alert("Il campo Nome non rispetta il formato, solo numeri e lettere.");
			 return false;
			}
	
	if (tel.value.length!=10 ) {
		alert("La lunghezza del campo telefono deve essere di 10 numeri. " + tel.value );
		document.contattami.tel.focus();
		return false;
	}
	
	if(ogg.value!="")
	{
		if ( ogg.value.length<2 || ogg.value.length > 20) {
			   alert("La lunghezza del campo oggetto deve essere compresa tra 2 e 20 caratteri.");
			   document.contattami.object.focus();
			   return false;
			}else if( ! (ogg.value.match(/^[0-9a-zA-Z ]+$/)) )
				{
				 alert("Il campo oggetto non rispetta il formato, solo numeri e lettere.");
				 return false;
				}
	}
	if ( mess.value=="" ||  mess.value.length<2 || mess.value.length > 125) {
		   alert("La lunghezza del campo messaggio deve essere compresa tra 2 e 125 caratteri.");
		   document.contattami.message.focus();
		   return false;
		}else if( ! (mess.value.match(/^[0-9a-zA-Z.]+$/)) )
			{
			 alert("Il campo messaggio non rispetta il formato, solo numeri e lettere.");
			 return false;
			}
	
	
	return true;
	
	
	
	
	
	//NOME 
	
	/*
	
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
	
	if (prezzo.value==0 ||  (prezzo== "") || prezzo.value.length>6 ) {
				alert("Il campo prezzo deve essere un numero maggiore di 1 e minore di 6 numeri ." + prezzo.value );
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
		}*/
	
	
}


</script>




</html>