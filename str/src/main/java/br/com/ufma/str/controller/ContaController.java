package br.com.ufma.str.controller;

import br.com.ufma.str.dto.ContaDtoOut;
import br.com.ufma.str.dto.CriarContaDtoIn;
import br.com.ufma.str.dto.TransacaoDtoIn;
import br.com.ufma.str.model.Conta;
import br.com.ufma.str.repository.ContaRepository;
import br.com.ufma.str.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }
    @PostMapping
    public ResponseEntity<ContaDtoOut> criarConta(@RequestBody CriarContaDtoIn dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.criarConta(dto));
    }
    @PostMapping("/transacao/{idConta}")
    public ResponseEntity<ContaDtoOut> criarTransacao(@RequestBody TransacaoDtoIn dto, @PathVariable Long idConta) throws InterruptedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.criarTransacao(dto, idConta));
    }
    @GetMapping("/extrato/{idConta}")
    public ResponseEntity<ContaDtoOut> consultarExtratoConta(@PathVariable Long idConta){
        return ResponseEntity.status(HttpStatus.OK).body(contaService.consultarExtratoConta(idConta));
    }

}
