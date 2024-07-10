package br.com.Kgm.CadastroPessoa.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.Kgm.CadastroPessoa.model.Cadastro;

public interface CadastroServiceInterface {

		Cadastro save(Cadastro cadastro);
		Optional<Cadastro> finById(Long id);
		List<Cadastro> findAll();
		Cadastro update(Cadastro cadastro);
		void delete(Long id);
}
