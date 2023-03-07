package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirComentarioRequest;
import br.com.cwi.api.domain.Comentario;

public class ComentarioMapper {
    public static Comentario toEntity(IncluirComentarioRequest request) {
        return Comentario.builder()
                .mensagem(request.getMensagem())
                .build();
    }
}
