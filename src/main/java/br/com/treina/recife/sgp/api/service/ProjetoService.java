package br.com.treina.recife.sgp.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treina.recife.sgp.api.dto.DadosRequisicaoProjeto;
import br.com.treina.recife.sgp.api.dto.DadosRespostaProjeto;
import br.com.treina.recife.sgp.api.model.Projeto;
import br.com.treina.recife.sgp.api.model.Usuario;
import br.com.treina.recife.sgp.api.model.enums.StatusProjeto;
import br.com.treina.recife.sgp.api.repository.ProjetoRepository;
import br.com.treina.recife.sgp.api.repository.UsuarioRepository;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosRespostaProjeto cadastrar(DadosRequisicaoProjeto dados) {
        Projeto projeto = new Projeto();

        projeto.setNome(dados.nome());
        projeto.setDescricao(dados.descricao());
        projeto.setDataInicio(dados.dataInicio());
        projeto.setDataConclusao(dados.dataConclusao());
        projeto.setStatus(dados.status());

        Usuario usuario = usuarioRepository.findById(dados.idResponsavel()).orElse(null);

        projeto.setResponsavel(usuario);

        // TODO: lancar excecao caso usuario for NULL

        projeto = projetoRepository.save(projeto);

        return new DadosRespostaProjeto(
                projeto.getId(),
                projeto.getNome(),
                projeto.getDescricao(),
                projeto.getDataInicio(),
                projeto.getDataConclusao(),
                projeto.getStatus().toString(),
                projeto.getResponsavel().getId(),
                projeto.getResponsavel().getNome());
    }

    public Projeto buscar(Long idProjeto) {
        return projetoRepository.findById(idProjeto).orElse(null);
    }

    public List<Projeto> buscar(StatusProjeto status) {
        return projetoRepository.findByStatus(status);
    }

    public List<Projeto> buscar(LocalDate dataInicio, LocalDate dataFim) {
        return projetoRepository.findByDataInicioBetween(dataInicio, dataFim);
    }

    public List<Projeto> filtrarPeloResponsavel(Long idUsuario) {
        return projetoRepository.findByResponsavel_Id(idUsuario);
    }

    public List<Projeto> listar() {
        return projetoRepository.findAll();
    }

    public void deletar(Long idProjeto) {
        projetoRepository.deleteById(idProjeto);
    }

}
