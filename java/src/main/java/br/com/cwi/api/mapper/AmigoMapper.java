package br.com.cwi.api.mapper;

import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.controller.response.AmigoResponse;

public class AmigoMapper {

    public static AmigoResponse toResponse(Usuario entity) {
        return AmigoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNomeCompleto())
                .apelido(entity.getApelido())
                .imagem(entity.getImagemPerfil())
                .build();
    }
}