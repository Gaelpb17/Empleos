package net.itinajero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.itinajero.model.Categoria;

//Repositorio es una interfas q extiende una interfas de un repositorio con los principalees metodos de crud
//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
