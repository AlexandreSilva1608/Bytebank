package com.alura.bytebank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.bytebank.dto.IntegracaoDTO;
import com.alura.bytebank.model.Cliente;
import com.alura.bytebank.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> buscaCliente() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> buscaClientePorId(Long id) {
		return clienteRepository.findById(id);
	}

	public void adicionaCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	public Cliente adicionaClienteIntegracaoDTO(IntegracaoDTO integracaoDTO) {
		IntegracaoDTO clienteIntegracaoDTO = integracaoDTO;
		Cliente cliente = new Cliente(clienteIntegracaoDTO.getNome(), clienteIntegracaoDTO.getCpf(),
				clienteIntegracaoDTO.getOccupation());
		return clienteRepository.save(cliente);
	}

	public boolean inativaCliente(Long idCliente) {
		Optional<Cliente> clienteOpt = buscaClientePorId(idCliente);
		if (clienteOpt.isPresent()) {
			Cliente cliente = clienteOpt.get();
			if (cliente.getAtivo()) {
				cliente.setAtivo(false);
				clienteRepository.save(cliente);
				return true;
			}
		}
		return false;
	}

	public boolean atualizaCliente(Long id, Cliente cliente) {
		Optional<Cliente> clienteOpt = buscaClientePorId(id);
		if (clienteOpt.isPresent()) {
			clienteOpt.get().setNome(cliente.getNome());
			clienteOpt.get().setCpf(cliente.getCpf());
			clienteOpt.get().setProfissao(cliente.getProfissao());
			clienteRepository.save(clienteOpt.get());
			return true;
		}
		return false;
	}



}
