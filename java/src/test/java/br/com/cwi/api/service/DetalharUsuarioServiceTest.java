package br.com.cwi.api.service;

import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.DetalharUsuarioService;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DetalharUsuarioServiceTest {

    @InjectMocks
    private DetalharUsuarioService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Test @DisplayName("Deve detalhar corretamente o usuário logado.")
    void deveDetalharCorretamenteOUsuario(){

        Usuario usuario = UsuarioFactory.get();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        tested.detalhar();

        verify(usuarioAutenticadoService).get();
    }

}
