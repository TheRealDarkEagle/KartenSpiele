package Maumau;

public class Karte {

	String wert;
	Symbol symbol;
	
	public Karte(String wert, String symbol) {
		this.wert = wert;
		this.symbol = getSymbol(symbol);
	}

	private Symbol getSymbol(String symbol) {
		switch(symbol){
		case "HERZ":
			return Symbol.HERZ;
		case "KARO":
			return Symbol.KARO;
		case "KREUZ":
			return Symbol.KREUZ;
		case "SCHIPPE":
			return Symbol.SCHIPPE;
		}
		return null;
	}
	
	@Override
	/**
	 * Gibt ENUM der Karte zurück
	 */
	public String toString() {
		return this.symbol.toString();
	}
	
	/**
	 * Gibt den Wert der Karte zurück z.B. 7 / Dame / Bube 
	 * @return
	 */
	public String getWert() {
		return this.wert;
	}
	
	/**
	 * Gibt das Symbol UND den Wert zurück
	 * @return
	 */
	public String getKarte() {
		return this.symbol + " " + this.wert;
	}

}
