package palindromo.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class PalindromoController {

    @GetMapping("/palindromo/{word}")
    public String getMethodName(@PathVariable String word) {
        
        return isPalindromo(word) ? "Es un palindromo" : "No es un palindromo";
    }

    private boolean isPalindromo(String word) {
        String word_with_spaces;
        String reverse_word = "";
        word_with_spaces = word.toLowerCase().replaceAll("\\s+", "");

        for (int i = word_with_spaces.length() - 1; i >= 0; i--) {

            reverse_word += word_with_spaces.charAt(i);
        }

        return word_with_spaces.equals(reverse_word);
    }

}
