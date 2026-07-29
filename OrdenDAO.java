package dao;

import java.util.ArrayList;
import modelo.Orden;

public class OrdenDAO {
    
    private static final ArrayList<Orden> listaOrdenes = new ArrayList<>();
    private static int contadorId = 1;

    public ArrayList<Orden> listarOrdenesPendientes() {
        ArrayList<Orden> pendientes = new ArrayList<>();
        
        ClienteDAO clienteDAO = new ClienteDAO();
        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        ServicioDAO servicioDAO = new ServicioDAO();

        for (Orden o : listaOrdenes) {
            if (o.getHoraSalida() == null || o.getHoraSalida().isEmpty() || o.getHoraSalida().equals("--:--")) {
                
                // Mapeo seguro de Clientes (Solo usando getNombre)
                for (modelo.Cliente c : clienteDAO.listarClientes()) {
                    if (c.getIdCliente() == o.getIdCliente()) {
                        o.setNombreCliente(c.getNombre());
                        break;
                    }
                }
                
                // Mapeo de Vehículos
                for (modelo.Vehiculo v : vehiculoDAO.listarVehiculos()) {
                    if (v.getIdVehiculo() == o.getIdVehiculo()) {
                        o.setNombreVehiculo(v.getMarca() + " " + v.getModelo());
                        break;
                    }
                }
                
                // Mapeo seguro de Servicios (Quitamos getDuracion para evitar el error)
                for (modelo.Servicio s : servicioDAO.listarServicios()) {
                    if (s.getIdServicio() == o.getIdServicio()) {
                        o.setNombreServicio(s.getNombre());
                        break;
                    }
                }
                
                pendientes.add(o);
            }
        }
        return pendientes;
    }

    public boolean guardarOrden(Orden orden) {
        try {
            orden.setIdOrden(contadorId++);
            listaOrdenes.add(orden);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean guardarOrdenServicio(int idOrden, int idServicio) {
        return true;
    }

    public boolean actualizarSalida(Orden orden) {
        for (int i = 0; i < listaOrdenes.size(); i++) {
            if (listaOrdenes.get(i).getIdOrden() == orden.getIdOrden()) {
                listaOrdenes.get(i).setHoraSalida(orden.getHoraSalida());
                listaOrdenes.get(i).setCostoFinal(orden.getCostoFinal());
                return true;
            }
        }
        return false;
    }
}