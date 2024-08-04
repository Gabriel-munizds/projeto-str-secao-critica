package br.com.ufma.str.repository;

import br.com.ufma.str.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransacaoRepository extends JpaRepository <Transacao, Long> {
    @Query("select t from Transacao t " +
            "inner join Conta conta on t.conta = conta " +
            "where conta.id = :idConta ")
    List<Transacao> findAllTransacoesByIdConta(Long idConta);
}
