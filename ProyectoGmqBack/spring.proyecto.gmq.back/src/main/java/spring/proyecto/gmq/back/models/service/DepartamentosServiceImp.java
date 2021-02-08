package spring.proyecto.gmq.back.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.proyecto.gmq.back.models.dao.IDepartamentoDao;
import spring.proyecto.gmq.back.models.entity.Departamentos;

@Service
public class DepartamentosServiceImp implements IDepartamentosService{

	@Autowired
	IDepartamentoDao depdao;
	
	//Metodo para encontrar  el departamento del empleado por id
	@Override
	public Departamentos findDepById(Long id) {
		// TODO Auto-generated method stub
		return depdao.findById(id).orElse(null);
	}
}
