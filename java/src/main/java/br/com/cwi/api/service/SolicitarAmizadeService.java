package br.com.cwi.api.service;

import br.com.cwi.api.domain.Solicitacao;
import br.com.cwi.api.repository.SolicitacaoRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.validator.VerificarAmizadeExistenteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitarAmizadeService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private VerificarAmizadeExistenteValidator verificarAmizadeExistenteValidator;

    @Transactional
    public void solicitar(Long amigoId) {

        Usuario usuarioQueSolicitou = usuarioAutenticadoService.get();
        Usuario amigoSolicitado = buscarUsuarioService.porId(amigoId);

        verificarAmizadeExistenteValidator.jaSaoAmigos(usuarioQueSolicitou.getAmigos(), amigoSolicitado);
        verificarAmizadeExistenteValidator.jaSolicitado(usuarioQueSolicitou.getSoliciteiAmizade(), amigoSolicitado);

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setUsuario(usuarioQueSolicitou);
        solicitacao.setAmigo(amigoSolicitado);

        solicitacaoRepository.save(solicitacao);
    }
}
