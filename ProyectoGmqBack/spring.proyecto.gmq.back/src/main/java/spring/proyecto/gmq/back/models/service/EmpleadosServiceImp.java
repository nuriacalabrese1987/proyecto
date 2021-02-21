package spring.proyecto.gmq.back.models.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.jdi.Location;

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
	
	private Location ubicacion;
	
	
	
	
	//METODO PARA LISTAR EMPLEADOS 
	
	@Override
	public List<Empleados> listar() {
		// TODO Auto-generated method stub
		return (List<Empleados>) dao.findAll();
	}
	
	
	
	//METODO PARA ENCONTRAR EL EMPLEADO POR ID
	
	@Override
	@Transactional(readOnly = true)
	public Empleados findById(Long id) {
		// TODO Auto-generated method stub
		
		
		return dao.findById(id).orElse(null);
	}

	//METODO PARA BUSCAR POR TELEFONO
	
	public List<Empleados> findByTelefono(String telefono) {
		List<Empleados> empleado=dao.findByTelefono(telefono);
		//TwilioService.sms(empleado.get(0));
		return empleado;
		
	}
	
	public Boolean solicitarToken(String telefono) {
		List<Empleados> empleado=findByTelefono(telefono);
		TwilioService.sms(empleado.get(0));
		System.out.println(empleado.get(0).getDireccion());
		Boolean respuesta=true;
		return respuesta;
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
		
		return rpta;
	}
	
	
	@Override
	public Boolean getLogin(String telefono, String token) {
		Boolean authToken;
		List<Empleados> empleado = findByTelefono(telefono);
		System.out.println("aqui tambien");
		String tokenEmpleado="\""+String.valueOf(empleado.get(0).getToken()+"\"");
		if(tokenEmpleado.equals(token)) {
			System.out.println("al true");
			authToken=true;
		}else {
			System.out.println(tokenEmpleado);
			System.out.println(token);
			authToken=false;
		}
		System.out.println(authToken);
		return authToken;
	}
		
	
}
