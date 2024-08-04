package br.com.ufma.str.dto;

import java.util.Objects;

public class DadosTransacaoDTO {
    private Long idConta;
    private Long tipoTransacao;

    public DadosTransacaoDTO(Long idConta, Long tipoTransacao) {
        this.idConta = idConta;
        this.tipoTransacao = tipoTransacao;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public Long getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(Long tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DadosTransacaoDTO that = (DadosTransacaoDTO) o;
        return Objects.equals(idConta, that.idConta) && Objects.equals(tipoTransacao, that.tipoTransacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConta, tipoTransacao);
    }
}
