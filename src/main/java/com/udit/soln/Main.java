package com.udit.soln;

import com.udit.soln.service.CsvParserService;
import com.udit.soln.service.EmployeeAnalyticsService;
import com.udit.soln.service.ManagerAnalyticsService;

public class Main {

    // keep runtime memory low by not storing the csv file in memory
    public static void main(String[] args) {
        EmployeeAnalyticsService employeeAnalyticsService = new EmployeeAnalyticsService();
        ManagerAnalyticsService managerAnalyticsService = new ManagerAnalyticsService();

        // We read csv file one line at a time and build collections to hold the info we require
        CsvParserService csvParser = new CsvParserService();

        csvParser.parseCsvFile("employees.csv");

        managerAnalyticsService.findManagerWithAnomaly(
                csvParser.getEmployeeDetails(),
                csvParser.getSubordinatesCount(),
                csvParser.getSubordinatesSalariesSum()
        );

        // We read csv file one line at a time and build collections to hold the info we require
        // this keeps memory foot-print low
        employeeAnalyticsService.findEmployeesWithLongReporting(4);
    }
}