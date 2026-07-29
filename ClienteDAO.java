package dao;

import java.util.ArrayList;
import modelo.Cliente;

public class ClienteDAO {
    
    // Lista estática que simula la tabla en la base de datos
    private static final ArrayList<Cliente> listaClientes = new ArrayList<>();
    private static int contadorId = 1;

    public ArrayList<Cliente> listarClientes() {
        return new ArrayList<>(listaClientes);
    }

    public boolean guardarCliente(Cliente cliente) {
        try {
            cliente.setIdCliente(contadorId++);
            listaClientes.add(cliente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizarCliente(Cliente cliente) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getIdCliente() == cliente.getIdCliente()) {
                listaClientes.set(i, cliente);
                return true;
            }
        }
        return false;
    }
}
