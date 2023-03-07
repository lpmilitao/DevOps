package br.com.cwi.api.service;

import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import br.com.cwi.api.security.service.DesfazerAmizadeService;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DesfazerAmizadeServiceTest {

    @InjectMocks
    private DesfazerAmizadeService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarUsuarioService buscarUsuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;

    @Test @DisplayName("Deve desfazer corretamente a amizade de duas pessoas.")
    void deveDesfazerAmizadeCorretamente(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        usuario.comecarAmizade(amigo);

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(amigo.getId())).thenReturn(amigo);

        tested.desfazer(amigo.getId());

        verify(usuarioRepository).save(usuarioCaptor.capture());

        Usuario usuarioSemAmigo = usuarioCaptor.getValue();
        assertFalse(usuarioSemAmigo.getAmigos().stream().anyMatch(
                amigoDoUsuario -> amigoDoUsuario.getId() == amigo.getId()
        ));
    }

    @Test @DisplayName("Deve lançar uma exceção quando o usuário tenta desfazer uma amizade que não existe.")
    void naoDeveDesfazerAmizadeQueNaoExiste(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        assertThrows(ResponseStatusException.class, () -> {
            tested.desfazer(amigo.getId());
        });

        verify(buscarUsuarioService, never()).porId(amigo.getId());
        verify(usuarioRepository, never()).save(usuarioCaptor.capture());
    }


}
