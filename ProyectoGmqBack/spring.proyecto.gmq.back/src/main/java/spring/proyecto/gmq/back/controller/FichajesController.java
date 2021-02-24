package spring.proyecto.gmq.back.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.proyecto.gmq.back.models.entity.Centros;
import spring.proyecto.gmq.back.models.entity.Empleados;
import spring.proyecto.gmq.back.models.entity.Fichajes;
import spring.proyecto.gmq.back.models.service.CentrosServiceImp;
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
	
	@Autowired
	CentrosServiceImp centroService;
	
	@GetMapping("/verFichajes/{id}")
	public List<Fichajes> listarFichajes (@PathVariable Long id) {
		
		return fichService.findAllFichajesEmpleado(id);
	}
	
	
	@PostMapping("/hacerFichaje/{telefono}/{distancia}")
	public Boolean comprobarCara (@PathVariable String telefono, 
			@PathVariable float distancia, 
			@RequestBody String imagen){
		
		Map<String, Object> response = new HashMap<>();
		//Buscamos primero el empleado por telefono
		Empleados emp = serviceImp.buscarPorTfno(telefono);
		//Buscamos el centro mediante el empleado
		Centros centro = centroService.findCentroById((long) emp.getN_centro());
		//Guardamos la longitud y la latitud
		double lat = centro.getLatitud();
		double longi = centro.getLongitud();
		//Creamos un nuevo fichaje para establecer a true o false
		Fichajes fichaje = new Fichajes();
		
		Fichajes fichajeNuevo = null; //Nuevo fichaje que guardaremos
		
		System.out.println(emp.getUrl_storage());
		
		//Alacenamos la imagen en un String
		String imagen1 = emp.getUrl_storage();
		
		boolean result = serviceImp.comprobarCaras(imagen1, imagen);
		
		Date date = new Date();
		//date.getTime();
		java.sql.Timestamp fecha = new java.sql.Timestamp(date.getTime());
		System.out.println(distancia);
		if (result == true  && distancia <= 150) {
			

			System.out.println("\n\nLas caras son iguales");
			fichaje.setFecha(fecha);
			fichaje.setId_empleado(emp.getId_empleado());
			fichaje.setEstado(true);
			//System.out.println(d);

			fichajeNuevo = fichService.guardarFichaje(fichaje);
			response.put("Resultado: ", fichaje.isEstado());
			return true;
			
		} else {
			System.out.println("Ha entrado aqui...");
			fichaje.setFecha(fecha);
			
			fichaje.setId_empleado(emp.getId_empleado());
			fichaje.setEstado(false);
			fichajeNuevo = fichService.guardarFichaje(fichaje);
			return false;
		}	
		
	}
	
	//Metodo seleccion del centro
	@GetMapping("hacerFichaje/{numCentro}")
	public Centros getLatLongCentro(@PathVariable int numCentro){
		Centros centro = centroService.findCentroById((long)numCentro);
		
		return centro;
		
	}
}
