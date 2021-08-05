package br.com.muxi.desafio.service;

import javax.validation.Valid;

import br.com.muxi.desafio.data.dto.DesafioDTO;

public interface DesafioControllerService {

	DesafioDTO save(String values) throws Exception;
	
	DesafioDTO get(Integer logic) throws Exception;

	DesafioDTO update(@Valid Integer logic, @Valid DesafioDTO desafioDTO) throws Exception;
	
}
