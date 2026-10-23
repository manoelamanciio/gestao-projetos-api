package br.com.treina.recife.sgp.api.dto;

import java.time.LocalDate;

import br.com.treina.recife.sgp.api.model.enums.PrioridadeTarefa;
import br.com.treina.recife.sgp.api.model.enums.StatusTarefa;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosRequisicaoTarefa(
    @NotBlank @Size(min = 3)
    String titulo,
    String descricao,
    @NotNull
    LocalDate dataCriacao,
    @FutureOrPresent
    LocalDate dataConclusao,
    @NotNull
    PrioridadeTarefa prioridade,
    @NotNull
    StatusTarefa statusTarefa,
    @NotNull
    Long idProjeto,
    Long idUsuario
) {
    
}
