package com.udit.soln.service;

import com.udit.soln.models.EmployeeDetails;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManagerAnalyticsService {

    private final Set<EmployeeDetails> managersWithHighSalary    =  new HashSet<>();
    private final Set<EmployeeDetails> managersWithLowSalary     =  new HashSet<>();
    private final Set<EmployeeDetails> managersWithCorrectSalary =  new HashSet<>();

    public void findManagerWithAnomaly(Map<Integer, EmployeeDetails> managerSalaries,
                                       Map<Integer, Integer> subordinatesCount,
                                       Map<Integer, Double> subordinatesSalariesSum) {
        // Calculate and report the results
        for (Map.Entry<Integer, EmployeeDetails> entry : managerSalaries.entrySet()) {
            Integer managerId = entry.getKey();
            EmployeeDetails employeeDetails = entry.getValue();
            Double managerSalary = employeeDetails.getSalary();

            if (subordinatesCount.containsKey(managerId)) {
                Double totalSubordinateSalary = subordinatesSalariesSum.get(managerId);
                Integer count = subordinatesCount.get(managerId);
                double averageSalary = totalSubordinateSalary / count;

                Double minSalary = 1.2 * averageSalary;
                Double maxSalary = 1.5 * averageSalary;

                if (managerSalary < minSalary) {
                    employeeDetails.setSalaryDiff(minSalary - managerSalary);
                    managersWithLowSalary.add(employeeDetails);
                    System.out.println("Manager with ID " + managerId +
                            " earns less than they should by " + (minSalary - managerSalary));
                } else if (managerSalary > maxSalary) {
                    employeeDetails.setSalaryDiff(managerSalary - maxSalary);
                    managersWithHighSalary.add(employeeDetails);
                    System.out.println("Manager with ID " + managerId +
                            " earns more than they should by " + (managerSalary - maxSalary));
                } else {
                    employeeDetails.setSalaryDiff(0.0);
                    managersWithCorrectSalary.add(employeeDetails);
                    System.out.println("Manager with ID " + managerId + " earns within the correct range.");
                }
            }
        }
    }

    public Set<EmployeeDetails> getManagersWithHighSalary() {
        return managersWithHighSalary;
    }

    public Set<EmployeeDetails> getManagersWithLowSalary() {
        return managersWithLowSalary;
    }

    public Set<EmployeeDetails> getManagersWithCorrectSalary() {
        return managersWithCorrectSalary;
    }
}
