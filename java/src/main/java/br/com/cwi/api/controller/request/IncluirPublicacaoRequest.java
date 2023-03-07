package br.com.cwi.api.controller.request;

import br.com.cwi.api.domain.VisualizacaoPublicacao;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class IncluirPublicacaoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @URL
    private String imagem;

    @NotNull
    private VisualizacaoPublicacao visualizacaoPublicacao;

}
