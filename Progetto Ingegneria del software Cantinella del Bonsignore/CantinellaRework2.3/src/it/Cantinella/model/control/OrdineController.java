package it.Cantinella.model.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.Cantinella.model.DAO.OrdineDAO;
import it.Cantinella.model.bean.Ordine;
import it.Cantinella.model.bean.Cart;
import it.Cantinella.model.bean.Vino;

/**
 * Servlet implementation class OrdineController
 */
@WebServlet("/OrdineController")
public class OrdineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public OrdineController() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		OrdineDAO ordinedao= new OrdineDAO();
		String sort = request.getParameter("sort");
		System.out.println(sort);
		if(sort.equals("acquisto"))
		{
			
		Ordine Ordine = new Ordine();
		String descrizione = "";
		
		
		Ordine.setUsernameCliente(request.getParameter("user"));
		Ordine.setEmailTracciamento(request.getParameter("email"));
		Ordine.setIndirizzo(request.getParameter("indirizzo"));
		Ordine.setZipCode(Integer.parseInt(request.getParameter("zipcode")));
		Ordine.setTotale(request.getParameter("totale"));
		
		float calcolo = Float.parseFloat(request.getParameter("totale"));
		calcolo = calcolo * 0.1f;
		Ordine.setPercentuale(""+calcolo);
		
		Cart cart = new Cart();
		cart = (Cart) request.getSession().getAttribute("carrello");
		
		if (cart != null) {
			int i=1;
			List<Vino> wines = cart.getWinesList();
			for (Vino winebean : wines) {
				descrizione = descrizione + i + " " + winebean.getNome() + " " + winebean.getQuantita() + " " + winebean.getPrezzo() + " euro\n" ; 
				i++;
			}
			Ordine.setDescrizione(descrizione);
			}
		
		
		System.out.println(descrizione);
		System.out.println(Ordine.toString());
		
		try {
			if(ordinedao.addArticolo(Ordine))
			{
				
				request.getSession().removeAttribute("cart");
				RequestDispatcher redispatcher = getServletContext().getRequestDispatcher("/Home.jsp");
				redispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}else if(sort.equals("admin"))
		{
			
			
			try {

				request.removeAttribute("adminOrdini");
				request.setAttribute("adminOrdini", ordinedao.doRetriveAll());


			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			RequestDispatcher redispatcher = getServletContext().getRequestDispatcher("/Ordini.jsp");
			redispatcher.forward(request, response);
			
			
		}
		
		

		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
