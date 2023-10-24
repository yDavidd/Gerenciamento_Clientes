package com.client.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.entities.Cliente;
import com.client.repository.ClientRepository;

@Service
public class ClientServico {
	
	private final ClientRepository clientRepository;
	
	@Autowired
	public ClientServico (ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	public List<Cliente> buscaTodosClientes(){
		return clientRepository.findAll();
	}
	public Cliente buscaClienteId(Long id) {
		Optional <Cliente> Cliente = clientRepository.findById(id);
		return Cliente.orElse(null);
	}
	public Cliente alterarCliente (Long id, Cliente alterarCliente) {
		Optional <Cliente> existeCliente = clientRepository.findById(id);
		if(existeCliente.isPresent()) {
			alterarCliente.setId(id);
			return clientRepository.save(alterarCliente);
		}
		return null;
	}
	public boolean apagarCliente(Long id) {
		Optional <Cliente> existeCliente = clientRepository.findById(id);
		if(existeCliente.isPresent()) {
		clientRepository.deleteById(id);
		return true;
	}
	return false;
	}
	public Cliente salvaCliente(Cliente cliente) {
		return clientRepository.save(cliente);
	}
}