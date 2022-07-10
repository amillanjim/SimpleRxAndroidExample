package com.etheur.rxproject.models;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Empleado {
    private int id;
    private String name;
    private String position;
    private Date timeWorking;
    private Double salary;
    private Double plusSalary;

    public Empleado(int id, String name, String position, Date timeWorking, Double salary, Double plusSalary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.timeWorking = timeWorking;
        this.salary = salary;
        this.plusSalary = plusSalary;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getTimeWorking() {
        return timeWorking;
    }

    public void setTimeWorking(Date timeWorking) {
        this.timeWorking = timeWorking;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getPlusSalary() {
        return plusSalary;
    }

    public void setPlusSalary(Double plusSalary) {
        this.plusSalary = plusSalary;
    }

    public static final List<Empleado> setNewEmpleados(){
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1, "Alejandro", "Developer", new Date(), 50000.00, 2000.00));
        empleados.add(new Empleado(1, "Argeo", "Developer", new Date(), 50000.00, 2000.00));
        empleados.add(new Empleado(1, "Edson", "Developer", new Date(), 50000.00, 2000.00));
        empleados.add(new Empleado(1, "Maciel", "Developer", new Date(), 50000.00, 2000.00));
        return empleados;
    }
}
