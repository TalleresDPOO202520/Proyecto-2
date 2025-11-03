package marketplace;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import usuarios.Cliente;
import tiquetes.Tiquete;


public class Oferta{
	private String idOferta;
	public Cliente vendedor;
	public ArrayList<Tiquete> tiquetes;
	public Double precio;
	public int estado; //0: publicado, 1: vendido, 2: eliminado
	public ArrayList<ContraOferta> contraOfertas;
	public Date fechaCreacion;
	
	
    public Oferta(String idOferta, Cliente vendedor, ArrayList<Tiquete> tiquetes, double precio) {
        if (vendedor == null || tiquetes == null || tiquetes.isEmpty())
            throw new IllegalArgumentException("Datos inválidos para crear oferta.");

        for (Tiquete t	: tiquetes) {
            if (!t.isTransferible() || t.isTransferido()) {
                throw new IllegalArgumentException("No se puede incluir un tiquete no transferible o ya transferido.");
            }
        }

        this.idOferta = idOferta;
        this.vendedor = vendedor;
        this.tiquetes = new ArrayList<Tiquete>(tiquetes);
        this.precio = precio;
        this.estado = 0;
        this.fechaCreacion = LocalDateTime.now();
        this.contraOfertas = new ArrayList<ContraOferta>();
    }
    
    
    public vender(Cliente comprador){
    	this.estado = 1
    	
    }
		
}