package br.com.ufma.str.dto;

public class ErroDTO {
    private String mensagem;

    public ErroDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
