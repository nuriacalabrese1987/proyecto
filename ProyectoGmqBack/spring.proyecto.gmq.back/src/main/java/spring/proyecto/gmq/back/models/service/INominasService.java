package spring.proyecto.gmq.back.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.proyecto.gmq.back.models.entity.Nominas;

@Service
public interface INominasService {
	
	public List<Nominas> findNominaByNumEmpleado(int id);
	
	public Nominas findNominaMes(String fecha);
}
