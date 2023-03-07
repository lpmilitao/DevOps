package br.com.cwi.api.service;

import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.controller.request.UsuarioRequest;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.IncluirUsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class IncluirUsuarioServiceTest {

    @InjectMocks
    private IncluirUsuarioService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;

    @Test @DisplayName("Deve incluir um novo usuário corretamente")
    void deveIncluirUsuarioCorretamente(){
        UsuarioRequest request = UsuarioFactory.getRequest();

        tested.incluir(request);

        verify(passwordEncoder).encode(request.getSenha());
        verify(usuarioRepository).save(usuarioCaptor.capture());

        Usuario usuario = usuarioCaptor.getValue();
        assertTrue(usuario.isAtivo());
        assertEquals(1, usuario.getPermissoes().size());
    }

}