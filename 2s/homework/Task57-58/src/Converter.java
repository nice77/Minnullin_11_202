import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {
    public static List<User> userObjectCreator() {
        String csvFile = "BataDase.csv";
        List<User> userList = new ArrayList<>();
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile)).build();
            String[] line = reader.readNext();
            while ((line = reader.readNext()) != null) {
                userList.add(new User(line));
            }
            reader.close();
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public static List<String> fromCSVtoJSON() {
        List<User> userList = userObjectCreator();
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        List<String> out = new ArrayList<>();
        for (User user : userList) {
            out.add(gson.toJson(user));
        }
        return out;
    }

    public static List<User> fromJSONtoOBJ(List<String> userList) {
        List<User> out = new ArrayList<>();
        for (String json : userList) {
            out.add(fromJSONtoOBJExact(json));
        }
        return out;
    }

    public static User fromJSONtoOBJExact(String userJson) {
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        return gson.fromJson(userJson, User.class);
    }

    public static void fromJSONtoCSV(List<String> json) {
        List<User> userList = fromJSONtoOBJ(json);
        try {
            FileWriter writer = new FileWriter("out.csv");
            ColumnPositionMappingStrategy<User> ms = new ColumnPositionMappingStrategy<>();
            ms.setType(User.class);
            System.out.println("Type: " + ms.getType());
            String[] columns = new String[] {"naym", "city", "mainInterest", "bio", "favColor", "pet", "futureProf", "year", "id"};
            ms.setColumnMapping(columns);
            System.out.println("Columns: " + Arrays.toString(ms.getColumnMapping()));

            StatefulBeanToCsvBuilder<User> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<User> beanWriter = builder.withMappingStrategy(ms).build();
            beanWriter.write(userList);
            writer.close();

        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }
}