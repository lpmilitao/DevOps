package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.IdResponse;
import br.com.cwi.api.factories.SimpleFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class IdMapperTest {

    @Test @DisplayName("Deve retornar o id mapeado para response quando o id for válido.")
    void deveMapearCorretamenteComIdValido(){
        Long id = SimpleFactory.getRandomLong();
        IdResponse response = IdMapper.toResponse(id);

        assertEquals(response.getId(), id);
    }

    @Test @DisplayName("Deve retornar null quando o id informado for inválido.")
    void deveRetornarNullComIdInvalido(){
        Long id = null;
        IdResponse response = IdMapper.toResponse(id);

        assertNull(response.getId());
    }

}
