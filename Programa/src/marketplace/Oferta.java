package marketplace;

import java.util.*;

public class Oferta{
	private String idOferta;
	public Cliente vendedor;
	public ArrayList<Tiquete> tiquetes;
	public Double precio;
	public ArrayList<ContraOferta> contraOfertas;
	public Date fechaCreacion;
	
	
	public oferta(Cliente vendedor, Double precio) {
		this.idOferta = 0;
		this.vendedor = vendedor;
		this.tiquetes = tiquetes;
		this.precio = precio;
		tiquetes = new ArrayList<Tiquetes>();
	}
	
	public void agregarTiquete(Tiquete tiquete) {
		if (tiquete.isTransferible() == false)
			System.out.println("Tiquete no transferible");
		else
			tiquetes.add(tiquete);
		
	}
	
}