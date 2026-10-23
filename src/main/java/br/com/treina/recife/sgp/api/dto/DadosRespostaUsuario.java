package br.com.treina.recife.sgp.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.treina.recife.sgp.api.model.enums.StatusUsuario;

public record DadosRespostaUsuario(
    Long id,
    String nome,
    String email,
    String cpf,
    Integer idade,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataNascimento,
    StatusUsuario status
) {
    
}
