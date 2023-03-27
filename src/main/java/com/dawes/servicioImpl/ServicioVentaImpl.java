package com.dawes.servicioImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.VentaVO;
import com.dawes.repositorio.ConciertoRepositorio;
import com.dawes.repositorio.VentaRepositorio;
import com.dawes.servicio.ServicioVenta;

@Service
public class ServicioVentaImpl implements ServicioVenta {
	@Autowired
	VentaRepositorio vr;
	@Autowired
	ConciertoRepositorio cr;

	@Override
	public Optional<VentaVO> findByDni(String dni) {
		return vr.findByDni(dni);
	}

	@Override
	public <S extends VentaVO> S save(S entity) {
		//Recuperamos el concierto para el se hace la venta
		ConciertoVO c = entity.getConcierto();
		//le asignamos la diferencia entre las plazas del concierto y el número de entradas vendidas
		c.setPlazas(entity.getConcierto().getPlazas() - entity.getNumeroentradas());
		//A través del repositorio del concierto, actualizamos la tabla con los nuevos datos
		cr.save(c);
		return vr.save(entity);
	}

	@Override
	public <S extends VentaVO> Iterable<S> saveAll(Iterable<S> entities) {
		return vr.saveAll(entities);
	}

	@Override
	public Optional<VentaVO> findById(Integer id) {
		return vr.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return vr.existsById(id);
	}

	@Override
	public Iterable<VentaVO> findAll() {
		return vr.findAll();
	}

	@Override
	public Iterable<VentaVO> findAllById(Iterable<Integer> ids) {
		return vr.findAllById(ids);
	}

	@Override
	public long count() {
		return vr.count();
	}

	@Override
	public void deleteById(Integer id) {
		vr.deleteById(id);
	}

	@Override
	public void delete(VentaVO entity) {
		vr.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		vr.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends VentaVO> entities) {
		vr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		vr.deleteAll();
	}

}
