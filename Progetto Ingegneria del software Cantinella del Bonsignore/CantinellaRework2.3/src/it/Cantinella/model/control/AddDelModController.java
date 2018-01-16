package it.Cantinella.model.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import it.Cantinella.model.bean.Vino;
import it.Cantinella.model.DAO.ArticoloDAO;

@WebServlet("/AddDelModController")
public class AddDelModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddDelModController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		ArticoloDAO articoloDao = new ArticoloDAO();
		String azione = request.getParameter("azione");
		System.out.println("azione= "+ azione);
		
		if(azione.equals("aggiungi"))
		{
			
			Vino articolo = new Vino();

			articolo.setNome(request.getParameter("nome"));
			
			articolo.setDescrizione(request.getParameter("descrizione"));
			
			articolo.setAnno(request.getParameter("anno"));
			
			articolo.setPrezzo(request.getParameter("prezzo"));
			
			articolo.setTipo(request.getParameter("tipo"));
			
			articolo.setImmagine(request.getParameter("immagine"));
			
			articolo.setCfUser(request.getParameter("cf"));
			

			int quantita= Integer.parseInt(request.getParameter("quantita"));
			
			
			articolo.setQuantita(quantita);
			
			
			
			System.out.println(articolo.toString());
			
			try {
				if(articoloDao.addArticolo(articolo))
				{
					RequestDispatcher redispatcher = getServletContext().getRequestDispatcher("/Profile.jsp");
					redispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		if(azione.equals("elimina"))
		{
			
			String idArticolo = request.getParameter("idwine");
			try {
				if(articoloDao.delArticolo(idArticolo))
				{
					System.out.println("eliminazione effettuata");
					RequestDispatcher redispatcher = getServletContext().getRequestDispatcher("/Profile.jsp");
					redispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if(azione.equals("modifica"))
		{
			
			String idArticolo = request.getParameter("idwine");
			
			if((request.getParameter("prezzo").equals(""))!=true)
			{
				String prezzo = request.getParameter("prezzo");
				System.out.println(prezzo.length());
				if( (Float.parseFloat(prezzo)<= 100000 ) && (prezzo.length()<8)) {
					try {
						if(articoloDao.modPrezzo(idArticolo, prezzo))
						{
							System.out.println("modifica prezzo effettuata");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			
			if((request.getParameter("quantita").equals(""))!=true)
			{
				String quantita = request.getParameter("quantita");
				System.out.println(quantita);
				
				if(quantita.length()<7)
				{
					try {
					if(articoloDao.modQuantita(idArticolo, quantita))
					{
						System.out.println("modifica quantita effettuata");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				}
				
			}
			
			
			
			
			RequestDispatcher redispatcher = getServletContext().getRequestDispatcher("/Profile.jsp");
			redispatcher.forward(request, response);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
