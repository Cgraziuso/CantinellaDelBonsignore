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


@WebFilter("/FiltroAmministratore")
public class FiltroAdminUser implements Filter {

    public FiltroAdminUser() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("	cattanoooooooooooooooooo");
	    
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);//dammi la sessione ma non crearla se non esiste
		System.out.println("Try filter");
	    if(session != null && session.getAttribute("userName") != null)
	    {
	    	if(session.getAttribute("userName").equals("Amministratore"))
	    	{
	    		 System.out.println("admin");
	    		 chain.doFilter(request, response);
	    	 }else{
	    		 res.sendRedirect("./Home.jsp");
	    	 }
	     }
	     else{	 
	    	 res.sendRedirect("./Home.jsp");
	     }}

	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
