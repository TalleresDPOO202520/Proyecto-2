package marketplace;

import java.time.LocalDateTime;
import usuarios.Cliente;

public class ContraOferta {

    private String idContra;
    public Cliente comprador;
    public Double precioPropuesto;
    public int estado;              // 0=pendiente, 1=aceptada, 2=rechazada
    public LocalDateTime fecha;

    public ContraOferta(String idContra, Cliente comprador, Double precioPropuesto) {
        // TODO: inicializar atributos
    }

    public String getIdContra() {
        // TODO
        return null;
    }

    public Cliente getComprador() {
        // TODO
        return null;
    }

    public Double getPrecioPropuesto() {
        // TODO
        return null;
    }

    public int getEstado() {
        // TODO
        return 0;
    }

    public LocalDateTime getFecha() {
        // TODO
        return null;
    }

    public void marcarAceptada() {
        estado = 1;
    }

    public void marcarRechazada() {
        estado = 2;
    }
}
