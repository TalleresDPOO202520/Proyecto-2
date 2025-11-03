package marketplace;

import java.util.ArrayList;
import java.util.List;
import usuarios.Cliente;
import usuarios.Administrador;
import tiquetes.Tiquete;

public class Marketplace {

    public HashMap<String, Oferta> ofertasActivas;

    public Marketplace() {
        this.ofertasActivas = new HashMap<>();
    }

    public void publicarOferta(String idOferta, Cliente vendedor, ArrayList<Tiquete> tiquetes, double precio) {
    	Oferta o = new Oferta(idOferta, vendedor, tiquetes, precio);
    	ofertasActivas.put(idOferta, o);
        System.out.println("Ofertas activas:" + ofertasActivas);
    }

    public HashMap<String, Oferta> getOfertasActivas() {
        return ofertasActivas;
    }

    public void eliminarPorVendedor(String idOferta, Cliente vendedor) {
        ofertasActivas.remove(idOferta);
        System.out.prinln("El vendedor " + vendedor + " eliminó su oferta con ID: " + idOferta);
    }

    public void eliminarPorAdmin(String idOferta, Administrador admin) {
    	ofertasActivas.remove(idOferta);
        System.out.prinln("El admin " + admin + " eliminó la oferta con ID: " + idOferta);
    }

    public void contraofertar(String idOferta, String idContra, Cliente comprador, double precio) {
        ContraOferta co = new ContraOferta(idContra, comprador, precio);
        Oferta o = ofertasActivas.get(idOferta);
        
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
