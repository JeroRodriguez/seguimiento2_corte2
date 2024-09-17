package org.example.application.service;

import org.example.domain.Paciente;
import java.util.List;

public interface PacienteService {
    void registrarPaciente(Paciente paciente);
    Paciente buscarPaciente(String nombre);
    void actualizarPaciente(Paciente paciente);
    List<Paciente> listarPacientes();
}
