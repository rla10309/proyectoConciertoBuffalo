package com.dawes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.SalaVO;
import com.dawes.modelo.VentaVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioSala;
import com.dawes.servicio.ServicioVenta;

@SpringBootTest
class ApplicationTests {
	@Autowired
	ServicioSala ss;
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioVenta sv;

	// inserta una sala
	@Test
	public void test01() {
		assertNotNull(ss.save(new SalaVO("La Salvaje", "C/Martin, 2", 100, new ArrayList<ConciertoVO>())));
	}
	//inserta una sala que ya existe
	@Test
	public void test02() {
		assertNotNull(ss.save(new SalaVO("La Salvaje", "C/Asturias, 45", 100, new ArrayList<ConciertoVO>())));

	}

	// inserta un concierto
	@Test
	public void test03() {
		sc.save(new ConciertoVO("Coldplay", LocalDate.of(2023, 12, 15), LocalTime.of(20, 30), 65d, 60d, 100,
				ss.findByNombre("La Salvaje").get(), new ArrayList<VentaVO>()));
		assertNotNull(sc.save(new ConciertoVO("Oasis", LocalDate.of(2023, 8, 15), LocalTime.of(20, 30), 60d, 55d, 100,
				ss.findByNombre("La Salvaje").get(), new ArrayList<VentaVO>())));
	}
	
	//inserta un concierto en una fecha ocupada
	@Test
	public void test04() {
		assertNotNull(sc.save(new ConciertoVO("Oasis", LocalDate.of(2023, 12, 15), LocalTime.of(20, 30), 65d, 60d, 100,
				ss.findByNombre("La Salvaje").get(), new ArrayList<VentaVO>())));
	}

	// inserta una venta
	@Test
	public void test05() {
		sv.save(new VentaVO("111", 4, LocalDate.now(), LocalTime.now(), sc.findByGrupo("Oasis").get()));
		sv.save(new VentaVO("222", 4, LocalDate.now(), LocalTime.now(), sc.findByGrupo("Coldplay").get()));
		assertNotNull(sv.save(new VentaVO("333", 2, LocalDate.now(), LocalTime.now(), sc.findByGrupo("Oasis").get())));
	}

	// comprueba la actualizacion de las plazas disponbiles
	@Test
	public void test06() {
		assertEquals(94, sv.findByDni("111").get().getConcierto().getPlazas());
	}

	// busca las ventas de un concierto
	@Test
	public void test07() {
		ConciertoVO c = sc.findVentasByConciertoAndGrupo("Oasis").get();
		assertEquals(2, c.getVentas().size());
	}

	// busca los conciertos de una sala
	@Test
	public void test08() {
		SalaVO s = ss.findConciertosByNombre("La Salvaje").get();
		assertEquals(2, s.getConciertos().size());
	}

	// intenta borrar un concierto y sus ventas
	@Test
	public void test09() {
		ConciertoVO c = sc.findByGrupo("Coldplay").get();
		sc.delete(c);
		assertEquals("Coldplay", c.getGrupo());
	}
	//intenta borrar una sala 
	@Test
	public void test10() {
		SalaVO s = ss.findByNombre("La Salvaje").get();
		ss.delete(s);
		assertEquals(100, s.getAforo());
	}

}
