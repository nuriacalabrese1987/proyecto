package spring.proyecto.gmq.back.models.dao;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import spring.proyecto.gmq.back.models.entity.Centros;


@EnableJpaRepositories
public interface ICentrosDao extends CrudRepository<Centros, Long>{

}
