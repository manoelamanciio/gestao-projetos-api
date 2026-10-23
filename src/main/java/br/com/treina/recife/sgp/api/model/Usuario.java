package br.com.treina.recife.sgp.api.model;

import java.time.LocalDate;

import br.com.treina.recife.sgp.api.model.enums.StatusUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// CREATE TABLE TB_USUARIOS
@Entity(name = "TB_USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // ID PRIMARY KEY AUTO_INCREMENT

    @Column(nullable = false, length = 150) // NOT NULL VARCHAR(150)
    private String nome;
    
    @Column(nullable = false, unique = true, length = 14)    // NOT NULL UNIQUE VARCHAR(14)
    private String cpf;
    
    @Column(nullable = false, unique = true)    // NOT NULL UNIQUE
    private String email;

    @Column(nullable = false) // NOT NULL
    private String senha;

    private LocalDate dataNascimento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusUsuario status;

}
