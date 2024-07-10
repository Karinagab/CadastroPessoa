package br.com.Kgm.CadastroPessoa.resource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

import br.com.Kgm.CadastroPessoa.dto.PessoaDTO;
import br.com.Kgm.CadastroPessoa.model.Pessoa;
import br.com.Kgm.CadastroPessoa.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController 
@RequestMapping("/api/pessoa") //http://localhost:8088/api/pessoa
public class PessoaResource {

	@Autowired
	PessoaService pessoaService;
	@Operation(summary = "Cria o registro de uma pessoa")
	@GetMapping("criar") //http://localhost:8088/api/pessoas/pessoa
	public ResponseEntity<Pessoa> save(){
		Pessoa pessoa = new Pessoa ();
		pessoa.setId(1L);
		pessoa.setNome("karina");
		pessoa.setEndereço("rua");
		pessoa.setCep("1234");
		pessoa.setCidade("sao paulo");
		pessoa.setUf("sp");
		
		Pessoa pessoaResposta = pessoaService.save(pessoa);
		if(pessoaResposta == null) {
			ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa);
	}
	@Operation(summary = "Busca todas as pessoas registradas")
	@GetMapping //http://localhost:8088/api/pessoas
	public ResponseEntity<List<Pessoa>> findAllPessoas(){
		List<Pessoa> pessoas = pessoaService.findAll();
		if(pessoas == null) {
		return ResponseEntity.notFound().build();	
		}
		if(pessoas.size() == 0) {
		return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoas);			
	}
	@Operation(summary = "Busca a pessoa registrada atrávez do Id")	
	@GetMapping("/{id}") //http:localhost:8088/api/pessoa/10
		public ResponseEntity<Optional<Pessoa>> findById(@PathVariable Long id){
			Optional<Pessoa> pessoa = pessoaService.findById(id);
			if(pessoa.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
		return ResponseEntity.ok(pessoa);
	}
	@Operation(summary = "Salva os registros de pessoas")
	@PostMapping //http://localhost:8088/api/pessoas
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		Pessoa newPessoa = pessoaService.save(pessoa);
		if(newPessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(newPessoa);
		}
	@Operation(summary = "Atualiza o registro de uma pessoa")
	@PutMapping
	public ResponseEntity<Pessoa> update(Pessoa pessoa){
		Pessoa updPessoa = pessoaService.update(pessoa);
		if(updPessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updPessoa);
		}
	@Operation(summary = "Deleta o registro de uma pessoa por Id")
	@DeleteMapping("/{id}")//http:localhost:8088/api/pessoa/10
	public ResponseEntity<?> delete(Long id){
		pessoaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@Operation(summary = "Pesquisa de uma lista contendo os dados de Pessoa e seu contato")
	@GetMapping("/pessoaAndContato") //http://localhost:8088/api/pessoas/pessoaAndContato
	public ResponseEntity<Object> findPessoaAndContato(){
		List<PessoaDTO> pessoaDTOs = pessoaService.findPessoaAndContato();  
		Object pessoasDTos;
		if(pessoasDTos == null) {
		return ResponseEntity.notFound().build();	
		}
		Object pessoasDTOs;
		if(pessoasDTOs).size() == 0) {
		return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoasDTOs);	
	}
}
