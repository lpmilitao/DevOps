package br.com.cwi.api.service;

import br.com.cwi.api.domain.Publicacao;
import br.com.cwi.api.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurtirPublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    private static final Long CURTIDA = 1L;

    @Transactional
    public void curtir(Long idPublicacao) {

        Publicacao publicacao = buscarPublicacaoService.porId(idPublicacao);
        publicacao.setCurtidas(publicacao.getCurtidas() + CURTIDA);

        publicacaoRepository.save(publicacao);
    }

    @Transactional
    public void retirarCurtir(Long idPublicacao) {

        Publicacao publicacao = buscarPublicacaoService.porId(idPublicacao);
        publicacao.setCurtidas(publicacao.getCurtidas() - CURTIDA);

        publicacaoRepository.save(publicacao);
    }

}
