package com.alura.bytebank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.bytebank.dto.MetricaDTO;
import com.alura.bytebank.model.Conta;
import com.alura.bytebank.service.ContaService;

@RestController
@RequestMapping("conta")
public class ContaController {

	@Autowired
	private ContaService contaService;
	

	@GetMapping
	public ResponseEntity<List<Conta>> buscaConta() {
		List<Conta> contas = contaService.buscaConta();
		if (contas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(contas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Conta>> buscaContaPorId(@PathVariable Long id) {
		Optional<Conta> contas = contaService.buscaContaPorId(id);
		if (contas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(contas);
	}
	
	@PostMapping("/abreConta")
	public ResponseEntity<?> abreConta(@RequestBody Conta conta) {
		Conta contaAberta = contaService.abreConta(conta);
		Optional<Conta> existeConta = contaService.buscaContaPorId(contaAberta.getId());
		if (existeConta.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping("/deposita/{id}/{valor}")
	public ResponseEntity<Double> deposita(@PathVariable Long id, @PathVariable double valor, @RequestBody Conta conta) {
		boolean depositaConta = contaService.deposita(valor, id);
		if (depositaConta) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping("/saca/{id}/{valor}")
	public ResponseEntity<Conta> saca(@PathVariable Long id, @PathVariable double valor, @RequestBody Conta conta) {
		boolean sacaConta = contaService.saca(id, valor);
		if (sacaConta) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@GetMapping("/mostraSaldo/{id}")
	public ResponseEntity<Double> mostraSaldo(@PathVariable Long id) {
		double saldo = contaService.mostraSaldo(id);
		return ResponseEntity.status(HttpStatus.OK).body(saldo);
	}

	@PostMapping("transfere")
	public ResponseEntity<?> tranfere(Long idOrigem, Long idDestino, double valorTransferencia) {
		boolean tranfere = contaService.tranfere(idOrigem, idDestino, valorTransferencia);
		if (tranfere) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@GetMapping("/totalContas")
	public String totalContas() {
		return contaService.totalContas();
	}

	@GetMapping("/metrica")
	public MetricaDTO totalContasImpostoRenda() {
		return contaService.totalContasImpostoRenda();
	}

}
