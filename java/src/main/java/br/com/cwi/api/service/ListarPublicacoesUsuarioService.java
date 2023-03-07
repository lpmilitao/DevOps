package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.PublicacaoResponse;
import br.com.cwi.api.mapper.PublicacaoMapper;
import br.com.cwi.api.repository.PublicacaoRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.cwi.api.domain.VisualizacaoPublicacao.PUBLICO;
import static java.util.stream.Collectors.toList;

@Service
public class ListarPublicacoesUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;
    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public Page<PublicacaoResponse> listar(Long id) {

        Usuario usuario = buscarUsuarioService.porId(id);
        Long usuarioLogadoId = usuarioAutenticadoService.getId();

        List<PublicacaoResponse> publicacoes = usuario.getPublicacoes().stream()
                .map(PublicacaoMapper::toResponse)
                .collect(toList());

        boolean saoAmigos = usuario.getAmigos().stream().anyMatch(
                amigo -> amigo.getId() == usuarioLogadoId
        );

        if(saoAmigos){
            return new PageImpl<>(publicacoes);
        }

        List<PublicacaoResponse> publicacoesPublicas = publicacoes.stream()
                .filter(publicacao -> publicacao.getVisualizacao() == PUBLICO)
                .collect(toList());

        return new PageImpl<>(publicacoesPublicas);
    }
}
