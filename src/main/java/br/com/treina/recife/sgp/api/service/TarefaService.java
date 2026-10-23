package br.com.treina.recife.sgp.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treina.recife.sgp.api.dto.DadosRequisicaoTarefa;
import br.com.treina.recife.sgp.api.model.Projeto;
import br.com.treina.recife.sgp.api.model.Tarefa;
import br.com.treina.recife.sgp.api.model.Usuario;
import br.com.treina.recife.sgp.api.repository.ProjetoRepository;
import br.com.treina.recife.sgp.api.repository.TarefaRepository;
import br.com.treina.recife.sgp.api.repository.UsuarioRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Tarefa cadastrar(DadosRequisicaoTarefa dados) {
        Projeto projeto = projetoRepository.findById(dados.idProjeto())
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Usuario usuario = null;
        if (dados.idUsuario() != null) {
            usuario = usuarioRepository.findById(dados.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        }

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dados.titulo());
        tarefa.setDescricao(dados.descricao());
        tarefa.setDataCriacao(dados.dataCriacao());
        tarefa.setDataConclusao(dados.dataConclusao());
        tarefa.setPrioridade(dados.prioridade());
        tarefa.setStatus(dados.statusTarefa());
        tarefa.setProjeto(projeto);
        tarefa.setUsuario(usuario);

        return tarefaRepository.save(tarefa);
    }

    public Tarefa buscar(Long idTarefa) {
        return tarefaRepository.findById(idTarefa).orElse(null);
    }

    public List<Tarefa> listar() {
        return tarefaRepository.findAll();
    }

    public void deletar(Long idTarefa) {
        tarefaRepository.deleteById(idTarefa);
    }

    public Tarefa atualizar(Long idTarefa, Tarefa dadosAtualizados) {
        dadosAtualizados.setId(idTarefa);
        return tarefaRepository.save(dadosAtualizados);
    }

}