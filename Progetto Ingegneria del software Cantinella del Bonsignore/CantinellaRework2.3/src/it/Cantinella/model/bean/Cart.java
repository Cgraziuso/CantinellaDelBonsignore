package it.Cantinella.model.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.Cantinella.model.bean.Vino;

public class Cart implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	private List<Vino> products;	//lista di vini da mantenere nel carrello
	private int numWine = 0;		//contatore del numero di vini presenti nella lista
	
	//costruttore 
	public Cart()
	{
		products = new ArrayList<Vino>();
		numWine = 0;
	}
	
	/*	aggiunge l'oggetto vino alla lista
		@param Wine vino da aggiungere
	*/
	public void addWine(Vino wine)
	{
		
		for(Vino p : products)
		{
			if(p.getIdVino() == wine.getIdVino())
			{
				p.setQuantita(p.getQuantita()+1);
				
				p.setPrezzo("" +(Float.parseFloat(p.getPrezzo())+ Float.parseFloat( wine.getPrezzo() ) ) );
				numWine++;
				return;
			}
		}
		
		
		products.add(wine);
		numWine++;
	}
	
	/*	cancellare l'oggetto vino dalla lista
		@param WineId identificativo del vino da cancellare
	*/
	public void deleteWine (int wineId)
	{
		
		for(Vino p : products)
		{
			if(p.getIdVino() == wineId)
			{
				numWine= numWine - p.getQuantita();
				products.remove(p);
				
				break;
			}
		}
	}
	
	/*	ottenere un riferimento al carrello
		@return List<Vino> lista di vini nel carrello
	*/
	public List<Vino> getWinesList()
	{
		return  products;
	}
	
	/*	restituisce il numero dei vini presenti nel carrello
		@return Int contatore dei vini
	*/
	public int getWineNumber()
	{
		return numWine;
	}
}

