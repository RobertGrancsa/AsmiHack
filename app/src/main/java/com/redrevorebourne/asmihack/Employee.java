package com.redrevorebourne.asmihack;

public class Employee {
    private String Name;
    private String Position;
    private String Salary;
    private String Team;

    public Employee() {
    }

    public Employee(String Name, String Position, String Salary, String Team) {
        this.Name = Name;
        this.Position = Position;
        this.Salary = Salary;
        this.Team = Team;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String Salary) {
        this.Salary = Salary;
    }

    public String getTeam() {
        return Team;
    }

    public void setTeam(String Team) {
        this.Team = Team;
    }
}
