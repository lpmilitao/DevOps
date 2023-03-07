package br.com.cwi.api.security.service;

import br.com.cwi.api.security.controller.request.EditarUsuarioRequest;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditarUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void editar(EditarUsuarioRequest request) {

        Usuario usuario = usuarioAutenticadoService.get();

        if (!(usuario.getNomeCompleto().equalsIgnoreCase(request.getNome()))){
            usuario.setNomeCompleto(request.getNome());
        }

        if (!(usuario.getApelido().equalsIgnoreCase(request.getApelido()))){
            usuario.setApelido(request.getApelido());
        }

        if (!(usuario.getImagemPerfil().equalsIgnoreCase(request.getImagemPerfil()))){
            usuario.setImagemPerfil(request.getImagemPerfil());
        }

        usuarioRepository.save(usuario);
    }
}
