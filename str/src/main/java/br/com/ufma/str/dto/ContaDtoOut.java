package br.com.ufma.str.dto;

import java.math.BigDecimal;
import java.util.List;

public class ContaDtoOut {
    private Long id;
    private BigDecimal saldo;
    private List<TransacaoDtoOut> historicoTransacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<TransacaoDtoOut> getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    public void setHistoricoTransacoes(List<TransacaoDtoOut> historicoTransacoes) {
        this.historicoTransacoes = historicoTransacoes;
    }
}
