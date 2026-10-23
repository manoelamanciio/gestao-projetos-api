package br.com.treina.recife.sgp.api.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.treina.recife.sgp.api.model.enums.StatusUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosRequisicaoUsuario(
    @NotBlank @Size(min = 3, max = 150)
    String nome,
    @NotBlank @Email
    String email,
    @NotBlank @CPF
    String cpf,
    @NotBlank(message = "Campo `senha` é obrigatório.")
    String senha,
    LocalDate dataNascimento,
    @NotNull
    StatusUsuario status
) {

}