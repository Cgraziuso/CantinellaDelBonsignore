package it.Cantinella.model.DAO;

import java.sql.Statement;



import java.sql.ResultSet;
import java.sql.Connection;


public class CancellUserDAO 
{

	Statement st=null;
	 ResultSet res=null;

	public CancellUserDAO(Connection conny)
	{
		
		try
		{
		 st = conny.createStatement();
		}
		catch (Exception e) 
		{
			System.out.println("ECCEZIONE:in CalcellUser.java, st = conny.createStatement();");
			e.printStackTrace();
		}
	}
	
	public ResultSet ListaUser(){

		String sql = "SELECT * FROM lacantinelladelbonsignore.utenti;";
		System.out.println("vediamo la stringa qsl = "+ sql);
		
try{
		  res = st.executeQuery(sql);
}catch (Exception e) {
	System.out.println("ECCEZIONE:in CalcellUser : ResultSet res = st.executeQuery(sql);");
	e.printStackTrace();
}

		return res;
	
	}

}