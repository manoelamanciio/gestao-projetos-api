package br.com.treina.recife.sgp.api.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.treina.recife.sgp.api.dto.ErroValidacao;
import br.com.treina.recife.sgp.api.dto.ErroValidacao.CampoInvalido;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroValidacao> tratarDataInvalida(HttpMessageNotReadableException ex) {
        List<CampoInvalido> campos = new ArrayList<>();

        campos.add(new CampoInvalido("Data de Nascimento", "O formato deve ser YYYY-MM-DD."));
        
        ErroValidacao erro = new ErroValidacao(
            LocalDateTime.now(), 
            HttpStatus.BAD_REQUEST.value(),
            "Campo Inválido", 
            campos
        );

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> tratarCamposInvalidos(MethodArgumentNotValidException ex) {
        List<CampoInvalido> campos = ex.getFieldErrors()
                                        .stream()
                                        .map(erro -> new CampoInvalido(erro.getField(), erro.getDefaultMessage()))
                                        .toList();

        ErroValidacao erro = new ErroValidacao(
            LocalDateTime.now(), 
            HttpStatus.BAD_REQUEST.value(), 
            "Campos Inválidos", 
            campos);

        return ResponseEntity.badRequest().body(erro);
    }

}
