package it.Cantinella.model.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.Cantinella.model.DAO.FeedBackDAO;


@WebServlet("/FeedbackController")
public class FeedbackController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FeedbackController() {
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
				FeedBackDAO feedback = new FeedBackDAO();
				String opt = request.getParameter("opt"); // opt � il nome che abbiamo settato con javascript dopo aver cliccato uno dei pulsanti like controlliamo se insert o delect rispettivamete (like,dislike)
				
				int idVino =Integer.parseInt(request.getParameter("wine_id"));
				String user =  request.getParameter("cf_user");
				
				System.out.println("id vino "+ idVino + " cf "+ user + " opt "+ opt);
				
				
					try {
						if( feedback.checkFeed(idVino, user) ) {
							
							//se entra qui, esiste la relazione
							if(opt.equals("insert"))
							{
								//attributo gia inserito
								request.getSession().setAttribute("erroreLike", "gia_inserito");
								request.getSession().setAttribute("erroreLikeid", idVino);
								
							}else {
								request.getSession().setAttribute("erroreLike", "null");
								request.getSession().setAttribute("erroreLikeid", idVino);
								feedback.removeLike(idVino, user);
							}
							
							request.getRequestDispatcher("/Catalogo.jsp").forward(request, response);

						}else{
							//se entra qui non esiste
							if(opt.equals("insert"))
							{
								request.getSession().setAttribute("erroreLike", "null");
								request.getSession().setAttribute("erroreLikeid", idVino);
								feedback.insertLike(idVino, user);
							}else {
								
								//attributo che cazzo fai?? non c'è il like che vuoi eliminare, sto cazzo?
								request.getSession().setAttribute("erroreLike", "non_esiste");
								request.getSession().setAttribute("erroreLikeid", idVino);
							}
							
							HttpSession session = request.getSession(false);
							session.setAttribute("errorLikeExists", request.getParameter("wine_id"));
							request.getRequestDispatcher("/Catalogo.jsp").forward(request, response);

						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			doGet(request, response);
		}

	}
