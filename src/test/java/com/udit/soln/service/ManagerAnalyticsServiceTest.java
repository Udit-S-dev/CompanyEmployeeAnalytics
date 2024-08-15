package com.udit.soln.service;


import com.udit.soln.models.EmployeeDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagerAnalyticsServiceTest {

    private ManagerAnalyticsService service;
    private Map<Integer, EmployeeDetails> managerSalaries;
    private Map<Integer, Integer> subordinatesCount;
    private Map<Integer, Double> subordinatesSalariesSum;

    @BeforeEach
    public void setUp() {
        service = new ManagerAnalyticsService();
        managerSalaries = new HashMap<>();
        subordinatesCount = new HashMap<>();
        subordinatesSalariesSum = new HashMap<>();
    }

    @Test
    public void testManagerWithLowSalary() {
        // Arrange
        EmployeeDetails manager = new EmployeeDetails(1, "Manager A", 800.0);
        managerSalaries.put(1, manager);
        subordinatesCount.put(1, 2);
        subordinatesSalariesSum.put(1, 1500.0); // Average salary = 750.0, min salary = 1.2 * 750 = 900.0

        // Act
        service.findManagerWithAnomaly(managerSalaries, subordinatesCount, subordinatesSalariesSum);

        // Assert
        Set<EmployeeDetails> lowSalaryManagers = service.getManagersWithLowSalary();
        assertEquals(1, lowSalaryManagers.size());
        assertTrue(lowSalaryManagers.contains(manager));
        assertEquals(Double.valueOf(100.0), manager.getSalaryDiff());
    }

    @Test
    public void testManagerWithHighSalary() {
        // Arrange
        EmployeeDetails manager = new EmployeeDetails(2, "Manager B", 2000.0);
        managerSalaries.put(2, manager);
        subordinatesCount.put(2, 3);
        subordinatesSalariesSum.put(2, 3000.0); // Average salary = 1000.0, max salary = 1.5 * 1000 = 1500.0

        // Act
        service.findManagerWithAnomaly(managerSalaries, subordinatesCount, subordinatesSalariesSum);

        // Assert
        Set<EmployeeDetails> highSalaryManagers = service.getManagersWithHighSalary();
        assertEquals(1, highSalaryManagers.size());
        assertTrue(highSalaryManagers.contains(manager));
        assertEquals(Double.valueOf(500.0), manager.getSalaryDiff());
    }

    @Test
    public void testManagerWithCorrectSalary() {
        // Arrange
        EmployeeDetails manager = new EmployeeDetails(3, "Manager C", 1200.0);
        managerSalaries.put(3, manager);
        subordinatesCount.put(3, 2);
        subordinatesSalariesSum.put(3, 2000.0); // Average salary = 1000.0, min = 1200.0, max = 1500.0

        // Act
        service.findManagerWithAnomaly(managerSalaries, subordinatesCount, subordinatesSalariesSum);

        // Assert
        Set<EmployeeDetails> correctSalaryManagers = service.getManagersWithCorrectSalary();
        assertEquals(1, correctSalaryManagers.size());
        assertTrue(correctSalaryManagers.contains(manager));
        assertEquals(Double.valueOf(0.0), manager.getSalaryDiff());
    }

    @Test
    public void testManagerWithNoSubordinates() {
        // Arrange
        EmployeeDetails manager = new EmployeeDetails(4, "Manager D", 1000.0);
        managerSalaries.put(4, manager);

        // Act
        service.findManagerWithAnomaly(managerSalaries, subordinatesCount, subordinatesSalariesSum);

        // Assert
        assertTrue(service.getManagersWithHighSalary().isEmpty());
        assertTrue(service.getManagersWithLowSalary().isEmpty());
        assertTrue(service.getManagersWithCorrectSalary().isEmpty());
    }
}

