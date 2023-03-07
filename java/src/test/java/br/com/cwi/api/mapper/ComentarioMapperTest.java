package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirComentarioRequest;
import br.com.cwi.api.domain.Comentario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class ComentarioMapperTest {

    @Test @DisplayName("Deve retornar comentário corretamente quando request for válido")
    void deveRetornarEntidadeCorretamente(){
        IncluirComentarioRequest request = new IncluirComentarioRequest();
        request.setMensagem("Também amo gatinhos!");

        Comentario comentario = ComentarioMapper.toEntity(request);

        assertEquals(request.getMensagem(), comentario.getMensagem());
    }

    @Test @DisplayName("Deve retornar comentário incompleto quando request não tiver mensagem")
    void deveRetornarEntidadeIncorreta(){
        IncluirComentarioRequest request = new IncluirComentarioRequest();

        Comentario comentario = ComentarioMapper.toEntity(request);

        assertNull(comentario.getMensagem());
    }

}