package br.com.Kgm.CadastroPessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Kgm.CadastroPessoa.model.Cadastro;

public interface CadastroRepository extends JpaRepository<Cadastro, Long>{

}
