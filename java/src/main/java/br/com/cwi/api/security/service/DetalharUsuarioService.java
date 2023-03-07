package br.com.cwi.api.security.service;

import br.com.cwi.api.controller.response.UsuarioDetalhadoResponse;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.cwi.api.security.mapper.UsuarioDetalhadoMapper.toResponse;

@Service
public class DetalharUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public UsuarioDetalhadoResponse detalhar() {

        Usuario usuario = usuarioAutenticadoService.get();

        return toResponse(usuario);
    }
}
