package com.fdmgroup.tdd.Hangman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Hangman_Project - Command line version of Hangman game.
 * 
 * @author Heather Tsui
 * @version 1.0
 */

public class HangmanProject {

	/**
	 * All Attributes:
	 * 
	 * @param WORDS          Word bank of all words that can be guessed.
	 * @param BLANK          Dashes used for unknown letters.
	 * @param word           Calls randomWord method which selects random word from
	 *                       WORDS ArrayList.
	 * @param unknownLetters Character array of the letters of the word selected and
	 *                       known by player.
	 * @param letterBank     ArrayList of guessed letters input by player during
	 *                       game.
	 * 
	 * @param numGuesses     Number of guesses player has.
	 */
	private static final String[] WORDS = { "CHICKEN", "DRUMSTICK", "WING", "SEASONING", "FRY", "GIBLETS", "THIGH",
			"TAIL", "ALFREDO", "GRILL", "EGG", "CLUCK", "NUGGETS", "ROAST", "GOUJON", "FILLET", "WINGS", "PATTY",
			"HOTDOG", "STEW" };
	private static final char BLANK = '-';
	public static String word = randomWord(WORDS);
	public static char[] unknownLetters = new char[word.length()];
	public static ArrayList<Character> letterBank = new ArrayList<>();

	public static int numGuesses = 8;

	/**
	 * Selects random word from words bank, WORDS.
	 * 
	 * @param WORDS  ArrayList of all words
	 * @param random creates new random object.
	 * @return Random word chosen from words arraylist
	 */
	public static String randomWord(String[] WORDS) {
		Random random = new Random();
		int randomNum = random.nextInt(WORDS.length); // generates random number within the length of the word
		return WORDS[randomNum];
	}

	/**
	 * Checks to see if all blanks '-' in the word have been filled in by converting
	 * current status of guessed word 'unknownLetters' into string to be evaluated.
	 * 
	 * 
	 * @param letters        String version of unknownLettersw
	 * @param guessedLetters
	 * @return Returns true if word no longer contains '-'.
	 */
	public static boolean areBlanks(char[] unknownLetters) {
		String letters = new String(unknownLetters);
		return !(letters.contains("-"));
	}

	/**
	 * Checks if letterGuessed is in word by iterating through character of
	 * unknownLetters. If true, replaces unknownLetter at the location of that
	 * letter in the word.
	 * 
	 * @param word           Current word being guessed.
	 * @param unKnownLetters Character array of the letters of the word selected and
	 *                       known by player.
	 * @param letterGuessed  Current letter guessed by player.
	 * @param upCaseLetter   Converts guessed letter to upper case for tests.
	 * @return letterFound returns true if letter found in word.
	 */

	public static boolean letterFound(String word, char[] unknownLetters, char letterGuessed) {

		char upCaseLetter = Character.toUpperCase(letterGuessed);

		boolean letterFound = false;

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == upCaseLetter) {
				unknownLetters[i] = upCaseLetter;
				letterFound = true;

			}
		}
		return letterFound;
	}
	
	/**
	 * Checks letterGuesses against a ArrayList containing all letters already
	 * guessed. Returns true if this is the case.
	 * 
	 * @param letterBank ArrayList of guessed letters input by player during game.
	 * @Return True is letterGuessed by player has already been tried.
	 */

	public static boolean alreadyGuessed(char letterGuessed) {

		boolean letterExists = false;

		for (char letter : letterBank) {
			while (letter == letterGuessed) {
				letterExists = true;
				break;
			}
		}
		return letterExists;
	}

	/**
	 * Method only runs when numGuesses less than 0 (no more guesses). If there are
	 * no more blanks in unknown letters, win statement will run. Else, the lose
	 * statement will run.
	 * 
	 */
	public static void winLose() {

		if (areBlanks(unknownLetters)) {
			System.out.println("You guessed the word: " + new String(unknownLetters));
			System.out.println("You win.");
		} else {
			System.out.println("You're completely hung.");
			System.out.println("The word was: " + word);
			System.out.println("You lose.");
		}
	}

	/**
	 * Contains main method for game implementation. unknownLetters containing the
	 * word is first converted into blanks that player will see. Game will run while
	 * there are still numGuesses remaining and there are still blanks remaining in
	 * unknownLetters which contains word that player is guessing. * If guess is
	 * incorrect, the number of guesses will be deducted. When there are nore more
	 * guesses remaining, winLose method will run.
	 * 
	 * @param unknownLetters[i] Converts character array of unknown letters to blank
	 *                          '-'.
	 * @param scanner           Gets user input
	 * @param letter            Returns next letter from scanner and concerts to
	 *                          character.
	 * @param upCaseLetter      Converts user input letter to uppercase, which is
	 *                          used from remaining tests.
	 * 
	 */
	
	public static void beginHangman() {

		for (int i = 0; i < unknownLetters.length; i++) {
			unknownLetters[i] = BLANK;
		}

		Scanner scanner = new Scanner(System.in);


		while (numGuesses > 0 && !areBlanks(unknownLetters)) {

			System.out.println("The word now looks like this: " + new String(unknownLetters));
			System.out.println("You have " + numGuesses + " guesses left.");


			System.out.print("Your guess: ");
			char letter = scanner.next().charAt(0);
			char upCaseLetter = Character.toUpperCase(letter);


			if (alreadyGuessed(letter)) {
				System.out.println("You've already tried letter " + upCaseLetter + ". Guess another letter.");

			} else if (letterFound(word, unknownLetters, letter)) {
				System.out.println("That guess is correct.");
				letterBank.add(letter);

			} else if (!letterFound(word, unknownLetters, letter)) {
				System.out.println("There are no " + upCaseLetter + "'s in the word");
				letterBank.add(letter);
				numGuesses--;
			}
		}
		winLose();

		scanner.close();
	}




	/////////////////////////////////// Runner/////////////////////////////////////////////
	public static void main(String args[]) {
		System.out.println("Welcome to Hangman!");
		beginHangman();
	}

}

