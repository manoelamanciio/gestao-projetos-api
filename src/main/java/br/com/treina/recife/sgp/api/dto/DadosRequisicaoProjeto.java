package br.com.treina.recife.sgp.api.dto;

import java.time.LocalDate;

import br.com.treina.recife.sgp.api.model.enums.StatusProjeto;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosRequisicaoProjeto(
    @NotBlank @Size(min = 3)
    String nome,
    String descricao,
    @NotNull
    LocalDate dataInicio,
    @FutureOrPresent
    LocalDate dataConclusao,
    @NotNull
    StatusProjeto status,
    @NotNull
    Long idResponsavel
) {
    
}
