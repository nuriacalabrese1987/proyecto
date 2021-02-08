package spring.proyecto.gmq.back.models.service;

import org.springframework.stereotype.Service;

import spring.proyecto.gmq.back.models.entity.Departamentos;

@Service
public interface IDepartamentosService {

	public Departamentos findDepById(Long id);
}
