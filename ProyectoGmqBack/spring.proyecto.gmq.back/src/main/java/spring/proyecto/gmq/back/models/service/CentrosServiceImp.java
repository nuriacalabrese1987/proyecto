package spring.proyecto.gmq.back.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.proyecto.gmq.back.models.dao.ICentrosDao;
import spring.proyecto.gmq.back.models.entity.Centros;

@Service
public class CentrosServiceImp implements ICentrosService{

	@Autowired
	ICentrosDao centdao;
	
	@Override
	public Centros findCentroById(Long id) {
		// TODO Auto-generated method stub
		return centdao.findById(id).orElse(null);
	}

}
