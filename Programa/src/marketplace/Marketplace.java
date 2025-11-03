package marketplace;

import java.util.ArrayList;
import java.util.List;
import usuarios.Cliente;
import usuarios.Administrador;
import tiquetes.Tiquete;

public class Marketplace {

    public ArrayList<Oferta> ofertasActivas;

    public Marketplace() {
        this.ofertasActivas = new ArrayList<>();
    }

    public Oferta publicarOferta(String idOferta, Cliente vendedor, ArrayList<Tiquete> tiquetes, double precio) {
        // TODO
        return null;
    }

    public List<Oferta> ofertasPublicadas() {
        // TODO
        return null;
    }

    public void eliminarPorVendedor(String idOferta, Cliente vendedor) {
        // TODO
    }

    public void eliminarPorAdmin(String idOferta, Administrador admin) {
        // TODO
    }

    public ContraOferta contraofertar(String idOferta, String idContra, Cliente comprador, double precio) {
        // TODO
        return null;
    }

    public void aceptarContraOferta(String idOferta, String idContra, Cliente vendedor) {
        // TODO
    }

    public void comprarPrecioFijo(String idOferta, Cliente comprador) {
        // TODO
    }

    public Oferta getOferta(String idOferta) {
        // TODO
        return null;
    }
}
