package org.exampleEmpleado.infrastructure.repository;

import org.exampleEmpleado.domain.Empleado;

import java.util.List;

public interface EmpleadoRepository {
    void guardar(Empleado empleado);
    Empleado buscarPorNombre(String nombre);
    List<Empleado> listarTodos();
    void actualizar(Empleado empleado);
}
