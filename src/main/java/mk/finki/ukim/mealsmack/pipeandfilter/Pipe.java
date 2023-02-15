package mk.finki.ukim.mealsmack.pipeandfilter;

import java.util.ArrayList;
import java.util.List;

public class Pipe<T> {
    List<Filter<T>> filterList = new ArrayList<>();

    public void addFilter(Filter<T> f){
        filterList.add(f);
    }

    public T runFilters(T in){
        for(Filter<T> filter: filterList){
            in = filter.execute(in);
        }
        return in;
    }
}
