package logic;

import java.util.HashSet;
import java.util.Set;

public class ProfanityFilter {
    private Set<String> badWords;

    public ProfanityFilter() {
        this.badWords = new HashSet<>();
        init();
    }

    private void init() {
        //fichero?
        badWords.add("mierda");
        badWords.add("puta");
        badWords.add("joder");
        badWords.add("gilipollas");
        badWords.add("cabron");
    }

    public boolean checkWord(String word) {
        return badWords.contains(word);
    }

    public boolean containsProfanity(String titulo) {
        String[] words = titulo.toLowerCase().split("\\s+");

        for (String word : words) {
            if (checkWord(word))
                return true;
        }

        return false;
    }
}
