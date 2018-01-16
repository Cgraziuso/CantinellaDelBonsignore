package it.Cantinella.model.bean;

import java.io.Serializable;

public class Vino implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	
	private int idVino;				//identificativo del vino
	private String cfUser;			//codice fiscale del venditore
	private String nome;			//nome del vino
	private String descrizione;		//descrizione del vino 
	private String anno;			//annata
	private String prezzo;			//prezzo del vino
	private String immagine;		//url che contiene l'immagine del vino
	private String tipo;			//indica il colore
	private int quantita;			//quantità di bottiglie disponibili
	private int feedback;			//numero di consensi acquisiti
	
	
	
	//costruttore 
	//@Param idVino identificativo del vino
	//@Param cfUser codice fiscale del venditore
	//@Param nome nome del vino
	//@Param descrizione descrizione del vino 
	//@Param anno annata
	//@Param prezzo prezzo del vino
	//@Param immagine url che contiene l'immagine del vino
	//@Param tipo indica il colore
	//@Param quantita quantità di bottiglie disponibili
	//@Param feedback numero di consensi acquisiti
	
	public Vino(int idVino, String cfUser, String nome, String descrizione, String anno, String prezzo, String immagine,
						String tipo, int quantita, int feedback) 
	{
		
		this.idVino=idVino;
		this.cfUser = cfUser;
		this.nome = nome;
		this.descrizione = descrizione;
		this.anno = anno;
		this.prezzo = prezzo;
		this.immagine = immagine;
		this.tipo = tipo;
		this.quantita = quantita;
		this.feedback = feedback;
	}


	public Vino() {
		
	}


	public int getIdVino() {
		return idVino;
	}


	public void setIdVino(int idVino) {
		this.idVino = idVino;
	}


	public String getCfUser() {
		return cfUser;
	}


	public void setCfUser(String cfUser) {
		this.cfUser = cfUser;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public String getAnno() {
		return anno;
	}


	public void setAnno(String anno) {
		this.anno = anno;
	}


	public String getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}


	public String getImmagine() {
		return immagine;
	}


	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}


	public int getFeedback() {
		return feedback;
	}


	public void setFeedback(int feedback) {
		this.feedback = feedback;
	}


	@Override
	public String toString() {
		return "Vino [idVino=" + idVino + ", cfUser=" + cfUser + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", anno=" + anno + ", prezzo=" + prezzo + ", immagine=" + immagine + ", tipo=" + tipo + ", quantita="
				+ quantita + ", feedback=" + feedback + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vino other = (Vino) obj;
		if (anno == null) {
			if (other.anno != null)
				return false;
		} else if (!anno.equals(other.anno))
			return false;
		if (cfUser == null) {
			if (other.cfUser != null)
				return false;
		} else if (!cfUser.equals(other.cfUser))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (feedback != other.feedback)
			return false;
		if (idVino != other.idVino)
			return false;
		if (immagine == null) {
			if (other.immagine != null)
				return false;
		} else if (!immagine.equals(other.immagine))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (prezzo == null) {
			if (other.prezzo != null)
				return false;
		} else if (!prezzo.equals(other.prezzo))
			return false;
		if (quantita != other.quantita)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
