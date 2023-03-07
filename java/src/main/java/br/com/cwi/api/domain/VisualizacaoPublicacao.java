package br.com.cwi.api.domain;

public enum VisualizacaoPublicacao {
    PRIVADO("PRIVADO"),
    PUBLICO("PUBLICO");

    private String texto;

    VisualizacaoPublicacao(String texto){
        this.texto = texto;
    }
}
