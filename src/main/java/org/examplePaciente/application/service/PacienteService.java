package org.examplePaciente.application.service;

import org.examplePaciente.domain.Paciente;
import java.util.List;

public interface PacienteService {
    void registrarPaciente(Paciente paciente);
    Paciente buscarPaciente(String nombre);
    void actualizarPaciente(Paciente paciente);
    List<Paciente> listarPacientes();
}
