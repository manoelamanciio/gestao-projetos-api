package br.com.treina.recife.sgp.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErroValidacao(
    LocalDateTime dataHora,
    int status,
    String erro,
    List<CampoInvalido> campos
) {

    public record CampoInvalido(
        String campo,
        String mensagem
    ) {

    }
    
}
