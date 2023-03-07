package br.com.cwi.api.controller.response;

import br.com.cwi.api.domain.VisualizacaoPublicacao;
import lombok.*;

import java.time.LocalDate;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class PublicacaoResponse {

    private Long id;

    private String titulo;

    private String mensagem;

    private String imagem;

    private Long curtidas;

    private Long descurtidas;

    private VisualizacaoPublicacao visualizacao;

    private LocalDate dataPublicacao;

    private Long autorId;

    private String nomeAutor;

    private Long comentarios;

}