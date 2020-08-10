package com.alura.bytebank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.bytebank.dto.MetricaDTO;
import com.alura.bytebank.model.Conta;
import com.alura.bytebank.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	public List<Conta> buscaConta() {
		return contaRepository.findAll();
	}

	public Optional<Conta> buscaContaPorId(Long id) {
		return contaRepository.findById(id);
	}

	public boolean deposita(double valor, Long id) {
		Optional<Conta> dadosConta = contaRepository.findById(id);
		double saldoConta = dadosConta.get().getSaldo();
		if (valor > 0 && dadosConta.isPresent()) {
			saldoConta += valor;
			Conta contaDeposita = dadosConta.get();
			contaDeposita.setSaldo(saldoConta);
			contaRepository.save(contaDeposita);
			return true;
		}
		return false;
	}

	public boolean saca(Long id, double valor) {
		Optional<Conta> dadosConta = contaRepository.findById(id);
		double saldoConta = dadosConta.get().getSaldo();
		if (saldoConta >= valor) {
			saldoConta -= valor;
			Conta contaSaca = dadosConta.get();
			contaSaca.setSaldo(saldoConta);
			contaRepository.save(contaSaca);
			return true;
		}
		return false;
	}

	public boolean tranfere(Long idOrigem, Long idDestino, double valorTransferencia) {
		boolean saca = saca(idOrigem, valorTransferencia);
		if (saca) {
			deposita(valorTransferencia, idDestino);
			return true;
		}
		return false;
	}

	public double mostraSaldo(Long id) {
		Optional<Conta> contaSaldo = contaRepository.findById(id);
		return contaSaldo.get().getSaldo();
	}

	public String totalContas() {
		return ("Total de contas: " + contaRepository.count());
	}

	public MetricaDTO totalContasImpostoRenda() {
		MetricaDTO metricaDTO = new MetricaDTO();
		long countConta = contaRepository.count();
		metricaDTO.setTotalContas(countConta);
		
		List<Conta> conta = contaRepository.findAll();
		for (int i = 0; i < conta.size(); i++) {
			Conta contaSaldo = conta.get(i);
			if (contaSaldo.getSaldo() >= 5000) {
				metricaDTO.getContasImpostoRenda().add(conta.get(i));
				metricaDTO.setTotalImpostoRenda(metricaDTO.getTotalImpostoRenda() + 1);
			}
		}
		return metricaDTO;
	}
	
	public Conta abreConta(Conta conta) {
		Conta contaSalva = new Conta(conta.getSaldo(), conta.getAgencia(), conta.getTitular(), conta.getNumero());
		return contaRepository.save(contaSalva);
	}
	
}
