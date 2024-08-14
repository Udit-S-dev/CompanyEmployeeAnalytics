package com.udit.soln.models;

import java.util.List;

public class EmployeeDetails {

    Integer id;
    String name;
    Double salary;
    Double salaryDiff;
    private List<EmployeeNode> subordinates;

    public EmployeeDetails(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getSalaryDiff() {
        return salaryDiff;
    }

    public void setSalaryDiff(Double salaryDiff) {
        this.salaryDiff = salaryDiff;
    }

    public List<EmployeeNode> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<EmployeeNode> subordinates) {
        this.subordinates = subordinates;
    }
}
