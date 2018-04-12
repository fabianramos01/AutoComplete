package models;

public class Letter {

	private char letter;
	private int visit;
	
	public Letter(char letter) {
		this.letter = letter;
	}
	
	public void addVisit() {
		visit++;
	}
	
	public char getLetter() {
		return letter;
	}
	
	public int getVisit() {
		return visit;
	}
	
	@Override
	public String toString() {
		return String.valueOf(letter);
	}
}
