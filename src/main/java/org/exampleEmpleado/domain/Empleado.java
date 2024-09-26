package org.exampleEmpleado.domain;

import java.util.List;

public class Empleado {
    private String nombre;
    private String apellido;
    private int edad;
    private String cargo;
    private String departamento;
    private double salario;
    private List<Tarea> tareas; // Agregación: Empleado tiene una lista de Tareas

    public Empleado(String nombre, String apellido, int edad, String cargo, String departamento, double salario, List<Tarea> tareas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.cargo = cargo;
        this.departamento = departamento;
        this.salario = salario;
        this.tareas = tareas;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", cargo='" + cargo + '\'' +
                ", departamento='" + departamento + '\'' +
                ", salario=" + salario +
                '}';
    }
}
