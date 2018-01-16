package it.Cantinella.model.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import it.Cantinella.model.connection.DMConnectionPool;

import it.Cantinella.model.bean.Utente;
import it.Cantinella.model.bean.Vino;

public class ProfileDAO 
{
	/*	recupera tutte le informazioni relative al profilo dell'utente dal DB
		@param String userName indica l'user dell'utente da cui ricavare le informazioni relative al profilo
		@return Utente contiene tutte le informazioni relative al profilo dell'utente
	 */
	public Utente informationProfile (String userName)
	{
		Utente client = new Utente();
		//Cerca nel database e istanzia il cliente
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String query = "SELECT * FROM utenti WHERE user = '"+userName+"'";
		try{
			con = DMConnectionPool.getConnection();
			statement = (Statement) con.createStatement();
			//statement.setString(1, userName);
			resultSet = statement.executeQuery(query);
			
			if(resultSet.next()){
				client.setCf( resultSet.getString("cf"));
				client.setNome(resultSet.getString("nome"));
				client.setCognome(resultSet.getString("cognome"));
				client.setEmail(resultSet.getString("email"));	
				client.setUser(resultSet.getString("user"));
				client.setPassword(resultSet.getString("password"));
				
				client.setSeller(resultSet.getBoolean("isSeller"));
				
				if(client.isSeller()==true)
				{
					client.setPiva(resultSet.getString("piva"));
					client.setNomeAzienda(resultSet.getString("nomeAzienda"));
				}
			}
			statement.close();
			resultSet.close();
			con.close();

		}catch (Exception e) {
			e.printStackTrace();
		}		
		return client;
	}
	
	/*	recupera le informazioni relative a tutti gli utenti dal DB
		@return Collection<Utente> lista di utenti registrati sul sito
	 */
	public Collection<Utente> doRetriveAll() throws SQLException, IOException, ClassNotFoundException 
	{
		Connection con = null;
		PreparedStatement prepstate = null;
		
		Collection<Utente> collection = new LinkedList<Utente>();
		
		String query = "SELECT * FROM utenti ";
		try {
			
		con = DMConnectionPool.getConnection();
		prepstate = con.prepareStatement(query);

		ResultSet resultstet = prepstate.executeQuery();
		while (resultstet.next())
		{
			Utente utente = new Utente();
			
			utente.setCf(resultstet.getString("cf"));
			utente.setNome(resultstet.getString("nome"));
			utente.setCognome(resultstet.getString("cognome"));
			utente.setEmail(resultstet.getString("email"));
			utente.setUser(resultstet.getString("user"));
			utente.setPassword(resultstet.getString("password"));
			utente.setSeller(resultstet.getBoolean("isSeller"));
			utente.setNomeAzienda(resultstet.getString("nomeAzienda"));
			utente.setPiva(resultstet.getString("piva"));
		
			collection.add(utente);
		}
		return collection;
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}finally 
		{
			try {
				if (prepstate != null)
					prepstate.close();
			} finally {
				if (con != null)
					con.close();
			}
		}
		return collection;
	}
	
}	
	
	
	
	
	


