package it.Cantinella.model.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.Cantinella.model.bean.Utente;
import it.Cantinella.model.DAO.RegistrationDAO;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String checked =   request.getParameter("rad");
		
		
		
			System.out.println("/n dopo l'if del check");
			Utente newUser=new Utente();
			RegistrationDAO newUserDAO = new RegistrationDAO();
			
			
			newUser.setUser(request.getParameter("user"));
			newUser.setPassword(request.getParameter("password"));
			newUser.setNome(request.getParameter("nome")); 
			newUser.setCognome(request.getParameter("cognome"));
			newUser.setCf(request.getParameter("cf"));
			newUser.setEmail(request.getParameter("email"));
			
			
			if(checked.equals("client"))
			{
				
				newUser.setSeller(false);	
				newUser.setNomeAzienda(null);
				newUser.setPiva(null);
				
				
				try {
					if(!newUserDAO.checkUser(newUser.getUser()) )
					{
						request.getSession().setAttribute("errore", "null");
						System.out.println(request.getSession().getAttribute("errore"));
					}else {
						request.getSession().setAttribute("errore", "user");
						System.out.println(request.getSession().getAttribute("errore"));
						request.getRequestDispatcher("/Registrazione.jsp?").forward(request, response);
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					if(!newUserDAO.checkCF(newUser.getCf()))
					{
						request.getSession().setAttribute("errore", "null");
						System.out.println(request.getSession().getAttribute("errore"));
					}else {
						request.getSession().setAttribute("errore", "cf");
						System.out.println(request.getSession().getAttribute("errore"));
						request.getRequestDispatcher("/Registrazione.jsp?").forward(request, response);
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				try {
			
					//Inserimento tramite dao
					if(newUserDAO.setRegistration(newUser)){
						request.getRequestDispatcher("/Home.jsp").forward(request, response);
					}else{
						request.getRequestDispatcher("/Registrazione.jsp").forward(request, response);
						System.out.println("errore reg");
					}
				
					System.out.println("dopo il setRegistration");
			
				} catch (SQLException e) {
					//Inserire la riderezione alla pagina di errore 
					e.printStackTrace();
				
			}
		

		}else if (checked.equals("seller")){
			
			newUser.setSeller(true);
			newUser.setNomeAzienda(request.getParameter("nome_azienda"));
			newUser.setPiva(request.getParameter("partita_iva"));
			
			
			try {
				if(!newUserDAO.checkCF(newUser.getCf()))
				{
					request.getSession().setAttribute("errore", "null");
				}else {
					request.getSession().setAttribute("errore", "cf");
					
					request.getRequestDispatcher("/Registrazione.jsp?").forward(request, response);
					return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if(!newUserDAO.checkUser(newUser.getUser()))
				{
					request.getSession().setAttribute("errore", "null");
				}else {
					request.getSession().setAttribute("errore", "user");
					request.getRequestDispatcher("/Registrazione.jsp?").forward(request, response);
					return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
			try {
		
				//Inserimento tramite dao
				if(newUserDAO.setRegistration(newUser)){
					request.getRequestDispatcher("/Home.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("/Registrazione.jsp").forward(request, response);
				}
			
				System.out.println("dopo il setRegistration");
		
			} catch (SQLException e) {
				//Inserire la riderezione alla pagina di errore 
				e.printStackTrace();
			
		}
			

		

	}
	
	}
}
	
	
	
	
	
