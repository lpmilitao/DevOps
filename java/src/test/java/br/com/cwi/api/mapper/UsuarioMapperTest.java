package br.com.cwi.api.mapper;

import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.controller.request.UsuarioRequest;
import br.com.cwi.api.security.controller.response.UsuarioResponse;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.mapper.UsuarioMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class UsuarioMapperTest {

    @Test @DisplayName("Deve retornar uma entidade corretamente quando o request for válido.")
    void deveRetornarUsuarioCorretamente(){

        UsuarioRequest request = UsuarioFactory.getRequest();

        Usuario usuario = UsuarioMapper.toEntity(request);

        assertEquals(request.getNomeCompleto(), usuario.getNomeCompleto());
        assertEquals(request.getEmail(), usuario.getEmail());
        assertEquals(request.getApelido(), usuario.getApelido());
        assertEquals(request.getDataNascimento(), usuario.getDataNascimento());
        assertEquals(request.getImagemPerfil(), usuario.getImagemPerfil());
    }

    @Test @DisplayName("Deve retornar uma entidade nula quando o request for inválido.")
    void deveRetornarUsuarioNulo(){

        UsuarioRequest request = new UsuarioRequest();

        Usuario usuario = UsuarioMapper.toEntity(request);

        assertNull(usuario.getNomeCompleto());
        assertNull(usuario.getEmail());
        assertNull(usuario.getApelido());
        assertNull(usuario.getDataNascimento());
        assertNull(usuario.getImagemPerfil());
    }

    @Test @DisplayName("Deve retornar um response corretamente quando a entidade for válida.")
    void deveRetornarResponseCorretamente(){

        Usuario usuario = UsuarioFactory.get();

        UsuarioResponse response = UsuarioMapper.toResponse(usuario);

        assertEquals(usuario.getId(), response.getId());
        assertEquals(usuario.getNomeCompleto(), response.getNome());
        assertEquals(usuario.getEmail(), response.getEmail());
    }

    @Test @DisplayName("Deve retornar um response nulo quando a entidade for inválida.")
    void deveRetornarResponseNulo(){

        Usuario usuario = new Usuario();

        UsuarioResponse response = UsuarioMapper.toResponse(usuario);

        assertNull(response.getId());
        assertNull(response.getNome());
        assertNull(response.getEmail());
    }

}