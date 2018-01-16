package it.Cantinella.model.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;

import it.Cantinella.model.connection.DMConnectionPool;

import it.Cantinella.model.bean.Vino;

public class ArticoloDAO
{
	/*	recupera tutte le informazioni relative al vino dal DB, esclusa la descrizione
		Ordinate secondo il parametro sort
		@param string sort indica se la ricerca deve essere effuata in ordine alfabetico oppure secondo il colore del vino
		@return Collection<Vino> lista dei vini in vendita ordinati secondo il parametro sort
	*/
	public Collection<Vino> doRetriveAll( String sort) throws SQLException, IOException, ClassNotFoundException 
	{
		Connection con = null;
		PreparedStatement prepstate = null;
		
		Collection<Vino> collection = new LinkedList<Vino>();
		
		String query = "SELECT idVino, cf, nome,anno,prezzo,immagine,tipo,quantita,feedback FROM vino";
		
		if(sort != null && !sort.equals("") && !sort.equals("catalogo"))
		{
			if(sort.equals("rosso") || sort.equals("bianco"))
			{
				query += " WHERE tipo ='"+sort+"'";
			}else{
				query += " ORDER by " + sort ;
				
			}
		}
		try{
			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(query);
			ResultSet resultstet = prepstate.executeQuery();
			while (resultstet.next())
			{
				Vino vino = new Vino();
				vino.setIdVino(resultstet.getInt("idVino"));
				vino.setCfUser(resultstet.getString("cf"));
				vino.setNome(resultstet.getString("nome"));
				//vino.setDescrizione(resultstet.getString("descrizione"));
				vino.setAnno(resultstet.getString("anno"));
				vino.setPrezzo(resultstet.getString("prezzo"));
				vino.setImmagine(resultstet.getString("immagine"));
				vino.setTipo(resultstet.getString("tipo"));
				vino.setQuantita(resultstet.getInt("quantita"));
				vino.setFeedback(resultstet.getInt("feedback"));
				collection.add(vino);
			}
			return collection;
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
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
	
	
	/* 	aggiunge un nuovo vino nel DataBase
	 	@param Vino articolo nuovo vino da aggiungere nel DB
	 	@return boolean true se l'inserimento ha avuto successo false altrimenti
	*/
	public Boolean addArticolo(Vino articolo) throws SQLException
	{
	
		Connection con = null;
		PreparedStatement prepstate = null;
		boolean ret = false;
		
		String query = "INSERT INTO vino (cf, nome, descrizione, anno,  prezzo, immagine, tipo,  quantita, feedback) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String cf = articolo.getCfUser();
		String nome = articolo.getNome();
		String descrizione = articolo.getDescrizione();
		String anno = articolo.getAnno();
		String prezzo = articolo.getPrezzo();
		String immagine = articolo.getImmagine();
		String tipo = articolo.getTipo();
		int quantita = articolo.getQuantita();

		try{
			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(query);
			prepstate = con.prepareStatement(query);
			prepstate.setString(1, cf);
			prepstate.setString(2, nome);
			prepstate.setString(3, descrizione);
			prepstate.setString(4, anno);
			prepstate.setString(5, prezzo);
			prepstate.setString(6, immagine);
			prepstate.setString(7, tipo);
			prepstate.setInt(8, quantita);
			prepstate.setInt(9, 0);

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
	
	/*	modifica le informazioni relative al prezzo del vino nel DataBase
		@param string id identificativo del vino che deve essere modificato
		@param string prezzo nuovo prezzo da inserire 
		@return boolean true se la modifica ha avuto successo false altrimenti
	*/
	public Boolean modPrezzo(String id, String prezzo) throws SQLException
	{
		Connection con = null;
		PreparedStatement prepstate = null;
		boolean ret = false;
		
		String query = "UPDATE vino SET prezzo=?  WHERE  idVino= ?";
		
		try{

			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(query);
			prepstate = con.prepareStatement(query);
			prepstate.setString(1, prezzo);
			prepstate.setString(2, id);
		
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
	
	/*	modifica le informazioni relative alla quantità del vino nel DataBase
		@param string id identificativo del vino che deve essere modificato
		@param string quantita nuova quantità da inserire 
		@return boolean true se la modifica ha avuto successo false altrimenti
	 */
	public Boolean modQuantita(String id, String quantita) throws SQLException
	{
		Connection con = null;
		PreparedStatement prepstate = null;
		boolean ret = false;
		
		String query = "UPDATE vino SET quantita=?  WHERE  idVino= ?";
		
		try{

			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(query);

			prepstate = con.prepareStatement(query);
			prepstate.setString(1, quantita);
			prepstate.setString(2, id);
						

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
	
	/* 	Cancellare un vino dal DataBase
 		@param String id identificativo del vino da cancellare nel DB
 		@return boolean true se la cancellazione ha avuto successo false altrimenti
	 */
	public Boolean delArticolo(String id) throws SQLException
	{
		
		Connection con = null;
		PreparedStatement prepstate = null;
		boolean ret = false;
		
		
		
		String query = "DELETE FROM vino WHERE idVino=?";
		try{

			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(query);

			prepstate = con.prepareStatement(query);
			prepstate.setString(1, id);
			
						

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
	
	/*	recupera tutte le informazioni dei vini venduti da un'utente nel DataBase
		@param string cf codice fiscale del venditore
		@return Collection<Vino> lista dei vini venduti da un singolo utente
	 */	
	public Collection<Vino> doRetriveByCF(String cf) throws SQLException {

		Connection con = null;
		PreparedStatement prepstate = null;

		Collection<Vino> collection = new LinkedList<Vino>();

		String query = "SELECT * FROM vino WHERE cf= '"+cf+"'";

		
			try{

			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(query);

			ResultSet resultstet = prepstate.executeQuery();

			while(resultstet.next()){

				Vino vino = new Vino();
				
				if( resultstet.getString("immagine") != null){
	
					vino.setImmagine(resultstet.getString("immagine"));
				}
				
				vino.setIdVino(resultstet.getInt("idVino"));
				vino.setDescrizione(resultstet.getString("descrizione"));
				vino.setNome(resultstet.getString("nome"));
				vino.setPrezzo(resultstet.getString("prezzo"));
				vino.setAnno(resultstet.getString("anno"));
				vino.setTipo(resultstet.getString("tipo"));
				vino.setQuantita(resultstet.getInt("quantita"));
				vino.setFeedback(resultstet.getInt("feedback"));
				
				collection.add(vino);
			}

		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
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
	
	/*	aggiorna la quantita dei vini disponibili per la vendita nel DataBase
		@param int id identificativo del vino da aggiornare
		@param String azione tipo di aggiornamento da effettuare decremento o incremento
		@param int num valore da sommare o sottrarre 
		@return boolean true se l'aggiornamento ha avuto successo false altrimenti
 */	
	public Boolean updateVino(int id, String azione, int num) throws SQLException
	{
		Connection con = null;
		PreparedStatement prepstate =null;
		boolean ret = false;

		ResultSet resultSet = null;
		int quantita = 0;
		
		String query1 = "SELECT quantita FROM vino WHERE idVino= '"+id+"'";
		
		try{
			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(query1);
			resultSet= prepstate.executeQuery(query1);
			con.commit();
			
			if(resultSet.next() == true){

				quantita = resultSet.getInt("quantita");
			
				if(azione.equals("del"))
				{
					quantita=quantita-num;
				}else if(azione.equals("add"))
				{
					quantita=quantita+num;
				}
				
				try{
					String query = "UPDATE vino SET quantita="+quantita+" WHERE idVino="+id+"";
					 
					prepstate.executeUpdate(query);
					con.commit();

				}catch (Exception e) {
					e.printStackTrace();
				}
			}			
			ret = true;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			prepstate.close();
			con.close();
		}
		return ret;
	}
}
