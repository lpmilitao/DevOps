package br.com.cwi.api.factories;

import br.com.cwi.api.domain.Solicitacao;

public class SolicitacaoFactory {

    public static Solicitacao get() {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(SimpleFactory.getRandomLong());
        solicitacao.setUsuario(UsuarioFactory.get());
        solicitacao.setAmigo(UsuarioFactory.get());

        return solicitacao;
    }

}