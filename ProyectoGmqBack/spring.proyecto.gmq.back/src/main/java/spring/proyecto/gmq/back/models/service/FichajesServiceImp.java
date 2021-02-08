package spring.proyecto.gmq.back.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.proyecto.gmq.back.models.dao.IFichajesDao;
import spring.proyecto.gmq.back.models.entity.Fichajes;

@Service
public class FichajesServiceImp implements IFichajesService{

	@Autowired
	IFichajesDao dao;
	
	@Override
	public List<Fichajes> findAllFichajesEmpleado(Long id) {
		// TODO Auto-generated method stub
		return dao.findAllFichajesEmpleado(id);
	}


}
