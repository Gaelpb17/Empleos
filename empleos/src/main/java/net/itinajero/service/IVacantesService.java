package net.itinajero.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.itinajero.model.Vacante;

public interface IVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	void guardar (Vacante vacante);
	List<Vacante> buscarDestacadas();
	void eliminar(Integer idvacante);
	List<Vacante> BuscarbyExample(Example<Vacante> Example);
	Page<Vacante>buscarTodas(Pageable page);
}
