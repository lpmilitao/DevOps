package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.AmigoResponse;
import br.com.cwi.api.mapper.AmigoMapper;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ListarAmigosService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<AmigoResponse> listar(String filtro, Pageable pageable) {

        Usuario usuario = usuarioAutenticadoService.get();

        Page<Usuario> usuariosFiltrados = usuarioRepository.findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCase(
                filtro, filtro, pageable
        );

        List<AmigoResponse> amigos = usuariosFiltrados.stream()
                .filter(amigo -> usuario.getAmigos().stream().anyMatch(
                        amigoDoUsuario -> amigo.getId() == amigoDoUsuario.getId() ))
                .map(AmigoMapper::toResponse)
                .collect(toList());

        Page<AmigoResponse> amigosDeVerdade = new PageImpl<>(amigos);

        return amigosDeVerdade;
    }
}
