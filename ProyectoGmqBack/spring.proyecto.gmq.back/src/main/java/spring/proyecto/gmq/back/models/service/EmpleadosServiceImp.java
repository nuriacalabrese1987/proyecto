package spring.proyecto.gmq.back.models.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.proyecto.gmq.back.models.dao.IEmpleadosDao;
import spring.proyecto.gmq.back.models.dao.INominasDao;

import spring.proyecto.gmq.back.models.entity.Empleados;
import spring.proyecto.gmq.back.models.entity.Nominas;
import spring.proyecto.gmq.back.serviciosazure.CompararCaras;


@Service
public class EmpleadosServiceImp implements IEmpleadosService{

	@Autowired
	IEmpleadosDao dao;
	
	@Autowired
	INominasDao nomdao;
	
	@Autowired
	TwilioService whats;
	
	@Override
	public List<Empleados> listar() {
		// TODO Auto-generated method stub
		return (List<Empleados>) dao.findAll();
	}
	//Metodo para encontrar al empleado por id
	@Override
	@Transactional(readOnly = true)
	public Empleados findById(Long id) {
		// TODO Auto-generated method stub
		
		
		return dao.findById(id).orElse(null);
	}

	
	public List<Empleados> findByTelefono(String telefono) {
		List<Empleados> empleado=dao.findByTelefono(telefono);
		TwilioService.sms(empleado.get(0));
		return empleado;
		
	}

	/*
	 * 	METODO PARA COMPROBAR LAS CARAS
	 */
	public ResponseEntity<?> comprobarCaras(String imagen1, String imagen2) {
		Map<String, Object> response = new HashMap<>();
		
		String respuesta = CompararCaras.returnIdentical(imagen1, imagen2);
		if (Double.parseDouble(respuesta) < 0.8) {
			response.put("Respuesta: ", "Estado -> no son la misma persona");
			return null; //Retornamos null para tratar el error desde el front
		} else {
			response.put("Respuesta", "Estado -> son la misma persona");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
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
