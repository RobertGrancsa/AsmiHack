package com.redrevorebourne.asmihack;

public class Employee {
    private String name;
    private String position;
    private String salary;
    private String team;

    public Employee() {
    }

    public Employee(String name, String position, String salary, String team) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
