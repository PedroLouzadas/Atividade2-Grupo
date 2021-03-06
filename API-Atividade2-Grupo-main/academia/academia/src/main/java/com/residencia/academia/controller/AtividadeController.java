package com.residencia.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.academia.entity.Atividade;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.AtividadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/atividade")
@Tag(name= "Atividade", description = "endpoints")
public class AtividadeController {

	@Autowired 
	private AtividadeService atividadeService;
	
	@GetMapping
	@Operation(summary = "Listar todas as atividades")
	public ResponseEntity<List<Atividade>> findAllAtividade() {
		List<Atividade> atividade = atividadeService.findAllAtividade();
		if (null == atividade)
			throw new NoSuchElementFoundException("Nenhuma atividade encontrada");
		else
		    return new ResponseEntity<>(atividadeService.findAllAtividade(), HttpStatus.OK);

	}
	@PostMapping
	@Operation(summary = "Adicionar nova atividade")
	public ResponseEntity<Atividade> saveAtividade(@RequestBody Atividade atividade) {
		return new ResponseEntity<>(atividadeService.saveAtividade(atividade), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Listar atividade pelo ID")
	public ResponseEntity<Atividade> findAtividadeById(@PathVariable Integer id) {
		Atividade atividade = atividadeService.findAtividadeById(id);
		if (null == atividade)
			throw new NoSuchElementFoundException("N??o foi encontrada Atividade com o id " + id);
		else
			return new ResponseEntity<>(atividadeService.findAtividadeById(id), HttpStatus.OK);
	} 

	@PutMapping
	@Operation(summary = "Editar atividade existente")
	public ResponseEntity<Atividade> updateAtividade(@RequestBody Atividade atividade) {
		return new ResponseEntity<>(atividadeService.updateAtividade(atividade), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir atividade")
	public ResponseEntity<String> deleteAtividade(@PathVariable Integer id) {
		Atividade atividade = atividadeService.findAtividadeById(id);
		if (null == atividade)
			throw new NoSuchElementFoundException("N??o foi possivel deletar a Atividade com o id " + id);
		else
			atividadeService.deleteAtividade(id);
			return new ResponseEntity<>("", HttpStatus.OK);
	
	}
 
	
}
