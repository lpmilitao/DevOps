package br.com.cwi.api.service;

import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarUsuarioServiceTest {

    @InjectMocks
    private BuscarUsuarioService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test @DisplayName("Deve buscar corretamente um usuário quando o id é válido.")
    void deveBuscarUsuarioCorretamente(){

        Usuario usuario = UsuarioFactory.get();

        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        Usuario response = tested.porId(usuario.getId());

        verify(usuarioRepository).findById(usuario.getId());
        assertEquals(usuario.getId(), response.getId());
    }

    @Test @DisplayName("Deve lançar uma exceção quando id de usuário for inválido.")
    void naoDeveBuscarUsuarioComIdInvalido(){

        Long usuarioId = SimpleFactory.getRandomLong();

        assertThrows(ResponseStatusException.class, () -> {
            tested.porId(usuarioId);
        });
    }

}