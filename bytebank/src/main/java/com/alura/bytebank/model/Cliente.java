package com.alura.bytebank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;
@Data
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	private String nome;
	private String cpf;
	private String profissao;
	private Boolean ativo = true;
	
	public Cliente(String nome, String cpf, String profissao) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.profissao = profissao;
	}
	
	public Cliente() {
		
	}
	
}
