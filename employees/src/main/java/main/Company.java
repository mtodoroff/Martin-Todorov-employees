package main;

import util.FileParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {

    File file;
    //list with all project and the employees working on them
    HashMap<String, ArrayList<Employee>> projects;
    //list with all teams
    public List<Team> teams;

    Company(File file){
        this.file = file;
        this.teams = new ArrayList<>();
        this.projects = new HashMap<>();
        readFile(file);
    }

    private void readFile(File file){
        try {
            if(!file.exists()) {
                System.out.println("File not found");
            } else {
                FileParser.parse(file, projects);
                teamUp(projects);
            }
        }
        catch (IOException e) {
            System.out.println("Something happened: " + e.getMessage());
        }
    }

    private void teamUp(HashMap<String, ArrayList<Employee>> employees){
        for (Map.Entry<String, ArrayList<Employee>> emps : employees.entrySet()) {
            if(emps.getValue().size() == 2 && !emps.getValue().get(0).getEmpId().equals(emps.getValue().get(1).getEmpId())) {
                Team newTeam = new Team(emps.getValue().get(0), emps.getValue().get(1), emps.getKey());
                teams.add(newTeam);
            }
        }
    }

    public void printTeamWhoWorkedLongestTogether(){
        if(teams.size() >= 1) {
            this.teams.sort(((o2, o1) -> Long.compare(o1.getTotalDaysCollaborated(), o2.getTotalDaysCollaborated())));
            String emp1 = this.teams.get(0).getEmployee1().getEmpId();
            String emp2 = this.teams.get(0).getEmployee2().getEmpId();
            System.out.println("==========Team that worked most together========== ");
            System.out.println(emp1 + " and " + emp2 + " on project - " + teams.get(0).getCommonProjectId() + " - " + teams.get(0).getTotalDaysCollaborated() + " days.");
        }else {
            System.out.println("No teams working together");
        }
    }

    public void printAllTeamsSorted(){
        if(teams.size() >= 1) {
            System.out.println("===================Teams Sorted===================");
            this.teams.sort(((o2, o1) -> Long.compare(o1.getTotalDaysCollaborated(), o2.getTotalDaysCollaborated())));
            for (Team team : teams) {
                System.out.println(team.getEmployee1().getEmpId() + " and " + team.getEmployee2().getEmpId() + " on project - "
                        + team.getCommonProjectId() + " - for " + team.getTotalDaysCollaborated() + " days.");
            }
        }
        else {
            System.out.println("No teams working together");
        }
    }
}
