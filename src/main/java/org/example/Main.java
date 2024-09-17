package org.example.application;

import org.example.application.service.PacienteService;
import org.example.application.service.PacienteServiceImpl;
import org.example.domain.Paciente;
import org.example.infrastructure.repository.PacienteRepositoryImpl;

import java.util.Scanner;

public class Main {
    private static PacienteService pacienteService = new PacienteServiceImpl(new PacienteRepositoryImpl());
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("1. Registrar Paciente");
            System.out.println("2. Mostrar Pacientes");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1 -> registrarPaciente();
                case 2 -> mostrarPacientes();
                case 3 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 3);
    }
    private static void registrarPaciente() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        Paciente paciente = new Paciente(nombre, apellido, edad, genero, direccion, telefono);
        pacienteService.registrarPaciente(paciente);
        System.out.println("Paciente registrado correctamente.");
    }

    private static void mostrarPacientes() {
        for (Paciente paciente : pacienteService.listarPacientes()) {
            System.out.println(paciente);
        }
    }
}