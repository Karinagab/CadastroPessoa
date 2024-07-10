package br.com.Kgm.CadastroPessoa.resource;

import java.util.Optional;

import org.hibernate.mapping.List;
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

import br.com.Kgm.CadastroPessoa.model.Cadastro;
import br.com.Kgm.CadastroPessoa.service.CadastroService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/cadastro")
public class CadastroResource {
	@Autowired
	private CadastroService cadastroService;
	@Operation(summary = "Adiciona um novo contato a uma pessoa")
	@PostMapping
	public ResponseEntity<Cadastro> save(@RequestBody Cadastro cadastro){
		Cadastro newCadastro = cadastroService.save(cadastro);
		if(newCadastro == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(newCadastro);
	}
	@Operation(summary = "Retorna o contato de uma pessoa")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cadastro>> findById(@PathVariable Long id){
		Optional<Cadastro> findCadastro = cadastroService.finById(id);
		if(findCadastro == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(findCadastro);
	}
	@Operation(summary = "Lista todos os contados de uma pessoa")
	@GetMapping
	public ResponseEntity<List> findAll(){
		List cadastros = (List) cadastroService.findAll();
		if(cadastros == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(cadastros);
	}
	@Operation(summary = "Atualiza o contato de uma pessoa")
	@PutMapping
	public ResponseEntity<Cadastro> update(@RequestBody Cadastro cadastro){
		Cadastro updCadastro = cadastroService.update(cadastro);
		if(updCadastro == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updCadastro);
	}
	@Operation(summary = "Deleta o contato de uma pessoa")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		cadastroService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

