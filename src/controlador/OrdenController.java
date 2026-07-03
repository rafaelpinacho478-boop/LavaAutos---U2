package controlador;

import dao.OrdenDAO;
import java.util.ArrayList;
import modelo.Orden;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OrdenController {

    private OrdenDAO ordenDAO;

    public OrdenController() {

        ordenDAO = new OrdenDAO();

    }

    public ArrayList<Orden> listarOrdenes() {

    return ordenDAO.listarOrdenes();

}
    
    public ArrayList<Orden> listarOrdenesPendientes() {

    return ordenDAO.listarOrdenesPendientes();

}
    
    public int guardarOrden(Orden orden) {

        return ordenDAO.guardarOrden(orden);

    }

    public boolean guardarOrdenServicio(int idOrden,
                                        int idServicio,
                                        double costoFinal) {

        return ordenDAO.guardarOrdenServicio(
                idOrden,
                idServicio,
                costoFinal);

    }
    
    public boolean actualizarOrden(Orden orden) {

    return ordenDAO.actualizarOrden(orden);

}

public boolean actualizarOrdenServicio(int idOrden,
                                       int idServicio,
                                       double costo) {

    return ordenDAO.actualizarOrdenServicio(
            idOrden,
            idServicio,
            costo);

}

public boolean registrarSalida(int idOrden) {

    Orden orden = ordenDAO.obtenerOrdenPorId(idOrden);

    if (orden == null) {

        return false;

    }

    DateTimeFormatter formato =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    LocalTime horaIngreso =
            LocalTime.parse(
                    orden.getHoraIngreso(),
                    formato);

    LocalTime horaSalida =
            LocalTime.now();

    Duration diferencia =
        Duration.between(horaIngreso, horaSalida);

long horas = diferencia.toHours();

long minutos = diferencia.toMinutesPart();

long segundos = diferencia.toSecondsPart();

String duracion;

if (horas > 0) {

    if (minutos > 0) {

        duracion = horas +
                (horas == 1 ? " hora " : " horas ")
                + minutos +
                (minutos == 1 ? " minuto" : " minutos");

    } else {

        duracion = horas +
                (horas == 1 ? " hora" : " horas");

    }

} else if (minutos > 0) {

    duracion = minutos +
            (minutos == 1 ? " minuto" : " minutos");

} else {

    duracion = segundos +
            (segundos == 1 ? " segundo" : " segundos");

}

    return ordenDAO.registrarSalida(
            idOrden,
            horaSalida.format(formato),
            duracion);

}

public Orden obtenerOrdenTicket(int idOrden) {

    return ordenDAO.obtenerOrdenTicket(idOrden);

}

public Orden obtenerOrdenPorId(int idOrden) {

    return ordenDAO.obtenerOrdenPorId(idOrden);

}
}