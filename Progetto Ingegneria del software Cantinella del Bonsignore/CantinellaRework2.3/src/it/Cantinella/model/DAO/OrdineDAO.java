package it.Cantinella.model.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;

import it.Cantinella.model.bean.Ordine;
import it.Cantinella.model.connection.DMConnectionPool;

public class OrdineDAO {
	
	
	/* 	aggiunge un nuovo ordine nel DataBase
 		@param Ordine ordine nuovo ordine da aggiungere nel DB
 		@return boolean true se l'inserimento ha avuto successo false altrimenti
	 */
	public Boolean addArticolo(Ordine ordine) throws SQLException
	{
		Connection con = null;
		PreparedStatement prepstate = null;
		boolean ret = false;
		String query = "INSERT INTO ordine (user, descrizione, email,  indirizzo, zipCode, totale, percentuale) VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		String user = ordine.getUsernameCliente();
		String descrizione = ordine.getDescrizione();
		String email = ordine.getEmailTracciamento();
		String indirizzo = ordine.getIndirizzo();
		int zipCode = ordine.getZipCode();
		String totale = ordine.getTotale();
		String perc = ordine.getPercentuale();
	
		try{
			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(query);

			prepstate = con.prepareStatement(query);
			prepstate.setString(1, user);
			prepstate.setString(2, descrizione);
			prepstate.setString(3, email);
			prepstate.setString(4, indirizzo);
			prepstate.setInt(5, zipCode);
			prepstate.setString(6, totale);
			prepstate.setString(7, perc);

			prepstate.executeUpdate();
			con.commit();
			ret = true;

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			prepstate.close();
			con.close();
		}
		
	return ret;
	}
	
	/*	recupera tutte le informazioni relative agli ordini dal DB
		@return Collection<Ordine> lista di ordini degli utenti
	 */
	public Collection<Ordine> doRetriveAll() throws SQLException, IOException, ClassNotFoundException 
	{
		Connection con = null;
		PreparedStatement prepstate = null;
		
		Collection<Ordine> collection = new LinkedList<Ordine>();
		String query = "SELECT * FROM ordine ";
		try {
		con = DMConnectionPool.getConnection();
		prepstate = con.prepareStatement(query);

		ResultSet resultstet = prepstate.executeQuery();
		
		while (resultstet.next())
		{
			Ordine ordine = new Ordine();
			
			ordine.setIdOrdine(resultstet.getInt("idOrdine"));
			ordine.setUsernameCliente(resultstet.getString("user"));
			ordine.setDescrizione(resultstet.getString("descrizione"));
			ordine.setEmailTracciamento(resultstet.getString("email"));
			ordine.setIndirizzo(resultstet.getString("indirizzo"));
			ordine.setZipCode(resultstet.getInt("zipCode"));
			ordine.setTotale(resultstet.getString("totale"));
			ordine.setPercentuale(resultstet.getString("percentuale"));
		
			collection.add(ordine);
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
