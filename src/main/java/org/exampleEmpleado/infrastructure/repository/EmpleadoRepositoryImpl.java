package org.exampleEmpleado.infrastructure.repository;

import org.exampleEmpleado.domain.Empleado;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepositoryImpl implements EmpleadoRepository {
    private static final String FILE_PATH = "empleados.txt";
    private List<Empleado> empleados = new ArrayList<>();

    @Override
    public void guardar(Empleado empleado) {
        empleados.add(empleado);
        guardarDatosEnArchivo();
    }

    @Override
    public Empleado buscarPorNombre(String nombre) {
        cargarDatosDesdeArchivo();
        return empleados.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Empleado> listarTodos() {
        cargarDatosDesdeArchivo();
        return empleados;
    }

    @Override
    public void actualizar(Empleado empleado) {
        Empleado encontrado = buscarPorNombre(empleado.getNombre());
        if (encontrado != null) {
            empleados.remove(encontrado);
            empleados.add(empleado);
            guardarDatosEnArchivo();
        }
    }

    private void guardarDatosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(empleados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarDatosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            empleados = (List<Empleado>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
