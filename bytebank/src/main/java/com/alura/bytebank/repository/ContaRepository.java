package com.alura.bytebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.bytebank.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}
