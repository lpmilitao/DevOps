package br.com.cwi.api.service;

import br.com.cwi.api.domain.Solicitacao;
import br.com.cwi.api.repository.SolicitacaoRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class AceitarAmizadeService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private BuscarSolicitacaoService buscarSolicitacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void aceitar(Long solicitacaoId) {

        Solicitacao solicitacao = buscarSolicitacaoService.porId(solicitacaoId);
        Usuario usuario = usuarioAutenticadoService.get();

        if(solicitacao.getAmigo().getId() != usuario.getId()){
            throw new ResponseStatusException(BAD_REQUEST,
                    "Você não pode aceitar uma solicitação que não foi feita para você.");
        }

        usuario.comecarAmizade(solicitacao.getUsuario());
        solicitacaoRepository.delete(solicitacao);
        usuarioRepository.save(usuario);
    }
}
