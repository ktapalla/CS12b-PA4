/**
* Kirsten Tapalla
* ktapalla@brandeis.edu
* March 24, 2021
* PA 4
* Deck class that initializes/constructs a Deck object (deck of cards)
* - can shuffle cards, draw next card, and has discarded cards
* - also has a toString method 
*/
import java.util.*;
public class Deck {

	/**
	 * Initializes final class constants -> deck size, array for suits, and array for values
	 * - complete decks always have 52 cards
	 * - there are only 4 possible suits
	 * - there are only 13 possible values 
	 */
	private final int deckSize = 52; 
	private final String[] sArr = {"Diamonds", "Hearts", "Spades", "Clubs"};
	private final int[] vArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	
	private Card[] deck, discardPile;
	private Random rand;
	private int drawNextCardInd;
	private Card nextCard;
	
	/**
	 * Class constructor - constructs a Deck object
	 * Automatically shuffles deck after creation 
	 */
	public Deck() {
		this.drawNextCardInd = 0;
		this.rand = new Random();
		this.discardPile = new Card[deckSize];
		this.deck = new Card[deckSize];
		int dInd = 0; 
		while (dInd != deckSize) {
			int sInd = 0;
			while (sInd != sArr.length) {
				String s = sArr[sInd];
				int vInd = 0;
				while (vInd != vArr.length) {
					int v = vArr[vInd];
					this.deck[dInd] = new Card(v, s);;
					dInd++;
					vInd++;
				}
				sInd++;
			}
		}
		shuffle();
	}
	
	/**
	 * Shuffles deck of cards
	 * - shuffleSize starts as deck size then decreases by 1 after every random card is 
	 * 	 taken so that the range properly adjusts  
	 * - runs through entire deck from beginning to end
	 * - shuffled deck ends up being in order from which random card is pulled 
	 *   (modern method example but swaps the random card with the first card not yet 
	 *   chosen rather than with the last card not yet chosen) 
	 */
	public void shuffle() {
		int shuffleSize = deckSize;
		for (int i = 0; i < deckSize; i++) {
			int dInd = this.rand.nextInt(shuffleSize) + i ;
			Card rCard = this.deck[dInd];
			Card iCard = this.deck[i];
			if (rCard != null && iCard != null) {
				this.deck[i] = rCard;
				this.deck[dInd] = iCard;
			}
			shuffleSize--;				
		}
	}
	
	/**
	 * @returns the next card in the deck and adds that card to the discard pile
	 * If every card in the deck has been drawn:
	 * - resets drawNextCardInd to 0, clones discardPile to the deck, shuffles deck, 
	 *   prints first card of newly shuffled deck, empties discardPile 
	 */
	public Card drawNextCard() {
		if (this.drawNextCardInd != deckSize) {
			this.nextCard = this.deck[this.drawNextCardInd];
			discard(this.nextCard);
			this.drawNextCardInd++;
		}
		else {
			this.drawNextCardInd = 0;
			this.deck = discardPile.clone();
			shuffle();
			this.discardPile = new Card[this.deckSize];
			this.nextCard = this.deck[this.drawNextCardInd];
			discard(this.nextCard);
			this.drawNextCardInd++;
		}
		return nextCard;
	}
	
	/**
	 * @param c = drawn card being discarded 
	 * Adds drawn cards to discard pile
	 */
	public void discard(Card c) {
		this.discardPile[this.drawNextCardInd] = c;
	}
	
}
	
