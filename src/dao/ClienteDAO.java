package dao;

import java.sql.Connection;
import utilidades.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Cliente;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO {

    private Connection conexion; 

    public ClienteDAO() {
        this.conexion = Conexion.getConexion();
    }

    public boolean guardarCliente(Cliente cliente) {

        String sql = """
            INSERT INTO clientes
            (nombre, apellido_paterno, apellido_materno, telefono)
            VALUES (?, ?, ?, ?)
            """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidoPaterno());
            ps.setString(3, cliente.getApellidoMaterno());
            ps.setString(4, cliente.getTelefono());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e.getMessage()); 
            return false;
        }
    }
    
    public ArrayList<Cliente> listarClientes() {

    ArrayList<Cliente> lista = new ArrayList<>();

    String sql = "SELECT * FROM clientes";

    try {

        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            Cliente cliente = new Cliente();

            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setApellidoPaterno(rs.getString("apellido_paterno"));
            cliente.setApellidoMaterno(rs.getString("apellido_materno"));
            cliente.setTelefono(rs.getString("telefono"));

            lista.add(cliente);

        }

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

    return lista;

}

    public boolean actualizarCliente(Cliente cliente) {

    String sql = """
        UPDATE clientes
        SET nombre = ?,
            apellido_paterno = ?,
            apellido_materno = ?,
            telefono = ?
        WHERE id_cliente = ?
        """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getApellidoPaterno());
        ps.setString(3, cliente.getApellidoMaterno());
        ps.setString(4, cliente.getTelefono());
        ps.setInt(5, cliente.getIdCliente());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println(e.getMessage());

        return false;

    }

}
}
