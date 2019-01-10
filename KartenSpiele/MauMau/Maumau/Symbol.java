package Maumau;

public enum Symbol {

	HERZ("Rot"),
	KARO("Rot"),
	SCHIPPE("Schwarz"),
	KREUZ("Schwarz");
	
	private final String color;
	
	private Symbol(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}
	
	
	public String getSymbol() {
		return this.toString();
	}
	
}
