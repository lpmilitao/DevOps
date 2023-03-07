package br.com.cwi.api.controller.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class IncluirGatoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String cor;

    private String apelido;

    private String imagem;

}
