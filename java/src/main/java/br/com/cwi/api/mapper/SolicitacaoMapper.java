package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.SolicitacaoResponse;
import br.com.cwi.api.domain.Solicitacao;

public class SolicitacaoMapper {
    public static SolicitacaoResponse toResponse(Solicitacao entity) {
        return SolicitacaoResponse.builder()
                .id(entity.getId())
                .usuarioId(entity.getUsuario().getId())
                .usuario(entity.getUsuario().getNomeCompleto())
                .amigoId(entity.getAmigo().getId())
                .amigo(entity.getAmigo().getNomeCompleto())
                .build();
    }
}
