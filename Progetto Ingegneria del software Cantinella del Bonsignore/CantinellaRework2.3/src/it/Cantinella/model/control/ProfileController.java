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
import it.Cantinella.model.DAO.ProfileDAO;
import it.Cantinella.model.DAO.ArticoloDAO;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userName = (String) request.getSession().getAttribute("userName");
		System.out.println("username" + userName);
		
		
		
		
		
		ProfileDAO profile = new ProfileDAO();
		Utente client = profile.informationProfile(userName);
		ArticoloDAO catalog = new ArticoloDAO();
	
		System.out.println("prima di sort" + request.getParameter("sort"));
	
		if( (request.getParameter("sort") !=null) && (request.getParameter("sort").equals("admin")) )
		{
			try {

				request.removeAttribute("users");
				request.setAttribute("users", profile.doRetriveAll());


			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			RequestDispatcher redispatcher = getServletContext().getRequestDispatcher("/Utenti.jsp");
			redispatcher.forward(request, response);
			
		}
		
		
		request.setAttribute("client",client);
		
		
		try {
			request.removeAttribute("productsCF");
			request.setAttribute("productsCF", catalog.doRetriveByCF(client.getCf()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/Profile.jsp").include(request, response);
	}

}
