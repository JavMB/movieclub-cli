package logic.filters;

import java.util.Set;

public class DirectorFilter implements Filter<String> {
    private final Set<String> bannedDirectors;

    public DirectorFilter() {
        bannedDirectors = Set.of("nacho vidal", "tarantino", "quentin tarantino");
    }

    @Override
    public boolean isValid(String director) {
        if (director == null || director.trim().isEmpty()) {
            return false;
        }

        String directorLowerCase = director.toLowerCase().trim();

        for (String bannedDirector : bannedDirectors) {
            if (directorLowerCase.contains(bannedDirector)) {
                return false;
            }
        }

        return true;
    }
}