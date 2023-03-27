package com.dawes.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.ConciertoVO;

@Repository
public interface ConciertoRepositorio extends CrudRepository<ConciertoVO, Integer> {
	public Optional<ConciertoVO> findByGrupo(String grupo);
	@Query("select c from ConciertoVO c join fetch c.ventas where c.grupo = ?1")
	public Optional<ConciertoVO> findVentasByConciertoAndGrupo(String grupo);

}
