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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treina.recife.sgp.api.dto.DadosRequisicaoUsuario;
import br.com.treina.recife.sgp.api.dto.DadosRespostaUsuario;
import br.com.treina.recife.sgp.api.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping
    public ResponseEntity<DadosRespostaUsuario> cadastrarUsuario(@Valid @RequestBody DadosRequisicaoUsuario dados) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrar(dados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosRespostaUsuario> buscarUsuarioPeloId(@PathVariable Long id) {
        DadosRespostaUsuario u = usuarioService.buscarPeloId(id);

        if (Objects.isNull(u)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(u);
    }

    @GetMapping
    public ResponseEntity<List<DadosRespostaUsuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        DadosRespostaUsuario u = usuarioService.buscarPeloId(id);

        if (Objects.isNull(u)) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
