package Maumau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainMenü {

	public static void main(String[] args) {
		System.out.println("Bitte geben Sie die Spieleranzahl an");
		MauMau maumau = new MauMau();
		maumau.initialisiereGame(input());
	}

	
	private static int input() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String input = "";
		try {
			input = br.readLine();
		} catch (IOException e) {
			System.out.println("Fehler bei Ihrere Eingabe!");
		}
		int player = Integer.parseInt(input.replaceAll("[^1-9]", ""));
		return player;
	}
}
