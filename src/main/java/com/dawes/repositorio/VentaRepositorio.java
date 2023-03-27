package com.dawes.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.VentaVO;

@Repository
public interface VentaRepositorio extends CrudRepository<VentaVO, Integer> {
	Optional<VentaVO> findByDni(String dni);

}
