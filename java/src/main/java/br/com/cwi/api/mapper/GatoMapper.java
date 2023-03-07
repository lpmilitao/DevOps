package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirGatoRequest;
import br.com.cwi.api.controller.response.GatoResponse;
import br.com.cwi.api.domain.Gato;

public class GatoMapper {

    public static GatoResponse toResponse(Gato gato){
        return  GatoResponse.builder()
                .nome(gato.getNome())
                .apelido(gato.getApelido())
                .build();
    }

    public static Gato toEntity(IncluirGatoRequest request) {
        return Gato.builder()
                .nome(request.getNome())
                .cor(request.getCor())
                .apelido(request.getApelido())
                .imagem(request.getImagem())
                .build();
    }
}
