package mk.finki.ukim.mealsmack.pipeandfilter;

public interface Filter<T> {
    T execute(T input);
}
