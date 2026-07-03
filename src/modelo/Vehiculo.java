package modelo;

public class Vehiculo {

    private int idVehiculo;
    private int idCliente;
    private String marca;
    private String modelo;
    private String color;
    private String tipo;
    private String observaciones;
    private String nombreCliente;

    public Vehiculo() {
    }

    public Vehiculo(int idVehiculo,
                    int idCliente,
                    String marca,
                    String modelo,
                    String color,
                    String tipo,
                    String observaciones) {

        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipo = tipo;
        this.observaciones = observaciones;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdCliente() {
    return idCliente;
    }

    public void setIdCliente(int idCliente) {
    this.idCliente = idCliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String getNombreCliente() {
    return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    @Override
public String toString() {

    return marca + " " + modelo;

}
}