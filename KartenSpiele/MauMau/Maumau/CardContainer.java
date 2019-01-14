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
	
	private ArrayList<Karte> hand= new ArrayList<Karte>();;
	

	
	
	//Anzahl der Karten 
	/**
	 * Gibt Anzahl der vorhenden Karten
	 * @return
	 */
	public int sizeHolder() {
		return hand.size();
	}
	
	/*
	 * Karte aufnehmen 
	 */
	public void takeCard(Karte karte) {
		hand.add(0, karte);
	}
	
	/**
	 * Karte spielen 
	 * @return
	 */
	public Karte playCard() {
		int card = selectCard();
		Karte thisCard = hand.get(card);
		hand.remove(thisCard);
		return thisCard;
	}
	
	/**
	 * gibt die erste (index = 0) Karte zurück
	 * @return
	 */
	public Karte playTopCard() {
		Karte thisCard = hand.get(0);
		hand.remove(0);
		return thisCard;
	}
	
	/**
	 * Gibt die angebebene Karte zurück
	 * @param card
	 * @return
	 */
	public Karte seeCard(int card) {
		return hand.get(card);
	}
	
	/**
	 * Wähle eine Karte aus
	 * @return
	 */
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
	
	/**
	 * Erstellt ein Deck mit 32 Karten -> 7-As 1x je Symbol 
	 * @param deck
	 * @return
	 */
	public void getDeck (){
		hand.add((new Karte("7", "HERZ")));
		hand.add((new Karte("8", "HERZ")));
		hand.add((new Karte("9", "HERZ")));
		hand.add((new Karte("10", "HERZ")));
		hand.add((new Karte("Bube", "HERZ")));
		hand.add((new Karte("Dame", "HERZ")));
		hand.add((new Karte("König", "HERZ")));
		hand.add((new Karte("As", "HERZ")));
		hand.add((new Karte("7", "KARO")));
		hand.add((new Karte("8", "KARO")));
		hand.add((new Karte("9", "KARO")));
		hand.add((new Karte("10", "KARO")));
		hand.add((new Karte("Bube", "KARO")));
		hand.add((new Karte("Dame", "KARO")));
		hand.add((new Karte("König", "KARO")));
		hand.add((new Karte("As", "KARO")));
		hand.add((new Karte("7", "SCHIPPE")));
		hand.add((new Karte("8", "SCHIPPE")));
		hand.add((new Karte("9", "SCHIPPE")));
		hand.add((new Karte("10", "SCHIPPE")));
		hand.add((new Karte("Bube", "SCHIPPE")));
		hand.add((new Karte("Dame", "SCHIPPE")));
		hand.add((new Karte("König", "SCHIPPE")));
		hand.add((new Karte("As", "SCHIPPE")));
		hand.add((new Karte("7", "KREUZ")));
		hand.add((new Karte("8", "KREUZ")));
		hand.add((new Karte("9", "KREUZ")));
		hand.add((new Karte("10", "KREUZ")));
		hand.add((new Karte("Bube", "KREUZ")));
		hand.add((new Karte("Dame", "KREUZ")));
		hand.add((new Karte("König", "KREUZ")));
		hand.add((new Karte("As", "KREUZ")));
	}
	
}
