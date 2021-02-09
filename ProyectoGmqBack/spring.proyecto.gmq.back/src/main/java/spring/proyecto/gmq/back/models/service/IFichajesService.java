package spring.proyecto.gmq.back.models.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import spring.proyecto.gmq.back.models.entity.Fichajes;

@Service
public interface IFichajesService {
	

	public List<Fichajes> findAllFichajesEmpleado(Long id);
	
	public Fichajes guardarFichaje(Fichajes fichaje);
	
}
