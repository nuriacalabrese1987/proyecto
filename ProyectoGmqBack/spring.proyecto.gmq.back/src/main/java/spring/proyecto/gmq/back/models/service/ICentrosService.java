package spring.proyecto.gmq.back.models.service;

import org.springframework.stereotype.Service;

import spring.proyecto.gmq.back.models.entity.Centros;

@Service
public interface ICentrosService {

	public Centros findCentroById(Long id);
}
