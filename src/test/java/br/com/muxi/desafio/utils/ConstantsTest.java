package br.com.muxi.desafio.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstantsTest {
	@Test
	void deveriaGerarObjeto() {
		Assertions.assertEquals("Ocorreu um erro interno no servidor.", Constants.INTERNAL_SERVER_ERROR_MESSAGE);
		Assertions.assertEquals("%s - %s", Constants.STATUS_CODE_FORMAT);
		Assertions.assertEquals("Falha ao converter parâmetros recebidos.", Constants.PARAMETERS_INVALID_ERROR_MSG);
		Assertions.assertEquals("O parâmetro '%s' é incompatível com o valor '%s'. O tipo do campo aceito é %s.", Constants.PARAMETER_MISMATCH_ERROR_MSG);
		Assertions.assertEquals("Campo %s: %s.", Constants.PARAMETER_ISNOTPRESENT_ERROR_MSG);
	    Assertions.assertEquals("dd/MM/yyyy HH:mm:ss.SSS", Constants.DATE_TIME_UNIX_PATTERN);
	}
}
