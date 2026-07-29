package dao;

import java.util.ArrayList;
import modelo.Servicio;

public class ServicioDAO {
    
    private static final ArrayList<Servicio> listaServicios = new ArrayList<>();
    private static int contadorId = 1;

    // Bloque estático corregido usando métodos set seguros
    static {
        try {
            Servicio s1 = new Servicio();
            s1.setIdServicio(contadorId++);
            s1.setNombre("Lavado Sencillo");
            listaServicios.add(s1);

            Servicio s2 = new Servicio();
            s2.setIdServicio(contadorId++);
            s2.setNombre("Lavado Completo");
            listaServicios.add(s2);

            Servicio s3 = new Servicio();
            s3.setIdServicio(contadorId++);
            s3.setNombre("Lavado de Motor");
            listaServicios.add(s3);

            Servicio s4 = new Servicio();
            s4.setIdServicio(contadorId++);
            s4.setNombre("Servicio VIP");
            listaServicios.add(s4);
        } catch (Exception e) {
            // Evita que un error de inicialización detenga el programa
        }
    }

    public ArrayList<Servicio> listarServicios() {
        return new ArrayList<>(listaServicios);
    }

    public boolean guardarService(Servicio servicio) {
        try {
            servicio.setIdServicio(contadorId++);
            listaServicios.add(servicio);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizarServicio(Servicio servicio) {
        for (int i = 0; i < listaServicios.size(); i++) {
            if (listaServicios.get(i).getIdServicio() == servicio.getIdServicio()) {
                listaServicios.set(i, servicio);
                return true;
            }
        }
        return false;
    }
}