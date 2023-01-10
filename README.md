# HangmanProject
Command line version of Hangman written in Java. A brief preview is given below:

![image](https://user-images.githubusercontent.com/108417157/211446069-4b9927a0-4a8f-4e49-9076-d44b7765919f.png)

# Features
*	The player is given 8 guesses, deducted only if the guessed letter does not exist in the hidden word. 
*	The hidden word should be displayed, with a dash marking any hidden letters.
*	A list of previously guessed letters should also be displayed.
If the player guesses a previously guessed letter, message displayed indicating as such and allow them to guess again. 
*	The hidden word will be selected at random from a pre-populated list of words

# Assumptions
*	The hidden word is in all uppercase
*	Guesses are not case sensitive. That is, if the player guessed ‘A’ it should still reveal all instances of ‘a’
*	The player will always give an appropriate input (a single letter)
*	Words will not contain any punctuation
*	Words will also be a single word (not a phrase)
