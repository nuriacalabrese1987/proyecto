package spring.proyecto.gmq.back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.proyecto.gmq.back.models.entity.Empleados;
import spring.proyecto.gmq.back.models.entity.Fichajes;
import spring.proyecto.gmq.back.models.service.FichajesServiceImp;
import spring.proyecto.gmq.back.models.service.IEmpleadosService;
import spring.proyecto.gmq.back.models.service.IFichajesService;

@RestController
@RequestMapping("/fichajes")
public class FichajesController {
	
	@Autowired
	IFichajesService fichService;
	
	@Autowired 
	FichajesServiceImp serviceImp;
	
	@GetMapping("/verFichajes/{id}")
	public List<Fichajes> listarFichajes (@PathVariable Long id) {
		
		return fichService.findAllFichajesEmpleado(id);
	}
	
	@PostMapping("/hacerFichaje/{telefono}")
	public ResponseEntity<?> comprobarCara (@PathVariable String telefono, String imagen){
		//Buscamos primero el empleado por telefono
		Empleados emp = serviceImp.buscarPorTfno(telefono);		
		System.out.println(emp.getUrl_storage());
		//Alacenamos la imagen en un String
		String imagen1 = emp.getUrl_storage();
		
		return serviceImp.comprobarCaras(imagen1, imagen);
	}
}
