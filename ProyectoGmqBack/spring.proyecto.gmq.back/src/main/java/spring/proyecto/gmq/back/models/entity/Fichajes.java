package spring.proyecto.gmq.back.models.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fichajes")
public class Fichajes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long n_fichaje;
	
	private Long id_empleado;
	
	private boolean estado;
	
	@Column(name="fecha")
	//@Temporal(TemporalType.DATE)
	private Timestamp fecha;

	public Long getN_fichaje() {
		return n_fichaje;
	}

	public void setN_fichaje(Long n_fichaje) {
		this.n_fichaje = n_fichaje;
	}
	
	public Long getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(Long id_empleado) {
		this.id_empleado = id_empleado;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha2) {
		this.fecha = fecha2;
	}


	
	
}
