package it.polito.tdp.ruzzle.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.ruzzle.db.DizionarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
	private final int SIZE = 4;
	private Board board ;
	private List<String> dizionario ;
	private StringProperty statusText ;
	
	
	
	public List<Pos> trovaParola(String parola)
	{
		Ricerca ric = new Ricerca();
		return ric.trovaParola(parola, board);
	}

	public Model() {
		this.statusText = new SimpleStringProperty() ;
		
		this.board = new Board(SIZE);
		DizionarioDAO dao = new DizionarioDAO() ;
		this.dizionario = dao.listParola() ;
		statusText.set(String.format("%d parole lette", this.dizionario.size())) ;
	
	}
	
	public void reset() {
		this.board.reset() ;
		this.statusText.set("Board Reset");
	}

	public Board getBoard() {
		return this.board;
	}

	public final StringProperty statusTextProperty() {
		return this.statusText;
	}
	

	public final String getStatusText() {
		return this.statusTextProperty().get();
	}
	

	public final void setStatusText(final String statusText) {
		this.statusTextProperty().set(statusText);
	}

	public List<String> trovaTutte() {
		
		// scorre tutte le parole del dizionario e per ogni valore va a vedere se è contenuta nella matrice o no
		List<String> tutte = new LinkedList<String>();
		for(String p : this.dizionario)
			
		{
			if(p.length()>1)
			{p=p.toUpperCase();
			
			if(this.trovaParola(p)!= null)
			{tutte.add(p);}
			
			}
		}
		return tutte;
	}
	

}
