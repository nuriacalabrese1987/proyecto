package spring.proyecto.gmq.back.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.proyecto.gmq.back.models.entity.Centros;
import spring.proyecto.gmq.back.models.entity.Departamentos;
import spring.proyecto.gmq.back.models.entity.Empleados;
import spring.proyecto.gmq.back.models.entity.Nominas;

@Service
public interface IEmpleadosService {
	
	public List<Empleados> listar();

	public Empleados findById(Long id);
	
	public Empleados save(Empleados empleado);

	public List<Empleados> findByTelefono(String telefono);

	public Boolean getLogin(String telefono, String token);
	
}
