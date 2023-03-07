package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.GatoDetalhadoResponse;
import br.com.cwi.api.domain.Gato;

public class GatoDetalhadoMapper {

    public static GatoDetalhadoResponse toResponse(Gato entity) {
        return GatoDetalhadoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .cor(entity.getCor())
                .apelido(entity.getApelido())
                .imagem(entity.getImagem())
                .donoId(entity.getDono().getId())
                .dono(entity.getDono().getNomeCompleto())
                .build();
    }
}