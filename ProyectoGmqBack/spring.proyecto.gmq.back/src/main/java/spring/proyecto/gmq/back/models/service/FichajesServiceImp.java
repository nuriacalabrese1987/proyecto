package spring.proyecto.gmq.back.models.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.proyecto.gmq.back.models.dao.IFichajesDao;
import spring.proyecto.gmq.back.models.entity.Empleados;
import spring.proyecto.gmq.back.models.entity.Fichajes;
import spring.proyecto.gmq.back.serviciosazure.CompararCaras;

@Service
public class FichajesServiceImp implements IFichajesService{
 
	@Autowired
	IFichajesDao dao;
	
	@Override
	public List<Fichajes> findAllFichajesEmpleado(Long id) {
		// TODO Auto-generated method stub
		return dao.findAllFichajesEmpleado(id);
	}

	public Empleados buscarPorTfno(String telefono) {
		return dao.buscarPorTfno(telefono);
	}

	/*
	 * Metodo para comprobar las caras
	 */
	public Boolean comprobarCaras(String imagen1, String imagen2) {
		Map<String, Object> response = new HashMap<>();
		
		String respuesta = CompararCaras.returnIdentical(imagen1, imagen2);
		if (Double.parseDouble(respuesta) < 0.8) {
			response.put("Respuesta: ", "Estado -> no son la misma persona");
			return false; //Retornamos null para tratar el error desde el front
		} else {
			response.put("Respuesta", "Estado -> son la misma persona");
			return true;
		}
	}

	@Override
	public Fichajes guardarFichaje(Fichajes fichaje) {
		// TODO Auto-generated method stub
		return dao.save(fichaje);
	}
}
