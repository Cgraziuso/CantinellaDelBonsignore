package it.Cantinella.model.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.Cantinella.model.DAO.ArticoloDAO;
import it.Cantinella.model.bean.Vino;
import it.Cantinella.model.bean.Cart;

@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArticoloDAO modQuantita= new ArticoloDAO();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		System.out.println("qui ci va");
		if (cart != null) {
			System.out.println("qui ci va");
			List<Vino> wines = cart.getWinesList();
			for (Vino winebean : wines) {
				try {
					modQuantita.updateVino(winebean.getIdVino() , "add", winebean.getQuantita());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			}
		
		HttpSession session = request.getSession();
		session.invalidate();

		
		request.getRequestDispatcher("/Home.jsp").include(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
