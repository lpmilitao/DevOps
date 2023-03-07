package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.PublicacaoResponse;
import br.com.cwi.api.mapper.PublicacaoMapper;
import br.com.cwi.api.repository.PublicacaoRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.cwi.api.domain.VisualizacaoPublicacao.PUBLICO;
import static br.com.cwi.api.mapper.PublicacaoMapper.toResponse;
import static java.util.stream.Collectors.toList;

@Service
public class ListarPublicacoesService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public Page<PublicacaoResponse> listar(Pageable pageable) {

        Usuario usuario = usuarioAutenticadoService.get();

        List<PublicacaoResponse> publicacoes = publicacaoRepository.findAll(pageable)
                .stream().filter(publicacao -> publicacao.getAutor().getId() == usuario.getId() ||
                        usuario.getAmigos().stream().anyMatch(
                                amigo -> amigo.getId() == publicacao.getAutor().getId()))
                .map(PublicacaoMapper::toResponse)
                .collect(toList());

        Page<PublicacaoResponse> response = new PageImpl<>(publicacoes);

        return response;
    }

    public Page<PublicacaoResponse> listarPublicas(Pageable pageable) {

        return publicacaoRepository.findByVisualizacaoLike(
                        PUBLICO, pageable)
                .map(publicacao -> toResponse(publicacao));
    }

}