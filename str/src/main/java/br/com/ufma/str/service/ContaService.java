package br.com.ufma.str.service;

import br.com.ufma.str.dto.*;
import br.com.ufma.str.exception.TempoEsgotadoException;
import br.com.ufma.str.model.Conta;
import br.com.ufma.str.model.Transacao;
import br.com.ufma.str.repository.ContaRepository;
import br.com.ufma.str.repository.TransacaoRepository;
import br.com.ufma.str.utils.GenericMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContaService {
    private final static Logger logger = LoggerFactory.getLogger(ContaService.class);
    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;
    private final GenericMapper genericMapper;
    private static Map<DadosTransacaoDTO, Object> lock = new HashMap<>();

    public ContaService(ContaRepository contaRepository, TransacaoRepository transacaoRepository, GenericMapper genericMapper) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
        this.genericMapper = genericMapper;
    }
    @Transactional
    public ContaDtoOut criarConta(CriarContaDtoIn dto) {
        Conta novaConta = genericMapper.toObject(dto, Conta.class);
        contaRepository.save(novaConta);
        return genericMapper.toObject(novaConta, ContaDtoOut.class);
    }
    @Transactional
    public ContaDtoOut criarTransacao(TransacaoDtoIn dto, Long idConta) throws InterruptedException {
        DadosTransacaoDTO dadosTransacao = new DadosTransacaoDTO(dto.getTipoTransacao(), idConta);
        logger.info("Thread {} tentando criar transação para conta {}", Thread.currentThread().getName(), idConta);
        synchronized (getLock(dadosTransacao)){
            try {
                logger.info("Thread {} entrou na seção crítica para conta {}", Thread.currentThread().getName(), idConta);
                Thread.sleep(10000);
                Transacao transacao = genericMapper.toObject(dto, Transacao.class);
                Conta conta = contaRepository.findById(idConta).orElseThrow();
                BigDecimal saldoAtualizado = TransacaoEnum.ofId(dto.getTipoTransacao())
                        .calcularTransacao(dto.getValorTransacao(), conta.getSaldo());
                transacao.setConta(conta);
                transacao.setDataTransacao(LocalDateTime.now());
                transacaoRepository.save(transacao);
                conta.setSaldo(saldoAtualizado);
                contaRepository.save(conta);
                logger.info("Thread {} concluiu a transação para conta {}", Thread.currentThread().getName(), idConta);
                return genericMapper.toObject(conta, ContaDtoOut.class);
            } catch (TempoEsgotadoException e){
                throw new TempoEsgotadoException(e.getMessage());
            }
        }
    }

    private static synchronized Object getLock(DadosTransacaoDTO dadosTransacao) {
        return lock.computeIfAbsent(dadosTransacao, v -> new Object());
    }

    public ContaDtoOut consultarExtratoConta(Long idConta) {
        Conta conta = contaRepository.findById(idConta).orElseThrow();
        ContaDtoOut dto = genericMapper.toObject(conta, ContaDtoOut.class);
        List<Transacao> transacaos = transacaoRepository.findAllTransacoesByIdConta(idConta);
        dto.setHistoricoTransacoes(genericMapper.toList(transacaos, TransacaoDtoOut.class));
        return dto;
    }
}
