<%@ page import="it.Cantinella.model.bean.Cart"%>
<style>

/****** LOGIN MODAL ******/
nav{
background-color: #090c14 !important;
color:#878c94 !important;

}

.error{
color:red !important;
}

.loginmodal-container {
  padding: 30px;
  max-width: 350px;
  width: 100% !important;
  background-color: #F7F7F7;
  margin: 0 auto;
  border-radius: 2px;
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  overflow: hidden;
  font-family: roboto;
}

.loginmodal-container h1 {
  text-align: center;
  font-size: 1.8em;
  font-family: roboto;
}

.loginmodal-container input[type=submit] {
  width: 100%;
  display: block;
  margin-bottom: 10px;
  position: relative;
}

.loginmodal-container input[type=text], input[type=password] {
  height: 44px;
  font-size: 16px;
  width: 100%;
  margin-bottom: 10px;
  -webkit-appearance: none;
  background: #fff;
  border: 1px solid #d9d9d9;
  border-top: 1px solid #c0c0c0;
  /* border-radius: 2px; */
  padding: 0 8px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
}

.loginmodal-container input[type=text]:hover, input[type=password]:hover {
  border: 1px solid #b9b9b9;
  border-top: 1px solid #a0a0a0;
  -moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
}

.loginmodal {
  text-align: center;
  font-size: 14px;
  font-family: 'Arial', sans-serif;
  font-weight: 700;
  height: 36px;
  padding: 0 8px;
/* border-radius: 3px; */
/* -webkit-user-select: none;
  user-select: none; */
}

.loginmodal-submit {
  /* border: 1px solid #3079ed; */
  border: 0px;
  color: #fff;
  text-shadow: 0 1px rgba(0,0,0,0.1); 
  background-color: #4d90fe;
  padding: 17px 0px;
  font-family: roboto;
  font-size: 14px;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
}

.loginmodal-submit:hover {
  /* border: 1px solid #2f5bb7; */
  border: 0px;
  text-shadow: 0 1px rgba(0,0,0,0.3);
  background-color: #357ae8;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
}

.loginmodal-container a {
  text-decoration: none;
  color: #666;
  font-weight: 400;
  text-align: center;
  display: inline-block;
  opacity: 0.6;
  transition: opacity ease 0.5s;
} 

.login-help{
  font-size: 12px;
}



.welcome{
	color: white;
	font-size: xx-large;
	margin-top: 3%;

}

</style>




<nav class="navbar">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
       <a class="navbar-brand" href="Home.jsp">La Cantinella Del Bonsignore</a> 
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="Home.jsp"   >Home</a></li>
        <li><a href="Catalogo.jsp" onclick="funzioneAttributi">Catalog</a></li>
        <li><a href="Contact.jsp">Contact us</a></li>
      </ul>
      
      <% 
				String error = (String) request.getAttribute("error");
				if(error != null){
					%>
					<div class="error"><%=error%></div>
					<%
					
				}
			%>
		<ul class="nav navbar-nav navbar-right">
			<%
			
				if(session.getAttribute("userName") != null){ 
			%>
					<li class="li_cont welcome"><p class="welcome" > Welcome <a class="text_userName" href="./Profile.jsp"><%= session.getAttribute("userName")%></a> </p></li>
			
			<%
				}
			%>
			
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <img class="user_logo" alt="User"
				src="image/social_icon/user_icon2.png" width="35px" height="35px"></a>

				<%if(session.getAttribute("userName") == null){ %>

				
				<ul  class="dropdown-menu">
				
				<li><a data-toggle="modal" data-target="#login-modal">login</a></li>
				
				<li><a href="Registrazione.jsp">registrazione</a></li>
					
				</ul>
				 <%}else{  %>
					
				
				<ul  class="dropdown-menu">
				
				
				
					
				
					
				<%	if(session.getAttribute("userName").equals("Amministratore")){%>
					
							<li><a href="Utenti.jsp" >Users</a></li>
							<li><a href="Ordini.jsp" >Ordini</a></li>
					<%} %>
					<li><a href="LogoutController">Logout</a></li>
						
					</ul>
					<% 	} %> 
				
				
				
		</li>
		<%
			int num_element = 0;
			if( session.getAttribute("cart") != null){
				Cart cart = (Cart) session.getAttribute("cart");
				num_element = cart.getWineNumber();
				session.setAttribute("num_element", num_element);
			}
		
		%>
		
		
		<li class="dropdown">
			<a href="Cart.jsp" class="dropdown-toggle" > 
				<span id="NumArticle" class="number_article"> <%=num_element%></span>
				<img class="user_logo"  alt="Cart" src="image/social_icon/cart_icon2.png" width="40px" height="40px">
			</a>
			
			
		</li>
		
		</ul>
      
      
      
      
    </div>
  </div>
  
  
 <!-- Login Form --> 
  <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	  <div class="modal-dialog">
				<div class="loginmodal-container">
					<h1>Login to Your Account</h1><br>
				  <form onsubmit="return formLoginValidation();" action="LoginController" method="get" name="LoginForm">
					<input type="text" name="username" placeholder="username" id="user" >
					<input type="password" name="password" placeholder="password" id="password">
					<input type="submit" name="login" class="login loginmodal-submit" value="Login">
				  </form>
					
				  <div class="login-help">
					<a href="Registrazione.jsp">Register</a>
				  </div>
				</div>
			</div>
		  </div>
 

	
	
	
</nav>



<script type="text/javascript">

function formLoginValidation() {

	var username = document.LoginForm.username;
	
	var pass = document.LoginForm.password;

	//alert ("username "+ username.value.length + " pass "+ pass.value.length );
	
	if( userLoginValidation(username, 2,30) )
			{
				if( passLoginValidation (pass, 6, 30 ))
				{
					return true;
				}
			}
			return false;

}


function passLoginValidation(passid,mx,my)  
{  
	var passid_len = passid.value.length;  
	if (passid_len == 0 ||passid_len >= my || passid_len < mx)  
	{  
		alert("La password non puo' essere vuota / la linghezza deve essere compresa fra  "+mx+"   "+my);  
		passid.focus();  
		return false;  
	}  
	return true;  
}  

function userLoginValidation(name,mx,my) {
	var letters = /^[0-9a-zA-Z]+$/;
	var name_length = name.value.length;
	if ( (name.value.match(letters)) && ( (name_length>2) && (name_length<30) )  )
	{
		return true;
				
	} else {
		
		alert('Username non abbastanza lungo/non rispetta il formato');
		name.focus();
		return false;
	}
}

</script>

