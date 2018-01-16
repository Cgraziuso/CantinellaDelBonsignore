package it.Cantinella.model.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import it.Cantinella.model.bean.LoginBean;
import it.Cantinella.model.DAO.LoginDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username_get = request.getParameter("username");
		String password_get = request.getParameter("password");
		
		
		
		LoginDAO logindao = new LoginDAO();
		
		boolean userValidate = logindao.authenticateUser(username_get, password_get);
		System.out.println("User validate value : " + userValidate);
		
		if(userValidate){
			//Implementazione sessione
			
			HttpSession session = request.getSession();
			session.setAttribute("userName" , username_get);
			
		
			response.sendRedirect("Home.jsp");
		}
		else{
			
			request.setAttribute("error", "Invalid Username or Password");
			RequestDispatcher rd=request.getRequestDispatcher("/Home.jsp");            
			rd.include(request, response);
		}
		
	}
	

}
