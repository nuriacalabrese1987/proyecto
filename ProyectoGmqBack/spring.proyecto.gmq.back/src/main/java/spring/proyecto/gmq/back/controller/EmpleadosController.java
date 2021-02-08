package spring.proyecto.gmq.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spring.proyecto.gmq.back.models.entity.Centros;
import spring.proyecto.gmq.back.models.entity.Departamentos;
import spring.proyecto.gmq.back.models.entity.Empleados;
import spring.proyecto.gmq.back.models.service.EmpleadosServiceImp;
import spring.proyecto.gmq.back.models.service.ICentrosService;
import spring.proyecto.gmq.back.models.service.IDepartamentosService;
import spring.proyecto.gmq.back.models.service.IEmpleadosService;

@RestController
@RequestMapping("/empleados")
public class EmpleadosController {

	@Autowired
	EmpleadosServiceImp empservice;
	
	@Autowired
	IEmpleadosService service;
	
	@Autowired
	ICentrosService centService;
	
	@Autowired
	IDepartamentosService depService;
	
	
	//Metodo para listar empleados
	@GetMapping("/listar")
	public List<Empleados> listar() {
		return empservice.listar();
	}
	
	//Metodo para listar un empleado por su id
	@SuppressWarnings("unused")
	@GetMapping("/listar/{id}")
	public ResponseEntity<?> show (@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		Empleados empleado = service.findById(id); //Buscamos al empleado por su id		
		Centros centro = centService.findCentroById((long) empleado.getN_centro()); //Buscamos el centro a traves del numero del centro del empleado		
		Departamentos dep = depService.findDepById((long) empleado.getN_departamento()); //Hacemos lo mismo para saber su departamento
		
		/*
		try {
			empservice.findById(id);
		} catch (DataAccessException e){
			  response.put("mensaje", "Error al realizar la consulta en la base de datos");
			  return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
		
		if (empleado == null) {
			response.put("mensaje", "La visita con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			response.put("Ver info: ", empleado);
			response.put("Bienvenido de nuevo: ", empleado.getNombre() +" " + empleado.getApellidos());
			response.put("Tu centro es: (centro numero: " + centro.getN_centro() + ") "
					, centro.getNombre() + " , ubicado en: " + centro.getDireccion());
			response.put("Departamento: ", dep.getNombre());
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

			//return new ResponseEntity<Empleados>(empleado, HttpStatus.OK);
		}
	}
	
	//Metodo para solicitar el token
	@GetMapping("token/{telefono}")
	public List<Empleados> solicitarToken(@PathVariable String telefono){
		return service.findByTelefono(telefono);
	}
	
	//Metodo para detectar caras
	@PostMapping("/fichajeCara/{id}")
	public ResponseEntity<?> comprobarCaraArray (@PathVariable Long id, @RequestBody String imagen) {
		Empleados emp = empservice.findById(id);
		System.out.println(emp.getUrl_storage());
		
		String imagen1 = emp.getUrl_storage();
		
		return empservice.comprobarCaras(imagen1, imagen);
	}
}
