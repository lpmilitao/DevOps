package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.UsuarioDetalhadoResponse;
import br.com.cwi.api.security.mapper.UsuarioDetalhadoMapper;
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
public class ListarUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<UsuarioDetalhadoResponse> listar(String filtro, Pageable pageable) {

        Long usuarioLogadoId = usuarioAutenticadoService.getId();

        Page<UsuarioDetalhadoResponse>  usuarios =
                usuarioRepository.findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCase(
                filtro, filtro, pageable
        ).map(UsuarioDetalhadoMapper::toResponse);

        List<UsuarioDetalhadoResponse> usuariosFiltrados = usuarios.stream()
                .filter(usuario -> usuario.getId() != usuarioLogadoId )
                .collect(toList());

        return new PageImpl<>(usuariosFiltrados);
    }
}
