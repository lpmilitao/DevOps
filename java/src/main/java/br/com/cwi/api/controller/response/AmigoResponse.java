package br.com.cwi.api.controller.response;

import lombok.*;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class AmigoResponse {

    private Long id;

    private String nome;

    private String apelido;

    private String imagem;

}