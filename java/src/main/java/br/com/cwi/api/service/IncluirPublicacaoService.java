package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirPublicacaoRequest;
import br.com.cwi.api.domain.Publicacao;
import br.com.cwi.api.repository.PublicacaoRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.security.service.core.NowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.api.mapper.PublicacaoMapper.toEntity;

@Service
public class IncluirPublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private NowService nowService;

    @Transactional
    public void publicar(IncluirPublicacaoRequest request){

        Usuario autor = usuarioAutenticadoService.get();

        Publicacao publicacao = toEntity(request);
        autor.adicionarPublicacao(publicacao);
        publicacao.setDataPublicacao(nowService.getDate());

        publicacaoRepository.save(publicacao);
        usuarioRepository.save(autor);
    }

}
