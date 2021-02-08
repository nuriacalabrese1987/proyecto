package spring.proyecto.gmq.back.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import spring.proyecto.gmq.back.models.entity.Empleados;

@EnableJpaRepositories
public interface IEmpleadosDao extends CrudRepository<Empleados, Long>{
	
	@Query("select u from Empleados u where u.telefono=?1")
	public List<Empleados> findByTelefono(String telefono);
	
}
