package org.example.application.service;

import org.example.domain.Paciente;
import org.example.infrastructure.repository.PacienteRepository;

import java.util.List;

public class PacienteServiceImpl implements PacienteService {
    private PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void registrarPaciente(Paciente paciente) {
        pacienteRepository.guardarPaciente(paciente);
    }

    @Override
    public Paciente buscarPaciente(String nombre) {
        return pacienteRepository.buscarPacientePorNombre(nombre);
    }

    @Override
    public void actualizarPaciente(Paciente paciente) {
        pacienteRepository.actualizarPaciente(paciente);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepository.obtenerTodosLosPacientes();
    }
}
