package br.com.ufma.str.controller;

import br.com.ufma.str.dto.CriarContaDtoIn;
import br.com.ufma.str.model.Conta;
import br.com.ufma.str.repository.ContaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {
    private final ContaRepository contaRepository;

    public ContaController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @PostMapping
    public String criarConta(@RequestBody CriarContaDtoIn dto){
        Conta novaConta = new Conta();
        return "";
    }
}
