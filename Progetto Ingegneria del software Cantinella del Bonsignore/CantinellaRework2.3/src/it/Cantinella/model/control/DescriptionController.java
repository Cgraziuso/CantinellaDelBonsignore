package it.Cantinella.model.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.Cantinella.model.DAO.ArticoloDAO;
import it.Cantinella.model.DAO.descriptionDAO;
import it.Cantinella.model.bean.Vino;


@WebServlet("/DescriptionController")
public class DescriptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DescriptionController() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idVinoDanyint;
		String idVinoDany;
		String descrizioneDany="(VUOTO)";

		descriptionDAO descDao = new descriptionDAO();
		String sort = request.getParameter("sort");
		System.out.println("sort= "+ sort);

		//String action = request.getParameter("action");

		if ((request.getParameter("dammiDescrizione").equals("TieniDescrizione")))
		{	
			System.out.println("ci devi entrareEEEEEEEEEEEEEEEEEEE");
			idVinoDany=(String)request.getParameter("wineId");
			idVinoDanyint=Integer.parseInt(idVinoDany);
			try{
				descrizioneDany= descDao.getDescr(idVinoDanyint);
			}catch (Exception e) {
				System.out.println("ECCEZIONE prelievo descrizione vino da DB fallita CatalogController.java");
			}
			
			request.setAttribute("invioVino",descrizioneDany);
			System.out.println("speriamo bene = "+descrizioneDany);
			HttpSession session = request.getSession();
			session.setAttribute("descriz" , descrizioneDany);
	
		
			Vino vino = new Vino(00,"zero",(String)request.getParameter("wineName"),"vuoto",(String)request.getParameter("wineYear"),(String)request.getParameter("winePrice"),(String)request.getParameter("wineImg"),"rosso",0,0);
			System.out.println("stampiamo l'oggeto costruito con la chimata ajax in descriptionCntroller = "+" id = "+vino.getIdVino()+"nome ="+vino.getNome()+"anno"+vino.getAnno()+"prezzo"+vino.getPrezzo());
		
		
			session.setAttribute("wineName" ,(String)request.getParameter("wineName"));
			session.setAttribute("wineYear" , (String)request.getParameter("wineYear"));
			session.setAttribute("winePrice" , (String)request.getParameter("winePrice"));
			System.out.println("fin qui tutto bene");
			
		}

		
		RequestDispatcher redispatcher = getServletContext().getRequestDispatcher("/InfoDescrizione.jsp");
		redispatcher.forward(request, response);
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
