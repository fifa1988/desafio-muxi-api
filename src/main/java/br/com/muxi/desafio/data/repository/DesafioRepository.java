package br.com.muxi.desafio.data.repository;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.muxi.desafio.data.dto.DesafioDTO;
import br.com.muxi.desafio.data.entity.Desafio;

@Repository
public interface DesafioRepository extends JpaRepository<Desafio, Integer> {

	public static final Function<DesafioDTO, Desafio> DTO_TO_JPA = (dto -> {
		Desafio jpa = new Desafio();		
		BeanUtils.copyProperties(dto, jpa);
		return jpa;
	});
	
	public static final Function<Desafio, DesafioDTO> JPA_TO_DTO = (jpa -> {
		DesafioDTO dto = DesafioDTO.builder().build();
		BeanUtils.copyProperties(jpa, dto);
		return dto;
	});
	
	Optional<Desafio> findByLogic(Integer logic);

}
