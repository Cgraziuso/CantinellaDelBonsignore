package it.Cantinella.model.bean;

public class Utente {
	
	private String user;		//user univoco dell'utente registrato
	private String password;	//password dell'utente
	private String email;		//email di contatto dell'utente
	private String nome;		//nome dell'utente
	private String cognome;		//cognome dell'utente
	private String cf;			//codice fiscale dell'utente
	private boolean Seller;		//indica se l'utente è un venditore
	private String nomeAzienda;	//nome dell'azienda che rappresenta, solo se l'utente è venditore  
	private String piva;		//Partita Iva Dell'azienda, solo se l'utente è venditore 
	

	//costruttore 
	//@Param user user univoco dell'utente registrato
	//@Param password password dell'utente
	//@Param email email di contatto dell'utente
	//@Param nome nome dell'utente
	//@Param cognome cognome dell'utente
	//@Param cf codice fiscale dell'utente
	//@Param Seller indica se l'utente è un venditore
	public Utente(String user, String password, String email, String nome, String cognome, String cf, boolean Seller,
			String nomeAzienda, String piva) {
		super();
		this.user = user;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.Seller = Seller;
		this.nomeAzienda = nomeAzienda;
		this.piva = piva;
	}
	
	public Utente() {}



	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getCf() {
		return cf;
	}


	public void setCf(String cf) {
		this.cf = cf;
	}


	public boolean isSeller() {
		return Seller;
	}


	public void setSeller(boolean Seller) {
		this.Seller = Seller;
	}


	public String getNomeAzienda() {
		return nomeAzienda;
	}


	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}


	public String getPiva() {
		return piva;
	}


	public void setPiva(String piva) {
		this.piva = piva;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (cf == null) {
			if (other.cf != null)
				return false;
		} else if (!cf.equals(other.cf))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (Seller != other.Seller)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeAzienda == null) {
			if (other.nomeAzienda != null)
				return false;
		} else if (!nomeAzienda.equals(other.nomeAzienda))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (piva == null) {
			if (other.piva != null)
				return false;
		} else if (!piva.equals(other.piva))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	
	
	
	

}
