package com.alura.bytebank.dto;

import java.util.ArrayList;
import java.util.List;

import com.alura.bytebank.model.Conta;

import lombok.Data;
@Data
public class MetricaDTO {

	private Long totalContas;
	private Long totalImpostoRenda = 0L;
	private List<Conta> contasImpostoRenda;
	
	public List<Conta> getContasImpostoRenda() {
		if(contasImpostoRenda == null) {
			contasImpostoRenda = new ArrayList<Conta>();
		}
		return contasImpostoRenda;
	}
}
