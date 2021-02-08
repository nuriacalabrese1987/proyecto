package spring.proyecto.gmq.back.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "departamentos")
public class Departamentos {

	@Id
	private Long n_departamento;
	
	private String nombre;

	public Long getN_departamento() {
		return n_departamento;
	}

	public void setN_departamento(Long n_departamento) {
		this.n_departamento = n_departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
