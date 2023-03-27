package com.dawes.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ventas")
public class VentaVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idventa;
	private String dni;
	private int numeroentradas;
	private LocalDate fechaventa;
	private LocalTime horaventa;

	@ManyToOne
	@JoinColumn(name = "idconcierto", foreignKey = @ForeignKey(name = "FK_idconcierto"))
	private ConciertoVO concierto;

	public VentaVO(String dni, int numeroentradas, LocalDate fechaventa, LocalTime horaventa, ConciertoVO concierto) {
		super();
		this.dni = dni;
		this.numeroentradas = numeroentradas;
		this.fechaventa = fechaventa;
		this.horaventa = horaventa;
		this.concierto = concierto;
	}

}
