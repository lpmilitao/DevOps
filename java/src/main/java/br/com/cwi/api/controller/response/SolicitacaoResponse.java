package br.com.cwi.api.controller.response;

import lombok.*;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class SolicitacaoResponse {

    private Long id;

    private Long usuarioId;

    private String usuario;

    private Long amigoId;

    private String amigo;

}
