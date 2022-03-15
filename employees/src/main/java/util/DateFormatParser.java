package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DateFormatParser {
    public static List<String> datePattern;
    private static final DateFormatParser instance = new DateFormatParser();
    private File dateFormats = new File("dateformats.txt");

    public static DateFormatParser getInstance(){
        return instance;
    }

    private DateFormatParser(){
        datePattern = parse(dateFormats);
    }

    public static List<String> parse(File file){
        ArrayList<String> formats = new ArrayList<>();
        try(Scanner sc = new Scanner(file)){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                formats.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return formats;
    }

    public static List<String> getDatePattern() {
        return datePattern;
    }
}
