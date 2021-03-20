package org.generation.blogPedrita.controller;

import java.util.List;

import org.generation.blogPedrita.model.Postagem;
import org.generation.blogPedrita.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/Postagens")
@CrossOrigin("*")
public class PostagemController {
    
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping("/buscarTodos")
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem>GetById(@PathVariable long Id) {
		return repository.findById(Id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
		
	}
	@GetMapping("/Titulo/{Titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String Titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(Titulo));
	}
	@PostMapping("/criarPostagem")
	public ResponseEntity<Postagem>Post(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
		
	}
	@PutMapping
	public ResponseEntity<Postagem>Put(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
		
	}
	@DeleteMapping("/deletarPostagem/{Id}")
	public void delete(@PathVariable long Id) {
		repository.deleteById(Id);
	}
}

    
