package com.udit.soln;

import com.udit.soln.service.CsvParserService;
import com.udit.soln.service.EmployeeBinaryTree;
import com.udit.soln.service.ManagerAnalyticsService;

public class Main {
    public static void main(String[] args) {

        EmployeeBinaryTree employeeTree = new EmployeeBinaryTree();
        ManagerAnalyticsService managerAnalyticsService = new ManagerAnalyticsService();

        CsvParserService csvParser = new CsvParserService();

        csvParser.parseCsvFile("employees.csv", employeeTree);

        managerAnalyticsService.findManagerWithAnomaly(
                csvParser.getEmployeeDetails(),
                csvParser.getSubordinatesCount(),
                csvParser.getSubordinatesSalariesSum()
        );

        // Find employee with more than 4 managers
        employeeTree.findEmployeesWithMoreThanNManagers(2);
    }
}