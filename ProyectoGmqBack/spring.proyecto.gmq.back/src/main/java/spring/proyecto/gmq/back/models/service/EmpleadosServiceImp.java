package spring.proyecto.gmq.back.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.proyecto.gmq.back.models.dao.ICentrosDao;
import spring.proyecto.gmq.back.models.dao.IDepartamentoDao;
import spring.proyecto.gmq.back.models.dao.IEmpleadosDao;
import spring.proyecto.gmq.back.models.dao.INominasDao;
import spring.proyecto.gmq.back.models.entity.Centros;
import spring.proyecto.gmq.back.models.entity.Departamentos;
import spring.proyecto.gmq.back.models.entity.Empleados;
import spring.proyecto.gmq.back.models.entity.Nominas;


@Service
public class EmpleadosServiceImp implements IEmpleadosService{

	@Autowired
	IEmpleadosDao dao;
	@Autowired
	ICentrosDao centdao;
	
	@Autowired
	IDepartamentoDao depdao;
	
	@Autowired
	INominasDao nomdao;
	
	@Autowired
	TwilioService whats;
	
	//Metodo para encontrar al empleado por id
	@Override
	@Transactional(readOnly = true)
	public Empleados findById(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id).orElse(null);
	}

	//Metodo para encontrar el centro del empleado por id
	@Override
	public Centros findCentroById(Long id) {
		// TODO Auto-generated method stub
		return centdao.findById(id).orElse(null);
	}

	//Metodo para encontrar  el departamento del empleado por id
	@Override
	public Departamentos findDepById(Long id) {
		// TODO Auto-generated method stub
		return depdao.findById(id).orElse(null);
	}

	//Metodo para listar todas las nominas de un empleado
	@Override
	public List<Nominas> findNominaByNumEmpleado(int id) {
		// TODO Auto-generated method stub
		return nomdao.findNominaByNumEmpleado(id);
	}

	
	
	//METODO TWILIO PARA CREAR USUARIO POR TOKEN
	@Override
	public Empleados save(Empleados empleado) {
		// TODO Auto-generated method stub
		Empleados rpta = dao.save(empleado);

		whats.sms(empleado);
		
		return rpta;
	}
	
		
	
}
