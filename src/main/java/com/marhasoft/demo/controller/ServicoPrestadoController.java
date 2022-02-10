package com.marhasoft.demo.controller;

import com.marhasoft.demo.dto.ServicoPrestadoDTO;
import com.marhasoft.demo.model.Cliente;
import com.marhasoft.demo.model.ServicoPrestado;
import com.marhasoft.demo.repository.ServicoPrestadoRepository;
import com.marhasoft.demo.service.ClienteService;
import com.marhasoft.demo.util.BigDecimalConverter;
import com.marhasoft.demo.util.DateConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/servicos-prestados")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ServicoPrestadoController {

    private final ClienteService clienteService;
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final BigDecimalConverter bigDecimalConverter;
    private final DateConverter dateConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO servicoPrestadoDto) {
        Integer clienteId = servicoPrestadoDto.getClienteId();
        Cliente cliente = clienteService.buscarPorId(clienteId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Cliente inexistente no sistema!"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(servicoPrestadoDto.getDescricao());
        servicoPrestado.setData(dateConverter.converterStringEmLocalDate(servicoPrestadoDto.getData()));
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(servicoPrestadoDto.getPreco()));
        return servicoPrestadoRepository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ) {
        return servicoPrestadoRepository.findByNomeClienteAndMes("%" +nome+ "%", mes);
    }

}
