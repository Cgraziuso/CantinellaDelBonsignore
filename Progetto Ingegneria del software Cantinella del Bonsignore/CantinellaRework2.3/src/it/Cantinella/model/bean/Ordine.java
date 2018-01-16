package it.Cantinella.model.bean;

public class Ordine {
	
	private int idOrdine;				//identificativo dell'ordine
	private String usernameCliente;		//user del cliente intestatario dell'ordine
	private String emailTracciamento;	//email di comunicazione
	private String descrizione;			//descrizione dell'ordine
	private String indirizzo;			//indirizzo di spedizione
	private int zipCode;				//codice posale
	private String totale;				//totale Euro spesi 
	private String percentuale;			//percentuale di guadagno dell'Amministratore
	
	
	public Ordine() {
		super();
	}
	
	/*	costruttore 
		@Param usernameCliente user del cliente intestatario dell'ordine
		@Param emailTracciamento email di comunicazione
		@Param descrizione descrizione dell'ordine
		@Param zipCode codice posale
		@Param totale totale Euro spesi 
		@Param percentuale percentuale di guadagno dell'Amministratore
		@Param indirizzo indirizzo di spedizione
	 */
	public Ordine(String usernameCliente, String emailTracciamento, String descrizione, int zipCode, String totale, String percentuale, String indirizzo) {
		super();
		this.usernameCliente = usernameCliente;
		this.emailTracciamento = emailTracciamento;
		this.descrizione = descrizione;
		this.zipCode = zipCode;
		this.totale = totale;
		this.percentuale = percentuale;
		this.indirizzo=indirizzo;
	}

	
	public String getEmailTracciamento() {
		return emailTracciamento;
	}
	

	public void setEmailTracciamento(String emailTracciamento) {
		this.emailTracciamento = emailTracciamento;
	}


	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public int getIdOrdine() {
		return idOrdine;
	}


	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}


	public String getUsernameCliente() {
		return usernameCliente;
	}

	public void setUsernameCliente(String usernameCliente) {
		this.usernameCliente = usernameCliente;
	}


	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}


	public String getTotale() {
		return totale;
	}


	public void setTotale(String totale) {
		this.totale = totale;
	}


	public String getPercentuale() {
		return percentuale;
	}

	public void setPercentuale(String percentuale) {
		this.percentuale = percentuale;
	}


	@Override
	public String toString() {
		return "Ordine [idOrdine=" + idOrdine + ", usernameCliente=" + usernameCliente + ", emailTracciamento="
				+ emailTracciamento + ", descrizione=" + descrizione + ", indirizzo=" + indirizzo + ", zipCode="
				+ zipCode + ", totale=" + totale + ", percentuale=" + percentuale + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordine other = (Ordine) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (emailTracciamento == null) {
			if (other.emailTracciamento != null)
				return false;
		} else if (!emailTracciamento.equals(other.emailTracciamento))
			return false;
		if (idOrdine != other.idOrdine)
			return false;
		if (indirizzo == null) {
			if (other.indirizzo != null)
				return false;
		} else if (!indirizzo.equals(other.indirizzo))
			return false;
		if (percentuale == null) {
			if (other.percentuale != null)
				return false;
		} else if (!percentuale.equals(other.percentuale))
			return false;
		if (totale == null) {
			if (other.totale != null)
				return false;
		} else if (!totale.equals(other.totale))
			return false;
		if (usernameCliente == null) {
			if (other.usernameCliente != null)
				return false;
		} else if (!usernameCliente.equals(other.usernameCliente))
			return false;
		if (zipCode != other.zipCode)
			return false;
		return true;
	}





	
	
	

}
