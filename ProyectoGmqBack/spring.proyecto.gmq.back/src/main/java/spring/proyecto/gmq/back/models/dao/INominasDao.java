package spring.proyecto.gmq.back.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import spring.proyecto.gmq.back.models.entity.Nominas;

@EnableJpaRepositories
public interface INominasDao extends CrudRepository <Nominas, Long>{

	//Necesitamos una query para mostrar todas las nominas de un empleado
	@Query("select u from Nominas u where u.id_empleado=?1")
	public List<Nominas> findNominaByNumEmpleado(int id);
	
	//Metodo para seleccionar la url de la bbdd
	@Query("select u from Nominas u where to_char(u.fecha, 'MM')=?1")
	public Nominas findNominaMes(String fecha);
	
}
