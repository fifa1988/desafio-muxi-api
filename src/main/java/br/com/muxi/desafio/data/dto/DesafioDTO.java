package br.com.muxi.desafio.data.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesafioDTO{

	private Integer logic;
	@NotNull(message = "Campo serial é obrigatório") @NotEmpty(message = "Informe um valor")
	private String serial;
	@NotNull(message = "Campo model é obrigatório") @NotEmpty(message = "Informe um valor")
	private String model;
	@Min(0)
	private Integer sam;
	private String ptid;
	private Integer plat;
	@NotNull(message = "Campo version é obrigatório")  @NotEmpty(message = "Informe um valor")
	private String version;
	private Integer mxr;
	private Integer mxf;
	@JsonProperty("VERFM")
	private String verfm;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
