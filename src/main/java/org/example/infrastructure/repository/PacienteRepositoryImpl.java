package org.example.infrastructure.repository;

import org.example.domain.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepositoryImpl implements PacienteRepository {
    private static final String FILE_PATH = "pacientes.txt";

    @Override
    public void guardarPaciente(Paciente paciente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(paciente.getNombre() + "," + paciente.getApellido() + "," + paciente.getEdad() + "," +
                    paciente.getGenero() + "," + paciente.getDireccion() + "," + paciente.getTelefono());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Paciente buscarPacientePorNombre(String nombre) {
        List<Paciente> pacientes = obtenerTodosLosPacientes();
        for (Paciente paciente : pacientes) {
            if (paciente.getNombre().equalsIgnoreCase(nombre)) {
                return paciente;
            }
        }
        return null;
    }

    @Override
    public void actualizarPaciente(Paciente pacienteActualizado) {
        List<Paciente> pacientes = obtenerTodosLosPacientes();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Paciente paciente : pacientes) {
                if (paciente.getNombre().equalsIgnoreCase(pacienteActualizado.getNombre())) {
                    writer.write(pacienteActualizado.getNombre() + "," + pacienteActualizado.getApellido() + "," +
                            pacienteActualizado.getEdad() + "," + pacienteActualizado.getGenero() + "," +
                            pacienteActualizado.getDireccion() + "," + pacienteActualizado.getTelefono());
                } else {
                    writer.write(paciente.getNombre() + "," + paciente.getApellido() + "," + paciente.getEdad() + "," +
                            paciente.getGenero() + "," + paciente.getDireccion() + "," + paciente.getTelefono());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Paciente> obtenerTodosLosPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Paciente paciente = new Paciente(datos[0], datos[1], Integer.parseInt(datos[2]), datos[3], datos[4], datos[5]);
                pacientes.add(paciente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }
}
