package br.com.ufma.str.service;

import br.com.ufma.str.exception.NegocioException;

import java.math.BigDecimal;

public enum TransacaoEnum {
    DEPOSITO(1L) {
        @Override
        public BigDecimal calcularTransacao(BigDecimal valorTransacao, BigDecimal saldoAnterior) {
            return saldoAnterior.add(valorTransacao);
        }
    },
    SAQUE(2L) {
        @Override
        public BigDecimal calcularTransacao(BigDecimal valorTransacao, BigDecimal saldoAnterior) {
            if(saldoAnterior.compareTo(valorTransacao) < 0 ){
                throw new NegocioException("SALDO INSUFICIENTE!");
            }
            return saldoAnterior.subtract(valorTransacao);
        }
    };
    private Long tipoTransacao;

    TransacaoEnum(Long tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public abstract BigDecimal calcularTransacao(BigDecimal valorTransacao, BigDecimal saldoAnterior);

    public static TransacaoEnum ofId(Long id){
        for(TransacaoEnum transacaoEnum : values()){
            if(transacaoEnum.tipoTransacao.equals(id))
                return transacaoEnum;
        }
        return null;
    }
}
