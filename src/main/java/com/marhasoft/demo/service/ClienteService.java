package com.marhasoft.demo.service;

import com.marhasoft.demo.model.Cliente;
import com.marhasoft.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return this.clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Integer id) {
        return this.clienteRepository.findById(id);
    }

    public Cliente salvar(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public void deletar(Cliente cliente) {
        this.clienteRepository.delete(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }
}
