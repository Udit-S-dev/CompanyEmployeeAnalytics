package com.udit.soln.models;

public class EmployeeNode {
    int id;
    String name;
    EmployeeNode left;  // Used to store a subordinate
    EmployeeNode right; // Used to store another subordinate

    public EmployeeNode(int id, String name) {
        this.id = id;
        this.name = name;
        this.left = null;
        this.right = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeNode getLeft() {
        return left;
    }

    public void setLeft(EmployeeNode left) {
        this.left = left;
    }

    public EmployeeNode getRight() {
        return right;
    }

    public void setRight(EmployeeNode right) {
        this.right = right;
    }
}
