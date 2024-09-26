package org.exampleEmpleado;

import org.exampleEmpleado.application.service.EmpleadoService;
import org.exampleEmpleado.application.service.EmpleadoServiceImpl;
import org.exampleEmpleado.domain.Empleado;
import org.exampleEmpleado.domain.Tarea;
import org.exampleEmpleado.infrastructure.repository.EmpleadoRepository;
import org.exampleEmpleado.infrastructure.repository.EmpleadoRepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmpleadoRepository repository = new EmpleadoRepositoryImpl();
        EmpleadoService service = new EmpleadoServiceImpl(repository);

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Actualizar datos del empleado");
            System.out.println("3. Asignar nueva tarea a empleado");
            System.out.println("4. Eliminar tarea de empleado");
            System.out.println("5. Listar empleados registrados");
            System.out.println("6. Mostrar tareas asignadas a un empleado");
            System.out.println("7. Actualizar estado de una tarea");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            switch (opcion) {
                case 1:
                    registrarEmpleado(service, scanner);
                    break;
                case 2:
                    actualizarEmpleado(service, scanner);
                    break;
                case 3:
                    asignarTarea(service, scanner);
                    break;
                case 4:
                    eliminarTarea(service, scanner);
                    break;
                case 5:
                    listarEmpleados(service);
                    break;
                case 6:
                    listarTareasEmpleado(service, scanner);
                    break;
                case 7:
                    actualizarEstadoTarea(service, scanner);
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 8);

        scanner.close();
    }

    private static void registrarEmpleado(EmpleadoService service, Scanner scanner) {
        System.out.println("=== Registrar Empleado ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();
        System.out.print("Salario: ");
        double salario = scanner.nextDouble();

        List<Tarea> tareas = new ArrayList<>();
        Empleado empleado = new Empleado(nombre, apellido, edad, cargo, departamento, salario, tareas);
        service.registrar(empleado);
        System.out.println("Empleado registrado correctamente.");
    }

    private static void actualizarEmpleado(EmpleadoService service, Scanner scanner) {
        System.out.println("=== Actualizar Empleado ===");
        System.out.print("Ingrese el nombre del empleado a actualizar: ");
        String nombre = scanner.nextLine();
        Empleado empleado = service.buscarEmpleado(nombre);
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        System.out.print("Nuevo apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Nueva edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Nuevo cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Nuevo departamento: ");
        String departamento = scanner.nextLine();
        System.out.print("Nuevo salario: ");
        double salario = scanner.nextDouble();

        empleado.setApellido(apellido);
        empleado.setEdad(edad);
        empleado.setCargo(cargo);
        empleado.setDepartamento(departamento);
        empleado.setSalario(salario);

        service.actualizarEmpleado(empleado);
        System.out.println("Empleado actualizado correctamente.");
    }

    private static void asignarTarea(EmpleadoService service, Scanner scanner) {
        System.out.println("=== Asignar Nueva Tarea ===");
        System.out.print("Ingrese el nombre del empleado: ");
        String nombreEmpleado = scanner.nextLine();
        Empleado empleado = service.buscarEmpleado(nombreEmpleado);
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        System.out.print("Título de la tarea: ");
        String titulo = scanner.nextLine();
        System.out.print("Descripción de la tarea: ");
        String descripcion = scanner.nextLine();
        System.out.print("Fecha de inicio (YYYY-MM-DD): ");
        LocalDate fechaInicio = LocalDate.parse(scanner.nextLine());
        System.out.print("Fecha de fin (YYYY-MM-DD): ");
        LocalDate fechaFin = LocalDate.parse(scanner.nextLine());
        System.out.print("Estado de la tarea: ");
        String estado = scanner.nextLine();

        Tarea tarea = new Tarea(titulo, descripcion, fechaInicio, fechaFin, estado, empleado);
        service.asignarTarea(empleado, tarea);
        System.out.println("Tarea asignada correctamente.");
    }

    private static void eliminarTarea(EmpleadoService service, Scanner scanner) {
        System.out.println("=== Eliminar Tarea de Empleado ===");
        System.out.print("Ingrese el nombre del empleado: ");
        String nombreEmpleado = scanner.nextLine();
        Empleado empleado = service.buscarEmpleado(nombreEmpleado);
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        List<Tarea> tareas = empleado.getTareas();
        if (tareas.isEmpty()) {
            System.out.println("El empleado no tiene tareas asignadas.");
            return;
        }

        System.out.println("Tareas asignadas:");
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ". " + tareas.get(i).getTitulo());
        }

        System.out.print("Seleccione el número de la tarea a eliminar: ");
        int indice = scanner.nextInt();
        if (indice > 0 && indice <= tareas.size()) {
            tareas.remove(indice - 1);
            service.actualizarEmpleado(empleado);
            System.out.println("Tarea eliminada correctamente.");
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private static void listarEmpleados(EmpleadoService service) {
        System.out.println("=== Lista de Empleados Registrados ===");
        List<Empleado> empleados = service.listarEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            empleados.forEach(System.out::println);
        }
    }

    private static void listarTareasEmpleado(EmpleadoService service, Scanner scanner) {
        System.out.println("=== Lista de Tareas de Empleado ===");
        System.out.print("Ingrese el nombre del empleado: ");
        String nombreEmpleado = scanner.nextLine();
        Empleado empleado = service.buscarEmpleado(nombreEmpleado);
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        List<Tarea> tareas = service.listarTareas(empleado);
        if (tareas.isEmpty()) {
            System.out.println("El empleado no tiene tareas asignadas.");
        } else {
            tareas.forEach(System.out::println);
        }
    }

    private static void actualizarEstadoTarea(EmpleadoService service, Scanner scanner) {
        System.out.println("=== Actualizar Estado de Tarea ===");
        System.out.print("Ingrese el nombre del empleado: ");
        String nombreEmpleado = scanner.nextLine();
        Empleado empleado = service.buscarEmpleado(nombreEmpleado);
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        List<Tarea> tareas = empleado.getTareas();
        if (tareas.isEmpty()) {
            System.out.println("El empleado no tiene tareas asignadas.");
            return;
        }

        System.out.println("Tareas asignadas:");
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ". " + tareas.get(i).getTitulo() + " - Estado: " + tareas.get(i).getEstado());
        }

        System.out.print("Seleccione el número de la tarea a actualizar: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (indice > 0 && indice <= tareas.size()) {
            System.out.print("Nuevo estado (Pendiente/En progreso/Completada): ");
            String nuevoEstado = scanner.nextLine();
            Tarea tarea = tareas.get(indice - 1);
            tarea.setEstado(nuevoEstado);
            service.actualizarEmpleado(empleado);
            System.out.println("Estado de tarea actualizado correctamente.");
        } else {
            System.out.println("Opción no válida.");
        }
    }
}
