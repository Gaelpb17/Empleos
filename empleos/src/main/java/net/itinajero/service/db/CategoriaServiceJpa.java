package net.itinajero.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.model.Categoria;
import net.itinajero.model.Vacante;
import net.itinajero.repository.CategoriasRepository;
import net.itinajero.service.ICategoriasService;

@Service
@Primary
public class CategoriaServiceJpa implements ICategoriasService {
	
	@Autowired
	private CategoriasRepository CategoriasRepo;

	@Override
	public void guardar(Categoria categoria) {
		// TODO Auto-generated method stub
		CategoriasRepo.save(categoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return CategoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		// TODO Auto-generated method stub
		Optional<Categoria> optional = CategoriasRepo.findById(idCategoria);
		if(optional.isPresent()){
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		CategoriasRepo.deleteById(idCategoria);
		
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		return CategoriasRepo.findAll(page);
	}

}
