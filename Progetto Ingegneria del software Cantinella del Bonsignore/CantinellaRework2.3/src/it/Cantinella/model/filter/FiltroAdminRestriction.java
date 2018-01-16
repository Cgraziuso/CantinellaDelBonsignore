package it.Cantinella.model.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AdminCart")
public class FiltroAdminRestriction implements Filter{

    public FiltroAdminRestriction() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("	cattanoooooooooooooooooo");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);//dammi la sessione ma non crearla se non esiste
		System.out.println("session = "+session);
		System.out.println("session.getAttribute(userName) = "+session.getAttribute("userName"));
		
		if(session.getAttribute("userName") == null)
		{
			chain.doFilter(request, response);
		}else{
			if(session.getAttribute("userName").equals("Amministratore"))
			{
				res.sendRedirect("./Home.jsp");
			}else{
	    		 System.out.println("admin");
	    		 chain.doFilter(request, response);
			}

		}
	 }



	public void init(FilterConfig fConfig) throws ServletException {}	

}
