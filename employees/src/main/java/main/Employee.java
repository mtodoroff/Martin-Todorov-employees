package main;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Employee {

    private String empId;
    Project project;

    public Employee(String empId){
        this.empId = empId;
    }

    public void setProject(String projectId, String dateFrom, String dateTo) {
        this.project = new Project(projectId, dateFrom, dateTo);
    }
}
