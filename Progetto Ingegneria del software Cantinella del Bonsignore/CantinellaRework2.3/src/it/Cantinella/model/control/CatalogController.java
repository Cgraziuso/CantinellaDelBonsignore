package it.Cantinella.model.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import it.Cantinella.model.DAO.ArticoloDAO;




@WebServlet("/CatalogController")
public class CatalogController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public CatalogController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("edrfgthgrfdefrgthygrf ");
		ArticoloDAO articoloDao = new ArticoloDAO();
		String sort = request.getParameter("sort");
		System.out.println("sort= "+ sort);

		//String action = request.getParameter("action");

			try {

				request.removeAttribute("products");
				request.setAttribute("products", articoloDao.doRetriveAll(sort));


			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			RequestDispatcher redispatcher = getServletContext().getRequestDispatcher("/Catalogo.jsp");
			redispatcher.forward(request, response);
			

			
		


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}