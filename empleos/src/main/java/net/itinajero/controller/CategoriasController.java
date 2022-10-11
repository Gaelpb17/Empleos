package net.itinajero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.model.Categoria;
import net.itinajero.service.ICategoriasService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
	
	@Autowired
	//@Qualifier("categoriasServiceJpa")
	private ICategoriasService serviceCategorias;

	// Ejercicio del video 12
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista = serviceCategorias.buscarTodas();
        model.addAttribute("categorias", lista);
		return "categorias/listCategorias"; 
	}
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Categoria> lista = serviceCategorias.buscarTodas(page);
	model.addAttribute("categorias", lista);
	return "categorias/listCategorias";
	}


	// @GetMapping("/create") Rresenta la URL, la c ual es "localhost:8080/create*/
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String crear(Categoria categoria) {
		return "categorias/formCategoria";
	}

	// @PostMapping("/save")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			return "categorias/formCategoria";
		}
		serviceCategorias.guardar(categoria); 
		attributes.addFlashAttribute("msg", "Los datos de la categoria estan guardados"); 															
	    return "redirect:/categorias/index";
	
	}
	// METODO PARA ELIMINAR UNA CATEGORIA
	@RequestMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes attributes) {
		
		try {
			System.out.println("Borrando categoria con id: " + idCategoria);
			serviceCategorias.eliminar(idCategoria);
			attributes.addFlashAttribute("msg","La categoria fue eliminada");
			//attributes.addFlashAttribute("msg", "La categoria fue eliminada!");
			
		} catch (Exception e) {
			attributes.addFlashAttribute("msg","No se puede eliminar una categoria con una vacante");
		}
		return "redirect:/categorias/index";
		
	}
	@GetMapping("/edit/{id}")
	private String editar(@PathVariable("id") int idCategoria, Model model) {
		Categoria categoria = serviceCategorias.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "categorias/formCategoria";
		
	}
	@ModelAttribute
	public void setGenericos(Model model) { 
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
	}
}
