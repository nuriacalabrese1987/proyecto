package spring.proyecto.gmq.back.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "nominas")
public class Nominas {
	
	private int n_nomina;
	
	private int id_empleado;
	
	private String url;
	
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	//Getters & Setters
	
	public int getN_nomina() {
		return n_nomina;
	}

	public void setN_nomina(int n_nomina) {
		this.n_nomina = n_nomina;
	}


	public int getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
