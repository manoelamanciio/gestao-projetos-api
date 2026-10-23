package br.com.treina.recife.sgp.api.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treina.recife.sgp.api.dto.DadosRequisicaoUsuario;
import br.com.treina.recife.sgp.api.dto.DadosRespostaUsuario;
import br.com.treina.recife.sgp.api.model.Usuario;
import br.com.treina.recife.sgp.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosRespostaUsuario cadastrar(DadosRequisicaoUsuario dados) {
        Usuario usuario = usuarioRepository.save(preencherUsuario(dados));

        return converterParaDTO(usuario);
    }

    public DadosRespostaUsuario buscarPeloId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (Objects.nonNull(usuario)) {
            return converterParaDTO(usuario);
        }

        return null;
    }

    public List<DadosRespostaUsuario> listar() {
        return usuarioRepository.findAll().stream().map(u -> converterParaDTO(u)).toList();
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    private Usuario preencherUsuario(DadosRequisicaoUsuario dados) {
        Usuario usuario = new Usuario();

        usuario.setNome(dados.nome());
        usuario.setCpf(dados.cpf());
        usuario.setEmail(dados.email());
        usuario.setSenha(dados.senha());
        usuario.setDataNascimento(dados.dataNascimento());
        usuario.setStatus(dados.status());

        return usuario;
    }

    private DadosRespostaUsuario converterParaDTO(Usuario usuario) {
        String cpfFormatado = usuario.getCpf().substring(0, 3) + ".***.***-**";

        Period periodo = Period.between(usuario.getDataNascimento(), LocalDate.now());

        return new DadosRespostaUsuario(
            usuario.getId(), 
            usuario.getNome(), 
            usuario.getEmail(), 
            cpfFormatado, 
            periodo.getYears(), 
            usuario.getDataNascimento(), 
            usuario.getStatus()
        );
    }

}
