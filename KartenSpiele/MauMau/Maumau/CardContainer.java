package Maumau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Besitz die Hand Karten des Spieles
 * @author danzk
 *
 */
public class CardContainer {

	private ArrayList<Karte> cardOnHand = new ArrayList<Karte>();
	
	//Anzahl der Karten 
	/**
	 * Gibt Anzahl der vorhenden Karten
	 * @return
	 */
	public int sizeHolder() {
		return cardOnHand.size();
	}
	//Karte aufnehmen 
	public void takeCard(Karte karte) {
		cardOnHand.add(karte);
	}
	
	//Karte spielen 
	public Karte playCard() {
		int card = selectCard();
		Karte thisCard = cardOnHand.get(card);
		cardOnHand.remove(thisCard);
		return thisCard;
	}
	
	public Karte seeCard(int card) {
		return cardOnHand.get(card);
	}
	
	private int selectCard() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String input = "";
		try {
			input = br.readLine();
		} catch (IOException e) {
			System.out.println("Fehler bei Ihrere Eingabe!");
		}
		int cardNumber = Integer.parseInt(input.replaceAll("[^1-9]", ""));
		return cardNumber-1;
		
	}
	
}
