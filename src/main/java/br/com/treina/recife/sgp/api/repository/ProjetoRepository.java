package br.com.treina.recife.sgp.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treina.recife.sgp.api.model.Projeto;
import br.com.treina.recife.sgp.api.model.enums.StatusProjeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    List<Projeto> findByStatus(StatusProjeto status);

    List<Projeto> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);
    // SELECT * FROM TB_PROJETOS WHERE dataInicio BETWEEN ...

    List<Projeto> findByResponsavel_Id(Long idUsuario);
    
}
