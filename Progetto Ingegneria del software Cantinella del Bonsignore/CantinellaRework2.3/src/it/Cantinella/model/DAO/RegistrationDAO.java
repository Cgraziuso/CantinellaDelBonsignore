package it.Cantinella.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

import it.Cantinella.model.connection.DMConnectionPool;
import it.Cantinella.model.bean.Utente;

public class RegistrationDAO {
	
	/*	salva le informazioni dell'utente che intende registrarsi sul sito, nel DataBase
		@param Utente newuserbean utente da registrare nel DataBase
		@return boolean true se la registrazione ha avuto successo false altrimenti
	*/
	public RegistrationDAO(){}
	public boolean setRegistration(Utente newuserbean) throws SQLException{
		System.out.println("qua ci siamo");
		Connection con = null;
		PreparedStatement prepstet = null;
		boolean ret = false;

		String nome = newuserbean.getNome();
		String cognome = newuserbean.getCognome();
		String email = newuserbean.getEmail();
		String cf = newuserbean.getCf();
		String username = newuserbean.getUser();
		String password = newuserbean.getPassword();
		Boolean isSeller= newuserbean.isSeller();
		String nomeAzienda=newuserbean.getNomeAzienda();
		String piva=newuserbean.getPiva();

		String query = "INSERT INTO utenti (user, password, email, nome, cognome, cf, isSeller, nomeAzienda, piva )VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try{
				con = DMConnectionPool.getConnection();
				
				prepstet = con.prepareStatement(query);
				prepstet.setString(1, username);
				prepstet.setString(2, password);
				prepstet.setString(3, email);
				prepstet.setString(4, nome);
				prepstet.setString(5, cognome);
				prepstet.setString(6, cf);
				prepstet.setBoolean(7, isSeller);
				prepstet.setString(8, nomeAzienda);
				prepstet.setString(9, piva);

				prepstet.executeUpdate();
				con.commit();
				ret = true;

			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				prepstet.close();
				con.close();
			}
		return ret;
	}
	
	/*	controlla se l'utente è gia registrato nel DataBase
		@param String cf codice fiscale dell'utente parametro della ricerca
		@return boolean true se l'utente è gia registrato false altrimenti
	 */
	public boolean checkCF(String cf) throws SQLException{
		boolean giaEsiste=false;

		Statement stet;
		String sql ="SELECT cf FROM utenti;";
		Connection con = DMConnectionPool.getConnection();

		stet = con.createStatement();
		try{
			ResultSet res = stet.executeQuery(sql);
			while( res.next() )
			{
				System.out.println(res.getString("cf"));
				/////!!!!!!!!!!!
				if(res.getString("cf").equals(cf))
				{
					System.out.println("sono nel if ");
					giaEsiste=true;
					return giaEsiste;
				}
				System.out.println("sono nel while");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			con.close();
			stet.close();
		}
		return giaEsiste;
	}
	
	/*	controlla se l'utente è gia registrato nel DataBase
		@param String user user dell'utente parametro della ricerca
		@return boolean true se l'utente è gia registrato false altrimenti
	 */
	public boolean checkUser(String user) throws SQLException{
		boolean giaEsiste=false;

		Statement stet;
		String sql ="SELECT user FROM utenti;";
		Connection con = DMConnectionPool.getConnection();

		stet = con.createStatement();
		try{
			ResultSet res = stet.executeQuery(sql);

			while( res.next() )
			{
				System.out.println(res.getString("user"));
				/////!!!!!!!!!!!
				if(res.getString("user").equals(user))
				{
					System.out.println("sono nel if ");
					giaEsiste=true;
					return giaEsiste;
				}
				System.out.println("sono nel while");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			con.close();
			stet.close();
		}
		return giaEsiste;
	}
	
	/*	elimina un utente registrato dal DataBase
	 	@param String cf codice fiscale dell'utente parametro della ricerca
		@return boolean true se la cancellazione ha avuto successo false altrimenti
	*/
	public boolean eliminaUser(String cf) throws SQLException{
		Connection con = null;
		PreparedStatement prepstate = null;
		String query = " DELETE FROM utenti  WHERE  cf= ?";
		try{
			con = DMConnectionPool.getConnection();
			prepstate = con.prepareStatement(query);
			prepstate = con.prepareStatement(query);
			prepstate.setString(1, cf);
	
			prepstate.executeUpdate();
			con.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			prepstate.close();
			con.close();
		}
		return false;
	}
}
