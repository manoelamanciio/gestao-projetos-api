package br.com.treina.recife.sgp.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DadosRespostaProjeto(
    Long id,
    String nome,
    String descricao,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataInicio,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataConclusao,
    String status,
    Long idResponsavel,
    String nomeResponsavel
) {
    
}
