package com.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.client.entities.Cliente;

public interface ClientRepository extends JpaRepository<Cliente, Long> {

	
	

}