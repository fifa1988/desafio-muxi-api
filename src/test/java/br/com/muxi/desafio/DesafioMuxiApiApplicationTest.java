package br.com.muxi.desafio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

class DesafioMuxiApiApplicationTest {
	
	@Mock
	ApplicationContext application;
	
	@Test
	void contextLoads() throws Exception {
		DesafioMuxiApiApplication desafioMuxiApiApplicationMock = Mockito.mock(DesafioMuxiApiApplication.class);
		Assertions.assertNotNull(desafioMuxiApiApplicationMock);
		desafioMuxiApiApplicationMock.setApplicationContext(application);
		desafioMuxiApiApplicationMock.init();
		desafioMuxiApiApplicationMock.configure(new SpringApplicationBuilder(DesafioMuxiApiApplication.class));
	}
}
