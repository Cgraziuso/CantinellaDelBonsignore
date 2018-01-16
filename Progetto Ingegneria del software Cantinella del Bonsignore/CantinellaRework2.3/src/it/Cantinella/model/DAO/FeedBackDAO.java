package it.Cantinella.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


import it.Cantinella.model.connection.DMConnectionPool;

public class FeedBackDAO {


	/*	inserisce un like relativo ad un vino nel DataBase
		@param int wine_id_read identificativo del vino
		@param String user utente propietario del like
		@return boolean true se l'inserimento del like ha avuto successo false altrimenti
	 */
	public boolean insertLike(int wine_id_read ,String user)
	{
		Connection con = null;
		PreparedStatement prepstate = null;
		//query 1 inseriamo la nuova coppia "mi piace id_wine " ,"codice fiscale utente in sessione" nel db
		String sql1 = "INSERT INTO feedback (idVino, user) VALUES ('"+wine_id_read+"', '"+user+"')";
		try{
			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(sql1);
			prepstate.executeUpdate(sql1);
			con.commit();			
			updateLikeInc(con, prepstate, wine_id_read);

			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	/*	aggiorna il contatore principare del like assegnato ad un vino nel DataBase
		@param Connection connessione verso il DataBase
		@param PreparedStatement per eseguire la query
		@param int wine_id identificativo del vino da cui aggiornare il contatore dei like
	 */
	public void updateLikeInc (Connection con , PreparedStatement prepstate,int wine_id){
		ResultSet resultSet = null;
		int like = 0;
		String sql2="SELECT feedback FROM vino WHERE idVino='"+wine_id+"'";
		try{
			resultSet = prepstate.executeQuery(sql2);
			con.commit();
			if(resultSet.next() == true){

				like = resultSet.getInt("feedback");
				like++;
				//query 3 aggiorniamo il nuovo valore preso e incremetato di nuovo nella colonna like dei vini

				String sql3 = "UPDATE vino SET feedback='"+like+"' WHERE idVino='"+wine_id+"';";

				try{
					prepstate.executeUpdate(sql3);
					con.commit();

				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*	rimuovere un like relativo ad un vino nel DataBase
		@param int wine_id_read identificativo del vino
		@param String user utente propietario del like
		@return boolean true se la rimozione del like ha avuto successo false altrimenti
	 */
	public boolean removeLike(int wine_id_read , String user) {
		Connection con = null;
		PreparedStatement prepstate = null;
		//query 1 inseriamo la nuova coppia "mi piace id_wine " ,"codice fiscale utente in sessione" nel db
		String sql1 = "DELETE FROM feedback WHERE idVino='"+wine_id_read+"' and user='"+user+"'";
		try{
			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(sql1);
			prepstate.executeUpdate(sql1);
			con.commit();			

			updateLikeDec(con, prepstate, wine_id_read);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/*	aggiorna il contatore principare del like assegnato ad un vino nel DataBase
		@param Connection connessione verso il DataBase
		@param PreparedStatement per eseguire la query
		@param int wine_id identificativo del vino da cui aggiornare il contatore dei like
	*/
	public void updateLikeDec(Connection con, PreparedStatement prepstate, int wine_id) {

		ResultSet resultSet = null;
		int like = 0;
		String sql2="SELECT feedback FROM vino WHERE idVino='"+wine_id+"'";
		try{
			resultSet = prepstate.executeQuery(sql2);
			con.commit();
			
			if(resultSet.next() == true){
				like = resultSet.getInt("feedback");
				like--;
				//query 3 aggiorniamo il nuovo valore preso e incremetato di nuovo nella colonna like dei vini

				String sql3 = "UPDATE vino SET feedback='"+like+"' WHERE idVino='"+wine_id+"'";
				try{
					prepstate.executeUpdate(sql3);
					con.commit();

				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*	controlla se l'utente ha gia inserito il like nel DB
		@param int wine_id_read  identificativo del vino
		@param String cf_read codice fiscale dell'utente propietario della lista dei like
		@return boolean true se il like è gia stato inserito false altrimenti
	 */
	public boolean checkFeed(int wine_id_read ,String cf_read ) throws SQLException{

		boolean giaEsiste=false;
		Statement stet;
		String sql ="SELECT * FROM feedback WHERE idVino='"+wine_id_read+"' AND user='"+cf_read+"'";
		Connection con = DMConnectionPool.getConnection();
		stet = (Statement) con.createStatement();
		try{
			ResultSet res = stet.executeQuery(sql);
			if( res.next()==true )
			{
					System.out.println("sono nel if ");
					giaEsiste=true;
					return giaEsiste;
			}else {
				System.out.println("nn ci sta ");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			con.close();
			stet.close();
		}
		return giaEsiste;
	}
}


