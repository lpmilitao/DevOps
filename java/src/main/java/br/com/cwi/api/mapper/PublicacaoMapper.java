package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirPublicacaoRequest;
import br.com.cwi.api.controller.response.PublicacaoResponse;
import br.com.cwi.api.domain.Publicacao;

public class PublicacaoMapper {
    private static final Long NUMERO_INICIAL = 0L;

    public static Publicacao toEntity(IncluirPublicacaoRequest request) {
        return Publicacao.builder()
                .titulo(request.getTitulo())
                .mensagem(request.getMensagem())
                .imagem(request.getImagem())
                .visualizacao(request.getVisualizacaoPublicacao())
                .curtidas(NUMERO_INICIAL)
                .build();
    }

    public static PublicacaoResponse toResponse(Publicacao entity) {
        return PublicacaoResponse.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .mensagem(entity.getMensagem())
                .imagem(entity.getImagem())
                .curtidas(entity.getCurtidas())
                .visualizacao(entity.getVisualizacao())
                .dataPublicacao(entity.getDataPublicacao())
                .autorId(entity.getAutor().getId())
                .nomeAutor(entity.getAutor().getNomeCompleto())
                .comentarios((long)entity.getComentarios().size())
                .build();
    }

}
