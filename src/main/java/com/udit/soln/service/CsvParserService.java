package com.udit.soln.service;

import com.udit.soln.exceptions.InvalidInputCsvFileException;
import com.udit.soln.models.EmployeeDetails;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CsvParserService {

    private final ManagerAnalyticsService managerAnalyticsService = new ManagerAnalyticsService();

    Map<Integer, EmployeeDetails> employeeDetails = new HashMap<>();
    Map<Integer, Double> subordinatesSalariesSum = new HashMap<>();
    Map<Integer, Integer> subordinatesCount = new HashMap<>();

    public void parseCsvFile(String fileName, EmployeeBinaryTree employeeTree ) {
        String csvFile = CsvParserService.class.getClassLoader().getResource(fileName).getPath(); // Path to your CSV file
        String line;
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                if( data.length < 4) {
                    throw new InvalidInputCsvFileException("Found a row with incorrect number of columns");
                }
                Integer id = Integer.parseInt(data[0]);
                String firstName = data[1];
                String lastName = data[2];
                Double salary = Double.parseDouble(data[3]);
                // for ceo no manager
                Integer managerId = data.length > 4 ? Integer.parseInt(data[4]) : 0;

                employeeTree.insert(id, firstName+" "+lastName, managerId);
                // If the employee has a manager, update the manager's subordinates' data
                if (Objects.nonNull(managerId) ) {
                    subordinatesSalariesSum.merge(managerId, salary, Double::sum);
                    subordinatesCount.merge(managerId, 1, Integer::sum);
                }
                // Track the manager's salary
                employeeDetails.put(id, new EmployeeDetails(id, firstName+" "+lastName,salary));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ManagerAnalyticsService getManagerAnalyticsService() {
        return managerAnalyticsService;
    }

    public Map<Integer, EmployeeDetails> getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(Map<Integer, EmployeeDetails> employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public Map<Integer, Double> getSubordinatesSalariesSum() {
        return subordinatesSalariesSum;
    }

    public void setSubordinatesSalariesSum(Map<Integer, Double> subordinatesSalariesSum) {
        this.subordinatesSalariesSum = subordinatesSalariesSum;
    }

    public Map<Integer, Integer> getSubordinatesCount() {
        return subordinatesCount;
    }

    public void setSubordinatesCount(Map<Integer, Integer> subordinatesCount) {
        this.subordinatesCount = subordinatesCount;
    }
}
