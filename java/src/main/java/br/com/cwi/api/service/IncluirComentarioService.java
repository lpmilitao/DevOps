package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirComentarioRequest;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Publicacao;
import br.com.cwi.api.repository.ComentarioRepository;
import br.com.cwi.api.repository.PublicacaoRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.api.mapper.ComentarioMapper.toEntity;

@Service
public class IncluirComentarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    @Transactional
    public void comentar(Long id, IncluirComentarioRequest request) {

        Usuario autor = usuarioAutenticadoService.get();
        Publicacao publicacao = buscarPublicacaoService.porId(id);

        Comentario comentario = toEntity(request);

        publicacao.adicionarComentario(comentario);
        autor.adicionarComentario(comentario);

        comentarioRepository.save(comentario);
        publicacaoRepository.save(publicacao);
        usuarioRepository.save(autor);
    }
}
