package br.com.cwi.api.security.service;

import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class DesfazerAmizadeService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void desfazer(Long id) {

        Usuario usuario = usuarioAutenticadoService.get();
        boolean eAmigo = usuario.getAmigos().stream().anyMatch(
                amigo -> amigo.getId() == id
        );

        if(!eAmigo){
            throw new ResponseStatusException(BAD_REQUEST, "Você já não é amigo dessa pessoa.");
        }

        Usuario amigo = buscarUsuarioService.porId(id);
        usuario.terminarAmizade(amigo);

        usuarioRepository.save(usuario);
    }
}
