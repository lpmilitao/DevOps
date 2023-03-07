package br.com.cwi.api.controller.response;

import lombok.*;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class GatoDetalhadoResponse {

    private Long id;

    private String nome;

    private String cor;

    private String apelido;

    private String imagem;

    private Long donoId;

    private String dono;

}