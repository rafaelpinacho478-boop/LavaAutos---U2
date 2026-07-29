package dao;

import java.util.ArrayList;
import modelo.Vehiculo;

public class VehiculoDAO {
    
    private static final ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    private static int contadorId = 1;

    public ArrayList<Vehiculo> listarVehiculos() {
        ClienteDAO clienteDAO = new ClienteDAO();
        for (Vehiculo v : listaVehiculos) {
            for (modelo.Cliente c : clienteDAO.listarClientes()) {
                if (c.getIdCliente() == v.getIdCliente()) {
                    // CORREGIDO: Usamos solo getNombre() para evitar el error si getApellidos() no existe
                    v.setNombreCliente(c.getNombre());
                    break;
                }
            }
        }
        return new ArrayList<>(listaVehiculos);
    }

    public boolean guardarVehiculo(Vehiculo vehiculo) {
        try {
            vehiculo.setIdVehiculo(contadorId++);
            listaVehiculos.add(vehiculo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizarVehiculo(Vehiculo vehiculo) {
        for (int i = 0; i < listaVehiculos.size(); i++) {
            if (listaVehiculos.get(i).getIdVehiculo() == vehiculo.getIdVehiculo()) {
                listaVehiculos.set(i, vehiculo);
                return true;
            }
        }
        return false;
    }
}
