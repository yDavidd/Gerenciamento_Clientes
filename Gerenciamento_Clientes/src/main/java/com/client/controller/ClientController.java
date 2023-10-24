package com.client.controller;

import java.util.List;

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

import com.client.entities.Cliente;
import com.client.services.ClientServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Clientes", description = "API REST DE GERENCIAMENTO DE CLIENTES")
@RestController
@RequestMapping("/cliente")
public class ClientController {
	
	private final ClientServico clientServico;
	
	@Autowired
	public ClientController(ClientServico clientServico) {
		this.clientServico = clientServico;
	}
	@GetMapping("/{id}")
	@Operation (summary = "Localiza cliente por ID")
	public ResponseEntity<Cliente> buscaClienteControlId(@PathVariable Long id){
		Cliente cliente = clientServico.buscaClienteId(id);
		if(cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		else{
			return ResponseEntity.notFound().build();
	
		}
	}
	@GetMapping("/")
	@Operation (summary = "Apresenta todos os clientes")
	public ResponseEntity<List<Cliente>> buscaTodosClientesControl(){
		List<Cliente> Clientes = clientServico.buscaTodosClientes();
		return ResponseEntity.ok(Clientes);
	}
	@PostMapping("/")
	@Operation (summary = "Cadastra um cliente")
	public ResponseEntity<Cliente> salvaClientesControl (@RequestBody @Valid Cliente cliente){
		Cliente salvaCliente = clientServico.salvaCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCliente);
	}
	@PutMapping("/{id}")
	@Operation (summary = "Altera cliente")
	public ResponseEntity<Cliente> alteraClienteControl (@PathVariable Long id, @RequestBody @Valid Cliente cliente){
		Cliente alteraCliente = clientServico.alterarCliente(id, cliente);
		if(alteraCliente != null) {
			return ResponseEntity.ok(cliente);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Exclui usuário")
	public ResponseEntity<Cliente> apagaUsuarioControl (@PathVariable Long id){
		boolean apagar = clientServico.apagarCliente(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
