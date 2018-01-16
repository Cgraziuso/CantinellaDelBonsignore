package it.Cantinella.model.DAO;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import it.Cantinella.model.connection.DMConnectionPool;


public class LoginDAO {

	
	
	/* 	controlla se l'utente è registrato nel DataBase
 		@param String nome user dell'utente
 		@param String pass password dell'utente
 		@return boolean true se l'utente è registrato false altrimenti
	 */
	public boolean authenticateUser(String nome, String pass){
		String username = nome;
		String password = pass;
		System.out.println("Username : " + username + "/nPassword "+ password);
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String userDBmatch = "";
		String passDBmatch = "";
		String query = "SELECT user,password FROM utenti";
		try{
			
			con = DMConnectionPool.getConnection();
			statement = (Statement) con.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				userDBmatch = resultSet.getString("user");
				passDBmatch = resultSet.getString("password");
				if (userDBmatch.equals(username) && passDBmatch.equals(password)){
					return true;
				}
			}
			statement.close();
			resultSet.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}
}
