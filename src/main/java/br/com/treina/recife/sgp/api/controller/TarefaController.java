package br.com.treina.recife.sgp.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.treina.recife.sgp.api.dto.DadosRequisicaoTarefa;

import br.com.treina.recife.sgp.api.model.Tarefa;
import br.com.treina.recife.sgp.api.service.TarefaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;
    
     @PostMapping
    public ResponseEntity<Tarefa> cadastrarTarefa(@Valid @RequestBody DadosRequisicaoTarefa dados) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.cadastrar(dados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefa(@PathVariable Long id) {
        Tarefa t = tarefaService.buscar(id);

        if (Objects.isNull(t)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(t);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        return ResponseEntity.ok(tarefaService.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
        Tarefa t = tarefaService.buscar(id);

        if (Objects.isNull(t)) {
            return ResponseEntity.notFound().build();
        }

        tarefaService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(
        @PathVariable Long id, @RequestBody Tarefa dadosAtualizados) {

        Tarefa t = tarefaService.buscar(id);

        if (Objects.isNull(t)) {
            return ResponseEntity.notFound().build();
        } 

        return ResponseEntity.ok(tarefaService.atualizar(id, dadosAtualizados));
    }

}
