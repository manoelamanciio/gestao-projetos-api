package br.com.treina.recife.sgp.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.treina.recife.sgp.api.model.enums.PrioridadeTarefa;
import br.com.treina.recife.sgp.api.model.enums.StatusTarefa;

public record DadosRespostaTarefa(
    Long id,
    String titulo,
    String descricao,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataCriacao,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataConclusao,
    PrioridadeTarefa prioridade,
    StatusTarefa status,
    Long idProjeto,
    String nomeProjeto,
    Long idUsuario,
    String nomeUsuario
) {
    
}
