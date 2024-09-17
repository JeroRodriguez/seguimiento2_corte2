package org.example.infrastructure.repository;

import org.example.domain.Paciente;

import java.util.List;

public interface PacienteRepository {
    void guardarPaciente(Paciente paciente);
    Paciente buscarPacientePorNombre(String nombre);
    void actualizarPaciente(Paciente paciente);
    List<Paciente> obtenerTodosLosPacientes();
}
