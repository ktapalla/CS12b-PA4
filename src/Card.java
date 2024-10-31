/**
 * Kirsten Tapalla
 * ktapalla@brandeis.edu
 * March 24, 2021
 * PA 4
 * Card class that initializes/constructs a Card object
 * - Can retrieve card value, color, and suit 
 * - Can convert card object to its string representation 
 */
public class Card {

	private String color;
	private int value;
	private String suit;

	/**
	 * @param value = value of card 
	 * @param suit = suit of card 
	 * class constructor - constructs a Card object 
	 */
	public Card(int value, String suit) {
		this.value = value;
		this.suit = suit;
	}

	/** 
	 * @returns value of card 
	 */
	public int getValue() {
		return this.value;
	}

	/** 
	 * @returns suit of card 
	 */
	public String getSuit() {
		return this.suit;
	}

	/** 
	 * @returns true if card is red 
	 * - suit is either heart or diamond
	 */
	public boolean ifRed() {
		return (this.suit.charAt(0)== 'H' || this.suit.charAt(0)== 'h' 
				|| this.suit.charAt(0)== 'D' || this.suit.charAt(0)== 'd');
	}

	/** 
	 * @returns true if card is black 
	 * - suit is either spade or club 
	 */
	public boolean ifBlack() {
		return (this.suit.charAt(0)== 'S' || this.suit.charAt(0)== 's' 
				|| this.suit.charAt(0)== 'C' || this.suit.charAt(0)== 'c');
	}

	/** 
	 * @returns color of card based on suit 
	 */
	public String getColor() {
		if (ifRed()) {
			this.color = "Red";
		}
		else if(ifBlack()) {
			this.color = "Black";
		}
		return this.color;

	}

	/**
	 * @return string of name of special values and regular values
	 * - Special values: 1 = ace, 11 = jack, 12 = queen, 13 = king
	 */
	public String getName() {
		String name = "";
		if (this.value == 1) {
			name = "Ace";
		}
		else if (this.value == 11 ) {
			name = "Jack";
		}
		else if (this.value == 12 ) {
			name = "Queen";
		}
		else if (this.value == 13 ) {
			name = "King";
		}
		else {
			name += this.value;
		}
		return name;
	}

	/**
	 * returns string representation of card
	 */
	public String toString() {
		return this.getName() + " of " + this.suit;
	}

}
