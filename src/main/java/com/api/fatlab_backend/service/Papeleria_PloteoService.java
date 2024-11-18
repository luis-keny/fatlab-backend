package com.api.fatlab_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.fatlab_backend.repository.Papeleria_PloteoRepository;

@Service
public class Papeleria_PloteoService {

	@Autowired
	private Papeleria_PloteoRepository papeleria_PloteoRepository;

	public void delete(int id) {
		papeleria_PloteoRepository.deleteById(id);
	}
}
