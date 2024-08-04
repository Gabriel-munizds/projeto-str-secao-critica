package br.com.ufma.str.model;

import javax.persistence.*;

@Entity
@Table(name = "TIPO_TRANSACAO")
public class TipoTransacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_TRANSACAO", nullable = false)
    private Long id;
    @Column(name = "DESC_TIPO_TRANSACAO", nullable = false)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
