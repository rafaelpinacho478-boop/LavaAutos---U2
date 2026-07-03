package controlador;

import dao.ClienteDAO;
import modelo.Cliente;
import java.util.ArrayList;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        clienteDAO = new ClienteDAO();
    }

    public boolean guardarCliente(Cliente cliente) {
        try {
            if (cliente.getNombre().trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio.");
            }

            if (cliente.getApellidoPaterno().trim().isEmpty()) {
                throw new Exception("El apellido paterno es obligatorio.");
            }

            if (cliente.getTelefono().trim().isEmpty()) {
                throw new Exception("El teléfono es obligatorio.");
            }

            return clienteDAO.guardarCliente(cliente);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Cliente> listarClientes() {

    return clienteDAO.listarClientes();

}
    
    public boolean actualizarCliente(Cliente cliente) {

    try {

        if (cliente.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre es obligatorio.");
        }

        if (cliente.getApellidoPaterno().trim().isEmpty()) {
            throw new Exception("El apellido paterno es obligatorio.");
        }

        if (cliente.getTelefono().trim().isEmpty()) {
            throw new Exception("El teléfono es obligatorio.");
        }

        return clienteDAO.actualizarCliente(cliente);

    } catch (Exception e) {

        System.out.println(e.getMessage());

        return false;

    }

}
}
