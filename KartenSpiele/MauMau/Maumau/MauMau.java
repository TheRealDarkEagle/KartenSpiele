package Maumau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class MauMau {
	//Ändern -> deck soll auch zu einem Typ CardHolder werden!
	private ArrayList<Karte> deck = new ArrayList<Karte>();
	
	public void initialisiereGame(int maxPlayer) {
		
		CardContainer[] player = new CardContainer[maxPlayer];
		prepareGame(player);
	}
			
		
		private void prepareGame(CardContainer[] player) {
			CardContainer deck = new CardContainer();
			for(int i = 0 ; i <= player.length % 4 ; i++) {
				deck = getDeck(deck);
			}
			deck = shuffleDeck(deck);
			startGame(player);
	}


		
		

	private void startGame(CardContainer[] player) {
		drawInitialCards();
		ArrayList<Karte> playedCards = new ArrayList<Karte>();
		playedCards.add(deck.get(0));
		deck.remove(0);
		int won = playGame(playedCards, player);
		System.out.println("Herzlichen Glückwunsch Spieler"+won);
		System.out.println("Nochmal spielen? y/n");
		String again = input(player[0]);
		if(again.contains("y")) {
			MainMenü.main(null);
		}else {
			System.out.println("Das Spiel wird beendet. Vielen Dank für´s Spielen. ");
			System.out.println("Created by Kai Danz");
		}
	}

	private int playGame(ArrayList<Karte> playedCards, CardContainer[] player) {
		boolean firstRound = true;
		int counter = 0;     
		boolean win = false;
		drawTopCard(deck, playedCards);
		removeTopCard(deck);
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
					if(!firstRound) {
						
					}else {
						playedCards.add(0, player[i].playCard());
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
				
				if(player.size()==0) {
					return 2;
				}
				if(counter==player.length-1) {
					counter = 0;
				}else {
					counter++;
				}
			}
			return counter;
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
	private void playCard(ArrayList<Karte> playedCards,CardContainer player) {
		System.out.println("Bitte geben Sie ihre zu spielende Karte ein!");
		int cardToPlay = Integer.valueOf(input(player).replaceAll("[^1-9]", ""))-1;
		if(player.seeCard(cardToPlay).getWert().equals(playedCards.get(0).getWert()) || 
		   player.seeCard(cardToPlay).toString().equals(playedCards.get(0).toString()) ||
		   player.seeCard(cardToPlay).getWert().contains("Bube")) {
				playedCards.add(0, player.playCard(cardToPlay));
		}else {
			System.out.println("Bitte wählen Sie eine Karte aus dessen Wert ODER Symbol übereinstimmen");
			playCard(playedCards,player);
		}
		
		
		
	}

	private String input(CardContainer spieler) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String input = "";
		try {
			input = br.readLine();
		} catch (IOException e) {
			System.out.println("Bitte wählen Sie eine Karte zwischen 1 und "+ spieler.sizeHolder()+" aus!" );
			input(spieler);
		}
		return input;
	}

	//Spieler zieht 6 Karten 
	private void drawInitialCards() {
		while(hand.size()<6) {
			drawTopCard(deck,hand);
			drawTopCard(deck,hand2);
		}
	}

	//Entfernt die oberste Karte vom Deck
	private ArrayList<Karte> removeTopCard(ArrayList<Karte> deck) {
		deck.remove(0);
		return deck;
	}

	//Spieler zieht die oberste Karte vom Deck 
	private ArrayList<Karte> drawTopCard(ArrayList<Karte> deck, ArrayList<Karte> hand) {
		hand.takeCard(deck.get(0));
		deck.remove(deck.get(0));
		return hand;
	}

	//Gibt dem Spieler seine Handkarten auf dem Bildschirm aus
	private void ausgabeDerHand(CardContainer spieler) {
		int counter = 1;
		for(Karte k:spieler) {
			System.out.println("Karte "+counter +": "+k.getKarte());
			counter++;
		}
		
	}

	//Shuffeld die Karten im Deck zufällig neu 
	private CardContainer shuffleDeck(CardContainer deck) {
		CardContainer shuffledDeck = new CardContainer();
		ArrayList<Integer> alreadyAddedCards = new ArrayList<Integer>();
		for(int a = deck.sizeHolder();a>0;a--) {
			int randomNumber =  new Random().nextInt(deck.sizeHolder())+0;
			if(!alreadyAddedCards.contains(randomNumber)) {
				alreadyAddedCards.add(randomNumber);
				shuffledDeck.takeCard((deck.playCard(randomNumber)));
			}else {
				a++;
			}
		}
		deck = shuffledDeck;
		return deck;
	}

	//Erstellt ein Deck mit 32 Karten -> 7-As 1x je Symbol 
	private CardContainer getDeck(CardContainer deck){
		deck.takeCard((new Karte("7", "HERZ")));
		deck.takeCard((new Karte("8", "HERZ")));
		deck.takeCard((new Karte("9", "HERZ")));
		deck.takeCard((new Karte("10", "HERZ")));
		deck.takeCard((new Karte("Bube", "HERZ")));
		deck.takeCard((new Karte("Dame", "HERZ")));
		deck.takeCard((new Karte("König", "HERZ")));
		deck.takeCard((new Karte("As", "HERZ")));
		deck.takeCard((new Karte("7", "KARO")));
		deck.takeCard((new Karte("8", "KARO")));
		deck.takeCard((new Karte("9", "KARO")));
		deck.takeCard((new Karte("10", "KARO")));
		deck.takeCard((new Karte("Bube", "KARO")));
		deck.takeCard((new Karte("Dame", "KARO")));
		deck.takeCard((new Karte("König", "KARO")));
		deck.takeCard((new Karte("As", "KARO")));
		deck.takeCard((new Karte("7", "SCHIPPE")));
		deck.takeCard((new Karte("8", "SCHIPPE")));
		deck.takeCard((new Karte("9", "SCHIPPE")));
		deck.takeCard((new Karte("10", "SCHIPPE")));
		deck.takeCard((new Karte("Bube", "SCHIPPE")));
		deck.takeCard((new Karte("Dame", "SCHIPPE")));
		deck.takeCard((new Karte("König", "SCHIPPE")));
		deck.takeCard((new Karte("As", "SCHIPPE")));
		deck.takeCard((new Karte("7", "KREUZ")));
		deck.takeCard((new Karte("8", "KREUZ")));
		deck.takeCard((new Karte("9", "KREUZ")));
		deck.takeCard((new Karte("10", "KREUZ")));
		deck.takeCard((new Karte("Bube", "KREUZ")));
		deck.takeCard((new Karte("Dame", "KREUZ")));
		deck.takeCard((new Karte("König", "KREUZ")));
		deck.takeCard((new Karte("As", "KREUZ")));
		return deck;
	}

	
	
}
