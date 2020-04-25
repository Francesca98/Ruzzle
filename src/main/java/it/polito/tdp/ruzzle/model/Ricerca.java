package it.polito.tdp.ruzzle.model;

import java.util.LinkedList;
import java.util.List;

public class Ricerca {

	
	
	/*mi dice se una parola è presente nella matrice ruzzle che so utilizando*/
	public List<Pos> trovaParola(String parola,Board board)
	{ /*vado prima a vedere se la matrice contiene la prima lettera della mia parola altrimenti non ha senso cercare*/
		 
		for(Pos p : board.getPositions())
		{
			//chiedo se ogni carattere corrispondete alla posizione è uguale alla proima lettera della mia parola 
		   if(board.getCellValueProperty(p).get().charAt(0)==  parola.charAt(0) ) 
		   { // è un potenziale inizio di ricorsione
			  List<Pos> percorso = new LinkedList<>(); //contiene tutte le posioni delle lettere delle parole che compongono la parola se la dovessi trovare
		   percorso.add(p);
		   /*FUNZIONE RICORSIVA : parola è la parola da cercare, livello =1 xk già ci ho inserito un elemento; soluzione parziale è percorso
		    *  Se mi restituisce vero vuol dire che ho trovato una soluzione e al che restituisco la lista delle posizini in modo da rintracciare la parola*/
		  if( cerca(parola,1,percorso,board) )
		   return percorso;
		   }
			   /*questo metodo restituisce la string property corrispondende alla posizione specificata
		ma non mi restituisce direttamente una stringa ma una proprietà che la include per ottenere la stringa si deve fare .get -->
		.get() mi restituisce una stringa ma io voglio convenrtirla in un carattero faccio charAt ecc*/
		}
		return null;}

	
	
	private boolean cerca(String parola, int livello, List<Pos> percorso, Board board)
	{ // caso terminale 
		/*se riesco a fare un percorso lungo esattamente quanto la parola vuol dire che ho trovato un percorso, perche mi basta una */
		
		if(livello == parola.length())
		return true; 
		
		//CASO INTERMEDIO  
		/*
		 * Generazione delle soluzioni : 
     Trovare tutte le posizioni che siano :
      -adiacenti all'ultima posizione usata
      -contenenti la lettera 'corretta'
      -non ancora utilizzate*/
		Pos ultima = percorso.get(percorso.size()-1); //è l'ultima cosa che ho inserito nella mia posizione parziale e dato questo mi prendo gli adiacenti
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		for (Pos p : adiacenti )
		{
			if(!percorso.contains(p) && parola.charAt(livello) == board.getCellValueProperty(p).get().charAt(0))//check se questo adiacente è già stato utilizzato o no
				// e poi vado a controllare se il secondo carattere è uguale ad uno dei caratteri che mi trovo tra quell adiacenti
			{
				 //faccio ricorsione
				percorso.add(p);
			if(	cerca(parola, livello+1, percorso, board))
				return true; 
			/*perche non è un problema di ottimizzazione quindi appena trovo la prima esco non mi interessa trovare la migliore*/
				percorso.remove(percorso.size()-1);
			}
		}
		
		
		
		
		return false;
	}

}
