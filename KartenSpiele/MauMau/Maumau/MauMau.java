package Maumau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class MauMau {
	
	private CardContainer deck;
	private CardContainer playedCards;
	
	public void initialisiereGame(int maxPlayer) {
		if(maxPlayer<2) {
			CardContainer[] player = new CardContainer[maxPlayer+1];
			prepareGame(player);
		}else {
			CardContainer[] player = new CardContainer[maxPlayer];
			prepareGame(player);
		}
		
	}
			
		
		private void prepareGame(CardContainer[] player) {
			CardContainer deck = new CardContainer();
			for(int i = 1 ; i < player.length % 4 ; i++) {
				deck.getDeck();
			}
			this.deck = shuffleDeck(deck);
			startGame(player);
	}


		
		

	private void startGame(CardContainer[] player) {
		//TODO nicht funktionsfähig
		System.out.println("start");
		System.out.println("size: " +deck.sizeHolder());
		int counter = 0;
		System.out.println("mittendrin");
		for(int a = 0 ; a <=5;a++) {
			for(int i=0; i < player.length-1;i++) {
				//Fehler tritt hier auf 
				player[i].takeCard(deck.playTopCard());
				System.out.println(counter);
				counter++;
			}
		}
		playGame(player);
		System.out.println("Herzlichen Glückwunsch Spieler");
		System.out.println("Nochmal spielen? y/n");
		String again = input(player[0]);
		if(again.contains("y")) {
			MainMenü.main(null);
		}else {
			System.out.println("Das Spiel wird beendet. Vielen Dank für´s Spielen. ");
			System.out.println("Created by Kai Danz");
		}
	}

	private void playGame(CardContainer[] player) {
		int round = 1;     
		boolean win = false;
		boolean cardEffeckt = false;
		drawTopCard(deck, playedCards);
		//Solange handkarten nicht = 0 
		//spiele 
		//
		/**
		 * außer in erster runde 
		 * nehme oberste Karte von playedCards 
		 * prüfe ob Karte 7 oder 8 ist 
		 * wenn 7 dann zieht spieler 2 karten oder legt eine weitere 7 um das ziehen weiterzugeben 
		 * wenn 8 setze aus 
		 * 
		 * bube kann auf alles gelegt werden 
		 * 
		 * wenn erste runde 
		 * spiele passende karte (getestet auf symbol oder wert)
		 * 
		 * Solange nicht gewonnen wird 
		 * gebe spieler in runde() 
		 * 
		 */
			while(!win) {
				for(int i = 0 ; i < player.length-1 ; i++) {
					if(round >1) {
						ausgabeDerHand(player[i]);
						isHandPlayable(playedCards.seeCard(0), player[i]);
						
						//Schaue zuletzt gespielte karte an 
						//Wenn 7 oder 8 dann tu etwas -> 7 = 2x ziehen 8 = aussetzen
					}else {
						playedCards.takeCard(player[i].playCard());
					}
				}
				
				
				
				/**
				 * @TODO:
				 * Prüfe welche Karte gespielt wurde 
				 * Wenn wert == 7 / 8 / As -> der nächste 2 ziehen / einmal aussetzen / negieren des Effektes
				 * 
				 */
				
				
				//Spieler2 spielt eine Karte
				//Prüfe ob spieler2 gewonnen
				/**
				 * @TODO:
				 * AI des Computers erstellen -> Überlegung, was diese KI alles wissen kann/sollte
				 * Vllt zwei verschiedene Schwierigkeitsgrade? einfach spielt einfach nur eine Karte aus der Hand und schwer speichert sich
				 * schon gespielte Karten und wählt anhand dessen die möglichst beste Karte aus seiner Hand aus, wodurch der 
				 * Gegenspieler wahrscheinlich eher ziehen muss, anstatt eine Karte aus seiner Hand zu spielen
				 */
				
				round++;
			}
	}
	

	private boolean isHandPlayable(Karte topPlayedCard, CardContainer spieler) {
		int card = 0;
		for(int i = 0; i < spieler.sizeHolder() ; i++) {
			Karte k = spieler.seeCard(i);
			if(k.getWert().equals(topPlayedCard.getWert()) || k.toString().equals(topPlayedCard.toString()) || k.getWert().equals("Bube")){
				return true;
			}
		}
		return false;
	}

	//Methode um eine Karte auszuwählen, welche man spielen möchte
	private void playCard(CardContainer playedCards,CardContainer player) {
		System.out.println("Bitte geben Sie ihre zu spielende Karte ein!");
		int cardToPlay = Integer.valueOf(input(player).replaceAll("[^1-9]", ""))-1;
		if(player.seeCard(cardToPlay).getWert().equals(playedCards.seeCard(0).getWert()) || 
		   player.seeCard(cardToPlay).toString().equals(playedCards.seeCard(0).toString()) ||
		   player.seeCard(cardToPlay).getWert().contains("Bube")) {
				playedCards.takeCard(player.playCard());
		}else {
			System.out.println("Bitte wählen Sie eine Karte aus dessen Wert ODER Symbol übereinstimmen");
			playCard(playedCards,player);
		}
		
		
		
	}

	private String input(CardContainer player) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String input = "";
		try {
			input = br.readLine();
		} catch (IOException e) {
			System.out.println("Bitte wählen Sie eine Karte zwischen 1 und " + player.sizeHolder() +" aus!" );
			input(player);
		}
		return input;
	}

	//Spieler zieht 6 Karten 
	private void drawInitialCards(CardContainer[] player) {
		while(player[0].sizeHolder()<6) {
			for(int i = 0 ; i < player.length-1;i++) {
				drawTopCard(deck,player[i]);
			}
			
		}
	}

	//Spieler zieht die oberste Karte vom Deck 
	private void drawTopCard(CardContainer takesCard, CardContainer givesCard) {
		takesCard.takeCard(givesCard.playTopCard());
	}

	//Gibt dem Spieler seine Handkarten auf dem Bildschirm aus
	private void ausgabeDerHand(CardContainer spieler) {
		int counter = 1;
		for(int i = 0 ; i < spieler.sizeHolder(); i++) {
			System.out.println("karte " + i+1 + ": " + spieler.seeCard(i));
		}
		
//		for(Karte k: spieler) {
//			System.out.println("Karte "+counter +": "+k.getKarte());
//			counter++;
//		}
		
	}

	//Shuffeld die Karten im Deck zufällig neu 
	private CardContainer shuffleDeck(CardContainer deck) {
		CardContainer shuffledDeck = new CardContainer();
		ArrayList<Integer> alreadyAddedCards = new ArrayList<Integer>();
		for(int a = deck.sizeHolder();a>0;a--) {
			int randomNumber =  new Random().nextInt(deck.sizeHolder())+0;
			if(!alreadyAddedCards.contains(randomNumber)) {
				alreadyAddedCards.add(randomNumber);
				shuffledDeck.takeCard(deck.seeCard(randomNumber));
				System.out.println(randomNumber);
			}else {
				a++;
			}
		}
		deck = shuffledDeck;
		return deck;
	}

	

	
	
}
