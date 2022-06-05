package com.marhasoft.demo.controller;

import com.marhasoft.demo.model.Cliente;
import com.marhasoft.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("http://localhost:4200/")
public class ClienteController {

    private static String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> buscarTodos() {
        return this.clienteService.listarTodos();
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
        try {
            Optional<Cliente> cliente = this.clienteService.buscarPorId(id);

            if (cliente.isEmpty()) {
                throw new RuntimeException(CLIENTE_NAO_ENCONTRADO);
            }
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente) {
        return this.clienteService.salvar(cliente);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteReq) {
        this.clienteService.buscarPorId(id)
        .map( cliente -> {
            clienteReq.setId(cliente.getId());
            return this.clienteService.atualizar(clienteReq);
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CLIENTE_NAO_ENCONTRADO));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        this.clienteService.buscarPorId(id)
                .map( cliente -> {
                    this.clienteService.deletar(cliente);
                    return Void.TYPE;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CLIENTE_NAO_ENCONTRADO));
    }
}
