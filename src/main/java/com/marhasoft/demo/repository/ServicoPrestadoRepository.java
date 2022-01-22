package com.marhasoft.demo.repository;

import com.marhasoft.demo.model.ServicoPrestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {
}
