package com.alura.bytebank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double saldo;
	private int agencia;
	@OneToOne
	@JoinColumn(name = "titular", referencedColumnName = "id")
	public Cliente titular;
	private int numero;

	public Conta() {
		
	}
	
	public Conta(double saldo, int agencia, Cliente cliente, int numero) {
		this.saldo = saldo;
		this.agencia = agencia;
		this.titular = cliente;
		this.numero = numero;
	}
	
	@JsonIgnore
	public Long getIdTitular() {
		return titular.getId();
	}

}
