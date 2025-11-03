package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import marketplace.Marketplace;
import marketplace.Oferta;
import tiquetes.Tiquete;
import usuarios.Cliente;
import usuarios.Administrador;

/**
 * Pruebas unitarias para Marketplace.
 * - Usa una clase StubTiquete mínima para evitar dependencias de eventos/localidades.
 * - Cubre publicar, eliminar por vendedor/admin, contraofertar, aceptar contraoferta y compra a precio fijo.
 */
public class MarketplaceTest {

    private Marketplace mp;
    private Cliente vendedor;
    private Cliente comprador;
    private Administrador admin;

    /** Stub mínima de Tiquete que permite asumir transferible=true y transferido=false */
    static class StubTiquete extends Tiquete {
        public StubTiquete(String id) {
            super(null, null, id);
            // transferible = true (default), transferido = false (default)
        }
        @Override public double calcularPrecioTotal(tiquetes.PoliticaCargos cargos) { return 0.0; }
    }

    private ArrayList<Tiquete> tiquetesTransferibles(int n) {
        ArrayList<Tiquete> lista = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            lista.add(new StubTiquete("T" + i));
        }
        return lista;
    }

    @BeforeEach
    void setUp() {
        mp = new Marketplace();
        vendedor = new Cliente("vend1", "pwd");
        comprador = new Cliente("comp1", "pwd");
        admin = new Administrador("admin", "pwd", "A1", "Root");
    }

    @Test
    @DisplayName("publicarOferta: crea y registra oferta activa")
    void testPublicarOferta() {
        ArrayList<Tiquete> tks = tiquetesTransferibles(2);
        mp.publicarOferta("OF1", vendedor, tks, 100.0);

        HashMap<String, Oferta> activas = mp.getOfertasActivas();
        assertTrue(activas.containsKey("OF1"), "La oferta debe quedar registrada");
        Oferta o = activas.get("OF1");
        assertNotNull(o);
        assertEquals(vendedor, o.getVendedor());
        assertEquals(100.0, o.getPrecio(), 0.0001);
        assertEquals(2, o.getTiquetes().size());
        assertEquals(0, o.estado, "Estado inicial debe ser publicado (0)");
        assertNotNull(o.fechaCreacion, "Debe registrar fecha de creación");
    }

    @Test
    @DisplayName("eliminarPorVendedor: marca eliminada y saca del mapa")
    void testEliminarPorVendedor() {
        ArrayList<Tiquete> tks = tiquetesTransferibles(1);
        mp.publicarOferta("OF2", vendedor, tks, 50.0);
        Oferta ref = mp.getOferta("OF2");
        assertNotNull(ref);

        mp.eliminarPorVendedor("OF2", vendedor);

        assertEquals(2, ref.estado, "La oferta debe quedar marcada como eliminada (2)");
        assertFalse(mp.getOfertasActivas().containsKey("OF2"), "La oferta debe ser removida del mapa");
    }

    @Test
    @DisplayName("eliminarPorAdmin: marca eliminada y saca del mapa")
    void testEliminarPorAdmin() {
        ArrayList<Tiquete> tks = tiquetesTransferibles(1);
        mp.publicarOferta("OF3", vendedor, tks, 75.0);
        Oferta ref = mp.getOferta("OF3");
        assertNotNull(ref);

        mp.eliminarPorAdmin("OF3", admin);

        assertEquals(2, ref.estado, "La oferta debe quedar marcada como eliminada (2)");
        assertFalse(mp.getOfertasActivas().containsKey("OF3"), "La oferta debe ser removida del mapa");
    }

    @Test
    @DisplayName("contraOfertar: registra una contraoferta en la oferta")
    void testContraOfertar() {
        ArrayList<Tiquete> tks = tiquetesTransferibles(1);
        mp.publicarOferta("OF4", vendedor, tks, 120.0);

        mp.contraOfertar("OF4", "CO1", comprador, 110.0);
        Oferta o = mp.getOferta("OF4");

        assertNotNull(o.getContraOfertas().get("CO1"),
                "La contraoferta debe existir en el mapa de la oferta");
    }

    @Test
    @DisplayName("aceptarContraOferta: cambia el estado de la contraoferta a aceptada (1)")
    void testAceptarContraOferta() {
        ArrayList<Tiquete> tks = tiquetesTransferibles(1);
        mp.publicarOferta("OF5", vendedor, tks, 200.0);
        mp.contraOfertar("OF5", "CO2", comprador, 180.0);

        mp.aceptarContraOferta("OF5", "CO2", vendedor);
        Oferta o = mp.getOferta("OF5");

        assertEquals(1, o.getContraOfertas().get("CO2").estado,
                "La contraoferta debe quedar en estado aceptada (1)");
    }

    @Test
    @DisplayName("comprarPrecioFijo: marca vendida y saca del mapa")
    void testComprarPrecioFijo() {
        ArrayList<Tiquete> tks = tiquetesTransferibles(2);
        mp.publicarOferta("OF6", vendedor, tks, 300.0);

        Oferta ref = mp.getOferta("OF6");
        mp.comprarPrecioFijo("OF6", comprador);

        assertEquals(1, ref.estado, "La oferta debe quedar marcada como vendida (1)");
        assertFalse(mp.getOfertasActivas().containsKey("OF6"), "La oferta debe ser removida del mapa");
    }
}
