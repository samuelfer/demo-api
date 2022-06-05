package com.marhasoft.demo.controller;

import com.marhasoft.demo.dto.ServicoPrestadoDTO;
import com.marhasoft.demo.model.ServicoPrestado;
import com.marhasoft.demo.service.ServicoPrestadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

    private final ServicoPrestadoService servicoPrestadoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO servicoPrestadoDto) {
        return servicoPrestadoService.salvar(servicoPrestadoDto);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ) {
        return servicoPrestadoService.findByNomeClienteAndMes("%" +nome+ "%", mes);
    }

}
