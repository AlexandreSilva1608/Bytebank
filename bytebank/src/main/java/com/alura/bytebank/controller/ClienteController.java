package com.alura.bytebank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.bytebank.dto.IntegracaoDTO;
import com.alura.bytebank.model.Cliente;
import com.alura.bytebank.service.ClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	
	@GetMapping
	public List<Cliente> buscaCliente() {
		return clienteService.buscaCliente();
	}

	@GetMapping("/{id}")
	public Optional<Cliente> buscaClientePorId(@PathVariable("id") Long idCliente) {
		return clienteService.buscaClientePorId(idCliente);
	}

	@PostMapping
	public ResponseEntity<Cliente> adicionaCliente(@RequestBody Cliente cliente) {
		clienteService.adicionaCliente(cliente);
		Optional<Cliente> clienteCriado = clienteService.buscaClientePorId(cliente.getId());
		if(clienteCriado.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> inativaCliente(@PathVariable("id") Long idCliente) {
		boolean inativaCliente = clienteService.inativaCliente(idCliente);
		Optional<Cliente> cliente = clienteService.buscaClientePorId(idCliente);
		if (inativaCliente) {
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> atualizaCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		boolean atualizaCliente = clienteService.atualizaCliente(id, cliente);
		Optional<Cliente> findClienteById = buscaClientePorId(id);
		if (atualizaCliente) {
			return ResponseEntity.status(HttpStatus.CREATED).body(findClienteById);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}



	@PostMapping("/integracao")
	public ResponseEntity<Object> salvaIntegracao(@RequestBody IntegracaoDTO integracaoDTO) {
		 Cliente adicionaClienteIntegracaoDTO = clienteService.adicionaClienteIntegracaoDTO(integracaoDTO);
		 Optional<Cliente> buscaClientePorId = buscaClientePorId(adicionaClienteIntegracaoDTO.getId());
		 if(buscaClientePorId.isPresent()) {
			 return ResponseEntity.status(HttpStatus.CREATED).body(buscaClientePorId);
		 }
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

}
