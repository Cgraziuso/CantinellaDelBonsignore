package it.Cantinella.model.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.Cantinella.model.bean.Cart;
import it.Cantinella.model.bean.Vino;
import it.Cantinella.model.DAO.ArticoloDAO;


@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		System.out.println("Check cart controller");
		HttpSession session = request.getSession();
			
		Cart cart = null;
		Object sessionCart = session.getAttribute("cart"); 

		if(sessionCart == null){
			cart = new Cart();
			session.setAttribute("cart", cart);
			
			
		}else{
			cart = (Cart) sessionCart;
		}
		
		if(action!= null && action != ""){
			
			if(action.equals("add")){
				
				try {
					addToCart(request , cart);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				response.sendRedirect("Catalogo.jsp");
				
			}/*else if(action.equals("delete")){
				try {
					removeToCart(request, cart);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				response.sendRedirect("Catalogo.jsp");
				

			}*/else if(action.equals("deleteCart")){
				try {
					removeToCart(request, cart);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("Cart.jsp");
			}
		}
		System.out.println("Check cart controller"+ action);
		
	/*	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalog.jsp");
		dispatcher.forward(request, response);*/
	}


	private void removeToCart(HttpServletRequest request, Cart cartBean) throws SQLException {

	
		String wine_id = request.getParameter("wine_id");
		int wine_id_int = Integer.parseInt(wine_id);

		String num = request.getParameter("quantita");
		int num2 = Integer.parseInt(num);
		
		ArticoloDAO modQuantita= new ArticoloDAO();
		
		modQuantita.updateVino(wine_id_int , "add", num2);
		cartBean.deleteWine(wine_id_int);
		

	}


	private void addToCart(HttpServletRequest request , Cart cart) throws SQLException {
		System.out.println("Check cart controller add to cart");
		
		String wineParaId = request.getParameter("wine_id");
		int wineID = Integer.parseInt(wineParaId);

		
		String wineParaName = request.getParameter("wine_name");
		String wineParaImg = request.getParameter("wine_img");
		
		
		
		String wineParaPrice = request.getParameter("wine_prize");
		

		String wineParaQuant = request.getParameter("wine_quant");
		int Quantita= Integer.parseInt(wineParaQuant);

		Vino wineChosen = new Vino(wineID, " ", wineParaName, " ", " ", wineParaPrice, wineParaImg, " ", Quantita, 0 );
		
		
		ArticoloDAO modQuantita= new ArticoloDAO();
		
		modQuantita.updateVino(wineID, "del", 1);
		cart.addWine(wineChosen);
	
	}

}




