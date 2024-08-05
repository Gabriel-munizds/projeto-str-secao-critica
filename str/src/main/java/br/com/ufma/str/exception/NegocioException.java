package br.com.ufma.str.exception;

public class NegocioException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String message;

    public NegocioException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
