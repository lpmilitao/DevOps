package br.com.cwi.api.factories;

import br.com.cwi.api.domain.Publicacao;

import java.time.LocalDate;

import static br.com.cwi.api.domain.VisualizacaoPublicacao.PRIVADO;
import static br.com.cwi.api.domain.VisualizacaoPublicacao.PUBLICO;

public class PublicacaoFactory {

    public static Publicacao get() {
        Publicacao publicacao = new Publicacao();
        publicacao.setId(SimpleFactory.getRandomLong());
        publicacao.setTitulo("Amo meu gatinho");
        publicacao.setMensagem("Meu gatinho é muito fofo");
        publicacao.setImagem("");
        publicacao.setCurtidas(10L);
        publicacao.setDataPublicacao(LocalDate.of(2023, 2, 25));
        return publicacao;
    }

    public static Publicacao getPublicacaoPrivada(){

        Publicacao publicacao = PublicacaoFactory.get();
        publicacao.setVisualizacao(PRIVADO);
        return publicacao;
    }

    public static Publicacao getPublicacaoPublica(){

        Publicacao publicacao = PublicacaoFactory.get();
        publicacao.setVisualizacao(PUBLICO);
        return publicacao;
    }

}
