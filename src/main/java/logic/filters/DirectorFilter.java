package logic.filters;

public class DirectorFilter implements Filter<String> {
    @Override
    public boolean isValid(String s) {
        return false;
    }
}
