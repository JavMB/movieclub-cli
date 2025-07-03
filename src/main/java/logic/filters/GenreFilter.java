package logic.filters;

import java.util.Set;

public class GenreFilter implements Filter<String> {
    private final Set<String> bannedGenres;

    public GenreFilter() {
        bannedGenres = Set.of("porno", "erótico", "adulto");
    }

    @Override
    public boolean isValid(String genero) {
        return !bannedGenres.contains(genero.toLowerCase());
    }
    
}
