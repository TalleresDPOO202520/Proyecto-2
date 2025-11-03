package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.*;

public class MarketplaceTest {

    Marketplace mp;
    Cliente vendedor;
    Cliente comprador;
    Administrador admin;

    @BeforeEach
    void setUp() {
        mp = new Marketplace();
        vendedor = new Cliente("v1");
        comprador = new Cliente("c1");
        admin = new Administrador("a1");
    }

    private ArrayList<Tiquete> tiquetes(int n){
        ArrayList<Tiquete> list = new ArrayList<>();
        for(int i = 0; i < n; i++) list.add(new Tiquete());
        return list;
    }

    @Test
    void publicarOferta() {
        mp.publicarOferta("O1", vendedor, tiquetes(2), 100.0);
        assertTrue(mp.getOfertasActivas().containsKey("O1"));
        Oferta o = mp.getOfertasActivas().get("O1");
        assertNotNull(o);
        assertEquals(100.0, o.getPrecio(), 0.001);
        assertEquals(vendedor, o.getVendedor());
        assertEquals(2, o.getTiquetes().size());
    }

    @Test
    void eliminarPorVendedor() {
        mp.publicarOferta("O2", vendedor, tiquetes(1), 50.0);
        mp.eliminarPorVendedor("O2", vendedor);

        assertFalse(mp.getOfertasActivas().containsKey("O2"));
    }

    @Test
    void eliminarPorAdmin() {
        mp.publicarOferta("O3", vendedor, tiquetes(1), 70.0);
        mp.eliminarPorAdmin("O3", admin);
        assertFalse(mp.getOfertasActivas().containsKey("O3"));
    }

    @Test
    void contraOfertar() {
        mp.publicarOferta("O4", vendedor, tiquetes(1), 120.0);
        mp.contraOfertar("O4", "C1", comprador, 110.0);

        Oferta o = mp.getOfertasActivas().get("O4");
        HashMap<String, ContraOferta> cos = o.getContraOfertas();
        assertNotNull(cos);
        assertTrue(cos.containsKey("C1"));
        assertEquals(110.0, cos.get("C1").getPrecioPropuesto(), 0.001);
    }

    @Test
    void aceptarContraOferta() {
        mp.publicarOferta("O5", vendedor, tiquetes(1), 150.0);
        mp.contraOfertar("O5", "C2", comprador, 140.0);
        mp.aceptarContraOferta("O5", "C2", vendedor);

        Oferta o = mp.getOfertasActivas().get("O5");
        assertEquals(1, o.getContraOfertas().get("C2").getEstado());
    }

    @Test
    void comprarPrecioFijo() {
        mp.publicarOferta("O6", vendedor, tiquetes(1), 200.0);
        mp.comprarPrecioFijo("O6", comprador);
        assertFalse(mp.getOfertasActivas().containsKey("O6"));
    }

    @Test
    void getOferta_debeRetornarLaOfertaCorrecta() {
        mp.publicarOferta("O7", vendedor, tiquetes(1), 99.0);
        Oferta o = mp.getOferta("O7");
        assertNotNull(o);
        assertEquals(99.0, o.getPrecio(), 0.001);
    }
}