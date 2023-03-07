package br.com.cwi.api.service;

import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.controller.request.EditarUsuarioRequest;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.EditarUsuarioService;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditarUsuarioServiceTest {

    @InjectMocks
    private EditarUsuarioService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;

    @Test @DisplayName("Deve editar corretamente o usuário conforme o request")
    void deveEditarUsuarioCorretamente(){

        Usuario usuario = UsuarioFactory.get();
        EditarUsuarioRequest request = new EditarUsuarioRequest();
        request.setNome("Teste");
        request.setApelido("Testinho");
        request.setImagemPerfil("");

        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        tested.editar(request);

        verify(usuarioRepository).save(usuarioCaptor.capture());

        Usuario usuarioEditado = usuarioCaptor.getValue();
        assertEquals(request.getNome(), usuarioEditado.getNomeCompleto());
        assertEquals(request.getApelido(), usuarioEditado.getApelido());
        assertEquals(request.getImagemPerfil(), usuarioEditado.getImagemPerfil());
    }

}
