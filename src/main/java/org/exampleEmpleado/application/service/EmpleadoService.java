package org.exampleEmpleado.application.service;

import org.exampleEmpleado.domain.Empleado;
import org.exampleEmpleado.domain.Tarea;

import java.util.List;

public interface EmpleadoService {
    void registrar(Empleado empleado);
    Empleado buscarEmpleado(String nombre);
    void actualizarEmpleado(Empleado empleado);
    void asignarTarea(Empleado empleado, Tarea tarea);
    List<Empleado> listarEmpleados();
    List<Tarea> listarTareas(Empleado empleado);
}
