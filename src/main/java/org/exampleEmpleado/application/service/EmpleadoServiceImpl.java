package org.exampleEmpleado.application.service;

import org.exampleEmpleado.domain.Empleado;
import org.exampleEmpleado.domain.Tarea;
import org.exampleEmpleado.infrastructure.repository.EmpleadoRepository;

import java.util.List;

public class EmpleadoServiceImpl implements EmpleadoService {
    private EmpleadoRepository repository;

    public EmpleadoServiceImpl(EmpleadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrar(Empleado empleado) {
        repository.guardar(empleado);
    }

    @Override
    public Empleado buscarEmpleado(String nombre) {
        return repository.buscarPorNombre(nombre);
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        repository.actualizar(empleado);
    }

    @Override
    public void asignarTarea(Empleado empleado, Tarea tarea) {
        empleado.getTareas().add(tarea);
        repository.actualizar(empleado);
    }

    @Override
    public List<Empleado> listarEmpleados() {
        return repository.listarTodos();
    }

    @Override
    public List<Tarea> listarTareas(Empleado empleado) {
        return empleado.getTareas();
    }
}
