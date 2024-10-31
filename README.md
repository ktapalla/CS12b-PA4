# README - COSI 12b Programming Assignment 4

The code provided in this repository contains the solutions to PA4 for COSI 12b - Advanced Programming Techniques in Java. The problem will be described below, as well as the instructions for how to install and run the code. 

Note: This assignment was done for a class where we were sometimes limited to using only Java features we had covered in class thus far. This may make the assignment solutions more complicated/longer than they would have to be without those limitations set. 

## Installation and Execution 

Get the files from GitHub and in your terminal/console move into the src folder of the project. To compile all of the java files for the entire assignment, run the following line: 

``` javac *.java ```

After compiling, run the following line: 

``` java Casino ```

## Project Description 

### Card Class 

This class represents a single card in a deck. Every card has the one value for each of the three following properties: 

* Suit 
    * Hearts 
    * Diamonds 
    * Clubs 
    * Spades 
* Color 
    * Red (for those that have a suit of Hearts or Diamonds)
    * Black (for those that have a suit of Spades or Clubs)
* Value 
    * A number from 1-12 (inclusive)
    * Special Values: 
        * 1 is an Ace 
        * 11 is a Jack 
        * 12 is a Queen 
        * 13 is a King 

The following functions were required to be in the Card class to help with the overall program/game: 

* public Card(int value, String suit)
    * Initializes the Card and its variables 
    * Ex: Card(12, "Hearts") will create a red 12 of Hearts 
* public in getValue() 
    * Returns the value of the card 
* public String getColor() 
    * Returns the color of the card 
* public String getSuit() 
    * Returns the suit of a card 
* public String toString() 
    * Returns the string representation of a card 
    * Ex: King of Hearts, 7 of Clubs, or Ace of Spades 

### Deck Class 

This class represents an entire deck of cards. In an entire deck of cards, there are 52 cards, each of the 13 values for the four different suits. The following functions were required to be in the Deck class to help with the overall program/game:  

* public Deck() 
    * This initializes the deck, creating an array of the 52 possible cards mentioned above. After the cards are generated, they are then shuffled within the array. This also initializes a discard pile array, which currently has nothing in it, but is used to hold the cards from the deck that are used and discarded later on. 
* public void shuffle() 
    * This method shuffles all of the cards/items in the deck. 
* public Card drawNextCard() 
    * This method gives you the next card in the deck. Initially, the card given will be at index 0, then index 1, and so on until index 51. After index 51, drawNextCard takes all cards in the discard pile, puts them back into the deck array, empties the discard pile, shuffles the newly refilled deck array, and returns the first one. 
* public void discard(Card c) 
    * This method adds the card being passed into the discard pile. It adds the cards starting at index 0, then index 1, and so on until it reaches index 51. 

### Casino Class - A Simple War Game 

The Casino class is where the simple war game for the assignment is implemented. The game is simpler than your usual game of war, but the objective of the user/player is to have a card with a higher value than the dealer. The user makes a bet and then both the user and the computer draws a card from the deck. If the user's card has a larger value than the computer's card, the user wins and the value of their bet is added to their current total. However, if the computer's card has a larger value than the user's, then the computer wins and the user loses their bet. If the drawn cards result in a tie, then neither the user or the computer wins, so the user keeps their bet (they don't win or lose anything and their current total stays the same). 

After each round, the cards that the user and computer have drawn for that round are discarded. Furthermore, the user will be asked if they want to keep playing at the end of each round. The user can respond with any word starting with "Y" (for yes) or "N" (for no). The case of the user's input doesn't matter, so they can be either lower case or upper case, so long as it begins with one of the two accepted letters. The game continues until the user says that they no longer want to play. 

The above is what was necessary for the core of this assignment, but there were choices that were left up to the students regarding the design of the game. The following are some specifications for my implementation of the assignment: 

* When/if the user runs out of money, they will be asked whether they would like to keep playing. If they say yes, they will be required to deposit more money so that they can keep making bets. If they say no, the game/program will end. 
* The game implements a minimum and maximum betting range. The range will be between 1% and 10% of their starting value (inclusive). The user is able to change the value of their bet at the start of every round. The minimum and maximum bet values are only recalculated when more funds are added by the user. 
* The minimum amount the user is required to have at the beginning of the game so that they can play is $100. If they enter an amount that is less than $100 as their initial funds, an error message will appear and they will be asked to enter a valid amount. This feature is also applied when/if the user runs out of money and still wishes to play again. They will be prompted to add more funds to continue, and they must add a minimum of $100. 
* When a player runs out of funds and decides to add more to keep playing, their total money is recalculated, and their minimum and maximum bets are changed to be between 1% and 10% of their new balance of funds. 
* When the player decides to end the game, the following game stats are provided: 
    * Rounds played 
    * Rounds won 
    * Rounds lost 
    * Rounds tied 
    * User's total money 