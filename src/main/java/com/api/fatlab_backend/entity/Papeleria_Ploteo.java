package com.api.fatlab_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "papeleria_ploteo")
public class Papeleria_Ploteo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int papeleria_ploteo_id;
	private String tipo_tinta;

	@OneToOne(mappedBy = "papeleria_ploteo")
	@JsonIgnore
	private Maquina maquina;
}
