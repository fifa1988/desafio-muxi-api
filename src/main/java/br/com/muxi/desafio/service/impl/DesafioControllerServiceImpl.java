package br.com.muxi.desafio.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.muxi.desafio.data.dto.DesafioDTO;
import br.com.muxi.desafio.data.entity.Desafio;
import br.com.muxi.desafio.data.repository.DesafioRepository;
import br.com.muxi.desafio.exception.APIException;
import br.com.muxi.desafio.service.DesafioControllerService;
import br.com.muxi.desafio.utils.Util;

@Service
@Transactional
public class DesafioControllerServiceImpl implements DesafioControllerService {

	private DesafioRepository desafioRepository;
	
	@Autowired
	public DesafioControllerServiceImpl(DesafioRepository desafioRepository){
		this.desafioRepository = desafioRepository;
	}
	
	@Override
	public DesafioDTO save(String values) throws Exception {
		
		Desafio desafio = validations(values);
		
		if(desafioRepository.findByLogic(desafio.getLogic()).isPresent()) {
			throw new APIException("Registro já cadastrado", HttpStatus.BAD_REQUEST);
		}
		
		desafio = desafioRepository.saveAndFlush(desafio);

		return DesafioRepository.JPA_TO_DTO.apply(desafio);
	}
	
	@Override
	public DesafioDTO get(Integer logic) throws Exception {
		Desafio jpa = desafioRepository.findByLogic(logic).orElseThrow(() -> new APIException("Nenhum registro encontrado", HttpStatus.BAD_REQUEST));
		return DesafioRepository.JPA_TO_DTO.apply(jpa);
	}

	@Override
	public DesafioDTO update(@Valid Integer logic, DesafioDTO dto) throws Exception {
		Desafio jpa = desafioRepository.findByLogic(logic).orElseThrow(() -> new APIException("Nenhum registro encontrado", HttpStatus.BAD_REQUEST));
		jpa.setLogic(logic);
		jpa.setSerial(dto.getSerial());
		jpa.setModel(dto.getModel());
		jpa.setSam(dto.getSam());
		jpa.setPtid(dto.getPtid());
		jpa.setPlat(dto.getPlat());
		jpa.setVersion(dto.getVersion());
		jpa.setMxr(dto.getMxr());
		jpa.setMxf(dto.getMxf());
		jpa.setVerfm(dto.getVerfm());
		
		return DesafioRepository.JPA_TO_DTO.apply(jpa);
	}

	private Desafio validations(String values) throws MissingServletRequestParameterException, MethodArgumentTypeMismatchException {
		String[] arrayValues = values.split(";");
		
		if(arrayValues[0].equals("")) {
			throw new MissingServletRequestParameterException("logic", Integer.class.toString());
		}
		
		validIntegerValue(arrayValues[0], "logic");
		validIntegerValue(arrayValues[3], "sam");
		validIntegerValue(arrayValues[5], "plat");
		validIntegerValue(arrayValues[7], "mxr");
		validIntegerValue(arrayValues[8], "mxf");
		
		return Desafio.builder()
				.logic((!arrayValues[0].equals("")) ? Integer.valueOf(arrayValues[0]) : null)
				.serial(arrayValues[1])
				.model(arrayValues[2])
				.sam(Integer.valueOf(arrayValues[3]))
				.ptid(arrayValues[4])
				.plat(Integer.valueOf(arrayValues[5]))
				.version(arrayValues[6])
				.mxr(Integer.valueOf(arrayValues[7]))
				.mxf(Integer.valueOf(arrayValues[8]))
				.verfm(arrayValues[9]).build();
	}
	
	private void validIntegerValue(String value, String nameValue) {
		if(!Util.isStringInteger(value) && !value.equals("")) {
			throw new MethodArgumentTypeMismatchException(value, Integer.class, nameValue, null, null);
		}
	}
}
