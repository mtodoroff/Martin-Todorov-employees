package util;

import main.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

public class FileParser {

    public static void parse(File filename, HashMap<String, ArrayList<Employee>> employees) throws IOException {
        Reader file = new FileReader(filename);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(Headers.class).withSkipHeaderRecord().parse(file);
        for (CSVRecord record : records) {
            String projectId = record.get(Headers.ProjectID);
            if(!employees.containsKey(projectId)) {
                employees.put(projectId, new ArrayList<>());
            }
            String empId = record.get(Headers.EmpID);
            employees.get(projectId).add(new Employee(empId));
            String dateFrom = record.get(Headers.DateFrom);
            String dateTo = record.get(Headers.DateTo);
            int arrayListSize = employees.get(projectId).size();
            employees.get(projectId).get(arrayListSize - 1).setProject(projectId, dateFrom, dateTo);
        }
    }
}
