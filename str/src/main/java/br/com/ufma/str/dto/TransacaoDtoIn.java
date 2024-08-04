package br.com.ufma.str.dto;

import java.math.BigDecimal;

public class TransacaoDtoIn {
    private BigDecimal valorTransacao;
    private Long tipoTransacao;

    public BigDecimal getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(BigDecimal valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public Long getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(Long tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}
