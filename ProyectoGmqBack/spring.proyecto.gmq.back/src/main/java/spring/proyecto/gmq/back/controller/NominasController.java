package spring.proyecto.gmq.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.proyecto.gmq.back.models.dao.INominasDao;
import spring.proyecto.gmq.back.models.entity.Nominas;
import spring.proyecto.gmq.back.models.service.INominasService;

@RestController
@RequestMapping("/nominas")
public class NominasController {

	@Autowired
	INominasService nomService;
	@Autowired
	INominasDao dao;
	//Metodo para mostrar todas las nominas de un empleado
	@GetMapping("/verNominas/{id}")
	public List<Nominas> verNominas(@PathVariable int id) {
		return nomService.findNominaByNumEmpleado(id);
	}
	
	//Metodo para descargar nomina por mes
	@GetMapping("/descargarNomina/{hola}")
	public String descargarUltimaNomina(@PathVariable String hola) {
		Nominas nom = new Nominas();
		
		nom = nomService.findNominaMes(hola);
		
		String url = nom.getUrl();
		System.out.println(url);
		return url;
	}
}
