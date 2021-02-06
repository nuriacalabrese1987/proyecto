package spring.proyecto.gmq.back.models.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "departamentos")
public class Departamentos {

	private int n_departamento;
	
	private String nombre;

	public int getN_departamento() {
		return n_departamento;
	}

	public void setN_departamento(int n_departamento) {
		this.n_departamento = n_departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
