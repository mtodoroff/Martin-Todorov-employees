package main;

import lombok.Getter;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

@Getter
public class Team {

    private Employee employee1;
    private Employee employee2;
    private String commonProjectId;
    private long totalDaysCollaborated;

    public Team(Employee employee1, Employee employee2, String commonProjectId) {
        this.employee1 = employee1;
        this.employee2 = employee2;
        this.commonProjectId = commonProjectId;
        this.totalDaysCollaborated = getCollaborationDays(employee1, employee2);
    }

    public long getCollaborationDays(Employee employee1, Employee employee2){
        LocalDate commonStartDate;
        LocalDate commonEndDate;
        LocalDate emp1StartDate = employee1.getProject().getDateFrom();
        LocalDate emp2StartDate = employee2.getProject().getDateFrom();
        LocalDate emp1EndDate = employee1.getProject().getDateTo();
        LocalDate emp2EndDate = employee2.getProject().getDateTo();
        commonStartDate = getCommonStartDate(emp1StartDate, emp2StartDate);
        commonEndDate = getCommonEndDate(emp1EndDate, emp2EndDate);
        if(commonStartDate == emp1StartDate && commonEndDate == emp2EndDate){
            return 0;
        }
        if(commonStartDate == emp2EndDate && commonEndDate == emp1EndDate){
            return 0;
        }
        if(emp1EndDate.compareTo(emp2StartDate) < 0){
            return 0;
        }
        if(emp2EndDate.compareTo(emp1StartDate) < 0){
            return 0;
        }
        return DAYS.between(commonStartDate, commonEndDate);
    }

    private LocalDate getCommonStartDate(LocalDate emp1StartDate, LocalDate emp2StartDate){
        if(emp1StartDate.compareTo(emp2StartDate) < 0){
            return emp2StartDate;
        }
        return emp1StartDate;
    }

    private LocalDate getCommonEndDate(LocalDate emp1EndDate, LocalDate emp2EndDate){
        if(emp1EndDate.compareTo(emp2EndDate) < 0){
            return emp2EndDate;
        }
        return emp1EndDate;
    }
}
