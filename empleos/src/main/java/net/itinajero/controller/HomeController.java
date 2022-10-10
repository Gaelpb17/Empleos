package net.itinajero.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.model.Perfil;
import net.itinajero.model.Usuario;
import net.itinajero.model.Vacante;
import net.itinajero.service.ICategoriasService;
import net.itinajero.service.IUsuariosService;
import net.itinajero.service.IVacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	@Autowired
	private IVacantesService serviceVacantes;

		@Autowired
	    private IUsuariosService serviceUsuarios;

		@GetMapping("/signup")
		public String registrarse(Usuario usuario,Model model) {
			return "usuarios/formRegistro";
		}

		@PostMapping("/signup")
		public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
			attributes = attributes;
			usuario.setEstatus(1);
			usuario.setFechaRegistro(new Date());

			// creamos el perfil que le asignaremos al usuario nuevo
			Perfil perfil = new Perfil();
			perfil.setId(3);
			
			// GUARDAMOS el USUARIO en la base de datos
			// el perfil se guarda automaticamente
			serviceUsuarios.guardar(usuario);
			attributes.addFlashAttribute("msg", "Usuario registrado exitosamente");		
			
			return "redirect:/usuarios/index";
		}
		
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return "tabla";
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero e Comunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a internet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero de Sistemas");
		lista.add("Auxiliar de Contabulidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos", lista);
		
		return "listado";
	}
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return "home";
	}
	
	
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante) {
		System.out.println("buscando por" +  vacante);
		
		// esta configuracion sirve para que en la consulta select se ejecute:
		// where descripcion like '%?%'
		//ExampleMatcher matcher = ExampleMatcher.matching().
			//	withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		//Example<Vacante> example = Example.of(vacante, matcher);
		///List<Vacante> lista = serviceVacantes.BuscarbyExample(example);
		//model.addAttribute("vacantes", lista);
		return "home";
	}
	@ModelAttribute
	public void setGenericos(Model model) {
		Vacante vacanteSearch = new Vacante();
		vacanteSearch.reset();
	 	model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		model.addAttribute("search", vacanteSearch);
	


	
	}
}
