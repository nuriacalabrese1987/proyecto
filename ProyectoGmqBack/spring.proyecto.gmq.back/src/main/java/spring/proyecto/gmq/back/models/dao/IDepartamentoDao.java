package spring.proyecto.gmq.back.models.dao;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import spring.proyecto.gmq.back.models.entity.Departamentos;

@EnableJpaRepositories
public interface IDepartamentoDao extends CrudRepository <Departamentos, Long>{
	
}
