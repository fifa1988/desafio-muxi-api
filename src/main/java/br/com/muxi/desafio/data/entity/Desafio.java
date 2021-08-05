package br.com.muxi.desafio.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "desafio")
public class Desafio implements java.io.Serializable {

	private static final long serialVersionUID = 6325913484572392807L;
	
	@Id
	@Column(name="logic")
	@Min(Integer.MIN_VALUE)
	@Max(Integer.MAX_VALUE)
	private Integer logic;
	@Column(name="version") @NotNull(message = "Campo version é obrigatório")  @NotEmpty(message = "Informe um valor")
	private String version;
	@Column(name="serial")  @NotNull(message = "Campo serial é obrigatório") @NotEmpty(message = "Informe um valor")
	private String serial;
	@Column(name="model")  @NotNull(message = "Campo model é obrigatório") @NotEmpty(message = "Informe um valor")
	private String model;
	@Column(name="sam")
	@Min(0)
	private Integer sam;
	@Column(name="ptid")
	private String ptid;
	@Column(name="plat")
	private Integer plat;
	@Column(name="mxr")
	private Integer mxr;
	@Column(name="mxf")
	private Integer mxf;
	@Column(name="verfm")
	private String verfm;

}
