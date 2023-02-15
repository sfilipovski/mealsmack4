package mk.finki.ukim.mealsmack.pipeandfilter;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mealsmack.Data.DataHolder;
import mk.finki.ukim.mealsmack.model.Restaurant;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class CleanData {
    @PostConstruct
    public void create() {
        Pipe<String> pipe = new Pipe<>();
        RawDataFilter filter = new RawDataFilter();

        pipe.addFilter(filter);

        Scanner dataScanner = null;
        StringBuilder sb = new StringBuilder();

        try {
            dataScanner = new Scanner(new File("C:\\Users\\Sergey\\Desktop\\DIANS\\mealsmack\\src\\main\\resources\\static\\Data.csv"));
            dataScanner.useDelimiter(",");

            while (dataScanner.hasNextLine()) {
                sb.append(pipe.runFilters(dataScanner.nextLine())).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String[] parts = sb.toString().trim().split("\n");


        for (String part : parts) {
            String[] data = part.split("\\,", -1);
            List<String> cuisines = new ArrayList<>();
            String[] cuisine = data[5].split(";");
            cuisines.addAll(Arrays.asList(cuisine));


            DataHolder.restaurants.add(new Restaurant(data[1], data[2], data[3], data[4], cuisines, data[6]));
        }
    }
}
