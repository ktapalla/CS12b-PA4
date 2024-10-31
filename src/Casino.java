/**
* Kirsten Tapalla
* ktapalla@brandeis.edu
* March 24, 2021
* PA 4
* Casino class that allows you to play the War game
* - keeps track of games played, won, lost, and tied
* - minimum amount required to play is 100
* - uses integers only 
* - user's bet must be between 1% and 10% of their starting value (inclusive)
* - bet range doesn't change 
*/
import java.util.*;
public class Casino {
	
	public static final int minToPlay = 100;
	
	/**
	 * @param initialMon - user's entered value (what they have)
	 * @param cons - scanner to check int input (user money)
	 * @returns valid amount for user to play
	 * Method checks if user has enough money to play
	 * - continues to ask for valid amount if enough wasn't entered
	 */
	public static int checkMon (int initialMon, Scanner cons) {
		if (initialMon  < minToPlay) {
			System.out.print("Error. Not enough money." + '\n'
					+ "Enter enough to play: ");
			initialMon = cons.nextInt();
		}
		return initialMon;
	}
	
	/**
	 * @param initialMon - user's starting amount of money
	 * @returns 1% of initialMon
	 * 	Method sets minimum bet required 
	 * - remains the same throughout the game
	 */
	public static int setMin (int initialMon) {
		return initialMon / 100;
	}
	
	/**
	 * @param initialMon - user's starting amount of money
	 * @return 10% of initialMon
	 * Method sets maximum bet required
	 * - remains the same throughout the game
	 */
	public static int setMax (int initialMon) {
		return initialMon / 10;
	}
	
	/**
	 * @param bet - bet entered by user
	 * @param bMin - minimum bet required 
	 * @param bMax - maximum bet required 
	 * @param cons - scanner to check int input (user's bet)
	 * @returns valid bet value 
	 * Method checks if the given bet is within the proper range
	 * - continues to ask for user's bet is what was entered wasn't a valid amount
	 */
	public static int checkBet(int bet, int bMin, int bMax, Scanner cons) { 
		while (bet < bMin || bet > bMax) {
			System.out.println("Error. Bet invalid. Must be between $" + bMin + " and $" + bMax + ". Please change bet." );
			System.out.print("New bet: ");
			bet = cons.nextInt();
		}
		System.out.println();
		return bet;
	}
	
	/**
	 * @param cons - scanner to read user's input 
	 * @param d - Deck of cards 
	 * @param totalMon - total money user has
	 * @param bMin - set min bet based on initial amount of money user entered
	 * @param bMax - set max bet based on initial amount of money user entered
	 * @param rCount - number of rounds played 
	 * @param wCount - number of rounds won 
	 * @param lCount - number of rounds lost 
	 * @param tCount - number of rounds tied 
	 * Method runs War game 
	 * - initializes user's bet, dealer card, and player card
	 * - tests who (player or dealer) wins -> runs method based on round's winer (or tied round)
	 */
	public static void playWarGame (Scanner cons, Deck d, int totalMon, int bMin, int bMax, int rCount, int wCount, int lCount, int tCount) {
		System.out.print("How much would you like to bet (integers only): ");
		int bet = checkBet(cons.nextInt(), bMin, bMax, cons);
		rCount++;
		Card dCard = d.drawNextCard();
		Card pCard = d.drawNextCard();
		System.out.print("Dealer's Card: " + dCard.toString() + '\n');
		System.out.print("Player's Card: " + pCard.toString() + '\n');
		if (pWins(dCard, pCard)) {
			/**
			 * player wins/dealer loses, wCount increased by 1, 
			 * prints greater value statement, winner method runs
			 */
			wCount++;
			System.out.println(pCard.getName() + " is greater than " + dCard.getName() + '\n' + "Player wins round!");
			winner(cons, d, totalMon, bet, bMin, bMax, rCount, wCount, lCount, tCount);
		}
		else if(dWins(dCard, pCard)) {
			/**
			 * dealer wins/player loses, lCount increased by 1, 
			 * prints greater value statement, loser method runs
			 */
			lCount++;
			System.out.println(dCard.getName() + " is greater than " + pCard.getName() + '\n' + "Dealer wins round!");
			loser(cons, d, totalMon, bet, bMin, bMax, rCount, wCount, lCount, tCount);
		}
		else if (tie(dCard, pCard)) {
			/**
			 * tie - no winner, tCount increased by 1, 
			 * prints equal value statement and user's money, 
			 * asks if user wants to play again (runs playAgain method)
			 */
			tCount++;
			System.out.println(pCard.getName() + " is equal to " + dCard.getName() + '\n' + "Tie - no winner!" );
			System.out.println("User's Total Money: $" + totalMon);
			playAgain(cons, d, totalMon, bet, bMin, bMax, rCount, wCount, lCount, tCount);
		}
	}
	
	/**
	 * @param cons - scanner to read user's input 
	 * @param d - Deck of cards 
	 * @param totalMon - total money user has 
	 * @param bMin - min bet 
	 * @param bMax - max bet 
	 * @param rCount - rounds played 
	 * @param wCount - rounds won
	 * @param lCount - rounds lost 
	 * @param tCount - rounds tied 
	 * Method used when player wins
	 * - bet is added to totalMon
	 */
	public static void winner(Scanner cons, Deck d, int totalMon, int bet, int bMin, int bMax, int rCount, int wCount, int lCount, int tCount) {
		totalMon += bet;
		System.out.println("User's Total Money: $" + totalMon);
		playAgain(cons, d, totalMon, bet, bMin, bMax, rCount, wCount, lCount, tCount);
	}

	/**
	 * @param cons - scanner to read user's input 
	 * @param d - Deck of cards 
	 * @param totalMon - total money user has 
	 * @param bMin - min bet 
	 * @param bMax - max bet 
	 * @param rCount - rounds played 
	 * @param wCount - rounds won
	 * @param lCount - rounds lost 
	 * @param tCount - rounds tied 
	 * Method used when player loses
	 * - bet is subtracted from totalMon
	 * - if totalMon is less than or equal to 0, informs player and asks if they want to keep playing
	 */
	public static void loser(Scanner cons, Deck d, int totalMon, int bet, int bMin, int bMax, int rCount, int wCount, int lCount, int tCount) {
		totalMon -= bet;
		if (totalMon <= 0) {
			System.out.print("Not enough funds. Would you like to keep playing? ");
			String input = cons.next();
			fundResp(cons, input, d, totalMon, bMin, bMax, rCount, wCount, lCount, tCount);
		}
		else {
			System.out.println("User's Total Money: $" + totalMon);
			playAgain(cons, d, totalMon, bet, bMin, bMax, rCount, wCount, lCount, tCount);
		}
		
	}
	
	/**
	 * @param cons - scanner to read user's input 
	 * @param d - Deck of cards 
	 * @param totalMon - total money user has 
	 * @param bMin - min bet 
	 * @param bMax - max bet 
	 * @param rCount - rounds played 
	 * @param wCount - rounds won
	 * @param lCount - rounds lost 
	 * @param tCount - rounds tied 
	 * Method used to ask if player wants to play again
	 */
	public static void playAgain(Scanner cons, Deck d, int totalMon, int bet, int bMin, int bMax, int rCount, int wCount, int lCount, int tCount) { 
		System.out.print('\n' + "Would you like to play another round?: ");
		checkResp(cons, d, totalMon, bMin, bMax, rCount, wCount, lCount, tCount);
		
	}
	
	/**
	 * @param cons - scanner to read user's input 
	 * @param d - Deck of cards 
	 * @param totalMon - total money user has 
	 * @param bMin - min bet 
	 * @param bMax - max bet 
	 * @param rCount - rounds played 
	 * @param wCount - rounds won
	 * @param lCount - rounds lost 
	 * @param tCount - rounds tied 
	 * Method used to check player's response when asked in playAgain
	 */
	public static void checkResp(Scanner cons, Deck d, int totalMon, int bMin, int bMax, int rCount, int wCount, int lCount, int tCount) {
		String input = cons.next();
		if (input.charAt(0) == 'Y' || input.charAt(0) == 'y') {
			System.out.println();
			playWarGame(cons, d, totalMon, bMin, bMax, rCount, wCount, lCount, tCount);
		}
		else if (input.charAt(0) == 'N' || input.charAt(0) == 'n') {
			endGame(rCount , wCount, lCount, tCount, totalMon);
		}
	}
	
	/**
	 * @param cons - scanner to read user's input 
	 * @param d - Deck of cards 
	 * @param totalMon - total money user has 
	 * @param bMin - min bet 
	 * @param bMax - max bet 
	 * @param rCount - rounds played 
	 * @param wCount - rounds won
	 * @param lCount - rounds lost 
	 * @param tCount - rounds tied 
	 * Method used to check player's response when asked if they want to keep playing when lacking funds
	 */
	public static void fundResp(Scanner cons, String input, Deck d, int totalMon, int bMin, int bMax, int rCount, int wCount, int lCount, int tCount) {
		if (input.charAt(0) == 'Y' || input.charAt(0) == 'y') {
			System.out.print("Deposit more money: ");
			int dep = cons.nextInt();
			dep = checkMon(dep, cons);
			totalMon += dep;
			bMin = setMin(totalMon);
			bMax = setMax(totalMon);
			playWarGame(cons, d, totalMon, bMin, bMax, rCount, wCount, lCount, tCount);
		}
		else if (input.charAt(0) == 'N' || input.charAt(0) == 'n') {
			endGame(rCount , wCount, lCount, tCount, totalMon);
		}
		
	}
	
	/**
	 * @param dCard - dealer's card
	 * @param pCard - player's card
	 * @returns true if player wins 
	 * - player's card value is greater than dealer's card value
	 */
	public static boolean pWins(Card dCard, Card pCard) {
		return dCard.getValue() < pCard.getValue();
	}
	
	/**
	 * @param dCard - dealer's card
	 * @param pCard - player's card
	 * @returns true if dealer wins 
	 * - dealer's card value is greater than player's card value
	 */
	public static boolean dWins(Card dCard, Card pCard) {
		return dCard.getValue() > pCard.getValue();
	}
	
	/**
	 * @param dCard - dealer's card
	 * @param pCard - player's card
	 * @returns true if round is tied 
	 * - player's card value and dealer's card value are the same
	 */
	public static boolean tie(Card dCard, Card pCard) {
		return dCard.getValue() == pCard.getValue();
	}
	
	/**
	 * @param rCount - rounds played (final count)
	 * @param wCount - rounds won(final count)
	 * @param lCount - rounds lost (final count)
	 * @param tCount - rounds tied (final count)
	 * @param totalMon - player's totalMon (final value) 
	 * Method runs when game is over (user has indicated they no longer wanted to play)
	 * - prints final game details/information 
	 */
	public static void endGame(int rCount, int wCount, int lCount, int tCount, int totalMon) {
		System.out.println("Game over." + '\n');
		System.out.printf("%-15s%d\n", "Rounds Played:", rCount);
		System.out.printf("%-15s%d\n", "Rounds Won:", wCount);
		System.out.printf("%-15s%d\n", "Rounds Lost:", lCount);
		System.out.printf("%-15s%d\n", "Rounds Tied:", tCount);
		System.out.println("User's Total Money: $" + totalMon);
	}
	
	/**
	 * Main method 
	 * - initializes certain variables and runs game
	 */
	public static void main(String[] args) {
		int rCount = 0, wCount = 0, lCount = 0, tCount = 0;
		Scanner cons = new Scanner(System.in);
		String gameIntro = "Let's play a game of War. You must have at least $" + minToPlay + " to play." + '\n'
				+ "Your bets have to be betweem 1% and 10% of your initial deposit." + '\n'  
				+ "The bet range will remain the same throughout the entire game." + '\n';
		System.out.println(gameIntro);
		System.out.print("How much money do you have (integers only): ");
		int initialMon = checkMon(cons.nextInt(), cons);
		int bMin = setMin(initialMon);
		int bMax = setMax(initialMon);
		System.out.println("Your bets must be between: $" + bMin + " and $" + bMax + " (inclusive)" + '\n');
		int totalMon = initialMon;
		Deck d = new Deck();
		playWarGame(cons, d, totalMon, bMin, bMax, rCount, wCount, lCount, tCount);
	}
	
}
