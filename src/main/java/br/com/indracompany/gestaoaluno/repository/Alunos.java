package br.com.indracompany.gestaoaluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.indracompany.gestaoaluno.model.Aluno;

public interface Alunos extends JpaRepository<Aluno, Long>{

}
