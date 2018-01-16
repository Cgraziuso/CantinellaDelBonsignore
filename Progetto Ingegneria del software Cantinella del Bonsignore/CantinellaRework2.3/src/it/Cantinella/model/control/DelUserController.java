package it.Cantinella.model.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import it.Cantinella.model.bean.Utente;
import it.Cantinella.model.DAO.RegistrationDAO;

@WebServlet("/DelUserController")
public class DelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DelUserController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		RegistrationDAO elimina = new RegistrationDAO();
		String cfi = request.getParameter("inputCF");
		System.out.println("azione= "+ cfi);
		
		if(request.getParameter("inputCF")!=null)
		{
			
		
			
			try {
				if(elimina.eliminaUser(cfi))
				{
					RequestDispatcher redispatcher = getServletContext().getRequestDispatcher("/Utenti.jsp");
					redispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}else{
			System.out.println("cf e uguale a null");
			RequestDispatcher redispatcher = getServletContext().getRequestDispatcher("/Utenti.jsp");
			redispatcher.forward(request, response);
		
		}
	
		
			

		}
		
		
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
