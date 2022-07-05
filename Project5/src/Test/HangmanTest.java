import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {

    @BeforeEach
    void setUp() {
        File text = new File("C:\\Users\\victo\\OneDrive\\Desktop\\Computer Languages\\Java\\Special Codes\\Hangman\\src\\words.txt");
        try{
            Scanner scan = new Scanner(text);
            System.out.println("File Found");
        }catch(FileNotFoundException e){
            System.out.println("No file found");
        }
    }

    @Test
    void validInput() {
        assertFalse(Hangman.validInput("a"));
        assertTrue(Hangman.validInput("1"));
        assertTrue(Hangman.validInput("?"));
    }
}