package logic.filters;

public interface Filter<T> {

    boolean isValid(T t);
}
