package com.udit.soln.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeAnalyticsService {

    public void findEmployeesWithLongReporting(int N) {
        String csvFile = CsvParserService.class
                .getClassLoader().getResource("employees.csv").getPath(); // Path to your CSV file
        String line;
        String cvsSplitBy = ",";

        Map<Integer, Integer> employeeToManagerMap = new HashMap<>();
        Map<Integer, String> employeeNames = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                int id = Integer.parseInt(data[0]);
                String firstName = data[1];
                String lastName = data[2];
                int managerId = data.length > 4 ? Integer.parseInt(data[4]) : 0;
                employeeToManagerMap.put(id, managerId);
                employeeNames.put(id, firstName + " " + lastName);
            }

            findLongReportingHierachy(N, employeeToManagerMap, employeeNames);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findLongReportingHierachy(int N, Map<Integer, Integer> employeeToManagerMap, Map<Integer, String> employeeNames) {
        // Find employees with more than 4 managers between them and the CEO
        for (Map.Entry<Integer, Integer> entry : employeeToManagerMap.entrySet()) {
            int employeeId = entry.getKey();
            int managerId = entry.getValue();

            int managerCount = 0;
            while (managerId != 0) {
                managerCount++;
                managerId = employeeToManagerMap.get(managerId);

                // Break early if the count exceeds 4
                if (managerCount > N) {
                    System.out.println("Employee " + employeeNames.get(employeeId) + " has more than "+ N +" managers between them and the CEO.");
                    break;
                }
            }
        }
    }
}
