package br.com.cwi.api.service;

import br.com.cwi.api.domain.Solicitacao;
import br.com.cwi.api.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BuscarSolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;


    public Solicitacao porId(Long id){
        return solicitacaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                NOT_FOUND, "Solicitação não encontrada."
        ));
    }

}
