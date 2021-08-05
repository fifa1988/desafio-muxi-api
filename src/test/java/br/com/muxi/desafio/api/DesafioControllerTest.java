package br.com.muxi.desafio.api;

import static org.mockito.Mockito.withSettings;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.muxi.desafio.api.v1.DesafioController;
import br.com.muxi.desafio.data.dto.DesafioDTO;
import br.com.muxi.desafio.data.entity.Desafio;
import br.com.muxi.desafio.data.repository.DesafioRepository;
import br.com.muxi.desafio.exception.APIException;
import br.com.muxi.desafio.service.impl.DesafioControllerServiceImpl;

class DesafioControllerTest {
	private String inputs = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";
	
	@Mock
	private DesafioControllerServiceImpl desafioControllerService;
	
	@Mock
	private DesafioController controller;
	
	@Mock
	DesafioRepository desafioRepository;
	
	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.initMocks(this);
		desafioControllerService = Mockito.mock(DesafioControllerServiceImpl.class, withSettings().
				useConstructor(desafioRepository));
		
		controller = Mockito.mock(DesafioController.class, withSettings().
				useConstructor(desafioControllerService));
	}
	
	@Test
	void contextLoads() throws Exception {
		Assertions.assertNotNull(controller);
	}
	
	@Test
	void testInsertHappyWay() throws Exception {
		Desafio desafio = getDesafio();
		
		Mockito.when(desafioRepository.findByLogic(desafio.getLogic())).thenReturn(findByLogicSemRegistro());
		Mockito.when(desafioRepository.saveAndFlush(desafio)).thenReturn(saveAndFlush());
		Mockito.when(desafioControllerService.save(inputs)).thenReturn(save());
		
		Assertions.assertEquals(false, desafioRepository.findByLogic(desafio.getLogic()).isPresent());
		
		DesafioDTO dto = desafioControllerService.save(inputs);
		
		Assertions.assertEquals(dto.getLogic(), desafio.getLogic());
		Assertions.assertEquals(dto.getSerial(), desafio.getSerial());
		Assertions.assertEquals(dto.getSam(), desafio.getSam());
		Assertions.assertEquals(dto.getPtid(), desafio.getPtid());
		Assertions.assertEquals(dto.getPlat(), desafio.getPlat());
		Assertions.assertEquals(dto.getVersion(), desafio.getVersion());
		Assertions.assertEquals(dto.getMxr(), desafio.getMxr());
		Assertions.assertEquals(dto.getMxf(), desafio.getMxf());
		Assertions.assertEquals(dto.getVerfm(), desafio.getVerfm());
		
		ResponseEntity<DesafioDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.CREATED);
		Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}
	
	@Test
	void testInsertDataAlreadyOnDB() throws Exception {
		Mockito.when(desafioRepository.findByLogic(44332211)).thenReturn(findByLogic());
		try {
			if(desafioRepository.findByLogic(44332211).isPresent())
				throw new APIException("Registro já cadastrado", HttpStatus.BAD_REQUEST);
			Assertions.fail("Registro não encontrado.");
		} catch (APIException e) {
			Assertions.assertEquals("Registro já cadastrado", e.getMessage());
		}
	}
	
	@Test
	void testUpdataHappyWay() throws Exception {
		DesafioDTO dto = getDesafioDTO();
		Desafio desafio = DesafioRepository.DTO_TO_JPA.apply(dto);
		
		Mockito.when(desafioRepository.findByLogic(44332211)).thenReturn(findByLogic());
		Mockito.when(desafioControllerService.update(44332211, dto)).thenReturn(update());
		
		Assertions.assertEquals(true, desafioRepository.findByLogic(desafio.getLogic()).isPresent());
		
		dto = desafioControllerService.update(desafio.getLogic(), dto);
		
		Assertions.assertEquals(dto.getLogic(), desafio.getLogic());
		Assertions.assertEquals(dto.getSerial(), desafio.getSerial());
		Assertions.assertEquals(dto.getSam(), desafio.getSam());
		Assertions.assertEquals(dto.getPtid(), desafio.getPtid());
		Assertions.assertEquals(dto.getPlat(), desafio.getPlat());
		Assertions.assertEquals(dto.getVersion(), desafio.getVersion());
		Assertions.assertEquals(dto.getMxr(), desafio.getMxr());
		Assertions.assertEquals(dto.getMxf(), desafio.getMxf());
		Assertions.assertEquals(dto.getVerfm(), desafio.getVerfm());
		
		ResponseEntity<DesafioDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	void testUpdateDataAlreadyOnDB() throws Exception {
		Mockito.when(desafioRepository.findByLogic(44332211)).thenReturn(findByLogicSemRegistro());
		try {
			if(!desafioRepository.findByLogic(44332211).isPresent())
				throw new APIException("Registro não encontrado", HttpStatus.BAD_REQUEST);
			Assertions.fail("Registro encontrado.");
		} catch (APIException e) {
			Assertions.assertEquals("Registro não encontrado", e.getMessage());
		}
	}
	
	@Test
	void testGetHappyWay() throws Exception {
		Mockito.when(desafioRepository.findByLogic(44332211)).thenReturn(findByLogic());
		
		Desafio desafio = desafioRepository.findByLogic(44332211).orElseThrow(() -> new APIException("Nenhum registro encontrado", HttpStatus.BAD_REQUEST));
		DesafioDTO dto = DesafioRepository.JPA_TO_DTO.apply(desafio);
		
		Assertions.assertEquals(dto.getLogic(), desafio.getLogic());
		Assertions.assertEquals(dto.getSerial(), desafio.getSerial());
		Assertions.assertEquals(dto.getSam(), desafio.getSam());
		Assertions.assertEquals(dto.getPtid(), desafio.getPtid());
		Assertions.assertEquals(dto.getPlat(), desafio.getPlat());
		Assertions.assertEquals(dto.getVersion(), desafio.getVersion());
		Assertions.assertEquals(dto.getMxr(), desafio.getMxr());
		Assertions.assertEquals(dto.getMxf(), desafio.getMxf());
		Assertions.assertEquals(dto.getVerfm(), desafio.getVerfm());
	}
	
	@Test
	void testGetWithoutDataOnDB() throws Exception {
		Mockito.when(desafioRepository.findByLogic(44332211)).thenReturn(findByLogicSemRegistro());
		try {
			if(!desafioRepository.findByLogic(44332211).isPresent())
				throw new APIException("Nenhum registro encontrado", HttpStatus.BAD_REQUEST);
			Assertions.fail("Registro encontrado.");
		} catch (APIException e) {
			Assertions.assertEquals("Nenhum registro encontrado", e.getMessage());
		}
	}
	
	private Desafio saveAndFlush() {
		return getDesafio();
	}

	private DesafioDTO save() throws Exception {
		return getDesafioDTO();
	}
	
	private DesafioDTO update() throws Exception {
		return getDesafioDTO();
	}

	private DesafioDTO getDesafioDTO() {
		return DesafioDTO.builder()
			.logic(44332211)
			.serial("123")
			.model("PWWIN")
			.sam(0)
			.ptid("F04A2E4088B")
			.plat(4)
			.version("8.00b3")
			.mxr(0)
			.mxf(16777216)
			.verfm("PWWIN")
			.build();
	}
	
	private Desafio getDesafio() {
		return Desafio.builder()
			.logic(44332211)
			.serial("123")
			.model("PWWIN")
			.sam(0)
			.ptid("F04A2E4088B")
			.plat(4)
			.version("8.00b3")
			.mxr(0)
			.mxf(16777216)
			.verfm("PWWIN")
			.build();
	}
	
	private Optional<Desafio> findByLogic() throws APIException {
		return Optional.of(getDesafio());
	}
	
	private Optional<Desafio> findByLogicSemRegistro() {
		return Optional.ofNullable(null);
	}
}
