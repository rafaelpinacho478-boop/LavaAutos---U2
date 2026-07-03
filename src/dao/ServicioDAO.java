package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Servicio;
import utilidades.Conexion;

public class ServicioDAO {

    private Connection conexion;

public ServicioDAO() {
    conexion = Conexion.getConexion();
}

public boolean guardarServicio(Servicio servicio) {

    String sql = """
    INSERT INTO servicios
    (nombre, descripcion, precio, duracion)
    VALUES (?, ?, ?, ?)
    """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setString(2, servicio.getDescripcion());
        ps.setDouble(3, servicio.getPrecio());
        ps.setInt(4, servicio.getDuracion());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println(e.getMessage());
        return false;

    }

}

public ArrayList<Servicio> listarServicios() {

    ArrayList<Servicio> lista = new ArrayList<>();

    String sql = "SELECT * FROM servicios";

    try {

        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            Servicio servicio = new Servicio();

            servicio.setIdServicio(rs.getInt("id_servicio"));
            servicio.setNombre(rs.getString("nombre"));
            servicio.setDescripcion(rs.getString("descripcion"));
            servicio.setPrecio(rs.getDouble("precio"));
            servicio.setDuracion(rs.getInt("duracion"));

            lista.add(servicio);

        }

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

    return lista;

}

public boolean actualizarServicio(Servicio servicio) {

    String sql = """
       UPDATE servicios
       SET nombre = ?,
           descripcion = ?,
           precio = ?,
           duracion = ?
       WHERE id_servicio = ?
       """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setString(1, servicio.getNombre());
        ps.setString(2, servicio.getDescripcion());
        ps.setDouble(3, servicio.getPrecio());
        ps.setInt(4, servicio.getDuracion());
        ps.setInt(5, servicio.getIdServicio());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println(e.getMessage());
        return false;

    }

}

public Servicio buscarServicioPorNombre(String nombre) {

    String sql = "SELECT * FROM servicios WHERE nombre = ?";

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Servicio servicio = new Servicio();

            servicio.setIdServicio(rs.getInt("id_servicio"));
            servicio.setNombre(rs.getString("nombre"));
            servicio.setDescripcion(rs.getString("descripcion"));
            servicio.setPrecio(rs.getDouble("precio"));
            servicio.setDuracion(rs.getInt("duracion"));

            return servicio;
        }

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

    return null;
}
}