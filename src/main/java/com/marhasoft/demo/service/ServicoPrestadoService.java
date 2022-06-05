package com.marhasoft.demo.service;

import com.marhasoft.demo.dto.ServicoPrestadoDTO;
import com.marhasoft.demo.model.Cliente;
import com.marhasoft.demo.model.ServicoPrestado;
import com.marhasoft.demo.repository.ServicoPrestadoRepository;
import com.marhasoft.demo.util.BigDecimalConverter;
import com.marhasoft.demo.util.DateConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoPrestadoService {

    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final ClienteService clienteService;
    private final BigDecimalConverter bigDecimalConverter;
    private final DateConverter dateConverter;

    public ServicoPrestado salvar(ServicoPrestadoDTO servicoPrestadoDTO) {
        Integer clienteId = servicoPrestadoDTO.getClienteId();

        Cliente cliente = clienteService.buscarPorId(clienteId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Cliente inexistente no sistema!"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(servicoPrestadoDTO.getDescricao());
        servicoPrestado.setData(dateConverter.converterStringEmLocalDate(servicoPrestadoDTO.getData()));
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(servicoPrestadoDTO.getPreco()));

        return servicoPrestadoRepository.save(servicoPrestado);
    }

    public List<ServicoPrestado> findByNomeClienteAndMes(String nome, Integer mes) {
        return servicoPrestadoRepository.findByNomeClienteAndMes(nome, mes);
    }
}
