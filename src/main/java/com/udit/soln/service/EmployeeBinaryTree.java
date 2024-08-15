package com.udit.soln.service;

import com.udit.soln.models.EmployeeNode;

public class EmployeeBinaryTree {
    EmployeeNode root;

    public EmployeeBinaryTree() {
        this.root = null;
    }

    // Insert a node into the binary tree
    public void insert(int id, String name, int managerId) {
        if (root == null && managerId == 0) {
            root = new EmployeeNode(id, name); // CEO node
        } else {
            insertRecursively(root, id, name, managerId);
        }
    }

    private void insertRecursively(EmployeeNode current, int id, String name, int managerId) {
        if (current == null) {
            return;
        }

        if (current.getId() == managerId) {
            if (current.getLeft() == null) {
                current.setLeft(new EmployeeNode(id, name));
            } else if (current.getRight() == null) {
                current.setRight(new EmployeeNode(id, name));
            } else {
                // Handle additional subordinates; could be extended to a general n-ary tree
                // For simplicity, we place additional subordinates in the left subtree
                insertRecursively(current.getLeft(), id, name, managerId);
            }
        } else {
            insertRecursively(current.getLeft(), id, name, managerId);
            insertRecursively(current.getRight(), id, name, managerId);
        }
    }

    // Find and print employees with more than N managers between them and the CEO
    public void findEmployeesWithMoreThanNManagers(int N) {
        findEmployeesWithMoreThanNManagersRecursively(root, 0, N);
    }

    private void findEmployeesWithMoreThanNManagersRecursively(EmployeeNode node, int managerCount, int N) {
        if (node == null) {
            return;
        }

        if (managerCount > N) {
            System.out.println("Employee " + node.getName() + " has more than " + N + " managers between them and the CEO.");
        }

        findEmployeesWithMoreThanNManagersRecursively(node.getLeft(), managerCount + 1, N);
        findEmployeesWithMoreThanNManagersRecursively(node.getRight(), managerCount + 1, N);
    }
}

