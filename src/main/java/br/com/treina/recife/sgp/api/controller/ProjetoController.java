package br.com.treina.recife.sgp.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treina.recife.sgp.api.dto.DadosRequisicaoProjeto;
import br.com.treina.recife.sgp.api.dto.DadosRespostaProjeto;
import br.com.treina.recife.sgp.api.model.Projeto;
import br.com.treina.recife.sgp.api.model.enums.StatusProjeto;
import br.com.treina.recife.sgp.api.service.ProjetoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;
    
    @PostMapping
    public ResponseEntity<DadosRespostaProjeto> cadastrarProjeto(@Valid @RequestBody DadosRequisicaoProjeto dados) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoService.cadastrar(dados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjeto(@PathVariable Long id) {
        Projeto p = projetoService.buscar(id);

        if (Objects.isNull(p)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(p);
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> listarProjetos() {
        return ResponseEntity.ok(projetoService.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProjeto(@PathVariable Long id) {
        Projeto p = projetoService.buscar(id);

        if (Objects.isNull(p)) {
            return ResponseEntity.notFound().build();
        }

        projetoService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtroPorStatus")
    public ResponseEntity<List<Projeto>> buscarPeloStatus(@RequestParam StatusProjeto status) {
        return ResponseEntity.ok(projetoService.buscar(status));
    } 

    @GetMapping("/filtroPelaDataInicio")
    public ResponseEntity<List<Projeto>> buscarPelaDataInicio(
        @RequestParam LocalDate startDate,
        @RequestParam LocalDate endDate
    ) {
        return ResponseEntity.ok(projetoService.buscar(startDate, endDate));
    }

    @GetMapping("/responsavel/{idResponsavel}")
    public ResponseEntity<List<Projeto>> buscarPeloResponsavel(@PathVariable Long idResponsavel) {
        return ResponseEntity.ok(projetoService.filtrarPeloResponsavel(idResponsavel));
    }

}
