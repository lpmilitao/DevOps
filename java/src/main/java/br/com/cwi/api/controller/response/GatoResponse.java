package br.com.cwi.api.controller.response;

import lombok.*;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class GatoResponse {

    private String nome;

    private String apelido;

}
