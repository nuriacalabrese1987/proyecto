package spring.proyecto.gmq.back.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import spring.proyecto.gmq.back.models.entity.Empleados;
import spring.proyecto.gmq.back.models.entity.Fichajes;


@EnableJpaRepositories
public interface IFichajesDao extends CrudRepository<Fichajes, Long>{

	@Query("select u from Fichajes u where u.id_empleado =?1")
	public List<Fichajes> findAllFichajesEmpleado(Long id);
	
	@Query("select u from Empleados u where u.telefono=?1")
	public Empleados buscarPorTfno(String telefono);
}
