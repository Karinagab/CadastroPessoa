package br.com.Kgm.CadastroPessoa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Kgm.CadastroPessoa.model.Cadastro;
import br.com.Kgm.CadastroPessoa.model.Pessoa;
import br.com.Kgm.CadastroPessoa.repository.CadastroRepository;
import br.com.Kgm.CadastroPessoa.repository.PessoaRepository;
import br.com.Kgm.CadastroPessoa.service.interfaces.CadastroServiceInterface;
@Service
public class CadastroService implements CadastroServiceInterface{
	
	@Autowired
	private CadastroRepository cadastroRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public Cadastro save(Cadastro cadastro) {
		
		if(cadastro.getPessoa().getId() != null) {
			Optional<Pessoa> findPessoa = pessoaRepository.
					findById(cadastro.getPessoa().getId());			
			if(!findPessoa.isEmpty()) {
				cadastro.setPessoa(findPessoa.get());
				return cadastroRepository.save(cadastro);
			}else {
				System.out.println("Pessoa não encontrado pelo id: " + 
						cadastro.getPessoa().getId());
				return null;
			}
			
		}else {
			System.out.println("Pessoa não encontrada!");
			return null;
		}
	}


	@Override
	public Optional<Cadastro> finById(Long id) {
		return cadastroRepository.findById(id);
	}

	@Override
	public List<Cadastro> findAll() {
		return cadastroRepository.findAll();
	}

	@Override
	public Cadastro update(Cadastro cadastro) {
		Optional<Cadastro> findCadastro = cadastroRepository.findById(cadastro.getId());
		
		if(findCadastro.isPresent()) {
			
			
			Cadastro updCadastro = findCadastro.get();
			updCadastro.setContato(cadastro.getContato());
			return cadastroRepository.save(updCadastro); 
		}else {
			
			return save(cadastro);
		}
		
	}	

	@Override
	public void delete(Long id) {
		cadastroRepository.deleteById(id);
		
	}

}
