import java.io.*;
import java.util.*;

public class Hangman {
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        boolean checking = true, valid = true;
        String response;
        String filename = "C:\\Users\\victo\\OneDrive\\Desktop\\Computer Languages\\Java\\Special Codes\\Hangman\\src\\words.txt";
        ArrayList<String> words;
        words = getWords(filename);

        do {
            playGame(words);  // call subroutine to play one game
            while(valid){
                System.out.print("\nWould you like to play again? (yes or no): ");
                response = keyboard.next().toLowerCase();
                if (response.equals("no")){
                    valid = false;
                    checking = false;
                }else if (response.equals("yes")){
                    System.out.println("Starting a new game\n");
                    keyboard.nextLine();
                    break;
                }else{
                    System.out.println("Please enter \"yes\" or \"no\"");
                }
            }
        } while (checking);
        System.out.println("Thanks for playing!");
    }

    static void playGame(ArrayList<String> words) {
        String word = randomWord(words);
        char guess = ' ';
        boolean validInput = true;
        int score;
        System.out.println("H A N G M A N");
        String s1 = " +---+\n";
        String s2 = "     |\n";
        String s3 = "     |\n";
        String s4 = "     |\n";
        String s5 = "     |\n";
        String s6 = "=======";

        score = 0;
        ArrayList<Character> missed = new ArrayList<>();
        ArrayList<Character> guessed = new ArrayList<>();
        int remaining = word.length();
        while (score < 6 && remaining > 0){
            System.out.println(s1 + s2 + s3 + s4 + s5 + s6);
            System.out.println("Missed Characters: " + missed);
            StringBuilder hidden = new StringBuilder();
            for(char c : word.toCharArray()){
                if(guessed.contains(c)){
                    hidden.append(c);
                }
                else{
                    hidden.append(" _ ");
                }
            }
            System.out.println(hidden);

            while (validInput){
                System.out.print("Guess a letter: ");
                String userInput = keyboard.nextLine();
                validInput = validInput(userInput);
                guess = userInput.charAt(0);
            }
            validInput = true;
            if(word.indexOf(guess)==-1){
                score++;
                missed.add(guess);
                switch (score){
                    case 1: s2 = " O   |\n";break;
                    case 2: s3 = " |   |\n";break;
                    case 3: s3 = "/|   |\n";break;
                    case 4: s3 = "/|\\  |\n";break;
                    case 5: s4 = "/    |\n";break;
                    case 6: s4 = "/ \\  |\n";break;
                    default:
                }
            }else if(!guessed.contains(guess)){
                guessed.add(guess);
            }
            remaining = 0;
            for(char c : word.toCharArray()){
                if(!guessed.contains(c)){
                    remaining ++;
                }
            }
        }
        if(score == 6){
            System.out.println(s1 + s2 + s3 + s4 + s5 + s6);
            System.out.println("Game Over, You Lost\nThe Word Was: "+ word);
        }else{
            System.out.println("You Won. You found the correct word: "+word);
        }
    }

    public static boolean validInput(String word){
        if (Character.isLetter(word.charAt(0))){
            return false;
        }else{
            System.out.println("Please enter a valid character (a-z)");
            return true;
        }
    }

    public static String randomWord(ArrayList<String> words){
        int index = (int)(Math.random() * words.size());
        return words.get(index);
    }

    public static ArrayList<String> getWords(String filename){
        ArrayList<String> result = new ArrayList<>();
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null){
                result.add(line);
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("Error! Unable to locate file");
        }
        return result;
    }

}
