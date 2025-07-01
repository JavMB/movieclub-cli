package logic.filters;

import java.util.HashSet;
import java.util.Set;

public class ProfanityFilter implements Filter<String> {
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

    public boolean isInsult(String word) {
        return badWords.contains(word);
    }


    @Override
    public boolean isValid(String titulo) {
        String[] words = titulo.toLowerCase().split("\\s+");

        for (String word : words) {
            if (isInsult(word))
                return false;
        }

        return true;
    }
}
