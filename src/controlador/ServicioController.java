package controlador;

import dao.ServicioDAO;
import java.util.ArrayList;
import modelo.Servicio;

public class ServicioController {

    private ServicioDAO servicioDAO;

    public ServicioController() {
        servicioDAO = new ServicioDAO();
    }

    // Guardar servicio
    public boolean guardarServicio(Servicio servicio) {

        try {

            if (servicio.getNombre().trim().isEmpty()) {
                throw new Exception("El nombre del servicio es obligatorio.");
            }

            if (servicio.getPrecio() <= 0) {
                throw new Exception("El precio debe ser mayor que cero.");
            }

            if (servicio.getDuracion() <= 0) {
                throw new Exception("La duración debe ser mayor que cero.");
            }

            return servicioDAO.guardarServicio(servicio);

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;

        }

    }

    // Listar servicios
    public ArrayList<Servicio> listarServicios() {

        return servicioDAO.listarServicios();

    }

    // Actualizar servicio
    public boolean actualizarServicio(Servicio servicio) {

        try {

            if (servicio.getNombre().trim().isEmpty()) {
                throw new Exception("El nombre del servicio es obligatorio.");
            }

            if (servicio.getPrecio() <= 0) {
                throw new Exception("El precio debe ser mayor que cero.");
            }

            if (servicio.getDuracion() <= 0) {
                throw new Exception("La duración debe ser mayor que cero.");
            }

            return servicioDAO.actualizarServicio(servicio);

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;

        }

    }

    public Servicio buscarServicioPorNombre(String nombre) {

    return servicioDAO.buscarServicioPorNombre(nombre);

    }
}