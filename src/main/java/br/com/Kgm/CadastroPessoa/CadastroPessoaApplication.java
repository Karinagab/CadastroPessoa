package br.com.Kgm.CadastroPessoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.Kgm.CadastroPessoa.model.Pessoa;


@SpringBootApplication
public class CadastroPessoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroPessoaApplication.class, args);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setNome("Karina");
		pessoa.setEndereço("rua");
		pessoa.setCep("123");
		pessoa.setCidade("sao paulo");
		pessoa.setUf("sp");
		
		System.out.println(pessoa.toString());
	}

}
