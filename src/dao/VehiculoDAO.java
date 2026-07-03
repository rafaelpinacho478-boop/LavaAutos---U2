package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Vehiculo;
import utilidades.Conexion;

public class VehiculoDAO {

    private Connection conexion;

    public VehiculoDAO() {
        conexion = Conexion.getConexion();
    }

    public boolean guardarVehiculo(Vehiculo vehiculo) {

    String sql = """
    INSERT INTO vehiculos
    (id_cliente,
     marca,
     modelo,
     color,
     tipo,
     observaciones)
    VALUES (?, ?, ?, ?, ?, ?)
    """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setInt(1, vehiculo.getIdCliente());
        ps.setString(2, vehiculo.getMarca());
        ps.setString(3, vehiculo.getModelo());
        ps.setString(4, vehiculo.getColor());
        ps.setString(5, vehiculo.getTipo());
        ps.setString(6, vehiculo.getObservaciones());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println(e.getMessage());

        return false;

    }

}
    
    public ArrayList<Vehiculo> listarVehiculos() {

    ArrayList<Vehiculo> lista = new ArrayList<>();

    String sql = """
SELECT
    v.id_vehiculo,
    v.id_cliente,
    CONCAT(
        c.nombre,
        ' ',
        c.apellido_paterno,
        IF(c.apellido_materno IS NULL OR c.apellido_materno = '',
           '',
           CONCAT(' ', c.apellido_materno))
    ) AS cliente,
    v.marca,
    v.modelo,
    v.color,
    v.tipo,
    v.observaciones
FROM vehiculos v
INNER JOIN clientes c
ON v.id_cliente = c.id_cliente
ORDER BY v.id_vehiculo
""";

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

    Vehiculo vehiculo = new Vehiculo();

    vehiculo.setIdVehiculo(rs.getInt("id_vehiculo"));
    vehiculo.setIdCliente(rs.getInt("id_cliente"));

    vehiculo.setNombreCliente(rs.getString("cliente"));

    vehiculo.setMarca(rs.getString("marca"));
    vehiculo.setModelo(rs.getString("modelo"));
    vehiculo.setColor(rs.getString("color"));
    vehiculo.setTipo(rs.getString("tipo"));
    vehiculo.setObservaciones(rs.getString("observaciones"));

    lista.add(vehiculo);

}

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

    return lista;

}
    
    public boolean actualizarVehiculo(Vehiculo vehiculo) {

    String sql = """
        UPDATE vehiculos
        SET id_cliente = ?,
            marca = ?,
            modelo = ?,
            color = ?,
            tipo = ?,
            observaciones = ?
        WHERE id_vehiculo = ?
        """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setInt(1, vehiculo.getIdCliente());
        ps.setString(2, vehiculo.getMarca());
        ps.setString(3, vehiculo.getModelo());
        ps.setString(4, vehiculo.getColor());
        ps.setString(5, vehiculo.getTipo());
        ps.setString(6, vehiculo.getObservaciones());
        ps.setInt(7, vehiculo.getIdVehiculo());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println(e.getMessage());

        return false;

    }

}
    
    public ArrayList<Vehiculo> listarVehiculosPorCliente(int idCliente) {

    ArrayList<Vehiculo> lista = new ArrayList<>();

    String sql = """
        SELECT *
        FROM vehiculos
        WHERE id_cliente = ?
        ORDER BY marca, modelo
        """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idCliente);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Vehiculo vehiculo = new Vehiculo();

            vehiculo.setIdVehiculo(rs.getInt("id_vehiculo"));
            vehiculo.setIdCliente(rs.getInt("id_cliente"));
            vehiculo.setMarca(rs.getString("marca"));
            vehiculo.setModelo(rs.getString("modelo"));
            vehiculo.setColor(rs.getString("color"));
            vehiculo.setTipo(rs.getString("tipo"));
            vehiculo.setObservaciones(rs.getString("observaciones"));

            lista.add(vehiculo);

        }

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

    return lista;

}
}