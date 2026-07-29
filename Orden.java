package modelo;

public class Orden {

    private int idOrden;

    private String fechaIngreso;
    private String horaIngreso;
    private String horaSalida;
    private String duracion;

    private double costoFinal;

    private String observaciones;

    private int idCliente;
    private int idVehiculo;
    private int idServicio;
    
    private String nombreCliente;
    private String nombreVehiculo;
    private String nombreServicio;

    public Orden() {
    }

    public Orden(int idOrden,
                 String fechaIngreso,
                 String horaIngreso,
                 String horaSalida,
                 double costoFinal,
                 String observaciones,
                 int idCliente,
                 int idVehiculo) {

        this.idOrden = idOrden;
        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.costoFinal = costoFinal;
        this.observaciones = observaciones;
        this.idCliente = idCliente;
        this.idVehiculo = idVehiculo;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double getCostoFinal() {
        return costoFinal;
    }

    public void setCostoFinal(double costoFinal) {
        this.costoFinal = costoFinal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    
    public String getNombreCliente() {
    return nombreCliente;
}

public void setNombreCliente(String nombreCliente) {
    this.nombreCliente = nombreCliente;
}

public String getNombreVehiculo() {
    return nombreVehiculo;
}

public void setNombreVehiculo(String nombreVehiculo) {
    this.nombreVehiculo = nombreVehiculo;
}

public String getNombreServicio() {
    return nombreServicio;
}

public void setNombreServicio(String nombreServicio) {
    this.nombreServicio = nombreServicio;
}

public int getIdServicio() {
    return idServicio;
}

public void setIdServicio(int idServicio) {
    this.idServicio = idServicio;
}

public String getDuracion() {
    return duracion;
}

public void setDuracion(String duracion) {
    this.duracion = duracion;
}
    @Override
public String toString() {

    return String.valueOf(idOrden);

}
}