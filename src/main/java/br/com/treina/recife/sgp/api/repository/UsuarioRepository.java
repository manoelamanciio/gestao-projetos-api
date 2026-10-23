package br.com.treina.recife.sgp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treina.recife.sgp.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
