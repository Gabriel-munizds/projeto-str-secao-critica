package br.com.ufma.str.config;

import br.com.ufma.str.dto.ErroDTO;
import br.com.ufma.str.exception.NegocioException;
import br.com.ufma.str.exception.TempoEsgotadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeoutException;

@RestControllerAdvice
public class GenericHandler {
    @ExceptionHandler(NegocioException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<Object> handlerNegocioException(NegocioException e) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CONFLICT).body(new ErroDTO(e.getMessage()));
        e.printStackTrace();
        return response;
    }

    @ExceptionHandler(TempoEsgotadoException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<Object> handlerTimeOutException(TempoEsgotadoException e) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CONFLICT).body(new ErroDTO(e.getMessage()));
        e.printStackTrace();
        return response;
    }
}
