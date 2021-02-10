package br.com.indracompany.gestaoaluno.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Aluno {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	private String email;
	private String nota;
}
