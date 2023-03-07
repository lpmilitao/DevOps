package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.SolicitacaoResponse;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.cwi.api.mapper.SolicitacaoMapper.toResponse;
import static java.util.stream.Collectors.toList;

@Service
public class ListarSolicitacoesService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public List<SolicitacaoResponse> peloUsuario() {

        Usuario usuario = usuarioAutenticadoService.get();

        return usuario.getSoliciteiAmizade().stream()
                .map(solicitacao -> toResponse(solicitacao))
                .collect(toList());
    }

    public List<SolicitacaoResponse> paraUsuario() {

        Usuario usuario = usuarioAutenticadoService.get();

        return usuario.getMeSolicitaram().stream()
                .map(solicitacao -> toResponse(solicitacao))
                .collect(toList());
    }
}
