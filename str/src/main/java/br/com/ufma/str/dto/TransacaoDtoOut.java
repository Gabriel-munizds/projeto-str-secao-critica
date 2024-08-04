package br.com.ufma.str.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoDtoOut {
    private Long id;
    private BigDecimal valorTransacao;
    private String tipoTransacao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "America/Fortaleza")
    private LocalDateTime dataTransacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(BigDecimal valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}
