package br.com.muxi.desafio.api.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.muxi.desafio.data.dto.DesafioDTO;
import br.com.muxi.desafio.service.DesafioControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping(path = "v1/desafio", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Desafio")
@RestController
public class DesafioController {
	
	private DesafioControllerService desafioControllerService;
	
	@Autowired
	public DesafioController(DesafioControllerService desafioControllerService) {
		this.desafioControllerService = desafioControllerService;
	}

	@PostMapping(consumes = "text/html; charset=utf-8")
	@ApiOperation(value = "Incluir", notes = "Cria Desafio")
	public ResponseEntity<DesafioDTO> insert(@Valid @RequestBody String values) throws Exception {
		return new ResponseEntity<>(desafioControllerService.save(values), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{logic}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Alterar", notes = "Altera Desafio")
	public ResponseEntity<DesafioDTO> update(@Valid @PathVariable("logic") Integer logic, @Valid @RequestBody DesafioDTO desafioDTO) throws Exception {
		return new ResponseEntity<>(desafioControllerService.update(logic, desafioDTO), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{logic}")
	@ApiOperation(value = "Consultar", notes = "Consulta Desafio")
	public ResponseEntity<DesafioDTO> consulta(@Valid @PathVariable("logic") Integer logic) throws Exception {
		return new ResponseEntity<>(desafioControllerService.get(logic), HttpStatus.OK);
	}
	
}
