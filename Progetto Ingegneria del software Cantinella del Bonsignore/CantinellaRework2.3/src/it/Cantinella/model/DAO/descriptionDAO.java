package it.Cantinella.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import it.Cantinella.model.connection.DMConnectionPool;

public class descriptionDAO {
	
	
	public descriptionDAO(){}
	
	
	/*	recupera le informazioni relative alla descrizione di un vino dal DataBase
		@param int idVino identificativo del vino da cui recuperare la descrizione
		@return String descrizione del vino
	*/
	public String getDescr(int idVino) throws SQLException
	{
		Connection con = null;
		PreparedStatement prepstate =null;
		String descrizione=null;
		ResultSet resultSet = null;
		String query1 = "SELECT descrizione FROM lacantinelladelbonsignore.vino WHERE idVino='"+idVino+"'";
		try{
			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(query1);
			resultSet= prepstate.executeQuery(query1);
			con.commit();
			
			if(resultSet.next() == true)
			{
				descrizione = resultSet.getString("descrizione");
				System.out.println("descrizione = " + descrizione);
			}			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			prepstate.close();
			con.close();
		}
		return descrizione;
	}
}
