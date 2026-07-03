package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Orden;
import utilidades.Conexion;

public class OrdenDAO {

    private Connection conexion;

    public OrdenDAO() {

        conexion = Conexion.getConexion();

    }

    public int guardarOrden(Orden orden) {

    String sql = """
        INSERT INTO ordenes
        (
            fecha_ingreso,
            hora_ingreso,
            hora_salida,
            duracion,
            costo_final,
            observaciones,
            id_cliente,
            id_vehiculo
        )
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

    try {

        PreparedStatement ps = conexion.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, orden.getFechaIngreso());
        ps.setString(2, orden.getHoraIngreso());
        ps.setString(3, orden.getHoraSalida());
        ps.setString(4, orden.getDuracion());
        ps.setDouble(5, orden.getCostoFinal());
        ps.setString(6, orden.getObservaciones());
        ps.setInt(7, orden.getIdCliente());
        ps.setInt(8, orden.getIdVehiculo());

        int filas = ps.executeUpdate();

        if (filas > 0) {

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                return rs.getInt(1);

            }

        }

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

    return -1;

}
    
    public boolean guardarOrdenServicio(int idOrden,
                                    int idServicio,
                                    double costoFinal) {

    String sql = """
        INSERT INTO orden_servicio
        (
            id_orden,
            id_servicio,
            costo_final
        )
        VALUES (?, ?, ?)
        """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setInt(1, idOrden);
        ps.setInt(2, idServicio);
        ps.setDouble(3, costoFinal);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println(e.getMessage());

        return false;

    }

}
    
    public ArrayList<Orden> listarOrdenes() {

    ArrayList<Orden> lista = new ArrayList<>();

    String sql = """
    SELECT
        o.id_orden,
        o.id_cliente,
        o.id_vehiculo,
        os.id_servicio,
        o.fecha_ingreso,
        o.hora_ingreso,
        o.duracion,
        o.observaciones,
        o.costo_final,

        CONCAT(
            c.nombre,' ',
            c.apellido_paterno,' ',
            IFNULL(c.apellido_materno,'')
        ) AS cliente,

        CONCAT(
            v.marca,' ',
            v.modelo
        ) AS vehiculo,

        s.nombre AS servicio

    FROM ordenes o

    INNER JOIN clientes c
        ON o.id_cliente = c.id_cliente

    INNER JOIN vehiculos v
        ON o.id_vehiculo = v.id_vehiculo

    INNER JOIN orden_servicio os
        ON o.id_orden = os.id_orden

    INNER JOIN servicios s
        ON os.id_servicio = s.id_servicio

    ORDER BY o.id_orden DESC
    """;

    try {

        Statement st = conexion.createStatement();

        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            Orden orden = new Orden();

            orden.setIdOrden(rs.getInt("id_orden"));
            
            orden.setIdCliente(rs.getInt("id_cliente"));

            orden.setIdVehiculo(rs.getInt("id_vehiculo"));

            orden.setIdServicio(rs.getInt("id_servicio"));

            orden.setFechaIngreso(rs.getString("fecha_ingreso"));

            orden.setHoraIngreso(rs.getString("hora_ingreso"));
            
            orden.setDuracion(rs.getString("duracion"));
            
            orden.setObservaciones(rs.getString("observaciones"));

            orden.setCostoFinal(rs.getDouble("costo_final"));

            orden.setNombreCliente(rs.getString("cliente"));

            orden.setNombreVehiculo(rs.getString("vehiculo"));

            orden.setNombreServicio(rs.getString("servicio"));

            lista.add(orden);

        }

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

    return lista;

}
    
    public boolean actualizarOrden(Orden orden) {

    String sql = """
        UPDATE ordenes
        SET
            id_cliente = ?,
            id_vehiculo = ?,
            observaciones = ?,
            costo_final = ?
        WHERE id_orden = ?
        """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setInt(1, orden.getIdCliente());
        ps.setInt(2, orden.getIdVehiculo());
        ps.setString(3, orden.getObservaciones());
        ps.setDouble(4, orden.getCostoFinal());
        ps.setInt(5, orden.getIdOrden());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println(e.getMessage());

        return false;

    }

}
    
    public boolean actualizarOrdenServicio(int idOrden,
                                       int idServicio,
                                       double costo) {

    String sql = """
        UPDATE orden_servicio
        SET
            id_servicio = ?,
            costo_final = ?
        WHERE id_orden = ?
        """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setInt(1, idServicio);
        ps.setDouble(2, costo);
        ps.setInt(3, idOrden);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println(e.getMessage());

        return false;

    }

}
    
    public ArrayList<Orden> listarOrdenesPendientes() {

    ArrayList<Orden> lista = new ArrayList<>();

    String sql = """
    SELECT

        o.id_orden,

        o.id_cliente,

        o.id_vehiculo,

        os.id_servicio,

        o.fecha_ingreso,

        o.hora_ingreso,

        o.hora_salida,
                 
        o.duracion,

        o.observaciones,

        o.costo_final,

        CONCAT(
            c.nombre,' ',
            c.apellido_paterno,' ',
            IFNULL(c.apellido_materno,'')
        ) AS cliente,

        CONCAT(
            v.marca,' ',
            v.modelo
        ) AS vehiculo,

        s.nombre AS servicio

    FROM ordenes o

    INNER JOIN clientes c
        ON o.id_cliente = c.id_cliente

    INNER JOIN vehiculos v
        ON o.id_vehiculo = v.id_vehiculo

    INNER JOIN orden_servicio os
        ON o.id_orden = os.id_orden

    INNER JOIN servicios s
        ON os.id_servicio = s.id_servicio

    WHERE o.hora_salida IS NULL

    ORDER BY o.fecha_ingreso,
             o.hora_ingreso;

    """;

    try {

        Statement st = conexion.createStatement();

        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            Orden orden = new Orden();

            orden.setIdOrden(rs.getInt("id_orden"));
            orden.setIdCliente(rs.getInt("id_cliente"));
            orden.setIdVehiculo(rs.getInt("id_vehiculo"));
            orden.setIdServicio(rs.getInt("id_servicio"));

            orden.setFechaIngreso(rs.getString("fecha_ingreso"));
            orden.setHoraIngreso(rs.getString("hora_ingreso"));
            orden.setHoraSalida(rs.getString("hora_salida"));
            orden.setDuracion(rs.getString("duracion"));

            orden.setObservaciones(rs.getString("observaciones"));
            orden.setCostoFinal(rs.getDouble("costo_final"));

            orden.setNombreCliente(rs.getString("cliente"));
            orden.setNombreVehiculo(rs.getString("vehiculo"));
            orden.setNombreServicio(rs.getString("servicio"));

            lista.add(orden);

        }

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

    return lista;

}
    
    public boolean registrarSalida(int idOrden,
                               String horaSalida,
                               String duracion) {

    String sql = """
        UPDATE ordenes
        SET
            hora_salida = ?,
            duracion = ?
        WHERE id_orden = ?
        """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setString(1, horaSalida);
        ps.setString(2, duracion);
        ps.setInt(3, idOrden);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {

        System.out.println(e.getMessage());

        return false;

    }

}
    
    public Orden obtenerOrdenTicket(int idOrden) {

    Orden orden = null;

    String sql = """
        SELECT

            o.id_orden,
            o.fecha_ingreso,
            o.hora_ingreso,
            o.hora_salida,
            o.duracion,
            o.costo_final,
            o.observaciones,

            CONCAT(
                c.nombre,
                ' ',
                c.apellido_paterno,
                IF(c.apellido_materno IS NULL OR c.apellido_materno = '',
                    '',
                    CONCAT(' ', c.apellido_materno))
            ) AS cliente,

            CONCAT(
                v.marca,
                ' ',
                v.modelo
            ) AS vehiculo,

            s.nombre AS servicio

        FROM ordenes o

        INNER JOIN clientes c
            ON o.id_cliente = c.id_cliente

        INNER JOIN vehiculos v
            ON o.id_vehiculo = v.id_vehiculo

        INNER JOIN orden_servicio os
            ON o.id_orden = os.id_orden

        INNER JOIN servicios s
            ON os.id_servicio = s.id_servicio

        WHERE o.id_orden = ?;
        """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setInt(1, idOrden);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            orden = new Orden();

            orden.setIdOrden(rs.getInt("id_orden"));

            orden.setFechaIngreso(rs.getString("fecha_ingreso"));
            orden.setHoraIngreso(rs.getString("hora_ingreso"));
            orden.setHoraSalida(rs.getString("hora_salida"));
            orden.setDuracion(rs.getString("duracion"));

            orden.setCostoFinal(rs.getDouble("costo_final"));
            orden.setObservaciones(rs.getString("observaciones"));

            orden.setNombreCliente(rs.getString("cliente"));
            orden.setNombreVehiculo(rs.getString("vehiculo"));
            orden.setNombreServicio(rs.getString("servicio"));

        }

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

    return orden;

}
    
    public Orden obtenerOrdenPorId(int idOrden) {

    Orden orden = null;

    String sql = """
        SELECT
            id_orden,
            fecha_ingreso,
            hora_ingreso,
            hora_salida,
            duracion,
            costo_final,
            observaciones,
            id_cliente,
            id_vehiculo
        FROM ordenes
        WHERE id_orden = ?
        """;

    try {

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setInt(1, idOrden);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            orden = new Orden();

            orden.setIdOrden(rs.getInt("id_orden"));
            orden.setFechaIngreso(rs.getString("fecha_ingreso"));
            orden.setHoraIngreso(rs.getString("hora_ingreso"));
            orden.setHoraSalida(rs.getString("hora_salida"));
            orden.setDuracion(rs.getString("duracion"));
            orden.setCostoFinal(rs.getDouble("costo_final"));
            orden.setObservaciones(rs.getString("observaciones"));
            orden.setIdCliente(rs.getInt("id_cliente"));
            orden.setIdVehiculo(rs.getInt("id_vehiculo"));

        }

    } catch (SQLException e) {

        System.out.println(e.getMessage());

    }

    return orden;

}
}