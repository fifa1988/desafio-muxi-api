package br.com.muxi.desafio.utils;

public class Constants {
	
	private Constants() {}
	
	// ### GENERAL
	public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Ocorreu um erro interno no servidor.";
	public static final String STATUS_CODE_FORMAT = "%s - %s";
	public static final String PARAMETERS_INVALID_ERROR_MSG = "Falha ao converter parâmetros recebidos.";
	public static final String PARAMETER_MISMATCH_ERROR_MSG = "O parâmetro '%s' é incompatível com o valor '%s'. O tipo do campo aceito é %s.";
	public static final String PARAMETER_ISNOTPRESENT_ERROR_MSG = "Campo %s: %s.";
    public static final String DATE_TIME_UNIX_PATTERN = "dd/MM/yyyy HH:mm:ss.SSS";
}
