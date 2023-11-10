import java.util.Scanner;

public class Main {
    // Global variables
    private static String secretPhrase = "HELLO HOW ARE YOU";
    private static String guessPhrase = "";
    private static int distanceFromKrustyKrab = 10;
    private static int numOfGuesses = 0;

    /* Helps test cases set the secretPhrase to use for the duration of the game.
     * Do NOT use this method in your code!
     */
    public static void setSecretPhrase(String phrase) {
        secretPhrase = phrase;
    }

    /* Helps test cases easily get the secretPhrase variable.
     * Do NOT use this method in your code!
     */
    public static String getSecretPhrase() {
        return secretPhrase;
    }

    public static void setGuessPhrase(String secretPhrase) {
        for(int i=0; i< secretPhrase.length(); i++){
           if(secretPhrase.charAt(i) != ' '){
               guessPhrase += "_";
           }
           else{
               guessPhrase +=" ";
           }
       }

    }

    /* Helps test cases easily get the guessPhrase variable.
     * Do NOT use this method in your code!
     */
    public static String getGuessPhrase() {

        return guessPhrase;
    }

    public static String displayGuess(String guessPhrase){

        String display = "";
        for( int i = 0; i < guessPhrase.length(); i++){
            display += guessPhrase.charAt(i) + " ";
        }
        return display;
    }

    public static String getGuess(){
        Scanner input = new Scanner(System.in);
        System.out.print("Guess a character:");
        String guess = input.nextLine();
        while(guess.length() !=1){
            System.out.print("You need to enter a single character.\nGuess a character:");
            guess = input.nextLine();
        }

        return guess.toUpperCase();
    }

    public static int countGuess(String guess){

        String guessP = "";
        int count = 0;
        for(int i = 0; i < secretPhrase.length(); i++){
            if(guess.equals(secretPhrase.substring(i,i+1))){
                count++;
                guessP+= guess;
            }
            else if(!guessPhrase.substring(i,i+1).equals("_")){
                guessP+= guessPhrase.substring(i,i+1);
            }
            else{
                guessP+= "_";
            }
        }
        guessPhrase = guessP;
        return count;
    }

    public static void gameOver(){
        if(!secretPhrase.equals(guessPhrase) || distanceFromKrustyKrab==0){
        System.out.println("The robot has stolen the secret formula. Plankton wins!\n You guessed this much of the secret phrase:\n"
                + displayGuess(guessPhrase));
        }
        else{
            System.out.println("Hooray! The robot has self-destructed and the Krusty Krab is safe.\nThe secret phrase was:\n"
            + secretPhrase + "\nYou got the phrase in " + numOfGuesses + " guesses.");
        }
    }

    public static void main(String[] args){
        
        String guess;
        int countGuess;

        setGuessPhrase(secretPhrase);
        System.out.println("Plankton’s robot is heading right toward the Krusty Krab! " +
                "\nStop it before it’s too late by guessing its secret phrase. \n");

        while(!secretPhrase.equals(guessPhrase) && distanceFromKrustyKrab >0){

            System.out.println(displayGuess(guessPhrase));
            System.out.println("The robot is " + distanceFromKrustyKrab + " km away!!");

            guess = getGuess();
            countGuess = countGuess(guess);
            if(countGuess == 0){
                System.out.println("\"" + guess + "\"" + " is not in the secret phrase");
                distanceFromKrustyKrab-=1;
                System.out.println("Oh no! The robot is moving clost to Krusty Krab!!\n");
            }
            else if(countGuess !=0){
                System.out.println(("\"" + guess + "\"" + " was found in the phrase " + countGuess + " times\n"));
            }
            numOfGuesses+=1;
        }
        gameOver();

    }
}