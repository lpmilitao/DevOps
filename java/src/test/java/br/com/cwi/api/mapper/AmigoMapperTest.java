package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.AmigoResponse;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class AmigoMapperTest {

    @Test @DisplayName("Deve mapear um amigo corretamente quando o usuário for válido")
    void deveRetornarAmigoCorretamente(){
        Usuario amigo = UsuarioFactory.get();

        AmigoResponse response = AmigoMapper.toResponse(amigo);

        assertEquals(amigo.getId(), response.getId());
        assertEquals(amigo.getNomeCompleto(), response.getNome());
        assertEquals(amigo.getApelido(), response.getApelido());
        assertEquals(amigo.getImagemPerfil(), response.getImagem());
    }

    @Test @DisplayName("Deve retornar um amigo nulo quando o usuário for inválido")
    void deveRetornarAmigoNulo(){
        Usuario amigo = new Usuario();

        AmigoResponse response = AmigoMapper.toResponse(amigo);

        assertNull(response.getId());
        assertNull(response.getNome());
        assertNull(response.getApelido());
        assertNull(response.getImagem());
    }

}