package br.com.Kgm.CadastroPessoa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.sql.ast.tree.cte.CteObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Kgm.CadastroPessoa.dto.PessoaDTO;
import br.com.Kgm.CadastroPessoa.model.Pessoa;
import br.com.Kgm.CadastroPessoa.repository.PessoaRepository;

	@Service
	public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa save(Pessoa pessoa) {
		
		if(pessoa.getNome() == null) {
			System.out.println("Nome da pessoa está vazio");
			return null;
			
		}
		if(pessoa.getId() == null) {
		System.out.println("Nome da pessoa está vazio");
		return null;
		}
			
		return pessoaRepository.save(pessoa);
	}
	
	public List<Pessoa> findAll(){
		return pessoaRepository.findAll();
		
	}
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}
	
	public Pessoa update(Pessoa pessoa) {
		Optional<Pessoa> findPessoa = pessoaRepository.findById(pessoa.getId());	
		if(findPessoa.isPresent()) {
		Pessoa updPessoa = findPessoa.get(); 
				updPessoa.setEndereço(pessoa.getEndereço());
				updPessoa.setNome(pessoa.getNome());
				updPessoa.setCep(pessoa.getCep());
				updPessoa.setCidade(pessoa.getCidade());
				updPessoa.setUf(pessoa.getUf());
				return pessoaRepository.save(updPessoa);
	}
	
		return pessoaRepository.save(pessoa);
			
	
	}
	
		public void delete(Long id) {
		pessoaRepository.deleteById(id);
	}
	
	public List<PessoaDTO> findPessoaAndContato(){
		List<Object[]> listResult = pessoaRepository.PessoaAndContato();
		List<PessoaDTO> listPessoaDTO = new ArrayList<PessoaDTO>();
		
		for(Object[] obj:listResult) {
			PessoaDTO pDTO = returnBDPessoaDTO(obj);
			listPessoaDTO.add(pDTO);
		}
		
		return null;
	}
	
	private PessoaDTO returnBDPessoaDTO(Object[] resultado) {
		PessoaDTO pessoaDTO = new PessoaDTO();
		if(resultado != null) {
			pessoaDTO.setId(((Long)resultado[0]).longValue());
			pessoaDTO.setNome((String)resultado[1]);
			pessoaDTO.setEndereco((String)resultado[3]);
			pessoaDTO.setCep((String)resultado[4]);
			pessoaDTO.setCidade((String)resultado[5]);
			pessoaDTO.setUf((String)resultado[6]);
			pessoaDTO.setContato(((Integer)resultado[7]).intValue());
		}
		return pessoaDTO;
	}
	
	
	
 }