package org.examplePaciente.infrastructure.repository;

import org.examplePaciente.domain.Paciente;

import java.util.List;

public interface PacienteRepository {
    void guardarPaciente(Paciente paciente);
    Paciente buscarPacientePorNombre(String nombre);
    void actualizarPaciente(Paciente paciente);
    List<Paciente> obtenerTodosLosPacientes();
}
