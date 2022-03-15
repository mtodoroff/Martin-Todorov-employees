package main;

import lombok.Getter;
import util.DateFormatParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Getter
public class Project {

    private String projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    Project(String projectId, String dateFrom, String dateTo){
        this.projectId = projectId;
        this.dateFrom = convertStringToLocalDate(dateFrom.trim(), DateFormatParser.datePattern);
//        this.dateFrom = convertStringToLocalDate(dateFrom.trim());
        setDateTo(dateTo);
    }

    private LocalDate convertStringToLocalDate(String dateString, List<String> formatStrings) {
        for (String formatString : formatStrings) {
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatString);
                LocalDate localDate = LocalDate.parse(dateString, dateFormatter);
                return localDate;
            }
            catch (DateTimeParseException e) {}
        }
        return null;
    } //new way to convert all String date patterns to LocalDate

//    private LocalDate convertStringToLocalDate(String date) {
//        List<Date> parse = new PrettyTimeParser().parse(date);
//        return parse
//                .get(0)
//                .toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate();
//    }

    public void setDateTo(String dateTo) {
        if(dateTo.trim().equalsIgnoreCase("NULL")){
            this.dateTo = LocalDate.now();
        } else {
            this.dateTo = convertStringToLocalDate(dateTo.trim(), DateFormatParser.datePattern);
//            this.dateTo = convertStringToLocalDate(dateTo.trim());
        }
    }
}