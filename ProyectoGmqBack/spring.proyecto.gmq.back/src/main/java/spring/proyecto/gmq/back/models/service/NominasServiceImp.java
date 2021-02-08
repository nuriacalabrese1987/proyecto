package spring.proyecto.gmq.back.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.proyecto.gmq.back.models.dao.INominasDao;
import spring.proyecto.gmq.back.models.entity.Nominas;

@Service
public class NominasServiceImp implements INominasService{
	
	@Autowired
	INominasDao nomdao;

	@Override
	public List<Nominas> findNominaByNumEmpleado(int id) {
		// TODO Auto-generated method stub
		return nomdao.findNominaByNumEmpleado(id);
	}

}
