package com.bancodedados.bancoDeDados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bancodedados.bancoDeDados.model.ContatoModel;

public interface ContatoRepository extends JpaRepository<ContatoModel, Long> {
	
}
