package br.com.Kgm.CadastroPessoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.Kgm.CadastroPessoa.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	@Query(value = "select tp.id,tp.nome,tp.endereço,tp.cep,"
			+ "tp.cidade,tp.uf from tb_pessoa tp inner join cadastro te on tp.id = te.pessoa_id",
			nativeQuery = true)
	List<Object[]> PessoaAndContato();
	
	

}
