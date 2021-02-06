package spring.proyecto.gmq.back.models.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
/*
 * Pojo centros
 */

@Entity
@Table(name = "centros")
public class Centros {
	
	private String nombre;
	
	private int n_centro;
	
	private String direccion;
	
	private double latitud;
	
	private double longitud;

	
	//Getters & Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getN_centro() {
		return n_centro;
	}

	public void setN_centro(int n_centro) {
		this.n_centro = n_centro;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	
}
