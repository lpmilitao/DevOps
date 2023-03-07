package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.UsuarioDetalhadoResponse;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.mapper.UsuarioDetalhadoMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class UsuarioDetalhadoMapperTest {

    @Test @DisplayName("Deve retornar um response corretamente quando a entidade usuário for válida")
    void deveRetornarResponseCompleto(){

        Usuario usuario = UsuarioFactory.get();

        UsuarioDetalhadoResponse response = UsuarioDetalhadoMapper.toResponse(usuario);

        assertEquals(usuario.getId(), response.getId());
        assertEquals(usuario.getNomeCompleto(), response.getNomeCompleto());
        assertEquals(usuario.getEmail(), response.getEmail());
        assertEquals(usuario.getApelido(), response.getApelido());
        assertEquals(usuario.getDataNascimento(), response.getDataNascimento());
        assertEquals(usuario.getImagemPerfil(), response.getImagemPerfil());
    }

    @Test @DisplayName("Deve retornar um response nul quando a entidade usuário for inválida")
    void deveRetornarResponseNulo(){

        Usuario usuario = new Usuario();

        UsuarioDetalhadoResponse response = UsuarioDetalhadoMapper.toResponse(usuario);

        assertNull(response.getId());
        assertNull(response.getNomeCompleto());
        assertNull(response.getEmail());
        assertNull(response.getApelido());
        assertNull(response.getDataNascimento());
        assertNull(response.getImagemPerfil());
    }

}