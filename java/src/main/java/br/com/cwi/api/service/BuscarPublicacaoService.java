package br.com.cwi.api.service;

import br.com.cwi.api.domain.Publicacao;
import br.com.cwi.api.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BuscarPublicacaoService {

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public Publicacao porId(Long id){
        return publicacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                                NOT_FOUND, "Publicação não encontrada."));
    }

}
