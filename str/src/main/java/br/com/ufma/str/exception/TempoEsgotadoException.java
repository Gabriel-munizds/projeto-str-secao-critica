package br.com.ufma.str.exception;

public class TempoEsgotadoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String message;

    public TempoEsgotadoException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
