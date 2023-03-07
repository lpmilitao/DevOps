package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.GatoDetalhadoResponse;
import br.com.cwi.api.mapper.GatoDetalhadoMapper;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ListarGatosService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public List<GatoDetalhadoResponse> listarMeusGatos() {

        Usuario usuario = usuarioAutenticadoService.get();

        return usuario.getGatos().stream()
                .map(GatoDetalhadoMapper::toResponse)
                .collect(toList());
    }

    public List<GatoDetalhadoResponse> listarGatosDoUsuario(Long usuarioId) {

        Usuario usuario = buscarUsuarioService.porId(usuarioId);

        return usuario.getGatos().stream()
                .map(GatoDetalhadoMapper::toResponse)
                .collect(toList());
    }

}
